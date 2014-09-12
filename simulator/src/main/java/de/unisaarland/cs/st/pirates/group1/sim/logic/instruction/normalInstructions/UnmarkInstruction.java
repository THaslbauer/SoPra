package de.unisaarland.cs.st.pirates.group1.sim.logic.instruction.normalInstructions;

import de.unisaarland.cs.st.pirates.group1.sim.logger.InfoPoint;
import de.unisaarland.cs.st.pirates.group1.sim.logic.instruction.Instruction;
import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Ship;

public class UnmarkInstruction extends Instruction {
	
	private int type;

	public UnmarkInstruction(InfoPoint infoPoint, int type) {
		super(infoPoint);
		this.type = type;
	}
	
	@Override
	public void execute(Ship ship){
		//TODO implement
	}

}
