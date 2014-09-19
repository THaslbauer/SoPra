
package de.unisaarland.cs.st.pirates.group1.sim.parser;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;



import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import de.unisaarland.cs.st.pirates.group1.sim.driver.Simulator;
import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Faction;
import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Worldmap;
import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Worldmap6T;

/**
 * This is the parser for the whole world (namely the map and its entities).
 * 
 * @author Kerstin
 * @version Version 1.0
 */
public class MapParser {
	
	
	private HashMap<String, Faction> factions;
	
	public MapParser(){
	}

	
	/**
	 * This method parses the input map and thereby creates the map with its tiles and entities. 
	 * It uses the simulator whose methods are called:
	 * - createShip(Faction, Tile)
	 * - createKraken(Tile )
	 * It also uses the map whose methods are called:
	 * - createIslandTile(Position, boolean)
	 * - createSeaTile(Position)
	 * - createBaseTile(Position, Faction)
	 * - createTreasure(int, Position)
	 *
	 * @param stream 
	 * @param simulator
	 */
	public void parseMap(InputStream stream, Simulator simulator) throws IllegalArgumentException, NullPointerException{
		
		
		int width, height;
		try(Scanner scan = new Scanner(stream)){
			
			try{
				width = scan.nextInt();
				
				//these cases are not allowed for a correct map (concerning width)
				if(width < 200 || width < 2 ){
					throw new IllegalArgumentException("map is not correctly sized (problem with width)");
				}
				
				if(!(scan.nextLine().equals(""))){
					throw new IllegalArgumentException();
				}
				
				height = scan.nextInt();
				
				//these cases are not allowed for a correct map (concerning height)
				if(height > 200 || height < 2 || height%2 == 1 ){
					throw new IllegalArgumentException("map is not correctly sized (problem with height");
				}
				
				if(!(scan.nextLine().equals(""))){
					throw new IllegalArgumentException();
				}
				
				Worldmap mymap = new Worldmap6T(width, height, simulator.getLogWriter(), simulator.getEntityFactory());
					
				//building parts of the map
				String current;
				
				//IllegalStateException will be thrown and converted to IllegalArgumentException if scan.next() cannot proceed
				for(int i = 0; i< height; i++){
					
					for(int j = 0; j< width; j++){
						
						while(scan.hasNext()){
							
							current = scan.next();
							
							switch(current){
							
							case "#":
								break;
								
							case "$":
								break;
							
							case "&":
								break;
								
							case ".":
								break;
								
							case "a":
								break;
							
							case "b":
								break;
							
							case "c":
								break;
								
							case "d":
								break;
								
							case "e":
								break;
								
							case "f":
								break;
								
							case "g":
								break;
								
							case "h":
								break;
								
							case "i":
								break;
								
							case "j":
								break;
								
							case "k":
								break;
								
							case "l":
								break;
								
							case "m":
								break;
								
							case "n":
								break;
								
							case "o":
								break;
								
							case "p":
								break;
							
							case "q":
								break;
								
							case "r":
								break;
								
							case "s":
								break;
								
							case "t":
								break;
								
							case "u":
								break;
								
							case "v":
								break;
								
							case "w":
								break;
								
							case "x":
								break;
								
							case "y":
								break;
								
							case "z":
								break;
								
							case "1":
								break;
							
							case "2":
								break;
								
							case "3":
								break;
								
							case "4":
								break;
								
							case "5":
								break;
								
							case "6":
								break;
								
							case "7":
								break;
								
							case "8":
								break;
								
							case "9":
								break;
								
							default:
								throw new IllegalArgumentException("wrong symbol in map");
							}
						}
					
					}
					
				}
				
				
				
			}
			catch(NoSuchElementException | IllegalStateException c){
				throw new IllegalArgumentException();
			}
		}
	}
	
	public void createEmptyIsland(){
		
		
	}
}
