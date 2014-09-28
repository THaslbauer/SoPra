package de.unisaarland.cs.st.pirates.group1.tests.testUtil;

import java.util.Random;


public class SRandom extends Random {
	
	/**
	 * I have no idea what this does.
	 */
	private static final long serialVersionUID = 618966912020603967L;
	
	private long seed;
	private int lastInt;
	
	public SRandom(long seed) {
		super(seed);
		this.seed = seed;
	}
	public int nextInt(int bits) {
		lastInt = super.nextInt(bits);
		return lastInt;
	}
	
	public long getSeed() {
		return seed;
	}
	
	public int getLastInt() {
		return lastInt;
	}
}