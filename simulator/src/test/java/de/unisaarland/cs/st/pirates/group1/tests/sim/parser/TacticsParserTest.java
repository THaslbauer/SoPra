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
import de.unisaarland.cs.st.pirates.group1.sim.logic.expression.EqualOperator;
import de.unisaarland.cs.st.pirates.group1.sim.logic.expression.Expression;
import de.unisaarland.cs.st.pirates.group1.sim.logic.expression.LessOperator;
import de.unisaarland.cs.st.pirates.group1.sim.logic.instruction.Instruction;
import de.unisaarland.cs.st.pirates.group1.sim.logic.instruction.elseInstructions.FlipZeroInstruction;
import de.unisaarland.cs.st.pirates.group1.sim.logic.instruction.elseInstructions.IfAllInstruction;
import de.unisaarland.cs.st.pirates.group1.sim.logic.instruction.elseInstructions.IfAnyInstruction;
import de.unisaarland.cs.st.pirates.group1.sim.logic.instruction.elseInstructions.IfInstruction;
import de.unisaarland.cs.st.pirates.group1.sim.logic.instruction.elseInstructions.MoveInstruction;
import de.unisaarland.cs.st.pirates.group1.sim.logic.instruction.elseInstructions.PickupInstruction;
import de.unisaarland.cs.st.pirates.group1.sim.logic.instruction.elseInstructions.RefreshInstruction;
import de.unisaarland.cs.st.pirates.group1.sim.logic.instruction.elseInstructions.RepairInstruction;
import de.unisaarland.cs.st.pirates.group1.sim.logic.instruction.normalInstructions.DropInstruction;
import de.unisaarland.cs.st.pirates.group1.sim.logic.instruction.normalInstructions.GotoInstruction;
import de.unisaarland.cs.st.pirates.group1.sim.logic.instruction.normalInstructions.MarkInstruction;
import de.unisaarland.cs.st.pirates.group1.sim.logic.instruction.normalInstructions.SenseInstruction;
import de.unisaarland.cs.st.pirates.group1.sim.logic.instruction.normalInstructions.TurnInstruction;
import de.unisaarland.cs.st.pirates.group1.sim.logic.instruction.normalInstructions.UnmarkInstruction;
import de.unisaarland.cs.st.pirates.group1.sim.parser.TacticsParser;
import de.unisaarland.cs.st.pirates.group1.tests.testLogger.ExpectLogger;
import de.unisaarland.cs.st.pirates.group1.tests.testUtil.StreamHelper;

public class TacticsParserTest {
	private TacticsParser tacticsParser;
	private InputStream brokenStream;
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
	
	private String failString;
	private String brokenSenseString;
	private String brokenSenseStringTwo;
	private String brokenSenseStringThree;
	
	private InputStream failStream;
	private InputStream brokenSenseStream;
	private InputStream brokenSenseStreamTwo;
	private InputStream brokenSenseStreamThree;
	
	private String brokenPickupStringOne;
	private String brokenPickupStringTwo;
	private String brokenPickupStringThree;
	private String brokenPickupStringFour;
	private InputStream brokenPickupStreamOne;
	private InputStream brokenPickupStreamTwo;
	private InputStream brokenPickupStreamThree;
	private InputStream brokenPickupStreamFour;
	
	private String brokenGotoStringOne;
	private String brokenGotoStringTwo;
	private InputStream brokenGotoStreamOne;
	private InputStream brokenGotoStreamTwo;
	
	private String brokenFlipzeroStringOne;
	private String brokenFlipzeroStringTwo;
	private String brokenFlipzeroStringThree;
	private String brokenFlipzeroStringFour;
	private String brokenFlipzeroStringFive;
	private InputStream brokenFlipzeroStreamOne;
	private InputStream brokenFlipzeroStreamTwo;
	private InputStream brokenFlipzeroStreamThree;
	private InputStream brokenFlipzeroStreamFour;
	private InputStream brokenFlipzeroStreamFive;
	
	private String brokenTurnStringOne;
	private String brokenTurnStringTwo;
	private String brokenTurnStringThree;
	private InputStream brokenTurnStreamOne;
	private InputStream brokenTurnStreamTwo;
	private InputStream brokenTurnStreamThree;
	
	private String brokenIfStringOne;
	private String brokenIfStringTwo;
	private String brokenIfStringThree;
	private String brokenIfStringFour;
	private String brokenIfStringFive;
	private String brokenIfStringSix;
	private InputStream brokenIfStreamOne;
	private InputStream brokenIfStreamTwo;
	private InputStream brokenIfStreamThree;
	private InputStream brokenIfStreamFour;
	private InputStream brokenIfStreamFive;
	private InputStream brokenIfStreamSix;
	
	private String brokenIfAllStringOne;
	private String brokenIfAllStringTwo;
	private String brokenIfAllStringThree;
	private String brokenIfAllStringFour;
	private String brokenIfAllStringFive;
	private String brokenIfAllStringSix;
	private String brokenIfAllStringSeven;
	private String brokenIfAllStringEight;
	private InputStream brokenIfAllStreamOne;
	private InputStream brokenIfAllStreamTwo;
	private InputStream brokenIfAllStreamThree;
	private InputStream brokenIfAllStreamFour;
	private InputStream brokenIfAllStreamFive;
	private InputStream brokenIfAllStreamSix;
	private InputStream brokenIfAllStreamSeven;
	private InputStream brokenIfAllStreamEight;
	
	private String brokenIfAnyStringOne;
	private String brokenIfAnyStringTwo;
	private String brokenIfAnyStringThree;
	private String brokenIfAnyStringFour;
	private String brokenIfAnyStringFive;
	private String brokenIfAnyStringSix;
	private String brokenIfAnyStringSeven;
	private String brokenIfAnyStringEight;
	private InputStream brokenIfAnyStreamOne;
	private InputStream brokenIfAnyStreamTwo;
	private InputStream brokenIfAnyStreamThree;
	private InputStream brokenIfAnyStreamFour;
	private InputStream brokenIfAnyStreamFive;
	private InputStream brokenIfAnyStreamSix;
	private InputStream brokenIfAnyStreamSeven;
	private InputStream brokenIfAnyStreamEight;
	
	private String brokenMoveStringOne;
	private String brokenMoveStringTwo;
	private String brokenMoveStringThree;
	private String brokenMoveStringFour;
	private InputStream brokenMoveStreamOne;
	private InputStream brokenMoveStreamTwo;
	private InputStream brokenMoveStreamThree;
	private InputStream brokenMoveStreamFour;
	
	private String brokenRepairStringOne;
	private String brokenRepairStringTwo;
	private String brokenRepairStringThree;
	private String brokenRepairStringFour;
	private InputStream brokenRepairStreamOne;
	private InputStream brokenRepairStreamTwo;
	private InputStream brokenRepairStreamThree;
	private InputStream brokenRepairStreamFour;
	
	private String brokenRefreshStringOne;
	private String brokenRefreshStringTwo;
	private String brokenRefreshStringThree;
	private String brokenRefreshStringFour;
	private String brokenRefreshStringFive;
	private InputStream brokenRefreshStreamOne;
	private InputStream brokenRefreshStreamTwo;
	private InputStream brokenRefreshStreamThree;
	private InputStream brokenRefreshStreamFour;
	private InputStream brokenRefreshStreamFive;
	
	private String brokenMarkStringOne;
	private String brokenMarkStringTwo;
	private String brokenMarkStringThree;
	private InputStream brokenMarkStreamOne;
	private InputStream brokenMarkStreamTwo;
	private InputStream brokenMarkStreamThree;
	
