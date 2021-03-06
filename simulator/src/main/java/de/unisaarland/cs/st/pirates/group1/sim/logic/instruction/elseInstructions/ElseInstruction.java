package de.unisaarland.cs.st.pirates.group1.sim.logic.instruction.elseInstructions;

import de.unisaarland.cs.st.pirates.group1.sim.logger.ExtendedLogWriter;
import de.unisaarland.cs.st.pirates.logger.LogWriter.Entity;
import de.unisaarland.cs.st.pirates.logger.LogWriter.Key;
import de.unisaarland.cs.st.pirates.group1.sim.logic.instruction.Instruction;
import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Ship;

/**
 * Represents an Instruction that has an else-case where the PC of a ship is set to a different number.
 * @author thomas
 *
 */
public abstract class ElseInstruction extends Instruction {
	private int elsePC;
	
	/**
	 * Creates the instruction.
	 * @param logger
	 * @param elsePC The PC that is set if the else-condition is met.
	 */
	public ElseInstruction(ExtendedLogWriter logger, int elsePC){
		super(logger);
		this.elsePC = elsePC;
	}
	
	public int getElsePC() {
		return elsePC;
	}

	/**
	 * Sets the PC of the given ship to the value of elsePC
	 * @param ship
	 */
	protected void elseJump(Ship ship)
	{
		
		// changes the pc of the ship
		ship.setPC(elsePC);
		
		//notifies PC increase
		logger.notify(Entity.SHIP, ship.getId(), Key.PC, elsePC);
		
		// increases the boredom
		this.cycle(ship);
	}
}
