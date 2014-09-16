package de.unisaarland.cs.st.pirates.group1.tests.sim.gamestuff;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Base;
import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Faction;
import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Ship;
import de.unisaarland.cs.st.pirates.group1.sim.util.CellType;

public class BaseTest
{	
	private Base base;
	
	private Faction faction;
	
	private Ship ship;
	
	@Before
	public void setUp()
	{
		faction = new Faction("a", 1);
		
		ship = new Ship(faction, 0, null);
		
		base = new Base(faction, null, null);
	}
	
	@Test
	public void navigableTest()
	{
		Faction enemyFaction = new Faction("e", 1);
		
		Ship friendShip = new Ship(faction, 0, null);
		Ship enemyShip  = new Ship(enemyFaction, 0, null);
		
		
		assertTrue("The base's method navigable should return HOME if there's a friendly ship.",
				base.navigable(friendShip).equals(CellType.HOME));
		
		assertTrue("The base's method navigable should return ENEMYHOME if there's an enemy ship.",
				base.navigable(enemyShip).equals(CellType.ENEMYHOME));
	}
	
	@Test
	public void isSupplyTest()
	{
		assertTrue("The base's method isSupply should return false.", base.isSupply() == false);
	}
	
	@Test
	public void getFaction()
	{
		assertTrue("The base's method getFaction returns the wrong faction.",
				base.getFaction().equals(faction));
	}
}
