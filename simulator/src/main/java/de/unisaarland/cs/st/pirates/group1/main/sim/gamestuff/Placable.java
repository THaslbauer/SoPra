package de.unisaarland.cs.st.pirates.group1.main.sim.gamestuff;

public abstract class Placable {
	private final int id;
	private Tile myTile;
	
	public Placable(int id, Tile tile) {
		this.id = id;
		this.myTile = tile;
	}

	public Tile getMyTile() {
		return myTile;
	}

	public void setMyTile(Tile myTile) {
		//TODO: call attach / detach
		this.myTile = myTile;
	}

	public int getId() {
		return id;
	}

}
