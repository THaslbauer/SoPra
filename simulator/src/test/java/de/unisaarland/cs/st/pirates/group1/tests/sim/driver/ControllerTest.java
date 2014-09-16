package de.unisaarland.cs.st.pirates.group1.tests.sim.driver;

import static org.junit.Assert.*;

import java.io.InputStream;
import java.util.Random;

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
		public void step()
		{
			value += 1;
		}
	}
	
	
	private static Controller controller;
	
	private static TestMapParser testMapParser;
	
	private static TestTacticsParser testTacticsParser;
	
	private static TestSimulator testSimulator;
	
	
	@BeforeClass
	public static void init()
	{
		testMapParser     = new TestMapParser();
		testTacticsParser = new TestTacticsParser();
		testSimulator     = new TestSimulator(null);
		controller        = new Controller(testSimulator, testMapParser, testTacticsParser, null, null);
	}
	
	@Test
	public void intiTest()
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
	
	
	
}
