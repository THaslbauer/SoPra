package de.unisaarland.cs.st.pirates.group1.main.sim.gamestuff;

import de.unisaarland.cs.st.pirates.group1.main.sim.util.CellType;

public class Base extends Tile {

	private Faction faction;
	
	/**
	 * The base constructor
	 * @param faction the faction of the base
	 * @param map the map this tile is on
	 * @param position the position of this tile
	 */
	public Base(Faction faction, Map map, Position position) {
		super(map, position);
		this.faction = faction;
	}

	/**
	 * Accessibility of this Base
	 * @return HOME / ENEMYHOME based on the ship's faction
	 */
	@Override
	public CellType navigable(Ship ship) {
		return ship.getFaction() == faction ? CellType.HOME : CellType.ENEMYHOME;
	}

	/**
	 * Checks if this is a supply island
	 * @return false
	 */
	@Override
	public boolean isSupply() {
		return false;
	}

	
}
