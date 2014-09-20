package de.unisaarland.cs.st.pirates.group1.sim.logic.instruction.elseInstructions;

import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Ship;
import de.unisaarland.cs.st.pirates.group1.sim.logger.ExtendedLogWriter;
import de.unisaarland.cs.st.pirates.group1.sim.logger.LogWriter.Entity;
import de.unisaarland.cs.st.pirates.group1.sim.logger.LogWriter.Key;
import de.unisaarland.cs.st.pirates.group1.sim.logic.expression.Expression;

/**
 * Represents a basic if-statement with one condition.
 * @author Nico
 *
 */
public class IfInstruction extends ElseInstruction
{
	private Expression condition;

	/**
	 * Creates the instruction
	 * @param logger
	 * @param elsePC
	 * @param condition	The {@link Expression} that is the condition.
	 */
	public IfInstruction(ExtendedLogWriter logger, int elsePC, Expression condition) {
		super(logger, elsePC);
		this.condition = condition;
	}

	public Expression getCondition() {
		return condition;
	}

	/**
	 * This method checks if the condition is true related to the
	 * given ship's registers. If the condition is true, the ship's
	 * pc will be increased by 1. If the condition is false the
	 * ship's pc will be set to the elsePc.
	 * 
	 */
	@Override
	public void execute(Ship ship)
	{
		if(ship == null)
		{
			throw new IllegalArgumentException();
		}
		
		if(condition.evaluate(ship.getRegisters()) != 1)
		{
			this.elseJump(ship);
			return;
		}
		
		
		ship.increasePC();
		this.cycle(ship);
		
	
		this.logger.notify(Entity.SHIP, ship.getId(), Key.PC, ship.getPC());

	}

}
