package de.unisaarland.cs.st.pirates.group1.tests.sim.gamestuff;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Faction;
import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Kraken;
import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Position;
import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Tile;
import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Worldmap;
import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Worldmap6T;
import de.unisaarland.cs.st.pirates.group1.sim.util.IllegalCallException;
import de.unisaarland.cs.st.pirates.group1.tests.testUtil.ExpectLogger;
import de.unisaarland.cs.st.pirates.group1.tests.testUtil.SRandom;

/**
 * A test class for kraken
 * @author Kerstin
 *
 */
public class KrakenTest {

	private static Worldmap myMap1, myMap2, myMap3, myMap4, myMap5;
	private static Tile tile1, tile2, tile31, tile32, tile33,tile34, tile4;
	
	@Before
	public void init(){
		
		//map with 3 island tiles and one sea tile with a kraken attached to it
	    myMap1 = new Worldmap6T(2,2, new ExpectLogger(), null);
		myMap1.createIslandTile(new Position(0,0), false);
		myMap1.createIslandTile(new Position(0,1), false);
		myMap1.createIslandTile(new Position(1,1), false);
		myMap1.createSeaTile(new Position(1,0));
		
		//map with 4 sea tiles and one kraken attached to one tile
		myMap2 = new Worldmap6T(2,2, new ExpectLogger(), null);
		myMap2.createSeaTile(new Position(1,0));
		myMap2.createSeaTile(new Position(1,1));
		myMap2.createSeaTile(new Position(0,1));
		myMap2.createSeaTile(new Position(0,0));
		

		//map with 4 sea tiles and four kraken attached to them
		myMap3 = new Worldmap6T(2,2,new ExpectLogger(),null);
		myMap3.createSeaTile(new Position(1,0));
		myMap3.createSeaTile(new Position(1,1));
		myMap3.createSeaTile(new Position(0,1));
		myMap3.createSeaTile(new Position(0,0));
		
		//another map with 4 sea tiles and one kraken attached to one tile
		myMap4 = new Worldmap6T(2,2, new ExpectLogger(), null);
		myMap4.createSeaTile(new Position(1,0));
		myMap4.createSeaTile(new Position(1,1));
		myMap4.createSeaTile(new Position(0,1));
		myMap4.createSeaTile(new Position(0,0));

		//another map (for test with second kraken)
	    myMap5 = new Worldmap6T(2,2, new ExpectLogger(), null);
		myMap5.createIslandTile(new Position(0,0), false);
		myMap5.createIslandTile(new Position(0,1), false);
		myMap5.createIslandTile(new Position(1,1), false);
		myMap5.createSeaTile(new Position(1,0));
		
		tile1 = myMap1.getTile(new Position(1,0));
		tile2 = myMap2.getTile(new Position(1,0));
		tile31 = myMap3.getTile(new Position(1,0));
		tile32 = myMap3.getTile(new Position(1,1));
		tile33 = myMap3.getTile(new Position(0,1));
		tile34 = myMap3.getTile(new Position(0,0));
		tile4 = myMap4.getTile(new Position(1,0));
		
	}
	
	@Test
	public void krakenConstructorTest() throws IllegalCallException{
		
		Kraken kraken = new Kraken(1, tile1);	
		
		assertTrue("the kraken's id should be 1",kraken.getId() == 1);
		assertTrue("the tile should be correct",kraken.getMyTile().equals(tile1));
		assertTrue("the kraken should be correct",kraken.getMyTile().getKraken().equals(kraken));
		
		tile1.detach(kraken);
	}
	
	@Test 
	public void krakenfail1Test() throws IllegalCallException{
		
		myMap1.setRandom(new SRandom(16L));
		Kraken kraken = new Kraken(1, tile1);	
		kraken.step();
		
		//the kraken's tile should not change
		assertTrue("the kraken's tile should not change",kraken.getMyTile().equals(tile1));
		
		//the tile's kraken should not change
		assertTrue("the tile's kraken should not change",tile1.getKraken().equals(kraken));
		
		tile1.detach(kraken);
	}
	
