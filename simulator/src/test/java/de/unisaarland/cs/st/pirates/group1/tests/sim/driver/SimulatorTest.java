package de.unisaarland.cs.st.pirates.group1.tests.sim.driver;

import org.junit.Before;
import org.junit.Test;

import de.unisaarland.cs.st.pirates.group1.sim.driver.Simulator;
import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.EntityFactory;
import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Kraken;
import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Position;
import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Ship;
import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Tile;
import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Worldmap;
import de.unisaarland.cs.st.pirates.group1.sim.util.CellType;
import de.unisaarland.cs.st.pirates.group1.tests.testLogger.ExpectLogger;
import de.unisaarland.cs.st.pirates.group1.tests.testUtil.DumbWorldmap;
import junit.framework.TestCase;

public class SimulatorTest extends TestCase {
	private Simulator sim;
	private Worldmap map;
	private ExpectLogger logger;
	private Tile testTile;

	@Before
	protected void setUp() throws Exception {
		super.setUp();
		this.logger = new ExpectLogger();
		this.map = new DumbWorldmap();
		this.sim = new Simulator(logger);
		sim.setWorldmap(map);
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

	private class TestKraken extends Kraken {
		private int stepCount;

		public TestKraken(int id, Tile tile) {
			super(id, tile);
			stepCount = 0;
		}

		@Override
		public void step() {
			stepCount++;
			super.step();
		}
		
		public int getStepCount(){
			return stepCount;
		}
		
	}
	
	private class TestFactory extends EntityFactory{

		@Override
		public Kraken releaseTheKraken(Tile tile) {
			int nextID = super.getKrakenNextId();
			Kraken k = new TestKraken(nextID, tile);
			super.setKrakenNextId(nextID+1);
			return k;
		}

	}
}
