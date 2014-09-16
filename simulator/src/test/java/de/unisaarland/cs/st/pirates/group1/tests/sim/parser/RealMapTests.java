package de.unisaarland.cs.st.pirates.group1.tests.sim.parser;

import static org.junit.Assert.fail;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import de.unisaarland.cs.st.pirates.group1.sim.driver.Simulator;
import de.unisaarland.cs.st.pirates.group1.sim.logger.InfoPoint;
import de.unisaarland.cs.st.pirates.group1.sim.parser.MapParser;

import static de.unisaarland.cs.st.pirates.group1.tests.testUtil.StreamHelper.asIS;


public class RealMapTests {
	private static MapParser mp = new MapParser();
	private static InfoPoint ip = new InfoPoint();
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
}
