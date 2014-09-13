/**
 * The Controller class. Starts the Parser, Controls the simulator and gets orders from the GUI
 * 
 * @version 1.1
 * @author christopher
 */

package de.unisaarland.cs.st.pirates.group1.sim.driver;

import java.io.InputStream;
import java.util.List;

import de.unisaarland.cs.st.pirates.group1.sim.parser.MapParser;
import de.unisaarland.cs.st.pirates.group1.sim.parser.TacticsParser;

public class Controller {
	
	private Simulator simulator;
	private MapParser mapParser;
	private TacticsParser tacticsParser;
	private InputStream mapFile;
	private List<InputStream> tacticsFile;

	/**
	 * the Constructor for the Controller
	 * 
	 * @param simulator the simulator which the controller controls
	 * @param mapParser the Map parser which the controller starts
	 * @param tacticsParser the Tactic parser which the controller starts
	 * @param mapFile the mapfile, for parsing the map
	 * @param tacticsFile the tacticsfile for parsing the tactics
	 */
public Controller(Simulator simulator, MapParser mapParser,
		TacticsParser tacticsParser, InputStream mapFile, List<InputStream> tacticsFile){
	this.simulator = simulator;
	this.mapParser = mapParser;
	this.tacticsParser = tacticsParser;
	this.mapFile = mapFile;
	this.tacticsFile = tacticsFile;
	
}
	
	/*
	 * 
	 * 
	 * Setter and Getter
	 */
	
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
	 * Initializes the Simulator.
	 * 
	 * @param
	 * @return void
	 */
public void initializeSimulator(){
	/*TODO IMPLEMENT THIS
	 * 
	 */
	
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
