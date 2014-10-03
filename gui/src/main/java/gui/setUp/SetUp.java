package gui.setUp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import de.unisaarland.cs.st.pirates.group1.logger.OutLogger;
import de.unisaarland.cs.st.pirates.group1.main.Main;
import de.unisaarland.cs.st.pirates.group1.sim.driver.Controller;
import de.unisaarland.cs.st.pirates.group1.sim.logger.ExtendedLogWriter;
import util.MessageBox;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
	private VBox tacticsBox;
	

	

	@FXML
	void loadMapButtonPressed(ActionEvent event) {
		FileChooser fc = new FileChooser();
		this.mapFile = fc.showOpenDialog(ownStage);
		this.mapFileAdded = true;
		this.StartButton.setDisable(!(mapFileAdded && tacticsFileAdded));
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
		this.logFile = fc.showSaveDialog(ownStage);
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
		tacticsBox.getChildren().add(new TacticsListElement(tactic, this));
		tacticsFiles.add(tactic);
		tacticsFileAdded = true;
		StartButton.setDisable(!(tacticsFileAdded && mapFileAdded));
    }

	@FXML
	void startButtonPressed(ActionEvent event) {
		System.out.println("starting now");
		try {
			System.out.println(this.CycleField.getText());
			this.cycleCount = Integer.parseInt(this.CycleField.getText());
		}
		catch (NumberFormatException e) {
			MessageBox.displayMessage("Error with cycle count", "Cycle count can't be parsed, was:\n"+CycleField.getText()+"\nAborting now.");
			return;
		}
		try {
			this.delay = Integer.parseInt(this.DelayField.getText());
		}
		catch (NumberFormatException e) {
			MessageBox.displayMessage("Error with delay", "Delay can't be parsed, was:\n"+DelayField.getText()+"\nAborting now.");
			return;
		}
		try {
			this.seed = Long.parseLong(this.SeedField.getText());
		}
		catch (NumberFormatException e) {
			MessageBox.displayMessage("Error with seed", "Seed can't be parsed, was:\n"+SeedField.getText()+"\nAborting now.");
			return;
		}
		List<InputStream> tactics = new LinkedList<InputStream>();
		for(File f : tacticsFiles) {
			try {
			tactics.add(new FileInputStream(f));
			}
			catch(IOException e) {
				MessageBox.displayMessage("Error with tactics", "Could not open tacticsfile "+f.getName()+"\nAborting now.");
				return;
			}
		}
		FileInputStream mapStream = null;
		try {
			mapStream = new FileInputStream(mapFile);
		}
		catch(IOException e) {
			MessageBox.displayMessage("Error with map", "Could not open map file "+mapFile.getName());
			return;
		}
		Controller controller;
		List<ExtendedLogWriter> loggers = new LinkedList<ExtendedLogWriter>();
		//TODO add the proper thing in here, instead of the debug logger
		loggers.add(new OutLogger());
		if(logFile == null)
			controller = Main.build(null, cycleCount, seed, mapStream, tactics, loggers);
		else {
			FileOutputStream out = null;
			try {
				out = new FileOutputStream(logFile);
			}
			catch (IOException e) {
				MessageBox.displayMessage("Error with log", "Could not create log file "+logFile.getName()+", aborting.");
				return;
			}
			PrintStream p = new PrintStream(out, true);
			System.setOut(p);
			controller = Main.build(logFile.getName(), cycleCount, seed, mapStream, tactics, loggers);
		}
		//TODO remove this when we actually play a game via the game screen
		StartButton.setDisable(true);
		MessageBox.displayMessage("Playing game now", "Game is now running, please wait");
		controller.play();
		StartButton.setDisable(false);
			
	}

	public void deleteTactics(TacticsListElement tacticsListElement) {
		int index = tacticsBox.getChildren().indexOf(tacticsListElement);
		tacticsBox.getChildren().remove(index);
		tacticsFiles.remove(index);
		if(tacticsFiles.isEmpty()) {
			tacticsFileAdded = false;
			StartButton.setDisable(true);
		}
			
	}

	public void moveTacticsUp(TacticsListElement tacticsListElement) {
		int index = tacticsBox.getChildren().indexOf(tacticsListElement);
		if(index == 0)
			return;
		tacticsBox.getChildren().remove(index);
		File tactics = tacticsFiles.get(index);
		tacticsFiles.remove(index);
		tacticsBox.getChildren().add(index -1, tacticsListElement);
		tacticsFiles.add(index -1, tactics);
	}

	public void moveTacticsDown(TacticsListElement tacticsListElement) {
		int index = tacticsBox.getChildren().indexOf(tacticsListElement);
		if(index +1 == tacticsFiles.size()) 
			return;
		tacticsBox.getChildren().remove(index);
		File tactics = tacticsFiles.get(index);
		tacticsFiles.remove(index);
		tacticsBox.getChildren().add(index +1, tacticsListElement);
		tacticsFiles.add(index +1, tactics);
	}

}
