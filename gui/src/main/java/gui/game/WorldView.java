package gui.game;

import gui.game.objects.Buoy;
import gui.game.objects.Faction;
import gui.game.objects.GElement;
import gui.game.objects.Kraken;
import gui.game.objects.Passive;
import gui.game.objects.Ship;
import gui.game.objects.Treasure;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.LinkedList;

import util.CoolImageView;
import javafx.animation.Animation;
import javafx.animation.Transition;
import javafx.beans.InvalidationListener;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;
import de.unisaarland.cs.st.pirates.logger.LogWriter;

public class WorldView extends AnchorPane implements de.unisaarland.cs.st.pirates.group1.sim.logger.ExtendedLogWriter {
	
	public class CoolInsetValue<T extends Insets> implements ObservableValue<Insets> {

		LinkedList<ChangeListener> chl = new LinkedList<ChangeListener>();
		LinkedList<InvalidationListener> invl = new LinkedList<InvalidationListener>();
		
		private DoubleBinding left = null, top = null;
		private Insets insets;
		
		public CoolInsetValue(DoubleBinding top, DoubleBinding left) {
			this.left = left;
			this.top = top;
			left.addListener(new ChangeListener<Number>() {
				public void changed(
						ObservableValue<? extends Number> observable,
						Number oldValue, Number newValue) {
					callChanged();
				}
			});
			top.addListener(new ChangeListener<Number>() {
				public void changed(
						ObservableValue<? extends Number> observable,
						Number oldValue, Number newValue) {
					callChanged();
				}
			});
					
		}
		
		private void callChanged() {
			Insets oldinsets = insets;
			insets = new Insets(top.get(), 0D, 0D, left.get());	
			for(ChangeListener cl : chl) {
				cl.changed(this, oldinsets, insets);
			}
		}
		
		public void addListener(InvalidationListener listener) {
			invl.add(listener);				
		}

		public void removeListener(InvalidationListener listener) {
			invl.remove(listener);
		}

		public void addListener(ChangeListener listener) {
			chl.add(listener);				
		}

		public void removeListener(ChangeListener listener) {
			chl.add(listener);
		}

		public Insets getValue() {
			return insets;	
		}
				
	}
	
	
	// This ain't static! :D
	public HashMap<Integer, Ship> ships = new HashMap<Integer, Ship>();
	public HashMap<Integer, Passive> passives = new HashMap<Integer, Passive>();
	public Faction[] factions;
	
	
	private LinkedList<Transition> transitions = new LinkedList<Transition>();
	public Duration deltaMS; // Important value!!
	
	
	public WorldView(double width, double height, Duration cycleTime) {
		initGUIStuff();
		this.deltaMS = cycleTime;
	}
	
	
	
	
	/* Implementing the LISTENER STUFF */
	
	public LogWriter addCell(Cell arg0, Integer faction, int x, int y)
			throws NullPointerException, ArrayIndexOutOfBoundsException,
			IllegalArgumentException, IllegalStateException {
		
		CoolImageView ciw;
		switch(arg0) {
			case WATER :
				if(faction == null) {
					//tile = new Tile(Tile.Type.SEA);
					ciw = new CoolImageView(seaImage);
				} else {
					//tile = new Tile(Tile.Type.BASE).setFaction(faction);
					ciw = new CoolImageView(baseImage);
				}
				break;
			case ISLAND : //tile = new Tile(Type.BASE);
				ciw = new CoolImageView(islandImage);
				break;
			case SUPPLY : //tile = new Tile(Type.SUPPLY);
			ciw = new CoolImageView(supplyImage);
				break;
			default : throw new IllegalArgumentException ("Enum failure");
		}
		ciw.fitWidthProperty().bind(tileSize);
		ciw.fitHeightProperty().bind(tileSize);
		ciw.setPreserveRatio(true);
		ciw.cacheProperty().set(true);
		//System.out.println("HEIGHT: "+map1.getHeight()+"  WIDTH: "+map1.getWidth());
		//GameContent.tiles[x][y] = tile;
		if(y % 2 != 0)  // ungerade
			map2.add(ciw, x, (y-1)/2);
		else 
			map1.add(ciw, x, y/2);
		return this;
	}

	public LogWriter addCustomHeaderData(String arg0)
			throws NullPointerException, ArrayIndexOutOfBoundsException {
		// TODO Auto-generated method stub
		return null;
	}

	public Transaction beginTransaction(Entity arg0, int arg1)
			throws NullPointerException, IllegalArgumentException,
			IllegalStateException {
		// TODO Auto-generated method stub
		return null;
	}

	public void close() throws IllegalStateException, IOException {
		// TODO Auto-generated method stub
		
	}

	public LogWriter commitTransaction(Transaction arg0)
			throws NullPointerException, IllegalArgumentException,
			IllegalStateException {
		// TODO Auto-generated method stub
		return null;
	}

