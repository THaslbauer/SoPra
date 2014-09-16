package de.unisaarland.cs.st.pirates.group1.sim.gamestuff;

import de.unisaarland.cs.st.pirates.group1.sim.util.CellType;

/**
 * The representation of a pirate base tile in the map
 * @author Jens Kreber
 *
 */
public class Base extends Tile {

	private Faction faction;
	
	/**
	 * The base constructor
	 * @param faction the faction of the base
	 * @param map the map this tile is on
	 * @param position the position of this tile
	 */
	public Base(Faction faction, Worldmap map, Position position) {
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
	
	//TODO TEST
	public Faction getFacion() {
		return faction;
	}

}
