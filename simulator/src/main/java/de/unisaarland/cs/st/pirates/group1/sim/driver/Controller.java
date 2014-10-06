/**
 * The Controller class. Starts the Parser, Controls the simulator and gets orders from the GUI
 * 
 * @version 1.1
 * @author christopher
 * @author thomas
 */

package de.unisaarland.cs.st.pirates.group1.sim.driver;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Semaphore;

import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Faction;
import de.unisaarland.cs.st.pirates.group1.sim.logger.InfoPoint;
import de.unisaarland.cs.st.pirates.group1.sim.logic.instruction.Instruction;
import de.unisaarland.cs.st.pirates.group1.sim.parser.MapParser;
import de.unisaarland.cs.st.pirates.group1.sim.parser.TacticsParser;

public class Controller {
	
	private Simulator simulator;
	private MapParser mapParser;
	private TacticsParser tacticsParser;
	private ByteArrayOutputStream mapFile;
	private List<ByteArrayOutputStream> tacticsFile;
	private long seed;
	private Random random;
	private OutputStream output;
	private String outputString;
	private boolean paused;
	private Semaphore sema;
	private boolean dryRunSet;
	
	/**
	 * the Constructor for the Controller
	 * 
	 * @param simulator the simulator which the controller controls
	 * @param mapParser the Map parser which the controller starts
	 * @param tacticsParser the Tactic parser which the controller starts
	 * @param mapFile the mapfile, for parsing the map
	 * @param tacticsFile the tacticsfile for parsing the tactics
	 * @param seed the integer from the commandline giving us a valid seed
	 * @param logFileString The path for the output stream, which is needed so the Controller can initialize the InfoPoint
	 */
public Controller(Simulator simulator, MapParser mapParser,
		TacticsParser tacticsParser, InputStream mapFile, List<InputStream> tacticsFile, long seed, String logFileString){
	this.outputString = logFileString;
	this.simulator = simulator;
	this.mapParser = mapParser;
	this.tacticsParser = tacticsParser;
	this.mapFile = createByteArrayOutputStreamFromFile(mapFile);
	this.tacticsFile = new LinkedList<>();
	for(InputStream in : tacticsFile) {
		this.tacticsFile.add(createByteArrayOutputStreamFromFile(in));
	}
	this.seed = seed;
	this.random = new Random(seed);
	this.dryRunSet = false;
	sema = new Semaphore(1);
}

public Controller(Simulator simulator, MapParser mapParser,
		TacticsParser tacticsParser, InputStream mapFile, List<InputStream> tacticsFile, int seed, String logFileString){
	this(simulator, mapParser, tacticsParser, mapFile, tacticsFile, (long)seed, logFileString);
}

private ByteArrayOutputStream createByteArrayOutputStreamFromFile(InputStream in) {
	byte[] buffer = new byte[1024];
	int len;
	ByteArrayOutputStream out = new ByteArrayOutputStream();
	//read inputStream kB by kB and put it into a ByteArrayOutputStream.
	try {
		//read from stream, take length of read bytes and put those into the outputStream.
		while ((len = in.read(buffer)) > -1 ) {
			out.write(buffer, 0, len);
		}
	}
	catch(IOException e) {
		throw new IllegalStateException("Map file couldn't be read!\n"+e.getMessage()+"\n"+e.getCause());
	}
	try {
		//flush the outputStream so we have everything in memory
		out.flush();
	} catch (IOException e) {
		throw new IllegalStateException("couldn't write to ByteArrayOutputStream");
	}
	return out;
}
	