	public LogWriter create(Entity arg0, int arg1, Key[] arg2, int[] arg3)
			throws NullPointerException, IllegalArgumentException,
			ArrayIndexOutOfBoundsException, IllegalStateException 
	{
		int x = 0, y = 0;
		int faction = 0, condition = 0, direction = 0, value = 0;
		for(int i = 0; i < arg2.length; i++) {
			switch(arg2[i]) {
				case X_COORD : x = arg3[i]; break;
				case Y_COORD : y = arg3[i]; break;
				case FLEET : faction = arg3[i]; break;
				case CONDITION : condition = arg3[i]; break;
				case DIRECTION : direction = arg3[i]; break;
				case VALUE : value = arg3[i]; break;
				default : {}
			}
		}
		GElement ge = null;
		switch(arg0) {
			case SHIP : {
				Ship ship = new Ship(x,y, faction);
				ship.condition = condition;
				ship.direction = direction;
				ships.put(arg1, ship);
				ship.setImage(shipImages[faction]);
				ship.setTileSize(tileSize);
				ship.translateXProperty().bind(ship.xpos);
				ship.translateYProperty().bind(ship.ypos);
				this.getChildren().add(ship);
				ge = ship;
				break;
				}
			case KRAKEN : {
				Kraken kraken = new Kraken(x,y);
				passives.put(arg1, kraken); 
				kraken.setImage(krakenImage);
				kraken.setTileSize(tileSize);
				kraken.translateXProperty().bind(kraken.xpos);
				kraken.translateYProperty().bind(kraken.ypos);
				this.getChildren().add(kraken);
				ge = kraken;
				break;
			}
			case TREASURE : {
				Treasure treasure = new Treasure(x,y);
				treasure.value = value;
				passives.put(arg1,treasure);
				treasure.setImage(treasureImage);
				if(y % 2 != 0)  // ungerade
					map2.add(treasure, x, (y-1)/2);
				else
					map1.add(treasure, x, y/2);
				ge = treasure;
				break;
			}
			case BUOY : {
				Buoy buoy = new Buoy(x, y, faction, value);
				passives.put(arg1, buoy);
				buoy.setImage(buoyImage);
				if(y % 2 != 0)  // ungerade
					map2.add(buoy, x, y);
				else
					map1.add(buoy, x, y);
				ge = buoy;
				break;
			}
			default : {}
		} // end switch
//		//TODO: wtf is das hier? es funzt auf jeden fall grad ned 
//		//TODO: Das kommt noch. Epische plopp-up-Animation :) So wenn schätze entstehen unn so :D hat noch zeit
////		popUpAppear(ge);
		ge.fitWidthProperty().bind(tileSize);
		ge.fitHeightProperty().bind(tileSize);
		ge.setPreserveRatio(true);
		ge.cacheProperty().set(true);
		return this;
	}

	public LogWriter destroy(Entity arg0, int arg1)
			throws NullPointerException, IllegalArgumentException,
			IllegalStateException {
//		if(arg0 == Entity.SHIP) {
//			Ship s = GameContent.ships.get(arg1);
//			if(s.fighting) { // OMG this ship has lost a fight! Let it explode
//				
//			} else if(s.beaching) { // OMG this ship beached! break it hard and EXPLODE!
//				
//			} else { // Lol this ship's pc ran out. Let's make the biggest explosion ever!!
//				
//			}
//			GameContent.tiles[s.x][s.y].getChildren().remove(s.node);
//			GameContent.ships.remove(arg1);
//		}
//		else {
//			Passive s = GameContent.passives.get(arg1);
//			disappear(s);
//			GameContent.tiles[s.x][s.y].getChildren().remove(s.node);
//			GameContent.passives.remove(arg1);
//		}
		return this;
	}

	public LogWriter fleetScore(int arg0, int arg1)
			throws IllegalArgumentException, IllegalStateException {
		// TODO Auto-generated method stub
		return null;
	}

	public void init(OutputStream arg0, String arg1, String... arg2)
			throws NullPointerException, IOException,
			ArrayIndexOutOfBoundsException {
		String[] s = arg1.split("\n");
		xs = Integer.parseInt(s[0]);
		ys = Integer.parseInt(s[1]);
		//GameContent.tiles = new Tile[xs][ys];
		initRestOfGUIStuff();
		
	}

	public void logStep() throws IllegalStateException, IOException {
		for(int i = 0; i < ships.size(); i++) {
			Ship s = ships.get(i);
			s.fighting = false;
			s.beaching = false;
		}
	
	}

