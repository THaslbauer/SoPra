package de.unisaarland.cs.st.pirates.group1.sim.driver;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.*;
import de.unisaarland.cs.st.pirates.group1.sim.logger.ExtendedLogWriter;
import de.unisaarland.cs.st.pirates.group1.sim.logger.LogWriter.Entity;
import de.unisaarland.cs.st.pirates.group1.sim.logger.LogWriter.Key;

/**
 * This class can simulate game cycles. It knows all ships and krakens and
 * ends the game by showing the scores of each faction.
 * 
 * @author Nico
 * @version 1.1
 */
public class Simulator
{
	private List<Faction> factions;								// the list of factions in the game
	private List<Ship> ships;									// the list of ships in the game
	private List<Kraken> krakens;								// the list of krakens in the game
	private Worldmap worldmap;									// the map
	private int cycle;											// the current cycle of the game
	private final int maxCycle;									// the maximal cycles the game is running
	private int krakenWaittime;									// all kraken's current waittime
	private final int maxKrakenWaittime;						// the maximal waittime of every kraken until it can step() again
	private ExtendedLogWriter logger;							// the logger wich logs the creation of placables
	private EntityFactory entityFactory;						// the factory which creates the placables
	private Random random;										// an instance of the random class
	
	
	public Simulator()
	{
		this(null, 10000, null);
	}
	
	public Simulator(ExtendedLogWriter logger, Random random){
		this(logger, 10000, random);
	}
	
	public Simulator(ExtendedLogWriter logger, int maxCycle, Random random)
	{
		this.factions      = new LinkedList<Faction>();
		this.ships         = new LinkedList<Ship>();
		this.krakens       = new LinkedList<Kraken>();
		this.worldmap      = null;
		this.cycle         = 0;
		this.maxCycle      = maxCycle;
		this.logger        = logger;
		this.entityFactory = null;
		this.random        = null;
		this.krakenWaittime = 0;
		this.maxKrakenWaittime = 20;
	}
	
	/**
	 * This method simulates a single cycle of the game. At first, it iterates through
	 * the krakens and calls step(). Secondly, it iterates through the
	 * ships and calls step().
	 * 
	 */
	public void step()
	{
		//TODO unsupportedOperationException
		
		if(krakenWaittime == 0)
		{
			for(Kraken kraken : krakens)
			{
				kraken.step();
			}
		}
	}
	
	/**
	 * This method creates a ship via the entitiyFactory. It calls the infoPoint and
	 * adds the created ship to the list of ships.
	 * 
	 * @param faction  the new ship's faction
	 * @param tile	   the new ship's tile
	 * @return		   the created ship
	 */
	public Ship createShip(Faction faction, Tile tile)
	{
		// every key which 
		Key[] keys = new Key[8];
		keys[0]    = Key.DIRECTION;
		keys[1]    = Key.FLEET;
		keys[2]    = Key.MORAL;
		keys[3]    = Key.PC;
		keys[4]    = Key.RESTING;
		keys[5]    = Key.VALUE;
		keys[6]    = Key.X_COORD;
		keys[7]    = Key.Y_COORD;
		
		
		logger.create(Entity.SHIP, entityFactory.getShipNextId(), keys, null);
		
		return entityFactory.createShip(faction, tile);
	}
	
	/**
	 * This method creates a kraken via the entityFactory. It calls the infoPoint and
	 * adds the created kraken to the list of kraken.
	 * 
	 * @param tile     the new kraken's tile
	 * @return         the created kraken
	 */
	public Kraken createKraken(Tile tile)
	{
		return entityFactory.releaseTheKraken(tile);
	}
	
	/**
	 * This method removes a ship from the list of ships.
	 * 
	 * @param ship     the ship which should be removed
	 */
	public void removeShip(Ship ship)
	{
		if(ship == null || ships == null)
		{
			throw new IllegalArgumentException();
		}
		
		ships.remove(ship);
	}

	public List<Kraken> getKrakens() {
		return krakens;
	}

	public void setKrakens(List<Kraken> krakens) {
		this.krakens = krakens;
	}

	public List<Ship> getShips() {
		return ships;
	}

	public void setShips(List<Ship> ships) {
		this.ships = ships;
	}

	public Worldmap getWorldmap() {
		return worldmap;
	}

	public void setWorldmap(Worldmap worldmap) {
		this.worldmap = worldmap;
	}

	public List<Faction> getFactions() {
		return factions;
	}

	public void setFactions(List<Faction> factions) {
		this.factions = factions;
	}

	public int getCycle() {
		return cycle;
	}

	public void setCycle(int cycle) {
		this.cycle = cycle;
	}

	public ExtendedLogWriter getLogWriter() {
		return logger;
	}

	public void setLogWriter(ExtendedLogWriter logger) {
		this.logger = logger;
	}

	public EntityFactory getEntityFactory() {
		return entityFactory;
	}

	public void setEntityFactory(EntityFactory entityFactory) {
		this.entityFactory = entityFactory;
	}
	
	public int getMaxCycle(){
		return this.maxCycle;
	}
	
	public void setRandom(Random random)
	{
		this.random = random;
	}
	
}
