package game;

import gui.WorldView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import de.unisaarland.cs.st.pirates.logger.LogWriter;

public class Tile extends StackPane {
	public enum Type {
	SEA, BASE, ISLAND, SUPPLY;
	}
	
	public Tile(Type t) {
		type = t;
	}
	
	public Tile setFaction(Integer i) {
		faction = i;
		return this;
	}
	
	public Integer faction;
	public Type type;
	
	public void add(GElement ge) {
		super.getChildren().add(ge.node);
	}
	

	@Override
	protected double computePrefWidth(double height) {
		return GameContent.wv.sizePerTile;
	}
	
	@Override
	protected double computePrefHeight(double width) {
		return GameContent.wv.sizePerTile*(GameContent.wv.magicNumber - 0.33333333);
	}
	
	@Override
	protected double computeMaxHeight(double width) {
		return GameContent.wv.sizePerTile*(GameContent.wv.magicNumber - 0.33333333);
	}
	
	@Override
	protected double computeMinHeight(double width) {
		return GameContent.wv.sizePerTile*(GameContent.wv.magicNumber - 0.33333333);
	}
	
	@Override
	protected double computeMaxWidth(double height) {
		return GameContent.wv.sizePerTile;
	}
	
	@Override
	protected double computeMinWidth(double height) {
		return GameContent.wv.sizePerTile;
	}
}
