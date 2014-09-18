package de.unisaarland.cs.st.pirates.group1.sim.gamestuff;

import de.unisaarland.cs.st.pirates.group1.sim.util.Direction;
import de.unisaarland.cs.st.pirates.group1.sim.util.Heading;
import de.unisaarland.cs.st.pirates.group1.sim.util.IllegalCallException;

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
		int dir = myTile.getWorldmap().nextInt(6);
		Heading heading = Heading.values()[dir];
		try {
			Tile aim = myTile.getNeighbour(heading, Direction.D0);
			if(aim instanceof Island)
				return;
			setMyTile(aim); // move to aim
		} catch (IllegalArgumentException e) { // Not in the world anymore
			//do nothing
		}
	}

	@Override
	protected void detachFrom(Tile tile) throws IllegalCallException, IllegalArgumentException {
		tile.detach(this);
	}

	@Override
	protected void attachTo(Tile tile) throws IllegalCallException {
		tile.attach(this);
	}
}
