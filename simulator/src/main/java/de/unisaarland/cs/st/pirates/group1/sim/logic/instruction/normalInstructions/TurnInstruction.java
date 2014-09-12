package de.unisaarland.cs.st.pirates.group1.sim.logic.instruction.normalInstructions;

import de.unisaarland.cs.st.pirates.group1.sim.logger.InfoPoint;
import de.unisaarland.cs.st.pirates.group1.sim.logic.instruction.Instruction;
import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Ship;

public class TurnInstruction extends Instruction {
	private boolean left;
	
	public TurnInstruction(InfoPoint infoPoint, boolean left){
		super(infoPoint);
		this.left = left;
	}

	public void execute(Ship ship){
		//TODO implement
	}
	
}
