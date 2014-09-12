package de.unisaarland.cs.st.pirates.group1.sim.logic.instruction;

import de.unisaarland.cs.st.pirates.group1.sim.logger.InfoPoint;
import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Ship;

public abstract class Instruction {
	protected InfoPoint infoPoint;
	
	public Instruction(InfoPoint infoPoint){
		this.infoPoint = infoPoint;
	}
	
	protected boolean cycle(Ship ship){
		//TODO implement
		return false;
	}
	
	public abstract void execute(Ship ship);
}
