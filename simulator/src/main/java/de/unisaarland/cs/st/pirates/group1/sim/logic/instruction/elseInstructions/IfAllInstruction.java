package de.unisaarland.cs.st.pirates.group1.sim.logic.instruction.elseInstructions;

import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Ship;
import de.unisaarland.cs.st.pirates.group1.sim.logger.ExtendedLogWriter;
import de.unisaarland.cs.st.pirates.group1.sim.logic.expression.Expression;

public class IfAllInstruction extends ElseInstruction {
	Expression[] conditions;

	public IfAllInstruction(ExtendedLogWriter logger, int elsePC, Expression[] conditions) {
		super(logger, elsePC);
		this.conditions = conditions;
	}

	@Override
	public void execute(Ship ship) {
		// TODO Auto-generated method stub

	}

}
