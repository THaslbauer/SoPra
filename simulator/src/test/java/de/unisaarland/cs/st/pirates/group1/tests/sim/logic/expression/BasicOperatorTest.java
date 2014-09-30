package de.unisaarland.cs.st.pirates.group1.tests.sim.logic.expression;

import org.junit.Test;

import de.unisaarland.cs.st.pirates.group1.sim.logic.expression.Expression;
import de.unisaarland.cs.st.pirates.group1.sim.logic.expression.Literal;
import de.unisaarland.cs.st.pirates.group1.sim.logic.expression.Operator;
import de.unisaarland.cs.st.pirates.group1.sim.logic.expression.RegisterCall;
import de.unisaarland.cs.st.pirates.group1.sim.util.Register;

public class BasicOperatorTest extends BasicExpressionTest {

	@Test
	public void testCheckLeftRight() {
		Expression op = new Operator(new RegisterCall(Register.SENSE_SHIPCONDITION.ordinal()), new Literal(-1)) {
			
			@Override
			public int evaluate(int[] registers) {
				return checkLeftRight(registers) ? Expression.TRUE : Expression.FALSE;
			}
		};
		assertTrue("CheckLeftRight didn't realize unset SENSE_SHIPCONDITION in ship", op.evaluate(testShip.getRegisters()) == Expression.TRUE);
		testShip.setRegister(Register.SENSE_SHIPCONDITION, 2);
		assertTrue("CheckLeftRight thought Literal -1 was 'unset'", op.evaluate(testShip.getRegisters()) == Expression.FALSE);
		op = new Operator(new RegisterCall(Register.SENSE_SHIPCONDITION.ordinal()), new RegisterCall(Register.SENSE_SHIPTYPE.ordinal())) {
			
			@Override
			public int evaluate(int[] registers) {
				return checkLeftRight(registers) ? Expression.TRUE : Expression.FALSE;
			}
		};
		assertTrue("CheckLeftRight didn't realize unset SENSE_SHIPTYPE in ship", op.evaluate(testShip.getRegisters()) == Expression.TRUE);
		testShip.setRegister(Register.SENSE_SHIPTYPE, 1);
		assertTrue("CheckLeftRight thought SENSE_SHIPTYPE was unset", op.evaluate(testShip.getRegisters()) == Expression.FALSE);
	}
	
}
