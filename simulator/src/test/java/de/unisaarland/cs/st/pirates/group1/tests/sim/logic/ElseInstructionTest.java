/**
 * The Testing class for ElseInstructions
 * 
 * @version 1.1
 * @author christopher
 */
package de.unisaarland.cs.st.pirates.group1.tests.sim.logic;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.util.Random;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import de.unisaarland.cs.st.pirates.group1.logger.AddCell;
import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.EntityFactory;
import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Faction;
import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Position;
import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Ship;
import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Tile;
import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Treasure;
import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Worldmap;
import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Worldmap6T;
import de.unisaarland.cs.st.pirates.logger.LogWriter.Cell;
import de.unisaarland.cs.st.pirates.group1.sim.logic.expression.EqualOperator;
import de.unisaarland.cs.st.pirates.group1.sim.logic.expression.Expression;
import de.unisaarland.cs.st.pirates.group1.sim.logic.expression.GreaterOperator;
import de.unisaarland.cs.st.pirates.group1.sim.logic.expression.LessOperator;
import de.unisaarland.cs.st.pirates.group1.sim.logic.expression.Literal;
import de.unisaarland.cs.st.pirates.group1.sim.logic.expression.Primary;
import de.unisaarland.cs.st.pirates.group1.sim.logic.expression.RegisterCall;
import de.unisaarland.cs.st.pirates.group1.sim.logic.instruction.Instruction;
import de.unisaarland.cs.st.pirates.group1.sim.logic.instruction.elseInstructions.ElseInstruction;
import de.unisaarland.cs.st.pirates.group1.sim.logic.instruction.elseInstructions.FlipZeroInstruction;
import de.unisaarland.cs.st.pirates.group1.sim.logic.instruction.elseInstructions.IfAllInstruction;
import de.unisaarland.cs.st.pirates.group1.sim.logic.instruction.elseInstructions.IfAnyInstruction;
import de.unisaarland.cs.st.pirates.group1.sim.logic.instruction.elseInstructions.IfInstruction;
import de.unisaarland.cs.st.pirates.group1.sim.logic.instruction.elseInstructions.PickupInstruction;
import de.unisaarland.cs.st.pirates.group1.sim.logic.instruction.elseInstructions.RefreshInstruction;
import de.unisaarland.cs.st.pirates.group1.sim.logic.instruction.elseInstructions.RepairInstruction;
import de.unisaarland.cs.st.pirates.group1.sim.logic.instruction.normalInstructions.DropInstruction;
import de.unisaarland.cs.st.pirates.group1.sim.parser.TacticsParser;
import de.unisaarland.cs.st.pirates.group1.sim.util.Direction;
import de.unisaarland.cs.st.pirates.group1.sim.util.Register;
import de.unisaarland.cs.st.pirates.group1.tests.testUtil.ExpectLogger;
import de.unisaarland.cs.st.pirates.group1.tests.testUtil.TestGuiDropInstr;

public class ElseInstructionTest {
	private Worldmap worldMap;
	private Faction faction;
	private Ship ship;
	private Tile baseTile;
	private Tile waterTile1;
	private Tile waterTile2;
	private Tile islandTile2;
	private Tile islandTile1;
	private ExpectLogger logger;
	private EntityFactory factory;
	private int x;
	private TacticsParser tacticsParser;
	
	@Before
	public void init(){
		x = 0;
		
		//A worldMap with exactly one seatile
		logger = new ExpectLogger();
		factory = new EntityFactory();
		worldMap = new Worldmap6T(2,2, logger, factory);
		tacticsParser = new TacticsParser(logger);
		
		//A TestFaction, Position and Tile
		faction = new Faction("a",0);
		Position position1 = new Position(0,0);
		Position position2 = new Position(1,0);
		Position position3 = new Position(0,1);
		Position position4 = new Position(1,1);
		
		waterTile1 = worldMap.createSeaTile(position1);
		waterTile2 = worldMap.createSeaTile(position2);
		islandTile1 = worldMap.createIslandTile(position3, true);
		islandTile2 = worldMap.createIslandTile(position4, false);
		logger.expect(new AddCell(Cell.WATER, null, 0, 0));
		logger.expect(new AddCell(Cell.WATER, null, 1, 0));
		logger.expect(new AddCell(Cell.SUPPLY, null, 0, 1));
		logger.expect(new AddCell(Cell.ISLAND, null, 1, 1));
		
		
		//A Test ship of the TestFaction with ID 1, if ship is attaching itself
		ship = new Ship(faction,1,waterTile1);
		
		//waterTile1.attach(ship);
		
	}
	
