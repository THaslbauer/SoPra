package de.unisaarland.cs.st.pirates.group1.sim.logic.instruction;

public class SenseInstruction extends Instruction {

	private Direction dir;
	
	public SenseInstruction(InfoPoint infoPoint, Direction dir){
		this.dir = dir;
		this.infoPoint = infoPoint;
	}
	
	
	@Override
	public void execute(Ship ship){
		//TODO implement
	}
	
}
