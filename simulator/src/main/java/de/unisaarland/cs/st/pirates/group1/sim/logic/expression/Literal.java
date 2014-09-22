package de.unisaarland.cs.st.pirates.group1.sim.logic.expression;

/**
 * This class represents a number in a condition in an if
 * statement in the tactic programm
 * 
 * @author Nico
 *
 */
public class Literal extends Primary
{
	
	private int val;
	
	
	public Literal(int val)
	{
		this.val = val;
	}
	
	/**
	 * This method returns the value val.
	 * 
	 */
	public int evaluate(int[] registers)
	{
		return val;
	}

	@Override
	public Expression getLeft()
	{
		return this;
	}

	@Override
	public Expression getRight()
	{
		return null;
	}
	
}
