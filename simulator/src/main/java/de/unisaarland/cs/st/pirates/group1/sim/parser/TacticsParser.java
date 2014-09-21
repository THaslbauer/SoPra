package de.unisaarland.cs.st.pirates.group1.sim.parser;

import java.io.InputStream;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

import de.unisaarland.cs.st.pirates.group1.sim.logger.ExtendedLogWriter;
import de.unisaarland.cs.st.pirates.group1.sim.logic.expression.EqualOperator;
import de.unisaarland.cs.st.pirates.group1.sim.logic.expression.Expression;
import de.unisaarland.cs.st.pirates.group1.sim.logic.expression.GreaterOperator;
import de.unisaarland.cs.st.pirates.group1.sim.logic.expression.LessOperator;
import de.unisaarland.cs.st.pirates.group1.sim.logic.expression.Literal;
import de.unisaarland.cs.st.pirates.group1.sim.logic.expression.RegisterCall;
import de.unisaarland.cs.st.pirates.group1.sim.logic.expression.UnequalOperator;
import de.unisaarland.cs.st.pirates.group1.sim.logic.instruction.Instruction;
import de.unisaarland.cs.st.pirates.group1.sim.logic.instruction.elseInstructions.FlipZeroInstruction;
import de.unisaarland.cs.st.pirates.group1.sim.logic.instruction.elseInstructions.IfAllInstruction;
import de.unisaarland.cs.st.pirates.group1.sim.logic.instruction.elseInstructions.IfAnyInstruction;
import de.unisaarland.cs.st.pirates.group1.sim.logic.instruction.elseInstructions.IfInstruction;
import de.unisaarland.cs.st.pirates.group1.sim.logic.instruction.elseInstructions.MoveInstruction;
import de.unisaarland.cs.st.pirates.group1.sim.logic.instruction.elseInstructions.PickupInstruction;
import de.unisaarland.cs.st.pirates.group1.sim.logic.instruction.elseInstructions.RefreshInstruction;
import de.unisaarland.cs.st.pirates.group1.sim.logic.instruction.normalInstructions.DropInstruction;
import de.unisaarland.cs.st.pirates.group1.sim.logic.instruction.normalInstructions.GotoInstruction;
import de.unisaarland.cs.st.pirates.group1.sim.logic.instruction.normalInstructions.MarkInstruction;
import de.unisaarland.cs.st.pirates.group1.sim.logic.instruction.normalInstructions.SenseInstruction;
import de.unisaarland.cs.st.pirates.group1.sim.logic.instruction.normalInstructions.TurnInstruction;
import de.unisaarland.cs.st.pirates.group1.sim.util.Direction;

/**
 * 
 * This is the parser for the tactics of each faction.
 * 
 * @author Kerstin
 * @version Version 1.0
 */
public class TacticsParser {

	private ExtendedLogWriter logger;
	private Random random;
	private LinkedList<Instruction> ins;
	
	public TacticsParser(ExtendedLogWriter logger){		
		this.logger = logger;
		ins = new LinkedList<Instruction>();
	}
	
