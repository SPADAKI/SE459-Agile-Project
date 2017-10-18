package game;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class AgileGame extends Application {
	 public static void main(String[] args) {
		 launch(args);
	 }

	 private Stage window = null;
	 private int width = 800;
	 private int height = 600;

	 private LandingPage lanPage = new LandingPage(this, width, height);
	 private ProgressPage proPage = new ProgressPage(this, width, height);

	 @Override
	 public void start(Stage primaryStage) {
		 window = primaryStage;
		 window.setTitle("Agile Game - Group Six");

		 showLandingPage();
		 window.show();
	 }

	 public void showLandingPage() {
		 window.setScene(lanPage.getScene());
	 }

	 public void startGame() {
		 window.setScene(proPage.getScene());
	 }

	 public void showCongratulationPopup() {

	 }
}
