package de.unisaarland.cs.st.pirates.group1.tests;

import static org.junit.Assert.fail;

import org.junit.Test;

import de.unisaarland.cs.st.pirates.group1.sim.util.IllegalCallException;
import de.unisaarland.cs.st.pirates.group1.sim.util.ThrowHelper;

public class UselessTest {
	
	@Test
	public void throwHelperTest() {
		try {
			ThrowHelper.notNegative(-24, "Yay, it's negative!");
			fail();
		} catch(IllegalArgumentException e) {
			//nice
		}
	}
	
	@Test
	public void catchMeIfYouCanTest() {
		try {
			throw new IllegalCallException();
		} catch (IllegalCallException e) {
			//nice
		}
	}
}
