/**
 * the class for testing the tacticsParser
 * @author christopher
 */
package de.unisaarland.cs.st.pirates.group1.tests.sim.parser;

import static org.junit.Assert.*;

import java.io.InputStream;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

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
import de.unisaarland.cs.st.pirates.group1.tests.testUtil.StreamHelper;

public class TacticsParserTest {
	private TacticsParser tacticsParser;
	private InputStream stream;
	private Random random;
	
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
				+ "goto 14";
		
		random = new Random(0);
		tacticsParser = new TacticsParser();
		stream = StreamHelper.asIS(string);
	}
	//checks if the arraysize is right
	@Test
	public void bigInstructionSizeTest(){
		Instruction[] instrArray = tacticsParser.parseTactics(stream, random);
		assertTrue(instrArray.length == 24);
	}
	//checks if the right instructions are parsed
	@Test
	public void InstructionCheckTest(){
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
}
