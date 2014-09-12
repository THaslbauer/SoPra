package de.unisaarland.cs.st.pirates.group1.main.sim.gamestuff;

import java.util.HashMap;
import java.util.List;

import de.unisaarland.cs.st.pirates.group1.main.sim.util.CellType;
import de.unisaarland.cs.st.pirates.group1.main.sim.util.Direction;
import de.unisaarland.cs.st.pirates.group1.main.sim.util.Heading;

public abstract class Tile {
	private Map map;
	private Position position;
	private Placable [] placables;
	private HashMap<Faction, List<Buoy>> bouyMap;
	
	/**
	 * Tile constructor
	 * @param map the map the tile is on
	 * @param position the position where the tile should be at
	 */
	public Tile(Map map, Position position) {
		this.map = map;
		this.position = position;
	}
	
	/**
	 * Information about the geographic accessibility of this tile
	 * 
	 * @return if a ship can move there
	 */
	public abstract CellType navigable(Ship ship);
	
	/**
	 * Checks if this is a supply island
	 * @return true if this is a supply island, false otherwise
	 */
	public abstract boolean isSupply();
	
	/**
	 * Attaches a ship to this tile
	 * @param ship the ship to attach
	 */
	public void attach(Ship ship)  {
		
	}
	
	/**
	 * Attaches a kraken to this tile
	 * @param kraken the kraken to attach
	 */
	public void attach(Kraken kraken) {
		
	}
	
	/**
	 * Attaches a treasure to this tile
	 * @param treasure the treasure to attach
	 */
	public void attach(Treasure treasure) {
		
	}
	
	/**
	 * Attaches a buoy to this tile
	 * @param buoy the buoy to attach
	 */
	public void attach(Buoy buoy) {
		
	}
	
	/**
	 * Gets the geographically correct neighbour to this tile
	 * with a relative heading and direction
	 * @param heading The heading of the thing that wants to know the neighbour
	 * @param direction The direction of the thing
	 * @return the neighbour tile
	 * @throws IllegalArgumentException if there is no neighbour or if the direction
	 * and/or heading is illegal for the map geometry
	 */
	public Tile getNeighbour(Heading heading, Direction direction) {
		
		return null;
	}
	
	/**
	 * Increases a treasure package on this tile
	 * Creates one if non-existent
	 * @param value how much you'd like to increase the treasure count here
	 */
	public void increaseTreasure(int value) {
		
	}
	
	/**
	 * Decreases a treasure package on this tile
	 * Deletes one if completely empty
	 * @param value how much of this treasure you'd like to take
	 * @throws IllegalArgumentException if you want to take more than there is
	 */
	public void decreaseTreasure(int value) {
		
	}
}
