/**
 * The Testing class for Instructions
 * 
 * @version 1.1
 * @author christopher
 * @author Jens
 */

package de.unisaarland.cs.st.pirates.group1.tests.sim.logic;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Buoy;
import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Faction;
import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Position;
import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Sea;
import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Ship;
import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Tile;
import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Treasure;
import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Worldmap;
import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Worldmap6T;
import de.unisaarland.cs.st.pirates.group1.sim.logger.ExtendedLogWriter;
import de.unisaarland.cs.st.pirates.group1.sim.logic.instruction.Instruction;
import de.unisaarland.cs.st.pirates.group1.sim.logic.instruction.elseInstructions.ElseInstruction;
import de.unisaarland.cs.st.pirates.group1.sim.logic.instruction.normalInstructions.DropInstruction;
import de.unisaarland.cs.st.pirates.group1.sim.logic.instruction.normalInstructions.GotoInstruction;
import de.unisaarland.cs.st.pirates.group1.sim.logic.instruction.normalInstructions.MarkInstruction;
import de.unisaarland.cs.st.pirates.group1.tests.testUtil.TestGui;
import de.unisaarland.cs.st.pirates.group1.tests.testUtil.TestGuiDropInstr;
import de.unisaarland.cs.st.pirates.group1.tests.testUtil.TestGuiNotify;

public class InstructionTest {
	
	private static Worldmap worldMap;
	private static Faction faction;
	private static Ship ship;
	private static Tile baseTile;
	private static Tile waterTile1;
	private static Tile islandTile;
	private static Position position1;
	private static int x;
	
	@BeforeClass
	public static void init(){
		x = 0;
		
		//A worldMap with exactly one seatile
		worldMap = new Worldmap6T(2,2,null,null);
		
		//A TestFaction, Position and Tile
		faction = new Faction("a");
		Position position1 = new Position(0,0);
		Position position2 = new Position(1,0);
		Position position3 = new Position(0,1);
		Position position4 = new Position(1,1);
		
		waterTile1 = new Sea(worldMap,position1);
		//waterTile = new Sea(worldMap, position2);
		//waterTile = new Sea(worldMap, position3);
		//waterTile = new Sea(worldMap, position4);
		
		
		//A Test ship of the TestFaction with ID 1
		ship = new Ship(faction,1,waterTile1);
		
	}
/*
 * Christopher Tests START
 */
	
	//GotoInstruction Tests
	
	/**
	 * goTo address getter check
	 */
	@Test
	public void goToAdressGetterTest(){
		GotoInstruction goToInstruction = new GotoInstruction(null,-11);
		
		goToInstruction.execute(ship);
		assertTrue(goToInstruction.getAddress() == -11);
	}
	
	/**
	 * testing if the pc after the goto is increased of the right amount
	 * and also checks if a null logger is handled correctly
	 */
	@Test
	public void goToCorrectJumpTest(){
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
	public void goToCorrectZeroJumpTest(){
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
	public void goToLoggerReceives(){
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
	public void shipDropNotifyTest(){
		TestGuiDropInstr testGui = new TestGuiDropInstr();
		Instruction dropInstruction = new DropInstruction(testGui);
		
		ship.setLoad(4);
		//checks if create and notify is called
		dropInstruction.execute(ship);
		assertTrue(testGui.value == -1);
		assertTrue(testGui.val == 42);
	}
	
	/**
	 * checks if right amount of treasure value is on tile after drop
	 */
	
	@Test
	public void shipDropsLoadTest(){
		TestGuiDropInstr testGui = new TestGuiDropInstr();
		Instruction dropInstruction = new DropInstruction(testGui);
		
		int val;
		try{ 
			val = ship.getMyTile().getTreasure().getValue();
		} catch (NullPointerException e) {
			val = 0;
		}
		ship.setLoad(4);
		//checks if there is a treasure on the watertile after dropping
		
		dropInstruction.execute(ship);
		assertTrue(val+4 == ship.getMyTile().getTreasure().getValue());
	}
	
	/**
	 * checks if PC is increased after Drop
	 */
	@Test
	public void dropInstructionIncreasePC(){
		TestGuiDropInstr testGui = new TestGuiDropInstr();
		Instruction dropInstruction = new DropInstruction(testGui);
		
		int pc = ship.getPC();
		
		dropInstruction.execute(ship);
		assertTrue(pc == ship.getPC()+1);
	}
	
	// Markinstruction Tests
	
	/**
	 * checks if error raised if buoy id is >5
	 */
	@Test
	public void illegalArgMarkInstructionTest(){
		TestGuiDropInstr testGui = new TestGuiDropInstr();
		try{
		Instruction markInstruction = new MarkInstruction(testGui,6);
		fail("Nothing thrown");
		}
		catch(IllegalArgumentException e){
			
		}
	}
	/**
	 * checks if error raised if buoy id is <0
	 */
	
	@Test
	public void illegalArgNegMarkInstructionTest(){
		TestGuiDropInstr testGui = new TestGuiDropInstr();
		try{
		Instruction markInstruction = new MarkInstruction(testGui,-1);
		fail("Nothing thrown");
		}
		catch(IllegalArgumentException e){
			
		}
	}
	
	/**
	 * checks if ship marks the tile correctly
	 */
	@Test
	public void correctMarkInstructionTest(){
		TestGuiDropInstr testGui = new TestGuiDropInstr();
		Instruction markInstruction = new MarkInstruction(testGui,0);
		
		markInstruction.execute(ship);
		List<Buoy> buoyList = ship.getMyTile().getBuoyMap().get(ship.getFaction());
		
		for(Buoy b :buoyList){
			if(b.getId() == 0){
				x += 1;
			}else{
				continue;
			}
		}
		assertTrue(x == 1);
	}
	
	/**
	 * checks if a tile is marked only once, if its marked already
	 */
	
	@Test
	public void CorrectMarkedTwiceTest(){
		
		//adds a buoy to a list in the Hashmap
		Tile water = worldMap.getTile(position1);
		Buoy buoy = new Buoy(0, null, 0, water);
		List<Buoy> xs = new ArrayList();
		xs.add(buoy);
		water.getBuoyMap().put(faction, xs);
		
		
		TestGuiDropInstr testGui = new TestGuiDropInstr();
		Instruction markInstruction = new MarkInstruction(testGui,0);
		
		markInstruction.execute(ship);
		List<Buoy> buoyList = ship.getMyTile().getBuoyMap().get(ship.getFaction());
		
		//checks if there is only one buoy with ID 0 in the map
		for(Buoy b :buoyList){
			if(b.getId() == 0){
				x += 1;
			}else{
				continue;
			}
		}
		assertTrue(x == 1);
	}
	/**
	 * getter Test MarkInstruction
	 */
	
	@Test
	public void markInstructionGetterTest(){
		TestGuiDropInstr testGui = new TestGuiDropInstr();
		MarkInstruction markInstruction = new MarkInstruction(testGui,4);
		
		assertTrue(markInstruction.getType() == 4);
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
