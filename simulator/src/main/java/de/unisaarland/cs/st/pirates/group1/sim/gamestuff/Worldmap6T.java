package de.unisaarland.cs.st.pirates.group1.sim.gamestuff;

import de.unisaarland.cs.st.pirates.group1.sim.logger.ExtendedLogWriter;
import de.unisaarland.cs.st.pirates.group1.sim.util.Direction;
import de.unisaarland.cs.st.pirates.group1.sim.util.Heading;

import static de.unisaarland.cs.st.pirates.group1.sim.util.ThrowHelper.notNegative;


/**
 * A end-of-world-to-other-end-of-world-mapping, haxagon torus based map implementation
 * @author Jens Kreber
 *
 */
public class Worldmap6T extends Worldmap {

	private Tile[][] tiles; // [y][x]
	private int width, height;
	
	/**
	 * Constructor for this end-of-world-to-other-end-of-world-mapping, haxagon torus based map
	 * @param width the widht of the map
	 * @param height the height (!) of the map
	 * @param logger the mighty logger
	 * @param entityFactory the factory for entities
	 * @throws IllegalArgumentsException for non-positive size arguments and odd height values
	 */
	public Worldmap6T(int width, int height, ExtendedLogWriter logger, EntityFactory entityFactory) {
		super(logger, entityFactory);
		notNegative(width, "Width <= 0");
		notNegative(height, "Height <= 0");
		if(height % 2 != 0)
			throw new IllegalArgumentException("Height is odd!");
		this.width = width;
		this.height = height;
		tiles = new Tile[height][width];
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
		if(direction == Direction.D6)
			return position;
		int dir = heading.ordinal() + direction.ordinal();
		dir %= 6;
		
		//TODO a lot of fun
		
		return null;
	}

	@Override
	public Island createIslandTile(Position position, boolean supply) {
		Island island = new Island(supply, this, position);
		tiles[position.y][position.x] = island;
		return island;
	}

	@Override
	public Sea createSeaTile(Position position) {
		Sea sea = new Sea(this, position);
		tiles[position.y][position.x] = sea;
		return sea;
	}

	@Override
	public Base createBaseTile(Position position, Faction faction) {
		Base base = new Base(faction, this, position);
		tiles[position.y][position.x] = base;
		return base;
	}

	@Override
	public Buoy createBuoy(int type, Faction faction, Tile tile) {
		return entityFactory.createBuoy(type, faction, tile);
	}

	@Override
	public Treasure createTreasure(int value, Tile tile) {
		return entityFactory.createTreasure(value, tile);
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
	
}