	private String brokenUnmarkStringOne;
	private String brokenUnmarkStringTwo;
	private String brokenUnmarkStringThree;
	private InputStream brokenUnmarkStreamOne;
	private InputStream brokenUnmarkStreamTwo;
	private InputStream brokenUnmarkStreamThree;
	
	private String brokenTurnSingle;
	private String brokenMarkSingle;
	private String brokenUnmarkSingle;
	private String brokenMoveSingle;
	private String brokenPickupSingle;
	private String brokenDropSingle;
	private String brokenFlipzeroSingle;
	private String brokenGotoSingle;
	private String brokenSenseSingle;
	private String brokenIfSingle;
	private String brokenIfallSingle;
	private String brokenIfanySingle;
	private String brokenRefreshSingle;
	private String brokenRepairSingle;
	
	private InputStream brokenStreamTurnSingle;
	private InputStream brokenStreamMarkSingle;
	private InputStream brokenStreamUnmarkSingle;
	private InputStream brokenStreamMoveSingle;
	private InputStream brokenStreamPickupSingle;
	private InputStream brokenStreamDropSingle;
	private InputStream brokenStreamFlipzeroSingle;
	private InputStream brokenStreamGotoSingle;
	private InputStream brokenStreamSenseSingle;
	private InputStream brokenStreamIfSingle;
	private InputStream brokenStreamIfallSingle;
	private InputStream brokenStreamIfanySingle;
	private InputStream brokenStreamRefreshSingle;
	private InputStream brokenStreamRepairSingle;
	
	
	
	
	
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
				+ "flipzero 2 else 13"+"\n"
				+ "turn right"+"\n"
				+ "goto 0"+"\n"
				+ "sense 0"+"\n"
				+ "if sense_celltype==home else 19"+"\n"
				+ "move else 21"+"\n"
				+ "drop"+"\n"
				+ "goto 0"+"\n"
				+ "move else 21"+"\n"
				+ "goto 14"+"\n"
				+ "flipzero 3 else 24; == == <= test 123 bbq"+"\n"
				+ "turn left;this is another comment"+"\n"
				+ "goto 14"+"\n"
				+ "flipzero 2 else 26"+"\n"
				+ "turn right"+"\n"
				+ "goto 14"+"\n"
				+ "repair else 0" + "\n"
				+ "ifany sense_treasure ship_load==0 else 29"+"\n"
				+ "refresh 5 else 0"+"\n"
				+ "mark 0"+"\n"
				+ "mark 2"+"\n"
				+ "unmark 3;this is a freaking comment"+"\n"
				+ "repair else 2001"+"\n"
				+ "move else 2000";
		
		String brokenString = ""
				+ "ifall 2;== is much cool, ; ; lawl < ; "+"\n"
				+ ";lawlbbq"+"\n"
				+ "==";
		
		failString =""
				+ "sensed 0";
		
		brokenSenseString = ""
				+ "sense deinemudda 0";
		brokenSenseStringTwo = ""
				+ "refresh 7 else 0";
		brokenSenseStringThree = ""
				+ "sense 7";
		
		brokenPickupStringOne = ""
				+ "pickup 0 6 else";
		brokenPickupStringTwo = ""
				+ "pickup 8 9 else 8";
		brokenPickupStringThree = ""
				+ "pickup 0 else 9 12";
		brokenPickupStringFour = ""
				+ "pickup 7 else 12";
		
		brokenGotoStringOne = ""
				+ "goto else";
		brokenGotoStringTwo = ""
				+ "goto 4 else 3";
		
		brokenFlipzeroStringOne = ""
				+ "flipzero else else 11";
		brokenFlipzeroStringTwo = ""
				+ "flipzero 2 test 0";
		brokenFlipzeroStringThree = ""
				+ "flipzero 2 else else";
		brokenFlipzeroStringFour = ""
				+ "flipzero 1 else 1 1 ;blabla";
		brokenFlipzeroStringFive = ""
				+ "flipzero -1 else 11";
		
		brokenTurnStringOne = ""
				+ "turn lefty";
		brokenTurnStringTwo = ""
				+ "turn 8";
		brokenTurnStringThree = ""
				+ "turn right else left";
		
		brokenIfStringOne = ""
				+ "if sense_cell=home else 19";
		brokenIfStringTwo = ""
				+ "if sense_celltype==home elst 10";
		brokenIfStringThree = ""
				+ "if sense_celltype==home else else";
		brokenIfStringFour = ""
				+ "if sense_celltype==home else 10 10";
		brokenIfStringFive = ""
				+ "if 0 else 1";
		brokenIfStringSix = ""
				+ "if sense_celltype==home else 01";
		
		
		
		brokenIfAllStringOne = ""
				+ "ifall sense_celltype==home sensecelltype==home else 19";
		brokenIfAllStringTwo = ""
				+ "ifall sense_celltype==home sense_celltype==home elst 10";
		brokenIfAllStringThree = ""
				+ "ifall sense_celltype==home sense_treasure else else";
		brokenIfAllStringFour = ""
				+ "ifall sense_celltype==home else 10 10";
		brokenIfAllStringFive = ""
				+ "ifall 0 else 1";
		brokenIfAllStringSix = ""
				+ "ifall sense_treasure else";
		brokenIfAllStringSeven = ""
				+ "ifall sense_treasure ship_load<4 else";
		brokenIfAllStringEight = ""
				+ "ifall ship_load else 4";
		
		
		brokenIfAnyStringOne = ""
				+ "ifany sense_celltype==home sensecelltype==home else 19";
		brokenIfAnyStringTwo = ""
				+ "ifany sense_celltype==home sense_celltype==home elst 10";
		brokenIfAnyStringThree = ""
				+ "ifany sense_celltype==home sense_treasure else else";
		brokenIfAnyStringFour = ""
				+ "ifany sense_celltype==home else 10 10";
		brokenIfAnyStringFive = ""
				+ "ifany 0 else 1";
		brokenIfAnyStringSix = ""
				+ "ifany sense_treasure else";
		brokenIfAnyStringSeven = ""
				+ "ifany sense_treasure ship_load<4 else";
		brokenIfAnyStringEight = ""
				+ "ifany ship_load else 4";
		
		brokenRepairStringOne = ""
				+ "repair elset 0";
		brokenRepairStringTwo = ""
				+ "repair else 0 1";
		brokenRepairStringThree = ""
				+ "repair 5 else 1";
		brokenRepairStringFour = ""
				+ "repair else";
		
		brokenMoveStringOne = ""
				+ "move elset 0";
		brokenMoveStringTwo = ""
				+ "move else 0 1";
		brokenMoveStringThree = ""
				+ "move 5 else 1";
		brokenMoveStringFour = ""
				+ "move else";
		
		brokenRefreshStringOne = ""
				+ "refresh sense_celltype==home sensecelltype==home else 19";
		brokenRefreshStringTwo = ""
				+ "refresh sense_celltype==home sense_celltype==home elst 10";
		brokenRefreshStringThree = ""
				+ "refresh sense_celltype==home sense_treasure else else";
		brokenRefreshStringFour = ""
				+ "refresh sense_celltype==home else 10 10";
		brokenRefreshStringFive = ""
				+ "refresh 0 1 else 1";
		
		brokenMarkStringOne = ""
				+ "mark 6";
		brokenMarkStringTwo = ""
				+ "mark 5 else 2";
		brokenMarkStringThree = ""
				+ "mark else";

		
		brokenUnmarkStringOne = ""
				+ "unmark 7";
		brokenUnmarkStringTwo = ""
				+ "unmark 5 else 2";
		brokenUnmarkStringThree = ""
				+ "unmark else";
		
