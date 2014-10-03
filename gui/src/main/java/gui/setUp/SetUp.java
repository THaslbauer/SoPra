package gui.setUp;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import util.MessageBox;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class SetUp extends GridPane {

	private Stage ownStage;
	private long seed;
	private int cycleCount;
	private File logFile;
	private File mapFile;
	private int delay;
	private boolean mapFileAdded;
	private boolean tacticsFileAdded;
	private List<File> tacticsFiles;
	
	public SetUp(Stage stage) {
		this.ownStage = stage;
		this.tacticsFiles = new LinkedList<File>();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("SetUp.fxml"));
		loader.setController(this);
		loader.setRoot(this);
		try {
		loader.load();
		}
		catch(IOException e) {
			e.printStackTrace();
			throw new UnsupportedOperationException(e.getCause());
		}
		StartButton.setDisable(true);
		ownStage.setTitle("Simulator: configuration");
	}
	
	@FXML
	private Text mapPathText;
	
	@FXML
	private Text mapContentText;

	@FXML
	private Button LoadMapButton;

	@FXML
	private TextField DelayField;

	@FXML
	private TextField SeedField;

	@FXML
	private Button SetLogfileButton;

	@FXML
	private GridPane SetUp;

	@FXML
	private TextField CycleField;
	
	@FXML
    private Button LoadTacticsButton;
	
	@FXML
	private Button StartButton;
	
	@FXML
    private ListView<?> tacticsListView;

	@FXML
	void loadMapButtonPressed(ActionEvent event) {
		FileChooser fc = new FileChooser();
		this.mapFile = fc.showOpenDialog(ownStage);
		this.mapFileAdded = true;
		this.StartButton.setDisable(!(mapFileAdded && tacticsFileAdded));
		System.out.println("setting start disabled = "+!(tacticsFileAdded && mapFileAdded));
		this.mapPathText.setText("Map path is: "+mapFile.getAbsolutePath());
		String mapString = "";
		try {
			Scanner mapScanner = new Scanner(mapFile);
			mapScanner.useDelimiter("\\A");
			mapString = mapScanner.next();
		}
		catch(IOException e) {
			MessageBox.displayMessage("Error with map file", "Could not read from the map file. Cause was:\n"+e.getCause());
		}
		this.mapContentText.setText(mapString);
	}

	@FXML
	void setLogfileButtonPressed(ActionEvent event) {
		FileChooser fc = new FileChooser();
		this.logFile = fc.showOpenDialog(ownStage);
	}

	@FXML
	void changedCycles(ActionEvent event) {
		
	}

	@FXML
	void seedChanged(ActionEvent event) {

	}

	@FXML
	void delayChanged(ActionEvent event) {

	}
	
	@FXML
	void cycleFieldClicked(ActionEvent event) {
		
	}
	
	@FXML
    void loadTacticsButtonPressed(ActionEvent event) {
		FileChooser fc = new FileChooser();
		File tactic = fc.showOpenDialog(ownStage);
		tacticsFiles.add(tactic);
		tacticsFileAdded = true;
		System.out.println("setting start disabled = "+!(tacticsFileAdded && mapFileAdded));
		StartButton.setDisable(!(tacticsFileAdded && mapFileAdded));
    }

	@FXML
	void startButtonPressed(ActionEvent event) {
		System.out.println("starting now");
		try {
			this.cycleCount = Integer.parseInt(this.CycleField.getText());
		}
		catch (NumberFormatException e) {
			MessageBox.displayMessage("Error with cycle count", "Cycle count can't be parsed");
		}
		try {
			this.delay = Integer.parseInt(this.DelayField.getText());
		}
		catch (NumberFormatException e) {
			MessageBox.displayMessage("Error with delay", "Delay can't be parsed");
		}
		try {
			this.seed = Long.parseLong(this.SeedField.getText());
		}
		catch (NumberFormatException e) {
			MessageBox.displayMessage("Error with seed", "Seed can't be parsed");
		}
	}

}

