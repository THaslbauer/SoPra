package de.unisaarland.cs.st.pirates.group1.tests.sim.gamestuff;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Buoy;
import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.EntityFactory;
import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Faction;
import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Kraken;
import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Position;
import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Ship;
import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Tile;
import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Treasure;
import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Worldmap;
import de.unisaarland.cs.st.pirates.group1.sim.util.CellType;
import de.unisaarland.cs.st.pirates.group1.tests.testUtil.DumbWorldmap;
import junit.framework.TestCase;

public class FactoryTest extends TestCase {
	
	private EntityFactory factory;
	private Faction faction;
	private Tile tile;
	private Worldmap map;
	
	
	@BeforeClass
	public void setUpBeforeClass(){
		
	}
	
	@Before
	public void setUp(){
		factory = new EntityFactory();
		faction = new Faction("TestFaction", 0);
		tile = new Tile(map, new Position(0,0)) {
			
			@Override
			public CellType navigable(Ship ship) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public boolean isSupply() {
				// TODO Auto-generated method stub
				return false;
			}
		};
		map = new DumbWorldmap();
	}
	
	/**
	 * Tests if the create*-Methods create the needed Objects and build them properly
	 */
	@Test
	public void testBasicShipCreation(){
		Ship ship = factory.createShip(faction, tile);
		assertFalse("No Ship returned by factory", ship == null);
		assertTrue("Ship's ID wasn't 0, was "+ship.getId(), ship.getId() == 0);
		assertTrue("Ship's tile wasn't set properly", ship.getMyTile() == tile);
		assertTrue("Ship's faction wasn't set properly", ship.getFaction() == faction);
	}
	
	/**
	 * Tests if Treasure creation works properly
	 */
	@Test
	public void testBasicTreasureCreation(){
		int value = 5;
		Treasure treasure = factory.createTreasure(value, tile);
		assertFalse("No Treasure returned by factory", treasure == null);
		assertTrue("Treasure's ID wasn't 0, was "+treasure.getId(), treasure.getId() == 0);
		assertTrue("Treasure's tile wasn't set properly", treasure.getMyTile() == tile);
		assertTrue("Treasure's value wasn't initially 5, was "+ treasure.getValue(), treasure.getValue() == value);
	}
	
	/**
	 * Tests if Buoy creation works properly
	 */
	@Test
	public void testBasicBuoyCreation(){
		int type = 3;
		Buoy buoy = factory.createBuoy(type, faction, tile);
		assertFalse("No Buoy returned by factory", buoy == null);
		assertTrue("Buoy's ID wasn't 0, was "+buoy.getId(), buoy.getId() == 0);
		assertTrue("Buoy's tile wasn't set properly", buoy.getMyTile() == tile);
		assertTrue("Buoy's type wasn't "+type+", was "+buoy.getType(), buoy.getType() == type);
	}
	
	/**
	 * Tests if Kraken creation works properly
	 */
	@Test
	public void testBasicKrakenCreation(){
		Kraken kraken = factory.releaseTheKraken(tile);
		assertFalse("No Kraken returned by factory", kraken == null);
		assertTrue("Kraken's ID wasn't 0, was "+kraken.getId(), kraken.getId() == 0);
		assertTrue("Kraken's tile wasn't set properly", kraken.getMyTile() == tile);
	}
	
	/**
	 * Tests if IDs of Ships are properly incrementing
	 */
	@Test
	public void testIDCountingShip(){
		//test sequential ID creation
		Ship ship = factory.createShip(faction, tile);
		assertTrue("Expected next Ship ID 1, instead was ID "+factory.getShipNextId(), factory.getShipNextId() == 1);
		assertTrue("Expected Ship ID 0, instead had ID "+ship.getId(), ship.getId() == 0);
		tile = new Tile(map, new Position(0,0)) {
			
			@Override
			public CellType navigable(Ship ship) {
				return null;
			}
			
			@Override
			public boolean isSupply() {
				return false;
			}
		};
		ship = factory.createShip(faction, tile);
		assertTrue("Expected Ship ID 1, instead had ID "+ship.getId(), ship.getId() == 1);
		tile = new Tile(map, new Position(0,0)) {
			
			@Override
			public CellType navigable(Ship ship) {
				return null;
			}
			
			@Override
			public boolean isSupply() {
				return false;
			}
		};
		//Test reset of sequential IDs on new instance of class
		factory = new EntityFactory();
		ship = factory.createShip(faction, tile);
		assertTrue("Expected Ship ID 0, instead had ID "+ship.getId(), ship.getId() == 0);
		
	}
	
	/**
	 * Tests if IDs of Treasures are properly incrementing
	 */
	@Test
	public void testIDCountingTreasure(){
		//test sequential ID creation
		Treasure treasure = factory.createTreasure(0, tile);
		assertTrue("Expected next Treasure ID 1, instead was ID "+factory.getTreasureNextId(), factory.getTreasureNextId() == 1);
		assertTrue("Expected Treasure ID 0, instead had ID "+treasure.getId(), treasure.getId() == 0);
		tile = new Tile(map, new Position(0,0)) {
			
			@Override
			public CellType navigable(Ship ship) {
				return null;
			}
			
			@Override
			public boolean isSupply() {
				return false;
			}
		};
		treasure = factory.createTreasure(0, tile);
		assertTrue("Expected Treasure ID 1, instead had ID "+treasure.getId(), treasure.getId() == 1);
		tile = new Tile(map, new Position(0,0)) {
			
			@Override
			public CellType navigable(Ship ship) {
				return null;
			}
			
			@Override
			public boolean isSupply() {
				return false;
			}
		};
		//Test reset of sequential IDs on new instance of class
		factory = new EntityFactory();
		treasure = factory.createTreasure(0, tile);
		assertTrue("Expected Treasure ID 0, instead had ID "+treasure.getId(), treasure.getId() == 0);
		
	}
	
