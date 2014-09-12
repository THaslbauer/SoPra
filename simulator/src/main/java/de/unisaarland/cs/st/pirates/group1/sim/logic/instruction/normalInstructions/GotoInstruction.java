package de.unisaarland.cs.st.pirates.group1.sim.logic.instruction.normalInstructions;

import de.unisaarland.cs.st.pirates.group1.sim.logger.InfoPoint;
import de.unisaarland.cs.st.pirates.group1.sim.logic.instruction.Instruction;
import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Ship;


public class GotoInstruction extends Instruction {
	private int address;

	public GotoInstruction(InfoPoint infoPoint, int address) {
		super(infoPoint);
		this.address = address;
	}
	
	public void execute(Ship ship){
		//TODO implement
	}

}