	/**
	 * tests ifany
	 * @throws UnsupportedEncodingException 
	 */
	@Test
	public void pickupMaxTreasure() throws UnsupportedEncodingException{
		worldMap.createTreasure(9,islandTile1);
		ship.setRegister(Register.SHIP_DIRECTION,5);
		ship.setCondition(3);
		ship.setMorale(4);
		ship.setLoad(4);
		String myString = ""
				+ "pickup 1 else 13";
		
		
		InputStream is = new ByteArrayInputStream( myString.getBytes("utf-8") );
		Instruction[] arr = tacticsParser.parseTactics(is, new Random(2));
		arr[0].execute(ship);
		assertTrue(ship.getPC()==13);
	}
	
	/**
	 * tests refresh
	 * @throws UnsupportedEncodingException 
	 */
	@Test
	public void refreshMoralMax() throws UnsupportedEncodingException{
		ship.setRegister(Register.SHIP_DIRECTION,5);
		ship.setCondition(3);
		ship.setMorale(4);
		ship.setLoad(4);
		String myString = ""
				+ "refresh 2 else 13\n";
		
		
		InputStream is = new ByteArrayInputStream( myString.getBytes("utf-8") );
		Instruction[] arr = tacticsParser.parseTactics(is, new Random(2));
		arr[0].execute(ship);
		assertTrue(ship.getPC()==1);
	}
	
	//FlipZeroInstruction Test
	/**
	 * tests if the randomint is generated correctly
	 */
	@Test
	public void flipZeroInstructionRandTest(){
		Random rand = new Random(0);
		
		TestGuiDropInstr testGui = new TestGuiDropInstr();
		FlipZeroInstruction flipZeroInstr = new FlipZeroInstruction(testGui,11,rand,2);
		
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
		FlipZeroInstruction flipZeroInstr = new FlipZeroInstruction(testGui,11,rand,2);
		
		flipZeroInstr.execute(ship);
		
		//TODO CHECK THIS!!
		//Jump to else, if randomnumber is not 0
		//see page 2
		if (rand.nextInt(2) == 0){
			assertTrue(ship.getPC() == 1);
		}else{
			assertTrue(ship.getPC() == 11);
		}
	}
	
	/**
	 * tests ifany
	 * @throws UnsupportedEncodingException 
	 */
	@Test
	public void pickupInvalid() throws UnsupportedEncodingException{
		ship.setRegister(Register.SHIP_DIRECTION,5);
		ship.setCondition(3);
		ship.setMorale(4);
		ship.setLoad(4);
		String myString = ""
				+ "sense 2\n"
				+ "ifany sense_supply else 13";
		
		
		InputStream is = new ByteArrayInputStream( myString.getBytes("utf-8") );
		Instruction[] arr = tacticsParser.parseTactics(is, new Random(2));
		arr[0].execute(ship);
		arr[1].execute(ship);
		assertTrue(ship.getPC()==2);
	}
	
