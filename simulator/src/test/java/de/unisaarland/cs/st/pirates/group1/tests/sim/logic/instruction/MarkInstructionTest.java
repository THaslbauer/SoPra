package de.unisaarland.cs.st.pirates.group1.tests.sim.logic.instruction;

import org.junit.Test;

import de.unisaarland.cs.st.pirates.group1.sim.logic.instruction.Instruction;
import de.unisaarland.cs.st.pirates.group1.sim.logic.instruction.normalInstructions.MarkInstruction;
import de.unisaarland.cs.st.pirates.group1.tests.sim.logic.BasicInstructionTest;

public class MarkInstructionTest extends BasicInstructionTest {

	@Test
	public void testMarkInstructionEmpty() {
		Instruction markInstruction = new MarkInstruction(testLogger, 2);
		markInstruction.execute(testShip);
		
	}
	
}
