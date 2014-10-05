package gui.game;

import gui.game.objects.Background;
import gui.game.objects.Buoy;
import gui.game.objects.GElement;
import gui.game.objects.GameContent;
import gui.game.objects.Kraken;
import gui.game.objects.Passive;
import gui.game.objects.Ship;
import gui.game.objects.Tile;
import gui.game.objects.Treasure;
import gui.game.objects.Tile.Type;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.util.Iterator;
import java.util.LinkedList;

import javax.rmi.CORBA.Tie;

import util.CoolImageView;
import javafx.animation.Animation;
import javafx.animation.ParallelTransition;
import javafx.animation.RotateTransition;
import javafx.animation.Transition;
import javafx.animation.TranslateTransition;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.util.Duration;
import de.unisaarland.cs.st.pirates.logger.LogWriter;

public class WorldView extends AnchorPane implements de.unisaarland.cs.st.pirates.group1.sim.logger.ExtendedLogWriter {

	private LinkedList<Transition> transitions = new LinkedList<Transition>();
	public Duration deltaMS = new Duration(500); // Important value!!
	
	public WorldView(Game parent) {
		initGUIStuff();
	}
	
	public LogWriter addCell(Cell arg0, Integer faction, int x, int y)
			throws NullPointerException, ArrayIndexOutOfBoundsException,
			IllegalArgumentException, IllegalStateException {
		
		Tile tile;
		Background b;
		switch(arg0) {
			case WATER :
				if(faction == null) {
					tile = new Tile(Tile.Type.SEA);
					b = new Background(seaImage);
				} else {
					tile = new Tile(Tile.Type.BASE).setFaction(faction);
					b = new Background(baseImage);
				}
				break;
			case ISLAND : tile = new Tile(Type.BASE);
				b = new Background(islandImage);
				break;
			case SUPPLY : tile = new Tile(Type.SUPPLY);
				b = new Background(supplyImage);
				break;
			default : throw new IllegalArgumentException ("Enum failure");
		}
		b.node.fitWidthProperty().bind(tileSize);
		b.node.setPreserveRatio(true);
		b.node.cacheProperty().set(true);
		tile.add(b);
		tile.prefWidthProperty().set(50);
		tile.prefHeightProperty().set(80);
		System.out.println("HEIGHT: "+map1.getHeight()+"  WIDTH: "+map1.getWidth());
		GameContent.tiles[x][y] = tile;
		if(y % 2 == 0)  // ungerade
			map1.add(tile, x, y);
		else
			map2.add(tile, x, y);
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
				GameContent.ships.put(arg1, ship);
				ship.node = new CoolImageView(shipImages[faction]);
				ge = ship;
				break;
				}
			case KRAKEN : {
				Kraken kraken = new Kraken(x,y);
				GameContent.passives.put(arg1, kraken); 
				kraken.node = new CoolImageView(krakenImage);
				ge = kraken;
				break;
			}
			case TREASURE : {
				Treasure treasure = new Treasure(x,y);
				treasure.value = value;
				GameContent.passives.put(arg1,treasure);
				treasure.node = new CoolImageView(treasureImage);
				ge = treasure;
				break;
			}
			case BUOY : {
				Buoy buoy = new Buoy(x, y, faction, value);
				GameContent.passives.put(arg1, buoy);
				buoy.node = new CoolImageView(buoyImage);
				ge = buoy;
				break;
			}
			default : {}
		} // end switch
		//TODO: wtf is das hier? es funzt auf jeden fall grad ned
