package de.unisaarland.cs.st.pirates.group1.sim.logic.expression;

/**
 * This class represents a register in a condition of an if in the
 * tactic programm
 * 
 * @author Nico
 *
 */
public class RegisterCall extends Primary
{
	
	private int index;
	
	//TODO javadoc
	public RegisterCall(int index)
	{
		this.index = index;
	}
	
	/**
	 * This method returns the int value of the register index.
	 * 
	 */
	public int evaluate(int[] registers)
	{
		return registers[index];
	}
}
