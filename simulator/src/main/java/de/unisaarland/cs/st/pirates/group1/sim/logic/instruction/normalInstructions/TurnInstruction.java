package de.unisaarland.cs.st.pirates.group1.sim.logic.instruction.normalInstructions;

import de.unisaarland.cs.st.pirates.group1.sim.logger.ExtendedLogWriter;
import de.unisaarland.cs.st.pirates.group1.sim.logic.instruction.Instruction;
import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Ship;

public class TurnInstruction extends Instruction {
	private boolean left;
	
	public TurnInstruction(ExtendedLogWriter logger, boolean left){
		super(logger);
		this.left = left;
	}

	public void execute(Ship ship){
		//TODO implement
	}
	
}
