package de.unisaarland.cs.st.pirates.group1.tests.sim.gamestuff;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import de.unisaarland.cs.st.pirates.group1.sim.gamestuff.*;

public class BuoyAndTreasureTest {

	private Buoy buoy;
	private int type;
	private Faction faction;
	private int id;
	private Tile tile;
	
	private Treasure treasure;
	private int value;
	private int id2;
	
	
	@BeforeClass
	public void init(){
		
		//Everything that is needed for a buoy
	    type = 0;
	    faction = new Faction("a");	
		id = 1;
		tile = new Sea(null,new Position(1,1));
		
		buoy = new Buoy(type,faction,id,tile);
		
		//Everything that is needed for a treasure
		value = 30;
		id2 = 12;
		
		treasure = new Treasure(value, id2, tile);
		
	}
	
	@Test
	public void buoy1Test(){
		
		assertTrue(this.type == buoy.getType());
		assertTrue(this.faction.equals(buoy.getFaction()));
		assertTrue(this.id == buoy.getId());
		assertTrue(this.tile.equals(buoy.getMyTile()));
		
	}
		
	
	@Test
	public void Treasure1Test(){
		
		assertTrue(this.id2 == treasure.getId());
		assertTrue(this.value == treasure.getValue());
		assertTrue(this.tile.equals(treasure.getMyTile()));
	}
	

}
