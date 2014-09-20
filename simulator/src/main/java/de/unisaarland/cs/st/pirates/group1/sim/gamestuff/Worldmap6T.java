package de.unisaarland.cs.st.pirates.group1.sim.gamestuff;

import de.unisaarland.cs.st.pirates.group1.sim.logger.ExtendedLogWriter;
import de.unisaarland.cs.st.pirates.group1.sim.logger.LogWriter;
import de.unisaarland.cs.st.pirates.group1.sim.logger.LogWriter.Cell;
import de.unisaarland.cs.st.pirates.group1.sim.logger.LogWriter.Entity;
import de.unisaarland.cs.st.pirates.group1.sim.logger.LogWriter.Key;
import de.unisaarland.cs.st.pirates.group1.sim.util.Direction;
import de.unisaarland.cs.st.pirates.group1.sim.util.Heading;
import static de.unisaarland.cs.st.pirates.group1.sim.util.ThrowHelper.throwIAException;

import static de.unisaarland.cs.st.pirates.group1.sim.util.ThrowHelper.notNegative;


/**
 * A end-of-world-to-other-end-of-world-mapping, haxagon torus based map implementation
 * @author Jens Kreber
 *
 */
public class Worldmap6T extends Worldmap {

	protected Tile[][] tiles; // [y][x]
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
		this.width = width >= 2 ? width : (int) throwIAException("Width too small!");
		this.height = height >= 2 ? height : (int) throwIAException("Height too small!");
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
		int dx = 0, dy = 0;
		boolean even = position.y % 2 == 0;
		
		/*
		 *  0,0     1,0     2,0     3,0
		 *      0,1     1,1     2,1
		 *  0,2     1,2     2,2     3,2
		 * 
		 */
		
		switch(dir) {
			case 0 : {
				dx = 1;
				break;
			}
			case 1 : {
				// Wenn y gerade ist, dann x nicht ändern, bei ungerade +1
				dx = even ? 0 : 1;
				dy = 1;
				break;
			}
			case 2 : {
				dx = even ? -1 : 0;
				dy = 1;
				break;
			}
			case 3 : {
				dx = -1;
				break;
			}
			case 4 : {
				dx = even ? -1 : 0;
				dy = -1;
				break;
			}
			case 5 : {
				dx = even ? 0 : 1;
				dy = -1;
			}
			default : { }
		}
		int newx = position.x + dx;
		int newy = position.y + dy;
		newx = newx >= width ? 0 : ( newx < 0 ? width-1 : newx );
		newy = newy >= height ? 0 : ( newy < 0 ? height-1 : newy );
		
		return new Position(newx, newy);
	}

	@Override
	public Island createIslandTile(Position position, boolean supply) {
		Island island = new Island(supply, this, position);
		tiles[position.y][position.x] = island;
		if(supply)
			logger.addCell(Cell.SUPPLY, null, position.x, position.y);
		else
			logger.addCell(Cell.ISLAND, null, position.x, position.y);
		return island;
	}

	@Override
	public Sea createSeaTile(Position position) {
		Sea sea = new Sea(this, position);
		tiles[position.y][position.x] = sea;
		logger.addCell(Cell.WATER, null, position.x, position.y);
		return sea;
	}

	@Override
	public Base createBaseTile(Position position, Faction faction) {
		Base base = new Base(faction, this, position);
		tiles[position.y][position.x] = base;
		logger.addCell(Cell.WATER, faction.getFactionID(), position.x, position.y);
		return base;
	}

	@Override
	public Buoy createBuoy(int type, Faction faction, Tile tile) {
		Buoy buoy = entityFactory.createBuoy(type, faction, tile);
		logger.create(Entity.BUOY, buoy.getId(), new LogWriter.Key[] {Key.FLEET, Key.VALUE, Key.X_COORD, Key.Y_COORD}, new int[]{buoy.getFaction().getFactionID(), buoy.getType(), buoy.getMyTile().getPosition().x, buoy.getMyTile().getPosition().y});
		return buoy;
	}

	@Override
	public Treasure createTreasure(int value, Tile tile) {
		Treasure treasure = entityFactory.createTreasure(value, tile);
		logger.create(Entity.TREASURE, treasure.getId(), new LogWriter.Key[]{Key.VALUE, Key.X_COORD, Key.Y_COORD}, new int[]{treasure.getValue(), treasure.getMyTile().getPosition().x, treasure.getMyTile().getPosition().y});
		return treasure;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
	
}
