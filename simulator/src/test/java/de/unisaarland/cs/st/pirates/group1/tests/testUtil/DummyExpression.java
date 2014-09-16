package de.unisaarland.cs.st.pirates.group1.tests.testUtil;

import de.unisaarland.cs.st.pirates.group1.sim.logic.expression.Expression;

public class DummyExpression implements Expression {
	
	private int result;
	
	public DummyExpression(int res) {
		result = res;
	}
	
	@Override
	public int evaluate(int[] registers) {
		return result;
	}

}