	@Test
	public void krakensucceedTest() throws IllegalCallException{
		
		myMap2.setRandom(new SRandom(1236L));
		Kraken kraken = new Kraken(1, tile2);
		kraken.step();
		
		//the tile of the kraken should be a new one
		assertFalse("the tile of the kraken should be a new one",kraken.getMyTile().equals(tile2));
		
		//the former tile of the kraken should not have a kraken anymore
		assertTrue("the former tile of the kraken should not have a kraken anymore",tile2.getKraken() == null);
		
		kraken.setMyTile(null);
	}
	
	@Test
	public void krakenfail2Test(){
		
		Kraken kraken1 = new Kraken(1, tile31);
		Kraken kraken2 = new Kraken(2, tile32);
		Kraken kraken3 = new Kraken(3, tile33);
		Kraken kraken4 = new Kraken(3, tile34);
		
		myMap3.setRandom(new SRandom(9872L));
		kraken1.step();
		
		//the kraken's tile should remain the same
		assertTrue("the kraken's tile changed",kraken1.getMyTile().equals(tile31));
		
		//the tile's kraken should not change
		assertTrue("the tile's kraken should not change",tile31.getKraken().equals(kraken1));
		
		kraken1.setMyTile(null);
		kraken2.setMyTile(null);
		kraken3.setMyTile(null);
		kraken4.setMyTile(null);
		
	}
	
	@Test
	public void krakenfail3Test(){
		
		//faction for testing purposes
		String faction_name = "test";
		
		Faction faction = new Faction(faction_name,1);
		
		//world map with three base tiles and one sea tile
		Worldmap6T myMap = new Worldmap6T(2,2, new ExpectLogger(), null);
		myMap.setRandom(new SRandom(123746L));
		myMap.createBaseTile(new Position(0,0), faction);
		myMap.createBaseTile(new Position(0,1), faction);
		myMap.createBaseTile(new Position(1,1), faction);
		myMap.createSeaTile(new Position(1,0));
		
		Tile tile = myMap.getTile(new Position(1,0));
		
		Kraken kraken = new Kraken(2,tile);
		
		kraken.step();
		
		assertTrue("kraken should stay at its place", kraken.getMyTile().equals(tile));
		assertTrue("", tile.getKraken().equals(kraken));
		
		kraken.setMyTile(null);
	}
	
	@Test
	public void krakenMoveRightTest(){
		
		myMap4.setRandom(new SRandom(123456L));
		
		Kraken kraken = new Kraken(1, tile4);
		kraken.step();
		
		int movedirection = ((SRandom)myMap4.getRandom()).getLastInt();
		
		if (movedirection == 0 || movedirection == 3){
			
			Tile newtile = myMap4.getTile(new Position(0,0));
			assertTrue("kraken must move to tile with position (0,0)",kraken.getMyTile().equals(newtile));
			assertTrue("tile must notice that a kraken is attached",newtile.getKraken().equals(kraken));
			assertTrue ("tile must notice that kraken is detached",tile4.getKraken() == null);
		}
		
		if(movedirection == 1 || movedirection == 5){
			
			Tile newtile = myMap4.getTile(new Position(1,1));
			assertTrue("kraken must move to tile with position (1,1)",kraken.getMyTile().equals(newtile));
			assertTrue("tile must notice that a kraken is attached",newtile.getKraken().equals(kraken));
			assertTrue("tile must notice that a kraken is detached",tile4.getKraken() == null);
		}
		
		if(movedirection == 2 || movedirection == 4){
			
			Tile newtile = myMap4.getTile(new Position(0,1));
			assertTrue("kraken must be on tile with position (0,1)", kraken.getMyTile().equals(newtile));
			assertTrue("tile must notice that a kraken is attached", newtile.getKraken().equals(kraken));
			assertTrue("tile must notice that a kraken is detached", tile4.getKraken() == null);
		}
		
		kraken.setMyTile(null);
	}
	
	
	@Test
	public void attachSecondKraken(){
		
		Kraken kraken1 = new Kraken(1, myMap4.getTile(new Position(0,0)));
		
		try{
			Kraken kraken2 = new Kraken(2, myMap4.getTile(new Position(0,0)));
		}
		
		//TODO: find out which exception is thrown
		catch(Exception e){
			return;
		}
		
		fail("second kraken is now attached to tile");
		kraken1.setMyTile(null);
		
	}
}
