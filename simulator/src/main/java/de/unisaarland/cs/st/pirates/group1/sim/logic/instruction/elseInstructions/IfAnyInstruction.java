package de.unisaarland.cs.st.pirates.group1.sim.logic.instruction.elseInstructions;

import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Ship;
import de.unisaarland.cs.st.pirates.group1.sim.logger.ExtendedLogWriter;
import de.unisaarland.cs.st.pirates.group1.sim.logic.expression.Expression;

/**
 * Represents an if-statement where every condition is linked via OR.
 * @author thomas
 *
 */
public class IfAnyInstruction extends ElseInstruction {
	Expression[] conditions;

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

	@Override
	public void execute(Ship ship) {
		// TODO Auto-generated method stub

	}

}
