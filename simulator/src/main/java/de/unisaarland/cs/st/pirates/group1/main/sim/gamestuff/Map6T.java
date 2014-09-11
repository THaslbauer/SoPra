package de.unisaarland.cs.st.pirates.group1.main.sim.gamestuff;

import de.unisaarland.cs.st.pirates.group1.main.sim.logger.InfoPoint;

public class Map6T extends Map {

	private Tile[][] tiles;
	private int width, height;
	
	public Map6T(int width, int height, InfoPoint infoPoint, EntityFactory entityFactory) {
		super(infoPoint, entityFactory);
		this.width = width;
		this.height = height;
	}

	@Override
	public Tile getTile(Position position) {
		// TODO Auto-generated method stub
		return null;
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
