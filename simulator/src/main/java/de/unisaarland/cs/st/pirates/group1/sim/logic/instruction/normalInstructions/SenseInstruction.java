package de.unisaarland.cs.st.pirates.group1.sim.logic.instruction.normalInstructions;

import java.util.List;
import java.util.Map;

import de.unisaarland.cs.st.pirates.group1.sim.logger.ExtendedLogWriter;
import de.unisaarland.cs.st.pirates.logger.LogWriter.Entity;
import de.unisaarland.cs.st.pirates.logger.LogWriter.Key;
import de.unisaarland.cs.st.pirates.group1.sim.logic.expression.Expression;
import de.unisaarland.cs.st.pirates.group1.sim.logic.instruction.Instruction;
import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Buoy;
import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Faction;
import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Ship;
import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Tile;
import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Treasure;
import de.unisaarland.cs.st.pirates.group1.sim.util.CellType;
import de.unisaarland.cs.st.pirates.group1.sim.util.Direction;
import de.unisaarland.cs.st.pirates.group1.sim.util.Register;
import de.unisaarland.cs.st.pirates.group1.sim.util.ShipType;

/**
 * An Instruction that senses a {@link Tile} in a specific direction
 * @author thomas
 *
 */
public class SenseInstruction extends Instruction {

	private Direction dir;
	
	/**
	 * Creates the Instruction
	 * @param logger
	 * @param dir The {@link Direction} in which to look
	 */
	public SenseInstruction(ExtendedLogWriter logger, Direction dir){
		super(logger);
		if(dir == null)
			throw new IllegalArgumentException("Direction can't be null");
		this.dir = dir;
	}
	
	public Direction getDir() {
		return dir;
	}

	@Override
	public void execute(Ship ship){
		//reset Registers to false / unset state
		ship.clearSenseRegisters();
		Tile tile = ship.getMyTile();
		Tile neighbourTile = tile.getNeighbour(ship.getHeading(), dir);
		//work through registers in the order of the enum
		//get other CellType
		ship.setRegister(Register.SENSE_CELLTYPE, neighbourTile.navigable(ship).ordinal());
		//get if supply
//		if(neighbourTile.navigable(ship) == CellType.ISLAND) {
			int supply = neighbourTile.isSupply() ? Expression.TRUE : Expression.FALSE;
			ship.setRegister(Register.SENSE_SUPPLY, supply);
//		}
		//get if treasure
		Treasure treasure = neighbourTile.getTreasure();
		if(!(treasure == null || treasure.getValue() == 0)) {
			ship.setRegister(Register.SENSE_TREASURE, Expression.TRUE);
		}
		else
			ship.setRegister(Register.SENSE_TREASURE, Expression.FALSE);
		//reset Buoys
		resetBuoys(ship);
		//get all buoys
		Map<Faction, List<Buoy>>buoyMap = neighbourTile.getBuoyMap();
		//look for our faction's buoys
		List<Buoy> buoys = buoyMap.get(ship.getFaction());
		//count if our Bouy list is there
		int hasOurBuoys = buoys == null ? 0 : 1;
		//now iterate over the buoys, setting the appropriate register to true
		if(hasOurBuoys == 1) {
			for(Buoy b : buoys) {
				setMarkerRegister(ship, b);
			}
		}
		//if more elements in buoy Map : set enemy marker boolean
		ship.setRegister(Register.SENSE_ENEMYMARKER, Expression.FALSE);
		for(Faction f : buoyMap.keySet()) {
			List<Buoy> buoyList = buoyMap.get(f);
			if(f != ship.getFaction() && !buoyList.isEmpty())
				ship.setRegister(Register.SENSE_ENEMYMARKER, Expression.TRUE);
		}
		//look for other ship
		Ship otherShip = neighbourTile.getShip();
		if(otherShip != null){
			//ship is there, now change registers
			//friend or foe?
			int shipType = (otherShip.getFaction() == ship.getFaction()) ? ShipType.FRIEND.ordinal() : ShipType.ENEMY.ordinal();
			ship.setRegister(Register.SENSE_SHIPTYPE, shipType);
			//direction?
			ship.setRegister(Register.SENSE_SHIPDIRECTION, otherShip.getHeading().ordinal());
			//loaded?
			int loaded = otherShip.getLoad() > 0 ? Expression.TRUE : Expression.FALSE;
			ship.setRegister(Register.SENSE_SHIPLOADED, loaded);
			//condition?
			ship.setRegister(Register.SENSE_SHIPCONDITION, otherShip.getCondition());
		}
		//notify register changes
		logger.registerChange(ship);
		//PC increase / cycle! never forget!
		logger.notify(Entity.SHIP, ship.getId(), Key.PC, ship.increasePC());
		super.cycle(ship);
	}

	private void resetBuoys(Ship ship) {
		ship.setRegister(Register.SENSE_MARKER0, Expression.FALSE);
		ship.setRegister(Register.SENSE_MARKER1, Expression.FALSE);
		ship.setRegister(Register.SENSE_MARKER2, Expression.FALSE);
		ship.setRegister(Register.SENSE_MARKER3, Expression.FALSE);
		ship.setRegister(Register.SENSE_MARKER4, Expression.FALSE);
		ship.setRegister(Register.SENSE_MARKER5, Expression.FALSE);
	}

	/**
	 * A method to encapsulate the setting of the marker registers.
	 * @param ship
	 * @param buoy
	 */
	private void setMarkerRegister(Ship ship, Buoy buoy) {
		switch(buoy.getType()) {
		case 0:
			ship.setRegister(Register.SENSE_MARKER0, Expression.TRUE);
			break;
		case 1:
			ship.setRegister(Register.SENSE_MARKER1, Expression.TRUE);
			break;
		case 2:
			ship.setRegister(Register.SENSE_MARKER2, Expression.TRUE);
			break;
		case 3:
			ship.setRegister(Register.SENSE_MARKER3, Expression.TRUE);
			break;
		case 4:
			ship.setRegister(Register.SENSE_MARKER4, Expression.TRUE);
			break;
		case 5:
			ship.setRegister(Register.SENSE_MARKER5, Expression.TRUE);
			break;
		default:
			throw new IllegalArgumentException("We shouldn't ever get another Buoy type!");
		}
	}
	
}
