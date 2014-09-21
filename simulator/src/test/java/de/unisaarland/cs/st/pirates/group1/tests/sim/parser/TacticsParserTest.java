/**
 * the class for testing the tacticsParser
 * Using the example tactic Programm on page 2
 * @author christopher
 */
package de.unisaarland.cs.st.pirates.group1.tests.sim.parser;

import static org.junit.Assert.*;

import java.io.InputStream;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.EntityFactory;
import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Faction;
import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Position;
import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Ship;
import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Tile;
import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Worldmap;
import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Worldmap6T;
import de.unisaarland.cs.st.pirates.group1.sim.logic.expression.Expression;
import de.unisaarland.cs.st.pirates.group1.sim.logic.expression.LessOperator;
import de.unisaarland.cs.st.pirates.group1.sim.logic.instruction.Instruction;
import de.unisaarland.cs.st.pirates.group1.sim.logic.instruction.elseInstructions.FlipZeroInstruction;
import de.unisaarland.cs.st.pirates.group1.sim.logic.instruction.elseInstructions.IfAllInstruction;
import de.unisaarland.cs.st.pirates.group1.sim.logic.instruction.elseInstructions.IfInstruction;
import de.unisaarland.cs.st.pirates.group1.sim.logic.instruction.elseInstructions.MoveInstruction;
import de.unisaarland.cs.st.pirates.group1.sim.logic.instruction.elseInstructions.PickupInstruction;
import de.unisaarland.cs.st.pirates.group1.sim.logic.instruction.normalInstructions.DropInstruction;
import de.unisaarland.cs.st.pirates.group1.sim.logic.instruction.normalInstructions.GotoInstruction;
import de.unisaarland.cs.st.pirates.group1.sim.logic.instruction.normalInstructions.SenseInstruction;
import de.unisaarland.cs.st.pirates.group1.sim.logic.instruction.normalInstructions.TurnInstruction;
import de.unisaarland.cs.st.pirates.group1.sim.parser.TacticsParser;
import de.unisaarland.cs.st.pirates.group1.tests.testLogger.ExpectLogger;
import de.unisaarland.cs.st.pirates.group1.tests.testUtil.StreamHelper;

public class TacticsParserTest {
	private TacticsParser tacticsParser;
	private InputStream stream;
	private Random random;
	
	private Worldmap worldMap;
	private Faction faction;
	private Ship ship;
	private Tile baseTile;
	private Tile waterTile1;
	private Tile waterTile2;
	private Tile islandTile2;
	private Tile islandTile1;
	private Position position1;
	private int x;
	private ExpectLogger expectLogger;
	private EntityFactory entityFactory;
	
	@Before
	public void init(){
		String string =""
				+ "sense 0"+"\n"
				+ "ifall sense_treasure ship_load<4 else 4"+"\n"
				+ "pickup 0 else 6"+"\n"
				+ "goto 14"+"\n"
				+ "if ship_load>3 else 6"+"\n"
				+ "goto 14"+"\n"
				+ "move else 8"+"\n"
				+ "goto 0"+"\n"
				+ "flipzero 3 else 11"+"\n"
				+ "turn left"+"\n"
				+ "goto 0"+"\n"
				+ "sense 0"+"\n"
				+ "if sense_celltype==home else 19"+"\n"
				+ "move else 21"+"\n"
				+ "drop"+"\n"
				+ "goto 0"+"\n"
				+ "move else 21"+"\n"
				+ "goto 14"+"\n"
				+ "flipzero 3 else 24"+"\n"
				+ "turn left"+"\n"
				+ "goto 14"+"\n"
				+ "flipzero 2 else 26"+"\n"
				+ "turn right"+"\n"
				+ "goto 14"+"\n"
				+ "repair else 0" + "\n"
				+ "ifany sense_treasure ship_load==0 else 4"+"\n"
				+ "refresh 5 else 0";
		
		random = new Random(0);
		expectLogger = new ExpectLogger();
		tacticsParser = new TacticsParser(expectLogger);
		stream = StreamHelper.asIS(string);
		
		
		entityFactory = new EntityFactory();
		
		//A worldMap with exactly one seatile
		worldMap = new Worldmap6T(2,2,expectLogger,entityFactory);
		
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
	}
	
	
	/**
	 * checks if the array has the correct length
	 */
	@Test
	public void bigInstructionSizeParseTest(){
		Instruction[] instrArray = tacticsParser.parseTactics(stream, random);
		assertTrue(instrArray.length == 24);
	}
	

