package de.unisaarland.cs.st.pirates.group1.sim.logic.instruction;

import de.unisaarland.cs.st.pirates.group1.sim.logger.InfoPoint;
import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Ship;

public abstract class ElseInstruction extends Instruction {
	private int elsePC;
	
	public ElseInstruction(InfoPoint infoPoint, int elsePC){
		super(infoPoint);
		this.elsePC = elsePC;
	}
	
	protected void elseJump(Ship ship){
		//TODO implement
	}
}
