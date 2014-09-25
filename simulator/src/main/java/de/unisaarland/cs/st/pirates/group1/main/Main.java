package de.unisaarland.cs.st.pirates.group1.main;

import de.unisaarland.cs.st.pirates.group1.sim.driver.Simulator;
import de.unisaarland.cs.st.pirates.group1.sim.logger.InfoPoint;


public class Main {
	
	private static String log;
	private static int turns;
	private static long seed;
	
	public static void main(String[] args) {
		log =  System.getProperty("log");
		turns = Integer.parseInt(System.getProperty("turns"));
		seed = Long.parseLong(System.getProperty("seed"));
		
	}
}

