package de.unisaarland.cs.st.pirates.group1.sim.gamestuff;

import de.unisaarland.cs.st.pirates.group1.sim.logger.InfoPoint;
import de.unisaarland.cs.st.pirates.group1.sim.util.Direction;
import de.unisaarland.cs.st.pirates.group1.sim.util.Heading;

/**
 * Abstract class representing a map
 * @author Jens Kreber
 *
 */
public abstract class Map {
	private InfoPoint infoPoint;
	private EntityFactory entityFactory;
	
	/**
	 * Map constructor
	 * @param infoPoint the mighty infoPoint
	 * @param entityFactory
	 */
	public Map(InfoPoint infoPoint, EntityFactory entityFactory) {
		this.infoPoint = infoPoint;
		this.entityFactory = entityFactory;
	}

	
	/**
	 * Get a tile from the map at a specific position
	 * 
	 * @param position The position at where you want to get your tile
	 * @return The tile at the position you specified / null if non-existent
	 * @throws IllegalArgumentException for a position outside the map
	 */
	public abstract Tile getTile(Position position);
	
	/**
	 * Calculates a position with an absolute position and relative parameters
	 * 
	 * @param position The Position you want to start from
	 * @param heading The direction your thing is heading
	 * @param direction The direction your thing wants to do something in
	 * @return The new absolute position
	 * @throws IllegalArgumentException for a position outside the map
	 * @throws IllegalArgumentException for a Heading or a Direction outside current map geometry
	 */
	public abstract Position calcPosition(Position position, Heading heading, Direction direction);
	
	/**
	 * Creates a nice Island for you. 
	 * ... if you like with some supply
	 * 
	 * @param position The position where you want to create it
	 * @param supply true if this shall be a supply island
	 * @return your brand new island.
	 * @throws IllegalArgumentException for a position outside the map 
	 */
	public abstract Island createIslandTile(Position position, boolean supply);
	
	/**
	 * Creates an extensive piece of sea for you.
	 * 
	 * @param position The position where you want to create it
	 * @return your new epic piece of sea.
	 * @throws IllegalArgumentException for a position outside the map 
	 */
	public abstract Sea createSeaTile(Position position);
	
	/**
	 * Creates an invulnerable pirate base for you.
	 * 
	 * @param position The position where you want to create it
	 * @param faction The faction who owns this awesome base
	 * @return the base
	 * @throws IllegalArgumentException for a position outside the map 
	 */
	public abstract Base createBaseTile(Position position, Faction faction);
	
	/**
	 * Creates a small shiny buoy for you.
	 * 
	 * @param type The type (= the information) of your good buoy
	 * @param faction The faction that owns your buoy
	 * @param tile the tile on which you like to create the buoy
	 * @return your brand new buoy
	 */
	public abstract Buoy createBuoy(int type, Faction faction, Tile tile);
	
	/**
	 * Creates a colossal treasure for you.
	 * Small ones as well as big ones.
	 * 
	 * @param value The value of your treasure
	 * @param tile The tile you'd like to place the treasure on
	 * @return
	 */
	public abstract Treasure createTreasure(int value, Tile tile);
	
	public InfoPoint getInfoPoint() {
		return infoPoint;
	}

	public EntityFactory getEntityFactory() {
		return entityFactory;
	}
	
}
