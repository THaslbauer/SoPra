package de.unisaarland.cs.st.pirates.group1.tests.sim.logic.instruction;

import org.junit.Test;

import de.unisaarland.cs.st.pirates.group1.logger.Notify;
import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Faction;
import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Position;
import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Tile;
import de.unisaarland.cs.st.pirates.group1.sim.logic.instruction.Instruction;
import de.unisaarland.cs.st.pirates.group1.sim.logic.instruction.normalInstructions.SenseInstruction;
import de.unisaarland.cs.st.pirates.group1.sim.util.CellType;
import de.unisaarland.cs.st.pirates.group1.sim.util.Direction;
import de.unisaarland.cs.st.pirates.group1.tests.sim.logic.BasicInstructionTest;
import de.unisaarland.cs.st.pirates.logger.LogWriter.Entity;
import de.unisaarland.cs.st.pirates.logger.LogWriter.Key;

public class SenseInstructionTest extends BasicInstructionTest {

	@Test
	public void testSenseTreasure() {
		Tile shipTile = testTile;
		testTile = testMap.getTile(new Position(2,1));
		testMap.createTreasure(4, testTile);
		testLogger.expect();
		Instruction sense = new SenseInstruction(testLogger, Direction.D0);
		sense.execute(testShip);
		testLogger.expect(new Notify(Entity.SHIP, testShip.getId(), Key.PC, 1));
		int[] regs = testShip.getRegisters();
		int[] compRegs = {CellType.EMPTY.ordinal(), 0, 1, 0, 0, 0, 0, 0, 0, 0, -1, -1, 0, -1, 0, 0, 4, 3};
		compareRegisters(regs, compRegs);
	}
	
	@Test
	public void testSenseBuoys() {
		Tile shipTile = testTile;
		testTile = testMap.getTile(new Position(2,1));
		testMap.createBuoy(0, testFaction, testTile);
		testLogger.expect();
		testMap.createBuoy(1, testFaction, testTile);
		testLogger.expect();
		testMap.createBuoy(2, testFaction, testTile);
		testLogger.expect();
		testMap.createBuoy(3, testFaction, testTile);
		testLogger.expect();
		testMap.createBuoy(4, testFaction, testTile);
		testLogger.expect();
		testMap.createBuoy(5, testFaction, testTile);
		testLogger.expect();
		Faction secondFaction = new Faction("enemy", 25);
		testMap.createBuoy(1, secondFaction, testTile);
		testLogger.expect();
		Instruction sense = new SenseInstruction(testLogger, Direction.D0);
		sense.execute(testShip);
		testLogger.expect(new Notify(Entity.SHIP, testShip.getId(), Key.PC, 1));
		int[] regs = testShip.getRegisters();
		int[] compRegs = {CellType.EMPTY.ordinal(), 0, 0, 1, 1, 1, 1, 1, 1, 1, -1, -1, 0, -1, 0, 0, 4, 3};
		compareRegisters(regs, compRegs);
	}
	
//	@Test
//	public void testSenseShip() {
//		
//	}
	
	private void compareRegisters(int[] regs, int[] compRegs) {
		for(int i = 0; i < regs.length; i++) {
			assertTrue("Failed at register "+i+", value was "+regs[i]+" instead of "+compRegs[i], regs[i] == compRegs[i]);
		}
	}
	
}
