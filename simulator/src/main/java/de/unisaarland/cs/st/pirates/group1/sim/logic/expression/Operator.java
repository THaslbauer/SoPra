package de.unisaarland.cs.st.pirates.group1.sim.logic.expression;

public abstract class Operator implements Expression
{
	
	protected RegisterCall leftval;
	
	protected Primary rightval;
	
	
	public Operator(RegisterCall leftval, Primary rightval)
	{
		//TODO
	}
	
}
