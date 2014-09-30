package de.unisaarland.cs.st.pirates.group1.sim.logic.expression;

/**
 * This class represents a less operator in the tactic programm
 * 
 * @author Nico und Schatz
 *
 */
public class LessOperator extends Operator
{
	
	public LessOperator(RegisterCall leftval, Primary rightval)
	{
		super(leftval, rightval);
	}
	
	/**
	 * This method returns if the register in leftval is smaler
	 * than the register in rightval
	 * 
	 */
	@Override
	public int evaluate(int[] registers)
	{
		if(checkLeftRight(registers))
		{
			return 0;
		}
		
		if(leftval.evaluate(registers) < rightval.evaluate(registers))
		{
			return 1;
		}
		
		return 0;
	}
}
