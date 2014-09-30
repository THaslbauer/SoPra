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
import de.unisaarland.cs.st.pirates.group1.sim.logic.expression.NotOperator;
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
import de.unisaarland.cs.st.pirates.group1.sim.logic.instruction.elseInstructions.RepairInstruction;
import de.unisaarland.cs.st.pirates.group1.sim.logic.instruction.normalInstructions.DropInstruction;
import de.unisaarland.cs.st.pirates.group1.sim.logic.instruction.normalInstructions.GotoInstruction;
import de.unisaarland.cs.st.pirates.group1.sim.logic.instruction.normalInstructions.MarkInstruction;
import de.unisaarland.cs.st.pirates.group1.sim.logic.instruction.normalInstructions.SenseInstruction;
import de.unisaarland.cs.st.pirates.group1.sim.logic.instruction.normalInstructions.TurnInstruction;
import de.unisaarland.cs.st.pirates.group1.sim.logic.instruction.normalInstructions.UnmarkInstruction;
import de.unisaarland.cs.st.pirates.group1.sim.util.CellType;
import de.unisaarland.cs.st.pirates.group1.sim.util.Direction;

/**
 * 
 * This is the parser for the tactics of each faction.
 * 
 * @author Kerstin
 * @version Version 3.0
 */
public class TacticsParser {

	private ExtendedLogWriter logger;
	private Random random;
	private LinkedList<Instruction> ins;
	
	/**
	 * 
	 * @param logger The logger to use
	 */
	public TacticsParser(ExtendedLogWriter logger){		
		this.logger = logger;
		ins = new LinkedList<Instruction>();
	}
	
	/**
	 * This method parses the tactics file for each faction and thereby tests if types are consistent.
	 * It mainly converts the input stream to a string and uses split(regex) to finally 
	 * divide the string into string arrays where each element represents a part of an instruction.
	 * 
	 * @param input The input stream to use
	 * @param random The random object for flipzero instruction
	 */
	public Instruction[] parseTactics(InputStream input, Random random){
		
		//convert the input to string
		String tactics_string = this.convertStreamToString(input);
		this.random = random;
		ins = new LinkedList<Instruction>();
	
		//split the input string so that the comments vanish
	
		//split the input string into an array of instructions
		String[] tactics_string_array = tactics_string.split("\n");
		
		for(int i = 0; i< tactics_string_array.length; i++){
			
			//this int is needed to find out how many arguments there are
			int size;
			String[] in_between = tactics_string_array[i].split(";");
			String deletedWhitespace = in_between[0].trim();
			String[] instruction_array = deletedWhitespace.split(" ");
			
			size = instruction_array.length;
			
			try{
				int expr_num = instruction_array.length;
				
				String name = instruction_array[0].toLowerCase();
				
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
					if(this.equalsElse(instruction_array[1])){
						ins.add(this.makeMoveInstruction(instruction_array, size));
						break;
					}
					else{
						throw new IllegalArgumentException("Move instruction: else was misspelled or in wrong place " + instruction_array[1]);
					}
					
				case "pickup":
					if(this.equalsElse(instruction_array[2])){
						ins.add(this.makePickupInstruction(instruction_array, size));
						break;
					}
					else{
						throw new IllegalArgumentException("Pickup instruction: else was misspelled or wrong expression there " + instruction_array[2]);
					}
					
				case "drop":
					ins.add(this.makeDropInstruction(instruction_array, size));
					break;
					
				case "flipzero":
					if(this.equalsElse(instruction_array[2])){
						ins.add(this.makeFlipzeroInstruction(instruction_array, size));
						break;
					}
					else{
						throw new IllegalArgumentException("Flipzero instruction: else was misspelled or wrong expression there " + instruction_array[2]);
					}
					
				case "goto":
					ins.add(this.makeGotoInstruction(instruction_array, size));
					break;
					
				case "sense":
					ins.add(this.makeSenseInstruction(instruction_array, size));
					break;
					
				case "if":
					if(this.equalsElse(instruction_array[2])){
						ins.add(this.makeIfInstruction(instruction_array, size));
						break;
					}
					else{
						throw new IllegalArgumentException("If instruction: else was misspelled or wrong expression there " + instruction_array[2]);
					}
				
				case "ifall":
					if(this.equalsElse(instruction_array[size-2])){
						ins.add(this.makeIfallInstruction(instruction_array, size));
						break;
					}
					else{
						throw new IllegalArgumentException("Ifall instruction: else was misspelled or wrong expression there " + instruction_array[size-2]);
					}
				
				case "ifany":
					if(this.equalsElse(instruction_array[size-2])){
						ins.add(this.makeIfanyInstruction(instruction_array, size));
						break;
					}
					else{
						throw new IllegalArgumentException("Ifany instruction: else was misspelled or wrong expression there " + instruction_array[size-2]);
					}
					
				case "refresh":
					if(this.equalsElse(instruction_array[2])){
						ins.add(this.makeRefreshInstruction(instruction_array, size));
						break;
					}
					else{
						throw new IllegalArgumentException("Refresh instruction: else was misspelled or wrong expression there " + instruction_array[2]);
					}
					
				case "repair":
					if(this.equalsElse(instruction_array[1])){
						ins.add(this.makeRepairInstruction(instruction_array, size));
						break;
					}
					else{
						throw new IllegalArgumentException("Repair instruction: else was misspelled or wrong expression there " + instruction_array[1]);
					}
					
				default:
					throw new IllegalArgumentException("This instruction does not exist " + name);
				}
			}
			
			//TODO: find out which exceptions are necessary
			catch(Exception e){
			throw new IllegalArgumentException(e.toString());
		}
			
			
			
		}
		
