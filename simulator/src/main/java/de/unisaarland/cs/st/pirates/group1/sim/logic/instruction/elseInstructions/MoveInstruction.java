package de.unisaarland.cs.st.pirates.group1.sim.logic.instruction.elseInstructions;

import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Base;
import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Island;
import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Kraken;
import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Ship;
import de.unisaarland.cs.st.pirates.group1.sim.logger.ExtendedLogWriter;

/**
 * Represents an instruction to move a {@link Ship}. Also handles all things that can go wrong:
 * Ship beaching on an {@link Island}
 * Ship fighting with another ship
 * Ship getting attacked by {@link Kraken}
 * Ship trying to move onto an enemy {@link Base}
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
