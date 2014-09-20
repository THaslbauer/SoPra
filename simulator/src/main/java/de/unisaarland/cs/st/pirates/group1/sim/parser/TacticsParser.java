package de.unisaarland.cs.st.pirates.group1.sim.parser;

import java.io.InputStream;
import java.util.Random;
import java.util.Scanner;

import de.unisaarland.cs.st.pirates.group1.sim.logger.ExtendedLogWriter;
import de.unisaarland.cs.st.pirates.group1.sim.logic.instruction.Instruction;
import de.unisaarland.cs.st.pirates.group1.sim.logic.instruction.elseInstructions.FlipZeroInstruction;
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
	
	public TacticsParser(ExtendedLogWriter logger){		
		this.logger = logger;
	}
	
	/**
	 * This method parses the tactics file for each faction and thereby tests if types are consistent.
	 * @param input
	 * @param random
	 */
	public Instruction[] parseTactics(InputStream input, Random random){
		
		//convert the input to string
		String tactics_string = this.convertStreamToString(input);
	
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
					this.makeTurnInstruction(instruction_array, size);
					break;
					
				case "mark":
					this.makeMarkInstruction(instruction_array, size);
					break;
					
				case "unmark":
					this.makeUnmarkInstruction(instruction_array, size);
					break;
					
				case "move":
					this.makeMoveInstruction(instruction_array, size);
					break;
					
				case "pickup":
					this.makePickupInstruction(instruction_array, size);
					break;
					
				case "drop":
					this.makeDropInstruction(instruction_array, size);
					break;
					
				case "flipzero":
					this.makeFlipzeroInstruction(instruction_array, size);
					break;
					
				case "goto":
					this.makeGotoInstruction(instruction_array, size);
					break;
					
				case "sense":
					this.makeSenseInstruction(instruction_array, size);
					break;
					
				case "if":
					this.makeIfallInstruction(instruction_array, size);
					break;
				
				case "ifall":
					this.makeIfallInstruction(instruction_array, size);
					break;
				
				case "ifany":
					this.makeIfanyInstruction(instruction_array, size);
					break;
					
				case "refresh":
					this.makeRefreshInstruction(instruction_array, size);
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
		
		//TODO: delete this
		return null;
		
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
		
		if(!(instruction[1].equals("left")) || !(instruction[1].equals("right"))){
			
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
		
		//TODO: delete this
		return null;
		
	}
	
	public Instruction makeIfallInstruction(String[] instruction, int size){
		
		//TODO: delete this
		return null;
	}
	
	public Instruction makeIfanyInstruction(String[] instruction, int size){
		
		//TODO: delete this
		return null;
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
}
