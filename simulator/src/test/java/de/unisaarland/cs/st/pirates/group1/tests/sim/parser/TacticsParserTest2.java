package de.unisaarland.cs.st.pirates.group1.tests.sim.parser;

import static org.junit.Assert.*;

import java.io.InputStream;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import de.unisaarland.cs.st.pirates.group1.sim.logic.instruction.Instruction;
import de.unisaarland.cs.st.pirates.group1.sim.logic.instruction.elseInstructions.IfAllInstruction;
import de.unisaarland.cs.st.pirates.group1.sim.logic.instruction.elseInstructions.IfAnyInstruction;
import de.unisaarland.cs.st.pirates.group1.sim.logic.instruction.elseInstructions.IfInstruction;
import de.unisaarland.cs.st.pirates.group1.sim.parser.TacticsParser;
import de.unisaarland.cs.st.pirates.group1.tests.testUtil.ExpectLogger;
import de.unisaarland.cs.st.pirates.group1.tests.testUtil.StreamHelper;

public class TacticsParserTest2 {

	private TacticsParser parser;
	private Random random;
	private String correct_string;
	private String wrong_string1;
	private String wrong_string2;
	private String wrong_string3;
	private String wrong_string4;
	private String wrong_string5;
	private String wrong_string6;
	private String wrong_string7;
	private String wrong_string8;
	private String wrong_string9;
	private String wrong_string10;
	private String wrong_string11;
	private String wrong_string12;
	private String wrong_string13;
	private String wrong_string14;
	private String wrong_string15;
	private String wrong_string16;
	private String wrong_string17;
	private String wrong_string18;
	private String wrong_string19;
	private String wrong_string20;
	private String wrong_string21;
	private String wrong_string22;
	private String wrong_string23;
	private String wrong_string24;
	
