package de.unisaarland.cs.st.pirates.group1.tests.sim.driver;

import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import de.unisaarland.cs.st.pirates.group1.sim.driver.Simulator;
import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.EntityFactory;
import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Faction;
import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Kraken;
import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Position;
import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Ship;
import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Tile;
import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Worldmap;
import de.unisaarland.cs.st.pirates.group1.sim.logger.ExtendedLogWriter;
import de.unisaarland.cs.st.pirates.group1.sim.logger.LogWriter.Entity;
import de.unisaarland.cs.st.pirates.group1.sim.logger.LogWriter.Key;
import de.unisaarland.cs.st.pirates.group1.sim.util.CellType;
import de.unisaarland.cs.st.pirates.group1.sim.util.ShipType;
import de.unisaarland.cs.st.pirates.group1.tests.testLogger.Create;
import de.unisaarland.cs.st.pirates.group1.tests.testLogger.Destroy;
import de.unisaarland.cs.st.pirates.group1.tests.testLogger.ExpectLogger;
import de.unisaarland.cs.st.pirates.group1.tests.testLogger.LogStep;
import de.unisaarland.cs.st.pirates.group1.tests.testUtil.DumbExtendedLogWriter;
import de.unisaarland.cs.st.pirates.group1.tests.testUtil.DumbWorldmap;
import junit.framework.TestCase;

public class SimulatorTest extends TestCase {
	private Simulator sim;
	private Worldmap map;
	private ExpectLogger logger;
	private Tile testTile;
	private Faction testFaction;
	private List<Faction>factions;

