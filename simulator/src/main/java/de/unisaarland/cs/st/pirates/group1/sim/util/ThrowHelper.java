package de.unisaarland.cs.st.pirates.group1.sim.util;

public class ThrowHelper {
	public static <T> T throwIAException (String s) throws IllegalArgumentException {
		throw new IllegalArgumentException(s);
	}
	
	public static void notNegative (int value) throws IllegalArgumentException {
		if(value < 0)
			throw new IllegalArgumentException("Value is negative");
	}
}
