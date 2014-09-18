package de.unisaarland.cs.st.pirates.group1.tests.sim.gamestuff;

import org.junit.Before;
import org.junit.Test;

import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Base;
import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Buoy;
import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.EntityFactory;
import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Faction;
import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Island;
import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Position;
import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Sea;
import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Tile;
import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Treasure;
import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Worldmap;
import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Worldmap6T;
import de.unisaarland.cs.st.pirates.group1.sim.logger.LogWriter.Cell;
import de.unisaarland.cs.st.pirates.group1.sim.util.Direction;
import de.unisaarland.cs.st.pirates.group1.sim.util.Heading;
import de.unisaarland.cs.st.pirates.group1.tests.testLogger.AddCell;
import de.unisaarland.cs.st.pirates.group1.tests.testLogger.ExpectLogger;
import junit.framework.TestCase;

public class WorldMapTest extends TestCase {
	
	private Worldmap map;
	private EntityFactory factory;
	private ExpectLogger logger;
	private Position middle;
	
	@Before
	public void setUp(){
		this.factory = new EntityFactory();
		this.logger = new ExpectLogger();
		this.map = new Worldmap6T(4, 4, logger, factory);
		this.middle = new Position(1,1);
	}
	
	/**
	 * Tests basic Map initialization
	 */
	@Test
	public void testMapBuild(){
		map = new Worldmap(logger, factory) {
			
			@Override
			public Tile getTile(Position position) {
				return null;
			}
			
			@Override
			public Treasure createTreasure(int value, Tile tile) {
				return null;
			}
			
			@Override
			public Sea createSeaTile(Position position) {
				return null;
			}
			
			@Override
			public Island createIslandTile(Position position, boolean supply) {
				return null;
			}
			
			@Override
			public Buoy createBuoy(int type, Faction faction, Tile tile) {
				return null;
			}
			
			@Override
			public Base createBaseTile(Position position, Faction faction) {
				return null;
			}
			
			@Override
			public Position calcPosition(Position position, Heading heading,
					Direction direction) {
				return null;
			}
		};
		//test general abstract Worldmap properties
		assertTrue("EntityFactory of Worldmap wasnt set properly, was "+map.getEntityFactory(), map.getEntityFactory() == factory);
		assertTrue("Logger of Worldmap wasn't set properly, was "+map.getExtendedLogWriter(), map.getExtendedLogWriter() == logger);
		int mapX = 6;
		int mapY = 4;
		Worldmap6T map6t = new Worldmap6T(mapX, mapY, logger , factory);
		//test if Worldmap6T generates properly
		assertTrue("Map width of Worldmap6T wasn't set properly, was "+map6t.getWidth()+" instead of "+mapX, map6t.getWidth() == mapX);
		assertTrue("Map height of Worldmap6T wasn't set properly, was "+map6t.getHeight()+" instead of "+mapX, map6t.getHeight() == mapY);
		assertTrue("EntityFactory of Worldmap6T wasnt set properly, was "+map6t.getEntityFactory(), map6t.getEntityFactory() == factory);
		assertTrue("Logger of Worldmap6T wasn't set properly, was "+map6t.getExtendedLogWriter(), map6t.getExtendedLogWriter() == logger);
		for(int x = 0; x < mapX; x++){
			for(int y = 0; y < mapY; y++){
				assertTrue("Map had Tile at Position ("+x+", "+y+")", map6t.getTile(new Position(x, y)) == null);
			}
		}
	}
	