	public LogWriter notify(Entity ent, int id, Key k, int val)
			throws NullPointerException, IllegalArgumentException,
			IllegalStateException {
//		switch(ent) {
//			case SHIP : {
//				Ship s = GameContent.ships.get(id);
//				switch(k) {
//					case X_COORD : {
//						TranslateTransition tt = new TranslateTransition();
//						tt.setNode(s.node);
//						int dx = val - s.x;
//						Duration delay = s.fighting ? new Duration(100) : new Duration(0);
//						tt.setDelay(delay); // make fight animation before
//						tt.setDuration(deltaMS.subtract(delay));
//						tt.setToX(dx*sizePerTile);
//						transitions.add(tt);
//						s.x = val;
//						break;
//					}
//					case Y_COORD : {
//						TranslateTransition tt = new TranslateTransition(deltaMS, s.node);
//						int dy = val - s.y;
//						Duration delay = s.fighting ? new Duration(100) : new Duration(0);
//						tt.setDelay(delay); // make fight animation before
//						tt.setDuration(deltaMS.subtract(delay));
//						tt.setToY(dy*sizePerTile);
//						transitions.add(tt);
//						s.y = val;
//						break;
//					}
//					case DIRECTION : {
//						RotateTransition rt = new RotateTransition(deltaMS, s.node);
//						rt.setFromAngle(60 * s.direction); 
//						rt.setToAngle(60 * val);
//						s.direction = val;
//						transitions.add(rt);
//						break;
//					}
//				}
//				break;
//			} // SHIP
//			case KRAKEN : {
//				Kraken kraken = (Kraken)GameContent.passives.get(id);
//				if(k == Key.X_COORD) {
//					TranslateTransition tt = new TranslateTransition(deltaMS, kraken.node);
//					int dx = val - kraken.x;
//					tt.setByX(dx*sizePerTile);
//					transitions.add(tt);
//					kraken.x = val;
//				} else if(k == Key.Y_COORD) {
//					TranslateTransition tt = new TranslateTransition(deltaMS, kraken.node);
//					int dy = val - kraken.y;
//					tt.setByY(dy*sizePerTile);
//					transitions.add(tt);
//					kraken.y = val;
//				}
//				break;
//			}
//			case BUOY:
//				break;
//			case TREASURE:
//				break;
//			default:
//				break;
//		}
		return this;
	}


	public void fight(
			de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Ship ship,
			de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Ship otherShip) {
		ships.get(ship.getId()).fighting = true;
		// TODO instantly display a nice fight animation
		ships.get(otherShip.getId()).fighting = true;
		// TODO instantly display a nice fight animation
	}

	public void fight(
			de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Ship ship,
			de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Kraken kraken) {
		ships.get(ship.getId()).fighting = true;
		// TODO display a kraken-fight animation
	}

	public void registerChange(
			de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Ship ship) {
		// TODO Auto-generated method stub
		
	}
	
	
	

	
	/* PANE STUFF */
	
	
	
	
	/* --- GUI - STUFF */
	
	private GridPane map1 = new GridPane();
	private GridPane map2 = new GridPane();
	
	
	// Image Stuff
	private Image[] shipImages = new Image[2];
	private Image seaImage, supplyImage, islandImage, baseImage;
	private Image krakenImage, treasureImage, buoyImage;
	
	
	
	// Size - stuff
	private DoubleBinding tileSize; // The Size of a Tile as is, regardless of spacing stuff and shit
	private DoubleBinding tileWidth; // The width that a tile should have in grid (with spacing)
	private DoubleBinding tileHeight; // The width that a tile should have in grid (with spacing)
	
	public int xs=0, ys=0;
	private int spaceWidth = 100, spaceHeight = 100;
	public static final double magicNumber = 0.333333333333333333333333333333;
	
	private void initGUIStuff() {
		seaImage = new Image(""+getClass().getResource("objects/sea.png"));
		supplyImage = new Image(""+getClass().getResource("objects/supply.png"));
		islandImage = new Image(""+getClass().getResource("objects/island.png"));
		baseImage = new Image(""+getClass().getResource("objects/base.png"));
		krakenImage = new Image(""+getClass().getResource("objects/kraken.png"));
		treasureImage = new Image(""+getClass().getResource("objects/treasure.png"));
		buoyImage = new Image(""+getClass().getResource("objects/buoy.png"));
		shipImages[0] = new Image(""+getClass().getResource("objects/ship_a.png"));
		shipImages[1] = new Image(""+getClass().getResource("objects/ship_b.png"));
		
		this.getChildren().addAll(map1,map2);
	}
	
	private void initRestOfGUIStuff() {
		tileSize = this.widthProperty().add(-spaceWidth).divide(xs);
		//tileWidth = tileSize; // atm
		// Now fucking calculating magic :D
		//tileHeight = this.heightProperty().add(-spaceHeight).divide(ys).add(tileSize.divide(magicNumber * 2));
		
		//TODO FIX THIS!
//		CoolInsetValue<Insets> mainInsets = new CoolInsetValue<Insets>(tileSize.multiply(2*magicNumber), tileHeight.multiply(0D));
//		map2.paddingProperty().bind(mainInsets);
//		buoymap2.paddingProperty().bind(mainInsets);
//		treasuremap2.paddingProperty().bind(mainInsets);
		
		Insets insets = new Insets(65,0,0,65*0.6);
		map2.paddingProperty().set(insets);
		
	}
	
	
	
	public void disappear(GElement ge) {
		
	}

	public Animation[] getCycleAnimations() {
		return (Animation[]) transitions.toArray();
	}

}
