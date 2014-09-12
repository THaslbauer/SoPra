package de.unisaarland.cs.st.pirates.group1.sim.logic.instruction.elseInstructions;

import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Ship;
import de.unisaarland.cs.st.pirates.group1.sim.logger.ExtendedLogWriter;

public class MoveInstruction extends ElseInstruction {

	public MoveInstruction(ExtendedLogWriter logger, int elsePC) {
		super(logger, elsePC);
	}

	@Override
	public void execute(Ship ship) {
		// TODO Auto-generated method stub

	}
	
	private boolean cleanup(Ship ship){
		//TODO implement
		return false;
	}

}