	/**
	 * checks if all of the instructions are parsed correctly
	 */
	@Test
	public void InstructionCheckParseTest(){
		Instruction[] instrArray = tacticsParser.parseTactics(stream, random);
		
		assertTrue("The 0th Instruction should be a senseInstruction", 
				instrArray[0] instanceof SenseInstruction);
		
		assertTrue("The 1st Instruction should be an IfAllInstruction",
				instrArray[1] instanceof IfAllInstruction);
		
		assertTrue("The 2nd Instruction should be a PickUpInstruction",
				instrArray[2] instanceof PickupInstruction);
		
		assertTrue("The 3rd Instruction should be a GoToInstruction",
				instrArray[3] instanceof GotoInstruction);
		//
		
		assertTrue("The 4th Instruction should be an IfInstruction",
				instrArray[4] instanceof IfInstruction);
		
		assertTrue("The 5th Instruction should be a GotoInstruction",
				instrArray[5] instanceof GotoInstruction);
		
		assertTrue("The 6th Instruction should be a MoveInstruction",
				instrArray[6] instanceof MoveInstruction);
		
		assertTrue("The 7th Instruction should be a GotoInstruction",
				instrArray[7] instanceof GotoInstruction);
		
		//
		assertTrue("The 8th Instruction should be a FlipzeroInstruction",
				instrArray[8] instanceof FlipZeroInstruction);
		
		assertTrue("The 9th Instruction should be a TurnInstruction",
				instrArray[9] instanceof TurnInstruction);
		
		assertTrue("The 10th Instruction should be a GotoInstruction",
				instrArray[10] instanceof GotoInstruction);
		
		//
		assertTrue("The 11th Instruction should be a FlipZeroInstruction",
				instrArray[11] instanceof FlipZeroInstruction);
		
		assertTrue("The 12th Instruction should be a TurnInstruction",
				instrArray[12] instanceof TurnInstruction);
		
		assertTrue("The 13th Instruction should be a GotoInstruction",
				instrArray[13] instanceof GotoInstruction);
		
		//
		assertTrue("The 14th Instruction should be a SenseInstruction",
				instrArray[14] instanceof SenseInstruction);
		
		assertTrue("The 15th Instruction should be an IfInstruction",
				instrArray[15] instanceof IfInstruction);
		
		assertTrue("The 16th Instruction should be a MoveInstruction",
				instrArray[16] instanceof MoveInstruction);
		
		//
		assertTrue("The 17th Instruction should be a DropInstruction",
				instrArray[17] instanceof DropInstruction);
		
		assertTrue("The 18th Instruction should be a GotoInstruction",
				instrArray[18] instanceof GotoInstruction);
		
		assertTrue("The 19th Instruction should be a MoveInstruction",
				instrArray[19] instanceof MoveInstruction);
		
		//
		assertTrue("The 20st Instruction should be a GotoInstruction",
				instrArray[20] instanceof GotoInstruction);
		
		assertTrue("The 21st Instruction should be a FlipZeroInstruction",
				instrArray[21] instanceof FlipZeroInstruction);
		
		assertTrue("The 22nd Instruction should be a TurnInstruction",
				instrArray[22] instanceof TurnInstruction);
		
		assertTrue("The 23rd Instruction should be a GotoInstruction",
				instrArray[23] instanceof GotoInstruction);
		
		assertTrue("The 24th Instruction should be a FlipZeroInstruction",
				instrArray[24] instanceof FlipZeroInstruction);
		
		assertTrue("The 25th Instruction should be a TurnInstruction",
				instrArray[25] instanceof TurnInstruction);
		
		assertTrue("The 26th Instruction should be a GotoInstruction",
				instrArray[26] instanceof GotoInstruction);
		
	}
	
	
	/**
	 * checks if the ifinstr is parsed correctly
	 * also checks if the conditions are evaluated correctly
	 * also checks if the elseaddr is set correctly
	 */
	@Test
	public void checkIfInstructionParseTest(){
		Instruction[] instrArray = tacticsParser.parseTactics(stream, random);
		//get the expressions
		Instruction instr = instrArray[1];
		
		//check if it is the correct Instruction
		assertTrue("the instruction 1 should be a IfAllInstruction",
				instr instanceof IfAllInstruction);
		
		IfAllInstruction ifAllinstr = (IfAllInstruction) instr;
		Expression[] exp = ifAllinstr.getConditions();
		
		//check if the IfAllInstruction got 2 Expression
		assertTrue("length of the array should be 2",
				exp.length == 2);
		
		//check if the expressions are correct
		assertTrue("the secound expression should be a < exp"
				,exp[1] instanceof LessOperator);
		
		
		//check if the expressions are evaluated correctly
		int ret1 = exp[0].evaluate(ship.getRegisters());
		int ret2 = exp[1].evaluate(ship.getRegisters());
		
		assertTrue("The first exp should evaluate to false",
				ret1 == 0);
		assertTrue("The secound exp should evaluate to true",
				ret2 == 1);
		
		//checks if elseAddr is set correctly
		assertTrue("The elseAddr should be 4",
				ifAllinstr.getElsePC() == 4);
	}
	
	
	/**
	 * checks if the gotoinstr is parsed correctly
	 * also checks if the addr is set correctly
	 */
	@Test
	public void CheckGotoInstructionParseTest(){
		Instruction[] instrArray = tacticsParser.parseTactics(stream, random);
		
		Instruction instr = instrArray[26];
		
		//check if it is the correct instruction
		assertTrue("the instruction 26 should be a GotoInstruction",
				instr instanceof GotoInstruction);
		
		GotoInstruction gotoInstr = (GotoInstruction) instr;
		
		//check if the int is set correctly
		assertTrue("the jumpAddr should be 14",
				gotoInstr.getAddress() == 14);
	}
	
	
	/**
	 * checks if the moveinstr is parsed correctly
	 * and checks if the elseAddr is set correctly
	 */
	@Test
	public void MoveInstructionElseParseTest(){
		Instruction[] instrArray = tacticsParser.parseTactics(stream, random);
		
		Instruction instr = instrArray[6];
		
		//check if it is the correct instruction
		assertTrue("the instruction 6 should be a Moveinstruction",
				instr instanceof MoveInstruction);
		
		MoveInstruction moveInstr = (MoveInstruction) instr;
		
		//check if the else is set correctly
		assertTrue("the elsePC should be 8",
				moveInstr.getElsePC() == 8);
	}
	
	
	/**
	 * checks if the DropInstr is parsed correctly
	 */
	@Test
	public void DropInstructionParseTest(){
		Instruction[] instrArray = tacticsParser.parseTactics(stream, random);
		
		Instruction instr = instrArray[17];
		
		//check if it is the correct instruction
		assertTrue("the instruction 17 should be a DropInstruction",
				instr instanceof DropInstruction);
	}
	/**
	 * checks if the FlipZero is parsed correctly
	 * TODO HOW CAN WE CHECK IF THE SEED IS PARSED CORRECTLY INTO THE RANDOMOBJECT
	 */
	@Test
	public void FlipZeroInstructionParseTest(){
		Instruction[] instrArray = tacticsParser.parseTactics(stream, random);
		
		Instruction instr = instrArray[24];
		
		//check if it the correct instruction
		assertTrue("the instruction 24 should be a FlipZeroInstruction",
				instr instanceof FlipZeroInstruction);
		
		FlipZeroInstruction flipZeroInstr = (FlipZeroInstruction) instr;
		
		assertTrue("the elseAddr should be 26",
				flipZeroInstr.getElsePC() == 26);
		
		//TODO check the seed
		
	}
	
	
	/**
	 * checks if the turnInstructions are parsed correctly
	 */
	@Test
	public void TurnInstructionParseTest(){
		Instruction[] instrArray = tacticsParser.parseTactics(stream, random);
		
		Instruction instrLeft = instrArray[9];
		Instruction instrRight = instrArray[25];
		
		//check if the instructions are parsed correctly
		assertTrue("the instructions 9 and 25 should be a TurnInstruction",
				instrLeft instanceof TurnInstruction && instrRight instanceof TurnInstruction);
		
		TurnInstruction turnLeftInstr = (TurnInstruction) instrLeft;
		TurnInstruction turnRightInstr = (TurnInstruction) instrRight;
		
		//check if right/left is set correctly
		
		assertTrue("the instruction 9 should parse left",
				turnLeftInstr.isLeft());
		assertTrue("the instruction 25 should parse right",
				!turnRightInstr.isLeft());
		
	}
}
