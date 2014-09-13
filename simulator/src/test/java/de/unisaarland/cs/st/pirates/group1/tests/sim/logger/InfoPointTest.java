package de.unisaarland.cs.st.pirates.group1.tests.sim.logger;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import de.unisaarland.cs.st.pirates.group1.sim.logger.InfoPoint;

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
	public void fightTest()
	{
		int valTestLogger     = testLogger.value;
		int expectedValLogger = valTestLogger += 1;
		
		int valTestGui        = testGui.value;
		int expectedValGui    = valTestGui += 1;
		
		infoPoint.fight();
		
		assertTrue(expectedValLogger == testLogger.value);
		assertTrue(expectedValGui == testGui.value);
	}
	
	@Test
	public void addCellTest()
	{
		int valTestLogger     = testLogger.value;
		int expectedValLogger = valTestLogger += 1;
		
		int valTestGui        = testGui.value;
		int expectedValGui    = valTestGui += 1;
		
		infoPoint.addCell(null, 0, 0, 0);
		
		assertTrue(expectedValLogger == testLogger.value);
		assertTrue(expectedValGui == testGui.value);
	}
	
	@Test
	public void addCustomHeaderDataTest()
	{
		int valTestLogger     = testLogger.value;
		int expectedValLogger = valTestLogger += 1;
		
		int valTestGui        = testGui.value;
		int expectedValGui    = valTestGui += 1;
		
		infoPoint.addCustomHeaderData(null);
		
		assertTrue(expectedValLogger == testLogger.value);
		assertTrue(expectedValGui == testGui.value);
	}
	
	@Test
	public void beginTransactionTest()
	{
		int valTestLogger     = testLogger.value;
		int expectedValLogger = valTestLogger += 1;
		
		int valTestGui        = testGui.value;
		int expectedValGui    = valTestGui += 1;
		
		infoPoint.beginTransaction(null, 0);
		
		assertTrue(expectedValLogger == testLogger.value);
		assertTrue(expectedValGui == testGui.value);
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
		
		assertTrue(expectedValLogger == testLogger.value);
		assertTrue(expectedValGui == testGui.value);
	}
	
	@Test
	public void commitTransactionTest()
	{
		int valTestLogger     = testLogger.value;
		int expectedValLogger = valTestLogger += 1;
		
		int valTestGui        = testGui.value;
		int expectedValGui    = valTestGui += 1;
		
		infoPoint.commitTransaction(null);
		
		assertTrue(expectedValLogger == testLogger.value);
		assertTrue(expectedValGui == testGui.value);
	}
	
	@Test
	public void createTest()
	{
		int valTestLogger     = testLogger.value;
		int expectedValLogger = valTestLogger += 1;
		
		int valTestGui        = testGui.value;
		int expectedValGui    = valTestGui += 1;
		
		infoPoint.create(null, 0, null, null);
		
		assertTrue(expectedValLogger == testLogger.value);
		assertTrue(expectedValGui == testGui.value);
	}
	
	@Test
	public void destroyTest()
	{
		int valTestLogger     = testLogger.value;
		int expectedValLogger = valTestLogger += 1;
		
		int valTestGui        = testGui.value;
		int expectedValGui    = valTestGui += 1;
		
		infoPoint.destroy(null, 0);
		
		assertTrue(expectedValLogger == testLogger.value);
		assertTrue(expectedValGui == testGui.value);
	}
	
	@Test
	public void fleetScoreTest()
	{
		int valTestLogger     = testLogger.value;
		int expectedValLogger = valTestLogger += 1;
		
		int valTestGui        = testGui.value;
		int expectedValGui    = valTestGui += 1;
		
		infoPoint.fleetScore(0, 0);
		
		assertTrue(expectedValLogger == testLogger.value);
		assertTrue(expectedValGui == testGui.value);
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
		
		assertTrue(expectedValLogger == testLogger.value);
		assertTrue(expectedValGui == testGui.value);
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
		
		assertTrue(expectedValLogger == testLogger.value);
		assertTrue(expectedValGui == testGui.value);
	}
	
	@Test
	public void notifyTest()
	{
		int valTestLogger     = testLogger.value;
		int expectedValLogger = valTestLogger += 1;
		
		int valTestGui        = testGui.value;
		int expectedValGui    = valTestGui += 1;
		
		infoPoint.notify(null, 0, null, 0);
		
		assertTrue(expectedValLogger == testLogger.value);
		assertTrue(expectedValGui == testGui.value);
	}
	
	
}
