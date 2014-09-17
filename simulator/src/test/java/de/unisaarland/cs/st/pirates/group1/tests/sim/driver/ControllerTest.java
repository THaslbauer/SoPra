package de.unisaarland.cs.st.pirates.group1.tests.sim.driver;

import static org.junit.Assert.*;

import java.io.InputStream;
import java.nio.channels.UnsupportedAddressTypeException;
import java.util.Random;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import de.unisaarland.cs.st.pirates.group1.sim.driver.Controller;
import de.unisaarland.cs.st.pirates.group1.sim.driver.Simulator;
import de.unisaarland.cs.st.pirates.group1.sim.logger.InfoPoint;
import de.unisaarland.cs.st.pirates.group1.sim.parser.MapParser;
import de.unisaarland.cs.st.pirates.group1.sim.parser.TacticsParser;

public class ControllerTest
{
	
	// a test class for testing the controller class
	public static class TestMapParser extends MapParser
	{
		
		public int value = 0;
		
		@Override
		public void parseMap(InputStream stream, Simulator simulator)
		{
			value += 1;
		}
	}
	
	// a test class for testing the controller class
	public static class TestTacticsParser extends TacticsParser
	{
		public int value = 0;
		
		@Override
		public void parseTactics(InputStream stream, Random random)
		{
			value += 1;
		}
	}
	
	// a test class for testing the controller class
	public static class TestSimulator extends Simulator
	{
		public int value = 0;
		
		public TestSimulator(InfoPoint infoPoint) {
			super(infoPoint);
		}
		
		@Override
		synchronized public void step()
		{
			value += 1;
		}
		
		synchronized public int getValue(){
			return value;
		}
	}
	
	
	private static Controller controller;
	
	private static TestMapParser testMapParser;
	
	private static TestTacticsParser testTacticsParser;
	
	private static TestSimulator testSimulator;
	
	private Controller contr;
	private TestMapParser mp;
	private TestTacticsParser tp;
	private TestSimulator sim;
	
	
	@BeforeClass
	public static void init()
	{
		testMapParser     = new TestMapParser();
		testTacticsParser = new TestTacticsParser();
		testSimulator     = new TestSimulator(new InfoPoint());
		controller        = new Controller(testSimulator, testMapParser, testTacticsParser, null, null);
	}
	
	@Before
	public void setUp(){
		mp = new TestMapParser();
		tp = new TestTacticsParser();
		sim = new TestSimulator(new InfoPoint());
		contr = new Controller(sim, mp, tp, null, null);
		contr.initializeSimulator();
	}
	
	@Test
	public void initTest()
	{
		int valueMapParser     = testMapParser.value;
		int valueTacticsParser = testTacticsParser.value; 
		
		controller.initializeSimulator();
		
		int expectedValueMapParser     = valueMapParser + 1;
		int expectedValueTacticsParser = valueTacticsParser + 1;
		
		assertTrue("the MapParser's method parseMap() was not called", expectedValueMapParser == testMapParser.value);
		assertTrue("the TacticsParser's method parseTactics() was not called", expectedValueTacticsParser == testTacticsParser.value);
		
	}
	
	@Test
	public void playTest()
	{
		int valueSimulator = testSimulator.value;
		
		controller.play();
		
		int expectedValueSimulator = valueSimulator + 1;
		
		assertTrue("the simulator's method step() was not called", expectedValueSimulator == testSimulator.value);
	}
	
	@Test
	public void getsetTest()
	{
		controller = new Controller(null, null, null, null, null);
		
		controller.setSimulator(testSimulator);
		assertTrue("The controllers's setter or getter for the simulator was incorrect",
				testSimulator.equals(controller.getSimulator()));
		
		
		controller.setMapParser(testMapParser);
		assertTrue("The controllers's setter or getter for the mapParser was incorrect",
				testMapParser.equals(controller.getMapParser()));
		
		
		controller.setTacticsParser(testTacticsParser);
		assertTrue("The controllers's setter or getter for the tacticsParser was incorrect",
				testTacticsParser.equals(controller.getTacticsParser()));
		
		
		controller.setMapFile(null);
		assertTrue("The controllers's setter or getter for the mapFile was incorrect",
				controller.getMapFile() == null);
		
		
		controller.setTacticsFile(null);
		assertTrue("The controllers's setter or getter for the tacticsFile was incorrect",
				controller.getTacticsFile() == null);
	}
	
	/**
	 * Tests if pause pauses the Controller concurrently
	 */
	@Test
	public void testPause(){
		Runnable contrRun = new Runnable() {
			
			@Override
			public void run() {
				contr.play();
			}
		};
		Thread contrThread = new Thread(contrRun);
		contrThread.run();
		contr.pause();
		int firstStepCount = sim.getValue();
		try{
			Thread.sleep(200);
		}
		catch(InterruptedException e){
			throw new UnsupportedOperationException("Who the fuck interrupts a test?");
		}
		int secondStepCount = sim.getValue();
		assertTrue("Controller didn't stop", firstStepCount == secondStepCount);
	}
	
}
