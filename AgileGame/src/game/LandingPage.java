package game;

import java.util.List;

import database.IQuestion;
import database.QuestionProvider;
import exception.DuplicatePlayerException;
import exception.NullPlayerException;
import exception.NullTeamException;
import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.ImagePattern;

public class LandingPage extends AGPage {
	private int MAX_NAME_LENGTH = 15;
	private String teamOneName;
	private String teamTwoName;
	private int maxPlayers = 5;
	private int numPlayerTeamOne;
	private int numPlayerTeamTwo;
	private Team teamOne = new Team(""), teamTwo = new Team("");

	public LandingPage(AgileGame app, int width, int height) {
		super(app, width, height);

		// Variable initiations
		numPlayerTeamOne = 0;
		numPlayerTeamTwo = 0;

		// Grid setup
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(10, 10, 10, 10));
		// Column setup
		ColumnConstraints leftContraint = new ColumnConstraints();
		leftContraint.setPercentWidth(25);
		ColumnConstraints midConstraint = new ColumnConstraints();
		midConstraint.setPercentWidth(50);
		ColumnConstraints rightContraint = new ColumnConstraints();
		rightContraint.setPercentWidth(25);
		// Row setup
		for (int i = 0; i < 10; i++) {
			RowConstraints row = new RowConstraints(30);
			grid.getRowConstraints().add(row);
		}
		grid.getColumnConstraints().addAll(leftContraint, midConstraint, rightContraint);

		// Set vertical and horizontal gap
		grid.setVgap(10);
		grid.setHgap(10);

		Label gameTitle = new Label("Team Six Agile Game");
		GridPane.setHalignment(gameTitle, HPos.CENTER);
		GridPane.setConstraints(gameTitle, 1, 0);

		// Team One Name Input fields
		TextField teamOneNameInput = new TextField();
		teamOneNameInput.setPromptText("Team One Name");
		grid.add(teamOneNameInput, 0, 1);
		Button submitTeamOneName = new Button("Submit Team Name");
		grid.add(submitTeamOneName, 0, 2);
		TextField addPlayerTeamOne = new TextField();
		grid.add(addPlayerTeamOne, 0, 3);
		Button submitPlayerTeamOne = new Button("Submit Player Name");
		grid.add(submitPlayerTeamOne, 0, 4);

		// Team Two Name Input fields
		TextField teamTwoNameInput = new TextField();
		teamTwoNameInput.setPromptText("Team Two Name");
		grid.add(teamTwoNameInput, 2, 1);
		Button submitTeamTwoName = new Button("Submit Team Name");
		grid.add(submitTeamTwoName, 2, 2);
		TextField addPlayerTeamTwo = new TextField();
		grid.add(addPlayerTeamTwo, 2, 3);
		Button submitPlayerTeamTwo = new Button("Submit Player Name");
		grid.add(submitPlayerTeamTwo, 2, 4);

		// Create Two Teams Object based on input

		teamTwo = new Team(teamTwoNameInput.getText());

		// Continue to Progress Page Button, add image to start button
		Image imageStart = new Image("game/startButton.png", 80, 30, false, false);
		Button continueToGame = new Button("", new ImageView(imageStart));
		continueToGame.setAlignment(Pos.CENTER_RIGHT);
		continueToGame.setDisable(true);
		grid.add(continueToGame, 1, 10);

		//Quit Button on Landing Page, style with png
		Image imageQuit = new Image("game/quitButton.png", 80, 30, false, false);
		Button QuitGame = new Button("", new ImageView(imageQuit));
//        Button QuitGame =new Button ("Quit Game");

        GridPane.setHalignment(QuitGame, HPos.RIGHT);

        QuitGame.setDisable(false);
        grid.add(QuitGame, 1, 10);
        QuitGame.setOnAction(e -> QuitGame());

		// Add elements to the gridpane
		grid.getChildren().addAll(gameTitle);

		scene = new Scene(grid, width, height);
		grid.setStyle("-fx-background-image: url(game/background2.png); -fx-background-size: 800 600; -fx-background-repeat: stretch");


		// ========================================================================================================

		submitTeamOneName.setOnAction(e -> {

			// set team name to static, easy to retrieve, and cleaner without
			// Exception
			teamOne.setTeamName(teamOneNameInput.getText());
			System.out.println("Set Team1 name to: " + teamOne.getTeamName());
			if (!verifyTeamName(teamOne.getTeamName()))
				AlertBox.display("Error!", "Invalid Name. Try Again.");
			else {
				AlertBox.display("Success",
						String.format("Team Name '%s' successfully created.", teamOne.getTeamName()));
				if (checkTeamNames(teamOne.getTeamName(), teamTwo.getTeamName()))
					continueToGame.setDisable(false);
				try {
					GameService.getInstance().setUp(teamOne, teamTwo);
				} catch (NullTeamException error) {
				}
			}
		});

