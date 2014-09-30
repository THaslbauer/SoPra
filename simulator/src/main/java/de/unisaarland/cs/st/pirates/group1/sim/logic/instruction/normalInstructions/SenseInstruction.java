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
import de.unisaarland.cs.st.pirates.group1.sim.util.Direction;
import de.unisaarland.cs.st.pirates.group1.sim.util.Register;

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
		resetRegisters(ship);
		Tile tile = ship.getMyTile();
		Tile neighbourTile = tile.getNeighbour(ship.getHeading(), dir);
		ship.setRegister(Register.SENSE_CELLTYPE, neighbourTile.navigable(ship).ordinal());
		int supply = neighbourTile.isSupply() ? Expression.TRUE : Expression.FALSE;
		ship.setRegister(Register.SENSE_SUPPLY, supply);
		Treasure treasure = neighbourTile.getTreasure();
		if(!(treasure == null || treasure.getValue() == 0)) {
			ship.setRegister(Register.SENSE_TREASURE, Expression.TRUE);
		}
		Map<Faction, List<Buoy>>buoyMap = neighbourTile.getBuoyMap();
		List<Buoy> buoys = buoyMap.get(ship.getFaction());
		int hasOurBuoys = buoys == null ? Expression.FALSE : Expression.TRUE;
		if(hasOurBuoys == 1) {
			for(Buoy b : buoys) {
				setMarkerRegister(ship, b);
			}
		}
		if(buoyMap.keySet().size() > hasOurBuoys)
			ship.setRegister(Register.SENSE_ENEMYMARKER, Expression.TRUE);
		Ship otherShip = neighbourTile.getShip();
		if(otherShip != null){
			int enemy = (otherShip.getFaction() == ship.getFaction()) ? Expression.FALSE : Expression.FALSE;
			ship.setRegister(Register.SENSE_SHIPTYPE, enemy);
			ship.setRegister(Register.SENSE_SHIPDIRECTION, otherShip.getHeading().ordinal());
			int loaded = otherShip.getLoad() > 0 ? Expression.TRUE : Expression.FALSE;
			ship.setRegister(Register.SENSE_SHIPLOADED, loaded);
			ship.setRegister(Register.SENSE_SHIPCONDITION, otherShip.getCondition());
		}
		logger.registerChange(ship);
		logger.notify(Entity.SHIP, ship.getId(), Key.PC, ship.increasePC());
		super.cycle(ship);
	}
	
	private void resetRegisters(Ship ship) {
		ship.setRegister(Register.SENSE_CELLTYPE, 0);
		ship.setRegister(Register.SENSE_SUPPLY, 0);
		ship.setRegister(Register.SENSE_TREASURE, 0);
		ship.setRegister(Register.SENSE_MARKER0, 0);
		ship.setRegister(Register.SENSE_MARKER1, 0);
		ship.setRegister(Register.SENSE_MARKER2, 0);
		ship.setRegister(Register.SENSE_MARKER3, 0);
		ship.setRegister(Register.SENSE_MARKER4, 0);
		ship.setRegister(Register.SENSE_MARKER5, 0);
		ship.setRegister(Register.SENSE_ENEMYMARKER, 0);
		ship.setRegister(Register.SENSE_SHIPTYPE, -1);
		ship.setRegister(Register.SENSE_SHIPDIRECTION, -1);
		ship.setRegister(Register.SENSE_SHIPLOADED, -1);
		ship.setRegister(Register.SENSE_SHIPCONDITION, -1);
	}
	
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
		}
	}
	
}
