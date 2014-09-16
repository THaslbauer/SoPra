package de.unisaarland.cs.st.pirates.group1.tests.sim.util;

import static org.junit.Assert.*;

import org.junit.Test;

import de.unisaarland.cs.st.pirates.group1.sim.util.*;

public class EnumTest
{

	@Test
	public void cellTypeTest()
	{
		assertTrue(CellType.ISLAND.ordinal() == 0);
		assertTrue(CellType.HOME.ordinal() == 1);
		assertTrue(CellType.ENEMYHOME.ordinal() == 2);
		assertTrue(CellType.EMPTY.ordinal() == 3);
	}
	
	@Test
	public void directionTest()
	{
		assertTrue(Direction.D0.ordinal() == 0);
		assertTrue(Direction.D1.ordinal() == 1);
		assertTrue(Direction.D2.ordinal() == 2);
		assertTrue(Direction.D3.ordinal() == 3);
		assertTrue(Direction.D4.ordinal() == 4);
		assertTrue(Direction.D5.ordinal() == 5);
		assertTrue(Direction.D6.ordinal() == 6);
	}
	
	@Test
	public void headingTest()
	{
		assertTrue(Heading.H0.ordinal() == 0);
		assertTrue(Heading.H1.ordinal() == 1);
		assertTrue(Heading.H2.ordinal() == 2);
		assertTrue(Heading.H3.ordinal() == 3);
		assertTrue(Heading.H4.ordinal() == 4);
		assertTrue(Heading.H5.ordinal() == 5);
	}
	
	@Test
	public void registerTest()
	{
		assertTrue(Register.SENSE_CELLTYPE.ordinal() == 0);
		assertTrue(Register.SENSE_SUPPLY.ordinal() == 1);
		assertTrue(Register.SENSE_TREASURE.ordinal() == 2);
		assertTrue(Register.SENSE_MARKER0.ordinal() == 3);
		assertTrue(Register.SENSE_MARKER1.ordinal() == 4);
		assertTrue(Register.SENSE_MARKER2.ordinal() == 5);
		assertTrue(Register.SENSE_MARKER3.ordinal() == 6);
		assertTrue(Register.SENSE_MARKER4.ordinal() == 7);
		assertTrue(Register.SENSE_MARKER5.ordinal() == 8);
		assertTrue(Register.SENSE_ENEMYMARKER.ordinal() == 9);
		assertTrue(Register.SENSE_SHIPTYPE.ordinal() == 10);
		assertTrue(Register.SENSE_SHIPDIRECTION.ordinal() == 11);
		assertTrue(Register.SENSE_SHIPLOADED.ordinal() == 12);
		assertTrue(Register.SENSE_SHIPCONDITION.ordinal() == 13);
		assertTrue(Register.SHIP_DIRECTION.ordinal() == 14);
		assertTrue(Register.SHIP_LOAD.ordinal() == 15);
		assertTrue(Register.SHIP_MORAL.ordinal() == 16);
		assertTrue(Register.SHIP_CONDITION.ordinal() == 17);
	}
	
	@Test 
	public void shipTypeTest()
	{
		assertTrue(ShipType.FRIEND.ordinal() == 0);
		assertTrue(ShipType.ENEMY.ordinal()  == 1);
	}
	
}
