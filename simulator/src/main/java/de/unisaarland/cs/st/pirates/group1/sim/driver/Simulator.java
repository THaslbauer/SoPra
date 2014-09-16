package de.unisaarland.cs.st.pirates.group1.sim.driver;

import java.util.List;

import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.*;
import de.unisaarland.cs.st.pirates.group1.sim.logger.ExtendedLogWriter;
import de.unisaarland.cs.st.pirates.group1.sim.logger.InfoPoint;

/**
 * This class can simulate game cycles. It knows all ships and krakens and
 * ends the game by showing the scores of each faction.
 * 
 * @author Nico
 * @version 1.0
 */
public class Simulator
{
	private List<Kraken> krakens;
	
	private List<Ship> ships;
	
	private Worldmap worldmap;
	
	private List<Faction> factions;
	
	private int cycle;
	
	private final static int maxCicle = 10000;
	
	private ExtendedLogWriter logger;
	
	private EntityFactory entityFactory;
	
	
	
	public Simulator(InfoPoint infoPoint)
	{
		//TODO
	}
	
	/**
	 * This method simulates a single cycle of the game. At first, it iterates through
	 * the krakens and calls step(). Secondly, it iterates through the
	 * ships and calls step().
	 * 
	 */
	public void step()
	{
		//TODO
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
		//TODO
		return null;
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
		//TODO
		return null;
	}
	
	/**
	 * This method removes a ship from the list of ships.
	 * 
	 * @param ship     the ship which should be removed
	 */
	public void removeShip(Ship ship)
	{
		//TODO
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
	
	
}
