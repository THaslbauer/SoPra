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
	
	/**
	 * @param index   this is the int representation
	 * 				  of the register related to the
	 *                enum class Register.
	 */
	public RegisterCall(int index)
	{
		this.index = index;
	}
	
	/**
	 * This method returns the int value of the register at position index.
	 * 
	 */
	public int evaluate(int[] registers)
	{
		return registers[index];
	}

	@Override
	public Expression getLeft()
	{
		return this;
	}

	@Override
	public Expression getRight()
	{
		return null;
	}
	
	
}
