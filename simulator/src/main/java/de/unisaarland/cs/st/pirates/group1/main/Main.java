/**
 * the main class
 * @author christopher
 * @author thomas
 */

package de.unisaarland.cs.st.pirates.group1.main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import de.unisaarland.cs.st.pirates.group1.sim.driver.Controller;
import de.unisaarland.cs.st.pirates.group1.sim.driver.Simulator;
import de.unisaarland.cs.st.pirates.group1.sim.logger.ExtendedLogWriter;
import de.unisaarland.cs.st.pirates.group1.sim.logger.InfoPoint;
import de.unisaarland.cs.st.pirates.group1.sim.parser.MapParser;
import de.unisaarland.cs.st.pirates.group1.sim.parser.TacticsParser;
import de.unisaarland.cs.st.pirates.logger.LogProvider;
import de.unisaarland.cs.st.pirates.logger.LogWriter;


public class Main {
	
	private static String log;
	private static int turns;
	private static long seed;
	private static InputStream mapFile;
	private static OutputStream logFile;
	private static List<InputStream> tacticsFiles;
	
	public static void main(String[] args) {
		
		
		//create the outputfile
		log =  System.getProperty("log");
				if(log == null){
					logFile = null;
				}else{
				try {
					logFile = new FileOutputStream(log);
				} catch (FileNotFoundException e1) {
					throw new IllegalArgumentException();
					}
				}
		
		//create the number of turns
		String turn = System.getProperty("turns");
			if(turn == null){
				turns = 10000;
			}else{
				turns = Integer.parseInt(turn);
			}
		
		//create the seed
		String seedStr = System.getProperty("seed");
			if(seedStr == null){
				seed = 19580427;
			}else{
				seed = Integer.parseInt(seedStr);
			}
		
		
		//get the mapString
		try {
			mapFile = new FileInputStream(args[0]);
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException("there is no Mapfile!");
		}
		
		tacticsFiles = new LinkedList<InputStream>();
		
		for(int counter = 1; counter < args.length; counter++){
			try {
				tacticsFiles.add(new FileInputStream(args[counter]));
			} catch (FileNotFoundException e) {
				throw new IllegalArgumentException("tacticsfile not found at specified path");
			}
		}
		
		//call the construct method with a boolean flag to start
		construct(true);
	}
	
	/**
	 * constructs the simulator from the saved values
	 */
	private static void construct(boolean start) {
		construct(start, new LinkedList<ExtendedLogWriter>());
	}
	
	/**
	 * constructs the simulator from the saved values
	 * @param start the boolean flag to tell the controller to start the simulator
	 * @param loggers the loggers/guis implementing the ExtendedLogWriter interface
	 */
	private static void construct(boolean start, List<ExtendedLogWriter> loggers){
		Random rand = new Random(seed);
		InfoPoint infoPoint = new InfoPoint();
		infoPoint.setRefLogger(LogProvider.createInstance("RefLogger"));
		Simulator sim = new Simulator(infoPoint,turns,rand);
		MapParser mapParser = new MapParser();
		TacticsParser tacticsParser = new TacticsParser(infoPoint);
		Controller controller = new Controller(sim, mapParser, tacticsParser, 
				mapFile, tacticsFiles, seed, logFile);
		
		if(start)
			controller.play();
	}
}