	private InputStream correct_stream;
	private InputStream wrong_stream1;
	private InputStream wrong_stream2;
	private InputStream wrong_stream3;
	private InputStream wrong_stream4;
	private InputStream wrong_stream5;
	private InputStream wrong_stream6;
	private InputStream wrong_stream7;
	private InputStream wrong_stream8;
	private InputStream wrong_stream9;
	private InputStream wrong_stream10;
	private InputStream wrong_stream11;
	private InputStream wrong_stream12;
	private InputStream wrong_stream13;
	private InputStream wrong_stream14;
	private InputStream wrong_stream15;
	private InputStream wrong_stream16;
	private InputStream wrong_stream17;
	private InputStream wrong_stream18;
	private InputStream wrong_stream19;
	private InputStream wrong_stream20;
	private InputStream wrong_stream21;
	private InputStream wrong_stream22;
	private InputStream wrong_stream23;
	private InputStream wrong_stream24;
	

	
	@Before 
	public void init(){
		
		parser = new TacticsParser(new ExpectLogger());
		
		correct_string = ""
				+ "if sense_treasure else 200\n"
				+ "if sense_marker0 else 100\n"
				+ "if sense_marker1 else 2\n"
				+ "if sense_marker2 else 22\n"
				+ "if sense_marker3 else 387\n"
				+ "if sense_marker4 else 888\n"
				+ "if sense_marker5 else 876\n"
				+ "if sense_enemymarker else 888\n"
				+ "if sense_shiploaded else 1999\n"
				+ "if sense_supply else 0\n"
				+ "if !sense_treasure else 876\n"
				+ "if !sense_marker0 else 44\n"
				+ "if !sense_marker1 else 88\n"
				+ "if !sense_marker2 else 876\n"
				+ "if !sense_marker3 else 430\n"
				+ "if !sense_marker4 else 777\n"
				+ "if !sense_marker5 else 666\n"
				+ "if !sense_enemymarker else 999\n"
				+ "if !sense_shiploaded else 88\n"
				+ "if !sense_supply else 7\n"
				+ "if sense_celltype==island else 88\n"
				+ "if sense_celltype==home else 88\n"
				+ "if sense_celltype==enemyhome else 22\n"
				+ "if sense_celltype==empty else 44\n"
				+ "if sense_celltype!=island else 88\n"
				+ "if sense_celltype!=home else 88\n"
				+ "if sense_celltype!=enemyhome else 22\n"
				+ "if sense_celltype!=empty else 44\n"
				+ "if ship_direction==ship_direction else 76\n"
				+ "if ship_direction==ship_load else 876\n"
				+ "if ship_direction==ship_moral else 786\n"
				+ "if ship_direction==ship_condition else 777\n"
				+ "if ship_direction==sense_shipdirection else 111\n"
				+ "if ship_direction==sense_shipcondition else 222\n"
				+ "if ship_load!=ship_moral else 444\n"
				+ "if ship_load!=ship_condition else 333\n"
				+ "if ship_load!=sense_shipdirection else 123\n"
				+ "if ship_load!=sense_shipcondition else 999\n"
				+ "if ship_load!=ship_direction else 777\n"
				+ "if ship_load<ship_moral else 444\n"
				+ "if ship_load<ship_condition else 333\n"
				+ "if ship_load<sense_shipdirection else 123\n"
				+ "if ship_load<sense_shipcondition else 999\n"
				+ "if ship_load<ship_direction else 777\n"
				+ "if ship_load>ship_moral else 444\n"
				+ "if ship_load>ship_condition else 333\n"
				+ "if ship_load>sense_shipdirection else 123\n"
				+ "if ship_load>sense_shipcondition else 999\n"
				+ "if ship_load>ship_direction else 777\n"
				+ "if ship_direction==2 else 555\n"
				+ "if ship_load==3 else 678\n"
				+ "if ship_moral==1 else 888\n"
				+ "if ship_condition==3 else 4\n"
				+ "if sense_shipdirection==4 else 87\n"
				+ "if sense_shipcondition==2 else 77\n"
				+ "if ship_condition==900 else 7;this should be working?\n"
				+ "ifany sense_treasure sense_marker0 sense_marker1 sense_marker2 sense_marker3 else 7\n"
				+ "ifany sense_marker4 sense_marker5 sense_enemymarker else 3\n"
				+ "ifany sense_shiploaded sense_supply else 777\n"
				+ "ifany sense_treasure else 77; this should be working too?\n"
				+ "ifall sense_treasure sense_marker0 sense_marker1 sense_marker2 sense_marker3 else 7\n"
				+ "ifall sense_marker4 sense_marker5 sense_enemymarker else 3\n"
				+ "ifall sense_shiploaded sense_supply else 777\n"
				+ "ifall sense_treasure else 77; this should be working too?\n"
				+ "ifany sense_celltype==island sense_celltype!=enemyhome else 88\n"
				+ "ifany ship_direction==ship_load ship_moral!=ship_condition else 678\n"
				+ "ifany sense_shipdirection<sense_shipcondition ship_load>ship_moral else 67\n"
				+ "ifall sense_celltype==island sense_celltype!=enemyhome else 88\n"
				+ "ifall ship_direction==ship_load ship_moral!=ship_condition else 678\n"
				+ "ifall sense_shipdirection<sense_shipcondition ship_load>ship_moral else 67\n";
		
		//All possible type mismatches for sense_celltype
		wrong_string1 =""
				+ "if sense_celltype==friend else 3\n";
		wrong_string2 = ""
				+ "if sense_celltype==sense_treasure else 5\n";
		wrong_string3 = ""
				+ "if sense_celltype==ship_condition else 7\n";
		wrong_string4 =""
				+ "if sense_celltype!=friend else 3\n";
		wrong_string5 = ""
				+ "if sense_celltype!=sense_treasure else 5\n";
		wrong_string6 = ""
				+ "if sense_celltype!=ship_moral else 2\n";
		
		//All possible type mismatches for sense_shiptype
		wrong_string7 =""
				+ "if sense_shiptype==ship_load else 5\n";
		wrong_string8 = ""
				+ "if sense_shiptype==enemyhome else 5\n";
		wrong_string9 = ""
				+ "if sense_shiptype==sense_marker0 else 3\n";
		wrong_string10 = ""
				+ "if sense_shiptype!=ship_direction else 4\n";
		wrong_string11 = ""
				+ "if sense_shiptype!=empty else 6\n";
		wrong_string12 = ""
				+ "if sense_shiptype!=sense_marker5 else 2\n";
		
		//A few type mismatches for int_register (maybe all)
		wrong_string13 = ""
				+ "if ship_load==sense_marker0 else 2\n";
		wrong_string14 = ""
				+ "if ship_load==island else 3\n";
		wrong_string15 = ""
				+ "if ship_direction==friend else 5\n";
		wrong_string16 = ""
				+ "if ship_load!=sense_marker0 else 2\n";
		wrong_string17 = ""
				+ "if ship_load!=island else 3\n";
		wrong_string18 = ""
				+ "if ship_direction!=friend else 5\n";
		wrong_string19 = ""
				+ "if ship_load<sense_marker0 else 2\n";
		wrong_string20 = ""
				+ "if ship_load<island else 3\n";
		wrong_string21 = ""
				+ "if ship_direction<friend else 5\n";
		wrong_string22 = ""
				+ "if ship_load>sense_marker0 else 2\n";
		wrong_string23 = ""
				+ "if ship_load>island else 3\n";
		wrong_string24 = ""
				+ "if ship_direction>friend else 5\n";
		

		
		
				
		correct_stream = StreamHelper.asIS(correct_string);
		wrong_stream1 = StreamHelper.asIS(wrong_string1);
		wrong_stream2 = StreamHelper.asIS(wrong_string2);
		wrong_stream3 = StreamHelper.asIS(wrong_string3);
		wrong_stream4 = StreamHelper.asIS(wrong_string4);
		wrong_stream5 = StreamHelper.asIS(wrong_string5);
		wrong_stream6 = StreamHelper.asIS(wrong_string6);
		wrong_stream7 = StreamHelper.asIS(wrong_string7);
		wrong_stream8 = StreamHelper.asIS(wrong_string8);
		wrong_stream9 = StreamHelper.asIS(wrong_string9);
		wrong_stream10 = StreamHelper.asIS(wrong_string10);
		wrong_stream11 = StreamHelper.asIS(wrong_string11);
		wrong_stream12 = StreamHelper.asIS(wrong_string12);
		wrong_stream13 = StreamHelper.asIS(wrong_string13);
		wrong_stream14 = StreamHelper.asIS(wrong_string14);
		wrong_stream15 = StreamHelper.asIS(wrong_string15);
		wrong_stream16 = StreamHelper.asIS(wrong_string16);
		wrong_stream17 = StreamHelper.asIS(wrong_string17);
		wrong_stream18 = StreamHelper.asIS(wrong_string18);
		wrong_stream19 = StreamHelper.asIS(wrong_string19);
		wrong_stream20 = StreamHelper.asIS(wrong_string20);
		wrong_stream21 = StreamHelper.asIS(wrong_string21);
		wrong_stream22 = StreamHelper.asIS(wrong_string22);
		wrong_stream23 = StreamHelper.asIS(wrong_string23);
		wrong_stream24 = StreamHelper.asIS(wrong_string24);
							
		random = new Random(6);
		
		
	}
	
	
	@Test
	public void parseCorrectStream(){
		
		try{

			Instruction[] instructions = parser.parseTactics(correct_stream, random);
			System.out.println(instructions.toString());
			assertTrue("The length of the array should be 70 but was" + instructions.length, instructions.length == 70);
			
			assertTrue("The 0th Instruction should be an IfInstruction",
					instructions[0] instanceof IfInstruction);
			assertTrue("The 1th Instruction should be an IfInstruction",
					instructions[1] instanceof IfInstruction);
			assertTrue("The 2th Instruction should be an IfInstruction",
					instructions[2] instanceof IfInstruction);
			assertTrue("The 3th Instruction should be an IfInstruction",
					instructions[3] instanceof IfInstruction);
			assertTrue("The 4th Instruction should be an IfInstruction",
					instructions[4] instanceof IfInstruction);
			assertTrue("The 5th Instruction should be an IfInstruction",
					instructions[5] instanceof IfInstruction);
			assertTrue("The 6th Instruction should be an IfInstruction",
					instructions[6] instanceof IfInstruction);
			assertTrue("The 7th Instruction should be an IfInstruction",
					instructions[7] instanceof IfInstruction);
			assertTrue("The 8th Instruction should be an IfInstruction",
					instructions[8] instanceof IfInstruction);
			assertTrue("The 9th Instruction should be an IfInstruction",
					instructions[9] instanceof IfInstruction);
			assertTrue("The 10th Instruction should be an IfInstruction",
					instructions[10] instanceof IfInstruction);
			assertTrue("The 11th Instruction should be an IfInstruction",
					instructions[11] instanceof IfInstruction);
			assertTrue("The 12th Instruction should be an IfInstruction",
					instructions[12] instanceof IfInstruction);
			assertTrue("The 13th Instruction should be an IfInstruction",
					instructions[13] instanceof IfInstruction);
			assertTrue("The 14th Instruction should be an IfInstruction",
					instructions[14] instanceof IfInstruction);
			assertTrue("The 15th Instruction should be an IfInstruction",
					instructions[15] instanceof IfInstruction);
			assertTrue("The 16th Instruction should be an IfInstruction",
					instructions[16] instanceof IfInstruction);
			assertTrue("The 17th Instruction should be an IfInstruction",
					instructions[17] instanceof IfInstruction);
			assertTrue("The 18th Instruction should be an IfInstruction",
					instructions[18] instanceof IfInstruction);
			assertTrue("The 19th Instruction should be an IfInstruction",
					instructions[19] instanceof IfInstruction);
			assertTrue("The 20th Instruction should be an IfInstruction",
					instructions[20] instanceof IfInstruction);
			assertTrue("The 21th Instruction should be an IfInstruction",
					instructions[21] instanceof IfInstruction);
			assertTrue("The 22th Instruction should be an IfInstruction",
					instructions[22] instanceof IfInstruction);
			assertTrue("The 23th Instruction should be an IfInstruction",
					instructions[23] instanceof IfInstruction);
			assertTrue("The 24th Instruction should be an IfInstruction",
					instructions[24] instanceof IfInstruction);
			assertTrue("The 25th Instruction should be an IfInstruction",
					instructions[25] instanceof IfInstruction);
			assertTrue("The 26th Instruction should be an IfInstruction",
					instructions[26] instanceof IfInstruction);
			assertTrue("The 27th Instruction should be an IfInstruction",
					instructions[27] instanceof IfInstruction);
			assertTrue("The 28th Instruction should be an IfInstruction",
					instructions[28] instanceof IfInstruction);
			assertTrue("The 29th Instruction should be an IfInstruction",
					instructions[29] instanceof IfInstruction);
			assertTrue("The 30th Instruction should be an IfInstruction",
					instructions[30] instanceof IfInstruction);
			assertTrue("The 31th Instruction should be an IfInstruction",
					instructions[31] instanceof IfInstruction);
			assertTrue("The 32th Instruction should be an IfInstruction",
					instructions[32] instanceof IfInstruction);
			assertTrue("The 33th Instruction should be an IfInstruction",
					instructions[33] instanceof IfInstruction);
			assertTrue("The 34th Instruction should be an IfInstruction",
					instructions[34] instanceof IfInstruction);
			assertTrue("The 35th Instruction should be an IfInstruction",
					instructions[35] instanceof IfInstruction);
			assertTrue("The 36th Instruction should be an IfInstruction",
					instructions[36] instanceof IfInstruction);
			assertTrue("The 37th Instruction should be an IfInstruction",
					instructions[37] instanceof IfInstruction);
			assertTrue("The 38th Instruction should be an IfInstruction",
					instructions[38] instanceof IfInstruction);
			assertTrue("The 39th Instruction should be an IfInstruction",
					instructions[39] instanceof IfInstruction);
			assertTrue("The 40th Instruction should be an IfInstruction",
					instructions[40] instanceof IfInstruction);
			assertTrue("The 41th Instruction should be an IfInstruction",
					instructions[41] instanceof IfInstruction);
			assertTrue("The 42th Instruction should be an IfInstruction",
					instructions[42] instanceof IfInstruction);
			assertTrue("The 43th Instruction should be an IfInstruction",
					instructions[43] instanceof IfInstruction);
			assertTrue("The 44th Instruction should be an IfInstruction",
					instructions[44] instanceof IfInstruction);
			assertTrue("The 45th Instruction should be an IfInstruction",
					instructions[45] instanceof IfInstruction);
			assertTrue("The 46th Instruction should be an IfInstruction",
					instructions[46] instanceof IfInstruction);
			assertTrue("The 47th Instruction should be an IfInstruction",
					instructions[47] instanceof IfInstruction);
			assertTrue("The 48th Instruction should be an IfInstruction",
					instructions[48] instanceof IfInstruction);
			assertTrue("The 49th Instruction should be an IfInstruction",
					instructions[49] instanceof IfInstruction);
			assertTrue("The 50th Instruction should be an IfInstruction",
					instructions[50] instanceof IfInstruction);
			assertTrue("The 51th Instruction should be an IfInstruction",
					instructions[51] instanceof IfInstruction);
			assertTrue("The 52th Instruction should be an IfInstruction",
					instructions[52] instanceof IfInstruction);
			assertTrue("The 53th Instruction should be an IfInstruction",
					instructions[53] instanceof IfInstruction);
			assertTrue("The 54th Instruction should be an IfInstruction",
					instructions[54] instanceof IfInstruction);
			assertTrue("The 55th Instruction should be an IfInstruction",
					instructions[55] instanceof IfInstruction);
			assertTrue("The 56th Instruction should be an IfAnyInstruction",
					instructions[56] instanceof IfAnyInstruction);
			assertTrue("The 57th Instruction should be an IfAnyInstruction",
					instructions[57] instanceof IfAnyInstruction);
			assertTrue("The 58th Instruction should be an IfAnyInstruction",
					instructions[58] instanceof IfAnyInstruction);
			assertTrue("The 59th Instruction should be an IfAnyInstruction",
					instructions[59] instanceof IfAnyInstruction);
			assertTrue("The 60th Instruction should be an IfAllInstruction",
					instructions[60] instanceof IfAllInstruction);
			assertTrue("The 61th Instruction should be an IfAllInstruction",
					instructions[61] instanceof IfAllInstruction);
			assertTrue("The 62th Instruction should be an IfAllInstruction",
					instructions[62] instanceof IfAllInstruction);
			assertTrue("The 63th Instruction should be an IfAllInstruction",
					instructions[63] instanceof IfAllInstruction);
			assertTrue("The 64th Instruction should be an IfAnyInstruction",
					instructions[64] instanceof IfAnyInstruction);
			assertTrue("The 65th Instruction should be an IfAnyInstruction",
					instructions[65] instanceof IfAnyInstruction);
			assertTrue("The 66th Instruction should be an IfAnyInstruction",
					instructions[66] instanceof IfAnyInstruction);
			assertTrue("The 67th Instruction should be an IfAllInstruction",
					instructions[67] instanceof IfAllInstruction);
			assertTrue("The 68th Instruction should be an IfAllInstruction",
					instructions[68] instanceof IfAllInstruction);
			assertTrue("The 69th Instruction should be an IfAllInstruction",
					instructions[69] instanceof IfAllInstruction);
						
			
			
		}
		catch(Exception e){
			throw new IllegalArgumentException("This is a correctStream but there was the exception " + e.toString());
		}
				
		
	}
	
