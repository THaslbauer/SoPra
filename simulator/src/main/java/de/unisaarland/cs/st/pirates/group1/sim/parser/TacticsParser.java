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
			
			try{
				int expr_num = instruction_array.length;
				
				String name = instruction_array[0];
				
				switch(name){
				
				case "turn":
					break;
					
				case "mark":
					break;
					
				case "unmark":
					break;
					
				case "move":
					break;
					
				case "pickup":
					break;
					
				case "drop":
					break;
					
				case "flipzero":
					break;
					
				case "goto":
					break;
					
				case "sense":
					break;
					
				case "if":
					break;
				
				case "ifall":
					break;
				
				case "ifany":
					break;
					
				case "refresh":
					break;
					
				default:
					throw new IllegalArgumentException("This instruction does not exist");
				}
			}
			
			//TODO: find out which exceptions are necessary
			catch(Exception e){
				
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
}
