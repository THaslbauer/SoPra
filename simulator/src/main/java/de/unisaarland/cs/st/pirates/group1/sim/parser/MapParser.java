
package de.unisaarland.cs.st.pirates.group1.sim.parser;

import java.io.InputStream;
import java.util.HashMap;
import java.util.stream.Stream;

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
	public void parseMap(InputStream stream, Simulator simulator){
		//TODO: implement this
	}
}
