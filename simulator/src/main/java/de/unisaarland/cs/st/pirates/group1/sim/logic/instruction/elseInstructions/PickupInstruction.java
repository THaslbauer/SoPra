package de.unisaarland.cs.st.pirates.group1.sim.logic.instruction.elseInstructions;

import de.unisaarland.cs.st.pirates.group1.sim.util.Direction;
import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Ship;
import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Treasure;
import de.unisaarland.cs.st.pirates.group1.sim.logger.ExtendedLogWriter;

/**
 * Represents an instruction for a {@link Ship} to pick up a {@link Treasure}.
 * @author thomas
 *
 */
public class PickupInstruction extends ElseInstruction {
	Direction dir;

	/**
	 * Creates the instruction.
	 * @param logger
	 * @param elsePC
	 * @param dir The direction where the {@link Treasure} to be picked up should be.
	 */
	public PickupInstruction(ExtendedLogWriter logger, int elsePC, Direction dir) {
		super(logger, elsePC);
		this.dir = dir;
	}

	public Direction getDir() {
		return dir;
	}

	@Override
	public void execute(Ship ship) {
		// TODO Auto-generated method stub

	}

}
