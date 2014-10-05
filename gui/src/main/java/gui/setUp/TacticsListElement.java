package gui.setUp;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import util.MessageBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class TacticsListElement extends HBox {
	
	private SetUp parent;
	private File tacticsFile;
	
	@FXML
    private Label NameLabel;

    @FXML
    private Label PathLabel;

    @FXML
    private Button ShowButton;

    @FXML
    private Button DeleteButton;

    @FXML
    private Button UpButton;

    @FXML
    private Button DownButton;
	
	public TacticsListElement(File path, SetUp parent) {
		this.tacticsFile = path;
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/setUp/TacticsListElement.fxml"));
		loader.setRoot(this);
		loader.setController(this);
		try {
		loader.load();
		}
		catch(IOException e) {
			e.printStackTrace();
			throw new UnsupportedOperationException(e.getCause());
		}
		this.parent = parent;
		this.NameLabel.setText(path.getName());
		this.PathLabel.setText(path.getAbsolutePath());
	}
	
	@FXML
	void showButtonPressed (ActionEvent event) {
		try {
			Text t = new Text();
			Scanner tac = null;
			tac = new Scanner(tacticsFile);
			tac.useDelimiter("\\A");
			t.setText(tac.next());
			tac.close();
			ScrollPane root = new ScrollPane();
			root.setContent(t);
			Scene tacticsScene = new Scene(root);
			Stage fileDisplay = new Stage();
			fileDisplay.setScene(tacticsScene);
			fileDisplay.setTitle(tacticsFile.getName());
			fileDisplay.setMinWidth(400);
			fileDisplay.setMinHeight(500);
			fileDisplay.show();
		}
		catch(IOException e) {
			MessageBox.displayMessage("Error with tactics file", "Could not open tactics file "+tacticsFile.getName()+".\nAborting");
		}
	}
	
	@FXML
	void downButtonPressed (ActionEvent event) {
		parent.moveTacticsDown(this);
	}
	
	@FXML
	void upButtonPressed (ActionEvent event) {
		parent.moveTacticsUp(this);
	}
	
	@FXML
	void deleteButtonPressed (ActionEvent event) {
		parent.deleteTactics(this);
	}
	
}
