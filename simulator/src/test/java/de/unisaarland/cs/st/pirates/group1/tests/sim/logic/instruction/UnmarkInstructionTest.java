package de.unisaarland.cs.st.pirates.group1.tests.sim.logic.instruction;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Buoy;
import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Faction;
import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Sea;
import de.unisaarland.cs.st.pirates.group1.sim.logic.instruction.Instruction;
import de.unisaarland.cs.st.pirates.group1.sim.logic.instruction.normalInstructions.UnmarkInstruction;
import de.unisaarland.cs.st.pirates.group1.tests.sim.logic.BasicInstructionTest;

public class UnmarkInstructionTest extends BasicInstructionTest { 
	
	@Test
	public void testUnmarkInstruction() {
		Map<Faction, List<Buoy> > buoyMap = testTile.getBuoyMap();
		List<Buoy> buoys = new LinkedList<Buoy>();
		buoys.add(new Buoy(1, testFaction, 0, new Sea(testMap, middle)));
		buoyMap.put(testFaction, buoys);
		Instruction unmark = new UnmarkInstruction(testLogger, 1);
		unmark.execute(testShip);
		assertTrue("Buoy was not removed", !buoyMap.containsKey(testFaction));
	}
	
	@Test
	public void testUnmarkMultipleInstructions() {
		Map<Faction, List<Buoy> > buoyMap = testTile.getBuoyMap();
		List<Buoy> buoys = new LinkedList<Buoy>();
		buoys.add(new Buoy(1, testFaction, 0, new Sea(testMap, middle)));
		buoys.add(new Buoy(0, testFaction, 0, new Sea(testMap, middle)));
		buoyMap.put(testFaction, buoys);
		Instruction unmark = new UnmarkInstruction(testLogger, 1);
		unmark.execute(testShip);
		assertTrue("Unmark removed whole Buoy list", buoyMap.containsKey(testFaction));
		assertTrue("Unmark deleted more than one Buoy", buoys.size() == 1);
		assertTrue("Unmark deleted wrong Buoy", buoys.get(0).getType() != 1);
	}
	
	@Test
	public void testDontUnmark() {
		Instruction unmark = new UnmarkInstruction(testLogger, 1);
		unmark.execute(testShip);
	}
	
}
