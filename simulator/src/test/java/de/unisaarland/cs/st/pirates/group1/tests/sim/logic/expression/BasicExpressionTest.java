package de.unisaarland.cs.st.pirates.group1.tests.sim.logic.expression;

import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.EntityFactory;
import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Faction;
import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Position;
import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Ship;
import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Tile;
import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Worldmap;
import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Worldmap6T;
import de.unisaarland.cs.st.pirates.group1.sim.logger.ExtendedLogWriter;
import de.unisaarland.cs.st.pirates.group1.sim.logger.InfoPoint;
import de.unisaarland.cs.st.pirates.group1.sim.logic.expression.Expression;
import de.unisaarland.cs.st.pirates.group1.tests.testUtil.ExpectLogger;
import junit.framework.TestCase;

public class BasicExpressionTest extends TestCase {
	protected Ship testShip;
	protected Tile testTile;
	protected Worldmap testMap;
	protected Faction testFaction;
	protected ExpectLogger testLogger;
	protected Position middle;
	protected EntityFactory testFactory;
	
	
	@Before
	public void setUp() {
		this.testLogger = new ExpectLogger();
		this.middle = new Position(1,1);
		testFactory = new EntityFactory();
		testFaction = new Faction("test", 0);
		InfoPoint point = new InfoPoint();
		this.testMap = new Worldmap6T(4, 4, point, testFactory);
		testMap.createSeaTile(new Position(0,0));
		testMap.createSeaTile(new Position(0,1));
		testMap.createSeaTile(new Position(0,2));
		testMap.createSeaTile(new Position(0,3));
		testMap.createSeaTile(new Position(1,0));
		this.testTile = testMap.createSeaTile(middle);
		testMap.createSeaTile(new Position(1,2));
		testMap.createSeaTile(new Position(1,3));
		testMap.createSeaTile(new Position(2,0));
		testMap.createSeaTile(new Position(2,1));
		testMap.createSeaTile(new Position(2,2));
		testMap.createSeaTile(new Position(2,3));
		testMap.createSeaTile(new Position(3,0));
		testMap.createSeaTile(new Position(3,1));
		testMap.createSeaTile(new Position(3,2));
		testMap.createSeaTile(new Position(3,3));
		testShip = testFactory.createShip(testFaction, testTile);
		LinkedList<ExtendedLogWriter> loggers = new LinkedList<>();
		loggers.add(testLogger);
		point.setGUI(loggers);
	}
	
	@Test
	public void testFinals() {
		assertTrue("final value of TRUE ist wrong", Expression.TRUE <= 1 && Expression.TRUE >= 1);
		assertTrue("final value of FALSE ist wrong", Expression.FALSE <= 0&& Expression.FALSE >= 0);
	}
}
