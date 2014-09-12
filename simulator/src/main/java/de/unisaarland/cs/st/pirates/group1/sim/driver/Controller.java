/**
 * The Controller class. Starts the Parser, Controls the simulator and gets orders from the GUI
 * 
 * @version 1.0
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
	 * Initializes the Simulator.
	 * 
	 * @param
	 * @return void
	 */
public void initializeSimulator(){
	
}

	/**
	 * The order for the Simulator to do one step.
	 * 
	 * @param
	 * @return void
	 */
public void play(){
	
}

	/**
	 * The order for the Simulator to pause.
	 * 
	 * @param
	 * @return void
	 */
public void pause(){
	
}

}
