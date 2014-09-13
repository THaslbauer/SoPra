package de.unisaarland.cs.st.pirates.group1.sim.logic.instruction.normalInstructions;

import de.unisaarland.cs.st.pirates.group1.sim.logger.ExtendedLogWriter;
import de.unisaarland.cs.st.pirates.group1.sim.logic.instruction.Instruction;
import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Ship;

/**
 * An Instruction that removes a buoy from a Tile
 * @author thomas
 *
 */
public class UnmarkInstruction extends Instruction {
	
	private int type;

	/**
	 * creates the Instruction
	 * @param logger the Logger to connect to
	 * @param type Boytype to unmark
	 */
	public UnmarkInstruction(ExtendedLogWriter logger, int type) {
		super(logger);
		this.type = type;
	}
	
	public int getType() {
		return type;
	}

	@Override
	public void execute(Ship ship){
		//TODO implement
	}

}
