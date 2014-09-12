package de.unisaarland.cs.st.pirates.group1.main.sim.gamestuff;

public abstract class Placable {
	private final int id;
	private Tile myTile;
	
	/**
	 * Placable constructor
	 * Calls setMyTile()
	 * @param id the unique id (for every kind) of this Placable 
	 * @param tile the tile this thing should be on
	 * @see setMyTile()
	 */
	public Placable(int id, Tile tile) {
		this.id = id;
		setMyTile(tile);
	}

	public Tile getMyTile() {
		return myTile;
	}
	
	/**
	 * Moves this Placable to the specified tile.
	 * Detaches this Placable from the old tile and attaches it to the new one.
	 * @param myTile
	 */
	public void setMyTile(Tile myTile) {
		//TODO: call attach / detach
		this.myTile = myTile;
	}

	public int getId() {
		return id;
	}

}
