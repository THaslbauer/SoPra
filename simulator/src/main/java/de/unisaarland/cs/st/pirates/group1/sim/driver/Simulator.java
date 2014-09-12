package de.unisaarland.cs.st.pirates.group1.main.sim.driver;

import java.util.List;

import de.unisaarland.cs.st.pirates.group1.main.sim.gamestuff.EntityFactory;
import de.unisaarland.cs.st.pirates.group1.main.sim.gamestuff.Faction;
import de.unisaarland.cs.st.pirates.group1.main.sim.gamestuff.Kraken;
import de.unisaarland.cs.st.pirates.group1.main.sim.gamestuff.Map;
import de.unisaarland.cs.st.pirates.group1.main.sim.gamestuff.Ship;
import de.unisaarland.cs.st.pirates.group1.main.sim.gamestuff.Tile;
import de.unisaarland.cs.st.pirates.group1.main.sim.logger.InfoPoint;

public class Simulator
{
	private List<Kraken> krakens;
	
	private List<Ship> ships;
	
	private Map map;
	
	private List<Faction> factions;
	
	private int cycle;
	
	private final static int maxCicle = 10000;
	
	private InfoPoint infoPoint;
	
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
	
	/**
	 * This method creates a ship via the entitiyFactory. It calls the infoPoint and
	 * adds the created ship to the list of ships.
	 * 
	 * @param faction  the new ship's faction
	 * @param tile	   the new ship's tile
	 * @return		   the created ship
	 */
	public Ship createShip(Faction faction, Tile tile)
	
	/**
	 * This method creates a kraken via the entityFactory. It calls the infoPoint and
	 * adds the created kraken to the list of kraken.
	 * 
	 * @param tile     the new kraken's tile
	 * @return         the created kraken
	 */
	public Kraken createKraken(Tile tile)
	
	/**
	 * This method removes a ship from the list of ships.
	 * 
	 * @param ship     the ship which should be removed
	 */
	public void removeShip(Ship ship)
	
	
}
