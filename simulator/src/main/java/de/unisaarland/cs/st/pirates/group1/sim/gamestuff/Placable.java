package de.unisaarland.cs.st.pirates.group1.sim.gamestuff;

import static de.unisaarland.cs.st.pirates.group1.sim.util.ThrowHelper.notNegative;
import de.unisaarland.cs.st.pirates.group1.sim.util.IllegalCallException;

/**
 * Abstract class for game objects which can be placed on the map and identified with an id
 * @author Jens Kreber
 *
 */
public abstract class Placable {
	protected final int id;
	protected Tile myTile;
	
	/**
	 * Placable constructor
	 * Calls setMyTile()
	 * @param id the unique id (for every kind) of this Placable 
	 * @param tile the tile this thing should be on
	 * @throws IllegalArgumentException If the placable can't be placed on the specified tile
	 * @see setMyTile()
	 */
	public Placable(int id, Tile tile) throws IllegalArgumentException {
		notNegative(id);
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
	 * @throws IllegalArgumentException if the placable can't be moved to destination
	 */
	public void setMyTile(Tile tile) throws IllegalArgumentException {
		Tile oldTile = myTile;
		myTile = tile;
		try{
			if(oldTile != null)
				detachFrom(oldTile);
			if(tile != null)
				attachTo(tile);
		} catch (IllegalCallException e) {
			throw new IllegalArgumentException("Destination tile is occupied");
		}
	}
	
	protected abstract void detachFrom(Tile tile) throws IllegalCallException, IllegalArgumentException;
	
	protected abstract void attachTo(Tile tile) throws IllegalCallException;
	
	public int getId() {
		return id;
	}

}
