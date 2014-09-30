package de.unisaarland.cs.st.pirates.group1.sim.logic.expression;

/**
 * This class represents a not operator in the tactic programm
 * 
 * @author Nico
 *
 */
public class NotOperator extends Operator
{
	
	public NotOperator(RegisterCall val)
	{
		super(val, null);
	}
	
	/**
	 * This method returns the negated value of the register in leftval.
	 * 
	 */
	public int evaluate(int[] registers)
	{
		if(leftval.evaluate(registers) == -1)
			return -1;
		if(leftval.evaluate(registers) == 0)
		{
			return 1;
		}
		else
			return 0;
	}
	
}
