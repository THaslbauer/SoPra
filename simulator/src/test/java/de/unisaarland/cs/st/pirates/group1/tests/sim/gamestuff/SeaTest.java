package de.unisaarland.cs.st.pirates.group1.tests.sim.gamestuff;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Sea;
import de.unisaarland.cs.st.pirates.group1.sim.util.CellType;

public class SeaTest
{
	
	public Sea sea;
	
	@Before
	public void setUp()
	{
		sea = new Sea(null, null);
	}
	
	@Test
	public void navigableTest()
	{
		assertTrue("The Sea's method navigable should return EMPTY.", sea.navigable(null) == CellType.EMPTY);
	}
	
	@Test
	public void isSupplyTest()
	{
		assertTrue("The Sea's method isSupply should return false.", sea.isSupply() == false);
	}
}
