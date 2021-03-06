package de.unisaarland.cs.st.pirates.group1.sim.logic.expression;

/**
 * This class represents an equal operator in a tactic programm
 * 
 * @author Nico
 * 
 */
public class EqualOperator extends Operator
{
	
	public EqualOperator(RegisterCall leftval, Primary rightval)
	{
		super(leftval,rightval);
	}
	
	/**
	 * This method returns true if the two registers in leftval and
	 * rightval are equal.
	 * 
	 */
	@Override
	public int evaluate(int[] registers)
	{
		if(checkLeftRight(registers))
		{
			return 0;
		}
		
		if(leftval.evaluate(registers) == rightval.evaluate(registers))
		{
			return 1;
		}
		
		return 0;
	}

	@Override
	public String toString() {
		return "EqualOperator [leftval=" + leftval + ", rightval=" + rightval
				+ "]";
	}
	
}