	@Test
	public void sense_celltype_TypeMismatch1(){
		
		try{
			parser.parseTactics(wrong_stream1, random);
		}
		catch(Exception e){
			return;
		}
		
		fail("A type mismatch should have occured: " + wrong_string1);
	}
	
	@Test
	public void sense_celltype_TypeMismatch2(){
		
		try{
			parser.parseTactics(wrong_stream2, random);
		}
		catch(Exception e){
			return;
		}
		
		fail("A type mismatch should have occured: " + wrong_string2);
	}
	
	@Test
	public void sense_celltype_TypeMismatch3(){
		
		try{
			parser.parseTactics(wrong_stream3, random);
		}
		catch(Exception e){
			return;
		}
		
		fail("A type mismatch should have occured: " + wrong_string3);
	}
	
	@Test
	public void sense_celltype_TypeMismatch4(){
		
		try{
			parser.parseTactics(wrong_stream4, random);
		}
		catch(Exception e){
			return;
		}
		
		fail("A type mismatch should have occured: " + wrong_string4);
	}
	
	@Test
	public void sense_celltype_TypeMismatch5(){
		
		try{
			parser.parseTactics(wrong_stream5, random);
		}
		catch(Exception e){
			return;
		}
		
		fail("A type mismatch should have occured: " + wrong_string5);
	}
	
