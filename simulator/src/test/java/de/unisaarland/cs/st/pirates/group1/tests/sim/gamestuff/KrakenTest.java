package de.unisaarland.cs.st.pirates.group1.tests.sim.gamestuff;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Kraken;
import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Position;
import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Tile;
import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Worldmap;
import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Worldmap6T;

/**
 * A test class for kraken
 * @author Kerstin
 *
 */
public class KrakenTest {

	private static Worldmap myMap1, myMap2, myMap3;
	private static Tile tile1, tile2, tile31, tile32, tile33,tile34;
	
	@BeforeClass
	public static void init(){
		
		//map with 3 island tiles and one sea tile with a kraken attached to it
	    myMap1 = new Worldmap6T(2,2, null, null);
		myMap1.createIslandTile(new Position(0,0), false);
		myMap1.createIslandTile(new Position(0,1), false);
		myMap1.createIslandTile(new Position(1,1), false);
		myMap1.createSeaTile(new Position(1,0));
		
		//map with 4 sea tiles and one kraken attached to one tile
		myMap2 = new Worldmap6T(2,2, null, null);
		myMap2.createSeaTile(new Position(1,0));
		myMap2.createSeaTile(new Position(1,1));
		myMap2.createSeaTile(new Position(0,1));
		myMap2.createSeaTile(new Position(0,0));

		//map with 4 sea tiles and four kraken attached to them
		myMap3 = new Worldmap6T(2,2,null,null);
		myMap3.createSeaTile(new Position(1,0));
		myMap3.createSeaTile(new Position(1,1));
		myMap3.createSeaTile(new Position(0,1));
		myMap3.createSeaTile(new Position(0,0));
		
		
		tile1 = myMap1.getTile(new Position(1,0));
		tile2 = myMap2.getTile(new Position(1,0));
		tile31 = myMap3.getTile(new Position(1,0));
		tile32 = myMap3.getTile(new Position(1,1));
		tile33 = myMap3.getTile(new Position(0,1));
		tile34 = myMap3.getTile(new Position(0,0));
		
	}
	
	@Test
	public void krakenConstructorTest(){
		
		Kraken kraken = new Kraken(1, tile1);	
		
		assertTrue(kraken.getId() == 1);
		assertTrue(kraken.getMyTile().equals(tile1));
		
		//TODO:Test if tile notices kraken
		
	}
	
	@Test 
	public void krakenfail1Test(){
		
		Kraken kraken = new Kraken(1, tile1);	
		kraken.step();
		
		assertTrue(kraken.getMyTile().equals(tile1));
	}
	
	@Test
	public void krakensucceedTest(){
		
		Kraken kraken = new Kraken(1, tile2);
		kraken.step();
		
		assertFalse(kraken.getMyTile().equals(tile2));
	}
	
	@Test
	public void krakenfail2Test(){
		
		Kraken kraken1 = new Kraken(1, tile31);
		Kraken kraken2 = new Kraken(2, tile32);
		Kraken kraken3 = new Kraken(3, tile33);
		Kraken kraken4 = new Kraken(3, tile34);
		
		kraken1.step();
		
		assertTrue(kraken1.getMyTile().equals(tile31));
		
		
	}
}
