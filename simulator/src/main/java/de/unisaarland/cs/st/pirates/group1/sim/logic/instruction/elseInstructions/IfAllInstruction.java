package de.unisaarland.cs.st.pirates.group1.sim.logic.instruction.elseInstructions;

import java.util.Arrays;

import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Ship;
import de.unisaarland.cs.st.pirates.group1.sim.logger.ExtendedLogWriter;
import de.unisaarland.cs.st.pirates.logger.LogWriter.Entity;
import de.unisaarland.cs.st.pirates.logger.LogWriter.Key;
import de.unisaarland.cs.st.pirates.group1.sim.logic.expression.Expression;

/**
 * Represents an if-statement where all conditions are linked together with AND.
 * @author Nico
 *
 */
public class IfAllInstruction extends ElseInstruction
{
	private Expression[] conditions;

	/**
	 * Creates the instruction
	 * @param logger
	 * @param elsePC The PC to set if one condition evaluates to false.
	 * @param conditions The {@link Expression} Array that holds the conditions.
	 */
	public IfAllInstruction(ExtendedLogWriter logger, int elsePC, Expression[] conditions) {
		super(logger, elsePC);
		this.conditions = conditions;
	}

	public Expression[] getConditions() {
		return conditions;
	}
	
	/**
	 * This method checks if every condition of the conditions is
	 * true related to the given ship's registers. If every condition
	 * is true, the ship's pc will be increased by 1. Tf at least one
	 * condition is false, the ship's pc will be set to the elsePc.
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
			if(condition.evaluate(ship.getRegisters()) != 1) {
				this.elseJump(ship);
				return;
			}
		}
		
		logger.notify(Entity.SHIP, ship.getId(), Key.PC, ship.increasePC());
		this.cycle(ship);
	}

	@Override
	public String toString() {
		return "IfAllInstruction [conditions=" + Arrays.toString(conditions)
				+ "]";
	}

}
