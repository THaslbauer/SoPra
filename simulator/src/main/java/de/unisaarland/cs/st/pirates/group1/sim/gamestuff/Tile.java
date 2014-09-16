package de.unisaarland.cs.st.pirates.group1.sim.gamestuff;

import java.util.HashMap;
import java.util.List;

import de.unisaarland.cs.st.pirates.group1.sim.util.CellType;
import de.unisaarland.cs.st.pirates.group1.sim.util.Direction;
import de.unisaarland.cs.st.pirates.group1.sim.util.Heading;

/**
 * Abstract class for a tile. 
 * Features consistency methods.
 * @author Jens Kreber
 *
 */
public abstract class Tile {
	private Worldmap map;
	private Position position;
	private Placable [] placables; // {Ship, Kraken, Treasure}
	private HashMap<Faction, List<Buoy>> buoyMap;
	
	/**
	 * Tile constructor
	 * @param map the map the tile is on
	 * @param position the position where the tile should be at
	 */
	public Tile(Worldmap map, Position position) {
		this.map = map;
		this.position = position;
		placables = new Placable[3];
		buoyMap = new HashMap<Faction, List<Buoy>>();
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
	 * @throws IllegalCallException if there is already a ship on this tile
	 */
	public void attach(Ship ship)  {
		//TODO
	}
	
	/**
	 * Attaches a kraken to this tile
	 * @param kraken the kraken to attach
	 * @throws IllegalCallException if there is already a kraken on this tile
	 */
	public void attach(Kraken kraken) {
		//TODO
	}
	
	/**
	 * Attaches a treasure to this tile
	 * @param treasure the treasure to attach
	 * @throws IllegalCallException if there is already a Treasure on this tile
	 */
	public void attach(Treasure treasure) {
		//TODO
	}
	
	/**
	 * Detaches a ship from this tile
	 * @param ship the ship to attach
	 * @throws IllegalCallException if there is no ship on this tile
	 * @throws IllegalArgumentException if there is a ship, but not this ship on this tile
	 */
	public void detach(Ship ship) {
		//TODO
	}
	
	/**
	 * Detaches a kraken from this tile
	 * @param kraken the kraken to attach
	 * @throws IllegalCallException if there is no kraken on this tile
	 * @throws IllegalArgumentException if there is a kraken, but not this kraken on this tile
	 */
	public void detach(Kraken kraken) {
		//TODO
	}
	
	/**
	 * Detaches a treasure from this tile
	 * @param treasure the treasure to attach
	 * @throws IllegalCallException if there is no treasure on this tile
	 * @throws IllegalArgumentException if there is a treasure, but not this treasure on this tile
	 */
	public void detach(Treasure treasure) {
		//TODO	
	}
	
	/**
	 * Returns a Ship. Can be null.
	 * @return the ship / null
	 */
	public Ship getShip() {
		return (Ship) placables[0];
	}
	
	/**
	 * Returns a Kraken. Can be null.
	 * @return the kraken / null
	 */
	public Kraken getKraken() {
		return (Kraken) placables[1];
	}
	
	/**
	 * Returns a Treasure. Can be null.
	 * @return the treasure / null
	 */
	public Treasure getTreasure() {
		return (Treasure) placables[2];
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
		return map.getTile(map.calcPosition(this.position, heading, direction));
		//TODO
	}
	
	/**
	 * Increases a treasure package on this tile
	 * Creates one if non-existent
	 * @param value how much you'd like to increase the treasure count here
	 */
	public void increaseTreasure(int value) {
		//TODO
	}
	
	/**
	 * Decreases a treasure package on this tile
	 * Deletes one if completely empty
	 * @param value how much of this treasure you'd like to take
	 * @throws IllegalArgumentException if you want to take more than there is
	 */
	public void decreaseTreasure(int value) {
		//TODO
	}
	
	public HashMap<Faction, List<Buoy>> getBuoyMap() {
		return buoyMap;
	}
	
	public Position getPosition() {
		return position;
	}
}
