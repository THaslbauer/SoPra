
package de.unisaarland.cs.st.pirates.group1.sim.parser;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;



import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import de.unisaarland.cs.st.pirates.group1.sim.driver.Simulator;
import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.EntityFactory;
import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Faction;
import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Position;
import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Tile;
import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Worldmap;
import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Worldmap6T;

/**
 * This is the parser for the whole world (namely the map and its entities).
 * 
 * @author Kerstin
 * @version Version 2.0
 */
public class MapParser {
	
	private HashMap<String, Faction> factions;
	private ArrayList<Faction> list;
	private Worldmap mymap;
	private int factioncount =0;
	private Simulator simulator;
	
	public MapParser(){
		factions = new HashMap<String,Faction>();
		list = new ArrayList<Faction>();
	}

	
	/**
	 * This method parses the input map and thereby creates the map with its tiles and entities. 
	 * It only indirectly uses the simulator whose methods are called:
	 * - createShip(Faction, Tile)
	 * - createKraken(Tile )
	 * It also indirectly uses the map whose methods are called:
	 * - createIslandTile(Position, boolean)
	 * - createSeaTile(Position)
	 * - createBaseTile(Position, Faction)
	 * - createTreasure(int, Position)
	 *
	 *Indirectly means, that is invokes its own methods which use the methods above.
	 * @param stream 
	 * @param simulator
	 */
	public void parseMap(InputStream stream, Simulator simulator) throws IllegalArgumentException, NullPointerException{
		
		HashMap<String, Faction> fact = factions;
		
		int width, height;
		try(Scanner scan = new Scanner(stream)){
			
			try{
				this.simulator = simulator;
				
				String readline = scan.nextLine();
				
				width = Integer.parseInt(readline.trim());
				
			
				//these cases are not allowed for a correct map (concerning width)
				if(width > 200 || width < 2 ){
					throw new IllegalArgumentException("map is not correctly sized (problem with width)");
				}
				
				readline = scan.nextLine();
				
				height = Integer.parseInt(readline.trim());
				
				//these cases are not allowed for a correct map (concerning height)
				if(height > 200 || height < 2 || height%2 == 1 ){
					throw new IllegalArgumentException("map is not correctly sized (problem with height");
				}

				
				if(!(scan.hasNext())){
					throw new IllegalArgumentException("map is missing");
				}
				
				mymap = new Worldmap6T(width, height, simulator.getLogWriter(), simulator.getEntityFactory());
				simulator.setWorldmap(mymap);
				//building of the map: beginning
				char[] current = this.streamToCharArr(scan);
				
				
				int counter = -1;
				
				//IllegalStateException will be thrown and converted to IllegalArgumentException if scan.next() cannot proceed
				for(int j = 0; j< height; j++){
					
					for(int i = 0; i< width; i++){
						
						counter = counter +1;
						switch(current[counter]){
						
						case '#':
							this.createIsland(new Position(i,j), false);
							break;
							
						case '$':
							this.createIsland(new Position(i,j), true);
							break;
						
						case '&':
							this.createKraken(new Position(i,j));
							break;
							
						case '.':
							this.createWater(new Position(i,j));
							break;
							
						case 'a':
							this.createPirateBase(new Position(i,j), "a");
							break;
						
						case 'b':
							this.createPirateBase(new Position(i,j), "b" );
							break;
						
						case 'c':
							this.createPirateBase(new Position(i,j), "c");
							break;
							
						case 'd':
							this.createPirateBase(new Position(i,j), "d");
							break;
							
						case 'e':
							this.createPirateBase(new Position(i,j), "e");
							break;
							
						case 'f':
							this.createPirateBase(new Position(i,j), "f");
							break;
							
						case 'g':
							this.createPirateBase(new Position(i,j), "g");
							break;
							
						case 'h':
							this.createPirateBase(new Position(i,j), "h");
							break;
							
						case 'i':
							this.createPirateBase(new Position(i,j), "i");
							break;
							
						case 'j':
							this.createPirateBase(new Position(i,j), "j");
							break;
							
						case 'k':
							this.createPirateBase(new Position(i,j), "k");
							break;
							
						case 'l':
							this.createPirateBase(new Position(i,j), "l");
							break;
							
						case 'm':
							this.createPirateBase(new Position(i,j), "m");
							break;
							
						case 'n':
							this.createPirateBase(new Position(i,j), "n");
							break;
							
						case 'o':
							this.createPirateBase(new Position(i,j), "o");
							break;
							
						case 'p':
							this.createPirateBase(new Position(i,j), "p");
							break;
						
						case 'q':
							this.createPirateBase(new Position(i,j), "q");
							break;
							
						case 'r':
							this.createPirateBase(new Position(i,j), "r");
							break;
							
						case 's':
							this.createPirateBase(new Position(i,j), "s");
							break;
							
						case 't':
							this.createPirateBase(new Position(i,j), "t");
							break;
							
						case 'u':
							this.createPirateBase(new Position(i,j), "u");
							break;
							
						case 'v':
							this.createPirateBase(new Position(i,j), "v");
							break;
							
						case 'w':
							this.createPirateBase(new Position(i,j), "w");
							break;
							
						case 'x':
							this.createPirateBase(new Position(i,j), "x");
							break;
							
						case 'y':
							this.createPirateBase(new Position(i,j), "y");
							break;
							
						case 'z':
							this.createPirateBase(new Position(i,j), "z");
							break;
							
						case '1':
							this.createIslandWithTreasure(new Position(i,j), 1);
							break;
						
						case '2':
							this.createIslandWithTreasure(new Position(i,j), 2);
							break;
							
						case '3':
							this.createIslandWithTreasure(new Position(i,j), 3);
							break;
							
						case '4':
							this.createIslandWithTreasure(new Position(i,j), 4);
							break;
							
						case '5':
							this.createIslandWithTreasure(new Position(i,j), 5);
							break;
							
						case '6':
							this.createIslandWithTreasure(new Position(i,j), 6);
							break;
							
						case '7':
							this.createIslandWithTreasure(new Position(i,j), 7);
							break;
							
						case '8':
							this.createIslandWithTreasure(new Position(i,j), 8);
							break;
							
						case '9':
							this.createIslandWithTreasure(new Position(i,j), 9);
							break;
						default:
							throw new IllegalArgumentException("wrong symbol in map" + current[counter] + "Boxsack");
						}
				
					}
					
				}
				
				//gives the simulator its faction list
				this.simulator.setFactions(list);
				factions = new HashMap<String, Faction>();
				list = new ArrayList<Faction>();
				factioncount =0;
				
			}
			catch(NoSuchElementException | IllegalStateException | ArrayIndexOutOfBoundsException c){
				throw new IllegalArgumentException();
			}
		}
	}
	