	/**
	 * This method parses the tactics file for each faction and thereby tests if types are consistent.
	 * @param input
	 * @param random
	 */
	public Instruction[] parseTactics(InputStream input, Random random){
		
		//convert the input to string
		String tactics_string = this.convertStreamToString(input);
		this.random = random;
	
		//split the input string into an array of instructions
		String[] tactics_string_array = tactics_string.split("\n");
		
		for(int i = 0; i< tactics_string_array.length; i++){
			
			//this int is needed to find out how many arguments there are
			int size;
			
			String[] instruction_array = tactics_string_array[i].split(" ");
			
			size = instruction_array.length;
			
			try{
				int expr_num = instruction_array.length;
				
				String name = instruction_array[0];
				
				switch(name){

				case "turn":
					ins.add(this.makeTurnInstruction(instruction_array, size));
					break;
					
				case "mark":
					ins.add(this.makeMarkInstruction(instruction_array, size));
					break;
					
				case "unmark":
					ins.add(this.makeUnmarkInstruction(instruction_array, size));
					break;
					
				case "move":
					ins.add(this.makeMoveInstruction(instruction_array, size));
					break;
					
				case "pickup":
					ins.add(this.makePickupInstruction(instruction_array, size));
					break;
					
				case "drop":
					ins.add(this.makeDropInstruction(instruction_array, size));
					break;
					
				case "flipzero":
					ins.add(this.makeFlipzeroInstruction(instruction_array, size));
					break;
					
				case "goto":
					ins.add(this.makeGotoInstruction(instruction_array, size));
					break;
					
				case "sense":
					ins.add(this.makeSenseInstruction(instruction_array, size));
					break;
					
				case "if":
					ins.add(this.makeIfInstruction(instruction_array, size));
					break;
				
				case "ifall":
					ins.add(this.makeIfallInstruction(instruction_array, size));
					break;
				
				case "ifany":
					ins.add(this.makeIfanyInstruction(instruction_array, size));
					break;
					
				case "refresh":
					ins.add(this.makeRefreshInstruction(instruction_array, size));
					break;
					
				default:
					throw new IllegalArgumentException("This instruction does not exist");
				}
			}
			
			//TODO: find out which exceptions are necessary
			catch(Exception e){
				throw new IllegalArgumentException("Something with this instruction is wrong");
			}
			
			
			
		}
		
		return ins.toArray(new Instruction[0]);
		
	}
	
	private String convertStreamToString(InputStream input){
		try(Scanner scan = new Scanner(input).useDelimiter("\\A")){
			return scan.hasNext() ? scan.next() : "";
		}
	}
	
	public Instruction makeTurnInstruction(String[] instruction, int size){
		
		if (size != 2){
			throw new IllegalArgumentException("A turn instruction must consist of two parts");
		}
		
		
		if(!(instruction[1].equals("left")) && !(instruction[1].equals("right"))){
			
			throw new IllegalArgumentException("A turn instruction cannot have another argument then left or right");
		}
		
		else{
			if(instruction[1].equals("left")){
				 return new TurnInstruction(logger, true);
			}
			
			else{
				 return new TurnInstruction(logger, false);
			}
		}
	}
	
	public Instruction makeMarkInstruction(String[] instruction, int size){
		
		
		if(size != 2){
			throw new IllegalArgumentException("A mark instruction must consist of two parts");
		}
		
		if(instruction[1]. equals("0") || instruction[1]. equals("1") ||
				instruction[1].equals("2") || instruction[1].equals("3") || 
				instruction[1].equals("4") || instruction[1].equals("5")){
			
			return new MarkInstruction(logger, Integer.parseInt(instruction[1]));
			
		}
		
		else{
			throw new IllegalArgumentException("A mark instruction cannot have an int value less than zero or greater than 5");
		}

	}
	
	public Instruction makeUnmarkInstruction(String[] instruction, int size){
		
		if(size != 2){
			throw new IllegalArgumentException("A unmark instruction must consist of two parts");
		}
		
		if(instruction[1]. equals("0") || instruction[1]. equals("1") ||
				instruction[1].equals("2") || instruction[1].equals("3") || 
				instruction[1].equals("4") || instruction[1].equals("5")){
			
			return new MarkInstruction(logger, Integer.parseInt(instruction[1]));
			
		}
		
		else{
			throw new IllegalArgumentException("A unmark instruction cannot have an int value less than zero or greater than 5");
		}

	}
	
	public Instruction makeMoveInstruction(String[] instruction, int size){
		
		if(size != 3){
			throw new IllegalArgumentException("A move instruction consists of 3 parts");
		}
		
		if(instruction[1].equals("else") && Integer.parseInt(instruction[2]) >= 0 && Integer.parseInt(instruction[2]) <= 1999){
			return new MoveInstruction(logger, Integer.parseInt(instruction[2]));
		}
		
		else{
			throw new IllegalArgumentException("Move instruction wrong");
		}
	}
	
	public Instruction makePickupInstruction(String[] instruction, int size){
		
		if(size != 4){
			throw new IllegalArgumentException("Pickup instruction consists of 4 parts");
		}
		
		if(!(this.isCorrectAddress(Integer.parseInt(instruction[3]))) || !(this.isCorrectSenseDir(Integer.parseInt(instruction[1])))){
			throw new IllegalArgumentException("Pickup instruction is wrong (address or sensedirection) ");
		}
		
		else{
			return new PickupInstruction(logger, Integer.parseInt(instruction[3]), this.changeIntToDirection(Integer.parseInt(instruction[1])));
		}
	}
	
