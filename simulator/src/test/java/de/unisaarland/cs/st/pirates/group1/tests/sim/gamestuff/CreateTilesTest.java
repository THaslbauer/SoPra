package de.unisaarland.cs.st.pirates.group1.tests.sim.gamestuff;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.*;


public class CreateTilesTest {
	private static Worldmap map;
	
	@BeforeClass
	public static void init() {
		map = new Worldmap6T(2, 2, null, null);
	}
	
	@Test
	public void createIslandTest() {
		Island island = new Island(true, map, new Position(1,3));
		assertTrue(island.isSupply());
	}

}
