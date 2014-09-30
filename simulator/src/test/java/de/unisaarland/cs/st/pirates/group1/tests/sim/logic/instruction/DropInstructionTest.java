package de.unisaarland.cs.st.pirates.group1.tests.sim.logic.instruction;

import org.junit.Test;

import de.unisaarland.cs.st.pirates.group1.logger.AddCell;
import de.unisaarland.cs.st.pirates.group1.logger.Create;
import de.unisaarland.cs.st.pirates.group1.logger.Notify;
import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Position;
import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Treasure;
import de.unisaarland.cs.st.pirates.group1.sim.logic.instruction.Instruction;
import de.unisaarland.cs.st.pirates.group1.sim.logic.instruction.normalInstructions.DropInstruction;
import de.unisaarland.cs.st.pirates.group1.tests.sim.logic.BasicInstructionTest;
import de.unisaarland.cs.st.pirates.logger.LogWriter.Cell;
import de.unisaarland.cs.st.pirates.logger.LogWriter.Entity;
import de.unisaarland.cs.st.pirates.logger.LogWriter.Key;

public class DropInstructionTest extends BasicInstructionTest {

	@Test
	public void testDropOnWater() {
		testShip.setLoad(4);
		Instruction drop = new DropInstruction(testLogger);
		drop.execute(testShip);
		testLogger.expect(new Notify(Entity.SHIP, testShip.getId(), Key.VALUE, 0));
		Treasure t = testTile.getTreasure();
		try {
			Key[] keys = {Key.X_COORD, Key.Y_COORD, Key.VALUE};
			int[] vals = {1, 1, 4};
			testLogger.expect(new Create(Entity.TREASURE, t.getId(), keys, vals));
		}
		catch(NullPointerException e) {
			fail("No treasure was dropped");
		}
		testLogger.expect(new Notify(Entity.SHIP, testShip.getId(), Key.MORAL, 2));
		testLogger.expect(new Notify(Entity.SHIP, testShip.getId(), Key.PC, 1));
		testLogger.expectNothing();
	}
	
	@Test
	public void testDropOnBase() {
		testTile = testMap.createBaseTile(new Position(2,2), testFaction);
		testLogger.expect(new AddCell(Cell.WATER, 0, 2, 2));
		testShip = testFactory.createShip(testFaction, testTile);
		testShip.setLoad(4);
		Instruction drop = new DropInstruction(testLogger);
		drop.execute(testShip);
		testLogger.expect(new Notify(Entity.SHIP, testShip.getId(), Key.VALUE, 0));
		assertTrue("Faction score didn't get increased", testFaction.getScore() == 4);
		testLogger.expect(new Notify(Entity.SHIP, testShip.getId(), Key.PC, 1));
	}
}
