package de.unisaarland.cs.st.pirates.group1.tests.sim.gamestuff;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		HashMap<Faction, List<Buoy>> m = tile.getBuoyMap();
		List<Buoy> l = m.get(faction);
		l.contains(buoy);
		assertTrue(tile.getBuoyMap().get(faction).contains(buoy));
	}
	
	@Test
	public void buoyConstructor2Test(){
		
		int type = -1;
	    Faction faction = new Faction("a",0);	
		int id = 1;
		Tile tile = new Sea(null,new Position(1,1));
		
		try{
			Buoy buoy = new Buoy(type,faction,id,tile);
		}
		
		catch(IllegalArgumentException e){
			return;
		}
		
		fail("type of buoy cannot be -1");
	}
	
	@Test
	public void buoyConstructor3Test(){
		
		int type = 6;
	    Faction faction = new Faction("a",0);	
		int id = 1;
		Tile tile = new Sea(null,new Position(1,1));
		
		try{
			Buoy buoy = new Buoy(type,faction,id,tile);
		}
		
		catch(IllegalArgumentException e){
			return;
		}
		
		fail("type of buoy cannot be 6");
	}
		
	
	@Test
	public void treasureConstructorTest(){
		
		assertTrue(id2 == treasure.getId());
		assertTrue(value == treasure.getValue());
		assertTrue(tile.equals(treasure.getMyTile()));
		assertTrue(tile.getTreasure().equals(treasure));
	}
	

}
