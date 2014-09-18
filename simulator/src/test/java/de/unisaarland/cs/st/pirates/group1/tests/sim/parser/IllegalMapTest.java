package de.unisaarland.cs.st.pirates.group1.tests.sim.parser;

import static de.unisaarland.cs.st.pirates.group1.tests.testUtil.StreamHelper.asIS;
import static org.junit.Assert.fail;

import org.junit.Test;

import de.unisaarland.cs.st.pirates.group1.sim.driver.Simulator;
import de.unisaarland.cs.st.pirates.group1.sim.parser.MapParser;
import de.unisaarland.cs.st.pirates.group1.tests.testLogger.ExpectLogger;

public class IllegalMapTest {
	
	private static MapParser mp = new MapParser();
	private static ExpectLogger elogger = new ExpectLogger();
	
	private static void checkNFail(String s, String exp) {
		Simulator simulator = new Simulator(elogger, null);
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
	public void supidStuffTest() {
		String s = "Rolf XD \nasdf COOl\t420yoloswagpattern";
		checkNFail(s,"Complete nonsense");
	}
	
	@Test
	public void emtpyStreamTest() {
		String s = "";
		checkNFail(s,"Empty stream");
	}
	
	@Test
	public void instantMapTest() {
		String s = "#.\n.2";
		checkNFail(s,"no size");
	}
	
	@Test
	public void onlySizeTest() {
		String s = "10\n12\n";
		checkNFail(s, "no map data");		
	}
	
	@Test
	public void illegalWidhtTest1() {
		String s = "-2\n4\n##\n##\n##\n##";
		checkNFail(s,"illegal width(neg)");
	}
	
	@Test
	public void illegalWidthTest2() {
		String s = "0\n2\n##";
		checkNFail(s,"illegal width(0)");
	}
	
	@Test
	public void illegalHeightTest() {
		String s = "2\n0\n##";
		checkNFail(s,"illegal height(0)");
	}
	
	@Test
	public void tooSmallSizeTest1() {
		String s = "1\n1\n.";
		checkNFail(s,"Map too small");
	}
	
	@Test
	public void tooSmallSizeTest2() {
		String s = "1\n2\n.\n#";
		checkNFail(s,"Map too small");
	}
	
	@Test
	public void tooBigSizeTest1() {
		String s = "201\n2\n";
		for(int i = 0; i < 2; i++) {
			for(int j = 0; j < 201; j++) {
				s += ".";
			}
			s += "\n";
		}
		checkNFail(s,"Map too big(width)");
	}
	
	@Test
	public void tooBigSizeTest2() {
		String s = "2\n201\n";
		for(int i = 0; i < 201; i++) {
			for(int j = 0; j < 2; j++) {
				s += ".";
			}
			s += "\n";
		}
		checkNFail(s,"Map too big(height)");
	}
	
	@Test
	public void sizeNotMatchMapTest1() {
		String s = "3\n4\n..5\n#&a\n...";
		checkNFail(s,"Map size does not match map structure");
	}
	
	@Test
	public void sizeNotMatchMapTest2() {
		String s = "4\n3\n1234\n..#&\na..";
		checkNFail(s,"Map size does not match map structure");		
	}
	
}
