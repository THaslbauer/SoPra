package de.unisaarland.cs.st.pirates.group1.sim.logic.instruction;

public class UnmarkInstruction extends Instruction {
	
	private int type;

	public UnmarkInstruction(InfoPoint infoPoint, int type) {
		this.type = type;
		this.infoPoint = infoPoint;
	}
	
	@Override
	public void execute(Ship ship){
		//TODO implement
	}

}
