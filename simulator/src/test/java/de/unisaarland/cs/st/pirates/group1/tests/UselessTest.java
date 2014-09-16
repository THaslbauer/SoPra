package de.unisaarland.cs.st.pirates.group1.tests;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

import de.unisaarland.cs.st.pirates.group1.sim.util.IllegalCallException;
import de.unisaarland.cs.st.pirates.group1.sim.util.ThrowHelper;

import sun.applet.Main;

public class UselessTest {
	
	@Test
	public void throwHelperTest() {
		ThrowHelper th = new ThrowHelper();
		try {
			th.notNegative(-24, "Yay, it's negative!");
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
