package de.unisaarland.cs.st.pirates.group1.sim.gamestuff;

import de.unisaarland.cs.st.pirates.group1.sim.util.Heading;
import de.unisaarland.cs.st.pirates.group1.sim.util.Register;

/**
 * The representation of a pirate ship
 * @author Jens Kreber
 *
 */
public class Ship extends Placable {
	private Faction faction;
	static private final int maxLoad = 4;
	private int load;
	static private final int maxMorale = 4;
	private int morale;
	static private final int maxBoredom = 40;
	private int boredom;
	private int pc;
	private int [] registers;
	private int restTime;
	private Heading heading;
	private int condition;
	
	/**
	 * Ship constructor
	 * @param id the ship id
	 * @param tile the tile to place the ship on
	 */
	public Ship(Faction faction, int id, Tile tile) {
		super(id, tile);
		this.load = maxLoad;
	}
	
	/**
	 * Executes a cycle for this ship
	 */
	public void step() {
		//TODO
	}
	
	/**
	 * Clears all registers (to unset / false)
	 */
	public void clearRegisters() {
		//TODO
	}
	
	/**
	 * Get the value of a certain register.
	 * @param register the register to get the value from
	 * @return the value of sense and ship state registers
	 */
	public int getRegister(Register register) {
		//TODO
		return 0;
	}
	
	/**
	 * Sets a certain register
	 * @param register the register to set
	 * @param value the value to set
	 */
	public void setRegister(Register register, int value) {
		//TODO
	}
	
	/**
	 * Resets the boredom of this ship
	 */
	public void resetBoredom() {
		//TODO
	}
	
	/**
	 * Increases the boredom of this ship by one
	 */
	public void increaseBoredom() {
		//TODO
	}
	
	/**
	 * Increases the program counter of this ship by one
	 */
	public void increasePC() {
		//TODO
	}

	
	public int getLoad() {
		return load;
	}

	public void setLoad(int load) {
		this.load = load;
	}

	public int getMorale() {
		return morale;
	}

	public void setMorale(int morale) {
		this.morale = morale;
	}

	public int getPC() {
		return pc;
	}

	public void setPC(int pc) {
		this.pc = pc;
	}

	public int getRestTime() {
		return restTime;
	}

	public void setRestTime(int restTime) {
		this.restTime = restTime;
	}

	public Heading getHeading() {
		return heading;
	}

	public void setHeading(Heading heading) {
		this.heading = heading;
	}

	public int getCondition() {
		return condition;
	}

	public void setCondition(int condition) {
		this.condition = condition;
	}

	public Faction getFaction() {
		return faction;
	}

	public static int getMaxload() {
		return maxLoad;
	}

	public static int getMaxmorale() {
		return maxMorale;
	}

	public static int getMaxboredom() {
		return maxBoredom;
	}

	public int getBoredom() {
		return boredom;
	}

}
