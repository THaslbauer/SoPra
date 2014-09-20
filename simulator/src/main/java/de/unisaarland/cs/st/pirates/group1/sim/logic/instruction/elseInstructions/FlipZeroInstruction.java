package de.unisaarland.cs.st.pirates.group1.sim.logic.instruction.elseInstructions;

import java.util.Random;

import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Ship;
import de.unisaarland.cs.st.pirates.group1.sim.logger.ExtendedLogWriter;
import de.unisaarland.cs.st.pirates.group1.sim.logger.LogWriter.Entity;
import de.unisaarland.cs.st.pirates.group1.sim.logger.LogWriter.Key;
/**
 * Represents a coin flip that jumps to a specific part of the program.
 * Simulates a coin flip by randomly generating a 1 or a 0.
 * @author thomas
 *
 */
public class FlipZeroInstruction extends ElseInstruction {
	
	private Random rand;

	/**
	 * Creates the Instruction 
	 * @param logger
	 * @param elsePC The PC which the instruction jumps to if the next random int is 1
	 * @param rand  The {@link Random} object which generates the int
	 */
	public FlipZeroInstruction(ExtendedLogWriter logger, int elsePC, Random rand) {
		super(logger, elsePC);
		if(rand == null)
			throw new IllegalArgumentException("Random can't be null");
		this.rand = rand;
	}

	public Random getRand() {
		return rand;
	}

	@Override
	public void execute(Ship ship) {
		if(rand.nextInt(2) == 0) {
			logger.notify(Entity.SHIP, ship.getId(), Key.PC, ship.increasePC());
		}
		else {
			super.elseJump(ship);
		}
		super.cycle(ship);
	}

}
