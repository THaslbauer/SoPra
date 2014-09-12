package sim.logic.instruction;


public class GotoInstruction extends Instruction {
	private int address;

	public GotoInstruction(InfoPoint infoPoint, int address) {
		this.address = address;
		this.infoPoint = infoPoint;
	}
	
	public void execute(Ship ship){
		//TODO implement
	}

}
