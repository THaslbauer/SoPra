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
import de.unisaarland.cs.st.pirates.group1.sim.logic.instruction.Instruction;
import de.unisaarland.cs.st.pirates.group1.sim.logic.instruction.elseInstructions.ElseInstruction;
import de.unisaarland.cs.st.pirates.group1.sim.logic.instruction.normalInstructions.GotoInstruction;

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
	 */
	@Test
	public static void ElseCorrectJumpTest(){
		Instruction goToInstruction = new GotoInstruction(null,1);
		
		int pc = ship.getPC();
		goToInstruction.execute(ship);
		assertTrue(ship.getPC() == pc + 1);
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
