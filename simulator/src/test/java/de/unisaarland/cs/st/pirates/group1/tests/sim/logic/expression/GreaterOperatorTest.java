package de.unisaarland.cs.st.pirates.group1.tests.sim.logic.expression;

import org.junit.Test;

import de.unisaarland.cs.st.pirates.group1.sim.logic.expression.Expression;
import de.unisaarland.cs.st.pirates.group1.sim.logic.expression.GreaterOperator;
import de.unisaarland.cs.st.pirates.group1.sim.logic.expression.Literal;
import de.unisaarland.cs.st.pirates.group1.sim.logic.expression.RegisterCall;
import de.unisaarland.cs.st.pirates.group1.sim.util.Register;

public class GreaterOperatorTest extends BasicOperatorTest { 
	
	@Test
	public void testUnset() {
		Expression grExpr = new GreaterOperator(new RegisterCall(Register.SENSE_SHIPDIRECTION.ordinal()), new Literal(0));
		assertTrue("Greater didn't evaluate to FALSE", grExpr.evaluate(testShip.getRegisters()) == Expression.FALSE);
	}
	
	@Test
	public void testSetGreater() {
		testShip.setRegister(Register.SENSE_SHIPDIRECTION, 5);
		Expression grExpr = new GreaterOperator(new RegisterCall(Register.SENSE_SHIPDIRECTION.ordinal()), new Literal(0));
		assertTrue("Greater didn't evaluate to TRUE", grExpr.evaluate(testShip.getRegisters()) == Expression.TRUE);
	}
	
	@Test
	public void testSetLess() {
		testShip.setRegister(Register.SENSE_SHIPDIRECTION, 0);
		Expression grExpr = new GreaterOperator(new RegisterCall(Register.SENSE_SHIPDIRECTION.ordinal()), new Literal(5));
		assertTrue("Greater didn't evaluate to FALSE", grExpr.evaluate(testShip.getRegisters()) == Expression.FALSE);
	}
	
}
