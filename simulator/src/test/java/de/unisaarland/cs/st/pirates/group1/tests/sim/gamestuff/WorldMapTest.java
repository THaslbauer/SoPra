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
import de.unisaarland.cs.st.pirates.group1.sim.logger.ExtendedLogWriter;
import de.unisaarland.cs.st.pirates.group1.sim.util.Direction;
import de.unisaarland.cs.st.pirates.group1.sim.util.Heading;
import de.unisaarland.cs.st.pirates.group1.tests.testUtil.DumbWorldmap;
import de.unisaarland.cs.st.pirates.group1.tests.testUtil.TestLoggerWithMapChecks;
import junit.framework.TestCase;

public class WorldMapTest extends TestCase {

	private Worldmap map;
	private EntityFactory factory;
	private ExtendedLogWriter logger;
	private Position middle;
	
	@Before
	public void setUp(){
		this.factory = new EntityFactory();
		this.logger = new TestLoggerWithMapChecks();
		this.map = new Worldmap6T(4, 4, new TestLoggerWithMapChecks(), new EntityFactory());
		this.middle = new Position(1,1);
	}
	
	/**
	 * Tests basic Map initialization
	 */
	@Test
	public void testMapBuild(){
		ExtendedLogWriter testlog = new TestLoggerWithMapChecks();
		EntityFactory factory = new EntityFactory();
		map = new Worldmap(testlog, factory) {
			
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
		assertTrue("EntityFactory of Worldmap wasnt set properly, was "+map.getEntityFactory(), map.getEntityFactory() == factory);
		assertTrue("Logger of Worldmap wasn't set properly, was "+map.getExtendedLogWriter(), map.getExtendedLogWriter() == testlog);
		int mapX = 6;
		int mapY = 4;
		Worldmap6T map6t = new Worldmap6T(mapX, mapY, testlog , factory);
		assertTrue("Map width of Worldmap6T wasn't set properly, was "+map6t.getWidth()+" instead of "+mapX, map6t.getWidth() == mapX);
		assertTrue("Map height of Worldmap6T wasn't set properly, was "+map6t.getHeight()+" instead of "+mapX, map6t.getHeight() == mapY);
		assertTrue("EntityFactory of Worldmap6T wasnt set properly, was "+map6t.getEntityFactory(), map6t.getEntityFactory() == factory);
		assertTrue("Logger of Worldmap6T wasn't set properly, was "+map6t.getExtendedLogWriter(), map6t.getExtendedLogWriter() == testlog);
		for(int x = 0; x < mapX; x++){
			for(int y = 0; x < mapY; y++){
				assertTrue("Map had Tile at Position ("+x+", "+y+")", map6t.getTile(new Position(x, y)) == null);
			}
		}
	}
	
	
	/**
	 * Tests directional stuff (calcPosition)
	 */
	public void testDirectionStuff(){
		//testing direction switching
		Position p = map.calcPosition(middle, Heading.H0, Direction.D0);
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
	public void testCreation(){
		
	}
	
}