	public Instruction makeDropInstruction(String[] instruction, int size){
		
		if(size != 1){
			throw new IllegalArgumentException("A drop instruction consists only of one part");
		}
		
		else{
			return new DropInstruction(logger);
		}
	}
	
	public Instruction makeFlipzeroInstruction(String[] instruction, int size){
		
		if (size != 4){
			throw new IllegalArgumentException("Flipzero instruction consists of 4 parts");
		}
		
		//TODO: ask what to do with an integer that is too big
		if(Integer.parseInt(instruction[1]) < 1 ||  !(this.isCorrectAddress(Integer.parseInt(instruction[3])))){
			
			throw new IllegalArgumentException("Flipzero: wrong seed or too big address");
		}
		
		else{
			return new FlipZeroInstruction(logger, Integer.parseInt(instruction[3]), random, Integer.parseInt(instruction[1]));
		}
		
	}
	
	public Instruction makeGotoInstruction(String[] instruction, int size){
		
		if (size != 2){
			throw new IllegalArgumentException("A goto instruction consists of two parts");
		}
		
		if(!(this.isCorrectAddress(Integer.parseInt(instruction[1])))){
			throw new IllegalArgumentException("Illegal PC given");
		}
		
		else{
			return new GotoInstruction(logger, Integer.parseInt(instruction[1]));
		}
	}
	
	public Instruction makeSenseInstruction(String[] instruction, int size){
		
		if( size != 2){
			throw new IllegalArgumentException("A sense instruction must consist of two parts");
		}
		
		if(this.isCorrectSenseDir(Integer.parseInt(instruction[1]))){
			
			return new SenseInstruction(logger, this.changeIntToDirection(Integer.parseInt(instruction[1])));
		}
		
		else{
			throw new IllegalArgumentException("Direction must be between 0 and 6");
		}
	}
	
	public Instruction makeIfInstruction(String[] instruction, int size){
		
		if(size != 4){
			
			throw new IllegalArgumentException("IfInstruction consists of 4 parts");
		}
		
		//Produce an expression 
		Expression exp = this.produceExpression(instruction[1]);
		
		//when type mismatch occurs null is given back by the method produceExpression
		if(exp == null){
			throw new IllegalArgumentException("Type mismatch in if instruction occured");
		}
		
		else{
			return new IfInstruction(logger, Integer.parseInt(instruction[3]), exp);
		}
	}
	
	
	public Instruction makeIfallInstruction(String[] instruction, int size){
		
		if(size <4){
			throw new IllegalArgumentException("An ifall instruction awaits at least 4 arguments");
		}
	
		
		int diff = size - 3;
		
		Expression[] exps = new Expression[diff];
		
		for (int i = 0; i< diff; i++){
			
			//TODO: test if null is given back
			exps[i] = this.produceExpression(instruction[i+1]);
		}
		
		return new IfAllInstruction(logger, Integer.parseInt(instruction[size-1]) , exps);
	}
	
	public Instruction makeIfanyInstruction(String[] instruction, int size){
		
		if(size <4){
			throw new IllegalArgumentException("An ifany instruction awaits at least 4 arguments");
		}
	
		
		int diff = size - 3;
		
		Expression[] exps = new Expression[diff];
		
		for (int i = 0; i< diff; i++){
			
			exps[i] = this.produceExpression(instruction[i+1]);
		}
		
		return new IfAnyInstruction(logger, Integer.parseInt(instruction[size-1]) , exps);

	}
	
	public Instruction makeRefreshInstruction(String[] instruction, int size){
		
		if(size != 4){
			throw new IllegalArgumentException("Refresh instruction consists of 4 parts");
		}
		
		if(!(this.isCorrectAddress(Integer.parseInt(instruction[3]))) || !(this.isCorrectSenseDir(Integer.parseInt(instruction[1])))){
			throw new IllegalArgumentException("Refresh Instruction:Wrong integer value sense direction or address");
		}
		
		else{
			return new RefreshInstruction(logger, Integer.parseInt(instruction[3]), this.changeIntToDirection(Integer.parseInt(instruction[1])));
		}
	}
	
