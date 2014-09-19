package de.unisaarland.cs.st.pirates.group1.sim.gamestuff;

import static de.unisaarland.cs.st.pirates.group1.sim.util.ThrowHelper.notNegative;
import de.unisaarland.cs.st.pirates.group1.sim.util.IllegalCallException;


/**
 * The representation of a treasure
 * @author Jens Kreber
 *
 */
public class Treasure extends Placable {
	private int value;
	
	/**
	 * Treasure constructor.
	 * @param value the value of the new treasure package
	 * @param id the id of the treasure
	 * @param tile the tile the treasure should be placed on
	 */
	public Treasure(int value, int id, Tile tile) throws IllegalArgumentException {
		super(id, tile);
		setValue(value);
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		notNegative(value);
		this.value = value;
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
