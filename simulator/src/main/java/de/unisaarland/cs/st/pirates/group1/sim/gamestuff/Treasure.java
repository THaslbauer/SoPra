package de.unisaarland.cs.st.pirates.group1.sim.gamestuff;

import static de.unisaarland.cs.st.pirates.group1.sim.util.ThrowHelper.notNegative;


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
	public Treasure(int value, int id, Tile tile) {
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
	
}
