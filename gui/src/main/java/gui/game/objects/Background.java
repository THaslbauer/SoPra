package gui.game.objects;

import util.CoolImageView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Background extends GElement {
	public Background(Image i) {
		node = new CoolImageView(i);
	}
}
