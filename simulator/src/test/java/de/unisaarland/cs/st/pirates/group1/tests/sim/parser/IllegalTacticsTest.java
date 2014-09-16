package de.unisaarland.cs.st.pirates.group1.tests.sim.parser;

import static de.unisaarland.cs.st.pirates.group1.tests.testUtil.StreamHelper.asIS;
import static org.junit.Assert.fail;
import de.unisaarland.cs.st.pirates.group1.sim.driver.Simulator;
import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Worldmap.sRandom;
import de.unisaarland.cs.st.pirates.group1.sim.parser.TacticsParser;
import de.unisaarland.cs.st.pirates.group1.tests.testLogger.ExpectLogger;

public class IllegalTacticsTest {
	private static TacticsParser tp = new TacticsParser();
	private static sRandom srandom = new sRandom(0);
	
	private static void checkNFail(String s, String exp) {
		try{
			tp.parseTactics(asIS(s), srandom);
			fail("Nothing thrown");
		} catch (IllegalArgumentException e) {
			System.out.println("OK: EXPECTED \""+exp+"\" GOT \""+e+"\"");
		} catch (NullPointerException n) {
			throw n;
		} catch(Exception e) {
			fail("Wrong exception thrown");
		}
	}
	
	
	public void senselessTest() {
		String s = new String("HOHOHOHOH\nTROLOLOLOL");
		checkNFail(s,"Senseless input\n");
	}
	
}
