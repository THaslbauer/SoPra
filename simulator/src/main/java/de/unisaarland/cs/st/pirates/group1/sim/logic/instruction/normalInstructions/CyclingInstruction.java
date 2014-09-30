package de.unisaarland.cs.st.pirates.group1.sim.logic.instruction.normalInstructions;

import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Ship;
import de.unisaarland.cs.st.pirates.group1.sim.logger.ExtendedLogWriter;
import de.unisaarland.cs.st.pirates.group1.sim.logic.instruction.Instruction;

public class CyclingInstruction extends Instruction {

	public CyclingInstruction(ExtendedLogWriter logger) {
		super(logger);
	}

	@Override
	public void execute(Ship ship) {
		super.cycle(ship);
	}

}
