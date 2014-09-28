package de.unisaarland.cs.st.pirates.group1.sim.logic.instruction.elseInstructions;

import java.util.Random;

import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Ship;
import de.unisaarland.cs.st.pirates.group1.sim.logger.ExtendedLogWriter;
import de.unisaarland.cs.st.pirates.logger.LogWriter.Entity;
import de.unisaarland.cs.st.pirates.logger.LogWriter.Key;
/**
 * Represents a dice roll that jumps to a specific part of the program.
 * Simulates a dice roll by randomly generating an integer.
 * @author thomas
 *
 */
public class FlipZeroInstruction extends ElseInstruction {
	
	private Random rand;
	private int p;

	/**
	 * Creates the Instruction 
	 * @param logger
	 * @param elsePC The PC which the instruction jumps to if the next random int is not 0
	 * @param rand  The {@link Random} object which generates the int
	 * @param p This is the upper bound of the dice roll, the computed random integer i is 0 <= i < p
	 */
	public FlipZeroInstruction(ExtendedLogWriter logger, int elsePC, Random rand, int p) {
		super(logger, elsePC);
		if(rand == null)
			throw new IllegalArgumentException("Random can't be null");
		this.rand = rand;
		this.p = p;
	}

	public Random getRand() {
		return rand;
	}
	
	public int getP() {
		return p;
	}

	@Override
	public void execute(Ship ship) {
		if(rand.nextInt(p) == 0) {
		}
		else {
			super.elseJump(ship);
		}
		super.cycle(ship);
	}

}
