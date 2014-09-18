/**
 * The Testingclass for Expressions
 * 
 * @version 1.0
 * @author christopher
 */
package de.unisaarland.cs.st.pirates.group1.tests.sim.logic;

import org.junit.Test;

import static org.junit.Assert.*;
import de.unisaarland.cs.st.pirates.group1.sim.logic.expression.*;
import de.unisaarland.cs.st.pirates.group1.sim.logic.expression.Expression;
import de.unisaarland.cs.st.pirates.group1.sim.logic.expression.Literal;
import de.unisaarland.cs.st.pirates.group1.sim.logic.expression.RegisterCall;

public class ExpressionTest {

	public static void checkNFail(Expression exp, int [] registers, String why) {
		try {
			exp.evaluate(registers);
			fail("No exception thrown");
		} catch (NullPointerException npe) {
			throw npe;
		} catch (Exception e) {
			System.out.println("OK: EXPECTED \""+why+"\" GOT \""+e+"\"");
		}
	}
	
	public static void dontFail(Expression exp, int [] registers) {
		try {
			exp.evaluate(registers);
		} catch (NullPointerException npe) {
			throw npe;
		} catch (Exception e) {
			fail("FAIL: Got Exception: \""+e+"\"");
		}
	}
	
	private int [] registers1 = new int[]{0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 4, 2, 3, 0, 2, 4, 3};
	
	@Test
	public void testRegisterCall() {
		RegisterCall rc = new RegisterCall(17);
		assertTrue(rc.evaluate(registers1) == 3);
	}
	
	@Test
	public void testLiteral() {
		Literal l = new Literal(42);
		assertTrue(l.evaluate(null) == 42);
	}
	
	@Test
	public void testEqualOPFalse() {
		Literal pe = new Literal(7);
		RegisterCall rc = new RegisterCall(4);
		EqualOperator eq = new EqualOperator(rc, pe);
		assertTrue(eq.evaluate(registers1) == Expression.FALSE);
	}
	
	@Test
	public void testEqualOPTrue() {
		Literal pe = new Literal(1);
		RegisterCall rc = new RegisterCall(5);
		EqualOperator eq = new EqualOperator(rc, pe);
		assertTrue(eq.evaluate(registers1) == Expression.TRUE);
	}
	
	@Test
	public void testUnequalOPFalse() {
		Literal pe = new Literal(1);
		RegisterCall rc = new RegisterCall(4);
		UnequalOperator eq = new UnequalOperator(rc, pe);
		assertTrue(eq.evaluate(registers1) == Expression.FALSE);
	}
	
	@Test
	public void testUnequalOPTrue() {
		Literal pe = new Literal(0);
		RegisterCall rc = new RegisterCall(5);
		UnequalOperator eq = new UnequalOperator(rc, pe);
		assertTrue(eq.evaluate(registers1) == Expression.TRUE);
	}
	
	@Test
	public void testGreaterOPFalse() {
		Literal l = new Literal (3);
		RegisterCall rc = new RegisterCall(17);
		GreaterOperator gr = new GreaterOperator(rc,l);
		assertTrue(gr.evaluate(registers1) == Expression.FALSE);		
	}
	
	@Test
	public void testGreaterOPTrue() {
		Literal l = new Literal (1);
		RegisterCall rc = new RegisterCall(17);
		GreaterOperator gr = new GreaterOperator(rc,l);
		assertTrue(gr.evaluate(registers1) == Expression.TRUE);	
	}
	
	@Test
	public void testLessOPFalse() {
		Literal l = new Literal (0);
		RegisterCall rc = new RegisterCall(5);
		LessOperator gr = new LessOperator(rc,l);
		assertTrue(gr.evaluate(registers1) == Expression.FALSE);		
	}
	
	
	@Test
	public void testLessOPTrue() {
		Literal l = new Literal (42);
		RegisterCall rc = new RegisterCall(17);
		LessOperator gr = new LessOperator(rc,l);
		assertTrue(gr.evaluate(registers1) == Expression.TRUE);	
	}
	
	
	@Test
	public void testNotOperatorFalse() {
		RegisterCall rc = new RegisterCall(3);
		NotOperator neq = new NotOperator(rc);
		assertTrue(neq.evaluate(registers1) == 0);
	}
	
	@Test
	public void testNotOperatorTrue() {
		RegisterCall rc = new RegisterCall(2);
		NotOperator neq = new NotOperator(rc);
		assertTrue(neq.evaluate(registers1) == 1);
	}
	
}
