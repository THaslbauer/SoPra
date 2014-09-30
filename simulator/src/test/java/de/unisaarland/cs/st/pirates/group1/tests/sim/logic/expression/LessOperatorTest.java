package de.unisaarland.cs.st.pirates.group1.tests.sim.logic.expression;

import org.junit.Test;

import de.unisaarland.cs.st.pirates.group1.sim.logic.expression.Expression;
import de.unisaarland.cs.st.pirates.group1.sim.logic.expression.LessOperator;
import de.unisaarland.cs.st.pirates.group1.sim.logic.expression.Literal;
import de.unisaarland.cs.st.pirates.group1.sim.logic.expression.RegisterCall;
import de.unisaarland.cs.st.pirates.group1.sim.util.Register;

public class LessOperatorTest extends BasicOperatorTest { 
	
	@Test
	public void testUnset() {
		Expression leExpr = new LessOperator(new RegisterCall(Register.SENSE_SHIPDIRECTION.ordinal()), new Literal(0));
		assertTrue("Less didn't evaluate to FALSE", leExpr.evaluate(testShip.getRegisters()) == Expression.FALSE);
	}
	
	@Test
	public void testSetGreater() {
		testShip.setRegister(Register.SENSE_SHIPDIRECTION, 5);
		Expression leExpr = new LessOperator(new RegisterCall(Register.SENSE_SHIPDIRECTION.ordinal()), new Literal(0));
		assertTrue("Less didn't evaluate to FALSE", leExpr.evaluate(testShip.getRegisters()) == Expression.FALSE);
	}
	
	@Test
	public void testSetLess() {
		testShip.setRegister(Register.SENSE_SHIPDIRECTION, 0);
		Expression leExpr = new LessOperator(new RegisterCall(Register.SENSE_SHIPDIRECTION.ordinal()), new Literal(5));
		assertTrue("Less didn't evaluate to TRUE", leExpr.evaluate(testShip.getRegisters()) == Expression.TRUE);
	}
	
}