	/**
	 * Useful note: Setup creates an EntityFactory that creates TestShips and TestKrakens instead of Ships and Krakens.
	 * Just cast them to their proper class and they give Info about how many times they were stepped.
	 */
	@Before
	protected void setUp() throws Exception {
		super.setUp();
		this.logger = new ExpectLogger();
		this.map = new DumbWorldmap();
		this.testFaction = new Faction("test", 0);
		this.factions = new LinkedList<Faction>();
		factions.add(testFaction);
		this.sim = new Simulator(logger);
		this.sim.setEntityFactory(new TestFactory());
		sim.setWorldmap(map);
		sim.setFactions(factions);
		testTile = new Tile(map, new Position(0,0)) {
			
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
	}
	
	/**
	 * Tests if simulator only steps Krakens every 20 rounds
	 * @author thomas
	 *
	 */
	@Test
	public void testKrakenStep(){
		int stepCount = 0;
		Kraken kraken = sim.createKraken(testTile);
		TestKraken testKraken = (TestKraken)kraken;
		for(int i = 0; i < 10; i++){
			for(int j = 0; i < 20; i++){
				sim.step();
				if(j == 0 && testKraken.getStepCount() != (stepCount+1))
					fail("Kraken wasn't stepped");
			}
			stepCount = testKraken.getStepCount();
			assertTrue("Kraken was stepped more than once every 20 cycles between cycle "+i*20+" and "+(i+1)*20,
					stepCount == (1+i));
		}
	}
	
	/**
	 * Tests if correct constructor calls work
	 * @author thomas
	 *
	 */
	@Test
	public void testCorrectConstructor(){
		sim = new Simulator(logger);
		assertTrue("Simulator cycle should be 0 at the beginning", sim.getCycle() == 0);
		assertTrue("Default maxCycle should be 10000, was "+sim.getMaxCycle()+" instead",
				sim.getMaxCycle() == 10000);
		assertTrue("Logger wasn't set properly", sim.getLogWriter() == logger);
		int maxCycle = 30;
		sim = new Simulator(logger, maxCycle);
		assertTrue("Simulator cycle should be 0 at the beginning", sim.getCycle() == 0);
		assertTrue("Simulator maxCycle should be "+maxCycle+" instead was "+sim.getMaxCycle(),
				sim.getMaxCycle() == maxCycle);
		assertTrue("Logger wasn't set properly", sim.getLogWriter() == logger);
		assertTrue("Ship and Kraken pool had objects in them", sim.getShips().isEmpty() && sim.getKrakens().isEmpty());
		assertTrue("Factions has been set without consulting others", sim.getFactions() == null);
		assertTrue("Worldmap has been set without consulting others", sim.getWorldmap() == null);
		assertTrue("EntityFactory has been set without consulting others", sim.getEntityFactory() == null);
	}
	
	/**
	 * Tests if incorrect constructor calls fail
	 * @author thomas
	 *
	 */
	@Test
	public void testIncorrectConstructor(){
		boolean notifiedError = false;
		try{
			sim = new Simulator(logger, -5);
		}
		catch(IllegalArgumentException e){
			notifiedError = true;
		}
		assertTrue("Constructor did not fail with negative maxCycles", notifiedError);
		notifiedError = false;
		try{
			sim = new Simulator(logger, 0);
		}
		catch(IllegalArgumentException e){
			notifiedError = true;
		}
		assertTrue("Constructor did not fail with 0 maxCycles", notifiedError);
	}
	
	/**
	 * Tests if stepping after maxCycles were reached throws an Exception
	 * @author thomas
	 *
	 */
	@Test
	public void testExceptionAtMaxCycles(){
		boolean notifiedMaxCycle = false;
		sim = new Simulator(logger, 1);
		sim.step();
		try{
			sim.step();
		}
		catch(UnsupportedOperationException e){
			notifiedMaxCycle = true;
		}
		assertTrue("Simulator didn't throw Exception when overstepping maxCycle", notifiedMaxCycle);
	}
	
	/**
	 * Tests creation method of Ship
	 * @author thomas
	 *
	 */
	@Test
	public void testShipCreation(){
		Ship ship = sim.createShip(testFaction, testTile);
		Key[] keys = {Key.DIRECTION, Key.FLEET, Key.MORAL, Key.PC, Key.RESTING, Key.VALUE, Key.X_COORD, Key.Y_COORD};
		int[] values = {0, 0, 4, 0, 0, 0, 0, 0, 3};
		assertFalse("Ship returned was null", ship == null);
		assertTrue("Ship Faction set improperly", ship.getFaction() == testFaction);
		assertTrue("Ship Tile set improperly", testTile == ship.getMyTile());
		assertFalse("Simulator didn't put ship in ship pool", sim.getShips().isEmpty());
		assertTrue("Faction wasn't notified of new Ship", testFaction.getShipCount() == 1);
		logger.expect(new Create(Entity.SHIP, 0, keys, values));
	}
	
	/**
	 * Tests creation method of Kraken
	 * @author thomas
	 *
	 */
	@Test
	public void testKrakenCreation(){
		Kraken kraken = sim.createKraken(testTile);
		assertFalse("Kraken returned was null", kraken == null);
		assertTrue("Kraken Tile was set improperly", kraken.getMyTile() == testTile);
		assertFalse("Simulator didn't put kraken in kraken pool", sim.getKrakens().isEmpty());
		//TODO watch for Logger notification
	}
	
	/**
	 * Tests if removeShip works
	 * @author thomas
	 *
	 */
	@Test
	public void testRemoveShip(){
		Ship ship = sim.createShip(testFaction, testTile);
		sim.removeShip(ship);
		assertTrue("Ship wasn't removed", sim.getShips().isEmpty());
		assertTrue("Ship wasn't removed from faction", testFaction.getShipCount() == 0);
	}
	
	/**
	 * Tests if ships with condition 0 get removed when stepping
	 * @author thomas
	 *
	 */
	@Test
	public void testAutoremove(){
		Ship ship = sim.createShip(testFaction, testTile);
		ship.setCondition(0);
		sim.step();
		assertTrue("Ship wasn't autoremoved", sim.getShips().isEmpty());
		assertTrue("Ship wasn't removed from faction", testFaction.getShipCount() == 0);
	}
	
	/**
	 * Tests if ships are stepped and if the cycle end gets recorded properly
	 */
	@Test
	public void testShipStep(){
		Ship ship = sim.createShip(testFaction, testTile);
		TestShip testShip = (TestShip)ship;
		for(int stepCount = 1; stepCount <= 25; stepCount++){
			sim.step();
			assertTrue("Simulator didn't step Ship", testShip.getStepCount() == stepCount);
			assertTrue("Simulator doesn't count cycles properly, it said cycle was "+sim.getCycle()+" instead of "+stepCount,
					sim.getCycle() == stepCount);
			logger.expect(new LogStep());
		}
	}
	
	/**
	 * Tests Setters and Getters (pretty much useless except for coverage
	 * @author thomas
	 *
	 */
	@Test
	public void testSetterGetter(){
		//setCycle
		int cycle = 5;
		sim.setCycle(cycle);
		assertTrue("Setting / Getting cycle didn't work", sim.getCycle()== cycle);
		//setEntityFactory
		EntityFactory fact = new EntityFactory();
		sim.setEntityFactory(fact);
		assertTrue("Setting / Getting of EntityFactory didn't work", sim.getEntityFactory() == fact);
		//setFactions
		List<Faction>factions = new LinkedList<>();
		sim.setFactions(factions);
		assertTrue("Setting / Getting of Factions didn't work", sim.getFactions() == factions);
		//setKrakens
		List<Kraken>krakens = new LinkedList<>();
		sim.setKrakens(krakens);
		assertTrue("Setting / Getting of Krakens didn't work", sim.getKrakens() == krakens);
		//setShips
		List<Ship>ships = new LinkedList<>();
		sim.setShips(ships);
		assertTrue("Setting / Getting of Ships didn't work", sim.getShips() == ships);
		//setLogWriter
		ExtendedLogWriter logWriter = new DumbExtendedLogWriter();
		sim.setLogWriter(logWriter);
		assertTrue("Setting / Getting of logWriter didn't work", sim.getLogWriter() == logWriter);
		//setWorldmap
		Worldmap worldmap = new DumbWorldmap();
		sim.setWorldmap(worldmap);
		assertTrue("Setting / Getting of Worldmap didn't work", sim.getWorldmap() == worldmap);
	}

	
	/**
	 * TestShip is a Ship used for logging step()-calls.
	 * Instead of executing some logic, step() increments a counter so you can see how often it was called.
	 * @author thomas
	 *
	 */
	private class TestShip extends Ship{
		private int stepCount;
		
		/**
		 * Constructor. See {@link Ship} for further info.
		 * @param faction
		 * @param id
		 * @param tile
		 */
		public TestShip(Faction faction, int id, Tile tile) {
			super(faction, id, tile);
			this.stepCount = 0;
		}

		/**
		 * increments stepCount
		 */
		@Override
		public void step() {
			stepCount++;
		}
		
		/**
		 * 
		 * @return How often step was called
		 */
		public int getStepCount(){
			return this.stepCount;
		}

	}
	
	/**
	 * TestKraken is a Kraken that is used for logging step() calls.
	 * Instead of executing some logic, step() increments a counter so you can see how often it is called.
	 * @author thomas
	 *
	 */
	private class TestKraken extends Kraken {
		private int stepCount;

		/**
		 * Constructor. See {@link Kraken} for more info.
		 * @param id
		 * @param tile
		 */
		public TestKraken(int id, Tile tile) {
			super(id, tile);
			stepCount = 0;
		}

		/**
		 * Increments stepCount()
		 */
		@Override
		public void step() {
			stepCount++;
		}
		
		/**
		 * 
		 * @return How often step() was called.
		 */
		public int getStepCount(){
			return stepCount;
		}
		
	}
	
	/**
	 * An {@link EntityFactory} that creates TestKrakens and TestShips instead of normal Krakens and Ships.
	 * @author thomas
	 *
	 */
	private class TestFactory extends EntityFactory{

		@Override
		public Kraken releaseTheKraken(Tile tile) {
			int nextID = super.getKrakenNextId();
			Kraken k = new TestKraken(nextID, tile);
			super.setKrakenNextId(nextID + 1);
			return k;
		}

		@Override
		public Ship createShip(Faction faction, Tile tile) {
			int nextID = super.getShipNextId();
			Ship s = new TestShip(faction, nextID, tile);
			super.setKrakenNextId(nextID + 1);
			return s;
		}

	}
}
