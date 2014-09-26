package de.unisaarland.cs.st.pirates.group1.logger;

import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Kraken;
import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Placable;
import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Ship;

public class Fight extends OptionalLogOperation{
	Placable attacker, defender;
	
	boolean shipOnShip;
	
	public Fight(Ship one, Ship two) {
		attacker = one;
		defender = two;
		shipOnShip = true;
	}
	
	public Fight(Ship one, Kraken two) {
		attacker = one;
		defender = two;
		shipOnShip = false;
	}
	
	public boolean equals(Object o) {
		Fight other;
		try {
			other = (Fight)o;
		} catch(ClassCastException e) {
			return false;
		}
		return other.attacker == this.attacker && other.defender == this.defender;
	}
	
	public String toString() {
		return attacker+" FOUGHT "+defender;
	}
}
