package de.unisaarland.cs.st.pirates.group1.sim.logic.instruction.elseInstructions;

import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Ship;
import de.unisaarland.cs.st.pirates.group1.sim.logger.ExtendedLogWriter;
import de.unisaarland.cs.st.pirates.group1.sim.logic.expression.Expression;

/**
 * Represents a basic if-statement with one condition.
 * @author thomas
 *
 */
public class IfInstruction extends ElseInstruction {
	Expression condition;

	/**
	 * Creates the instruction
	 * @param logger
	 * @param elsePC
	 * @param condition	The Expression that is the condition.
	 */
	public IfInstruction(ExtendedLogWriter logger, int elsePC, Expression condition) {
		super(logger, elsePC);
		this.condition = condition;
	}

	public Expression getCondition() {
		return condition;
	}

	@Override
	public void execute(Ship ship) {
		// TODO Auto-generated method stub

	}

}