	private Direction changeIntToDirection(int i){
		
		switch(i){
		case 0:
			return Direction.D0;
		case 1:
			return Direction.D1;
		case 2:
			return Direction.D2;
		case 3:
			return Direction.D3;
		case 4:
			return Direction.D4;
		case 5:
			return Direction.D5;
		case 6:
			return Direction.D6;
		default:
			throw new IllegalArgumentException("A direction must be between 0 and 6");
	
		}
	}
	
	private boolean isCorrectAddress(int i){
		
		if (i< 0 || i> 1999){
			return false;
		}
		
		else{
			return true;
		}
	}
	
	private boolean isCorrectSenseDir(int i){
		
		if (i< 0 || i> 6){
			return false;
		}
		
		else{
			return true;
		}
	}
	
	private Expression produceExpression(String s){
		
		if (this.isBool_register(s)){
			return new RegisterCall(this.changeRegisterToInt(s));
		}
		
		String[] comparison = s.split("==");
		
		if(comparison.length == 2){
			if (comparison[0].equals("sense_celltype")&& this.isCell_type(comparison[1])){
				
				return new EqualOperator(new RegisterCall(this.changeRegisterToInt(comparison[0])), new Literal(this.changeCell_typeToInt(comparison[1])));
			}
			
			if(comparison[0].equals("sense_shiptype") && this.isShip_type(comparison[1])){
				
				return new EqualOperator(new RegisterCall(this.changeRegisterToInt(comparison[0])), new Literal(this.changeShip_typeToInt(comparison[1])));
			}
			
			if(this.isInt_register(comparison[0]) && this.isInt_register(comparison[1])){
				
				return new EqualOperator(new RegisterCall(this.changeRegisterToInt(comparison[0])), new RegisterCall(this.changeRegisterToInt(comparison[1])));
			}
			
			if(this.isInt_register(comparison[0]) && this.isInt(comparison[1])){
				
				return new EqualOperator(new RegisterCall(this.changeRegisterToInt(comparison[0])), new Literal(Integer.parseInt(comparison[1])));
			}
			
		}
		
		if(comparison.length == 1){
			
			comparison = comparison[0].split("!=");
			
			if(comparison.length == 2){
				
				if (comparison[0].equals("sense_celltype")&& this.isCell_type(comparison[1])){
					
					return new UnequalOperator(new RegisterCall(this.changeRegisterToInt(comparison[0])), new Literal(this.changeCell_typeToInt(comparison[1])));
				}
				
				if(comparison[0].equals("sense_shiptype") && this.isShip_type(comparison[1])){
					
					return new UnequalOperator(new RegisterCall(this.changeRegisterToInt(comparison[0])), new Literal(this.changeShip_typeToInt(comparison[1])));
				}
				
				if(this.isInt_register(comparison[0]) && this.isInt_register(comparison[1])){
					
					return new UnequalOperator(new RegisterCall(this.changeRegisterToInt(comparison[0])), new RegisterCall(this.changeRegisterToInt(comparison[1])));
				}
				
				if(this.isInt_register(comparison[0]) && this.isInt(comparison[1])){
					
					return new UnequalOperator(new RegisterCall(this.changeRegisterToInt(comparison[0])), new Literal(Integer.parseInt(comparison[1])));
				}
			}
		}
		
		if(comparison.length == 1){
			
			comparison = comparison[0].split("<");
			
			if(this.isInt_register(comparison[0]) && this.isInt_register(comparison[1])){
				
				return new LessOperator(new RegisterCall(this.changeRegisterToInt(comparison[0])), new RegisterCall(this.changeRegisterToInt(comparison[1])));
			}
			
			if(this.isInt_register(comparison[0]) && this.isInt(comparison[1])){
				
				return new LessOperator(new RegisterCall(this.changeRegisterToInt(comparison[0])), new Literal(Integer.parseInt(comparison[1])));
			}
			
		}
		
		if(comparison.length == 1){
			
			comparison = comparison[0].split(">");
			
			if(this.isInt_register(comparison[0]) && this.isInt_register(comparison[1])){
				
				return new GreaterOperator(new RegisterCall(this.changeRegisterToInt(comparison[0])), new RegisterCall(this.changeRegisterToInt(comparison[1])));
			}
			
			if(this.isInt_register(comparison[0]) && this.isInt(comparison[1])){
				
				return new GreaterOperator(new RegisterCall(this.changeRegisterToInt(comparison[0])), new Literal(Integer.parseInt(comparison[1])));
			}
			
		}
		
		
		//This is returned when there is a type mismatch to produce an expression
		return null;
		
		
		
	}
	
	
	private boolean isInt(String s){
		try{
			Integer.parseInt(s);
		}
		
		catch(Exception e){
			return false;
		}
		
		return true;
	}
	
