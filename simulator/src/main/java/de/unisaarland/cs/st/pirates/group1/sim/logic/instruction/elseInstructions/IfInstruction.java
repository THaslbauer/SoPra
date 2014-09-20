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

	@Override
	public void execute(Ship ship)
	{
		if(condition.evaluate(ship.getRegisters()) == 1)
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
