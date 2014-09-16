package de.unisaarland.cs.st.pirates.group1.tests.sim.logger;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Kraken;
import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Ship;
import de.unisaarland.cs.st.pirates.group1.sim.logger.InfoPoint;
import de.unisaarland.cs.st.pirates.group1.tests.testUtil.TestGui;
import de.unisaarland.cs.st.pirates.group1.tests.testUtil.TestLogger;

public class InfoPointTest
{
	private static InfoPoint infoPoint;
	
	private static TestLogger testLogger;
	
	private static TestGui testGui;
	
	
	@BeforeClass
	public static void setUp()
	{
		infoPoint  = new InfoPoint();
		testLogger = new TestLogger();
		testGui    = new TestGui();
		
		infoPoint.setRefLogger(testLogger);
		infoPoint.setGUI(testGui);
	}
	
	@Test
	public void fight1Test()
	{
		int valTestLogger     = testLogger.value;
		int expectedValLogger = valTestLogger += 1;
		
		int valTestGui        = testGui.value;
		int expectedValGui    = valTestGui += 1;
		
		infoPoint.fight(new Ship(null, 0, null), new Ship(null, 0, null));
		
		assertTrue("The logger's method fight(ship, ship) was not called.", expectedValLogger == testLogger.value);
		assertTrue("The gui's method fight(ship, ship) was not called.", expectedValGui == testGui.value);
	}
	
	@Test
	public void fight2Test()
	{
		int valTestLogger     = testLogger.value;
		int expectedValLogger = valTestLogger += 1;
		
		int valTestGui        = testGui.value;
		int expectedValGui    = valTestGui += 1;
		
		infoPoint.fight(new Ship(null, 0, null), new Kraken(0, null));
		
		assertTrue("The logger's method fight(ship, kraken) was not called.", expectedValLogger == testLogger.value);
		assertTrue("The gui's method fight(ship, kraken) was not called.", expectedValGui == testGui.value);
	}
	
	@Test
	public void addCellTest()
	{
		int valTestLogger     = testLogger.value;
		int expectedValLogger = valTestLogger += 1;
		
		int valTestGui        = testGui.value;
		int expectedValGui    = valTestGui += 1;
		
		infoPoint.addCell(null, 0, 0, 0);
		
		assertTrue("The logger's method addcell() was not called.", expectedValLogger == testLogger.value);
		assertTrue("The gui's method addcell() was not called.", expectedValGui == testGui.value);
	}
	
	@Test
	public void addCustomHeaderDataTest()
	{
		int valTestLogger     = testLogger.value;
		int expectedValLogger = valTestLogger += 1;
		
		int valTestGui        = testGui.value;
		int expectedValGui    = valTestGui += 1;
		
		infoPoint.addCustomHeaderData(null);
		
		assertTrue("The logger's method addCustomHeaderData() was not called.", expectedValLogger == testLogger.value);
		assertTrue("The gui's method addCustomHeaderData() was not called.", expectedValGui == testGui.value);
	}
	
	@Test
	public void beginTransactionTest()
	{
		int valTestLogger     = testLogger.value;
		int expectedValLogger = valTestLogger += 1;
		
		int valTestGui        = testGui.value;
		int expectedValGui    = valTestGui += 1;
		
		infoPoint.beginTransaction(null, 0);
		
		assertTrue("The logger's method beginTransaction() was not called.", expectedValLogger == testLogger.value);
		assertTrue("The gui's method beginTransaction() was not called.", expectedValGui == testGui.value);
	}
	
	@Test
	public void closeTest()
	{
		int valTestLogger     = testLogger.value;
		int expectedValLogger = valTestLogger += 1;
		
		int valTestGui        = testGui.value;
		int expectedValGui    = valTestGui += 1;
		
		try
		{
			infoPoint.close();
		} 
		catch (Exception e) 
		{
			fail();
		}
		
		assertTrue("The logger's method closeTest() was not called.", expectedValLogger == testLogger.value);
		assertTrue("The gui's method closeTest() was not called.", expectedValGui == testGui.value);
	}
	