	private boolean isBool_register(String s){
		
		switch(s){
		case("sense_treasure"):
			return true;
		case("sense_marker0"):
			return true;
		case("sense_marker1"):
			return true;
		case("sensemarker2"):
			return true;
		case("sensemarker3"):
			return true;
		case("sensemarker4"):
			return true;
		case("sensemarker5"):
			return true;
		case("sense_enemymarker"):
			return true;
		case("sense_shiploaded"):
			return true;
		case("sense_supply"):
			return true;
		case("!sense_treasure"):
			return true;
		case("!sense_marker0"):
			return true;
		case("!sense_marker1"):
			return true;
		case("!sensemarker2"):
			return true;
		case("!sensemarker3"):
			return true;
		case("!sensemarker4"):
			return true;
		case("!sensemarker5"):
			return true;
		case("!sense_enemymarker"):
			return true;
		case("!sense_shiploaded"):
			return true;
		case("!sense_supply"):
			return true;
		default:
			return false;
		}
			
	}
	
	private boolean isCell_type(String s){
		
		switch(s){
		case("island"):
			return true;
		case("home"):
			return true;
		case("enemyhome"):
			return true;
		case("empty"):
			return true;
		default:
			return false;
		}
	}
	
	private boolean isShip_type(String s){
		
		switch(s){
		case("friend"):
			return true;
		case("enemy"):
			return true;
		default:
			return false;
		}
	}
	
	private boolean isInt_register(String s){
		
		switch(s){
		case("ship_direction"):
			return true;
		case("ship_load"):
			return true;
		case("ship_moral"):
			return true;
		case("ship_conition"):
			return true;
		case("sense_shipdirection"):
			return true;
		case("sense_shipcondition"): //TODO: find out how this looks like
			return true;
		default:
			return false;
		
		}
	}
	
	private int changeCell_typeToInt(String s){
		
		switch(s){
		case("island"):
			return 0;
		case("home"):
			return 1;
		case("enemyhome"):
			return 2;
		case("empty"):
			return 3;
		default:
			throw new IllegalStateException("Don't know how this could happen");
		}
	}
	
	private int changeShip_typeToInt(String s){
		
		switch(s){
		case("friend"):
			return 0;
		case("enemy"):
			return 1;
		default:
			throw new IllegalStateException("This should never happen");
		}
	}
	
	private int changeRegisterToInt(String s){
		
		switch(s){
		case("sense_celltype"):
			return 0;
		case("sense_supply"):
			return 1;
		case("sense_treasure"):
			return 2;
		case("sense_marker0"):
			return 3;
		case("sense_marker1"):
			return 4;
		case("sense_marker2"):
			return 5;
		case("sense_marker3"):
			return 6;
		case("sense_marker4"):
			return 7;
		case("sense_marker5"):
			return 8;
		case("sense_enemymarker"):
			return 9;
		case("sense_shiptype"):
			return 10;
		case("sense_shipdirection"):
			return 11;
		case("sense_shiploaded"):
			return 12;
		case("sense_shipcondition"):
			return 13;
		case("ship_direction"):
			return 14;
		case("ship_load"):
			return 15;
		case("ship_moral"):
			return 16;
		case("ship_condition"):
			return 17;
		default:
			throw new IllegalStateException("This should never happen");
		
				
		}
	}
}
