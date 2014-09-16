package de.unisaarland.cs.st.pirates.group1.sim.logic.instruction.elseInstructions;

import de.unisaarland.cs.st.pirates.group1.sim.util.Direction;
import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Ship;
import de.unisaarland.cs.st.pirates.group1.sim.logger.ExtendedLogWriter;

/**
 * Represents an instruction to refresh the morale of a {@link Ship}.
 * @author thomas
 
 */
public class RefreshInstruction extends ElseInstruction {
	Direction dir;

	/**
	 * Creates the instruction.
	 * @param logger
	 * @param elsePC The PC that is set when the instruction fails
	 * @param dir The {@link Direction} in which to look for the refresh
	 */
	public RefreshInstruction(ExtendedLogWriter logger, int elsePC, Direction dir) {
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
