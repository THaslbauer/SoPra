/**
 * The Controller class. Starts the Parser, Controls the simulator and gets orders from the GUI
 * 
 * @version 1.1
 * @author christopher
 */

package de.unisaarland.cs.st.pirates.group1.sim.driver;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Faction;
import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Worldmap;
import de.unisaarland.cs.st.pirates.group1.sim.logic.instruction.Instruction;
import de.unisaarland.cs.st.pirates.group1.sim.parser.MapParser;
import de.unisaarland.cs.st.pirates.group1.sim.parser.TacticsParser;

public class Controller {
	
	private Simulator simulator;
	private MapParser mapParser;
	private TacticsParser tacticsParser;
	private InputStream mapFile;
	private List<InputStream> tacticsFile;
	//TODO CHECK IF THIS IS RIGHT
	//the seed given from the main/command line
	private int seed;
	//the random object
	private Random random;
	private OutputStream output;

	/**
	 * the Constructor for the Controller
	 * 
	 * @param simulator the simulator which the controller controls
	 * @param mapParser the Map parser which the controller starts
	 * @param tacticsParser the Tactic parser which the controller starts
	 * @param mapFile the mapfile, for parsing the map
	 * @param tacticsFile the tacticsfile for parsing the tactics
	 * @param seed the integer from the commandline giving us a valid seed
	 * @param output the Output Steam, which is needed so the Controller can initialize the InfoPoint
	 */
public Controller(Simulator simulator, MapParser mapParser,
		TacticsParser tacticsParser, InputStream mapFile, List<InputStream> tacticsFile,int seed, OutputStream output){
	this.simulator = simulator;
	this.mapParser = mapParser;
	this.tacticsParser = tacticsParser;
	this.mapFile = mapFile;
	this.tacticsFile = tacticsFile;
	this.seed = seed;
	this.random = new Random(seed);
	this.output = output;
	
}
	
	/*
	 * 
	 * 
	 * Setter and Getter
	 */
public int getSeed() {
	return seed;
}

public void setSeed(int seed) {
	this.seed = seed;
}

public Random getRandom() {
	return random;
}

public void setRandom(Random random) {
	this.random = random;
}

public OutputStream getOutput() {
	return output;
}

public void setOutput(OutputStream output) {
	this.output = output;
}
	
	public Simulator getSimulator() {
		return simulator;
	}

	public void setSimulator(Simulator simulator) {
		this.simulator = simulator;
	}

	public MapParser getMapParser() {
		return mapParser;
	}

	public void setMapParser(MapParser mapParser) {
		this.mapParser = mapParser;
	}

	public TacticsParser getTacticsParser() {
		return tacticsParser;
	}

	public void setTacticsParser(TacticsParser tacticsParser) {
		this.tacticsParser = tacticsParser;
	}

	public InputStream getMapFile() {
		return mapFile;
	}

	public void setMapFile(InputStream mapFile) {
		this.mapFile = mapFile;
	}

	public List<InputStream> getTacticsFile() {
		return tacticsFile;
	}

	public void setTacticsFile(List<InputStream> tacticsFile) {
		this.tacticsFile = tacticsFile;
	}

	/**
	 * converts the inputStream to String, taken from stackoverflow.com
	 * @param is
	 * @return the String that represents the map
	 */
	
		private static String convertStreamToString(java.io.InputStream is) {
		    java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
		    return s.hasNext() ? s.next() : "";
		}
		
	/**
	 * Initializes the Simulator and also the Infopoint
	 * 
	 * @param
	 * @return void
	 */
public void initializeSimulator(){
	
	List<String> stringList = new LinkedList<String>();
	for(InputStream in : tacticsFile){
		stringList.add(convertStreamToString(in));
	}
	String[] strings = (String[])stringList.toArray();
	
	//initializes the LogWriter
	simulator.getLogWriter().init(output, convertStreamToString(mapFile) , strings);
	
	
	//sets the randomobject to the simulator
	simulator.setRandom(random);
	
	//parses the map and give it to the simulator
	mapParser.parseMap(mapFile, simulator);
	
	//get the factionarray
	List<Faction> factions = simulator.getFactions();
	
	int factionCounter = 0;
	//check if there are enough tacticsfiles for the factions
	if(factions.size() <= tacticsFile.size()){
	//iterate through the tacticsfile
	for(InputStream tactic : tacticsFile){
		Instruction[] instrArray = tacticsParser.parseTactics(tactic, random);
		try{
		factions.get(0).setTactics(instrArray);
		//breaks out from the loop, if there are no factions left to add tactics to
		}catch(IndexOutOfBoundsException e){
			break;
		}
		factionCounter++;
	}
	}else{
		throw new IllegalArgumentException("There are not enough tactics for the factions");
	}
	
}

	/**
	 * The order for the Simulator to do one step.
	 * 
	 * @param
	 * @return void
	 */
public void play(){
	/*
	 * TODO IMPLEMENT THIS
	 */
	
}

	/**
	 * The order for the Simulator to pause.
	 * 
	 * @param
	 * @return void
	 */
public void pause(){
	/*
	 * TODO IMPLEMENT THIS
	 */
	
}

}