	/**
	 * Tests if IDs of Buoys are properly incrementing
	 */
	@Test
	public void testIDCountingBuoy(){
		//test sequential ID creation
		Buoy buoy = factory.createBuoy(0, faction, tile);
		assertTrue("Expected next Buoy ID 1, instead was ID "+factory.getBuoyNextId(), factory.getBuoyNextId() == 1);
		assertTrue("Expected Buoy ID 0, instead had ID "+buoy.getId(), buoy.getId() == 0);
		tile = new Tile(map, new Position(0,0)) {
			
			@Override
			public CellType navigable(Ship ship) {
				return null;
			}
			
			@Override
			public boolean isSupply() {
				return false;
			}
		};
		buoy = factory.createBuoy(0, faction, tile);
		assertTrue("Expected Buoy ID 1, instead had ID "+buoy.getId(), buoy.getId() == 1);
		tile = new Tile(map, new Position(0,0)) {
			
			@Override
			public CellType navigable(Ship ship) {
				return null;
			}
			
			@Override
			public boolean isSupply() {
				return false;
			}
		};
		//Test reset of sequential IDs on new instance of class
		factory = new EntityFactory();
		buoy = factory.createBuoy(0, faction, tile);
		assertTrue("Expected Buoy ID 0, instead had ID "+buoy.getId(), buoy.getId() == 0);
		
	}
	
	/**
	 * Tests if IDs of Krakens are properly incrementing
	 */
	@Test
	public void testIDCountingKraken(){
		//test sequential ID creation
		Kraken kraken = factory.releaseTheKraken(tile);
		assertTrue("Expected next Kraken ID 1, instead was ID "+factory.getKrakenNextId(), factory.getKrakenNextId() == 1);
		assertTrue("Expected Kraken ID 0, instead had ID "+kraken.getId(), kraken.getId() == 0);
		tile = new Tile(map, new Position(0,0)) {
			
			@Override
			public CellType navigable(Ship ship) {
				return null;
			}
			
			@Override
			public boolean isSupply() {
				return false;
			}
		};
		kraken = factory.releaseTheKraken(tile);
		assertTrue("Expected Kraken ID 1, instead had ID "+kraken.getId(), kraken.getId() == 1);
		tile = new Tile(map, new Position(0,0)) {
			
			@Override
			public CellType navigable(Ship ship) {
				return null;
			}
			
			@Override
			public boolean isSupply() {
				return false;
			}
		};
		//Test reset of sequential IDs on new instance of class
		factory = new EntityFactory();
		kraken = factory.releaseTheKraken(tile);
		assertTrue("Expected Buoy ID 0, instead had ID "+kraken.getId(), kraken.getId() == 0);
	}
	
	/**
	 * Tests the shared ID pool
	 */
	@Test
	public void testCombinedIDs() {
		Buoy b = factory.createBuoy(0, faction, tile);
		Ship s = factory.createShip(faction, tile);
		Kraken k = factory.releaseTheKraken(tile);
		Treasure t = factory.createTreasure(1, tile);
		assertTrue("Buoy had wrong ID, was "+b.getId(), b.getId() == 0);
		assertTrue("Kraken had wrong ID, was "+k.getId(), k.getId() == 1);
		assertTrue("Treasure had wrong ID, was "+t.getId(), t.getId() == 2);
		assertTrue("Ship had wrong ID, was "+s.getId(), s.getId() == 0);
		assertTrue("Factory had wrong object NextID, was "+factory.getBuoyNextId(), factory.getBuoyNextId() == 3);
	}

	/**
	 * Tests Setters / Getters
	 */
	@Test
	public void testSetterGetter(){
		factory.setShipNextId(7);
		factory.setObjectsNextId(8);
		assertTrue("ShipNextID has problems after setting", factory.getShipNextId() == 7);
		assertTrue("TreasureNextID has problems after setting", factory.getObjectsNextId() == 8);
		assertTrue("TreasureNextID has problems after setting", factory.getBuoyNextId() == 8);
		assertTrue("TreasureNextID has problems after setting", factory.getTreasureNextId() == 8);
		assertTrue("TreasureNextID has problems after setting", factory.getKrakenNextId() == 8);
	}
	
	/**
	 * Tests Constructor
	 */
	@Test
	public void testConstructor(){
		factory = new EntityFactory();
		assertTrue("Problem with BuoyNextID", factory.getBuoyNextId() == 0);
		assertTrue("Problem with ShipNextID", factory.getShipNextId() == 0);
		assertTrue("Problem with TreasureNextID", factory.getTreasureNextId() == 0);
		assertTrue("Problem with KrakenNextID", factory.getKrakenNextId() == 0);
		assertTrue("Problem with ObjectNextID", factory.getObjectsNextId() == 0);
	}
	
}
