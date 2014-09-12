package de.unisaarland.cs.st.pirates.group1.sim.gamestuff;

/**
 * 
 * @author Kerstin
 * @version Version 1.0
 *
 */
public class EntityFactory {

	private int shipNextId;
	private int treasureNextId;
	private int buoyNextId;
	
	public EntityFactory(){
		//TODO:implement this
	}
	
	/**
	 * This method creates a ship using its faction and tile.
	 * 
	 * @param faction 
	 * @param tile 
	 * @return
	 */
	public Ship createShip(Faction faction, Tile tile){
		
		//TODO: implement this
		return null;
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
		//TODO: implement this
		return null;
	}
	
	/**
	 * This method creates a treasure using its value and the tile it is currently placed on.
	 * 
	 * @param value
	 * @param tile
	 * @return
	 */
	public Treasure createTreasure(int value, Tile tile){
		//TODO: implement this
		return null;
	}
	
	/**
	 * This method creates a kraken using the tile it is currently placed on.
	 * @param tile
	 * @return
	 */
	public Kraken releaseTheKraken(Tile tile){
		//TODO: implement this
		return null;
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
