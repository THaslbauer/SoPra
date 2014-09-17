/**
 * The Testing class for ElseInstructions
 * 
 * @version 1.1
 * @author christopher
 */
package de.unisaarland.cs.st.pirates.group1.tests.sim.logic;

import static org.junit.Assert.assertTrue;

import java.lang.reflect.Array;
import java.util.Random;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Faction;
import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Position;
import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Ship;
import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Tile;
import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Worldmap;
import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Worldmap6T;
import de.unisaarland.cs.st.pirates.group1.sim.logic.expression.EqualOperator;
import de.unisaarland.cs.st.pirates.group1.sim.logic.expression.Expression;
import de.unisaarland.cs.st.pirates.group1.sim.logic.expression.Literal;
import de.unisaarland.cs.st.pirates.group1.sim.logic.expression.Primary;
import de.unisaarland.cs.st.pirates.group1.sim.logic.expression.RegisterCall;
import de.unisaarland.cs.st.pirates.group1.sim.logic.instruction.Instruction;
import de.unisaarland.cs.st.pirates.group1.sim.logic.instruction.elseInstructions.ElseInstruction;
import de.unisaarland.cs.st.pirates.group1.sim.logic.instruction.elseInstructions.FlipZeroInstruction;
import de.unisaarland.cs.st.pirates.group1.sim.logic.instruction.elseInstructions.IfAllInstruction;
import de.unisaarland.cs.st.pirates.group1.sim.logic.instruction.elseInstructions.IfAnyInstruction;
import de.unisaarland.cs.st.pirates.group1.sim.logic.instruction.elseInstructions.IfInstruction;
import de.unisaarland.cs.st.pirates.group1.sim.logic.instruction.elseInstructions.RepairInstruction;
import de.unisaarland.cs.st.pirates.group1.sim.logic.instruction.normalInstructions.DropInstruction;
import de.unisaarland.cs.st.pirates.group1.sim.util.Register;
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
	
	@Before
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
	
	//FlipZeroInstruction Test
	/**
	 * tests if the randomint is generated correctly
	 */
	@Test
	public void flipZeroInstructionRandTest(){
		Random rand = new Random(0);
		
		TestGuiDropInstr testGui = new TestGuiDropInstr();
		FlipZeroInstruction flipZeroInstr = new FlipZeroInstruction(testGui,11,rand);
		
		flipZeroInstr.execute(ship);
		
		Random randTest = new Random(0);
		assertTrue(randTest.nextInt(2) == (rand.nextInt(2)));
	}
	/**
	 * tests if flipZeroInstr jumps correctly
	 */
	@Test
	public void flipZeroInstructionPCTest(){
		Random rand = new Random(0);
		TestGuiDropInstr testGui = new TestGuiDropInstr();
		FlipZeroInstruction flipZeroInstr = new FlipZeroInstruction(testGui,11,rand);
		
		flipZeroInstr.execute(ship);
		
		Random randTest = new Random(0);
		if (randTest.nextInt(2) == 1){
			assertTrue(ship.getPC() == 1);
		}else{
			assertTrue(ship.getPC() == 11);
		}
	}
	
	//IfALlInstruction Test should evaluate to false and set the new PC
	
	/**
	 * tests if an exp array is evaluated to false correctly and the PC is set correctly
	 */
	@Test
	public void ifAllInstructionTest(){
		//simulates the registerArray
		
		ship.setRegister(Register.SENSE_MARKER0, 1);
		
		//calls Register SENSE_MARKER0
		RegisterCall call = new RegisterCall(3);
		
		//build a ExpressionArray for testing puropses
		//and added two equalexpressions testing equality with 0 and 1
		Primary exp = new Literal(0);
		Primary exp2 = new Literal(1);
		
		Expression first = new EqualOperator(call,exp);
		Expression second = new EqualOperator(call, exp2);
		
		Expression[] exparr = new Expression[2];
		exparr[0] = first;
		exparr[1] = second;
		
		TestGuiDropInstr testGui = new TestGuiDropInstr();
		Instruction ifAllInstruction = new IfAllInstruction(testGui, 12, exparr);
		
		
		ifAllInstruction.execute(ship);
		
		assertTrue(ship.getPC() == 12);
	}
	
	/**
	 * tests if an exp array is evaluated to true correctly and the PC is set correctly
	 */
	@Test
	public void ifAllInstructionTrueTest(){
		//simulates the registerArray
		
		ship.setRegister(Register.SENSE_MARKER0, 1);
		
		//calls Register SENSE_MARKER0
		RegisterCall call = new RegisterCall(3);
		
		//build a ExpressionArray for testing puropses
		//and added two equalexpressions testing equality with 0 and 1
		Primary exp2 = new Literal(1);
		
		Expression second = new EqualOperator(call, exp2);
		
		Expression[] exparr = new Expression[1];
		exparr[0] = second;
		
		TestGuiDropInstr testGui = new TestGuiDropInstr();
		Instruction ifAllInstruction = new IfAllInstruction(testGui, 12, exparr);
		
		
		ifAllInstruction.execute(ship);
		
		assertTrue(ship.getPC() == 1);
	}
	
	/**
	 * getterTest ifALL
	 */
	@Test
	public void ifAllInstructionGetterTest(){
		Expression[] expArray = new Expression[2];
		expArray[0] = new Literal(42);
		expArray[1] = new Literal(1);
		TestGuiDropInstr testGui = new TestGuiDropInstr();
		IfAllInstruction ifAllInstruction = new IfAllInstruction(testGui, 12, expArray);
		
		assertTrue(ifAllInstruction.getConditions().equals(expArray));
	}
	
	//IF ANY INSTRUCTION
	/**
	 * tests if the instruction evaluates to true correctly and sets the pc correctly
	 */
	@Test
	public void ifAnyInstructionTrueTest(){
		//simulates the registerArray
		
		ship.setRegister(Register.SENSE_MARKER0, 1);
		
		//calls Register SENSE_MARKER0
		RegisterCall call = new RegisterCall(3);
		
		//build a ExpressionArray for testing puropses
		//and added two equalexpressions testing equality with 0 and 1
		Primary exp = new Literal(0);
		Primary exp2 = new Literal(1);
		
		Expression first = new EqualOperator(call,exp);
		Expression second = new EqualOperator(call, exp2);
		
		Expression[] exparr = new Expression[2];
		exparr[0] = first;
		exparr[1] = second;
		
		TestGuiDropInstr testGui = new TestGuiDropInstr();
		Instruction ifAnyInstruction = new IfAnyInstruction(testGui, 12, exparr);
		
		
		ifAnyInstruction.execute(ship);
		
		assertTrue(ship.getPC() == 1);
	}
	/**
	 * tests if the instruction evaluates to false correctly and sets the pc correctly
	 */
	@Test
	public void ifAnyInstructionFalseTest(){
		//simulates the registerArray
		
		ship.setRegister(Register.SENSE_MARKER0, 1);
		ship.setRegister(Register.SENSE_MARKER1, 0);
		
		//calls Register SENSE_MARKER0
		RegisterCall call = new RegisterCall(3);
		RegisterCall call2 = new RegisterCall(4);
		
		//build a ExpressionArray for testing puropses
		//and added two equalexpressions testing equality with 0 and 1
		Primary exp = new Literal(0);
		Primary exp2 = new Literal(1);
		
		Expression first = new EqualOperator(call,exp);
		Expression second = new EqualOperator(call2, exp2);
		
		Expression[] exparr = new Expression[2];
		exparr[0] = first;
		exparr[1] = second;
		
		TestGuiDropInstr testGui = new TestGuiDropInstr();
		Instruction ifAnyInstruction = new IfAnyInstruction(testGui, 12, exparr);
		
		
		ifAnyInstruction.execute(ship);
		
		assertTrue(ship.getPC() == 12);
	}
	
	/**
	 * getterTests
	 */
	
	@Test
	public void ifAnyInstructionGetterTest(){
		Expression[] expArray = new Expression[2];
		expArray[0] = new Literal(42);
		expArray[1] = new Literal(1);
		TestGuiDropInstr testGui = new TestGuiDropInstr();
		IfAnyInstruction ifAnyInstruction = new IfAnyInstruction(testGui, 12, expArray);
		
		assertTrue(ifAnyInstruction.getConditions().equals(expArray));
	}
	
	//IF Instruction
	/**
	 * checks that pc is set correctly if condition is false
	 */
	@Test
	public void ifInstructionTest(){
		
				ship.setRegister(Register.SENSE_MARKER0, 1);
				
				//calls Register SENSE_MARKER0
				RegisterCall call = new RegisterCall(3);
				
				//build a ExpressionArray for testing puropses
				//and added two equalexpressions testing equality with 0 and 1
				Primary exp = new Literal(0);
				
				Expression first = new EqualOperator(call,exp);
				
				TestGuiDropInstr testGui = new TestGuiDropInstr();
				Instruction ifInstruction = new IfInstruction(testGui, 12, first);
				
				
				ifInstruction.execute(ship);
				
				assertTrue(ship.getPC() == 12);
	}
	
	/**
	 * checks that pc is set correctly if condition is false
	 */
	
	@Test
	public void ifInstructionNegTest(){
		ship.setRegister(Register.SENSE_MARKER0, 1);
		
		//calls Register SENSE_MARKER0
		RegisterCall call = new RegisterCall(3);
		
		//build a ExpressionArray for testing puropses
		//and added two equalexpressions testing equality with 0 and 1
		Primary exp = new Literal(1);
		
		Expression first = new EqualOperator(call,exp);
		
		TestGuiDropInstr testGui = new TestGuiDropInstr();
		Instruction ifInstruction = new IfInstruction(testGui, 12, first);
		
		
		ifInstruction.execute(ship);
		
		assertTrue(ship.getPC() == 1);
	}
	/**
	 * getterTest for Ifinstruction
	 */
	@Test
	public void ifInstructionGetterTest(){
		TestGuiDropInstr testGui = new TestGuiDropInstr();
		Expression test = new Literal(0);
		IfInstruction ifInstruction = new IfInstruction(testGui, 12, test);
		
		assertTrue(ifInstruction.getCondition().equals(test));
	}
	//REPAIRINSTRUCTION TEST
	@Test
	public void RepairFailTest(){
		TestGuiDropInstr testGui = new TestGuiDropInstr();
		RepairInstruction repInstr = new RepairInstruction(testGui, 12);
		
		ship.setCondition(1);
		repInstr.execute(ship);
		
		assertTrue(repInstr.getElsePC() == 12);
		assertTrue(ship.getCondition() == 1);
	}
	
	/**
	 * a repairtest, where the ships is on a BaseTile of its faction, 
	 * but the faction has not enough score to repair
	 */
	@Test
	public void RepairNotValidOnBaseTest(){
		//build a testmap with only bases
		Worldmap6T worldTest = new Worldmap6T(2,2,null,null);
		
		Position position1 = new Position(0,0);
		Position position2 = new Position(1,0);
		Position position3 = new Position(0,1);
		Position position4 = new Position(1,1);
		
		Tile baseTile1 = worldMap.createBaseTile(position1,faction);
		Tile baseTile2 = worldMap.createBaseTile(position2,faction);
		Tile baseTile3 = worldMap.createBaseTile(position3,faction);
		Tile baseTile4 = worldMap.createBaseTile(position3,faction);
		
		
		TestGuiDropInstr testGui = new TestGuiDropInstr();
		RepairInstruction repInstr = new RepairInstruction(testGui, 12);
		
		ship.setCondition(1);
		repInstr.execute(ship);
		
		assertTrue(ship.getCondition() == 1);
	}
	
	/**
	 * a repairtest, where the ships is on a BaseTile of its faction, 
	 * and the faction has enough
	 */
	@Test
	public void RepairValidTest(){
		//build a testmap with only bases
		Worldmap6T worldTest = new Worldmap6T(2,2,null,null);
		
		faction.setScore(2);
		
		Position position1 = new Position(0,0);
		Position position2 = new Position(1,0);
		Position position3 = new Position(0,1);
		Position position4 = new Position(1,1);
		
		Tile baseTile1 = worldMap.createBaseTile(position1,faction);
		Tile baseTile2 = worldMap.createBaseTile(position2,faction);
		Tile baseTile3 = worldMap.createBaseTile(position3,faction);
		Tile baseTile4 = worldMap.createBaseTile(position3,faction);
		
		
		TestGuiDropInstr testGui = new TestGuiDropInstr();
		RepairInstruction repInstr = new RepairInstruction(testGui, 12);
		
		ship.setCondition(1);
		repInstr.execute(ship);
		
		assertTrue(ship.getCondition() == 3);
		assertTrue(faction.getScore() == 0);
	}
	//PickUpInstruction Tests
	
	/**
	 * 
	 */
	@Test
	public void PickUpInstruction(){
		
	}
}
