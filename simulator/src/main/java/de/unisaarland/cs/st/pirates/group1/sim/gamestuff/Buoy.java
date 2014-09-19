package de.unisaarland.cs.st.pirates.group1.sim.gamestuff;

import static de.unisaarland.cs.st.pirates.group1.sim.util.ThrowHelper.throwIAException;

import java.util.LinkedList;

import de.unisaarland.cs.st.pirates.group1.sim.util.IllegalCallException;

/**
 * Represents a Buoy on the far sea
 * @author Jens Kreber
 *
 */
public class Buoy extends Placable {
	private final int type;
	private final Faction faction;
	
	/**
	 * Buoy constructor
	 * @param type the information of the buoy
	 * @param faction the faction
	 * @param id the unique buoy-id
	 * @param tile the tile this buoy is on
	 */
	public Buoy(int type, Faction faction, int id, Tile tile) {
		super(id,tile);
		this.type = type >= 0 && type < 6 ? type : (int) throwIAException("Buoy Value is trash");
		this.faction = faction;	
		setMyTile(tile);
	}
	

	public int getType() {
		return type;
	}

	public Faction getFaction() {
		return faction;
	}

	@Override
	protected void detachFrom(Tile tile) throws IllegalCallException {
		if(myTile.getBuoyMap().get(faction) == null)
			throw new IllegalCallException("The list where i should be doesn't even exist :(");
		if(!myTile.getBuoyMap().get(faction).contains(this))
			throw new IllegalCallException("I am not in the list!! :( :(");
		myTile.getBuoyMap().get(faction).remove(this);
	}

	@Override
	protected void attachTo(Tile tile) throws IllegalCallException {
		if(myTile.getBuoyMap().get(faction) == null)
			myTile.getBuoyMap().put(faction, new LinkedList<Buoy>());
		if(myTile.getBuoyMap().get(faction).contains(this))
			throw new IllegalCallException("I am already in the list?? o.O");
		myTile.getBuoyMap().get(faction).add(this);
	}
	
	@Override
	public boolean equals(Object o) {
		if(!(o instanceof Buoy))
			return false;
		Buoy other = (Buoy)o;
		return other.id == id;
	}
	
}
