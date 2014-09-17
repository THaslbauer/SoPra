package de.unisaarland.cs.st.pirates.group1.tests.sim.parser;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.junit.Test;

import de.unisaarland.cs.st.pirates.group1.sim.driver.Simulator;
import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.*;
import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Position;
import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Worldmap;
import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Worldmap6T;
import de.unisaarland.cs.st.pirates.group1.sim.logger.InfoPoint;
import de.unisaarland.cs.st.pirates.group1.sim.logger.LogWriter.Cell;
import de.unisaarland.cs.st.pirates.group1.sim.parser.MapParser;
import de.unisaarland.cs.st.pirates.group1.tests.testLogger.*;
import de.unisaarland.cs.st.pirates.group1.tests.testUtil.DummyEntityFactory;

import static de.unisaarland.cs.st.pirates.group1.tests.testUtil.StreamHelper.asIS;


public class RealMapTests {
	private static MapParser mp = new MapParser();
	private static InfoPoint ip = new InfoPoint();
	private static ExpectLogger elogger = new ExpectLogger();
	private static Simulator sim;
	
	
	private static void checkNFail(String s, String exp) {
		Simulator simulator = new Simulator(ip);
		try{
			mp.parseMap(asIS(s), simulator);
			fail("Nothing thrown");
		} catch (IllegalArgumentException e) {
			System.out.println("OK: EXPECTED \""+exp+"\" GOT \""+e+"\"");
		} catch (NullPointerException n) {
			throw n;
		} catch(Exception e) {
			fail("Wrong exception thrown");
		}
	}
	
	@Test
	public void minimalMapEasyTest() {
		String s = "2\n2\n.#\n$.";
		sim = new Simulator(elogger);
		mp.parseMap(asIS(s), sim);
		elogger.expect(new AddCell(Cell.WATER, null, 0, 0));
		elogger.expect(new AddCell(Cell.ISLAND, null, 1, 0));
		elogger.expect(new AddCell(Cell.ISLAND, null, 0, 1));
		elogger.expect(new AddCell(Cell.WATER, null, 1, 1));
		Worldmap map = sim.getWorldmap();
		Object o;
		try {
			o = (Sea)map.getTile(new Position(0,1));
			o = (Island)map.getTile(new Position(1,0));
			assertTrue( ((Island)sim.getWorldmap().getTile(new Position(0,1))).isSupply() );
			o = (Sea)map.getTile(new Position(1,1));
		} catch(ClassCastException e) {
			fail("Wrong tile types");
		}
	}
}
