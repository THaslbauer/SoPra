package de.unisaarland.cs.st.pirates.group1.tests.sim.logic.expression;

import org.junit.Test;

import de.unisaarland.cs.st.pirates.group1.sim.logic.expression.Expression;
import de.unisaarland.cs.st.pirates.group1.sim.logic.expression.NotOperator;
import de.unisaarland.cs.st.pirates.group1.sim.logic.expression.RegisterCall;
import de.unisaarland.cs.st.pirates.group1.sim.util.Register;

public class NotOperatorTest extends BasicOperatorTest {
	
	@Test
	public void testNotUnset() {
		Expression notExpr = new NotOperator(new RegisterCall(Register.SENSE_SHIPLOADED.ordinal()));
		assertTrue("Didn't evaluate to -1", notExpr.evaluate(testShip.getRegisters()) == -1);
	}
	
	@Test
	public void testNotSetTrue() {
		testShip.setRegister(Register.SENSE_SHIPLOADED, Expression.TRUE);
		Expression notExpr = new NotOperator(new RegisterCall(Register.SENSE_SHIPLOADED.ordinal()));
		assertTrue("Didn't evaluate to FALSE", notExpr.evaluate(testShip.getRegisters()) == Expression.FALSE);
	}
	
	@Test
	public void testNotSetFalse() {
		testShip.setRegister(Register.SENSE_SHIPLOADED, Expression.FALSE);
		Expression notExpr = new NotOperator(new RegisterCall(Register.SENSE_SHIPLOADED.ordinal()));
		assertTrue("Didn't evaluate to TRUE", notExpr.evaluate(testShip.getRegisters()) == Expression.TRUE);
	}
}
