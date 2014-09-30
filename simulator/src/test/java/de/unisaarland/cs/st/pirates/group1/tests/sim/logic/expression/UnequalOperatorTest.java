package de.unisaarland.cs.st.pirates.group1.tests.sim.logic.expression;

import org.junit.Test;

import de.unisaarland.cs.st.pirates.group1.sim.logic.expression.EqualOperator;
import de.unisaarland.cs.st.pirates.group1.sim.logic.expression.Expression;
import de.unisaarland.cs.st.pirates.group1.sim.logic.expression.Literal;
import de.unisaarland.cs.st.pirates.group1.sim.logic.expression.RegisterCall;
import de.unisaarland.cs.st.pirates.group1.sim.logic.expression.UnequalOperator;
import de.unisaarland.cs.st.pirates.group1.sim.util.Register;

public class UnequalOperatorTest extends BasicOperatorTest {

	@Test
	public void testUnset() {
		Expression ueqExpr = new UnequalOperator(new RegisterCall(Register.SENSE_CELLTYPE.ordinal()), new Literal(0));
		assertTrue("Evaluation was not FALSE!", ueqExpr.evaluate(testShip.getRegisters()) == Expression.FALSE);
	}
	
	@Test
	public void testSetEqual() {
		testShip.setRegister(Register.SENSE_CELLTYPE, 0);
		int[] registers = testShip.getRegisters();
		Expression ueqExpr = new UnequalOperator(new RegisterCall(Register.SENSE_CELLTYPE.ordinal()), new Literal(0));
		assertTrue("Evaluation was not FALSE! Was "+ueqExpr.evaluate(registers), ueqExpr.evaluate(registers) == Expression.FALSE);
	}
	
	@Test
	public void testSetUnequal() {
		testShip.setRegister(Register.SENSE_CELLTYPE, 2);
		int[] registers = testShip.getRegisters();
		Expression ueqExpr = new UnequalOperator(new RegisterCall(Register.SENSE_CELLTYPE.ordinal()), new Literal(0));
		assertTrue("Evaluation was not TRUE! Was "+ueqExpr.evaluate(registers), ueqExpr.evaluate(registers) == Expression.TRUE);
		testShip.setRegister(Register.SENSE_CELLTYPE, 1);
		registers = testShip.getRegisters();
		ueqExpr = new UnequalOperator(new RegisterCall(Register.SENSE_CELLTYPE.ordinal()), new Literal(0));
		assertTrue("Evaluation was not TRUE! Was "+ueqExpr.evaluate(registers), ueqExpr.evaluate(registers) == Expression.TRUE);
	}
}
