package de.unisaarland.cs.st.pirates.group1.tests.sim.logic;

import static org.junit.Assert.*;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.BeforeClass;
import org.junit.Test;

import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.*;
import de.unisaarland.cs.st.pirates.group1.sim.logic.instruction.elseInstructions.MoveInstruction;
import de.unisaarland.cs.st.pirates.group1.sim.util.Heading;
import de.unisaarland.cs.st.pirates.group1.tests.sim.util.*;
import de.unisaarland.cs.st.pirates.group1.tests.testLogger.Create;
import de.unisaarland.cs.st.pirates.group1.tests.testLogger.ExpectLogger;
import de.unisaarland.cs.st.pirates.group1.tests.testLogger.Notify;
import de.unisaarland.cs.st.pirates.group1.tests.testUtil.DummyEntityFactory;
import de.unisaarland.cs.st.pirates.group1.tests.testUtil.ReflectionHelper;
import de.unisaarland.cs.st.pirates.logger.LogWriter.Entity;
import de.unisaarland.cs.st.pirates.logger.LogWriter.Key;

import static de.unisaarland.cs.st.pirates.group1.tests.testUtil.ReflectionHelper.*;


public class MoveInstructionTest {
	/* 			O	M	G			*/
	
	// ? this ship  ! enemy ship   § friendly ship
	/*  map1:
	 *  . ! .
	 *   # ? &
	 *  . a b
	 *   . . .
	 */
	
	/*  map 2:
	 *  . &!
	 *  § ?
	 */
	
	private static DummyEntityFactory def = new DummyEntityFactory();
	private static ExpectLogger elo = new ExpectLogger();
	private static Worldmap map1 = new Worldmap6T(3,4,new ExpectLogger(), new EntityFactory()) {
		public void setTile(Tile tile, int x, int y) {
			tiles[y][x] = tile;
		}
	};
	private static Faction faction1 = new Faction("a",1);
	private static Faction faction2 = new Faction("b",2);
	private static Ship m1ship2;
	private static Ship m1ship;
	private static Kraken m1kraken;
	
	@BeforeClass
	public static void setupMap1() {
		map1.createSeaTile(new Position(0,0));
		map1.createSeaTile(new Position(1,0));
		m1ship2 = new Ship(faction2,1,map1.getTile(new Position(1,0))); 
		map1.createSeaTile(new Position(2,0));
		map1.createIslandTile(new Position(0,1), false);
		map1.createSeaTile(new Position(1,1));
		m1ship = new Ship(faction1, 0, map1.getTile(new Position(1,1)));
		map1.createSeaTile(new Position(2,1));
		m1kraken = new Kraken(0, map1.getTile(new Position(2,1)));
		map1.createSeaTile(new Position(0,2));
		map1.createBaseTile(new Position(1,2), faction1);
		map1.createBaseTile(new Position(2,2), faction2);	
		map1.createSeaTile(new Position(0,3));
		map1.createSeaTile(new Position(1,3));
		map1.createSeaTile(new Position(2,3));
	}
	
	private void stuffCheck(Ship ship, int pc, int resttime, int boredom) {
		assertTrue("pc: expected "+pc+" got "+ship.getPC(), ship.getPC() == pc);
		assertTrue("resttime", ship.getRestTime() == resttime);
		assertTrue("boredom", ship.getBoredom() == boredom);
		elo.expect(new Notify(Entity.SHIP, ship.getId(), Key.PC, pc));
		elo.expect(new Notify(Entity.SHIP, ship.getId(), Key.RESTING, resttime));
	}
	
	private void posCheck(Ship ship, int x, int y) {
		assertTrue("position.x: expected "+x+" got "+ship.getMyTile().getPosition().x, ship.getMyTile().getPosition().x == x);
		assertTrue("position.y: expected "+y+" got "+ship.getMyTile().getPosition().y, ship.getMyTile().getPosition().y == y);
		elo.expect(new Notify(Entity.SHIP, ship.getId(), Key.X_COORD, x));
		elo.expect(new Notify(Entity.SHIP, ship.getId(), Key.Y_COORD, y));
	}
	
