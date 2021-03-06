package de.unisaarland.cs.st.pirates.group1.sim.logic.expression;

public interface Expression
{
	public static final int TRUE = 1;
	public static final int FALSE = 0;
	
	/**
	 * This method evaluates the expression.
	 * 
	 * @param registers    the ship's current registers
	 * @return             0 : false   |   1 : true
	 */
	int evaluate(int[] registers);
	
	Expression getLeft();
	
	Expression getRight();
}
