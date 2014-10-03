package util;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MessageBox extends VBox {
	
	private Stage ownStage;

	@FXML
    private Button OKButton;

    @FXML
    private Text messageText;
	
	public MessageBox (Stage ownStage, String title, String messageText) {
		this.ownStage = ownStage;
		this.ownStage.setTitle(title);
		FXMLLoader loader = new FXMLLoader(getClass().getResource("MessageBox.fxml"));
		loader.setController(this);
		loader.setRoot(this);
		try {
		loader.load();
		}
		catch(IOException e) {
			e.printStackTrace();
			throw new UnsupportedOperationException(e.getCause());
		}
		this.messageText.setText(messageText);
	}

    @FXML
    void onOKButtonClick(ActionEvent event) {
    	ownStage.close();
    }		
    
    public static void displayMessage(String title, String text) {
    	System.out.println(text);
    	Stage error = new Stage();
    	MessageBox errorM = new MessageBox(error, "Cycle count error", "Cycle count has to be number");
    	Scene errorS = new Scene(errorM);
    	error.setScene(errorS);
    	error.showAndWait();
    }
}
