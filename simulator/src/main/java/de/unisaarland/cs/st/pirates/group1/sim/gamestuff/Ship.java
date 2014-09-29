package de.unisaarland.cs.st.pirates.group1.sim.gamestuff;

import de.unisaarland.cs.st.pirates.group1.main.Main;
import de.unisaarland.cs.st.pirates.group1.sim.logic.instruction.Instruction;
import de.unisaarland.cs.st.pirates.group1.sim.util.Heading;
import de.unisaarland.cs.st.pirates.group1.sim.util.IllegalCallException;
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
	static private final int maxMorale = 4;
	static private final int maxBoredom = 40;
	private int boredom;
	static private final int maxCondition = 3;
	private int pc;
	private int [] registers = new int[18];
	private int restTime;
	private Heading heading;

	
	/**
	 * Ship constructor
	 * increases faction ship count
	 * @param id the ship id
	 * @param tile the tile to place the ship on
	 */
	public Ship(Faction faction, int id, Tile tile) throws IllegalArgumentException {
		super(id, tile);
		registers[Register.SHIP_LOAD.ordinal()] = 0;
		registers[Register.SHIP_MORAL.ordinal()] = maxMorale;
		this.boredom = 0;
		registers[Register.SHIP_CONDITION.ordinal()] = maxCondition;
		heading = Heading.H0;
		registers[Register.SHIP_DIRECTION.ordinal()] = heading.ordinal();
		this.faction = faction;
		clearSenseRegisters();
		faction.addShip();
	}
	
	/**
	 * Executes a cycle for this ship
	 * @throws ArrayIndexOutOfBoundsException if the pc is illegal
	 */
	public void step() throws ArrayIndexOutOfBoundsException {
		Instruction i = faction.getTactics()[pc]; /* THIS THROWS AN EXCEPTION */
		i.execute(this);
	}
	
	/**
	 * Clears all registers (to unset / false)
	 */
	public void clearSenseRegisters() {
		
		registers[0] = -1;	//SENSE_CELLTYPE,
		registers[1] = 0;	//SENSE_SUPPLY,
		registers[2] = 0;	//SENSE_TREASURE,
		registers[3] = 0;	//SENSE_MARKER0,
		registers[4] = 0;	//SENSE_MARKER1,
		registers[5] = 0;	//SENSE_MARKER2,
		registers[6] = 0;	//SENSE_MARKER3,
		registers[7] = 0;	//SENSE_MARKER4,
		registers[8] = 0;	//SENSE_MARKER5,
		registers[9] = 0;	//SENSE_ENEMYMARKER,
		registers[10] = -1;	//SENSE_SHIPTYPE,
		registers[11] = -1;	//SENSE_SHIPDIRECTION,
		registers[12] = -1;	//SENSE_SHIPLOADED,
		registers[13] = -1;	//SENSE_SHIPCONDITION,
	}
	
	/**
	 * Get the value of a certain register.
	 * @param register the register to get the value from
	 * @return the value of sense and ship state registers
	 */
	public int getRegister(Register register) {
		return registers[register.ordinal()];
	}
	
	/**
	 * Sets a certain register
	 * @param register the register to set
	 * @param value the value to set
	 */
	public void setRegister(Register register, int value) {
		registers[register.ordinal()] = value;
	}
	
	/**
	 * Resets the boredom of this ship
	 */
	public void resetBoredom() {
		boredom = 0;
	}
	
	/**
	 * Increases the boredom of this ship by one
	 */
	public void increaseBoredom() {
		boredom += 1;
	}
	
	/**
	 * Increases the program counter of this ship by one
	 */
	public int increasePC() {
		return ++pc;
	}

	
	public int getLoad() {
		return registers[Register.SHIP_LOAD.ordinal()];
	}

	/**
	 * Sets ship load. 
	 * @param load
	 * @throws IllegalArgumentException if value is too high.
	 */
	public void setLoad(int load) throws IllegalArgumentException {
		notNegative(load);
		registers[Register.SHIP_LOAD.ordinal()] = load <= maxLoad ? load : (int) throwIAException("SETTER: Load too high");
		/*
		 * Equivalent to:
		 *  if(load <= maxLoad) {
		 *  	registers[Register.SHIP_LOAD.ordinal()] = load;
		 *  } else {
		 *  	throw new IllegalArgumentException("SETTER: Load too high");
		 *  }
		 */
	}

	public int getMorale() {
		return registers[Register.SHIP_MORAL.ordinal()];
	}

	/**
	 * Sets ship morale. 
	 * @param morale
	 * @throws IllegalArgumentException if value is too high.
	 */
	public void setMorale(int morale) throws IllegalArgumentException {
		notNegative(morale);
		registers[Register.SHIP_MORAL.ordinal()] = morale <= maxMorale ? morale : (int) throwIAException("SETTER: Morale too high");
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
		notNegative(restTime);
		this.restTime = restTime <= 8 ? restTime : (int) throwIAException("Resttime tooooooo hiiigh");
	}

	public Heading getHeading() {
		return heading;
	}

	public void setHeading(Heading heading) {
		this.heading = heading;
	}

	public int getCondition() {
		return registers[Register.SHIP_CONDITION.ordinal()];
	}

	/**
	 * Sets ship condition. 
	 * @param condition
	 * @throws IllegalArgumentException if value is too high.
	 */
	public void setCondition(int condition) throws IllegalArgumentException {
		notNegative(condition);
		registers[Register.SHIP_CONDITION.ordinal()] = condition <= maxCondition ? condition : (int) throwIAException("SETTER: Morale too high");
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
	
	public static int getMaxCondition() {
		return maxCondition;
	}

	@Override
	protected void detachFrom(Tile tile) throws IllegalCallException, IllegalArgumentException {
		tile.detach(this);
	}

	@Override
	protected void attachTo(Tile tile) throws IllegalCallException {
		tile.attach(this);
	}

	public int[] getRegisters() {
		return registers;
	}
	
	public String toString() {
		int x, y = 0, f;
		if(myTile != null) {
			x = myTile.getPosition().x;
			y = myTile.getPosition().y;
		} else {
			x = -1;
		}
		f = faction == null ? -1 : faction.getFactionID();
		return "Ship" + (x==-1? "" : " ("+x+","+y+")")+(f == -1 ? "" : ": "+faction.getName()+" ("+f+")");
	}
	
}