	@Test
	public void sense_celltype_TypeMismatch6(){
		
		try{
			parser.parseTactics(wrong_stream6, random);
		}
		catch(Exception e){
			return;
		}
		
		fail("A type mismatch should have occured: " + wrong_string6);
	}
	
	@Test
	public void sense_shiptype_TypeMismatch1(){
		
		try{
			parser.parseTactics(wrong_stream7, random);
		}
		catch(Exception e){
			return;
		}
		
		fail("A type mismatch should have occured: " + wrong_string7);
	}
	
	@Test
	public void sense_shiptype_TypeMismatch2(){
		
		try{
			parser.parseTactics(wrong_stream8, random);
		}
		catch(Exception e){
			return;
		}
		
		fail("A type mismatch should have occured: " + wrong_string8);
	}
	
	@Test
	public void sense_shiptype_TypeMismatch3(){
		
		try{
			parser.parseTactics(wrong_stream9, random);
		}
		catch(Exception e){
			return;
		}
		
		fail("A type mismatch should have occured: " + wrong_string9);
	}
	
	@Test
	public void sense_shiptype_TypeMismatch4(){
		
		try{
			parser.parseTactics(wrong_stream10, random);
		}
		catch(Exception e){
			return;
		}
		
		fail("A type mismatch should have occured: " + wrong_string10);
	}
	
