package de.unisaarland.cs.st.pirates.group1.sim.logic.instruction.normalInstructions;

import de.unisaarland.cs.st.pirates.group1.sim.logger.ExtendedLogWriter;
import de.unisaarland.cs.st.pirates.group1.sim.logic.instruction.Instruction;
import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Ship;
import de.unisaarland.cs.st.pirates.group1.sim.util.Direction;

public class SenseInstruction extends Instruction {

	private Direction dir;
	
	public SenseInstruction(ExtendedLogWriter logger, Direction dir){
		super(logger);
		this.dir = dir;
	}
	
	
	@Override
	public void execute(Ship ship){
		//TODO implement
	}
	
}
