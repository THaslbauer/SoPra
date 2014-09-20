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
	
}
