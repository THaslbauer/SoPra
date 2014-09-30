package de.unisaarland.cs.st.pirates.group1.tests.sim.logic.elseInstruction;

import de.unisaarland.cs.st.pirates.group1.sim.logic.expression.Expression;
import de.unisaarland.cs.st.pirates.group1.sim.logic.instruction.Instruction;
import de.unisaarland.cs.st.pirates.group1.sim.logic.instruction.elseInstructions.IfAnyInstruction;
import de.unisaarland.cs.st.pirates.group1.sim.util.Register;

public class IfAnyInstructionTest extends BasicElseInstructionTest {

	public void testSenseSingleIfs() {
		testShip.setRegister(Register.SENSE_CELLTYPE, 2);
		
	}
	
	private void testRegisterIf(Register r, int val, Expression [] e) {
		setUp();
		testShip.setRegister(r, val);
		Instruction ifAny = new IfAnyInstruction(testLogger, 80, e);
		ifAny.execute(testShip);
	}
	
}
