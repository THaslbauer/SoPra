package de.unisaarland.cs.st.pirates.group1.sim.gamestuff;

import de.unisaarland.cs.st.pirates.group1.sim.logic.instruction.Instruction;

/**
 * A representation of the factions in the simulator.
 * @author thomas
 *
 */
public class Faction {
	private int score;
	private int shipCount;
	private int factionID;
	private Instruction[] tactics;
	private String name;
	
	/**
	 * create a Faction
	 * @param name The name of the Faction.
	 * @param id The ID of the Faction.
	 * @throws IllegalArgumentException if name is null
	 */
	public Faction(String name, int id) throws IllegalArgumentException{
		if(name == null)
			throw new IllegalArgumentException("A Faction's name should not be null");
		this.name = name;
		this.score = 0;
		this.shipCount = 0;
		this.tactics = null;
		this.factionID = id;
	}
	
	public int getFactionID() {
		return factionID;
	}

	public int getScore() {
		return score;
	}

	/**
	 * Setter for the Faction score
	 * @param score
	 * @throws IllegalArgumentException If Score would be set below 0
	 */
	public void setScore(int score) {
		if(score < 0)
			throw new IllegalArgumentException("Score can't be below 0");
		this.score = score;
	}

	public int getShipCount() {
		return shipCount;
	}

	/**
	 * Setter for the Faction's ship count
	 * @param shipCount
	 * @throws IllegalArgumentException If shipCount would be set below 0
	 */
	public void setShipCount(int shipCount) {
		if(shipCount < 0)
			throw new IllegalArgumentException("Ship count can't be less than 0");
		this.shipCount = shipCount;
	}

	public String getName() {
		return name;
	}

	public Instruction[] getTactics() {
		return this.tactics;
	}
	
	public void setTactics(Instruction[] tactics) {
		this.tactics = tactics;
	}

	/**
	 * increases the ship counter of the Faction
	 * @return The new amount of ships in the faction
	 */
	public int addShip() {
		return ++shipCount;
	}
	
	/**
	 * decreases the ship counter of the Faction
	 * @throws IllegalArgumentException If shipCount would fall below 0
	 * @return The new amount of ships in the faction
	 */
	public int removeShip() {
		if(shipCount == 0)
			throw new UnsupportedOperationException("Ship count is 0, no more ships can be lost.");
		return --shipCount;
	}
	
	/**
	 * increases the Factions score
	 * @param i By how much the score should be increased
	 * @throws IllegalArgumentException if i is below 0 or above 4
	 * (above 4 because at the moment we shouldn't increase score by more than 4 with a ship)
	 * @return The new score
	 */
	public int increaseScore(int i) {
		if(i < 0 || i > 4)
			throw new IllegalArgumentException("Score has to be increased by non-negative value");
		score = score + i;
		return score;
	}
	
	/**
	 * decreases the Factions score by 2
	 * @throws UnsupportedOperationException If score would fall below 0
	 * (last case as we shouldn't decrease by more with repairs)
	 * @return The new score
	 */
	public int decreaseScore() throws UnsupportedOperationException {
		if(score - 2 < 0)
			throw new UnsupportedOperationException("Score can't be negative");
		score = score - 2;
		return score;
	}
	
	/**
	 * gets an Instruction from the Faction logic with the PC
	 * @param pc The PC of the ship that wants an Instruction
	 * @return
	 * @throws IndexOutOfBoundsException if pc wasn't inside of the Faction's Instruction line count
	 */
	public Instruction getInstruction(int pc) throws IndexOutOfBoundsException{
			return this.tactics[pc];
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + factionID;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Faction other = (Faction) obj;
		if (factionID != other.factionID)
			return false;
		else if (!name.equals(other.name))
			return false;
		return true;
	}

}
