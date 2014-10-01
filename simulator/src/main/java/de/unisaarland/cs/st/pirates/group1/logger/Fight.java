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
	
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((attacker == null) ? 0 : attacker.hashCode());
		result = prime * result
				+ ((defender == null) ? 0 : defender.hashCode());
		result = prime * result + (shipOnShip ? 1231 : 1237);
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
		Fight other = (Fight) obj;
		if (attacker == null) {
			if (other.attacker != null)
				return false;
		} else if (!attacker.equals(other.attacker))
			return false;
		if (defender == null) {
			if (other.defender != null)
				return false;
		} else if (!defender.equals(other.defender))
			return false;
		if (shipOnShip != other.shipOnShip)
			return false;
		return true;
	}

	public String toString() {
		return attacker+" FOUGHT "+defender;
	}
}
