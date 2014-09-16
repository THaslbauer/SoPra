package de.unisaarland.cs.st.pirates.group1.tests.sim.gamestuff;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.*;

/**
 * A test class for buoys and treasures
 * @author Kerstin
 *
 */
public class BuoyAndTreasureTest {

	private static Buoy buoy;
	private static int type;
	private static Faction faction;
	private static int id;
	private static Tile tile;
	
	private static Treasure treasure;
	private static int value;
	private static int id2;
	
	
	@BeforeClass
	public static void init(){
		
		//Everything that is needed for a buoy
	    type = 0;
	    faction = new Faction("a",0);	
		id = 1;
		tile = new Sea(null,new Position(1,1));
		
		buoy = new Buoy(type,faction,id,tile);
		
		//Everything that is needed for a treasure
		value = 30;
		id2 = 12;
		
		treasure = new Treasure(value, id2, tile);
		
	}
	
	@Test
	public void buoyConstructorTest(){
		
		assertTrue(type == buoy.getType());
		assertTrue(faction.equals(buoy.getFaction()));
		assertTrue(id == buoy.getId());
		assertTrue(tile.equals(buoy.getMyTile()));
		
		//TODO: Test if tile noticed that a buoy is attached to it
	}
		
	
	@Test
	public void treasureConstructorTest(){
		
		assertTrue(id2 == treasure.getId());
		assertTrue(value == treasure.getValue());
		assertTrue(tile.equals(treasure.getMyTile()));
	}
	
	//TODO: test if tile noticed that a treasure is attached to it

}
