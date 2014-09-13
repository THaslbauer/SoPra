package de.unisaarland.cs.st.pirates.group1.sim.logic.instruction;

import de.unisaarland.cs.st.pirates.group1.sim.logger.ExtendedLogWriter;
import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Ship;

/**
 * represents an Instruction from the instruction file that belongs to a faction
 * @author thomas
 *
 */
public abstract class Instruction {
	protected ExtendedLogWriter logger;
	
	/**
	 * creates a new Instruction with the ExtendedLogWriter for logging
	 * @param logger
	 */
	public Instruction(ExtendedLogWriter logger){
		this.logger = logger;
	}
	
	/**
	 * handles increasing boredom and lowering morale of the ship
	 * morale gets lowered when there were too many cycles without a positive action,
	 * this lowering of morale then resets the boredom counter.
	 * @param ship
	 */
	protected void cycle(Ship ship){
		//TODO implement
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
