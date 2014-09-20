package de.unisaarland.cs.st.pirates.group1.sim.logic.instruction.elseInstructions;

import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Ship;
import de.unisaarland.cs.st.pirates.group1.sim.logger.ExtendedLogWriter;
import de.unisaarland.cs.st.pirates.group1.sim.logger.LogWriter.Entity;
import de.unisaarland.cs.st.pirates.group1.sim.logger.LogWriter.Key;
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

	@Override
	public void execute(Ship ship)
	{
		int value = 1;
		
		for(Expression condition : conditions)
		{
			value = value & condition.evaluate(ship.getRegisters());
		}
		
		if(value == 1)
		{
			ship.increasePC();
			
			this.logger.notify(Entity.SHIP, ship.getId(), Key.PC, ship.getPC());
			
			this.cycle(ship);
			return;
		}
		
		this.elseJump(ship);
		this.cycle(ship);
		return;
	}

}
