package de.unisaarland.cs.st.pirates.group1.sim.logic.instruction.normalInstructions;

import de.unisaarland.cs.st.pirates.group1.sim.logger.ExtendedLogWriter;
import de.unisaarland.cs.st.pirates.group1.sim.logic.instruction.Instruction;
import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Ship;

public class UnmarkInstruction extends Instruction {
	
	private int type;

	public UnmarkInstruction(ExtendedLogWriter logger, int type) {
		super(logger);
		this.type = type;
	}
	
	@Override
	public void execute(Ship ship){
		//TODO implement
	}

}
