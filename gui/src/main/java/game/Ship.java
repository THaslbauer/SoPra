package game;

import javafx.scene.Node;
import javafx.scene.image.ImageView;

public class Ship extends GElement {
	public int x, y;
	public int faction, condition, direction;
	
	public boolean fighting = false, beaching = false;
	
	public Ship(int _x, int _y, int faction) {
		x = _x;
		y = _y;
	}
	
	
}
