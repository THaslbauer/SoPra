package de.unisaarland.cs.st.pirates.group1.main.sim.gamestuff;

public class Treasure extends Placable {
	private int value;
	
	public Treasure(int value, int id, Tile tile) {
		super(id, tile);
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
	
}
