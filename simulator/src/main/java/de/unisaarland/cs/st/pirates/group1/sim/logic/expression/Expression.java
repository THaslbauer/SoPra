package de.unisaarland.cs.st.pirates.group1.sim.logic.expression;

public interface Expression
{
	public static int TRUE = 1;
	public static int FALSE = 0;
	
	/**
	 * This method evaluates the expression.
	 * 
	 * @param registers    the ship's current registers
	 * @return             0 : false   |   1 : true
	 */
	public int evaluate(int[] registers);
}
