package de.unisaarland.cs.st.pirates.group1.sim.logic.instruction;

public class TurnInstruction extends Instruction {
	private boolean left;
	
	public TurnInstruction(InfoPoint infoPoint, boolean left){
		this.left = left;
		this.infoPoint = infoPoint;
	}

	public void execute(Ship ship){
		//TODO implement
	}
	
}
