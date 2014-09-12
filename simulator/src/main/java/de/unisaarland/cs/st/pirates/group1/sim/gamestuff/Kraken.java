package de.unisaarland.cs.st.pirates.group1.sim.gamestuff;

/**
 * Representation of a Kraken on the sea
 * @author Jens Kreber
 *
 */
public class Kraken extends Placable {

	/** 
	 * Creates a gigantic Kraken
	 * @param id the id
	 * @param tile the tile the kraken should destroy everything on
	 */
	public Kraken(int id, Tile tile) {
		super(id, tile);
	}
	
	/**
	 * Do everything a kraken's brain is capable of
	 * The Kraken tries to move to a random direction.
	 * It fails if there's an island or another kraken in the way.
	 * That's alle it does.
	 */
	public void step() {
		
	}
}
