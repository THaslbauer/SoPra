package de.unisaarland.cs.st.pirates.group1.sim.gamestuff;

import de.unisaarland.cs.st.pirates.group1.sim.util.CellType;

/**
 * The representation of a piece of sea on the map
 * @author Jens Kreber
 *
 */
public class Sea extends Tile {
	
	/**
	 * Sea constructor
	 * Creates a sea tile
	 * @param map the map this tile should be on
	 * @param position the position of this tile
	 */
	public Sea(Worldmap map, Position position) {
		super(map, position);
	}
	
	/**
	 * Tells you that you can generally move here
	 * @return true
	 */
	@Override
	public CellType navigable(Ship ship) {
		return CellType.EMPTY;
	}
	
	/**
	 * Tells you that this ain't a supply island
	 * @return false
	 */
	@Override
	public boolean isSupply() {
		return false;
	}

}
