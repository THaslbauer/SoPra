package de.unisaarland.cs.st.pirates.group1.sim.logic.instruction.elseInstructions;

import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Ship;
import de.unisaarland.cs.st.pirates.group1.sim.logger.InfoPoint;
import de.unisaarland.cs.st.pirates.group1.sim.logic.expression.Expression;

public class IfInstruction extends ElseInstruction {
	Expression condition;

	public IfInstruction(InfoPoint infoPoint, int elsePC, Expression condition) {
		super(infoPoint, elsePC);
		this.condition = condition;
	}

	@Override
	public void execute(Ship ship) {
		// TODO Auto-generated method stub

	}

}