	/**
	 * Tests if improper constructors fail
	 */
	@Test
	public void testImproperBuild(){
		//test if Worldmap6T throws proper Exceptions
		boolean notifiedError = false;
		Worldmap6T map6t = null;
		try{
			map6t = new Worldmap6T(4, 3, logger, factory);
		}
		catch(IllegalArgumentException e){
			notifiedError = true;
		}
		assertTrue("Worldmap6T didn't fail to create for size(4,3)", notifiedError);
		notifiedError = false;
		try{
		map6t = new Worldmap6T(0, 2, logger, factory);
		}
		catch(IllegalArgumentException e){
			notifiedError = true;
		}
		assertTrue("Worldmap6T didn't fail to create for size (0,2)", notifiedError);
		notifiedError = false;
		try{
			map6t = new Worldmap6T(2, 0, logger, factory);
		}
		catch(IllegalArgumentException e){
			notifiedError = true;
		}
		assertTrue("Worldmap6T didn't fail to create for size (0,2)", notifiedError);
		//Only to remove the warning and make sure nothing gets deleted from this method
		map6t.getHeight();
	}
	
	
	/**
	 * Tests directional stuff (calcPosition)
	 */
	@Test
	public void testDirectionStuff(){
		//testing direction switching
		Position p = map.calcPosition(middle, Heading.H0, Direction.D0);
		assertFalse("calcPosition returned no Position!", p == null);
		assertTrue("calcPosition calculated wrong Position for H0, D0. Was ("+p.x+", "+p.y+") instead of (2,1)",p.x == 2 && p.y == 1);
		p = map.calcPosition(middle, Heading.H0, Direction.D1);
		assertTrue("calcPosition calculated wrong Position for H0, D1. Was ("+p.x+", "+p.y+") instead of (2,2)",p.x == 2 && p.y == 2);
		p = map.calcPosition(middle, Heading.H0, Direction.D2);
		assertTrue("calcPosition calculated wrong Position for H0, D2. Was ("+p.x+", "+p.y+") instead of (1,2)",p.x == 1 && p.y == 2);
		p = map.calcPosition(middle, Heading.H0, Direction.D3);
		assertTrue("calcPosition calculated wrong Position for H0, D3. Was ("+p.x+", "+p.y+") instead of (0,1)",p.x == 0 && p.y == 1);
		p = map.calcPosition(middle, Heading.H0, Direction.D4);
		assertTrue("calcPosition calculated wrong Position for H0, D4. Was ("+p.x+", "+p.y+") instead of (1,0)",p.x == 1 && p.y == 0);
		p = map.calcPosition(middle, Heading.H0, Direction.D5);
		assertTrue("calcPosition calculated wrong Position for H0, D5. Was ("+p.x+", "+p.y+") instead of (2,0)",p.x == 2 && p.y == 0);
		//testing if direction D6 is unaffected by heading
		p = map.calcPosition(middle, Heading.H0, Direction.D6);
		assertTrue("calcPosition calculated wrong Position for H0, D5. Was ("+p.x+", "+p.y+") instead of (1,1)",p.x == 1 && p.y == 1);
		p = map.calcPosition(middle, Heading.H1, Direction.D6);
		assertTrue("calcPosition calculated wrong Position for H1, D5. Was ("+p.x+", "+p.y+") instead of (1,1)",p.x == 1 && p.y == 1);
		p = map.calcPosition(middle, Heading.H2, Direction.D6);
		assertTrue("calcPosition calculated wrong Position for H2, D5. Was ("+p.x+", "+p.y+") instead of (1,1)",p.x == 1 && p.y == 1);
		p = map.calcPosition(middle, Heading.H3, Direction.D6);
		assertTrue("calcPosition calculated wrong Position for H3, D5. Was ("+p.x+", "+p.y+") instead of (1,1)",p.x == 1 && p.y == 1);
		p = map.calcPosition(middle, Heading.H4, Direction.D6);
		assertTrue("calcPosition calculated wrong Position for H4, D5. Was ("+p.x+", "+p.y+") instead of (1,1)",p.x == 1 && p.y == 1);
		p = map.calcPosition(middle, Heading.H5, Direction.D6);
		assertTrue("calcPosition calculated wrong Position for H5, D5. Was ("+p.x+", "+p.y+") instead of (1,1)",p.x == 1 && p.y == 1);
		//testing heading switching
		p = map.calcPosition(middle, Heading.H0, Direction.D5);
		assertTrue("calcPosition calculated wrong Position for H0, D5. Was ("+p.x+", "+p.y+") instead of (2,0)",p.x == 2 && p.y == 0);
		p = map.calcPosition(middle, Heading.H1, Direction.D5);
		assertTrue("calcPosition calculated wrong Position for H1, D5. Was ("+p.x+", "+p.y+") instead of (2,1)",p.x == 2 && p.y == 1);
		p = map.calcPosition(middle, Heading.H2, Direction.D5);
		assertTrue("calcPosition calculated wrong Position for H2, D5. Was ("+p.x+", "+p.y+") instead of (2,2)",p.x == 2 && p.y == 2);
		p = map.calcPosition(middle, Heading.H3, Direction.D5);
		assertTrue("calcPosition calculated wrong Position for H3, D5. Was ("+p.x+", "+p.y+") instead of (1,2)",p.x == 1 && p.y == 2);
		p = map.calcPosition(middle, Heading.H4, Direction.D5);
		assertTrue("calcPosition calculated wrong Position for H4, D5. Was ("+p.x+", "+p.y+") instead of (0,1)",p.x == 0 && p.y == 1);
		p = map.calcPosition(middle, Heading.H5, Direction.D5);
		assertTrue("calcPosition calculated wrong Position for H5, D5. Was ("+p.x+", "+p.y+") instead of (1,0)",p.x == 1 && p.y == 0);
	}
	
	/**
	 * Tests creation abilities and notification
	 */
	@Test
	public void testBaseCreation(){
		Faction testF = new Faction("test", 0);
		Tile test = map.createBaseTile(middle, testF);
		assertTrue("Base wasn't built", test == null);
		assertTrue("Tile is not at specified position", test == map.getTile(middle));
		assertTrue("Created Tile is not Base", test instanceof Base);
		Base base = (Base)test;
		assertTrue("Base faction wasn't set properly", base.getFaction() == testF);
		assertTrue("Base Position wasn't set properly", base.getPosition().x == middle.x && base.getPosition().y == middle.y);
		logger.expect(new AddCell(Cell.WATER, 0, middle.x, middle.y));
	}
	