		brokenTurnSingle = "turn";
		brokenMarkSingle = "mark";
		brokenUnmarkSingle = "unmark";
		brokenMoveSingle = "move";
		brokenPickupSingle = "pickup";
		brokenDropSingle = "drop 2";
		brokenFlipzeroSingle = "flipzero";
		brokenGotoSingle = "goto";
		brokenSenseSingle = "sense";
		brokenIfSingle = "if";
		brokenIfallSingle = "ifall";
		brokenIfanySingle = "ifany";
		brokenRefreshSingle = "refresh";
		brokenRepairSingle = "repair";
		
		
		random = new Random(0);
		expectLogger = new ExpectLogger();
		tacticsParser = new TacticsParser(expectLogger);
		
		stream = StreamHelper.asIS(string);
		brokenStream = StreamHelper.asIS(brokenString);
		brokenSenseStream = StreamHelper.asIS(brokenSenseString);
		brokenSenseStreamTwo = StreamHelper.asIS(brokenSenseStringTwo);
		brokenSenseStreamThree = StreamHelper.asIS(brokenSenseStringThree);
		
		failStream = StreamHelper.asIS(failString);
		brokenPickupStreamOne = StreamHelper.asIS(brokenPickupStringOne);
		brokenPickupStreamTwo = StreamHelper.asIS(brokenPickupStringTwo);
		brokenPickupStreamThree = StreamHelper.asIS(brokenPickupStringThree);
		brokenPickupStreamFour = StreamHelper.asIS(brokenPickupStringFour);
		
		brokenGotoStreamOne = StreamHelper.asIS(brokenGotoStringOne);
		brokenGotoStreamTwo = StreamHelper.asIS(brokenGotoStringTwo);
		brokenFlipzeroStreamOne = StreamHelper.asIS(brokenFlipzeroStringOne);
		brokenFlipzeroStreamTwo = StreamHelper.asIS(brokenFlipzeroStringTwo);
		brokenFlipzeroStreamThree = StreamHelper.asIS(brokenFlipzeroStringThree);
		brokenFlipzeroStreamFour = StreamHelper.asIS(brokenFlipzeroStringFour);
		brokenFlipzeroStreamFive = StreamHelper.asIS(brokenFlipzeroStringFive);
		
		brokenTurnStreamOne = StreamHelper.asIS(brokenTurnStringOne);
		brokenTurnStreamTwo = StreamHelper.asIS(brokenTurnStringTwo);
		brokenTurnStreamThree = StreamHelper.asIS(brokenTurnStringThree);
		
		brokenIfStreamOne = StreamHelper.asIS(brokenIfStringOne);
		brokenIfStreamTwo = StreamHelper.asIS(brokenIfStringTwo);
		brokenIfStreamThree = StreamHelper.asIS(brokenIfStringThree);
		brokenIfStreamFour = StreamHelper.asIS(brokenIfStringFour);
		brokenIfStreamFive = StreamHelper.asIS(brokenIfStringFive);
		brokenIfStreamSix = StreamHelper.asIS(brokenIfStringSix);
		
		brokenIfAllStreamOne = StreamHelper.asIS(brokenIfAllStringOne);
		brokenIfAllStreamTwo = StreamHelper.asIS(brokenIfAllStringTwo);
		brokenIfAllStreamThree = StreamHelper.asIS(brokenIfAllStringThree);
		brokenIfAllStreamFour = StreamHelper.asIS(brokenIfAllStringFour);
		brokenIfAllStreamFive = StreamHelper.asIS(brokenIfAllStringFive);
		brokenIfAllStreamSix = StreamHelper.asIS(brokenIfAllStringSix);
		brokenIfAllStreamSeven = StreamHelper.asIS(brokenIfAllStringSeven);
		brokenIfAllStreamEight = StreamHelper.asIS(brokenIfAllStringEight);
		
		brokenIfAnyStreamOne = StreamHelper.asIS(brokenIfAnyStringOne);
		brokenIfAnyStreamTwo = StreamHelper.asIS(brokenIfAnyStringTwo);
		brokenIfAnyStreamThree = StreamHelper.asIS(brokenIfAnyStringThree);
		brokenIfAnyStreamFour = StreamHelper.asIS(brokenIfAnyStringFour);
		brokenIfAnyStreamFive = StreamHelper.asIS(brokenIfAnyStringFive);
		brokenIfAnyStreamSix = StreamHelper.asIS(brokenIfAnyStringSix);
		brokenIfAnyStreamSeven = StreamHelper.asIS(brokenIfAnyStringSeven);
		brokenIfAnyStreamEight = StreamHelper.asIS(brokenIfAnyStringEight);
		
		brokenMoveStreamOne = StreamHelper.asIS(brokenMoveStringOne);
		brokenMoveStreamTwo = StreamHelper.asIS(brokenMoveStringTwo);
		brokenMoveStreamThree = StreamHelper.asIS(brokenMoveStringThree);
		brokenMoveStreamFour = StreamHelper.asIS(brokenMoveStringFour);
		
		brokenRepairStreamOne = StreamHelper.asIS(brokenRepairStringOne);
		brokenRepairStreamTwo = StreamHelper.asIS(brokenRepairStringTwo);
		brokenRepairStreamThree = StreamHelper.asIS(brokenRepairStringThree);
		brokenRepairStreamFour = StreamHelper.asIS(brokenRepairStringFour);
		
		brokenRefreshStreamOne = StreamHelper.asIS(brokenRefreshStringOne);
		brokenRefreshStreamTwo = StreamHelper.asIS(brokenRefreshStringTwo);
		brokenRefreshStreamThree = StreamHelper.asIS(brokenRefreshStringThree);
		brokenRefreshStreamFour = StreamHelper.asIS(brokenRefreshStringFour);
		brokenRefreshStreamFive = StreamHelper.asIS(brokenRefreshStringFive);
		
		brokenMarkStreamOne = StreamHelper.asIS(brokenMarkStringOne);
		brokenMarkStreamTwo = StreamHelper.asIS(brokenMarkStringTwo);
		brokenMarkStreamThree = StreamHelper.asIS(brokenMarkStringThree);
		
		brokenUnmarkStreamOne = StreamHelper.asIS(brokenUnmarkStringOne);
		brokenUnmarkStreamTwo = StreamHelper.asIS(brokenUnmarkStringTwo);
		brokenUnmarkStreamThree = StreamHelper.asIS(brokenUnmarkStringThree);
		
		brokenStreamTurnSingle = StreamHelper.asIS(brokenTurnSingle);
		brokenStreamMarkSingle = StreamHelper.asIS(brokenMarkSingle);
		brokenStreamUnmarkSingle = StreamHelper.asIS(brokenUnmarkSingle);
		brokenStreamMoveSingle = StreamHelper.asIS(brokenMoveSingle);
		brokenStreamPickupSingle = StreamHelper.asIS(brokenPickupSingle);
		brokenStreamDropSingle = StreamHelper.asIS(brokenDropSingle);
		brokenStreamFlipzeroSingle = StreamHelper.asIS(brokenFlipzeroSingle);
		brokenStreamGotoSingle = StreamHelper.asIS(brokenGotoSingle);
		brokenStreamSenseSingle = StreamHelper.asIS(brokenSenseSingle);
		brokenStreamIfSingle = StreamHelper.asIS(brokenIfSingle);
		brokenStreamIfallSingle = StreamHelper.asIS(brokenIfallSingle);
		brokenStreamIfanySingle = StreamHelper.asIS(brokenIfanySingle);
		brokenStreamRefreshSingle = StreamHelper.asIS(brokenRefreshSingle);
		brokenStreamRepairSingle = StreamHelper.asIS(brokenRepairSingle);
		
		
		
		
		
		
		entityFactory = new EntityFactory();
		
