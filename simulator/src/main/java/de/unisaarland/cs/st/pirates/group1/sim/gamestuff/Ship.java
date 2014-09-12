package de.unisaarland.cs.st.pirates.group1.main.sim.gamestuff;

import de.unisaarland.cs.st.pirates.group1.main.sim.util.Heading;

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
	 * @param id
	 * @param tile
	 */
	public Ship(Faction faction, int id, Tile tile) {
		super(id, tile);
		this.load = maxLoad;
	}
	
	//TODO: A lot of stuff

}
