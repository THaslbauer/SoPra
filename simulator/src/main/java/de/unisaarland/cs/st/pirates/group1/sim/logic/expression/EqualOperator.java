package de.unisaarland.cs.st.pirates.group1.sim.logic.expression;

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
	 * @param registers
	 * @return
	 */
	@Override
	public int evaluate(int[] registers)
	{
		//TODO
		return 0;
	}
	
}
