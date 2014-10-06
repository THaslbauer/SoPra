package gui.game.objects;

import javafx.beans.InvalidationListener;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.image.ImageView;

public class Ship extends GElement {
	public int x, y;
	public int faction, condition, direction;
	
	public DoubleBinding tileSize;
	public DoubleProperty xpos;
	public DoubleProperty ypos;
	
	public boolean fighting = false, beaching = false;
	
	public Ship(int _x, int _y, int faction) {
		x = _x;
		y = _y;
	}
	
	public void setTileSize(DoubleBinding db) {
		tileSize = db;
		xpos = new SimpleDoubleProperty() {
			@Override
			public double get() {
				double offset = y % 2 == 0 ? 0 : tileSize.get() / 4;
				return tileSize.get()/2 * x + offset;
			}
			
			@Override
			public Double getValue() {
				return new Double(get());
			}
		};
		ypos = new SimpleDoubleProperty() {
			@Override
			public double get() {
				return tileSize.get()/2 * y;
			}
			
			@Override
			public Double getValue() {
				return new Double(get());
			}
		};
	}
	
	
	
}
