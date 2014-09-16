package de.unisaarland.cs.st.pirates.group1.tests.testUtil;

import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Base;
import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Buoy;
import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.EntityFactory;
import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Faction;
import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Island;
import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Position;
import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Sea;
import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Tile;
import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Treasure;
import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Worldmap;
import de.unisaarland.cs.st.pirates.group1.sim.logger.InfoPoint;
import de.unisaarland.cs.st.pirates.group1.sim.util.Direction;
import de.unisaarland.cs.st.pirates.group1.sim.util.Heading;

public class DumbWorldmap extends Worldmap{

	public DumbWorldmap(){
		super(new DumbExtendedLogWriter(), new EntityFactory());
	}

	@Override
	public Tile getTile(Position position) {
		return null;
	}

	@Override
	public Position calcPosition(Position position, Heading heading,
			Direction direction) {
		return null;
	}

	@Override
	public Island createIslandTile(Position position, boolean supply) {
		return null;
	}

	@Override
	public Sea createSeaTile(Position position) {
		return null;
	}

	@Override
	public Base createBaseTile(Position position, Faction faction) {
		return null;
	}

	@Override
	public Buoy createBuoy(int type, Faction faction, Tile tile) {
		return null;
	}

	@Override
	public Treasure createTreasure(int value, Tile tile) {
		return null;
	}

}
