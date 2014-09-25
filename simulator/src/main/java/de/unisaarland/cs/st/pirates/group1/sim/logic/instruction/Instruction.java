package de.unisaarland.cs.st.pirates.group1.sim.logic.instruction;

import de.unisaarland.cs.st.pirates.group1.sim.logger.ExtendedLogWriter;
import de.unisaarland.cs.st.pirates.logger.LogWriter.Entity;
import de.unisaarland.cs.st.pirates.logger.LogWriter.Key;
import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Ship;

/**
 * Represents an Instruction from the instruction file that belongs to a faction.
 * @author thomas
 *
 */
public abstract class Instruction {
	protected ExtendedLogWriter logger;
	
	/**
	 * Creates a new Instruction with the ExtendedLogWriter for logging.
	 * @param logger
	 */
	public Instruction(ExtendedLogWriter logger){
		if(logger == null)
			throw new IllegalArgumentException("Needs a logger");
		this.logger = logger;
	}
	
	/**
	 * Handles increasing boredom and lowering morale of the ship.
	 * morale gets lowered when there were too many (default: 40) cycles without a positive action,
	 * this lowering of morale then resets the boredom counter.
	 * @param ship
	 */
	protected void cycle(Ship ship){
		if(ship.getBoredom() == 39){
			if(ship.getMorale() > 0) {
				ship.setMorale(ship.getMorale()-1);
				logger.notify(Entity.SHIP, ship.getId(), Key.MORAL, ship.getMorale());
			}
			ship.resetBoredom();
		}
		else
			ship.increaseBoredom();
	}
	
	/**
	 * this method gets implemented in the subclasses of Instruction and handles command execution
	 * @param ship
	 */
	public abstract void execute(Ship ship);
	
	public ExtendedLogWriter getLogger(){
		return this.logger;
	}
}
