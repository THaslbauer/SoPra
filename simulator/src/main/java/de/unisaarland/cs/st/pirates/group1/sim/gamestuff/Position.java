package de.unisaarland.cs.st.pirates.group1.sim.gamestuff;

import static de.unisaarland.cs.st.pirates.group1.sim.util.ThrowHelper.notNegative;

/**
 * A small class to represent a positon on a map. Read only.
 * @author Jens Kreber
 *
 */
public class Position {
	public final int x, y;
	
	/**
	 * Position constructor
	 * Creates a position. Coordinates can't be changed later
	 * @param x the x-coordinate.
	 * @param y the y-coordinate. really.
	 */
	public Position(int x, int y) {
		notNegative(x);
		notNegative(y);
		this.x = x;
		this.y = y;
	}
}