	private void posStay(Ship ship, int x, int y) {
		assertTrue("position.x: expected "+x+" got "+ship.getMyTile().getPosition().x, ship.getMyTile().getPosition().x == x);
		assertTrue("position.y: expected "+y+" got "+ship.getMyTile().getPosition().y, ship.getMyTile().getPosition().y == y);
	}
	
	private void resetM1ship() {
		m1ship.setMyTile(null);
		m1ship = new Ship(faction1, 0, map1.getTile(new Position(1,1)));
	}
	
	
	@Test
	public void driveOnSea() {
		m1ship.setHeading(Heading.H5);
		MoveInstruction mi = new MoveInstruction(elo, 42);
		elo.clear();
		mi.execute(m1ship);
		posCheck(m1ship, 2,0);
		stuffCheck(m1ship, 1, 4, 1);
		elo.expectNothing();
		resetM1ship();
	}
	
	@Test
	public void driveOnOwnBase() {
		m1ship.setHeading(Heading.H2);
		setPrivateField(m1ship, "boredom", 35);
		m1ship.setMorale(1);
		MoveInstruction mi = new MoveInstruction(elo, 42);
		elo.clear();
		mi.execute(m1ship);
		posCheck(m1ship, 1, 2);
		stuffCheck(m1ship, 1, 4, 0);
		assertTrue(m1ship.getMorale() == 4);
		elo.expect(new Notify(Entity.SHIP, m1ship.getId(), Key.MORAL, 4));
		elo.expectNothing();
		resetM1ship();
	}
	
	@Test
	public void driveOnEnemyBase() {
		m1ship.setHeading(Heading.H1);
		MoveInstruction mi = new MoveInstruction(elo, 42);
		elo.clear();
		mi.execute(m1ship);
		posStay(m1ship, 1, 1);
		stuffCheck(m1ship, 42, 4, 1);
		elo.expectNothing();
		resetM1ship();
	}
	
	@Test
	public void driveOnDaBeach() {
		m1ship.setHeading(Heading.H3);
		m1ship.setLoad(3);
		MoveInstruction mi = new MoveInstruction(elo, 0);
		elo.clear();
		mi.execute(m1ship);
		posStay(m1ship, 1,1);
		assertTrue(m1ship.getMorale() == 3);
		elo.expect(new Notify(Entity.SHIP, m1ship.getId(), Key.MORAL, 3));
		assertTrue(m1ship.getCondition() == 2);
		elo.expect(new Notify(Entity.SHIP, m1ship.getId(), Key.CONDITION, 2));
		try {
			assertTrue(map1.getTile(new Position(1,1)).getTreasure().getValue() == 3 && map1.getTile(new Position(1,1)).getTreasure().getId() == 0);
		} catch(NullPointerException e) {
			fail("There must be a treasure here");
		}
		elo.expect(new Create(Entity.TREASURE, 0, new Key[] {Key.VALUE, Key.X_COORD, Key.Y_COORD}, new int[] {3, 1, 1}));
		elo.expectNothing();
		resetM1ship();
	}
	
	@Test
	public void driveOnTheKraken1() {
		m1ship.setHeading(Heading.H0);
		m1ship.setCondition(2);
		MoveInstruction mi = new MoveInstruction(elo, 12);
		elo.clear();
		mi.execute(m1ship);
		posCheck(m1ship, 2, 1);
		stuffCheck(m1ship, 1, 4, 1);
		assertTrue(m1ship.getCondition() == 1);
		elo.expect(new Notify(Entity.SHIP, m1ship.getId(), Key.CONDITION, 1));
		elo.expectNothing();
		resetM1ship();
	}

	@Test
	public void cleanUpTest() {
		try {
		MoveInstruction mi = new MoveInstruction(new ExpectLogger(), 42);
		Ship ship = new Ship(new Faction("a",0), 1, new Sea(null, new Position(0,0)));
		Class<?> c = mi.getClass();
		Method cleanup = c.getDeclaredMethod("cleanup", Ship.class);
		cleanup.setAccessible(true);
		cleanup.invoke(mi,ship);
		} catch(Exception e) {
			fail(e.getMessage());
		}

		
	}
}
