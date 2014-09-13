package de.unisaarland.cs.st.pirates.group1.sim.gamestuff;

import java.lang.reflect.Array;

import de.unisaarland.cs.st.pirates.group1.sim.logic.instruction.Instruction;

/**
 * A representation of the factions in the simulator.
 * @author thomas
 *
 */
public class Faction {
	private int score;
	private int shipCount;
	private Instruction[] tactics;
	
	/**
	 * create a Faction
	 * @param name
	 */
	public Faction(String name){
		this.name = name;
		this.score = 0;
		this.shipCount = 0;
		this.tactics = null;
	}
	
	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getShipCount() {
		return shipCount;
	}

	public void setShipCount(int shipCount) {
		this.shipCount = shipCount;
	}

	public String getName() {
		return name;
	}

	public Instruction[] getTactics(){
		return this.tactics.clone();
	}
	
	public void setTactics(Instruction[] tactics) {
		this.tactics = tactics;
	}

	private String name;
	
	/**
	 * increases the ship counter of the Faction
	 */
	public void addShip(){
		//TODO implement
	}
	
	/**
	 * decreases the ship counter of the Faction
	 */
	public void removeShip(){
		//TODO implement
	}
	
	/**
	 * increases the Factions score
	 * @param i
	 */
	public void increaseScore(int i){
		//TODO implement
	}
	
	/**
	 * decreases the Factions score
	 * @param i
	 */
	public void decreaseScore(int i){
		//TODO implement
	}
	
	/**
	 * gets an Instruction from the Faction logic with the PC
	 * @param pc The PC of the ship that wants an Instruction
	 * @return
	 */
	public Instruction getInstruction(int pc){
		//TODO implement
		return null;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
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
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
}
