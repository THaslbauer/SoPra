package de.unisaarland.cs.st.pirates.group1.sim.gamestuff;

import de.unisaarland.cs.st.pirates.group1.sim.logger.ExtendedLogWriter;
import de.unisaarland.cs.st.pirates.group1.sim.logger.InfoPoint;
import de.unisaarland.cs.st.pirates.group1.sim.util.Direction;
import de.unisaarland.cs.st.pirates.group1.sim.util.Heading;

/**
 * A end-of-world-to-other-end-of-world-mapping, haxagon torus based map implementation
 * @author Jens Kreber
 *
 */
public class Worldmap6T extends Worldmap {

	private Tile[][] tiles;
	private int width, height;
	
	/**
	 * Constructor for this end-of-world-to-other-end-of-world-mapping, haxagon torus based map
	 * @param width the widht of the map
	 * @param height the height (!) of the map
	 * @param infoPoint the mighty infoPoint
	 * @param entityFactory the factory for entities
	 * @throws IllegalArgumentsException for non-positive size arguments and odd height values
	 */
	public Worldmap6T(int width, int height, ExtendedLogWriter logger, EntityFactory entityFactory) {
		super(logger, entityFactory);
		if(width <= 0)
			throw new IllegalArgumentException("Width <= 0");
		if(height <= 0)
			throw new IllegalArgumentException("Height <= 0");
		if(height % 2 != 0)
			throw new IllegalArgumentException("Height is odd!");
		this.width = width;
		this.height = height;
	}
	

	@Override
	public Tile getTile(Position position) {
		try{
			return tiles[position.y][position.x];
		} catch(ArrayIndexOutOfBoundsException e) {
			throw new IllegalArgumentException("Coordinates are outside the map");
		}
		// LOL it works :)
	}

	@Override
	public Position calcPosition(Position position, Heading heading,
			Direction direction) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Island createIslandTile(Position position, boolean supply) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Sea createSeaTile(Position position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Base createBaseTile(Position position, Faction faction) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Buoy createBuoy(int type, Faction faction, Tile tile) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Treasure createTreasure(int value, Tile tile) {
		// TODO Auto-generated method stub
		return null;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
	
}
