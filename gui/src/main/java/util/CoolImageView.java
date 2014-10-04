package util;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class CoolImageView extends ImageView {
	public CoolImageView(Image image) {
		super(image);
	}

	@Override
	public boolean isResizable() {
		return true;
	}
	
}
