package game;

import javafx.application.Application;
import javafx.stage.Stage;

public class AgileGame extends Application {
	 public static void main(String[] args) {
		 launch(args);
	 }

	 private Stage window = null;
	 private int width = 800;
	 private int height = 600;

	 private AGPage currentPage = null;
	 private LandingPage lanPage = new LandingPage(this, width, height);
	 private ProgressPage proPage = new ProgressPage(this, width, height);

	 @Override
	 public void start(Stage primaryStage) {
		 window = primaryStage;
		 window.setTitle("Agile Game - Group Six");

		 window.widthProperty().addListener((obs, oldVal, newVal) -> {
			 if(newVal.intValue() != width) {
				 width = newVal.intValue();
				 currentPage.updateSize(width, height);
			 }
		 });
		 window.heightProperty().addListener((obs, oldVal, newVal) -> {
			 if(newVal.intValue() != height) {
				 height = newVal.intValue();
				 currentPage.updateSize(width, height);
			 }
		 });

		 showLandingPage();
		 window.show();
	 }

	 public void showLandingPage() {
		 currentPage = lanPage;
		 currentPage.updateSize(width, height);
		 window.setScene(currentPage.getScene());
	 }

	 public void startGame() {
		 currentPage = proPage;
		 currentPage.updateSize(width, height);
		 window.setScene(currentPage.getScene());
	 }

	 public void showCongratulationPopup() {

	 }
}