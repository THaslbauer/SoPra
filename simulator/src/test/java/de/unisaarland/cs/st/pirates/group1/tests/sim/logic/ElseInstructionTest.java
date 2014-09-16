/**
 * The Testing class for ElseInstructions
 * 
 * @version 1.1
 * @author christopher
 */
package de.unisaarland.cs.st.pirates.group1.tests.sim.logic;

import static org.junit.Assert.assertTrue;

import java.lang.reflect.Array;

import org.junit.BeforeClass;
import org.junit.Test;

import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Faction;
import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Position;
import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Ship;
import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Tile;
import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Worldmap;
import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Worldmap6T;
import de.unisaarland.cs.st.pirates.group1.sim.logic.expression.Expression;
import de.unisaarland.cs.st.pirates.group1.sim.logic.expression.Literal;
import de.unisaarland.cs.st.pirates.group1.sim.logic.instruction.Instruction;
import de.unisaarland.cs.st.pirates.group1.sim.logic.instruction.elseInstructions.ElseInstruction;
import de.unisaarland.cs.st.pirates.group1.sim.logic.instruction.elseInstructions.IfAllInstruction;
import de.unisaarland.cs.st.pirates.group1.sim.logic.instruction.normalInstructions.DropInstruction;
import de.unisaarland.cs.st.pirates.group1.tests.testUtil.TestGuiDropInstr;

public class ElseInstructionTest {
	private static Worldmap worldMap;
	private static Faction faction;
	private static Ship ship;
	private static Tile baseTile;
	private static Tile waterTile1;
	private static Tile waterTile2;
	private static Tile islandTile2;
	private static Tile islandTile1;
	private static Position position1;
	private static int x;
	
	@BeforeClass
	public static void init(){
		x = 0;
		
		//A worldMap with exactly one seatile
		worldMap = new Worldmap6T(2,2,null,null);
		
		//A TestFaction, Position and Tile
		faction = new Faction("a",0);
		Position position1 = new Position(0,0);
		Position position2 = new Position(1,0);
		Position position3 = new Position(0,1);
		Position position4 = new Position(1,1);
		
		waterTile1 = worldMap.createSeaTile(position1);
		waterTile2 = worldMap.createSeaTile(position2);
		islandTile1 = worldMap.createIslandTile(position3, true);
		islandTile2 = worldMap.createIslandTile(position3, false);
		
		
		//A Test ship of the TestFaction with ID 1, if ship is attaching itself
		ship = new Ship(faction,1,waterTile1);
		
		//waterTile1.attach(ship);
		
	}
	
	//FlipZeroInstruction Test TODO WHAT TO TEST?
	
	@Test
	public void flipZeroInstructionPCTest(){
		//TODO TEST
	}
	
	//IfALlInstruction Test should evaluate to false and set the new PC
	@Test
	public void ifAllInstructionTest(){
		Expression[] expArray = new Expression[2];
		expArray[0] = new Literal(42);
		expArray[1] = new Literal(1);
		TestGuiDropInstr testGui = new TestGuiDropInstr();
		Instruction ifAllInstruction = new IfAllInstruction(testGui, 12, expArray);
		
		//TODO check if this test is right!!
		ifAllInstruction.execute(ship);
		
		assertTrue(ship.getPC() == 12);
	}
	
	@Test
	public void ifAnyInstructionTest(){
		Expression[] expArray = new Expression[2];
		expArray[0] = new Literal(42);
		expArray[1] = new Literal(1);
		TestGuiDropInstr testGui = new TestGuiDropInstr();
		Instruction ifAllInstruction = new IfAllInstruction(testGui, 12, expArray);
		
		//TODO check if this test is right!!
		ifAllInstruction.execute(ship);
		
		assertTrue(ship.getPC() == 1);
	}
}
