package de.unisaarland.cs.st.pirates.group1.sim.logic.instruction.normalInstructions;

import de.unisaarland.cs.st.pirates.group1.sim.logger.ExtendedLogWriter;
import de.unisaarland.cs.st.pirates.group1.sim.logic.instruction.Instruction;
import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Ship;
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
		//TODO implement
	}

}
