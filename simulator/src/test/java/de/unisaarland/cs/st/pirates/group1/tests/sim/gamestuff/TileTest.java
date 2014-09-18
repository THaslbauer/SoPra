package de.unisaarland.cs.st.pirates.group1.tests.sim.gamestuff;

import org.junit.Before;
import org.junit.Test;

import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Base;
import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.EntityFactory;
import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Faction;
import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Kraken;
import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Treasure;
import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Worldmap;
import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Worldmap6T;
import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Position;
import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Ship;
import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Tile;
import de.unisaarland.cs.st.pirates.group1.sim.logger.InfoPoint;
import de.unisaarland.cs.st.pirates.group1.sim.util.CellType;
import de.unisaarland.cs.st.pirates.group1.sim.util.Direction;
import de.unisaarland.cs.st.pirates.group1.sim.util.Heading;
import de.unisaarland.cs.st.pirates.group1.sim.util.IllegalCallException;
import junit.framework.TestCase;

public class TileTest extends TestCase {
	
	private Worldmap worldmap;
	private Position middle;
	private Tile basicTile;
	private Faction faction;
	
	@Before
	public void setUp(){
		this.worldmap = new Worldmap6T(4, 4, new InfoPoint(), new EntityFactory());
		worldmap.createSeaTile(new Position(0, 0));
		worldmap.createSeaTile(new Position(0, 1));
		worldmap.createSeaTile(new Position(0, 2));
		worldmap.createSeaTile(new Position(1, 0));
		worldmap.createSeaTile(new Position(1, 1));
		worldmap.createSeaTile(new Position(1, 2));
		worldmap.createSeaTile(new Position(2, 0));
		worldmap.createSeaTile(new Position(2, 1));
		worldmap.createSeaTile(new Position(2, 2));
		
		this.middle = new Position(1,1);
		this.basicTile = new Tile(worldmap, middle) {

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
		this.faction = new Faction("test", 0);
	}
	
	/**
	 * Tests basic creation of Tiles
	 */
	@Test
	public void testTileBuild(){
		assertTrue("Tile shouldn't have a ship before one is attached", basicTile.getShip() == null);
		Ship ship = new Ship(faction, 0, basicTile);
			if(basicTile.getShip() == ship){
				basicTile.detach(ship);
				assertTrue(basicTile.getShip() == null);
				basicTile.attach(ship);
				assertTrue(basicTile.getShip() == ship);
			}
			else{
				basicTile.attach(ship);
				assertTrue(basicTile.getShip() == ship);
				basicTile.detach(ship);
				assertTrue(basicTile.getShip() == null);
			}
	}
	
	/**
	 * Tests attaching and detaching
	 */
	@Test
	public void testKrakenAttachDetach()
	{
		// attaches and detaches a kraken
		Kraken kraken = new Kraken(0, basicTile);
		
		assertTrue("Either the kraken doesn't attach itselve to the tile or the tile's getter for kraken is wrong.",
				basicTile.getKraken() == kraken);

			basicTile.detach(kraken);
		
			assertTrue("The tile's method detach(kraken) didn't detache the kraken.", basicTile.getKraken() == null);
		
			basicTile.attach(kraken);
		
			assertTrue("The tile's method attach(kraken) didn't attach the kraken", basicTile.getKraken() == kraken);

	}
	
	@Test
	public void testTreasureAttachDetach()
	{
		// attaches and detaches a treasure
		Treasure treasure = new Treasure(1, 1, basicTile);
		
		assertTrue("Either the treasure doesn't attach itselve to the tile or the tile's getter for treasure is wrong.",
				basicTile.getTreasure() == treasure);

		basicTile.detach(treasure);
		
		assertTrue("The tile's method detach(treasure) didn't detach the kraken.", basicTile.getTreasure() == null);
		
		basicTile.attach(treasure);
		
		assertTrue("The tile's method attach(treasure) didn't attach the treasure", basicTile.getTreasure() == treasure);
		
	}
	
	/**
	 * Tests getNeighbour
	 */
	@Test
	public void testGetNeighbour()
	{

			Tile neighbourTile = worldmap.getTile(new Position(0,1));
			
			assertTrue("The tile's method getNeighbour() calculates the wrong neighbour tile.",
					basicTile.getNeighbour(Heading.H4, Direction.D0) == neighbourTile);
	

	}
	
	/**
	 * Tests creating new treasure if increaseTreasure doesn't find any
	 */
	@Test
	public void testIncreaseDecreaseTreasure()
	{
		try
		{
			basicTile.increaseTreasure(2);
			
			assertTrue("The tile's method increaseTreasure() didn't place a treasure.", basicTile.getTreasure() != null);
			
			assertTrue("The tile's method increaseTreasure() has placed a bad treasure.", basicTile.getTreasure().getValue() == 2);
			
			basicTile.increaseTreasure(2);
			
			assertTrue("The tile's method increase Treasure() didn't add the treasure to the previous one.",
					basicTile.getTreasure().getValue() == 4);
			
			basicTile.decreaseTreasure(4);
			
			assertTrue("The tile's method decreaseTreasure() didn't removed the treasure.", basicTile.getTreasure() == null);
		}
		catch(Exception e)
		{
			fail();
		}
	}

	/**
	 * Tests if increaseTreasure adds to existing treasure
	 */
	@Test
	public void testIncreaseExistingTreasure(){
		//TODO implement
	}
}