	/**
	 * This is a method which helps to create an island tile. It uses the maps method createIslandTile.
	 * @param position The position of this tile
	 * @param supply Boolean which tells you if this is a supply island
	 * @return
	 */
	public Tile createIsland(Position position, boolean supply){	
		
		 return mymap.createIslandTile(position, supply);				
	}

	/**
	 * This is a method which helps to create a sea tile. It uses the maps method createSeaTile.
	 * @param position The position of this water tile
	 * @return returns the tile it created
	 */
	public Tile createWater(Position position){
		
		return mymap.createSeaTile(position);
	}
	
	/**
	 * This is a method which helps to create a kraken. It invokes the createKraken method of the simulator.
	 * @param position The position for this kraken
	 */
	public void createKraken(Position position){
		
		simulator.createKraken(this.createWater(position));
		return;
	}
	
	/**
	 * This is a method which helps to create a pirate base. If this pirate base already exists it can be taken out of the factions HashMap.
	 * This method also invokes this classes createShip method.
	 * 
	 * @param position the position for this pirate base. 
	 * @param string the name of this pirate base
	 */
	public void createPirateBase(Position position, String string){
		
		if (factions.containsKey(string)){
			Tile tile = mymap.createBaseTile(position, factions.get(string));
			this.createShip(factions.get(string), tile);
		}
		
		else{
			Faction faction = new Faction(string, factioncount);
			factions.put(string, faction);
			list.add(faction);
			factioncount+=1;
			Tile tile = mymap.createBaseTile(position, faction);
			this.createShip(faction, tile);
			return;
		}
	}
	
	/**
	 * This method helps to create a ship. It thereby invokes the simulators method createShip.
	 * @param faction the faction of the new ship
	 * @param tile the tile where the ship should stand on
	 */
	public void createShip(Faction faction, Tile tile){
		
		simulator.createShip(faction, tile);
	}
	
	/**
	 * This method helps to create an island with a treasure attached to it. It therefore invokes the map's method createTreasure.
	 * @param position  the position of the treasure and tile which are created
	 * @param value the value of the treasure 
	 */
	public void createIslandWithTreasure(Position position, int value){
		
		mymap.createTreasure(value, this.createIsland(position, false));
		return;
	}
	
	/**
	 * This methods converts the stream into a whitespace free representation as string.
	 * @param scan
	 * @return
	 */
	private char[] streamToCharArr(Scanner scan){
		
		String map = "";
		
		while(scan.hasNextLine()){
			map += scan.nextLine();
		}
		
		String[] c = map.split("\\s+");
		
		String result ="";
		int length = c.length;
		
		for(int i = 0; i< length; i++){
			 result +=   c[i];
		}
		
		return result.toCharArray();
	}
}