	/*
	 * Setter and Getter
	 */
public long getSeed() {
	return seed;
}


public void setSeed(long seed) {
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

	public ByteArrayOutputStream getMapFile() {
		return mapFile;
	}

	public void setMapFile(ByteArrayOutputStream mapFile) {
		this.mapFile = mapFile;
	}

	public List<ByteArrayOutputStream> getTacticsFile() {
		return tacticsFile;
	}

	public void setTacticsFile(List<ByteArrayOutputStream> tacticsFile) {
		this.tacticsFile = tacticsFile;
	}

		
	/**
	 * Initializes the Simulator and also the Infopoint
	 * 
	 * @param
	 * @return void
	 * @throws IOException 
	 */
public void initializeSimulator() throws IOException{
	
	//dry run
	this.dryRun();
	
	//if we aren't doing a dry run, create new output stream
	if(!dryRunSet) {
		if(outputString != null)
			output = new FileOutputStream(outputString);
		else
			output = new ByteArrayOutputStream();
	}
	
	//read tactics from Streams
	List<String> stringList = new LinkedList<String>();
	for(ByteArrayOutputStream tactic : tacticsFile){
		stringList.add(tactic.toString());
	}
	
	
	//turn the String tactics into a array
	String[] stringArray = new String[stringList.size()];
	for (int i = 0; i < stringList.size(); i++) {
	    stringArray[i] = stringList.get(i);
	}
	
	//initializes the LogWriter
	try {
		simulator.getLogWriter().init(output, mapFile.toString(), stringArray);
	} catch (ArrayIndexOutOfBoundsException e1) {
		throw new IllegalStateException();
	} catch (NullPointerException e1) {
		throw new IllegalStateException();
	} catch (IOException e1) {
		throw new IllegalStateException();
	}
	
	
	//sets the randomobject to the simulator
	simulator.setRandom(random);
	
	//parses the map and give it to the simulator
	mapParser.parseMap(new ByteArrayInputStream(mapFile.toByteArray()), simulator);
	
	//get the factionarray
	List<Faction> factions = simulator.getFactions();
	
	int factionCounter = 0;
	//check if there are enough tacticsfiles for the factions
	if(factions.size() <= tacticsFile.size()){
	//iterate through the tacticsfile
	for(ByteArrayOutputStream tactic : tacticsFile){
		Instruction[] instrArray = tacticsParser.parseTactics(new ByteArrayInputStream(tactic.toByteArray()), random);
		try{
		factions.get(factionCounter).setTactics(instrArray);
		//breaks out from the loop, if there are no factions left to add tactics to
		}catch(IndexOutOfBoundsException e){
			break;
		}
		factionCounter++;
	}
	}else{
		throw new IllegalArgumentException("There are not enough tactics for the factions");
	}
	//now notify initial Faction score: 0
	for(Faction f : factions) {
		assert(f.getScore() == 0);
		simulator.getLogWriter().fleetScore(f.getFactionID(), f.getScore());
	}
	//log initial state
	simulator.getLogWriter().logStep();
}

	/**
	 * this builds a second Controller which goes through the whole initialization process itself but doesn't write anything to disk.
	 */
	private void dryRun() {
		//if we are already doing a dry run initialization, don't dryRun again.
		if(dryRunSet)
			return;
		List<InputStream> tacticStreams = new LinkedList<>();
		//create InputStreams from the tacticsFiles
		for(ByteArrayOutputStream out : tacticsFile) {
			tacticStreams.add(new ByteArrayInputStream(out.toByteArray()));
		}
		//create a new Controller for the dryRun.
		Controller dryRun = new Controller(new Simulator(new InfoPoint(), new Random(seed)), new MapParser(), new TacticsParser(new InfoPoint()), new ByteArrayInputStream(mapFile.toByteArray()), tacticStreams, seed, outputString);
		//set the dryRun bool flag -> voodoo
		dryRun.dryRunSet = true;
		//initialize it, should explode if some tactics are bad.
		try {
			dryRun.initializeSimulator();
		}
		catch (IOException e) {
			e.printStackTrace();
			throw new IllegalStateException("IOException from somewhere, how did this happen?\n"+e.getCause()+"\n"+e.getMessage());
		}
	}

	/**
	 * The order for the Simulator to do all steps.
	 * 
	 * @param
	 * @return void
	 */
	public void play(){
		play(0);
	}
	
public void play(long delay){
	//acquire a lock so we only run one play loop at a time.
	try {
		sema.acquire();
	} catch (InterruptedException e1) {
		return;
	}
	System.out.println("playing now");
	//let the simulator do one step at a time, stop if an UnsupportedOperationException came (reached maxCycle)
	//or if we got interrupted
	//pause with pause()-Method.
	boolean ende = false;
	while(!ende && waitForUnpaused() && !Thread.interrupted()){
		try{
		simulator.step();
		}catch(UnsupportedOperationException e){
			ende = true;
		}
		try {
		Thread.sleep(delay);
		}
		catch(InterruptedException e) {
			System.out.println("interrupted, aborting now");
			sema.release();
			return;
		}
	}
	System.out.println("done");
	//release the semaphor as we stopped playing.
	sema.release();
}

public void step() throws UnsupportedOperationException{
	simulator.step();
}

	/**
	 * The order for the controller to pause the simulation loop
	 * Has to be 'synchronized' for concurrency purposes.
	 * @param
	 * @return void
	 */
synchronized public void pause(){
	paused = true;
	System.out.println("paused");
	
}

	/**
	 * the order for the controller to unpause the simulation loop
	 * Has to be 'synchronized' for concurrency purposes.
	 */
synchronized public void unpause(){
	paused = false;
	System.out.println("unpaused");
	notifyAll();
}

/**
 * Wait method that blocks the loop in a paused state.
 * @return
 */
synchronized private boolean waitForUnpaused(){
	System.out.println("waiting");
	while(paused){
		try {
			wait();
		} catch (InterruptedException e) {
			return false;
		}
	}
	System.out.println("finished waiting");
	return true;
}

}
