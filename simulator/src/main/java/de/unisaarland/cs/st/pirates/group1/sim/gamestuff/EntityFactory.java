package de.unisaarland.cs.st.pirates.group1.sim.gamestuff;

/**
 * 
 * @author Kerstin
 * @version Version 1.0
 *
 */
public class EntityFactory {

	protected int shipNextId;
	protected int treasureNextId;
	protected int buoyNextId;
	protected int krakenNextId;
	
	public int getKrakenNextId() {
		return krakenNextId;
	}

	public void setKrakenNextId(int krakenNextId) {
		this.krakenNextId = krakenNextId;
	}

	public EntityFactory(){
		shipNextId = 0;
		treasureNextId = 0;
		buoyNextId = 0;
		krakenNextId = 0;
	}
	
	/**
	 * This method creates a ship using its faction and tile.
	 * 
	 * @param faction 
	 * @param tile 
	 * @return
	 */
	public Ship createShip(Faction faction, Tile tile){
		return new Ship(faction, shipNextId++, tile);
	}
	
	/**
	 * This method creates a buoy using its type (there are six types available), its faction 
	 * and the tile it is currently placed on.
	 * 
	 * @param type
	 * @param faction
	 * @param tile
	 * @return
	 */
	public Buoy createBuoy(int type, Faction faction, Tile tile){
		return new Buoy(type, faction, buoyNextId++, tile);
	}
	
	/**
	 * This method creates a treasure using its value and the tile it is currently placed on.
	 * 
	 * @param value
	 * @param tile
	 * @return
	 */
	public Treasure createTreasure(int value, Tile tile){
		return new Treasure(value, treasureNextId++, tile);
	}
	
	/**
	 * This method creates a kraken using the tile it is currently placed on.
	 * @param tile
	 * @return
	 */
	public Kraken releaseTheKraken(Tile tile){
		return new Kraken(krakenNextId++, tile);
	}

	public int getShipNextId() {
		return shipNextId;
	}

	public void setShipNextId(int shipNextId) {
		this.shipNextId = shipNextId;
	}

	public int getTreasureNextId() {
		return treasureNextId;
	}

	public void setTreasureNextId(int treasureNextId) {
		this.treasureNextId = treasureNextId;
	}

	public int getBuoyNextId() {
		return buoyNextId;
	}

	public void setBuoyNextId(int buoyNextId) {
		this.buoyNextId = buoyNextId;
	}
	
}
