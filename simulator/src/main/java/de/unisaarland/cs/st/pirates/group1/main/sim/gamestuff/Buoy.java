package de.unisaarland.cs.st.pirates.group1.main.sim.gamestuff;

public class Buoy extends Placable {
	private final int type;
	private final Faction faction;
	
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
