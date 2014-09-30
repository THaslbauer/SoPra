package de.unisaarland.cs.st.pirates.group1.sim.logic.expression;

/**
 * This class represents an operator in a tactic programm
 * 
 * @author Nico und Schatz
 *
 */
public abstract class Operator implements Expression
{
	
	protected RegisterCall leftval;
	
	protected Primary rightval;
	
	
	public Operator(RegisterCall leftval, Primary rightval)
	{
		this.leftval  = leftval;
		this.rightval = rightval;
	}
	
	
	public Expression getLeft()
	{
		return leftval;
	}
	
	public Expression getRight()
	{
		return rightval;
	}
	
	
	/**
	 * This method checks if wether the leftval or the rightval is
	 * unset (-1). In this case it returns true. If neither the leftval 
	 * nor the rightval is unset (-1) it returns false.
	 * 
	 * @param registers
	 * @return
	 */
	public boolean checkLeftRight(int[] registers)
	{
		if(leftval.evaluate(registers) == -1 || (rightval.evaluate(registers) == -1 && rightval instanceof RegisterCall))
		{
			return true;
		}
		
		return false;
	}
	
}
