package sim.logic.instruction;

public abstract class Instruction {
	protected InfoPoint infoPoint;
	
	public Instruction(InfoPoint infoPoint){
		this.infoPoint = infoPoint;
	}
	
	protected boolean cycle(Ship ship){
		//TODO implement
	}
	
	public abstract void execute(Ship ship);
}
