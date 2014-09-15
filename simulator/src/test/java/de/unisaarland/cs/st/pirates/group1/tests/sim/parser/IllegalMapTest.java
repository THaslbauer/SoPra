package de.unisaarland.cs.st.pirates.group1.tests.sim.parser;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import org.junit.Test;

import de.unisaarland.cs.st.pirates.group1.sim.driver.Simulator;
import de.unisaarland.cs.st.pirates.group1.sim.logger.InfoPoint;
import de.unisaarland.cs.st.pirates.group1.sim.parser.MapParser;

public class IllegalMapTest {
	
	private static MapParser mp = new MapParser();
	private static InfoPoint ip = new InfoPoint();
	private static Simulator sim;
	
	
	private static InputStream asIS(String s) {
		return new ByteArrayInputStream(s.getBytes());
	}
	
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

}
