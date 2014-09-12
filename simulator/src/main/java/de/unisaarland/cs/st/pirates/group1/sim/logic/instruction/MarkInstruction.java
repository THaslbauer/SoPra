package de.unisaarland.cs.st.pirates.group1.sim.logic.instruction;

import de.unisaarland.cs.st.pirates.group1.sim.logger.InfoPoint;
import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Ship;

public class MarkInstruction extends Instruction {
	
	private int type;

	public MarkInstruction(InfoPoint infoPoint, int type) {
		super(infoPoint);
		this.type = type;
	}

	@Override
	public void execute(Ship ship) {
		// TODO Auto-generated method stub
	}
}
