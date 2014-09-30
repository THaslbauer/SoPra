package de.unisaarland.cs.st.pirates.group1.tests.sim.logic.instruction;

import org.junit.Test;

import de.unisaarland.cs.st.pirates.group1.logger.Notify;
import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Faction;
import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Position;
import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Ship;
import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Tile;
import de.unisaarland.cs.st.pirates.group1.sim.logic.instruction.Instruction;
import de.unisaarland.cs.st.pirates.group1.sim.logic.instruction.normalInstructions.SenseInstruction;
import de.unisaarland.cs.st.pirates.group1.sim.util.CellType;
import de.unisaarland.cs.st.pirates.group1.sim.util.Direction;
import de.unisaarland.cs.st.pirates.group1.sim.util.Heading;
import de.unisaarland.cs.st.pirates.group1.sim.util.Register;
import de.unisaarland.cs.st.pirates.group1.tests.sim.logic.BasicInstructionTest;
import de.unisaarland.cs.st.pirates.group1.tests.testUtil.ExpectLogger;
import de.unisaarland.cs.st.pirates.logger.LogWriter.Entity;
import de.unisaarland.cs.st.pirates.logger.LogWriter.Key;

public class SenseInstructionTest extends BasicInstructionTest {

	@Test
	public void testSenseTreasure() {
		Tile shipTile = testTile;
		testTile = testMap.getTile(new Position(2,2));
		testMap.createTreasure(4, testTile);
		testLogger.expect();
		Instruction sense = new SenseInstruction(testLogger, Direction.D1);
		sense.execute(testShip);
		testLogger.expect(new Notify(Entity.SHIP, testShip.getId(), Key.PC, 1));
		int[] regs = testShip.getRegisters();
		int[] compRegs = {CellType.EMPTY.ordinal(), 0, 1, 0, 0, 0, 0, 0, 0, 0, -1, -1, -1, -1, 0, 0, 4, 3};
		compareRegisters(regs, compRegs);
	}
	
	@Test
	public void testSenseBuoys2()
	{
		Tile testTile = testMap.createIslandTile(new Position(2,1), true);
		
		Tile seaTile = testMap.createSeaTile(new Position(1, 1));
		
		Ship testship = new Ship(new Faction("a", 1), 0, seaTile);
		
		SenseInstruction i = new SenseInstruction(new ExpectLogger(), Direction.D0);
		
		testship.setRegister(Register.SENSE_CELLTYPE, -2);
		testship.setRegister(Register.SENSE_SUPPLY, -2);
		testship.setRegister(Register.SENSE_TREASURE, -2);
		testship.setRegister(Register.SENSE_MARKER0, -2);
		testship.setRegister(Register.SENSE_MARKER1, -2);
		testship.setRegister(Register.SENSE_MARKER2, -2);
		testship.setRegister(Register.SENSE_MARKER3, -2);
		testship.setRegister(Register.SENSE_MARKER4, -2);
		testship.setRegister(Register.SENSE_MARKER5, -2);
		testship.setRegister(Register.SENSE_ENEMYMARKER, -2);
		testship.setRegister(Register.SENSE_SHIPTYPE, -2);
		testship.setRegister(Register.SENSE_SHIPDIRECTION, -2);
		testship.setRegister(Register.SENSE_SHIPLOADED, -2);
		testship.setRegister(Register.SENSE_SHIPCONDITION, -2);
		
		i.execute(testship);
		
		assertTrue(testship.getRegister(Register.SENSE_CELLTYPE) == CellType.ISLAND.ordinal());
		assertTrue(testship.getRegister(Register.SENSE_SUPPLY) == 1);
		assertTrue(testship.getRegister(Register.SENSE_TREASURE) == 0);
		assertTrue(testship.getRegister(Register.SENSE_MARKER0) == 0);
		assertTrue(testship.getRegister(Register.SENSE_MARKER1) == 0);
		assertTrue(testship.getRegister(Register.SENSE_MARKER2) == 0);
		assertTrue(testship.getRegister(Register.SENSE_MARKER3) == 0);
		assertTrue(testship.getRegister(Register.SENSE_MARKER4) == 0);
		assertTrue(testship.getRegister(Register.SENSE_MARKER5) == 0);
		assertTrue(testship.getRegister(Register.SENSE_ENEMYMARKER) == 0);
		assertTrue(testship.getRegister(Register.SENSE_SHIPTYPE) == -1);
		assertTrue(testship.getRegister(Register.SENSE_SHIPDIRECTION) == -1);
		assertTrue(testship.getRegister(Register.SENSE_SHIPLOADED) == -1);
		assertTrue(testship.getRegister(Register.SENSE_SHIPCONDITION) == -1);
	}
	
	
	@Test
	public void testSenseBuoys() {
		testShip.setHeading(Heading.H5);
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
		Instruction sense = new SenseInstruction(testLogger, Direction.D1);
		sense.execute(testShip);
		testLogger.expect(new Notify(Entity.SHIP, testShip.getId(), Key.PC, 1));
		int[] regs = testShip.getRegisters();
		int[] compRegs = {CellType.EMPTY.ordinal(), 0, 0, 1, 1, 1, 1, 1, 1, 1, -1, -1, -1, -1, 0, 0, 4, 3};
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
