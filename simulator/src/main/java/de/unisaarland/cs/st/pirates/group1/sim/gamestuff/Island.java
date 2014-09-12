package de.unisaarland.cs.st.pirates.group1.main.sim.gamestuff;

import de.unisaarland.cs.st.pirates.group1.main.sim.util.CellType;

public class Island extends Tile {

	private boolean supply;
	
	/**
	 * Island constructor
	 * @param supply if supply island or not
	 * @param map the map
	 * @param position the position
	 */
	public Island(boolean supply, Map map, Position position) {
		super(map, position);
		this.supply = supply;
	}

	/**
	 * Tells you that this is an island
	 * @return ISLAND always.
	 */
	@Override
	public CellType navigable(Ship ship) {
		return CellType.ISLAND;
	}
	
	/**
	 * Tells you if this is a supply island
	 * @return true if supply island. false otherwise.
	 */
	@Override
	public boolean isSupply() {
		return supply;
	}

}
