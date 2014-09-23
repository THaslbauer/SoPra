package de.unisaarland.cs.st.pirates.group1.tests.sim.parser;

import static de.unisaarland.cs.st.pirates.group1.tests.testUtil.StreamHelper.asIS;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Random;

import org.junit.Test;

import de.unisaarland.cs.st.pirates.group1.sim.driver.Simulator;
import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.*;
import de.unisaarland.cs.st.pirates.logger.LogWriter.Cell;
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
		try{
			mp.parseMap(asIS(s), sim);
		} catch(Exception e) {
			fail("Umm.. i don't like to fail here :/");
		}
		Worldmap map = sim.getWorldmap();
		elogger.expect(new AddCell(Cell.WATER, null, 0, 0));
		elogger.expect(new AddCell(Cell.ISLAND, null, 1, 0));
		elogger.expect(new AddCell(Cell.SUPPLY, null, 0, 1));
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
	
	/*  map1:
	 *  5 a $
	 *  # b &
	 *  a . b
	 *  . & 9
	 */
	
	@Test
	public void testEverything () {
		String s = new String("3\n4\n"+"5a$\n"+"#b&\n"+"a.b\n"+".&9"); //epic constructor
		sim = new Simulator(elogger, new Random());
		try {
			mp.parseMap(asIS(s), sim);
		} catch (Exception e) {
			fail("Got exception: "+e);
		}
		Worldmap map = sim.getWorldmap();
		Faction faca = new Faction("a",0);
		Faction facb = new Faction("b",1);
		
		
		Tile tile = map.getTile(new Position(0,0));
		try {
		assertTrue("Incorrect tile",tile instanceof Island && ((Island)tile).isSupply() == false);
				assertTrue("Incorrect treasure",tile.getTreasure().getValue() == 5);
				assertTrue("Incorrect treasure id", tile.getTreasure().getId() == 0);
		} catch (Exception e) {
			failIt("0,0",e);
		}
		
		tile = map.getTile(new Position(1,0));
		try {
		assertTrue("Incorrect tile",tile instanceof Base);
			assertTrue("Incorrect faction",((Base)tile).getFaction().getName() == "a" && ((Base)tile).getFaction().getFactionID() == 0);
			assertTrue("Incorrect faction for ship",tile.getShip().getFaction().equals(faca));
			assertTrue("Incorrect id for ship",tile.getShip().getId() == 0);
		} catch(Exception e) {
			failIt("1,0",e);
		}
		
		tile = map.getTile(new Position(2,0));
		try {
		assertTrue("Incorrect tile",tile instanceof Island && ((Island)tile).isSupply() == true);
			assertTrue("There should be nothing here", tile.getKraken() == null && tile.getShip() == null && tile.getTreasure() == null);
		} catch(Exception e) {
			failIt("2,0",e);
		}
			
		
		tile = map.getTile(new Position(0,1));
		try {
		assertTrue("Incorrect tile",tile instanceof Island && ((Island)tile).isSupply() == false);
			assertTrue("There should be nothing here", tile.getKraken() == null && tile.getShip() == null && tile.getTreasure() == null);
		} catch(Exception e) {
			failIt("0,1",e);
		}
		
		tile = map.getTile(new Position(1,1));
		try {
		assertTrue("Incorrect tile",tile instanceof Base);
			assertTrue("Incorrect faction",((Base)tile).getFaction().equals(facb));
			assertTrue("Incorrect faction for ship",tile.getShip().getFaction().equals(facb));
			assertTrue("Incorrect id for ship",tile.getShip().getId() == 1);
		} catch(Exception e) {
			failIt("1,1",e);
		}
		
		tile = map.getTile(new Position(2,1));
		try{
		assertTrue("Incorrect tile",tile instanceof Sea);
			assertTrue("Wrong kraken here",((Sea)tile).getKraken().getId() == 0);
		} catch(Exception e) {
			failIt("2,1",e);
		}
		
		
		tile = map.getTile(new Position(0,2));
		try {
		assertTrue("Incorrect tile",tile instanceof Base);
			assertTrue("Incorrect faction",((Base)tile).getFaction().equals(faca));
			assertTrue("Incorrect faction for ship",tile.getShip().getFaction().equals(faca));
			assertTrue("Incorrect id for ship",tile.getShip().getId() == 2);
		} catch(Exception e) {
			failIt("0,2",e);
		}
		
		tile = map.getTile(new Position(1,2));
		try {
		assertTrue("Incorrect tile",tile instanceof Sea);
			assertTrue("There should be nothing here", tile.getKraken() == null && tile.getShip() == null && tile.getTreasure() == null);
		} catch(Exception e) {
			failIt("1,2",e);
		}
		
		tile = map.getTile(new Position(2,2));
		try {
		assertTrue("Incorrect tile",tile instanceof Base);
			assertTrue("Incorrect faction",((Base)tile).getFaction().equals(facb));
			assertTrue("Incorrect faction for ship",tile.getShip().getFaction().equals(facb));
			assertTrue("Incorrect id for ship",tile.getShip().getId() == 3);
		} catch(Exception e) {
			failIt("2,2",e);
		}
		
		
		tile = map.getTile(new Position(0,3));
		try {
		assertTrue("Incorrect tile",tile instanceof Sea);
			assertTrue("There should be nothing here", tile.getKraken() == null && tile.getShip() == null && tile.getTreasure() == null);
		} catch(Exception e) {
			failIt("0,3",e);
		}
		
		tile = map.getTile(new Position(1,3));
		try{
		assertTrue("Incorrect tile",tile instanceof Sea);
			assertTrue("Wrong kraken here",((Sea)tile).getKraken().getId() == 1);
		} catch(Exception e) {
			failIt("1,3",e);
		}
		
		tile = map.getTile(new Position(2,3));
		try {
		assertTrue("Incorrect tile",tile instanceof Island && ((Island)tile).isSupply() == false);
			assertTrue("Incorrect treasure",tile.getTreasure().getValue() == 9);
			assertTrue("Incorrect treasure id", tile.getTreasure().getId() == 1);
		} catch (Exception e) {
			failIt("2,3",e);
		}
	}

	
	/*  map 2:
	 *  c d
	 *  a b
	 */
	
	@Test
	public void testFactionIdOrder() {
		String s = "2\n2\ncd\nab";
		sim = new Simulator(elogger, new Random());
		try {
			mp.parseMap(asIS(s), sim);
		} catch(Exception e) {
			fail("Something went wrong here... :/");
		}
		Worldmap map = sim.getWorldmap();
		
		Tile tile = map.getTile(new Position(0,0));
		Faction f1 = ((Base)tile).getFaction();
		tile = map.getTile(new Position(1,0));
		Faction f2 = ((Base)tile).getFaction();
		tile = map.getTile(new Position(0,1));
		Faction f3 = ((Base)tile).getFaction();
		tile = map.getTile(new Position(1,1));
		Faction f4 = ((Base)tile).getFaction();
		
		assertTrue("Failed at faction 1", f1.getFactionID() == 0 && f1.getName() == "c");
		assertTrue("Failed at faction 2", f2.getFactionID() == 1 && f2.getName() == "d");
		assertTrue("Failed at faction 3", f3.getFactionID() == 2 && f3.getName() == "a");
		assertTrue("Failed at faction 4", f4.getFactionID() == 3 && f4.getName() == "b");
	}
	
	@Test
	public void whiteSpaceTest() {
		/*
		 * # .
		 * . 8
		 * a b
		 * 4 $
		 */
		String s = "2 \t\n4\t\t \t\n#. .    8\t \t  a\n \t  b\n4\n\n$\n\n\t\n\t\t\n";
		sim = new Simulator(elogger, new Random());
		try {
			mp.parseMap(asIS(s), sim);
		} catch(Exception e) {
			fail("The parser should not fail, this map is correct");
		}
		Worldmap map = sim.getWorldmap();
		try {
			assertTrue("There should be an island over here",map.getTile(new Position(0,0)) instanceof Island);
			assertTrue("The treasure here should be 8",map.getTile(new Position(1,1)).getTreasure().getValue() == 8);
			assertTrue("The ship here should be the second one",map.getTile(new Position(1,2)).getShip().getId() == 2);
			assertTrue("This should be a supply island",((Island)map.getTile(new Position(1,3))).isSupply());
		} catch(Exception e) {
			fail("O.o it failed: "+e.getMessage()+"... :/");
		}
	}
	
	private void failIt(String s, Exception e) {
		fail("Something went wrong with tile ("+s+"): "+e.getMessage());
	}
	
	
	
}
