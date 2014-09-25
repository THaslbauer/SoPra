package de.unisaarland.cs.st.pirates.group1.tests.sim.logic;

import static org.junit.Assert.*;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.*;
import de.unisaarland.cs.st.pirates.group1.sim.logic.instruction.elseInstructions.MoveInstruction;
import de.unisaarland.cs.st.pirates.group1.sim.util.Heading;
import de.unisaarland.cs.st.pirates.group1.tests.sim.util.*;
import de.unisaarland.cs.st.pirates.group1.tests.testLogger.Create;
import de.unisaarland.cs.st.pirates.group1.tests.testLogger.Destroy;
import de.unisaarland.cs.st.pirates.group1.tests.testLogger.ExpectLogger;
import de.unisaarland.cs.st.pirates.group1.tests.testLogger.Fight;
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
	 *  . &! .
	 *    § ? !
	 */
	
	private static class ResetFactory extends EntityFactory {
		public void reset() {
			//this.krakenNextId = 0; TODO
		}
	}
	
	private static class NoRandom extends Worldmap.sRandom {
		public int nextInt;
		public int lastInt = 0;
		public int a;
		public int nextInt(int a) {
			this.a = a;
			return nextInt;
		}
		public void setNextInt(int a) {
			nextInt = a;
		}
		public NoRandom(long a) {
			super(a);
		}
		public NoRandom() {
			super(123546789L);
		}
		public int getLastInt() {
			return lastInt;
		}
		public int getLastA() {
			return a;
		}
	}
	
	
	private static ExpectLogger elo = new ExpectLogger();
	private static Worldmap map1;
	private static Worldmap map2;
	private static Faction faction1 = new Faction("a",1);
	private static Faction faction2 = new Faction("b",2);
	private static Ship m1ship;
	private static Ship m2ship;
	private static Ship m2shipE;
	private static Ship m2shipE2;
	private static Ship m2shipF;
	private static Kraken m1kraken;
	
	@Before
	public void setupMaps() {
		map1 = new Worldmap6T(3,4,elo, new EntityFactory());
		map1.createSeaTile(new Position(0,0));
		map1.createSeaTile(new Position(1,0));
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
		
		 map2 = new Worldmap6T(3,2, elo, new EntityFactory());
		map2.createSeaTile(new Position(0,0));
		map2.createSeaTile(new Position(1,0));
		Kraken map2kraken = new Kraken(0, map2.getTile(new Position(1,0)));
		map2.createSeaTile(new Position(2,0));
		map2.createSeaTile(new Position(0,1));
		map2.createSeaTile(new Position(1,1));
		map2.createSeaTile(new Position(2,1));
		
		resetM1ship();
		setupShipForMap2();
	}
	
	
	
	public void setupShipForMap2(){
		try{
			m2shipE.setMyTile(null); 
		} catch(NullPointerException e) {
		} finally {
			m2shipE = new Ship(faction2,1,map2.getTile(new Position(1,0)));
		}
		
		try {
			m2shipF.setMyTile(null);
		} catch(NullPointerException e) {
		} finally {
			m2shipF = new Ship(faction1, 3, map2.getTile(new Position(0,1)));
		}
		
		try {
			m2shipE2.setMyTile(null);
		} catch(NullPointerException e) {
		} finally {
			m2shipE2 = new Ship(faction2, 2, map2.getTile(new Position(2,1)));
		}


		try {
			m2ship.setMyTile(null);
		} catch(NullPointerException e) {
		} finally {
			m2ship = new Ship(faction1, 0, map2.getTile(new Position(1,1)));
		}
		
	}
	
	
	
	private void stuffCheck(Ship ship, int pc, int resttime, int boredom) {
		assertTrue("pc: expected "+pc+" got "+ship.getPC(), ship.getPC() == pc);
		assertTrue("resttime", ship.getRestTime() == resttime);
		assertTrue("boredom: expected "+boredom+" got "+ship.getBoredom(), ship.getBoredom() == boredom);
		if(resttime != 0)
			elo.expect(new Notify(Entity.SHIP, ship.getId(), Key.RESTING, resttime));
		elo.expect(new Notify(Entity.SHIP, ship.getId(), Key.PC, pc));
	}
	
	private void  moraleCheck(Ship ship, int morale) {
		assertTrue("Morale: Expected "+morale+" got "+ship.getMorale(), ship.getMorale() == morale);
		elo.expect(new Notify(Entity.SHIP, ship.getId(), Key.MORAL, morale));
	}
	
	private void  conditionCheck(Ship ship, int condition) {
		assertTrue("Condition: Expected "+condition+" got "+ship.getCondition(), ship.getCondition() == condition);
		elo.expect(new Notify(Entity.SHIP, ship.getId(), Key.CONDITION, condition));
	}
	
	private void  loadCheck(Ship ship, int load) {
		assertTrue("Load: Expected "+load+" got "+ship.getLoad(), ship.getLoad() == load);
		elo.expect(new Notify(Entity.SHIP, ship.getId(), Key.VALUE, load));
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
	
	private void posSink(Ship ship, int x, int y) {
		elo.expect(new Notify(Entity.SHIP, ship.getId(), Key.X_COORD, x));
		elo.expect(new Notify(Entity.SHIP, ship.getId(), Key.Y_COORD, y));
	}
	
	
	public void resetM1ship() {
		try{
			m1ship.setMyTile(null);
		} catch(NullPointerException e) {
		} finally {
			m1ship = new Ship(faction1, 0, map1.getTile(new Position(1,1)));
		}
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
	}
	
	@Test
	public void driveOnOwnBase() {
		m1ship.setHeading(Heading.H2);
		setPrivateField(m1ship, "boredom", 35);
		m1ship.setMorale(1);
		MoveInstruction mi = new MoveInstruction(elo, 42);
		elo.clear();
		mi.execute(m1ship);
		moraleCheck(m1ship, 4);
		posCheck(m1ship, 1, 2);
		stuffCheck(m1ship, 1, 4, 0);
		assertTrue(m1ship.getMorale() == 4);
		elo.expectNothing();
	}
	
	@Test
	public void driveOnEnemyBase() {
		m1ship.setHeading(Heading.H1);
		MoveInstruction mi = new MoveInstruction(elo, 42);
		elo.clear();
		mi.execute(m1ship);
		posStay(m1ship, 1, 1);
		stuffCheck(m1ship, 42, 0, 1);
		elo.expectNothing();
	}
	
	@Test
	public void driveOnDaBeach() {
		m1ship.setHeading(Heading.H3);
		m1ship.setLoad(3);
		MoveInstruction mi = new MoveInstruction(elo, 0);
		elo.clear();
		mi.execute(m1ship);
		posStay(m1ship, 1,1);
		conditionCheck(m1ship, 2);
		moraleCheck(m1ship, 3);
		loadCheck(m1ship, 2);
		try {
			assertTrue(map1.getTile(new Position(1,1)).getTreasure().getValue() == 1);
		} catch(NullPointerException e) {
			fail("There must be a treasure here");
		}
		elo.expect(new Create(Entity.TREASURE, 0, new Key[] {Key.VALUE, Key.X_COORD, Key.Y_COORD}, new int[] {1, 1, 1}));
		stuffCheck(m1ship, 0, 0, 1);
		elo.expectNothing();
	}
	
	@Test
	public void driveOnDaBeachAndSink() {
		m1ship.setHeading(Heading.H3);
		m1ship.setMorale(0);
		m1ship.setLoad(3);
		m1ship.setCondition(1);
		MoveInstruction mi = new MoveInstruction(elo, 0);
		elo.clear();
		mi.execute(m1ship);
		conditionCheck(m1ship, 0);
		loadCheck(m1ship, 2);
		try {
			assertTrue("treasure should be 1 is "+map1.getTile(new Position(1,1)).getTreasure().getValue(), map1.getTile(new Position(1,1)).getTreasure().getValue() == 1);
		} catch(NullPointerException e) {
			fail("There must be a treasure here");
		}
		elo.expect(new Create(Entity.TREASURE, 0, new Key[] {Key.VALUE, Key.X_COORD, Key.Y_COORD}, new int[] {1, 1, 1}));
		assertTrue("The ship should be cleaned up",map1.getTile(new Position(1,1)).getShip() == null && m1ship.getMyTile() == null);
		elo.expect(new Destroy(Entity.SHIP, m1ship.getId()));
		elo.expectNothing();
	}
	
	@Test
	public void driveOnTheKraken() {
		m1ship.setHeading(Heading.H0);
		m1ship.setCondition(2);
		MoveInstruction mi = new MoveInstruction(elo, 12);
		elo.clear();
		mi.execute(m1ship);
		posCheck(m1ship, 2, 1);
		stuffCheck(m1ship, 1, 4, 1);
		assertTrue(m1ship.getCondition() == 1);
		elo.expect(new Notify(Entity.SHIP, m1ship.getId(), Key.CONDITION, 1));
		elo.expect(new Fight(m1ship, m1kraken));
		elo.expectNothing();
	}

	@Test
	public void driveOnTheKrakenAndSink() {
		m1ship.setHeading(Heading.H0);
		m1ship.setCondition(1);
		MoveInstruction mi = new MoveInstruction(elo, 12);
		elo.clear();
		mi.execute(m1ship);
		elo.expect(new Notify(Entity.SHIP, m1ship.getId(), Key.X_COORD, 2));
		elo.expect(new Notify(Entity.SHIP, m1ship.getId(), Key.Y_COORD, 1));
		stuffCheck(m1ship, 1, 4, 1);
		assertTrue(m1ship.getCondition() == 0);
		elo.expect(new Notify(Entity.SHIP, m1ship.getId(), Key.CONDITION, 0)); /// ja oder nein???
		assertTrue("The ship should be cleaned up",map1.getTile(new Position(2,1)).getShip() == null && m1ship.getMyTile() == null);
		elo.expect(new Destroy(Entity.SHIP, m1ship.getId()));
		elo.expect(new Fight(m1ship, m1kraken));
		elo.expectNothing();
	}
	
	
	
	
	/*  map 2:
	 *  . &! .
	 *    § ? !
	 */
	
	
	
	@Test
	public void driveOnFriendlyShip() {
		m2ship.setHeading(Heading.H3);
		MoveInstruction mi = new MoveInstruction(elo, 7895);
		elo.clear();
		mi.execute(m2ship);
		posStay(m2ship, 1, 1);
		posStay(m2shipF, 0, 1);
		stuffCheck(m2ship, 7895, 0, 1);
		elo.expectNothing();
	}
	
	
	@Test
	public void fightAndLoseMorale() {
		m2ship.setHeading(Heading.H0);
		m2ship.setMorale(3);
		m2ship.setLoad(4);
		m2shipE2.setLoad(2);
		MoveInstruction mi = new MoveInstruction(elo, 789);
		elo.clear();
		mi.execute(m2ship);
		posStay(m2ship, 1, 1);
		conditionCheck(m2ship, 2);
		moraleCheck(m2ship, 2);
		assertTrue("load", m2ship.getLoad() == 0);
		elo.expect(new Notify(Entity.SHIP, m2ship.getId(), Key.VALUE, 0));
		try{
			assertTrue("treasure value", m2ship.getMyTile().getTreasure().getValue() == 2);
		} catch(NullPointerException e) {
			fail("Treasure must be created");
		}
		elo.expect(new Create(Entity.TREASURE, 0, new Key[] {Key.VALUE, Key.X_COORD, Key.Y_COORD}, new int[] {2, 1, 1}));
		assertTrue("load enemy", m2shipE2.getLoad() == 4);
		elo.expect(new Notify(Entity.SHIP, m2shipE2.getId(), Key.VALUE, 4));
		stuffCheck(m2ship, 789, 0, 1);
		elo.expectNothing();
	}
	
	@Test
	public void fightAndSinkCondition() {
		m2ship.setHeading(Heading.H0);
		m2ship.setMorale(0);
		m2ship.setLoad(3);
		m2ship.setCondition(1);
		m2shipE2.setMorale(0);
		m2shipE2.setLoad(4);
		MoveInstruction mi = new MoveInstruction(elo, 789);
		elo.clear();
		mi.execute(m2ship);
		elo.expect(new Notify(Entity.SHIP, m2ship.getId(), Key.CONDITION, 0));
		elo.expect(new Notify(Entity.SHIP, m2ship.getId(), Key.VALUE, 0));
		try{
			assertTrue(" treasure value", map2.getTile(new Position(1,1)).getTreasure().getValue() == 3);
		} catch(NullPointerException e) {
			fail("Treasure must be created");
		}
		elo.expect(new Create(Entity.TREASURE, 0, new Key[] {Key.VALUE, Key.X_COORD, Key.Y_COORD}, new int[] {3, 1, 1}));
		assertTrue("load enemy", m2shipE2.getLoad() == 4);
		elo.expect(new Notify(Entity.SHIP, m2shipE2.getId(), Key.VALUE, 4));
		elo.expect(new Destroy(Entity.SHIP, m2ship.getId()));
		elo.expectNothing();
		elo.expect(new Fight(m2ship, m2shipE2));
	}
	
	@Test
	public void fightAndSinkRandom () {
		m2ship.setHeading(Heading.H0);
		m2ship.setMorale(4);
		m2ship.setLoad(4);
		m2ship.setCondition(1);
		m2shipE2.setMorale(4);
		m2shipE2.setLoad(0);
		m2shipE2.setCondition(1);
		map2.setRandom(new NoRandom());
		((NoRandom)map2.random).setNextInt(0);
		assertTrue(((NoRandom)map2.random).getLastA() < 2);
		MoveInstruction mi = new MoveInstruction(elo, 123);
		elo.clear();
		mi.execute(m2ship);
		elo.expect(new Notify(Entity.SHIP, m2ship.getId(), Key.CONDITION, 0));
		elo.expect(new Notify(Entity.SHIP, m2ship.getId(), Key.MORAL, 3));
		elo.expect(new Notify(Entity.SHIP, m2ship.getId(), Key.VALUE, 0));
		assertTrue("load enemy", m2shipE2.getLoad() == 4);
		elo.expect(new Notify(Entity.SHIP, m2shipE2.getId(), Key.VALUE, 4));
		elo.expect(new Destroy(Entity.SHIP, m2ship.getId()));
		elo.expectNothing();
		elo.expect(new Fight(m2ship, m2shipE2));
	}
	
	@Test
	public void fightAndWinCondition() {
		m2ship.setHeading(Heading.H0);
		m2ship.setMorale(3);
		m2ship.setLoad(3);
		m2shipE2.setMorale(3);
		m2shipE2.setCondition(2);
		m2shipE2.setLoad(0);
		MoveInstruction mi = new MoveInstruction(elo, 789);
		elo.clear();
		mi.execute(m2ship);
		posStay(m2ship, 1, 1);
		conditionCheck(m2shipE2, 1);
		moraleCheck(m2shipE2, 2);
		assertTrue("morale ", m2ship.getMorale() == 3);
		assertTrue("load", m2ship.getLoad() == 3);
		assertTrue("load enemy", m2shipE2.getLoad() == 0);
		stuffCheck(m2ship, 789, 0, 1);
		elo.expectNothing();
	}
	
	@Test
	public void fightAndKillMoraleWithTooMuchBoredom() {
		m2ship.setHeading(Heading.H0);
		m2ship.setCondition(1);
		m2ship.setLoad(2);
		ReflectionHelper.setPrivateField(m2ship, "boredom", 39);
		m2shipE2.setMorale(3);
		m2shipE2.setCondition(1);
		m2shipE2.setLoad(3);
		MoveInstruction mi = new MoveInstruction(elo, 312);
		elo.clear();
		mi.execute(m2ship);
		assertTrue("condition ", m2ship.getCondition() == 1);
		assertTrue("morale ", m2ship.getMorale() == 3);
		conditionCheck(m2shipE2, 0);
		moraleCheck(m2shipE2, 2);
		
		
		assertTrue("load enemy", m2shipE2.getLoad() == 0);
		elo.expect(new Notify(Entity.SHIP, m2shipE2.getId(), Key.VALUE, 0));
		try{
			assertTrue("treasure value", m2ship.getMyTile().getTreasure().getValue() == 1);
		} catch(NullPointerException e) {
			fail("Treasure must be created");
		}
		elo.expect(new Create(Entity.TREASURE, 0, new Key[] {Key.VALUE, Key.X_COORD, Key.Y_COORD}, new int[] {1, 1, 1}));
		assertTrue("load", m2ship.getLoad() == 4);
		elo.expect(new Notify(Entity.SHIP, m2ship.getId(), Key.VALUE, 4));
		elo.expect(new Destroy(Entity.SHIP, m2shipE2.getId()));
		assertTrue(map2.getTile(new Position(1,1)).getShip() == null);
		posCheck(m2ship, 2,1);
		stuffCheck(m2ship, 1, 6, 0);
		elo.expect(new Notify(Entity.SHIP, m2ship.getId(), Key.MORAL, 3));
		elo.expectNothing();
	}
	
	@Test
	public void fightAndKillRandomSoGetMaxWeight() {
		m2ship.setHeading(Heading.H0);
		m2ship.setMorale(0);
		m2ship.setCondition(1);
		m2ship.setLoad(1);
		m2ship.setPC(456);
		ReflectionHelper.setPrivateField(m2ship, "boredom", 39);
		m2shipE2.setMorale(0);
		m2shipE2.setCondition(1);
		map2.setRandom(new NoRandom());
		((NoRandom)map2.random).setNextInt(1);
		assertTrue(((NoRandom)map2.random).getLastA() < 2);
		MoveInstruction mi = new MoveInstruction(elo, 789);
		elo.clear();
		mi.execute(m2ship);
		conditionCheck(m2shipE2, 0);
		assertTrue(m2shipE2.getMorale() == 0);
		elo.expect(new Destroy(Entity.SHIP, m2shipE2.getId()));
		posCheck(m2ship, 2,1);
		stuffCheck(m2ship, 457, 8, 0);
		elo.expectNothing();
	}
	
	
	@Test
	public void coverageBeachWithNoLoad() {
		m1ship.setHeading(Heading.H3);
		m1ship.setLoad(0);
		MoveInstruction mi = new MoveInstruction(elo, 0);
		elo.clear();
		mi.execute(m1ship);
		posStay(m1ship, 1,1);
		conditionCheck(m1ship, 2);
		moraleCheck(m1ship, 3);
		stuffCheck(m1ship, 0, 0, 1);
		elo.expectNothing();
	}
	
	
	@Test
	public void cleanUpTest() {
		try {
		MoveInstruction mi = new MoveInstruction(new ExpectLogger(), 42);
		Sea tile = new Sea(null, new Position(0,0));
		Ship ship = new Ship(new Faction("a",0), 1, tile);
		Class<?> c = mi.getClass();
		Method cleanup = c.getDeclaredMethod("cleanup", Ship.class);
		cleanup.setAccessible(true);
		ship.setCondition(0);
		cleanup.invoke(mi,ship);
		assertTrue(ship.getMyTile() == null);
		assertTrue(tile.getShip() == null);
		} catch(Exception e) {
			fail(e.getMessage());
		}
	}
}
