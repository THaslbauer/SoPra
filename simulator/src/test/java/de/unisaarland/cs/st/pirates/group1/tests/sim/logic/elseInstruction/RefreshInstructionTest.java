package de.unisaarland.cs.st.pirates.group1.tests.sim.logic.elseInstruction;

import org.junit.Test;

import de.unisaarland.cs.st.pirates.group1.logger.Notify;
import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Position;
import de.unisaarland.cs.st.pirates.group1.sim.logic.instruction.Instruction;
import de.unisaarland.cs.st.pirates.group1.sim.logic.instruction.elseInstructions.RefreshInstruction;
import de.unisaarland.cs.st.pirates.group1.sim.util.Direction;
import de.unisaarland.cs.st.pirates.group1.sim.util.Heading;
import de.unisaarland.cs.st.pirates.logger.LogWriter.Entity;
import de.unisaarland.cs.st.pirates.logger.LogWriter.Key;

public class RefreshInstructionTest extends BasicElseInstructionTest {

	@Test
	public void testRefreshOnSupply() {
		testShip.setHeading(Heading.H5);
		testShip.setMorale(3);
		testTile = testMap.createIslandTile(new Position(2,1), true);
		testLogger.clear();
		Instruction ref = new RefreshInstruction(testLogger, 5, Direction.D1);
		ref.execute(testShip);
		assertTrue("Wrong PC, was "+testShip.getPC(), testShip.getPC() == 1);
		assertTrue("Wrong morale, was "+testShip.getMorale(), testShip.getMorale() == 4);
		testLogger.expect(new Notify(Entity.SHIP, testShip.getId(), Key.PC, 1));
		testLogger.expect(new Notify(Entity.SHIP, testShip.getId(), Key.MORAL, 4));
		
	}
	
}