		submitTeamTwoName.setOnAction(e -> {
			teamTwo.setTeamName(teamTwoNameInput.getText());
			System.out.println("Set Team2 name to: " + teamTwo.getTeamName());
			if (!verifyTeamName(teamTwo.getTeamName()))
				AlertBox.display("Error!", "Invalid Name. Try Again.");
			else {
				AlertBox.display("Success",
						String.format("Team Name '%s' successfully created.", teamTwo.getTeamName()));
				if (checkTeamNames(teamOne.getTeamName(), teamTwo.getTeamName()))
					continueToGame.setDisable(false);
				try {
					GameService.getInstance().setUp(teamOne, teamTwo);
				} catch (NullTeamException error) {
				}
			}
		});

		// Setting Limit to maximum (5) number of Team Members need to be added
		// to each team
		submitPlayerTeamOne.setOnAction(e -> {
			String name = addPlayerTeamOne.getText();
			boolean x = checkTeamOnePlayerName(name);
			try {
				if (numPlayerTeamOne == maxPlayers)
					AlertBox.display("Error!", "Max Number of Players reached.");
				else if (x) {
					GameService.getInstance().addPlayerTeamOne(new Player(name));
					grid.add(new Label(name), 0, numPlayerTeamOne + 3);
					GridPane.setConstraints(addPlayerTeamOne, 0, numPlayerTeamOne + 4);
					GridPane.setConstraints(submitPlayerTeamOne, 0, numPlayerTeamOne + 5);
					numPlayerTeamOne += 1;
				} else
					AlertBox.display("Error!", "Invalid Name. Try Again.");
			} catch (NullPlayerException | DuplicatePlayerException | NullTeamException error) {
				AlertBox.display("Error!", error.getMessage());
			}
			addPlayerTeamOne.clear();
		});

		submitPlayerTeamTwo.setOnAction(e -> {
			String name = addPlayerTeamTwo.getText();
			boolean x = checkTeamTwoPlayerName(name);
			try {
				if (numPlayerTeamTwo == maxPlayers)
					AlertBox.display("Error!", "Max Number of Players reached.");
				else if (x) {
					GameService.getInstance().addPlayerTeamTwo(new Player(name));
					grid.add(new Label(name), 2, numPlayerTeamTwo + 3);
					GridPane.setConstraints(addPlayerTeamTwo, 2, numPlayerTeamTwo + 4);
					GridPane.setConstraints(submitPlayerTeamTwo, 2, numPlayerTeamTwo + 5);
					numPlayerTeamTwo += 1;
				} else
					AlertBox.display("Error!", "Invalid Name. Try Again.");
			} catch (NullPlayerException | DuplicatePlayerException | NullTeamException error) {
				AlertBox.display("Error!", error.getMessage());
			}
			addPlayerTeamTwo.clear();
		});

		// Load questions to each team list after continue
		continueToGame.setOnAction(e -> {
			application.startGame();
		});
	}


    //Quit Function called
	private void QuitGame() {
		Boolean answer = AlertBox.display("Quit Game?","Sure you want to quit?");
		if(answer==true) {
			System.out.println("You Quit the Game");
			Platform.exit();
			System.exit(0);
		}
		else {
			System.out.println("Continue");
		}
	}

	//private static void exit() {
		// TODO Auto-generated method stub
	//	Platform.exit();
	//	System.exit(0);
	//}

	// Method doesn't allow null names, or strings below 0, or above 20 in length
    // Future: Should check if team names are the same
    private boolean verifyTeamName(String name) {
        if (name == null || name.length() <= 0 || name.length() > 20)
            return false;
        return true;
    }

	private boolean checkTeamNames(String one, String two) {
		return one != null && two != null;
	}

	// Checks for valid player name
	private boolean checkTeamOnePlayerName(String name) {
		if (name != null && name.length() > 0 && name.length() <= MAX_NAME_LENGTH)
			return true;
		return false;
	}

	// Checks for valid player name
	private boolean checkTeamTwoPlayerName(String name) {
		if (name != null && name.length() > 0 && name.length() <= MAX_NAME_LENGTH)
			return true;
		return false;
	}

}
