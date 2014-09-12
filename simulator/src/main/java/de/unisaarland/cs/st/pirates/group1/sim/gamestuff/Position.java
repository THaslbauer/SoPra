package de.unisaarland.cs.st.pirates.group1.main.sim.gamestuff;

public class Position {
	public final int x, y;
	
	/**
	 * Position constructor
	 * Creates a position. Coordinates can't be changed later
	 * @param x the x-coordinate.
	 * @param y the y-coordinate. really.
	 */
	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
