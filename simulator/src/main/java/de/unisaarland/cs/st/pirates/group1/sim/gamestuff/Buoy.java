package de.unisaarland.cs.st.pirates.group1.sim.gamestuff;

/**
 * Represents a Buoy on the far sea
 * @author Jens Kreber
 *
 */
public class Buoy extends Placable {
	private final int type;
	private final Faction faction;
	
	/**
	 * Buoy constructor
	 * @param type the information of the buoy
	 * @param faction the faction
	 * @param id the unique buoy-id
	 * @param tile the tile this buoy is on
	 */
	public Buoy(int type, Faction faction, int id, Tile tile) {
		super(id, tile);
		this.type = type;
		this.faction = faction;
	}

	public int getType() {
		return type;
	}

	public Faction getFaction() {
		return faction;
	}
	
}
