package de.unisaarland.cs.st.pirates.group1.sim.logic.instruction.elseInstructions;

import java.util.Random;

import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Ship;
import de.unisaarland.cs.st.pirates.group1.sim.logger.ExtendedLogWriter;
/**
 * Represents a coin flip that jumps to a specific part of the program.
 * @author thomas
 *
 */
public class FlipZeroInstruction extends ElseInstruction {
	
	private Random rand;

	//TODO add javadoc when i found out which number triggers the elseJump
	public FlipZeroInstruction(ExtendedLogWriter logger, int elsePC, Random rand) {
		super(logger, elsePC);
		this.rand = rand;
	}

	@Override
	public void execute(Ship ship) {
		// TODO Auto-generated method stub

	}

}
