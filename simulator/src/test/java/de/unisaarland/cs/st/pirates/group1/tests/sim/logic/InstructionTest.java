/**
 * The Testing class for Instructions
 * 
 * @version 1.1
 * @author christopher
 * @author Jens
 */

package de.unisaarland.cs.st.pirates.group1.tests.sim.logic;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Faction;
import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Position;
import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Sea;
import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Ship;
import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Tile;
import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Worldmap;
import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Worldmap6T;
import de.unisaarland.cs.st.pirates.group1.sim.logger.ExtendedLogWriter;
import de.unisaarland.cs.st.pirates.group1.sim.logic.instruction.Instruction;
import de.unisaarland.cs.st.pirates.group1.sim.logic.instruction.elseInstructions.ElseInstruction;
import de.unisaarland.cs.st.pirates.group1.sim.logic.instruction.normalInstructions.DropInstruction;
import de.unisaarland.cs.st.pirates.group1.sim.logic.instruction.normalInstructions.GotoInstruction;
import de.unisaarland.cs.st.pirates.group1.tests.sim.logger.TestGui;
import de.unisaarland.cs.st.pirates.group1.tests.sim.logger.TestGuiDropInstr;
import de.unisaarland.cs.st.pirates.group1.tests.sim.logger.TestGuiNotify;

public class InstructionTest {
	
	private static Worldmap worldMap;
	private static Faction faction;
	private static Ship ship;
	private static Tile baseTile;
	private static Tile waterTile;
	private static Tile islandTile;
	private static Position position;
	
	@BeforeClass
	public static void init(){
		
		//A worldMap with exactly one seatile
		worldMap = new Worldmap6T(0,0,null,null);
		
		//A TestFaction, Position and Tile
		faction = new Faction("a");
		position = new Position(0,0);
		waterTile = new Sea(worldMap,position);
		
		
		//A Test ship of the TestFaction with ID 1
		ship = new Ship(faction,1,waterTile);
		
	}
/*
 * Christopher Tests START
 */
	
	//GotoInstruction Tests
	
	/**
	 * testing if the pc after the goto is increased of the right amount
	 * and also checks if a null logger is handled correctly
	 */
	@Test
	public static void GoToCorrectJumpTest(){
		Instruction goToInstruction = new GotoInstruction(null,1);
		
		int pc = ship.getPC();
		try{
		goToInstruction.execute(ship);}
		catch (NullPointerException e){
			fail("NullPointerException, because logger is null");		
		}
		assertTrue(ship.getPC() == pc + 1);
	}
	
	/**
	 * testing if the jump to 0 is valid
	 */
	
	@Test
	public static void GoToCorrectZeroJumpTest(){
		Instruction goToInstruction = new GotoInstruction(null,0);
		
		//int pc = ship.getPC();
		try{
		goToInstruction.execute(ship);}
		catch (NullPointerException e){
			fail("NullPointerException because logger is null");		
		}
		assertTrue( 0 == ship.getPC());
	}
	
	/**
	 * checks if the logger/gui opens the notify method after receiving this
	 */
	
	@Test
	public static void GoToLoggerReceives(){
		TestGui testGui = new TestGuiNotify();
		Instruction goToInstruction = new GotoInstruction(testGui,12);
		
		goToInstruction.execute(ship);
		assertTrue(testGui.value == -1);
	}
	
	//DropInstruction Tests
	/**
	 * checks if notify and create method in gui is called
	 */
	@Test
	public static void ShipDropsLoadTest(){
		TestGuiDropInstr testGui = new TestGuiDropInstr();
		Instruction dropInstruction = new DropInstruction(testGui);
		
		ship.setLoad(4);
		//checks if create and notify is called
		dropInstruction.execute(ship);
		assertTrue(testGui.value == -1);
		assertTrue(testGui.val == 42);
	}
	
	/**
	 * checks if PC is increased after Drop
	 */
	@Test
	public static void DropInstructionIncreasePC(){
		TestGuiDropInstr testGui = new TestGuiDropInstr();
		Instruction dropInstruction = new DropInstruction(testGui);
		
		int pc = ship.getPC();
		
		dropInstruction.execute(ship);
		assertTrue(pc == ship.getPC()+1);
	}
	
/*
 * Christopher Tests END
 */
	
/*
 * Jens Tests START
 */
	
/*
 * Jens Tests END
 */

}
