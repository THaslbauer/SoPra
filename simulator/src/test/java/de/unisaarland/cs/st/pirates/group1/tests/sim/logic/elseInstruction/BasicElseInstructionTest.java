package de.unisaarland.cs.st.pirates.group1.tests.sim.logic.elseInstruction;

import org.junit.Test;

import de.unisaarland.cs.st.pirates.group1.logger.Notify;
import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Ship;
import de.unisaarland.cs.st.pirates.group1.sim.logic.instruction.elseInstructions.ElseInstruction;
import de.unisaarland.cs.st.pirates.group1.tests.sim.logic.BasicInstructionTest;
import de.unisaarland.cs.st.pirates.logger.LogWriter.Entity;
import de.unisaarland.cs.st.pirates.logger.LogWriter.Key;

public class BasicElseInstructionTest extends BasicInstructionTest {

	@Test
	public void testElseJump() {
		ElseInstruction elseIn = new ElseInstruction(this.testLogger, 5) {
			
			@Override
			public void execute(Ship ship) {
				elseJump(ship);
			}
		};
		elseIn.execute(testShip);
		testLogger.expect(new Notify(Entity.SHIP, testShip.getId(), Key.PC, 5));
	}
	
}
