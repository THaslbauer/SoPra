package de.unisaarland.cs.st.pirates.group1.sim.logic.instruction.normalInstructions;

import de.unisaarland.cs.st.pirates.group1.sim.logger.ExtendedLogWriter;
import de.unisaarland.cs.st.pirates.group1.sim.logic.instruction.Instruction;
import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Buoy;
import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Ship;

/**
 * An Instruction that sets a {@link Buoy} on a tile
 * @author thomas
 *
 */
public class MarkInstruction extends Instruction {
	
	private int type;

	/**
	 * Creates the Instruction
	 * @param logger
	 * @param type The type of the {@link Buoy} to set
	 */
	public MarkInstruction(ExtendedLogWriter logger, int type) {
		super(logger);
		this.type = type;
	}

	public int getType() {
		return type;
	}

	@Override
	public void execute(Ship ship) {
		// TODO Auto-generated method stub
	}
}