		return ins.toArray(new Instruction[0]);
		
	}
	
	/**
	 * This methods converts an input stream into a string.
	 * @param input The input stream which should be converted to a string
	 * @return the converted input stream as a string
	 */
	private String convertStreamToString(InputStream input){
		try(Scanner scan = new Scanner(input).useDelimiter("\\A")){
			return scan.hasNext() ? scan.next() : "";
		}
	}
	
	/**
	 * This method creates a TurnInstruction.
	 * @throws IllegalArgumentException if size!=2 or the word after turn does not equal "right" or "left"
	 * @param instruction The string array which represents the whole instruction
	 * @param size The size of this string array
	 * @return A TurnInstruction
	 */
	public Instruction makeTurnInstruction(String[] instruction, int size) throws IllegalArgumentException{
		
		if (size != 2){
			throw new IllegalArgumentException("A turn instruction must consist of two parts but was " + size);
		}
		
		
		if(!(instruction[1].toLowerCase().equals("left")) && !(instruction[1].toLowerCase().equals("right"))){
			
			throw new IllegalArgumentException("A turn instruction cannot have another argument than left or right but it was " + instruction[1]);
		}
		
		else{
			if(instruction[1].toLowerCase().equals("left")){
				 return new TurnInstruction(logger, true);
			}
			
			else{
				 return new TurnInstruction(logger, false);
			}
		}
	}
	
	/**
	 * This method creates a MarkInstruction
	 * @param instruction The string array which represents the whole instruction.
	 * @param size The size of this string array
	 * @return MarkInstruction
	 * @throws IllegalArgumentException if size!=2 or a wrong int value is given.
	 */
	public Instruction makeMarkInstruction(String[] instruction, int size) throws IllegalArgumentException{
		
		
		if(size != 2){
			throw new IllegalArgumentException("A mark instruction must consist of two parts");
		}
		
		if(instruction[1]. equals("0") || instruction[1]. equals("1") ||
				instruction[1].equals("2") || instruction[1].equals("3") || 
				instruction[1].equals("4") || instruction[1].equals("5")){
			
			return new MarkInstruction(logger, Integer.parseInt(instruction[1]));
			
		}
		
		else{
			throw new IllegalArgumentException("A mark instruction cannot have an int value less than zero or greater than 5 " + instruction[1]);
		}

	}
	
	/**
	 * This method creates a UnmarkInstruction
	 * @param instruction The string array which represents the whole instruction
	 * @param size The size of this string array
	 * @return UnmarkInstruction
	 * @throws IllegalArgumentException if size!=2 or wrong int value is given
	 */
	public Instruction makeUnmarkInstruction(String[] instruction, int size) throws IllegalArgumentException{
		
		if(size != 2){
			throw new IllegalArgumentException("A unmark instruction must consist of two parts");
		}
		
		if(instruction[1]. equals("0") || instruction[1]. equals("1") ||
				instruction[1].equals("2") || instruction[1].equals("3") || 
				instruction[1].equals("4") || instruction[1].equals("5")){
			
			return new UnmarkInstruction(logger, Integer.parseInt(instruction[1]));
			
		}
		
		else{
			throw new IllegalArgumentException("A unmark instruction cannot have an int value less than zero or greater than 5: " + instruction[1]);
		}

	}
	
	/**
	 * This method creates a RepairInstruction
	 * @param instruction The string array which represents the whole instruction
	 * @param size The size of this string array
	 * @return RepairInstruction
	 * @throws IllegalArgumentException if size!=3 or pc <0 or pc>1999
	 */
	public Instruction makeRepairInstruction(String[] instruction, int size) throws IllegalArgumentException{
		
		if(size!= 3){
			throw new IllegalArgumentException("A repair instruction consists of 3 parts but was: " + size);
		}
		
		if(!(this.isCorrectPC(Integer.parseInt(instruction[2])))){
			throw new IllegalArgumentException("RepairInstruction: wrong PC: " + instruction[2]);
		}
		
		
		else{
			return new RepairInstruction(logger, Integer.parseInt(instruction[2]));
		}
	}
	
	
	/**
	 * This method creates a MoveInstruction
	 * @param instruction The string array which represents the whole instruction
	 * @param size The size of this string array
	 * @return MoveInstruction
	 * @throws IllegalArgumentException if size!=3 
	 */
	public Instruction makeMoveInstruction(String[] instruction, int size) throws IllegalArgumentException{
		
		if(size != 3 || !(this.isCorrectPC(Integer.parseInt(instruction[2])))){
			throw new IllegalArgumentException("A move instruction consists of 3 parts or Illegal PC " + "parts: " +  size + " PC: " + instruction[2]);
		}
	
		
		return new MoveInstruction(logger, Integer.parseInt(instruction[2]));
		
		
	
	}
	
	/**
	 * This method creates a PickupInstruction
	 * @param instruction The string array which represents the whole instruction
	 * @param size The size of this string array
	 * @return PickupInstruction
	 * @throws IllegalArgumentException if size !=4 or incorrect  sense direction given
	 */
	public Instruction makePickupInstruction(String[] instruction, int size) throws IllegalArgumentException{
		
		if(size != 4){
			throw new IllegalArgumentException("Pickup instruction consists of 4 parts but was " + size);
		}
		
		if(!(this.isCorrectPC(Integer.parseInt(instruction[3])))){
			throw new IllegalArgumentException("Pickup instruction wrong PC: " + instruction[3]);
		}
		
		if( !(this.isCorrectSenseDir(Integer.parseInt(instruction[1])))){
			throw new IllegalArgumentException("Pickup instruction is wrong (sensedirection) " + instruction[1]);
		}
		
		else{
			return new PickupInstruction(logger, Integer.parseInt(instruction[3]), this.changeIntToDirection(Integer.parseInt(instruction[1])));
		}
	}
	
	/**
	 * This method creates DropInstruction
	 * @param instruction The string array which represents the whole instruction
	 * @param size The size of this string array
	 * @return DropInstruction
	 * @throws IllegalArgumentException if size!=1
	 */
	public Instruction makeDropInstruction(String[] instruction, int size) throws IllegalArgumentException{
		
		if(size != 1){
			throw new IllegalArgumentException("A drop instruction consists only of one part but was " + size);
		}
		
		else{
			return new DropInstruction(logger);
		}
	}
	
	/**
	 * This method creates a FlipzeroInstruction
	 * @param instruction The string array which represents the whole instruction
	 * @param size The size of this string array
	 * @return FlipzeroInstruction
	 * @throws IllegalArgumentException if size!=4 or seed is less than 1
	 */
	public Instruction makeFlipzeroInstruction(String[] instruction, int size) throws IllegalArgumentException{
		
		if (size != 4){
			throw new IllegalArgumentException("Flipzero instruction consists of 4 parts but was " + size);
		}
		
		if(!(this.isCorrectPC(Integer.parseInt(instruction[3])))){
			throw new IllegalArgumentException("Flipzero instruction: wrong PC: " + instruction[3]);
		}
		
		//TODO: ask what to do with an integer that is too big
		if(Integer.parseInt(instruction[1]) < 1 ){
			
			throw new IllegalArgumentException("Flipzero: wrong seed: " + instruction[1]);
		}
		
		else{
			return new FlipZeroInstruction(logger, Integer.parseInt(instruction[3]), random, Integer.parseInt(instruction[1]));
		}
		
	}
	
	/**
	 * This method creates a GotoInstruction
	 * @param instruction The string array which represents the whole instruction
	 * @param size The size of this string array
	 * @return GotoInstruction
	 * @throws IllegalArgumentException if size!=2 
	 */
	public Instruction makeGotoInstruction(String[] instruction, int size) throws IllegalArgumentException{
		
		if (size != 2){
			throw new IllegalArgumentException("A goto instruction consists of two parts but was " + size);
		}
		
		if(!(this.isCorrectPC(Integer.parseInt(instruction[1])))){
			throw new IllegalArgumentException("GotoInstruction: wrong PC: " + instruction[1]);
		}
		
		
		return new GotoInstruction(logger, Integer.parseInt(instruction[1]));

	}
	
	/**
	 * This method creates a SenseInstruction
	 * @param instruction The string array which represents the whole instruction
	 * @param size The size of this string array
	 * @return SenseInstruction
	 * @throws IllegalArgumentException if size!=2 or sense direction is invalid
	 */
	public Instruction makeSenseInstruction(String[] instruction, int size) throws IllegalArgumentException{
		
		if( size != 2){
			throw new IllegalArgumentException("A sense instruction must consist of two parts but was " + size);
		}
		
		if(this.isCorrectSenseDir(Integer.parseInt(instruction[1]))){
			
			return new SenseInstruction(logger, this.changeIntToDirection(Integer.parseInt(instruction[1])));
		}
		
		else{
			throw new IllegalArgumentException("Direction must be between 0 and 6 but was: " + instruction[1]);
		}
	}
	
	/**
	 * This method creates a IfInstruction
	 * @param instruction The string array which represents the whole instruction
	 * @param size the size of this string array
	 * @return IfInstruction
	 * @throws IllegalArgumentException if size!=4 or expression could not be build (type mismatch)
	 */
	public Instruction makeIfInstruction(String[] instruction, int size) throws IllegalArgumentException{
		
		if(size != 4){
			
			throw new IllegalArgumentException("IfInstruction consists of 4 parts but was " + size);
		}
		
		if(!(this.isCorrectPC(Integer.parseInt(instruction[3])))){
			throw new IllegalArgumentException("IfInstruction: wrong PC " +instruction[3]);
		}
		
		//Produce an expression 
		Expression exp = this.produceExpression(instruction[1]);
		
		//when type mismatch occurs null is given back by the method produceExpression
		if(exp == null){
			throw new IllegalArgumentException("Type mismatch in if instruction occured: " + instruction[1]);
		}
		
		else{
			return new IfInstruction(logger, Integer.parseInt(instruction[3]), exp);
		}
	}
	
	/**
	 * 
	 * @param instruction The string array which represents the whole instruction
	 * @param size The size of this string array
	 * @return IfallInstruction
	 * @throws IllegalArgumentException if size<4 or if expression could not be build (type mismatch)
	 */
	public Instruction makeIfallInstruction(String[] instruction, int size) throws IllegalArgumentException{
		
		if(size <4){
			throw new IllegalArgumentException("An IfallInstruction consists of four parts but was: " + size);
		}
	
		if(!(this.isCorrectPC(Integer.parseInt(instruction[size-1])))){
			throw new IllegalArgumentException("IfallInstruction: wrong PC : " + instruction[size-1]);
		}
		
		int diff = size - 3;
		
		Expression[] exps = new Expression[diff];
		
		for (int i = 0; i< diff; i++){
			
			Expression exp = this.produceExpression(instruction[i+1]);
			
			if(exp == null){
				throw new IllegalArgumentException("Ifall Instruction: something with the types or their spelling is wrong: " + instruction[i+1]);
			}
			
			exps[i] = exp;
		}
		
		return new IfAllInstruction(logger, Integer.parseInt(instruction[size-1]) , exps);
	}
	
	/**
	 * 
	 * @param instruction The string array which represents the whole instruction
	 * @param size The size of this string array
	 * @return IfanyInstruction
	 * @throws IllegalArgumentException if size<4 or expression cannot be built
	 */
	public Instruction makeIfanyInstruction(String[] instruction, int size) throws IllegalArgumentException{
		
		if(size <4){
			throw new IllegalArgumentException("An ifany instruction consists of four parts but was " + size);
		}
	
		if(!(this.isCorrectPC(Integer.parseInt(instruction[size-1])))){
			throw new IllegalArgumentException("IfanyInstruction: wrong PC: " + instruction[size-1]);
		}
		
		int diff = size - 3;
		
		Expression[] exps = new Expression[diff];
		
		for (int i = 0; i< diff; i++){
			
			Expression exp = this.produceExpression(instruction[i+1]);
			
			if(exp == null){
				throw new IllegalArgumentException("Ifany Instruction: something with the types or their spelling is wrong : " + instruction[i+1]);
			}
			
			exps[i] = exp;
		}
		
		return new IfAnyInstruction(logger, Integer.parseInt(instruction[size-1]) , exps);

	}
	
	/**
	 * 
	 * @param instruction The string array which represents the whole instruction
	 * @param size The size of this string array
	 * @return RefreshInstruction
	 * @throws IllegalArgumentException if size!=4 or sense direction is wrong
	 */
	public Instruction makeRefreshInstruction(String[] instruction, int size) throws IllegalArgumentException{
		
		if(size != 4){
			throw new IllegalArgumentException("Refresh instruction consists of 4 parts but there were only " + size);
		}
		
		if( !(this.isCorrectSenseDir(Integer.parseInt(instruction[1])))){
			throw new IllegalArgumentException("Refresh Instruction:Wrong integer value sense direction: " + instruction[1]);
		}
		
		if(!(this.isCorrectPC(Integer.parseInt(instruction[3])))){
			throw new IllegalArgumentException("RefreshInstruction: wrong PC " + instruction[3]);
		}
		
		else{
			return new RefreshInstruction(logger, Integer.parseInt(instruction[3]), this.changeIntToDirection(Integer.parseInt(instruction[1])));
		}
	}
	
	/**
	 * 
	 * @param i the int which should be cast
	 * @return Direction according to the given int
	 * @throws IllegalArgumentException if direction is less than 0 or greater than 6
	 */
	private Direction changeIntToDirection(int i) throws IllegalArgumentException{
		
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
			throw new IllegalArgumentException("A direction must be between 0 and 6 but direction was: " + i);
	
		}
	}
	
	
	/**
	 * 
	 * @param i the int which should be tested
	 * @return true if i is less than 0 or greater than 6
	 */
	private boolean isCorrectSenseDir(int i){
		
		if (i< 0 || i> 6){
			return false;
		}
		
		else{
			return true;
		}
	}
	
	/**
	 * This method produces an expression which will be part of the instruction (if, ifall, ifany)
	 * @param s  The string which should be converted into an expression
	 * @return Expression (null if the expression could not be built)
	 */
	private Expression produceExpression(String s){
		
		//Tests if this is a bool register and returns a RegisterCall
		if (this.isBool_register(s)){
			return new RegisterCall(this.changeRegisterToInt(s));
		}
		
		//Tests if this is a negated bool register and returns a NotOperator
		if(this.isNegBoolRegister(s)){
			String[] splitted = s.split("!");
			return new NotOperator(new RegisterCall(this.changeRegisterToInt(splitted[1])));
		}
		String[] comparison = s.split("==");
		
		if(comparison.length == 2){
			if (comparison[0].toLowerCase().equals("sense_celltype")&& this.isCell_type(comparison[1])){
				
				return new EqualOperator(new RegisterCall(this.changeRegisterToInt(comparison[0])), new Literal(this.changeCell_typeToInt(comparison[1])));
			}
			
			if(comparison[0].toLowerCase().equals("sense_shiptype") && this.isShip_type(comparison[1])){
				
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
				
				if (comparison[0].toLowerCase().equals("sense_celltype")&& this.isCell_type(comparison[1])){
					
					return new UnequalOperator(new RegisterCall(this.changeRegisterToInt(comparison[0])), new Literal(this.changeCell_typeToInt(comparison[1])));
				}
				
				if(comparison[0].toLowerCase().equals("sense_shiptype") && this.isShip_type(comparison[1])){
					
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
	
	/**
	 * This methods only tells you if you can parse a string to int
	 * @param s The string which should be tested.
	 * @return true if it can be parsed
	 */
	private boolean isInt(String s){
		try{
			Integer.parseInt(s);
		}
		
		catch(Exception e){
			return false;
		}
		
		return true;
	}
	
	/**
	 * This method tells you if there is a bool register or its negation
	 * @param s The string which should be tested
	 * @return true if the string is a bool register or its negation
	 */
	private boolean isBool_register(String s){
		
		switch(s.toLowerCase()){
		case("sense_treasure"):
			return true;
		case("sense_marker0"):
			return true;
		case("sense_marker1"):
			return true;
		case("sense_marker2"):
			return true;
		case("sense_marker3"):
			return true;
		case("sense_marker4"):
			return true;
		case("sense_marker5"):
			return true;
		case("sense_enemymarker"):
			return true;
		case("sense_shiploaded"):
			return true;
		case("sense_supply"):
			return true;
		default:
			return false;
		}
			
	}
	
	/**
	 * 
	 * @param s the string which should be a bool register
	 * @return returns true if there is a negated bool register
	 */
	private boolean isNegBoolRegister(String s){
		
		switch(s.toLowerCase()){
		case("!sense_treasure"):
			return true;
		case("!sense_marker0"):
			return true;
		case("!sense_marker1"):
			return true;
		case("!sense_marker2"):
			return true;
		case("!sense_marker3"):
			return true;
		case("!sense_marker4"):
			return true;
		case("!sense_marker5"):
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
	
	/**
	 * This method tells you if the given string is a cell type
	 * @param s The string which should be tested
	 * @return true if the string is a cell type
	 */
	private boolean isCell_type(String s){
		
		switch(s.toLowerCase()){
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
	
	/**
	 * This method tells you if the string represents a ship type
	 * @param s The string to be tested.
	 * @return true if the string represents a ship type
	 */
	private boolean isShip_type(String s){
		
		switch(s.toLowerCase()){
		case("friend"):
			return true;
		case("enemy"):
			return true;
		default:
			return false;
		}
	}
	
	/**
	 * This method tells you if the string is an int register
	 * @param s The string to be tested.
	 * @return true if this string represents an int register
	 */
	private boolean isInt_register(String s){
		
		switch(s.toLowerCase()){
		case("ship_direction"):
			return true;
		case("ship_load"):
			return true;
		case("ship_moral"):
			return true;
		case("ship_condition"):
			return true;
		case("sense_shipdirection"):
			return true;
		case("sense_shipcondition"): //TODO: find out how this looks like
			return true;
		default:
			return false;
		
		}
	}
	
	/**
	 * This method converts a string, which represents a cell type, into an int.
	 * @param s The string which should be converted
	 * @return The corresponding int
	 */
	private int changeCell_typeToInt(String s){
		
		switch(s.toLowerCase()){
		case("island"):
			return 0;
		case("home"):
			return 1;
		case("enemyhome"):
			return 2;
		case("empty"):
			return 3;
		default:
			throw new IllegalStateException("Don't know how this could happen (method: changeCell_typeToInt: " + s);
		}
	}
	
	/**
	 * This methods converts a string, which represents ship type, into an int
	 * @param s The string which should be converted.
	 * @return The corresponding int
	 */
	private int changeShip_typeToInt(String s){
		
		switch(s.toLowerCase()){
		case("friend"):
			return 0;
		case("enemy"):
			return 1;
		default:
			throw new IllegalStateException("This should never happen (method changeShip_typeToInt): " + s);
		}
	}
	
	/**
	 * This methods converts a sense_register into an int
	 * @param s The string which should be converted
	 * @return The corresponding int
	 */
	private int changeRegisterToInt(String s){
		
		switch(s.toLowerCase()){
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
			throw new IllegalStateException("This should never happen (method changeRegisterToInt): " + s);
		
				
		}
	}
	
	/**
	 * This method returns if the given string equals "else"
	 * @param s The string to be tested
	 * @return true if this string equals "else"
	 */
	public boolean equalsElse(String s){
		
		return s.toLowerCase().equals("else");
	}
	
	/**
	 * The method returns true if the integer is between 0 and 1999.
	 * @param i The integer which should be tested
	 * @return if the integer is an invalid PC
	 */
	public boolean isCorrectPC(int i){
		if(i<0 || i> 1999){
			return false;
		}
		
		else{
			return true;
		}
	}
}