	@Test
	public void commitTransactionTest()
	{
		int valTestLogger     = testLogger.value;
		int expectedValLogger = valTestLogger += 1;
		
		int valTestGui        = testGui.value;
		int expectedValGui    = valTestGui += 1;
		
		infoPoint.commitTransaction(null);
		
		assertTrue("The logger's method commitTransaction() was not called.", expectedValLogger == testLogger.value);
		assertTrue("The gui's method commitTransaction() was not called.", expectedValGui == testGui.value);
	}
	
	@Test
	public void createTest()
	{
		int valTestLogger     = testLogger.value;
		int expectedValLogger = valTestLogger += 1;
		
		int valTestGui        = testGui.value;
		int expectedValGui    = valTestGui += 1;
		
		infoPoint.create(null, 0, null, null);
		
		assertTrue("The logger's method createTest() was not called.", expectedValLogger == testLogger.value);
		assertTrue("The gui's method createTest() was not called.", expectedValGui == testGui.value);
	}
	
	@Test
	public void destroyTest()
	{
		int valTestLogger     = testLogger.value;
		int expectedValLogger = valTestLogger += 1;
		
		int valTestGui        = testGui.value;
		int expectedValGui    = valTestGui += 1;
		
		infoPoint.destroy(null, 0);
		
		assertTrue("The logger's method destroy() was not called.", expectedValLogger == testLogger.value);
		assertTrue("The gui's method destroy() was not called.", expectedValGui == testGui.value);
	}
	
	@Test
	public void fleetScoreTest()
	{
		int valTestLogger     = testLogger.value;
		int expectedValLogger = valTestLogger += 1;
		
		int valTestGui        = testGui.value;
		int expectedValGui    = valTestGui += 1;
		
		infoPoint.fleetScore(0, 0);
		
		assertTrue("The logger's method fleetScore() was not called.", expectedValLogger == testLogger.value);
		assertTrue("The gui's method fleetScore() was not called.", expectedValGui == testGui.value);
	}
	
	@Test
	public void initTest()
	{
		int valTestLogger     = testLogger.value;
		int expectedValLogger = valTestLogger += 1;
		
		int valTestGui        = testGui.value;
		int expectedValGui    = valTestGui += 1;
		
		try
		{
			infoPoint.init(null, null);
		}
		catch (Exception e)
		{
			fail();
		}
		
		assertTrue("The logger's method init() was not called.", expectedValLogger == testLogger.value);
		assertTrue("The gui's method init() was not called.", expectedValGui == testGui.value);
	}
	
	@Test
	public void logStepTest()
	{
		int valTestLogger     = testLogger.value;
		int expectedValLogger = valTestLogger += 1;
		
		int valTestGui        = testGui.value;
		int expectedValGui    = valTestGui += 1;
		
		try
		{
			infoPoint.logStep();
		}
		catch (Exception e)
		{
			fail();
		}
		
		assertTrue("The logger's method logStep() was not called.", expectedValLogger == testLogger.value);
		assertTrue("The gui's method logStep() was not called.", expectedValGui == testGui.value);
	}
	
	@Test
	public void notifyTest()
	{
		int valTestLogger     = testLogger.value;
		int expectedValLogger = valTestLogger += 1;
		
		int valTestGui        = testGui.value;
		int expectedValGui    = valTestGui += 1;
		
		infoPoint.notify(null, 0, null, 0);
		
		assertTrue("The logger's method notify() was not called.", expectedValLogger == testLogger.value);
		assertTrue("The gui's method notify() was not called.", expectedValGui == testGui.value);
	}
	
	@Test
	public void getsetTest()
	{
		infoPoint = new InfoPoint();
		
		infoPoint.setRefLogger(testLogger);
		assertTrue("The infoPoint's setter or getter for the RefLogger was incorrect",
				testLogger.equals(infoPoint.getRefLogger()));
		
		
		infoPoint.setGUI(testGui);
		assertTrue("The infoPoint's setter or getter for the GUI was incorrect",
				testGui.equals(infoPoint.getGUI()));
	}
	
}
