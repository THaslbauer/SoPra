package de.unisaarland.cs.st.pirates.group1.sim.logic.expression;

/**
 * This class represents the greater operator in a tactic programm
 * 
 * @author Nico
 *
 */
public class GreaterOperator extends Operator
{

	public GreaterOperator(RegisterCall leftval, Primary rightval)
	{
		super(leftval, rightval);
	}
	
	/**
	 * This method returns if the register in leftval is greater than
	 * the register in rightval.
	 * 
	 */
	@Override
	public int evaluate (int[] registers)
	{
		if(checkLeftRight(registers))
		{
			return 0;
		}
		
		if(leftval.evaluate(registers) > rightval.evaluate(registers))
		{
			return 1;
		}
		
		return 0;
	}
}
