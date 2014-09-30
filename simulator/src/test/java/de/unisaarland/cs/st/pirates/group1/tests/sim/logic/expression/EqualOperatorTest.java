package de.unisaarland.cs.st.pirates.group1.tests.sim.logic.expression;

import org.junit.Test;

import de.unisaarland.cs.st.pirates.group1.sim.logic.expression.EqualOperator;
import de.unisaarland.cs.st.pirates.group1.sim.logic.expression.Expression;
import de.unisaarland.cs.st.pirates.group1.sim.logic.expression.Literal;
import de.unisaarland.cs.st.pirates.group1.sim.logic.expression.RegisterCall;
import de.unisaarland.cs.st.pirates.group1.sim.util.Register;

public class EqualOperatorTest extends BasicOperatorTest {

	@Test
	public void testUnset() {
		Expression eqExpr = new EqualOperator(new RegisterCall(Register.SENSE_CELLTYPE.ordinal()), new Literal(0));
		assertTrue("Evaluation was not false!", eqExpr.evaluate(testShip.getRegisters()) == Expression.FALSE);
	}
	
	@Test
	public void testSetEqual() {
		testShip.setRegister(Register.SENSE_CELLTYPE, 0);
		int[] registers = testShip.getRegisters();
		Expression eqExpr = new EqualOperator(new RegisterCall(Register.SENSE_CELLTYPE.ordinal()), new Literal(0));
		assertTrue("Evaluation was not TRUE! Was "+eqExpr.evaluate(registers), eqExpr.evaluate(registers) == Expression.TRUE);
	}
	
	@Test
	public void testSetUnequal() {
		testShip.setRegister(Register.SENSE_CELLTYPE, 2);
		int[] registers = testShip.getRegisters();
		Expression eqExpr = new EqualOperator(new RegisterCall(Register.SENSE_CELLTYPE.ordinal()), new Literal(0));
		assertTrue("Evaluation was not FALSE! Was "+eqExpr.evaluate(registers), eqExpr.evaluate(registers) == Expression.FALSE);
		testShip.setRegister(Register.SENSE_CELLTYPE, 1);
		registers = testShip.getRegisters();
		eqExpr = new EqualOperator(new RegisterCall(Register.SENSE_CELLTYPE.ordinal()), new Literal(0));
		assertTrue("Evaluation was not FALSE! Was "+eqExpr.evaluate(registers), eqExpr.evaluate(registers) == Expression.FALSE);
	}
}
