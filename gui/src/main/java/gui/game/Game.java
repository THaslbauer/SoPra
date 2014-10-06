package gui.game;

import java.io.IOException;

import de.unisaarland.cs.st.pirates.group1.sim.driver.Controller;
import javafx.animation.ParallelTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;

public class Game extends GridPane {
	
	private Stage ownStage;
	private Stage setUp;
	private boolean paused;
	private boolean fastMode;
	private Controller controller;
	private WorldView wv;
	private int cycleCount;
	private int delay;

	@FXML
	private Button playPauseButton;
	
	@FXML
	private Button stopButton;
	
	@FXML
	private GridPane Game;
	
	@FXML
	private Text cycleCounter;
	
	@FXML
	private Slider speedSlider;

	public Game(Stage ownStage, Stage setUp, Controller controller, WorldView wv, int delay) {
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
		this.fastMode = true;
		this.controller = controller;
		this.ownStage = ownStage;
		this.ownStage.setTitle("Simulator: running");
		this.setUp = setUp;
		this.paused = true;
		this.wv = wv;
		this.cycleCount = 0;
		this.delay = delay;
		this.add(wv, 1, 1);
		this.cycleCounter.setText("Cycle count:\n"+cycleCount);
		playPauseButton.setText("Play");
	}

	@FXML
	void playPauseButtonClicked(ActionEvent event) {
		if(paused) {
			this.playPauseButton.setText("Pause");
			paused = false;
			runGame();
		}
		else {
			this.playPauseButton.setText("Play");
			paused = true;
		}
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
//			ParallelTransition gamechange = new ParallelTransition(wv.getCycleAnimations());
//			gamechange.setCycleCount(1);
			//TODO activate this:
//			gamechange.play();
			this.cycleCount++;
			this.cycleCounter.setText("Cycle count:\n"+cycleCount);
		}
	}

	@FXML
	void stopButtonClicked(ActionEvent event) {

	}

	public boolean fastMode() {
		return fastMode;
	}
	

}
	
