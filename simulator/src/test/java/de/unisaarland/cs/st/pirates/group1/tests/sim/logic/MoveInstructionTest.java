package de.unisaarland.cs.st.pirates.group1.tests.sim.logic;

import static org.junit.Assert.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.BeforeClass;
import org.junit.Test;

import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.*;
import de.unisaarland.cs.st.pirates.group1.sim.logic.instruction.elseInstructions.MoveInstruction;
import de.unisaarland.cs.st.pirates.group1.tests.sim.util.*;
import de.unisaarland.cs.st.pirates.group1.tests.testLogger.ExpectLogger;
import de.unisaarland.cs.st.pirates.group1.tests.testUtil.DummyEntityFactory;

public class MoveInstructionTest {
	/* 			O	M	G			*/
	
	// ? this ship  ! enemy ship   § friendly ship
	/*  map1:
	 *  . ! .
	 *  # ? &
	 *  a . b
	 */
	
	/*  map 2:
	 *  . &!
	 *  § ?
	 */
	
	private static DummyEntityFactory def = new DummyEntityFactory();
	private static ExpectLogger elo = new ExpectLogger();
	private static Worldmap map1 = new Worldmap6T(3, 4, elo, def);
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
		map1.createBaseTile(new Position(0,2), faction1);
		map1.createSeaTile(new Position(1,2));
		map1.createBaseTile(new Position(2,2), faction2);		
	}
	
	@Test
	public void easyMoveTest() {
		MoveInstruction mi = new MoveInstruction(elo, 42);
	}
	
	@Test
	public void cleanUpTest() {
		try {
		Class c = Class.forName("MoveInstruction");
		Method cleanup = c.getDeclaredMethod("cleanup", (Class <?>) null);
		cleanup.setAccessible(true);
		Object inst = c.newInstance();
		Object res = cleanup.invoke(inst, null);
		} catch(Exception e) {
			fail();
		}

		
	}
}
