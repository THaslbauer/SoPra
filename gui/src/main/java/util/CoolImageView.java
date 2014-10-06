package util;

import gui.game.WorldView;
import javafx.beans.binding.DoubleBinding;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class CoolImageView extends ImageView {
	
	public CoolImageView(Image image) {
		super(image);
	}
	
	public CoolImageView(){}
	
	
//TODO WHY
	@Override
	public boolean isResizable() {
		return false;
	}
	
	@Override
	public double minHeight(double width) {
		return minWidth(100) * (1+ WorldView.magicNumber * 2);
	}

	
}
