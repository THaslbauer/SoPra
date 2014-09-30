package de.unisaarland.cs.st.pirates.group1.sim.logic.expression;

/**
 * This class represents an unequal operator in the tactic programm
 * 
 * @author Nico
 *
 */
public class UnequalOperator extends Operator{
	
	
	public UnequalOperator(RegisterCall leftval, Primary rightval)
	{
		super(leftval, rightval);
	}

	/**
	 * This method returns if the register in leftval is not
	 * equal to the one in rightval.
	 * 
	 */
	public int evaluate (int[] registers)
	{
		if(checkLeftRight(registers))
		{
			return 0;
		}
		
		if(leftval.evaluate(registers) != rightval.evaluate(registers))
		{
			return 1;
		}
		
		return 0;
	}
}