//		popUpAppear(ge);
		ge.node.fitWidthProperty().bind(tileSize);
		ge.node.setPreserveRatio(true);
		GameContent.tiles[x][y].add(ge);
		return this;
	}

	public LogWriter destroy(Entity arg0, int arg1)
			throws NullPointerException, IllegalArgumentException,
			IllegalStateException {
		if(arg0 == Entity.SHIP) {
			Ship s = GameContent.ships.get(arg1);
			if(s.fighting) { // OMG this ship has lost a fight! Let it explode
				
			} else if(s.beaching) { // OMG this ship beached! break it hard and EXPLODE!
				
			} else { // Lol this ship's pc ran out. Let's make the biggest explosion ever!!
				
			}
			GameContent.tiles[s.x][s.y].getChildren().remove(s.node);
			GameContent.ships.remove(arg1);
		}
		else {
			Passive s = GameContent.passives.get(arg1);
			disappear(s);
			GameContent.tiles[s.x][s.y].getChildren().remove(s.node);
			GameContent.passives.remove(arg1);
		}
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
		GameContent.tiles = new Tile[xs][ys];
		
		initRestOfGUIStuff();
		
	}

	public void logStep() throws IllegalStateException, IOException {
		for(int i = 0; i < GameContent.ships.size(); i++) {
			Ship s = GameContent.ships.get(i);
			s.fighting = false;
			s.beaching = false;
		}
	
		// WHY the fuck is this necessary????
		Transition [] trans = new Transition[transitions.size()];
		Iterator<Transition> it = transitions.iterator();
		for(int i = 0; i < transitions.size(); i++) {
			trans[i] = it.next();
		}
		ParallelTransition pt = new ParallelTransition(trans); 
		// send them to whereever they should be
		pt.play();
		// now just render them
		transitions.clear();
	}

	public LogWriter notify(Entity ent, int id, Key k, int val)
			throws NullPointerException, IllegalArgumentException,
			IllegalStateException {
		switch(ent) {
			case SHIP : {
				Ship s = GameContent.ships.get(id);
				switch(k) {
					case X_COORD : {
						TranslateTransition tt = new TranslateTransition();
						tt.setNode(s.node);
						int dx = val - s.x;
						Duration delay = s.fighting ? new Duration(100) : new Duration(0);
						tt.setDelay(delay); // make fight animation before
						tt.setDuration(deltaMS.subtract(delay));
						tt.setToX(dx*sizePerTile);
						transitions.add(tt);
						s.x = val;
						break;
					}
					case Y_COORD : {
						TranslateTransition tt = new TranslateTransition(deltaMS, s.node);
						int dy = val - s.y;
						Duration delay = s.fighting ? new Duration(100) : new Duration(0);
						tt.setDelay(delay); // make fight animation before
						tt.setDuration(deltaMS.subtract(delay));
						tt.setToY(dy*sizePerTile);
						transitions.add(tt);
						s.y = val;
						break;
					}
					case DIRECTION : {
						RotateTransition rt = new RotateTransition(deltaMS, s.node);
						rt.setFromAngle(60 * s.direction); 
						rt.setToAngle(60 * val);
						s.direction = val;
						transitions.add(rt);
						break;
					}
				}
				break;
			} // SHIP
			case KRAKEN : {
				Kraken kraken = (Kraken)GameContent.passives.get(id);
				if(k == Key.X_COORD) {
					TranslateTransition tt = new TranslateTransition(deltaMS, kraken.node);
					int dx = val - kraken.x;
					tt.setByX(dx*sizePerTile);
					transitions.add(tt);
					kraken.x = val;
				} else if(k == Key.Y_COORD) {
					TranslateTransition tt = new TranslateTransition(deltaMS, kraken.node);
					int dy = val - kraken.y;
					tt.setByY(dy*sizePerTile);
					transitions.add(tt);
					kraken.y = val;
				}
				break;
			}
			case BUOY:
				break;
			case TREASURE:
				break;
			default:
				break;
		}
		return this;
	}


	public void fight(
			de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Ship ship,
			de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Ship otherShip) {
		GameContent.ships.get(ship.getId()).fighting = true;
		// TODO instantly display a nice fight animation
		GameContent.ships.get(otherShip.getId()).fighting = true;
		// TODO instantly display a nice fight animation
	}

	public void fight(
			de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Ship ship,
			de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Kraken kraken) {
		GameContent.ships.get(ship.getId()).fighting = true;
		// TODO display a kraken-fight animation
	}

	public void registerChange(
			de.unisaarland.cs.st.pirates.group1.sim.gamestuff.Ship ship) {
		// TODO Auto-generated method stub
		
	}

	
	
	
	
	
	
	/* --- GUI - STUFF */
	
	private GridPane map1 = new GridPane() {
		@Override
		protected double computePrefHeight(double width) {
			return width*2;
		}
	};
	private GridPane map2 = new GridPane();
	
	private Image[] shipImages = new Image[2];
	private Image seaImage, supplyImage, islandImage, baseImage;
	private Image krakenImage, treasureImage, buoyImage;
	
	public double sizePerTile;
	private SimpleDoubleProperty tileSize = new  SimpleDoubleProperty(); // DP lol
	public  SimpleDoubleProperty width = new SimpleDoubleProperty(), height = new  SimpleDoubleProperty();
	
	public static int xs=0, ys=0;
	public final double magicNumber = 1.66666666666666666666666666666;
	
	private void initGUIStuff() {
		seaImage = new Image(""+getClass().getResource("/gui/game/sea.png"));
		supplyImage = new Image(""+getClass().getResource("/gui/game/supply.png"));
		islandImage = new Image(""+getClass().getResource("/gui/game/island.png"));
		baseImage = new Image(""+getClass().getResource("/gui/game/base.png"));
		krakenImage = new Image(""+getClass().getResource("/gui/game/kraken.png"));
		treasureImage = new Image(""+getClass().getResource("/gui/game/treasure.png"));
		buoyImage = new Image(""+getClass().getResource("/gui/game/buoy.png"));
		shipImages[0] = new Image(""+getClass().getResource("/gui/game/ship_a.png"));
		shipImages[1] = new Image(""+getClass().getResource("/gui/game/ship_b.png"));
		
		// somehow determine the available space TODO TODO TODO
		width.set(500);
		height.set(300);
		// !!
		
		this.getChildren().add(map1);
		this.getChildren().add(map2);
		
		
//		map1.prefWidthProperty().set(width.get());
//		map1.prefHeightProperty().set(height.get());
//		map2.prefWidthProperty().set(width.get());
//		map2.prefHeightProperty().set(height.get());

//		map1.prefWidthProperty().bind(root.widthProperty());
//		map1.prefHeightProperty().bind(root.heightProperty());
//		map2.prefWidthProperty().bind(root.widthProperty());
//		map2.prefHeightProperty().bind(root.heightProperty());
		
	}
	
	private void initRestOfGUIStuff() {
		sizePerTile = width.get() / ys;
		tileSize.set(sizePerTile);
		
		// this has to be done automatically somehow if root is resized. Maybe by percentage?
		AnchorPane.setTopAnchor(map1, (double) 10+ 0);
		AnchorPane.setLeftAnchor(map1, (double) 10+ 0);
		AnchorPane.setTopAnchor(map2, (double) 10+ sizePerTile* (magicNumber - 1));
		AnchorPane.setLeftAnchor(map2, (double) 10 + 0.5*sizePerTile);
	
	}
	
	
	
	public void disappear(GElement ge) {
		
	}

	
	
	
	

}
