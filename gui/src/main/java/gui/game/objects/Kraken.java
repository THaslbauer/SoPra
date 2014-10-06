package gui.game.objects;

import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class Kraken extends Passive {

	public DoubleBinding tileSize;
	public DoubleProperty xpos;
	public DoubleProperty ypos;
	public Kraken(int _x, int _y) {
		super(_x, _y);
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
