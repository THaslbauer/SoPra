package de.unisaarland.cs.st.pirates.group1.tests.sim.parser;

import static de.unisaarland.cs.st.pirates.group1.tests.testUtil.StreamHelper.asIS;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Random;

import org.junit.Test;

import de.unisaarland.cs.st.pirates.group1.sim.driver.Simulator;
import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.*;
import de.unisaarland.cs.st.pirates.group1.sim.logger.LogWriter.Cell;
import de.unisaarland.cs.st.pirates.group1.sim.parser.MapParser;
import de.unisaarland.cs.st.pirates.group1.tests.testLogger.AddCell;
import de.unisaarland.cs.st.pirates.group1.tests.testLogger.ExpectLogger;


public class RealMapTests {
	private static MapParser mp = new MapParser();
	private static ExpectLogger elogger = new ExpectLogger();
	private static Simulator sim;
	private static Random r = new Random();
	
	
	@Test
	public void minimalMapEasyTest() {
		String s = "2\n2\n.#\n$.";
		sim = new Simulator(elogger, r);
		mp.parseMap(asIS(s), sim);
		Worldmap map = sim.getWorldmap();
		elogger.expect(new AddCell(Cell.WATER, null, 0, 0));
		elogger.expect(new AddCell(Cell.ISLAND, null, 1, 0));
		elogger.expect(new AddCell(Cell.ISLAND, null, 0, 1));
		elogger.expect(new AddCell(Cell.WATER, null, 1, 1));
		if( !(map.getTile(new Position(0,0)) instanceof Sea &&  
			map.getTile(new Position(1,0)) instanceof Island &&
			map.getTile(new Position(0,1)) instanceof Island &&
			map.getTile(new Position(1,1)) instanceof Sea) )
			fail("Wrong tiles attached");
		if(map.getTile(new Position(0,1)) instanceof Sea && map.getTile(new Position(1,0)) instanceof Island && map.getTile(new Position(1,1)) instanceof Sea)
			assertTrue( map.getTile(new Position(0,1)).isSupply() );
		else
			fail("Wrong tile types");
	}
}
