package gui.game.objects;

import gui.game.WorldView;

import java.util.ArrayList;
import java.util.HashMap;

public class GameContent {
	//TODO DELETE THIS SHIT PURGE IT FROM THE DISK REMOVE IT FROM GIT KILL IT WITH FIRE NO STATIC FIELDS FOR SPECIFIC GAMES ALLOWED
	public static WorldView wv; // for TESTING purposes!!
	
	public static HashMap<Integer, Ship> ships = new HashMap<Integer, Ship>();
	public static HashMap<Integer, Passive> passives = new HashMap<Integer, Passive>();
	public static Tile[][] tiles;
	public static Faction[] factions;
}
