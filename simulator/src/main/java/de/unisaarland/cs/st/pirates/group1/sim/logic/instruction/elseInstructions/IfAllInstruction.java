package de.unisaarland.cs.st.pirates.group1.sim.logic.instruction.elseInstructions;

import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Ship;
import de.unisaarland.cs.st.pirates.group1.sim.logger.ExtendedLogWriter;
import de.unisaarland.cs.st.pirates.group1.sim.logic.expression.Expression;

/**
 * Represents an if-statement where all conditions are linked together with AND.
 * @author thomas
 *
 */
public class IfAllInstruction extends ElseInstruction {
	Expression[] conditions;

	/**
	 * Creates the instruction
	 * @param logger
	 * @param elsePC The PC to set if one condition evaluates to false.
	 * @param conditions The Expressions that are the conditions.
	 */
	public IfAllInstruction(ExtendedLogWriter logger, int elsePC, Expression[] conditions) {
		super(logger, elsePC);
		this.conditions = conditions;
	}

	public Expression[] getConditions() {
		return conditions;
	}

	@Override
	public void execute(Ship ship) {
		// TODO Auto-generated method stub

	}

}
