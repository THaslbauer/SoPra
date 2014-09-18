package de.unisaarland.cs.st.pirates.group1.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Position;
import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Tile;
import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Worldmap6T;

public class DummyTest {
	
	@Test
	public void testArray() {
		Worldmap6T map = new Worldmap6T(3,6,null,null);
		Tile tile = map.getTile(new Position(2,2));
		assertTrue(tile == null);
	}
}
