package de.unisaarland.cs.st.pirates.group1.tests.sim.parser;

import static de.unisaarland.cs.st.pirates.group1.tests.testUtil.StreamHelper.asIS;
import static org.junit.Assert.fail;

import org.junit.Test;

import de.unisaarland.cs.st.pirates.group1.sim.parser.TacticsParser;
import de.unisaarland.cs.st.pirates.group1.tests.testUtil.ExpectLogger;
import de.unisaarland.cs.st.pirates.group1.tests.testUtil.SRandom;

public class IllegalTacticsTest {
	private static ExpectLogger expectLogger = new ExpectLogger();
	private static TacticsParser tp = new TacticsParser(expectLogger);
	private static SRandom srandom = new SRandom(0);
	
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
	
	@Test
	public void senselessTest() {
		String s = new String("HOHOHOHOH\nTROLOLOLOL");
		checkNFail(s,"Senseless input\n");
	}
	
}
