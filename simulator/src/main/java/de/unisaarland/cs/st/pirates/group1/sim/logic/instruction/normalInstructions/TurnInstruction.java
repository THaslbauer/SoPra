package de.unisaarland.cs.st.pirates.group1.sim.logic.instruction.normalInstructions;

import de.unisaarland.cs.st.pirates.group1.sim.logger.ExtendedLogWriter;
import de.unisaarland.cs.st.pirates.group1.sim.logic.instruction.Instruction;
import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Ship;

/**
 * Makes a ship turn.
 * @author thomas
 *
 */
public class TurnInstruction extends Instruction {
	private boolean left;
	
	/**
	 * Create the Instruction
	 * @param logger
	 * @param left Controls if you have to turn left or right (left == true)
	 */
	public TurnInstruction(ExtendedLogWriter logger, boolean left){
		super(logger);
		this.left = left;
	}

	public boolean isLeft() {
		return left;
	}

	public void execute(Ship ship){
		//TODO implement
	}
	
}