		//A worldMap with exactly one seatile
		worldMap = new Worldmap6T(2,2,expectLogger,entityFactory);
		
		//A TestFaction, Position and Tile
		faction = new Faction("a",0);
		Position position1 = new Position(0,0);
		Position position2 = new Position(1,0);
		Position position3 = new Position(0,1);
		//Position position4 = new Position(1,1);
		
		waterTile1 = worldMap.createSeaTile(position1);
		waterTile2 = worldMap.createSeaTile(position2);
		islandTile1 = worldMap.createIslandTile(position3, true);
		islandTile2 = worldMap.createIslandTile(position3, false);
		
		
		//A Test ship of the TestFaction with ID 1, if ship is attaching itself
		ship = new Ship(faction,1,waterTile1);
	}
	
	/**
	 * checks if there is an error raised while parsing a sense instruction
	 */
	@Test
	public void failSenseInstructionFour(){
		try{
			tacticsParser.parseTactics(brokenSenseStreamThree, random);
					}catch(IllegalArgumentException e){
						return;
					}
		fail("there should be a illegalargument exception raised because tried to parse: \" " + brokenSenseStringThree + "\" ." 
				+ "see in the tacticsparsertestclass @before");
	}
	
	
	/**
	 * checks if there is an error raised while parsing a sense instruction
	 */
	@Test
	public void failIfAnyInstructionEight(){
		try{
			tacticsParser.parseTactics(brokenIfAnyStreamEight, random);
					}catch(IllegalArgumentException e){
						return;
					}
		fail("there should be a illegalargument exception raised because tried to parse: \" " + brokenIfAnyStringEight + "\" ." 
				+ "see in the tacticsparsertestclass @before");
	}
	
	/**
	 * checks if there is an error raised while parsing a sense instruction
	 */
	@Test
	public void failIfAnyInstructionSeven(){
		try{
			tacticsParser.parseTactics(brokenIfAnyStreamSeven, random);
					}catch(IllegalArgumentException e){
						return;
					}
		fail("there should be a illegalargument exception raised because tried to parse: \" " + brokenIfAnyStringSeven + "\" ." 
				+ "see in the tacticsparsertestclass @before");
	}
	
	/**
	 * checks if there is an error raised while parsing a sense instruction
	 */
	@Test
	public void failIfAnyInstructionSix(){
		try{
			tacticsParser.parseTactics(brokenIfAnyStreamSix, random);
					}catch(IllegalArgumentException e){
						return;
					}
		fail("there should be a illegalargument exception raised because tried to parse: \" " + brokenIfAnyStringSix + "\" ." 
				+ "see in the tacticsparsertestclass @before");
	}
	
	/**
	 * checks if there is an error raised while parsing a sense instruction
	 */
	@Test
	public void failIfAllInstructionEight(){
		try{
			tacticsParser.parseTactics(brokenIfAllStreamEight, random);
					}catch(IllegalArgumentException e){
						return;
					}
		fail("there should be a illegalargument exception raised because tried to parse: \" " + brokenIfAllStringEight + "\" ." 
				+ "see in the tacticsparsertestclass @before");
	}
	
	/**
	 * checks if there is an error raised while parsing a sense instruction
	 */
	@Test
	public void failIfAllInstructionSeven(){
		try{
			tacticsParser.parseTactics(brokenIfAllStreamSeven, random);
					}catch(IllegalArgumentException e){
						return;
					}
		fail("there should be a illegalargument exception raised because tried to parse: \" " + brokenIfAllStringSeven + "\" ." 
				+ "see in the tacticsparsertestclass @before");
	}
	
	/**
	 * checks if there is an error raised while parsing a sense instruction
	 */
	@Test
	public void failIfAllInstructionSix(){
		try{
			tacticsParser.parseTactics(brokenIfAllStreamSix, random);
					}catch(IllegalArgumentException e){
						return;
					}
		fail("there should be a illegalargument exception raised because tried to parse: \" " + brokenIfAllStringSix + "\" ." 
				+ "see in the tacticsparsertestclass @before");
	}
	
	/**
	 * checks if there is an error raised while parsing a sense instruction
	 */
	@Test
	public void failSenseInstructionThree(){
		try{
			tacticsParser.parseTactics(brokenSenseStreamTwo, random);
					}catch(IllegalArgumentException e){
						return;
					}
		fail("there should be a illegalargument exception raised because tried to parse: \" " + brokenSenseStringTwo + "\" ." 
				+ "see in the tacticsparsertestclass @before");
	}
	
	/**
	 * checks if there is an error raised while parsing a single instruction
	 */
	@Test
	public void failDropDouble(){
		try{
			tacticsParser.parseTactics(brokenStreamDropSingle, random);
					}catch(IllegalArgumentException e){
						return;
					}
		fail("there should be a illegalargument exception raised because tried to parse: \" " + brokenDropSingle + "\" ." 
				+ "see in the tacticsparsertestclass @before");
	}
	
	/**
	 * checks if there is an error raised while parsing a single instruction
	 */
	@Test
	public void failRepairSingle(){
		try{
			tacticsParser.parseTactics(brokenStreamRepairSingle, random);
					}catch(IllegalArgumentException e){
						return;
					}
		fail("there should be a illegalargument exception raised because tried to parse: \" " + brokenRepairSingle + "\" ." 
				+ "see in the tacticsparsertestclass @before");
	}
	
	/**
	 * checks if there is an error raised while parsing a single instruction
	 */
	@Test
	public void failRefreshSingle(){
		try{
			tacticsParser.parseTactics(brokenStreamRefreshSingle, random);
					}catch(IllegalArgumentException e){
						return;
					}
		fail("there should be a illegalargument exception raised because tried to parse: \" " + brokenRefreshSingle + "\" ." 
				+ "see in the tacticsparsertestclass @before");
	}
	
	/**
	 * checks if there is an error raised while parsing a single instruction
	 */
	@Test
	public void failIfanySingle(){
		try{
			tacticsParser.parseTactics(brokenStreamIfanySingle, random);
					}catch(IllegalArgumentException e){
						return;
					}
		fail("there should be a illegalargument exception raised because tried to parse: \" " + brokenIfanySingle + "\" ." 
				+ "see in the tacticsparsertestclass @before");
	}
	
	/**
	 * checks if there is an error raised while parsing a single instruction
	 */
	@Test
	public void failIfallSingle(){
		try{
			tacticsParser.parseTactics(brokenStreamIfallSingle, random);
					}catch(IllegalArgumentException e){
						return;
					}
		fail("there should be a illegalargument exception raised because tried to parse: \" " + brokenIfallSingle + "\" ." 
				+ "see in the tacticsparsertestclass @before");
	}
	
	/**
	 * checks if there is an error raised while parsing a single instruction
	 */
	@Test
	public void failIfSingle(){
		try{
			tacticsParser.parseTactics(brokenStreamIfSingle, random);
					}catch(IllegalArgumentException e){
						return;
					}
		fail("there should be a illegalargument exception raised because tried to parse: \" " + brokenIfSingle + "\" ." 
				+ "see in the tacticsparsertestclass @before");
	}
	
	/**
	 * checks if there is an error raised while parsing a single instruction
	 */
	@Test
	public void failSenseSingle(){
		try{
			tacticsParser.parseTactics(brokenStreamSenseSingle, random);
					}catch(IllegalArgumentException e){
						return;
					}
		fail("there should be a illegalargument exception raised because tried to parse: \" " + brokenSenseSingle + "\" ." 
				+ "see in the tacticsparsertestclass @before");
	}
	
	/**
	 * checks if there is an error raised while parsing a single instruction
	 */
	@Test
	public void failGotoSingle(){
		try{
			tacticsParser.parseTactics(brokenStreamGotoSingle, random);
					}catch(IllegalArgumentException e){
						return;
					}
		fail("there should be a illegalargument exception raised because tried to parse: \" " + brokenGotoSingle + "\" ." 
				+ "see in the tacticsparsertestclass @before");
	}
	
	/**
	 * checks if there is an error raised while parsing a single instruction
	 */
	@Test
	public void failFlipzeroSingle(){
		try{
			tacticsParser.parseTactics(brokenStreamFlipzeroSingle, random);
					}catch(IllegalArgumentException e){
						return;
					}
		fail("there should be a illegalargument exception raised because tried to parse: \" " + brokenFlipzeroSingle + "\" ." 
				+ "see in the tacticsparsertestclass @before");
	}
	
	
	/**
	 * checks if there is an error raised while parsing a single instruction
	 */
	@Test
	public void failPickupSingle(){
		try{
			tacticsParser.parseTactics(brokenStreamPickupSingle, random);
					}catch(IllegalArgumentException e){
						return;
					}
		fail("there should be a illegalargument exception raised because tried to parse: \" " + brokenPickupSingle + "\" ." 
				+ "see in the tacticsparsertestclass @before");
	}
	
	/**
	 * checks if there is an error raised while parsing a single instruction
	 */
	@Test
	public void failMoveSingle(){
		try{
			tacticsParser.parseTactics(brokenStreamMoveSingle, random);
					}catch(IllegalArgumentException e){
						return;
					}
		fail("there should be a illegalargument exception raised because tried to parse: \" " + brokenMoveSingle + "\" ." 
				+ "see in the tacticsparsertestclass @before");
	}
	
	/**
	 * checks if there is an error raised while parsing a single instruction
	 */
	@Test
	public void failUnmarkSingle(){
		try{
			tacticsParser.parseTactics(brokenStreamUnmarkSingle, random);
					}catch(IllegalArgumentException e){
						return;
					}
		fail("there should be a illegalargument exception raised because tried to parse: \" " + brokenUnmarkSingle + "\" ." 
				+ "see in the tacticsparsertestclass @before");
	}
	
	/**
	 * checks if there is an error raised while parsing a single instruction
	 */
	@Test
	public void failMarkSingle(){
		try{
			tacticsParser.parseTactics(brokenStreamMarkSingle, random);
					}catch(IllegalArgumentException e){
						return;
					}
		fail("there should be a illegalargument exception raised because tried to parse: \" " + brokenMarkSingle + "\" ." 
				+ "see in the tacticsparsertestclass @before");
	}
	
	/**
	 * checks if there is an error raised while parsing a single instruction
	 */
	@Test
	public void failTurnSingle(){
		try{
			tacticsParser.parseTactics(brokenStreamTurnSingle, random);
					}catch(IllegalArgumentException e){
						return;
					}
		fail("there should be a illegalargument exception raised because tried to parse: \" " + brokenTurnSingle + "\" ." 
				+ "see in the tacticsparsertestclass @before");
	}
	
	
	/**
	 * checks if there is an error raised while parsing a wrong unmark instruction
	 */
	@Test
	public void failUnmarkInstructionThree(){
		try{
			tacticsParser.parseTactics(brokenUnmarkStreamThree, random);
					}catch(IllegalArgumentException e){
						return;
					}
		fail("there should be a illegalargument exception raised because tried to parse: \" " + brokenUnmarkStringThree + "\" ." 
				+ "see in the tacticsparsertestclass @before");
	}
	
	/**
	 * checks if there is an error raised while parsing a wrong unmark instruction
	 */
	@Test
	public void failUnmarkInstructionTwo(){
		try{
			tacticsParser.parseTactics(brokenUnmarkStreamTwo, random);
					}catch(IllegalArgumentException e){
						return;
					}
		fail("there should be a illegalargument exception raised because tried to parse: \" " + brokenUnmarkStringTwo + "\" ." 
				+ "see in the tacticsparsertestclass @before");
	}
	
	/**
	 * checks if there is an error raised while parsing a wrong unmark instruction
	 */
	@Test
	public void failUnmarkInstructionOne(){
		try{
			tacticsParser.parseTactics(brokenUnmarkStreamOne, random);
					}catch(IllegalArgumentException e){
						return;
					}
		fail("there should be a illegalargument exception raised because tried to parse: \" " + brokenUnmarkStringOne + "\" ." 
				+ "see in the tacticsparsertestclass @before");
	}
	
	/**
	 * checks if there is an error raised while parsing a wrong mark instruction
	 */
	@Test
	public void failMarkInstructionThree(){
		try{
			tacticsParser.parseTactics(brokenMarkStreamThree, random);
					}catch(IllegalArgumentException e){
						return;
					}
		fail("there should be a illegalargument exception raised because tried to parse: \" " + brokenMarkStringThree + "\" ." 
				+ "see in the tacticsparsertestclass @before");
	}
	
	/**
	 * checks if there is an error raised while parsing a wrong mark instruction
	 */
	@Test
	public void failMarkInstructionTwo(){
		try{
			tacticsParser.parseTactics(brokenMarkStreamTwo, random);
					}catch(IllegalArgumentException e){
						return;
					}
		fail("there should be a illegalargument exception raised because tried to parse: \" " + brokenMarkStringTwo + "\" ." 
				+ "see in the tacticsparsertestclass @before");
	}
	
	/**
	 * checks if there is an error raised while parsing a wrong mark instruction
	 */
	@Test
	public void failMarkInstructionOne(){
		try{
			tacticsParser.parseTactics(brokenMarkStreamOne, random);
					}catch(IllegalArgumentException e){
						return;
					}
		fail("there should be a illegalargument exception raised because tried to parse: \" " + brokenMarkStringOne + "\" ." 
				+ "see in the tacticsparsertestclass @before");
	}
	
	/**
	 * checks if there is an error raised while parsing a wrong refresh instruction
	 */
	@Test
	public void failRefreshInstructionFive(){
		try{
			tacticsParser.parseTactics(brokenRefreshStreamFive, random);
					}catch(IllegalArgumentException e){
						return;
					}
		fail("there should be a illegalargument exception raised because tried to parse: \" " + brokenRefreshStringFive + "\" ." 
				+ "see in the tacticsparsertestclass @before");
	}
	
	/**
	 * checks if there is an error raised while parsing a wrong refresh instruction
	 */
	@Test
	public void failRefreshInstructionFour(){
		try{
			tacticsParser.parseTactics(brokenRefreshStreamFour, random);
					}catch(IllegalArgumentException e){
						return;
					}
		fail("there should be a illegalargument exception raised because tried to parse: \" " + brokenRefreshStringFour + "\" ." 
				+ "see in the tacticsparsertestclass @before");
	}
	
	/**
	 * checks if there is an error raised while parsing a wrong refresh instruction
	 */
	@Test
	public void failRefreshInstructionThree(){
		try{
			tacticsParser.parseTactics(brokenRefreshStreamThree, random);
					}catch(IllegalArgumentException e){
						return;
					}
		fail("there should be a illegalargument exception raised because tried to parse: \" " + brokenRefreshStringThree + "\" ." 
				+ "see in the tacticsparsertestclass @before");
	}
	
	/**
	 * checks if there is an error raised while parsing a wrong refresh instruction
	 */
	@Test
	public void failRefreshInstructionTwo(){
		try{
			tacticsParser.parseTactics(brokenRefreshStreamTwo, random);
					}catch(IllegalArgumentException e){
						return;
					}
		fail("there should be a illegalargument exception raised because tried to parse: \" " + brokenRefreshStringTwo + "\" ." 
				+ "see in the tacticsparsertestclass @before");
	}
	
	/**
	 * checks if there is an error raised while parsing a wrong refresh instruction
	 */
	@Test
	public void failRefreshInstructionOne(){
		try{
			tacticsParser.parseTactics(brokenRefreshStreamOne, random);
					}catch(IllegalArgumentException e){
						return;
					}
		fail("there should be a illegalargument exception raised because tried to parse: \" " + brokenRefreshStringOne + "\" ." 
				+ "see in the tacticsparsertestclass @before");
	}
	
	/**
	 * checks if there is an error raised while parsing a wrong repair instruction
	 */
	@Test
	public void failRepairInstructionFour(){
		try{
			tacticsParser.parseTactics(brokenRepairStreamFour, random);
					}catch(IllegalArgumentException e){
						return;
					}
		fail("there should be a illegalargument exception raised because tried to parse: \" " + brokenRepairStringFour + "\" ." 
				+ "see in the tacticsparsertestclass @before");
	}
	
	/**
	 * checks if there is an error raised while parsing a wrong repair instruction
	 */
	@Test
	public void failRepairInstructionThree(){
		try{
			tacticsParser.parseTactics(brokenRepairStreamThree, random);
					}catch(IllegalArgumentException e){
						return;
					}
		fail("there should be a illegalargument exception raised because tried to parse: \" " + brokenRepairStringThree + "\" ." 
				+ "see in the tacticsparsertestclass @before");
	}
	
	/**
	 * checks if there is an error raised while parsing a wrong repair instruction
	 */
	@Test
	public void failRepairInstructionTwo(){
		try{
			tacticsParser.parseTactics(brokenRepairStreamTwo, random);
					}catch(IllegalArgumentException e){
						return;
					}
		fail("there should be a illegalargument exception raised because tried to parse: \" " + brokenRepairStringTwo + "\" ." 
				+ "see in the tacticsparsertestclass @before");
	}
	
	/**
	 * checks if there is an error raised while parsing a wrong repair instruction
	 */
	@Test
	public void failRepairInstructionOne(){
		try{
			tacticsParser.parseTactics(brokenRepairStreamOne, random);
					}catch(IllegalArgumentException e){
						return;
					}
		fail("there should be a illegalargument exception raised because tried to parse: \" " + brokenRepairStringOne + "\" ." 
				+ "see in the tacticsparsertestclass @before");
	}
	
	/**
	 * checks if there is an error raised while parsing a wrong move instruction
	 */
	@Test
	public void failMoveInstructionFour(){
		try{
			tacticsParser.parseTactics(brokenMoveStreamFour, random);
					}catch(IllegalArgumentException e){
						return;
					}
		fail("there should be a illegalargument exception raised because tried to parse: \" " + brokenMoveStringFour + "\" ." 
				+ "see in the tacticsparsertestclass @before");
	}
	
	/**
	 * checks if there is an error raised while parsing a wrong move instruction
	 */
	@Test
	public void failMoveInstructionThree(){
		try{
			tacticsParser.parseTactics(brokenMoveStreamThree, random);
					}catch(IllegalArgumentException e){
						return;
					}
		fail("there should be a illegalargument exception raised because tried to parse: \" " + brokenMoveStringThree + "\" ." 
				+ "see in the tacticsparsertestclass @before");
	}
	
	/**
	 * checks if there is an error raised while parsing a wrong Ifany instruction
	 */
	@Test
	public void failIfAnyInstructionFive(){
		try{
			tacticsParser.parseTactics(brokenIfAnyStreamFive, random);
					}catch(IllegalArgumentException e){
						return;
					}
		fail("there should be a illegalargument exception raised because tried to parse: \" " + brokenIfAnyStringFive + "\" ." 
				+ "see in the tacticsparsertestclass @before");
	}
	
	/**
	 * checks if there is an error raised while parsing a wrong Ifany instruction
	 */
	@Test
	public void failIfAnyInstructionFour(){
		try{
			tacticsParser.parseTactics(brokenIfAnyStreamFour, random);
					}catch(IllegalArgumentException e){
						return;
					}
		fail("there should be a illegalargument exception raised because tried to parse: \" " + brokenIfAnyStringFour + "\" ." 
				+ "see in the tacticsparsertestclass @before");
	}
	
	/**
	 * checks if there is an error raised while parsing a wrong Ifany instruction
	 */
	@Test
	public void failIfAnyInstructionThree(){
		try{
			tacticsParser.parseTactics(brokenIfAnyStreamThree, random);
					}catch(IllegalArgumentException e){
						return;
					}
		fail("there should be a illegalargument exception raised because tried to parse: \" " + brokenIfAnyStringThree + "\" ." 
				+ "see in the tacticsparsertestclass @before");
	}
	
	/**
	 * checks if there is an error raised while parsing a wrong Ifany instruction
	 */
	@Test
	public void failIfAnyInstructionTwo(){
		try{
			tacticsParser.parseTactics(brokenIfAnyStreamTwo, random);
					}catch(IllegalArgumentException e){
						return;
					}
		fail("there should be a illegalargument exception raised because tried to parse: \" " + brokenIfAnyStringTwo + "\" ." 
				+ "see in the tacticsparsertestclass @before");
	}
	
	/**
	 * checks if there is an error raised while parsing a wrong Ifany instruction
	 */
	@Test
	public void failIfAnyInstructionOne(){
		try{
			tacticsParser.parseTactics(brokenIfAnyStreamOne, random);
					}catch(IllegalArgumentException e){
						return;
					}
		fail("there should be a illegalargument exception raised because tried to parse: \" " + brokenIfAnyStringOne + "\" ." 
				+ "see in the tacticsparsertestclass @before");
	}
	
	/**
	 * checks if there is an error raised while parsing a wrong Ifall instruction
	 */
	@Test
	public void failIfAllInstructionFive(){
		try{
			tacticsParser.parseTactics(brokenIfAllStreamFive, random);
					}catch(IllegalArgumentException e){
						return;
					}
		fail("there should be a illegalargument exception raised because tried to parse: \" " + brokenIfAllStringFive + "\" ." 
				+ "see in the tacticsparsertestclass @before");
	}
	
	/**
	 * checks if there is an error raised while parsing a wrong Ifall instruction
	 */
	@Test
	public void failIfAllInstructionFour(){
		try{
			tacticsParser.parseTactics(brokenIfAllStreamFour, random);
					}catch(IllegalArgumentException e){
						return;
					}
		fail("there should be a illegalargument exception raised because tried to parse: \" " + brokenIfAllStringFour + "\" ." 
				+ "see in the tacticsparsertestclass @before");
	}
	
	/**
	 * checks if there is an error raised while parsing a wrong Ifall instruction
	 */
	@Test
	public void failIfAllInstructionThree(){
		try{
			tacticsParser.parseTactics(brokenIfAllStreamThree, random);
					}catch(IllegalArgumentException e){
						return;
					}
		fail("there should be a illegalargument exception raised because tried to parse: \" " + brokenIfAllStringThree + "\" ." 
				+ "see in the tacticsparsertestclass @before");
	}
	
	/**
	 * checks if there is an error raised while parsing a wrong Ifall instruction
	 */
	@Test
	public void failIfAllInstructionTwo(){
		try{
			tacticsParser.parseTactics(brokenIfAllStreamTwo, random);
					}catch(IllegalArgumentException e){
						return;
					}
		fail("there should be a illegalargument exception raised because tried to parse: \" " + brokenIfAllStringTwo + "\" ." 
				+ "see in the tacticsparsertestclass @before");
	}
	
	/**
	 * checks if there is an error raised while parsing a wrong Ifall instruction
	 */
	@Test
	public void failIfAllInstructionOne(){
		try{
			tacticsParser.parseTactics(brokenIfAllStreamOne, random);
					}catch(IllegalArgumentException e){
						return;
					}
		fail("there should be a illegalargument exception raised because tried to parse: \" " + brokenIfAllStringOne + "\" ." 
				+ "see in the tacticsparsertestclass @before");
	}
	
	/**
	 * checks if there is an error raised while parsing a wrong If instruction
	 */
	@Test
	public void failIfInstructionSix(){
		try{
			tacticsParser.parseTactics(brokenIfStreamSix, random);
					}catch(IllegalArgumentException e){
						return;
					}
		fail("there should be a illegalargument exception raised because tried to parse: \" " + brokenIfStringSix + "\" ." 
				+ "see in the tacticsparsertestclass @before");
	}
	
	/**
	 * checks if there is an error raised while parsing a wrong If instruction
	 */
	@Test
	public void failIfInstructionFive(){
		try{
			tacticsParser.parseTactics(brokenIfStreamFive, random);
					}catch(IllegalArgumentException e){
						return;
					}
		fail("there should be a illegalargument exception raised because tried to parse: \" " + brokenIfStringFive + "\" ." 
				+ "see in the tacticsparsertestclass @before");
	}
	
	/**
	 * checks if there is an error raised while parsing a wrong If instruction
	 */
	@Test
	public void failIfInstructionFour(){
		try{
			tacticsParser.parseTactics(brokenIfStreamFour, random);
					}catch(IllegalArgumentException e){
						return;
					}
		fail("there should be a illegalargument exception raised because tried to parse: \" " + brokenIfStringFour + "\" ." 
				+ "see in the tacticsparsertestclass @before");
	}
	
	/**
	 * checks if there is an error raised while parsing a wrong If instruction
	 */
	@Test
	public void failIfInstructionThree(){
		try{
			tacticsParser.parseTactics(brokenIfStreamThree, random);
					}catch(IllegalArgumentException e){
						return;
					}
		fail("there should be a illegalargument exception raised because tried to parse: \" " + brokenIfStringThree + "\" ." 
				+ "see in the tacticsparsertestclass @before");
	}
	
	/**
	 * checks if there is an error raised while parsing a wrong If instruction
	 */
	@Test
	public void failIfInstructionTwo(){
		try{
			tacticsParser.parseTactics(brokenIfStreamTwo, random);
					}catch(IllegalArgumentException e){
						return;
					}
		fail("there should be a illegalargument exception raised because tried to parse: \" " + brokenIfStringTwo + "\" ." 
				+ "see in the tacticsparsertestclass @before");
	}
	
	/**
	 * checks if there is an error raised while parsing a wrong If instruction
	 */
	@Test
	public void failIfInstructionOne(){
		try{
			tacticsParser.parseTactics(brokenIfStreamOne, random);
					}catch(IllegalArgumentException e){
						return;
					}
		fail("there should be a illegalargument exception raised because tried to parse: \" " + brokenIfStringOne + "\" ." 
				+ "see in the tacticsparsertestclass @before");
	}
	
	/**
	 * checks if there is an error raised while parsing a wrong turn instruction
	 */
	@Test
	public void failTurnInstructionThree(){
		try{
			tacticsParser.parseTactics(brokenTurnStreamThree, random);
					}catch(IllegalArgumentException e){
						return;
					}
		fail("there should be a illegalargument exception raised because tried to parse: \" " + brokenTurnStringThree + "\" ." 
				+ "see in the tacticsparsertestclass @before");
	}
	
	
	/**
	 * checks if there is an error raised while parsing a wrong turn instruction
	 */
	@Test
	public void failTurnInstructionTwo(){
		try{
			tacticsParser.parseTactics(brokenTurnStreamTwo, random);
					}catch(IllegalArgumentException e){
						return;
					}
		fail("there should be a illegalargument exception raised because tried to parse: \" " + brokenTurnStringTwo + "\" ." 
				+ "see in the tacticsparsertestclass @before");
	}
	
	
	/**
	 * checks if there is an error raised while parsing a wrong turn instruction
	 */
	@Test
	public void failTurnInstructionOne(){
		try{
			tacticsParser.parseTactics(brokenTurnStreamOne, random);
					}catch(IllegalArgumentException e){
						return;
					}
		fail("there should be a illegalargument exception raised because tried to parse: \" " + brokenTurnStringOne + "\" ." 
				+ "see in the tacticsparsertestclass @before");
	}
	
	/**
	 * checks if there is an error raised while parsing a wrong flipzero instruction
	 */
	@Test
	public void failFlipzeroInstructionFive(){
		try{
			tacticsParser.parseTactics(brokenFlipzeroStreamFive, random);
					}catch(IllegalArgumentException e){
						return;
					}
		fail("there should be a illegalargument exception raised because tried to parse: \" " + brokenFlipzeroStringFive + "\" ." 
				+ "see in the tacticsparsertestclass @before");
	}
	
	/**
	 * checks if there is an error raised while parsing a wrong flipzero instruction
	 */
	@Test
	public void failFlipzeroInstructionFour(){
		try{
			tacticsParser.parseTactics(brokenFlipzeroStreamFour, random);
					}catch(IllegalArgumentException e){
						return;
					}
		fail("there should be a illegalargument exception raised because tried to parse: \" " + brokenFlipzeroStringFour + "\" ." 
				+ "see in the tacticsparsertestclass @before");
	}

	/**
	 * checks if there is an error raised while parsing a wrong flipzero instruction
	 */
	@Test
	public void failFlipzeroInstructionThree(){
		try{
			tacticsParser.parseTactics(brokenFlipzeroStreamThree, random);
					}catch(IllegalArgumentException e){
						return;
					}
		fail("there should be a illegalargument exception raised because tried to parse: \" " + brokenFlipzeroStringThree + "\" ." 
				+ "see in the tacticsparsertestclass @before");
	}
	
	
	/**
	 * checks if there is an error raised while parsing a wrong flipzero instruction
	 */
	@Test
	public void failFlipzeroInstructionTwo(){
		try{
			tacticsParser.parseTactics(brokenFlipzeroStreamTwo, random);
					}catch(IllegalArgumentException e){
						return;
					}
		fail("there should be a illegalargument exception raised because tried to parse: \" " + brokenFlipzeroStringTwo + "\" ." 
				+ "see in the tacticsparsertestclass @before");
	}
	
	/**
	 * checks if there is an error raised while parsing a wrong flipzero instruction
	 * Test One
	 */
	@Test
	public void failFlipzeroInstructionOne(){
		try{
			tacticsParser.parseTactics(brokenFlipzeroStreamOne, random);
					}catch(IllegalArgumentException e){
						return;
					}
		fail("there should be a illegalargument exception raised because tried to parse: \" " + brokenFlipzeroStringOne + "\" ." 
				+ "see in the tacticsparsertestclass @before");
	}
	
	/**
	 * checks if there is an error raised while parsing a wrong goto instruction
	 */
	@Test
	public void failGotoInstructionTwo(){
		try{
			tacticsParser.parseTactics(brokenGotoStreamTwo, random);
					}catch(IllegalArgumentException e){
						return;
					}
		fail("there should be a illegalargument exception raised because tried to parse: \" " + brokenGotoStringTwo + "\" ." 
				+ "see in the tacticsparsertestclass @before");
	}
	
	/**
	 * checks if there is an error raised while parsing a wrong goto instruction
	 */
	@Test
	public void failGotoInstructionOne(){
		try{
			tacticsParser.parseTactics(brokenGotoStreamOne, random);
					}catch(IllegalArgumentException e){
						return;
					}
		fail("there should be a illegalargument exception raised because tried to parse: \" " + brokenGotoStringOne + "\" ." 
				+ "see in the tacticsparsertestclass @before");
	}
	
	/**
	 * checks if there is an error raised while parsing a wrong pickup instruction
	 * Test Four
	 */
	@Test
	public void failPickupInstructionFour(){
		try{
			tacticsParser.parseTactics(brokenPickupStreamFour, random);
					}catch(IllegalArgumentException e){
						return;
					}
		fail("there should be a illegalargument exception raised because tried to parse: \" " + brokenPickupStringFour + "\" ." 
				+ "see in the tacticsparsertestclass @before");
	}
	
	/**
	 * checks if there is an error raised while parsing a wrong pickup instruction
	 * Test Three
	 */
	@Test
	public void failPickupInstructionThree(){
		try{
			tacticsParser.parseTactics(brokenPickupStreamThree, random);
					}catch(IllegalArgumentException e){
						return;
					}
		fail("there should be a illegalargument exception raised because tried to parse: \" " + brokenPickupStringThree + "\" ." 
				+ "see in the tacticsparsertestclass @before");
	}
	
	
	/**
	 * checks if there is an error raised while parsing a wrong pickup instruction
	 * Test Two
	 */
	@Test
	public void failPickupInstructionTwo(){
		try{
			tacticsParser.parseTactics(brokenPickupStreamTwo, random);
					}catch(IllegalArgumentException e){
						return;
					}
		fail("there should be a illegalargument exception raised because tried to parse: \" " + brokenPickupStringTwo + "\" ." 
				+ "see in the tacticsparsertestclass @before");
	}
	
	
	/**
	 * checks if there is an error raised while parsing a wrong pickup instruction
	 * Test One
	 */
	@Test
	public void failPickupInstructionOne(){
		try{
			tacticsParser.parseTactics(brokenPickupStreamOne, random);
					}catch(IllegalArgumentException e){
						return;
					}
		fail("there should be a illegalargument exception raised because tried to parse: \" " + brokenPickupStringOne + "\" ." 
				+ "see in the tacticsparsertestclass @before");
	}
	
	/**
	 * checks if there is an error raised while parsing a wrong sense instruction
	 */
	@Test
	public void failSenseInstructionOne(){
		try{
			tacticsParser.parseTactics(failStream, random);
					}catch(IllegalArgumentException e){
						return;
					}
		fail("there should be a illegalargument exception raised because tried to parse: \" " + failString + "\" ." 
				+ "see in the tacticsparsertestclass @before");
	}
	
	/**
	 * checks if there is an error raised while parsing a wrong sense instruction
	 */
	@Test
	public void failSenseInstructionTwo(){
		try{
			tacticsParser.parseTactics(brokenSenseStream, random);
					}catch(IllegalArgumentException e){
						return;
					}
		fail("there should be a illegalargument exception raised because tried to parse: \" " + brokenSenseString + "\" ." 
				+ "see in the tacticsparsertestclass @before");
	}
	
	/**
	 * checks if there is an error raised while parsing a wrong move instruction
	 * Test one
	 */
	@Test
	public void failMoveInstructionOne(){
		try{
			tacticsParser.parseTactics(brokenMoveStreamOne, random);
					}catch(IllegalArgumentException e){
						return;
					}
		fail("there should be a illegalargument exception raised because tried to parse: \" " + brokenMoveStringOne + "\" ." 
				+ "see in the tacticsparsertestclass @before");
	}
	
	/**
	 * checks if there is an error raised while parsing a wrong move instruction
	 * Test two.
	 */
	@Test
	public void failMoveInstructionTwo(){
		try{
			tacticsParser.parseTactics(brokenMoveStreamTwo, random);
					}catch(IllegalArgumentException e){
						return;
					}
			fail("there should be a illegalargument exception raised because tried to parse: \" " + brokenMoveStringTwo + "\" ." 
					+ "see in the tacticsparsertestclass @before");
	}
	
	/**
	 * checks if there are errors while trying to parse the brokenStream
	 */
	@Test
	public void failInstruction(){
		try{
		tacticsParser.parseTactics(brokenStream, random);
				}catch(IllegalArgumentException e){
					return;
				}
		fail("there should be a illegalargument exception raised (parsed the broken string)"
				+ "see in the tacticsparsertestclass @before");
	}
	
	
	/**
	 * checks if the array has the correct length
	 */
	@Test
	public void bigInstructionSizeParseTest(){
		Instruction[] instrArray = tacticsParser.parseTactics(stream, random);
		
		assertTrue("" + instrArray.length, instrArray.length == 35);
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
		
		assertTrue("The 27th Instruction should be a RepairInstruction",
				instrArray[27] instanceof RepairInstruction);
		
		assertTrue("The 28th Instruction should be a IfAnyInstruction",
				instrArray[28] instanceof IfAnyInstruction);
		
		assertTrue("The 29th Instruction should be a RefreshInstruction",
				instrArray[29] instanceof RefreshInstruction);
		
		assertTrue("The 30th Instruction should be a MarkInstruction",
				instrArray[30] instanceof MarkInstruction);
		
		assertTrue("The 31st Instruction should be a MarkInstruction",
				instrArray[31] instanceof MarkInstruction);
		
		assertTrue("The 32snd Instruction should be a UnmarkInstruction",
				instrArray[32] instanceof UnmarkInstruction);
		
	}
	
	/**
	 * checks if the the Markinstruction marks on the right tile
	 */
	@Test
	public void checkMarkInstruction(){
		Instruction[] instrArray = tacticsParser.parseTactics(stream, random);
		
		Instruction instr = instrArray[30];
		
		assertTrue("the instruction 30 should be a MarkInstruction",
				instr instanceof MarkInstruction);
		
		MarkInstruction markInstr = (MarkInstruction) instr;
		
		assertTrue(markInstr.getType() == 0);
	}
	
	/**
	 * checks if the MarkInstruction marks on the right tile
	 */
	/**
	 * checks if the the Markinstruction marks on the right tile
	 */
	@Test
	public void checkMarkTwiceInstruction(){
		Instruction[] instrArray = tacticsParser.parseTactics(stream, random);
		
		Instruction instr = instrArray[31];
		
		assertTrue("the instruction 30 should be a MarkInstruction",
				instr instanceof MarkInstruction);
		
		MarkInstruction markInstr = (MarkInstruction) instr;
		
		assertTrue(markInstr.getType() == 2);
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
	 * checks if the ifinstr is parsed correctly
	 * also checks if the conditions are evaluated correctly
	 * also checks if the elseaddr is set correctly
	 */
	@Test
	public void checkIfAnyInstructionParseTest(){
		Instruction[] instrArray = tacticsParser.parseTactics(stream, random);
		//get the expressions
		Instruction instr = instrArray[28];
		
		//check if it is the correct Instruction
		assertTrue("the instruction 28 should be a IfAnyInstruction",
				instr instanceof IfAnyInstruction);
		
		IfAnyInstruction ifAnyinstr = (IfAnyInstruction) instr;
		Expression[] exp = ifAnyinstr.getConditions();
		
		//check if the IfAllInstruction got 2 Expression
		assertTrue("length of the array should be 2",
				exp.length == 2);
		
		//check if the expressions are correct
		assertTrue("the secound expression should be a == exp"
				,exp[1] instanceof EqualOperator);
		
		
		//check if the expressions are evaluated correctly
		int ret1 = exp[0].evaluate(ship.getRegisters());
		int ret2 = exp[1].evaluate(ship.getRegisters());
		
		assertTrue("The first exp should evaluate to false",
				ret1 == 0);
		assertTrue("The secound exp should evaluate to true",
				ret2 == 1);
		
		//checks if elseAddr is set correctly
		assertTrue("The elseAddr should be 29",
				ifAnyinstr.getElsePC() == 29);
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
