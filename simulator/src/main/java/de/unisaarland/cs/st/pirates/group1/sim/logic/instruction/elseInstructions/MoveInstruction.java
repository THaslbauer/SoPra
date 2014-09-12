package de.unisaarland.cs.st.pirates.group1.sim.logic.instruction.elseInstructions;

import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Ship;
import de.unisaarland.cs.st.pirates.group1.sim.logger.ExtendedLogWriter;

/**
 * Represents an instruction to move a ship. Also handles all things that can go wrong:
 * Ship beaching on an island
 * Ship fighting with another ship
 * Ship getting attacked by kraken
 * Ship trying to move onto an enemy base
 * @author thomas
 *
 */
public class MoveInstruction extends ElseInstruction {

	/**
	 * Creates the instruction.
	 * @param logger
	 * @param elsePC
	 */
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
