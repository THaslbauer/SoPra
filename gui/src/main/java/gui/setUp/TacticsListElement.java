package gui.setUp;

import java.io.File;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class TacticsListElement extends HBox {
	
	private SetUp parent;
	
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
