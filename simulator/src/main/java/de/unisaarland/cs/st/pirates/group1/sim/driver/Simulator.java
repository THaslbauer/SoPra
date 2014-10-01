package de.unisaarland.cs.st.pirates.group1.sim.driver;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.*;
import de.unisaarland.cs.st.pirates.group1.sim.logger.ExtendedLogWriter;
import de.unisaarland.cs.st.pirates.group1.sim.logic.instruction.Instruction;
import de.unisaarland.cs.st.pirates.group1.sim.logic.instruction.normalInstructions.CyclingInstruction;
import de.unisaarland.cs.st.pirates.logger.LogWriter.Entity;
import de.unisaarland.cs.st.pirates.logger.LogWriter.Key;

/**
 * This class can simulate game cycles. It knows all ships and krakens and
 * ends the game by showing the scores of each faction.
 * 
 * @author Nico
 * @version 1.2
 */
public class Simulator
{
	private List<Faction> factions;								// the list of factions in the game
	private List<Ship> ships;									// the list of ships in the game
	private List<Kraken> krakens;								// the list of krakens in the game
	private Worldmap worldmap;									// the map
	private int cycle;											// the current cycle of the game
	private final int maxCycle;									// the maximal cycles the game is running
	private ExtendedLogWriter logger;							// the logger wich logs the creation of placables
	private EntityFactory entityFactory;						// the factory which creates the placables
	private Random random;										// an instance of the random class
	private Instruction cyclingInstruction;						// the instruction to cycle a ship while the ship rest
	
	
	public Simulator()
	{
		this(null, 10000, null);
	}
	
	public Simulator(ExtendedLogWriter logger, Random random){
		this(logger, 10000, random);
	}
	
	public Simulator(ExtendedLogWriter logger, int maxCycle, Random random)
	{
		if(logger == null || maxCycle < 1 || random == null)
		{
			throw new IllegalArgumentException();
		}
		
		this.factions           = null;
		this.ships              = new LinkedList<Ship>();
		this.krakens            = new LinkedList<Kraken>();
		this.worldmap           = null;
		this.cycle              = 0;
		this.maxCycle           = maxCycle;
		this.logger             = logger;
		this.entityFactory      = new EntityFactory();
		this.random             = null;
		this.cyclingInstruction = new CyclingInstruction(logger);
	}
	
	/**
	 * This method simulates a single cycle of the game. At first, it iterates through
	 * the krakens and calls step() if the krakenwaitime allowes it. Secondly, it iterates
	 * through the ships and calls step() if the condition of the ship is greater than 0. If
	 * the ship's condition is 0 the simulator will remove the ship from its ships. At Last
	 * this method increases the cycle counter by 1.
	 * 
	 */
	public void step()
	{
		if(cycle > (maxCycle - 1))
		{
			this.endOutput();
			throw new UnsupportedOperationException();
		}
		
		// kraken cycle
		if(((cycle +1) % 20) == 0)
		{
			for(Kraken kraken : krakens)
			{
				kraken.step();
			}
			
		}
		
		
		// ship cycle
		LinkedList<Ship> dummy = new LinkedList<Ship>(ships);
		for(Ship ship : dummy)
		{
			
			
			//check if ship is ok
			if(ship.getCondition() != 0)
			{
				//check if ship is resting
				if(ship.getRestTime() > 0) 
				{
					//if resting, decrement restTime and notify new restTime
					ship.setRestTime(ship.getRestTime() - 1);
					this.logger.notify(Entity.SHIP, ship.getId(), Key.RESTING, ship.getRestTime());
					this.cyclingInstruction.execute(ship);
				}
				//if not resting, execute instruction
				else 
				{
					try
					{
						ship.step();
					} catch(ArrayIndexOutOfBoundsException e) 
					{   // sink the ship
						ship.setCondition(0);
						ship.setMyTile(null);
						this.removeShip(ship);
						logger.destroy(Entity.SHIP, ship.getId());
					}
				}
			}
			//ship is dead, kill ship
			else
			{
				this.removeShip(ship);
			}
		}
		
		cycle += 1;
		
		
		try
		{
			this.logger.logStep();
		}
		catch (IllegalStateException | IOException e)
		{
			e.printStackTrace();
		}
		
	}
	
	
	/**
	 * This method prints the scores of each faction. If the scores are
	 * equal, this method prints the whole output. If not, it prints the
	 * scores only.
	 * 
	 */
	public void endOutput()
	{
		if(factions == null || factions.size() == 0)
			return;
		// get the first factions score
		int factionScore = factions.iterator().next().getScore();

		boolean completeOutput = false;
		
		// tests if the other factions score is equal to the first one
		for(Faction faction : factions)
		{
			if(faction.getScore() == factionScore)
			{
				completeOutput = true;
			}
			else
			{
				completeOutput = false;
			}
		}
		
		// print the complete output
		if(completeOutput)
		{
			for(Faction faction : factions)
			{
				int flotte_ladungen = calculateLoad(faction);
				int flotte_schiffe  = calculateShipAmount(faction);
				int flotte_tactic   = calculateTactic(faction);
				
				System.out.println("" + faction.getScore() + "(" + flotte_ladungen + "," + flotte_schiffe + "," + flotte_tactic + ")");
			}
			
			return;
		}
		
		// don't print the complete output
		for(Faction faction : factions)
		{
			System.out.println("" + faction.getScore());
		}
	}
	
