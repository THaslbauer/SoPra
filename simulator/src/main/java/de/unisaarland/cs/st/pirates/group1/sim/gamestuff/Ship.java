package de.unisaarland.cs.st.pirates.group1.sim.gamestuff;

import de.unisaarland.cs.st.pirates.group1.sim.util.Heading;
import de.unisaarland.cs.st.pirates.group1.sim.util.Register;

import static de.unisaarland.cs.st.pirates.group1.sim.util.ThrowHelper.throwIAException;
import static de.unisaarland.cs.st.pirates.group1.sim.util.ThrowHelper.notNegative;

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
	static private final int maxCondition = 3;
	private int condition;
	private int pc;
	private int [] registers;
	private int restTime;
	private Heading heading;

	
	/**
	 * Ship constructor
	 * @param id the ship id
	 * @param tile the tile to place the ship on
	 */
	public Ship(Faction faction, int id, Tile tile) {
		super(id, tile);
		this.load = maxLoad;
		this.morale = maxMorale;
		this.boredom = 0;
		this.condition = maxCondition;
		heading = Heading.H0;
		this.faction = faction;
		clearRegisters();
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

	/**
	 * Sets ship load. 
	 * @param load
	 * @throws IllegalArgumentException if value is too high.
	 */
	public void setLoad(int load) throws IllegalArgumentException {
		notNegative(load);
		this.load = load <= maxLoad ? load : (int) throwIAException("SETTER: Load too high");
		/*
		 * Equivalent to:
		 *  if(load <= maxLoad) {
		 *  	this.load = load;
		 *  } else {
		 *  	throw new IllegalArgumentException("SETTER: Load too high");
		 *  }
		 */
	}

	public int getMorale() {
		return morale;
	}

	/**
	 * Sets ship morale. 
	 * @param morale
	 * @throws IllegalArgumentException if value is too high.
	 */
	public void setMorale(int morale) throws IllegalArgumentException {
		notNegative(morale);
		this.morale = morale <= maxMorale ? morale : (int) throwIAException("SETTER: Morale too high");
	}

	public int getPC() {
		return pc;
	}

	public void setPC(int pc) {
		notNegative(pc);
		this.pc = pc;
	}

	public int getRestTime() {
		return restTime;
	}

	public void setRestTime(int restTime) {
		notNegative(restTime);
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

	/**
	 * Sets ship condition. 
	 * @param condition
	 * @throws IllegalArgumentException if value is too high.
	 */
	public void setCondition(int condition) throws IllegalArgumentException {
		notNegative(condition);
		this.condition = condition <= maxCondition ? condition : (int) throwIAException("SETTER: Morale too high");
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
	
	public static int getMaxcondition() {
		return maxCondition;
	}

}
