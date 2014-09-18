package de.unisaarland.cs.st.pirates.group1.tests.testUtil;

import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.*;

public class DummyEntityFactory extends EntityFactory {
	boolean ship = false, buoy = false, treasure = false, kraken = false;
	
	@Override
	public Ship createShip(Faction faction, Tile tile) {
		ship = true;
		return null;
	}
	
	@Override
	public Kraken releaseTheKraken(Tile tile) {
		kraken = true;
		return null;
	}
	
	@Override
	public Buoy createBuoy(int type, Faction faction, Tile tile) {
		buoy = true;
		return null;
	}
	
	@Override
	public Treasure createTreasure(int value, Tile tile) {
		treasure = true;
		return null;
	}
	
	public DummyEntityFactory reset() {
		ship = false;
		buoy = false;
		treasure = false;
		kraken = false;
		return this;
	}

}