	/**
	 * This method calculates the length of the tactic program
	 * of the given faction.
	 * 
	 * @param faction
	 * @return
	 */
	private int calculateTactic(Faction faction)
	{
		return faction.getTactics().length;
	}
	
	/**
	 * This method  calculates the amount of living ships
	 * in the faction.
	 * 
	 * @param faction
	 * @return
	 */
	private int calculateShipAmount(Faction faction)
	{
		int ship_amount = 0;
		
		for(Ship ship : ships)
		{
			if(ship.getCondition() != 0 && ship.getFaction().equals(faction))
			{
				ship_amount += 1;
			}
		}
		
		return ship_amount;
	}
	
	/**
	 * This method calculates the amount of treasures
	 * which are on the ships and not already droped
	 * at the base.
	 * 
	 * @param faction
	 * @return
	 */
	private int calculateLoad(Faction faction)
	{
		int load = 0;
		
		for(Ship ship : ships)
		{
			if(ship.getCondition() != 0 && ship.getFaction().equals(faction))
			{
				load += ship.getLoad();
			}
		}
		
		return load;
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
		// every key which the ship needs
		Key[] keys = new Key[9];
		keys[0]    = Key.DIRECTION;
		keys[1]    = Key.FLEET;
		keys[2]    = Key.MORAL;
		keys[3]    = Key.PC;
		keys[4]    = Key.RESTING;
		keys[5]    = Key.VALUE;
		keys[6]    = Key.X_COORD;
		keys[7]    = Key.Y_COORD;
		keys[8]    = Key.CONDITION;
		
		// every value wich are matching the keys
		int[] values = new int[9];
		values[0]    = 0;
		values[1]    = faction.getFactionID();
		values[2]    = 4;
		values[3]    = 0;
		values[4]    = 0;
		values[5]    = 0;
		values[6]    = tile.getPosition().x;
		values[7]    = tile.getPosition().y;
		values[8]    = 3;
		
		// logs the creation before creating the ship
		logger.create(Entity.SHIP, entityFactory.getShipNextId(), keys, values);
		
		// creates the ship
		Ship newShip = entityFactory.createShip(faction, tile);
		
		// adds the ship to the ship list
		ships.add(newShip);
		
		return newShip;
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
		// every key wich the kraken needs
		Key[] keys  = new Key[2];
		keys[0]     = Key.X_COORD;
		keys[1]     = Key.Y_COORD;
		
		// every value wich are matching the keys
		int[] values = new int[2];
		values[0]    = tile.getPosition().x;
		values[1]    = tile.getPosition().y;
		
		// logs the creation before creating the ship
		logger.create(Entity.KRAKEN, entityFactory.getKrakenNextId(), keys, values);
		
		// creates the kraken
		Kraken newKraken = entityFactory.releaseTheKraken(tile);
		
		// adds the created kraken to the kraken list
		krakens.add(newKraken);
		
		return newKraken;
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
		
		// remove the ship from the ship list
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
	
	public Random getRandom()
	{
		return random;
	}
	
}
