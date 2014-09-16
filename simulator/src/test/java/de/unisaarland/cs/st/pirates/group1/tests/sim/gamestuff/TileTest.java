package de.unisaarland.cs.st.pirates.group1.tests.sim.gamestuff;

import org.junit.Before;
import org.junit.Test;

import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Base;
import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.EntityFactory;
import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Faction;
import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Worldmap;
import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Worldmap6T;
import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Position;
import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Ship;
import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Tile;
import de.unisaarland.cs.st.pirates.group1.sim.logger.InfoPoint;
import de.unisaarland.cs.st.pirates.group1.sim.util.CellType;
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
	public void testAttachDetach(){
	}
	
	/**
	 * Tests getNeighbour
	 */
	@Test
	public void testNeighbour(){
		//TODO implement
	}
	
	/**
	 * Tests creating new treasure if increaseTreasure doesn't find any
	 */
	@Test
	public void testNewTreasure(){
		//TODO implement
	}

	/**
	 * Tests if increaseTreasure adds to existing treasure
	 */
	@Test
	public void testIncreaseExistingTreasure(){
		//TODO implement
	}
	
	/**
	 * Tile specific checks: navigability
	 */
	public void testNavigability(){
		//TODO implement
	}
	
	/**
	 * Tile specific checks: supply availability
	 */
	public void testSupply(){
		//TODO implement
	}
	
	/**
	 * Test setting and removing buoys
	 */
	public void testSetRemoveBuoy(){
		//TODO implement
	}
	
}
