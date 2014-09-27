package de.unisaarland.cs.st.pirates.group1.sim.logic.instruction.normalInstructions;

import de.unisaarland.cs.st.pirates.group1.sim.logger.ExtendedLogWriter;
import de.unisaarland.cs.st.pirates.logger.LogWriter.Entity;
import de.unisaarland.cs.st.pirates.logger.LogWriter.Key;
import de.unisaarland.cs.st.pirates.group1.sim.logic.instruction.Instruction;
import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Base;
import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Ship;
import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Tile;
import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Treasure;

/**
 * An Instruction to drop a {@link Treasure}
 * @author thomas
 *
 */
public class DropInstruction extends Instruction {

	/**
	 * Creates the Instruction
	 * @param logger
	 */
	public DropInstruction(ExtendedLogWriter logger) {
		// TODO Auto-generated constructor stub
		super(logger);
	}
	
	@Override
	public void execute(Ship ship){
		Tile pos = ship.getMyTile();
		//get treasure and remove it all from the ship
		int treasure = ship.getLoad();
		ship.setLoad(0);
		logger.notify(Entity.SHIP, ship.getId(), Key.VALUE, 0);
		//add it to the Tile
		if(!(pos instanceof Base)){
			//drop treasure on Tile if not on base
			pos.increaseTreasure(treasure);
			//lower morale if you drop treasure on something other than a base
			//(you should only be on your base so no differentiating between factions)
			int morale = ship.getMorale();
			if(morale > 0) {
				morale -= 2;
				if(morale < 0)
					morale = 0;
				ship.setMorale(morale);
				logger.notify(Entity.SHIP, ship.getId(), Key.MORAL, morale);
			}
		}
		else {
			//increase Faction score if on base
			int newScore = ship.getFaction().increaseScore(treasure);
			logger.fleetScore(ship.getFaction().getFactionID(), newScore);
		}
		ship.increasePC();
		logger.notify(Entity.SHIP, ship.getId(), Key.PC, ship.getPC());
		super.cycle(ship);
	}

}
