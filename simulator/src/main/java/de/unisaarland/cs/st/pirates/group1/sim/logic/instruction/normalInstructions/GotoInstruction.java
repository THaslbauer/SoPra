package de.unisaarland.cs.st.pirates.group1.sim.logic.instruction.normalInstructions;

import de.unisaarland.cs.st.pirates.group1.sim.logger.ExtendedLogWriter;
import de.unisaarland.cs.st.pirates.logger.LogWriter.Entity;
import de.unisaarland.cs.st.pirates.logger.LogWriter.Key;
import de.unisaarland.cs.st.pirates.group1.sim.logic.instruction.Instruction;
import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Ship;

/**
 * An Instruction that sets the PC of a ship
 * @author thomas
 *
 */
public class GotoInstruction extends Instruction {
	private int address;

	/**
	 * Creates the Instruction
	 * @param logger
	 * @param address The new PC of the ship
	 */
	public GotoInstruction(ExtendedLogWriter logger, int address) {
		super(logger);
		this.address = address;
	}
	
	public int getAddress() {
		return address;
	}

	public void execute(Ship ship){
		ship.setPC(address);
		logger.notify(Entity.SHIP, ship.getId(), Key.PC, address);
		super.cycle(ship);
	}

}
