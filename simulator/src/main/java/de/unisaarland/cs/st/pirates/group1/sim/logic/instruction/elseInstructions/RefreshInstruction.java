package de.unisaarland.cs.st.pirates.group1.sim.logic.instruction.elseInstructions;

import de.unisaarland.cs.st.pirates.group1.sim.util.Direction;
import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Ship;
import de.unisaarland.cs.st.pirates.group1.sim.logger.ExtendedLogWriter;

public class RefreshInstruction extends ElseInstruction {
	Direction dir;

	public RefreshInstruction(ExtendedLogWriter logger, int elsePC, Direction dir) {
		super(logger, elsePC);
		this.dir = dir;
	}

	@Override
	public void execute(Ship ship) {
		// TODO Auto-generated method stub

	}

}
