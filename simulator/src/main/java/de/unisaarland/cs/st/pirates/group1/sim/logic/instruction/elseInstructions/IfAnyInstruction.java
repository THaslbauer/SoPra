package de.unisaarland.cs.st.pirates.group1.sim.logic.instruction.elseInstructions;

import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Ship;
import de.unisaarland.cs.st.pirates.group1.sim.logger.InfoPoint;
import de.unisaarland.cs.st.pirates.group1.sim.logic.expression.Expression;

public class IfAnyInstruction extends ElseInstruction {
	Expression[] conditions;

	public IfAnyInstruction(InfoPoint infoPoint, int elsePC, Expression[] conditions) {
		super(infoPoint, elsePC);
		this.conditions = conditions;
	}

	@Override
	public void execute(Ship ship) {
		// TODO Auto-generated method stub

	}

}
