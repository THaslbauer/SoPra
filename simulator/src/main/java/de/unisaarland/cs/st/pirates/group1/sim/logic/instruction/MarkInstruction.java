package de.unisaarland.cs.st.pirates.group1.sim.logic.instruction;

public class MarkInstruction extends Instruction {
	
	private int type;

	public MarkInstruction(InfoPoint infoPoint, int type) {
		this.type = type;
		this.infoPoint = infoPoint;
	}

	@Override
	public void execute(Ship ship) {
		// TODO Auto-generated method stub
	}
}