	/**
	 * Tests Sea creation
	 */
	@Test
	public void testSeaCreation(){
		Tile test = map.createSeaTile(middle);
		assertTrue("Tile is not at specified position", test == map.getTile(middle));
		assertTrue("Created Tile is not Sea", test instanceof Sea);
		Sea sea = (Sea)test;
		assertTrue("Base Position wasn't set properly", sea.getPosition().x == middle.x && sea.getPosition().y == middle.y);
		logger.expect(new AddCell(Cell.WATER, null, middle.x, middle.y));
	}
	
	/**
	 * Tests normal Island creation
	 */
	@Test
	public void testIslandCreation(){
		Tile test = map.createIslandTile(middle, false);
		assertTrue("Tile is not at specified position", test == map.getTile(middle));
		assertTrue("Created Tile is not Island", test instanceof Island);
		Island island = (Island)test;
		assertTrue("Island Position wasn't set properly", island.getPosition().x == middle.x && island.getPosition().y == middle.y);
		assertTrue("isSupply boolean wasn't set properly for normal Island", island.isSupply() == false);
		logger.expect(new AddCell(Cell.ISLAND, null, middle.x, middle.y));
	}
	
	/**
	 * Tests supply Island creation
	 */
	@Test
	public void testSupplyIslandCreation(){
		Tile test = map.createIslandTile(middle, true);
		assertTrue("Tile is not at specified position", test == map.getTile(middle));
		assertTrue("Created Tile is not SupplyIsland (aka Island)", test instanceof Island);
		Island island = (Island)test;
		assertTrue("SupplyIsland Position wasn't set properly", island.getPosition().x == middle.x && island.getPosition().y == middle.y);
		assertTrue("isSupply boolean wasn't set properly for SupplyIsland", island.isSupply() == true);
		logger.expect(new AddCell(Cell.SUPPLY, null, middle.x, middle.y));
	}
	
	/**
	 * Tests creation of Buoy
	 */
	@Test
	public void testBuoyCreation(){
		int type = 3;
		Tile testTile = map.createSeaTile(middle);
		Faction testFac = new Faction("test", 0);
		Buoy buoy = map.createBuoy(type, testFac, testTile);
		assertFalse("No Buoy returned", buoy == null);
		assertTrue("Tile not set properly", buoy.getMyTile() == testTile);
		assertTrue("Buoy type not set properly, was "+buoy.getType(), buoy.getType() == type);
		assertTrue("Faction not set properly", buoy.getFaction() == testFac);
	}
	
	/**
	 * Tests creation of Treasure
	 */
	@Test
	public void testTreasureCreation(){
		int value = 3;
		Tile testTile = map.createSeaTile(middle);
		Treasure treasure = map.createTreasure(value, testTile);
		assertFalse("No Treasure returned", treasure == null);
		assertTrue("Tile not set properly", treasure.getMyTile() == testTile);
		assertTrue("Value not set properly", treasure.getValue() == value);
	}

	/**
	 * Tests if going over map bounds with calculatePosition comes back from the other side
	 */
	@Test
	public void testWraparound(){
		Position upperLeft = new Position(0, 0);
		Position lowerRight = new Position(3, 3);
		Position test = map.calcPosition(upperLeft, Heading.H2, Direction.D0);
		assertTrue("calcPosition didn't return Position (3,1) on a 4x4-Worldmap if sensing with H2 and D0 on Position (0,0)", test.x == 3 && test.y == 1);
		test = map.calcPosition(upperLeft, Heading.H5, Direction.D0);
		assertTrue("calcPosition didn't return Position (0,3) on a 4x4-Worldmap if sensing with H5 and D0 on Position (0,0)", test.x == 0 && test.y == 3);
		test = map.calcPosition(upperLeft, Heading.H4, Direction.D0);
		assertTrue("calcPosition didn't return Position (3,3) on a 4x4-Worldmap if sensing with H4 and D0 on Position (0,0)", test.x == 3 && test.y == 3);
		test = map.calcPosition(lowerRight, Heading.H5, Direction.D0);
		assertTrue("calcPosition didn't return Position (0,2) on a 4x4-Worldmap if sensing with H5 and D0 on Position (3,3)", test.x == 0 && test.y == 2);
		test = map.calcPosition(lowerRight, Heading.H2, Direction.D0);
		assertTrue("calcPosition didn't return Position (3,0) on a 4x4-Worldmap if sensing with H2 and D0 on Position (3,3)", test.x == 3 && test.y == 0);
		test = map.calcPosition(lowerRight, Heading.H1, Direction.D0);
		assertTrue("calcPosition didn't return Position (0,0) on a 4x4-Worldmap if sensing with H1 and D0 on Position (3,3)", test.x == 0 && test.y == 0);
	}
}
