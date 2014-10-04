package game;

import gui.WorldView;

import java.util.ArrayList;
import java.util.HashMap;

public class GameContent {
	
	public static WorldView wv; // for TESTING purposes!!
	
	public static HashMap<Integer, Ship> ships = new HashMap<Integer, Ship>();
	public static HashMap<Integer, Passive> passives = new HashMap<Integer, Passive>();
	public static Tile[][] tiles;
	public static Faction[] factions;
}
