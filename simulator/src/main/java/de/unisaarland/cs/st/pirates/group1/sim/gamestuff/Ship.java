package de.unisaarland.cs.st.pirates.group1.sim.gamestuff;

import de.unisaarland.cs.st.pirates.group1.sim.util.Heading;

/**
 * The representation of a pirate ship
 * @author Jens Kreber
 *
 */
public class Ship extends Placable {
	private Faction faction;
	static private final int maxLoad = 4;
	private int load;
	static private final int maxMorale = 4;
	private int morale;
	static private final int maxBoredom = 40;
	private int boredom;
	private int pc;
	private int [] registers;
	private int restTime;
	private Heading heading;
	private int condition;
	
	/**
	 * Ship constructor
	 * @param id the ship id
	 * @param tile the tile to place the ship on
	 */
	public Ship(Faction faction, int id, Tile tile) {
		super(id, tile);
		this.load = maxLoad;
	}
	
//TODO

}
