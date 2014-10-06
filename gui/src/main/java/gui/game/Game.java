package gui.game;

import java.io.IOException;

import de.unisaarland.cs.st.pirates.group1.sim.driver.Controller;
import javafx.animation.ParallelTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class Game extends GridPane {
	
	private Stage ownStage;
	private Stage setUp;
	private boolean paused;
	private boolean fastMode;
	private Controller controller;
	private WorldView wv;

	@FXML
	private Button playPauseButton;
	
	@FXML
	private Button stopButton;
	
	@FXML
	private GridPane Game;

	public Game(Stage ownStage, Stage setUp, Controller controller, WorldView wv) {
		this.fastMode = true;
		this.controller = controller;
		this.ownStage = ownStage;
		this.setUp = setUp;
		this.paused = true;
		this.wv = wv;
//		this.add(wv, 1, 1);
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Game.fxml"));
		loader.setController(this);
		loader.setRoot(this);
		try {
			loader.load();
		}
		catch (IOException e) {
			e.printStackTrace();
			throw new UnsupportedOperationException(e.getCause());
		}
		playPauseButton.setText("Play");
		// von Jens
		this.add(wv, 1, 1);
		// /von Jens
	}

	@FXML
	void playPauseButtonClicked(ActionEvent event) {
		if(paused)
			runGame();
		else
			paused = true;
	}

	private void runGame() {
		while(!paused) {
			try {
				controller.step();
			}
			catch(UnsupportedOperationException e) {
				//TODO start displaying score here
				return;
			}
			ParallelTransition gamechange = new ParallelTransition(wv.getCycleAnimations());
			gamechange.setCycleCount(1);
			//TODO activate this:
//			gamechange.play();
		}
	}

	@FXML
	void stopButtonClicked(ActionEvent event) {

	}

	public boolean fastMode() {
		return fastMode;
	}

}
	