	@Test
	public void sense_shiptype_TypeMismatch5(){
		
		try{
			parser.parseTactics(wrong_stream11, random);
		}
		catch(Exception e){
			return;
		}
		
		fail("A type mismatch should have occured: " + wrong_string11);
	}
	
	@Test
	public void sense_shiptype_TypeMismatch6(){
		
		try{
			parser.parseTactics(wrong_stream12, random);
		}
		catch(Exception e){
			return;
		}
		
		fail("A type mismatch should have occured: " + wrong_string12);
	}
	
	@Test
	public void int_register_TypeMismatch1(){
		
		try{
			parser.parseTactics(wrong_stream13, random);
		}
		catch(Exception e){
			return;
		}
		
		fail("A type mismatch should have occured: " + wrong_string13);
	}
	
	@Test
	public void int_register_TypeMismatch2(){
		
		try{
			parser.parseTactics(wrong_stream14, random);
		}
		catch(Exception e){
			return;
		}
		
		fail("A type mismatch should have occured: " + wrong_string14);
	}
	
	@Test
	public void int_register_TypeMismatch3(){
		
		try{
			parser.parseTactics(wrong_stream15, random);
		}
		catch(Exception e){
			return;
		}
		
		fail("A type mismatch should have occured: " + wrong_string15);
	}
	
