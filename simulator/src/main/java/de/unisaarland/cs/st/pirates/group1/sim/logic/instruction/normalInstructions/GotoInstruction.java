package de.unisaarland.cs.st.pirates.group1.sim.logic.instruction.normalInstructions;

import de.unisaarland.cs.st.pirates.group1.sim.logger.ExtendedLogWriter;
import de.unisaarland.cs.st.pirates.group1.sim.logic.instruction.Instruction;
import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Ship;


public class GotoInstruction extends Instruction {
	private int address;

	public GotoInstruction(ExtendedLogWriter logger, int address) {
		super(logger);
		this.address = address;
	}
	
	public void execute(Ship ship){
		//TODO implement
	}

}