	/**
	 * tests ifany an string is parsed and evaluated correctly
	 * @throws UnsupportedEncodingException 
	 */
	@Test
	public void ifTestTwo() throws UnsupportedEncodingException{
		ship.setRegister(Register.SHIP_DIRECTION,5);
		ship.setCondition(3);
		ship.setMorale(4);
		ship.setLoad(4);
		String myString = "ifany ship_direction==4 ship_load<3 ship_moral<3 ship_condition<2 sense_supply sense_treasure else 13";
		
		
		InputStream is = new ByteArrayInputStream( myString.getBytes("utf-8") );
		Instruction[] arr = tacticsParser.parseTactics(is, new Random(2));
		arr[0].execute(ship);
		assertTrue(ship.getPC()==13);
	}
	
	
	/**
	 * tests ifany an string is parsed and evaluated correctly
	 * @throws UnsupportedEncodingException 
	 */
	@Test
	public void ifTestOne() throws UnsupportedEncodingException{
		ship.setRegister(Register.SHIP_DIRECTION,5);
		ship.setCondition(3);
		ship.setMorale(4);
		ship.setLoad(4);
		String myString = "ifany ship_direction==4 ship_load==3 ship_moral==3 ship_condition==2 sense_supply sense_treasure else 13";
		
		
		InputStream is = new ByteArrayInputStream( myString.getBytes("utf-8") );
		Instruction[] arr = tacticsParser.parseTactics(is, new Random(2));
		arr[0].execute(ship);
		assertTrue(ship.getPC()==13);
	}
	
	//IfALlInstruction Test should evaluate to false and set the new PC
	/**
	 * tests if an exp array is evaluated to false correctly and the PC is set correctly
	 */
	@Test
	public void ifAllInstructionSecondTest(){
		//simulates the registerArray
		
		ship.setRegister(Register.SHIP_DIRECTION,5);
		ship.setCondition(3);
		ship.setMorale(4);
		ship.setLoad(4);
		
		//calls Registers ship_direction ship_load ship_moral ship_condition
		RegisterCall call14 = new RegisterCall(14);
		RegisterCall call15 = new RegisterCall(15);
		RegisterCall call16 = new RegisterCall(16);
		RegisterCall call17 = new RegisterCall(17);
		
		//build a ExpressionArray for testing puropses
		//and added two equalexpressions testing equality with 0 and 1
		Primary prim5 = new Literal(5);
		Primary prim4 = new Literal(4);
		Primary prim3 = new Literal(3);
		Expression first = new EqualOperator(call14,prim5);
		Expression second = new LessOperator(call15,prim5);
		Expression third = new GreaterOperator(call16,prim3);
		Expression fourth = new LessOperator(call17,prim4);
		
		Expression[] exparr = new Expression[4];
		exparr[0] = first;
		exparr[1] = second;
		exparr[2] = third;
		exparr[3] = fourth;
		
		TestGuiDropInstr testGui = new TestGuiDropInstr();
		Instruction ifAllInstruction = new IfAllInstruction(testGui, 12, exparr);
		
		
		ifAllInstruction.execute(ship);
		
		assertTrue(ship.getPC() == 1);
	}
	
	
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
		logger = new ExpectLogger();
		factory = new EntityFactory();
		Worldmap6T worldTest = new Worldmap6T(2,2,logger,factory);
		
		Position position1 = new Position(0,0);
		Position position2 = new Position(1,0);
		Position position3 = new Position(0,1);
		Position position4 = new Position(1,1);
		
		Tile baseTile1 = worldTest.createBaseTile(position1,faction);
		Tile baseTile2 = worldTest.createBaseTile(position2,faction);
		Tile baseTile3 = worldTest.createBaseTile(position3,faction);
		Tile baseTile4 = worldTest.createBaseTile(position3,faction);
		
		Ship testShip = new Ship(faction, 3, baseTile1);
		
		TestGuiDropInstr testGui = new TestGuiDropInstr();
		RepairInstruction repInstr = new RepairInstruction(testGui, 12);
		
		testShip.setCondition(1);
		repInstr.execute(testShip);
		
