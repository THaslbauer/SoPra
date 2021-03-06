package de.unisaarland.cs.st.pirates.group1.sim.logic.instruction.elseInstructions;

import java.util.Arrays;

import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Ship;
import de.unisaarland.cs.st.pirates.group1.sim.logger.ExtendedLogWriter;
import de.unisaarland.cs.st.pirates.logger.LogWriter.Entity;
import de.unisaarland.cs.st.pirates.logger.LogWriter.Key;
import de.unisaarland.cs.st.pirates.group1.sim.logic.expression.Expression;

/**
 * Represents an if-statement where every condition is linked via OR.
 * @author Nico
 *
 */
public class IfAnyInstruction extends ElseInstruction
{
	private Expression[] conditions;

	/**
	 * Creates the instruction.
	 * @param logger
	 * @param elsePC The PC to be set if no conditions evaluates as true
	 * @param conditions The {@link Expression} Array that holds the conditions of the instruction
	 */
	public IfAnyInstruction(ExtendedLogWriter logger, int elsePC, Expression[] conditions) {
		super(logger, elsePC);
		this.conditions = conditions;
	}

	public Expression[] getConditions() {
		return conditions;
	}
	
	/**
	 * This method checks if at least one of the conditions is true related
	 * to the given ship's registers. If at least one of the conditions is true,
	 * the ship's pc will be increased by 1. If all conditions are false, the
	 * ship's pc will be set to the eslePc.
	 * 
	 */
	@Override
	public void execute(Ship ship)
	{
		if(ship == null)
		{
			throw new IllegalArgumentException();
		}
		
		for(Expression condition : conditions)
		{
			if(condition.evaluate(ship.getRegisters()) == 1) {
				logger.notify(Entity.SHIP, ship.getId(), Key.PC, ship.increasePC());
				this.cycle(ship);
				return;
			}
		}
		this.elseJump(ship);
	}

	@Override
	public String toString() {
		return "IfAnyInstruction [conditions=" + Arrays.toString(conditions)
				+ "]";
	}
	
}