	@Test
	public void int_register_TypeMismatch4(){
		
		try{
			parser.parseTactics(wrong_stream16, random);
		}
		catch(Exception e){
			return;
		}
		
		fail("A type mismatch should have occured: " + wrong_string16);
	}
	
	@Test
	public void int_register_TypeMismatch5(){
		
		try{
			parser.parseTactics(wrong_stream17, random);
		}
		catch(Exception e){
			return;
		}
		
		fail("A type mismatch should have occured: " + wrong_string17);
	}
	
	@Test
	public void int_register_TypeMismatch6(){
		
		try{
			parser.parseTactics(wrong_stream18, random);
		}
		catch(Exception e){
			return;
		}
		
		fail("A type mismatch should have occured: " + wrong_string18);
	}
	
	@Test
	public void int_register_TypeMismatch7(){
		
		try{
			parser.parseTactics(wrong_stream19, random);
		}
		catch(Exception e){
			return;
		}
		
		fail("A type mismatch should have occured: " + wrong_string19);
	}
	
	@Test
	public void int_register_TypeMismatch8(){
		
		try{
			parser.parseTactics(wrong_stream20, random);
		}
		catch(Exception e){
			return;
		}
		
		fail("A type mismatch should have occured: " + wrong_string20);
	}
	
	@Test
	public void int_register_TypeMismatch9(){
		
		try{
			parser.parseTactics(wrong_stream21, random);
		}
		catch(Exception e){
			return;
		}
		
		fail("A type mismatch should have occured: " + wrong_string21);
	}
	
	@Test
	public void int_register_TypeMismatch10(){
		
		try{
			parser.parseTactics(wrong_stream22, random);
		}
		catch(Exception e){
			return;
		}
		
		fail("A type mismatch should have occured: " + wrong_string22);
	}
	
	@Test
	public void int_register_TypeMismatch11(){
		
		try{
			parser.parseTactics(wrong_stream23, random);
		}
		catch(Exception e){
			return;
		}
		
		fail("A type mismatch should have occured: " + wrong_string23);
	}
	
	@Test
	public void int_register_TypeMismatch12(){
		
		try{
			parser.parseTactics(wrong_stream24, random);
		}
		catch(Exception e){
			return;
		}
		
		fail("A type mismatch should have occured: " + wrong_string24);
	}
	
	
	
}
