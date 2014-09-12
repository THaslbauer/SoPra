package de.unisaarland.cs.st.pirates.group1.sim.logic.instruction.elseInstructions;

import de.unisaarland.cs.st.pirates.group1.sim.util.Direction;
import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Ship;
import de.unisaarland.cs.st.pirates.group1.sim.logger.ExtendedLogWriter;
/**
 * Represents an instruction to repair a ship.
 * @author thomas
 *
 */
public class RepairInstruction extends ElseInstruction {

	/**
	 * Creates the instruction.
	 * @param logger
	 * @param elsePC The PC to set if the repair fails
	 */
	public RepairInstruction(ExtendedLogWriter logger, int elsePC) {
		super(logger, elsePC);
	}

	@Override
	public void execute(Ship ship) {
		// TODO Auto-generated method stub

	}

}
