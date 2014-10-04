package gui.main;

import gui.setUp.SetUp;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		SetUp p = new SetUp(primaryStage);
		Scene s = new Scene(p, 600, 550);
		primaryStage.setMinHeight(550);
		primaryStage.setMinWidth(700);
		primaryStage.setScene(s);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

}
