package de.unisaarland.cs.st.pirates.group1.tests.sim.gamestuff;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Island;
import de.unisaarland.cs.st.pirates.group1.sim.util.CellType;

public class IslandTest
{
	
	private Island island;
	
	@Test
	public void navigableTest()
	{
		island = new Island(true, null, null);
		
		assertTrue(island.navigable(null) == CellType.ISLAND);
	}
	
	@Test
	public void isSupplyTest()
	{
		Island supplyIsland    = new Island(true, null, null);
		Island nonSupplyIsland = new Island(false, null, null);
		
		assertTrue("The Island's method isSupply tells false and it's true", supplyIsland.isSupply() == true);
		assertTrue("The Island's method isSupply tells true and it's false", nonSupplyIsland.isSupply() == true);
	}
}
