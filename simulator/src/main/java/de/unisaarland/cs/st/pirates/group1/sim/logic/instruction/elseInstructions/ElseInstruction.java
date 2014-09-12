package de.unisaarland.cs.st.pirates.group1.sim.logic.instruction.elseInstructions;

import de.unisaarland.cs.st.pirates.group1.sim.logger.ExtendedLogWriter;
import de.unisaarland.cs.st.pirates.group1.sim.logic.instruction.Instruction;
import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Ship;

public abstract class ElseInstruction extends Instruction {
	private int elsePC;
	
	public ElseInstruction(ExtendedLogWriter logger, int elsePC){
		super(logger);
		this.elsePC = elsePC;
	}
	
	protected void elseJump(Ship ship){
		//TODO implement
	}
}
