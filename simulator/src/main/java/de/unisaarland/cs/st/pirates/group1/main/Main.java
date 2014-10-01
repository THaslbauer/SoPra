/**
 * the main class
 * @author christopher
 * @author thomas
 */

package de.unisaarland.cs.st.pirates.group1.main;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import de.unisaarland.cs.st.pirates.group1.logger.OutLogger;
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
	private static List<InputStream> tacticsFiles;
	
	
	/**
	 * Reads all arguments and VM arguments into the local static fields and then calls construct(true) to build the simulator and start it.
	 * VM arguments are: -Dlog=<logfile>, -Dturns=<cycle count>, -Dseed=<seed>.
	 * Defaults for those are: null, 10000, 19580427
	 * @param args The file paths for map and tactics, map comes first.
	 */
	public static void main(String[] args) {
		
		
		//create the outputfile
		log =  System.getProperty("log");
		
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
	 * @param start The boolean if we should start.
	 */
	private static void construct(boolean start) {
		construct(start, new LinkedList<ExtendedLogWriter>());
	}
	
	/**
	 * Constructs the simulator from the saved values.
	 * @param start the boolean flag to tell the controller to start the simulator
	 * @param loggers the loggers/guis implementing the ExtendedLogWriter interface
	 */
	private static void construct(boolean start, List<ExtendedLogWriter> loggers){
		//build the random for the simulator
		Random rand = new Random(seed);
		//make InfoPoint to collect loggers
		InfoPoint infoPoint = new InfoPoint();
		//initializing reference loggers
		List<LogWriter> refLoggers = new LinkedList<LogWriter>();
		//right now just one logger
		for(String name : LogProvider.supported()) {
			if("DEFAULT".equals(name))
				refLoggers.add(LogProvider.createInstance(name));
		}
		infoPoint.setRefLoggers(refLoggers);
		//add debug logger to extended loggers
		if(System.getProperty("dbg") != null) {
			loggers.add(new OutLogger());
		}
		//set extended loggers
		infoPoint.setGUI(loggers);
		Simulator sim = new Simulator(infoPoint,turns,rand);
		MapParser mapParser = new MapParser();
		TacticsParser tacticsParser = new TacticsParser(infoPoint);
		Controller controller = new Controller(sim, mapParser, tacticsParser, 
				mapFile, tacticsFiles, seed, log);
		//init simulator
		try {
			controller.initializeSimulator();
		}
		catch(IOException e) {
			e.printStackTrace();
			throw new IllegalStateException("Couldn't open file streams \n"+ e.getMessage()+"\n"+e.getCause());
		}
		
		//start controller if start boolean is set
		if(start) {
			//starting controller
			controller.play();
			try {
				//if controller has stopped, close the InfoPoint and thus all loggers
				infoPoint.close();
			} catch (IllegalStateException | IOException e) {
				System.err.println("Failed to close log: "+e.getMessage()+"\n"+e.getCause());
			}
		}
	}
}

