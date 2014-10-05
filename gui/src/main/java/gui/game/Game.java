package gui.game;

import java.io.IOException;

import gui.WorldView;
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

	@FXML
	private Button playPauseButton;
	
	@FXML
	private Button stopButton;
	
	@FXML
	private GridPane Game;

	public Game(Stage ownStage, Stage setUp) {
		this.ownStage = ownStage;
		this.setUp = setUp;
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
	}

	@FXML
	void playPauseButtonClicked(ActionEvent event) {

	}

	@FXML
	void stopButtonClicked(ActionEvent event) {

	}

}
	
