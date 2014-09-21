package de.unisaarland.cs.st.pirates.group1.tests.sim.driver;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import de.unisaarland.cs.st.pirates.group1.sim.driver.Controller;
import de.unisaarland.cs.st.pirates.group1.sim.driver.Simulator;
import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Faction;
import de.unisaarland.cs.st.pirates.group1.sim.logger.ExtendedLogWriter;
import de.unisaarland.cs.st.pirates.group1.sim.logic.instruction.Instruction;
import de.unisaarland.cs.st.pirates.group1.sim.parser.MapParser;
import de.unisaarland.cs.st.pirates.group1.sim.parser.TacticsParser;
import de.unisaarland.cs.st.pirates.group1.tests.testLogger.ExpectLogger;

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
		public TestTacticsParser(ExtendedLogWriter logger) {
			super(logger);
		}

		public int value = 0;
		
		@Override
		public Instruction[] parseTactics(InputStream stream, Random random)
		{
			value += 1;
			return null;
		}
	}
	
	// a test class for testing the controller class
	public static class TestSimulator extends Simulator
	{
		public int value = 0;
		
		public TestSimulator(ExpectLogger infoPoint) {
			super(infoPoint, new Random());
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
	private MapParser mp;
	private TacticsParser tp;
	private Simulator sim;
	
	private static final String mapStr = "2\n2\n..\n.b";
	private static final String tactics = "goto 0";
	
	
	@Before
	public void setUp()
	{
		mp = new MapParser();
		tp = new TacticsParser(new ExpectLogger());
		sim = new Simulator(new ExpectLogger(), 10000, new Random());
		
		InputStream mapInput;
		InputStream tacticsInput;
		
		mapInput     = new ByteArrayInputStream(Charset.forName("UTF-16").encode(mapStr).array());
		tacticsInput = new ByteArrayInputStream(Charset.forName("UTF-16").encode(tactics).array());
		
		List<InputStream> tactics = new LinkedList<>();
		OutputStream out          = new ByteArrayOutputStream();
		tactics.add(tacticsInput);
		
		contr = new Controller(sim, mp, tp, mapInput, tactics, 0, out);
		contr.initializeSimulator();
	}
	
	@Test
	public void initTest()
	{
		assertTrue("The controller's method initializeSimulator() didn't set the simulator's map", sim.getWorldmap() != null);
		assertTrue("The controller's method initializeSimulator() didn't set the simulator's factions", sim.getFactions() != null);
		
		List<Faction> factions = sim.getFactions();
		Faction faction        = factions.iterator().next();

		assertTrue("The controller's method initializeSimulator() did set the wrong faction", faction.getName().equals("b"));
		assertTrue("The controller's method initializeSimulator() didn't set the faction's tactic programm", faction.getTactics() == null);
	}
	
	@Test
	public void playTest()
	{
		int valueSimulator = testSimulator.value;
		
		controller.setSimulator(testSimulator);
		controller.play();
		
		int expectedValueSimulator = valueSimulator + 1;
		
		assertTrue("the simulator's method step() was not called", expectedValueSimulator == testSimulator.value);
	}
	
	@Test
	public void getsetTest()
	{
		controller = new Controller(null, null, null, null, null, 0, null);
		
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
		contr.setSimulator(testSimulator);
		Thread contrThread = new Thread(contrRun);
		contrThread.run();
		contr.pause();
		int firstStepCount = testSimulator.getValue();
		try{
			Thread.sleep(200);
		}
		catch(InterruptedException e){
			throw new UnsupportedOperationException("Who the fuck interrupts a test?");
		}
		int secondStepCount = testSimulator.getValue();
		assertTrue("Controller didn't stop", firstStepCount == secondStepCount);
	}
	
}