		assertTrue(testShip.getCondition() == 1);
	}
	
	/**
	 * a repairtest, where the ships is on a BaseTile of its faction, 
	 * and the faction has enough
	 */
	@Test
	public void RepairValidTest(){
		//build a testmap with only bases
		logger = new ExpectLogger();
		factory = new EntityFactory();
		Worldmap6T worldTest = new Worldmap6T(2,2,logger,factory);
		
		faction.setScore(2);
		
		Position position1 = new Position(0,0);
		Position position2 = new Position(1,0);
		Position position3 = new Position(0,1);
		Position position4 = new Position(1,1);
		
		Tile baseTile1 = worldTest.createBaseTile(position1,faction);
		Tile baseTile2 = worldTest.createBaseTile(position2,faction);
		Tile baseTile3 = worldTest.createBaseTile(position3,faction);
		Tile baseTile4 = worldTest.createBaseTile(position3,faction);
		Ship testShip = new Ship(faction,1,baseTile1);
		
		
		TestGuiDropInstr testGui = new TestGuiDropInstr();
		RepairInstruction repInstr = new RepairInstruction(testGui, 12);
		
		testShip.setCondition(1);
		repInstr.execute(testShip);
		
		assertTrue(testShip.getCondition() == 3);
		assertTrue(faction.getScore() == 0);
	}
	//PickUpInstruction Tests
	
	/**
	 * tests if the ship can pickup
	 */
	@Test
	public void PickUpValidInstructionTest(){
		Direction d = Direction.D0;
		TestGuiDropInstr testGui = new TestGuiDropInstr();
		PickupInstruction pickupInstr = new PickupInstruction(testGui, 12, d);
		
		//attach treasure on watertile2
		Treasure tr = new Treasure(2,1,waterTile2);
		pickupInstr.execute(ship);
		
		assertTrue(ship.getLoad() == 2);
	}
	
	/**
	 * tests if the ship can pickup wraparound
	 */
	@Test
	public void PickUpWrapAroundInstructionTest(){
		Direction d = Direction.D3;
		TestGuiDropInstr testGui = new TestGuiDropInstr();
		PickupInstruction pickupInstr = new PickupInstruction(testGui, 12, d);
		
		//attach treasure on watertile2
		Treasure tr = new Treasure(2,1,waterTile2);
		pickupInstr.execute(ship);
		
		assertTrue(ship.getLoad() == 2);
		assertTrue(ship.getPC() == 1);
	}
	
	/**
	 * tests if the PC is set correctly after fail
	 */
	@Test
	public void PickUpFailInstructionTest(){
		Direction d = Direction.D3;
		TestGuiDropInstr testGui = new TestGuiDropInstr();
		PickupInstruction pickupInstr = new PickupInstruction(testGui, 12, d);
		
		pickupInstr.execute(ship);
		
		assertTrue(ship.getPC() == 12);
		assertTrue(ship.getLoad() == 0);
	}
	/**
	 * getter Tests
	 */
	@Test
	public void PickUpGetterTest(){
		Direction d = Direction.D3;
		TestGuiDropInstr testGui = new TestGuiDropInstr();
		PickupInstruction pickupInstr = new PickupInstruction(testGui, 12, d);
		
		pickupInstr.execute(ship);
		
		assertTrue(pickupInstr.getDir().equals(d));
	}
	//Refresh Instruction
	
	/**
	 * test if moral is refreshed correctly
	 */
	@Test
	public void RefreshValidInstructionTest(){
		Direction d = Direction.D1;
		TestGuiDropInstr testGui = new TestGuiDropInstr();
		RefreshInstruction refreshInstr = new RefreshInstruction(testGui, 2, d);
		
		refreshInstr.execute(ship);
		
		assertTrue(ship.getMaxmorale() == ship.getMorale());
	}
	/**
	 * test if refreshment fails
	 */
	@Test
	public void RefreshInstructionFailTest(){
		Direction d = Direction.D0;
		TestGuiDropInstr testGui = new TestGuiDropInstr();
		RefreshInstruction refreshInstr = new RefreshInstruction(testGui, 2, d);
		
		int morale = ship.getMorale();
		refreshInstr.execute(ship);
		
		assertTrue(morale == ship.getMorale());
		assertTrue(ship.getPC() == 2);
	}
}