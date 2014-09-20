package de.unisaarland.cs.st.pirates.group1.sim.parser;

import java.io.InputStream;
import java.util.Random;
import java.util.Scanner;

import de.unisaarland.cs.st.pirates.group1.sim.logic.instruction.Instruction;

/**
 * 
 * This is the parser for the tactics of each faction.
 * 
 * @author Kerstin
 * @version Version 1.0
 */
public class TacticsParser {

	public TacticsParser(){		
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
		
		//TODO: delete this
		return null;
	}
	
	public Instruction makeMarkInstruction(String[] instruction, int size){
		
		//TODO: delete this
				return null;

	}
	
	public Instruction makeUnmarkInstruction(String[] instruction, int size){
		
		//TODO: delete this
				return null;

	}
	
	public Instruction makeMoveInstruction(String[] instruction, int size){
		
		//TODO: delete this
		return null;
	}
	
	public Instruction makePickupInstruction(String[] instruction, int size){
		
		//TODO: delete this
		return null;
	}
	
	public Instruction makeDropInstruction(String[] instruction, int size){
		
		//TODO: delete this
		return null;
	}
	
	public Instruction makeFlipzeroInstruction(String[] instruction, int size){
		
		//TODO: delete this
		return null;
	}
	
	public Instruction makeGotoInstruction(String[] instruction, int size){
		
		//TODO: delete this
		return null;
	}
	
	public Instruction makeSenseInstruction(String[] instruction, int size){
		
		//TODO: delete this
		return null;
	}
	
	public Instruction makeIfInstruction(String[] instruction, int size){
		
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
		
		//TODO: delete this
		return null;
	}
}
