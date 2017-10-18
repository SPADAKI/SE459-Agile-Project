package game;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

public class LandingPage extends AGPage {
    private int numRows = 15;
    private static int MAX_NAME_LENGTH = 15;
    private static String teamOneName;
    private static String teamTwoName;
    private static int maxPlayers = 6;
    private static int numPlayerTeamOne;
    private static int numPlayerTeamTwo;

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
        //GridPane.setConstraints(gameTitle, 1, 0);

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

        // Continue to Add Players Scene
        Button continueToGame = new Button("Next");
        //GridPane.setConstraints(continueToGame, 8, 5);
        //continueToGame.setDisable(true);
        grid.add(continueToGame, 1, 5);

        // Add elements to the gridpane
        grid.getChildren().addAll(gameTitle);

        scene = new Scene(grid, width, height);

        submitTeamOneName.setOnAction(e -> {
            teamOneName = "";
            String tempName = teamOneNameInput.getText();
            if (!verifyName(tempName))
                AlertBox.display("Error!", "Invalid Name. Try Again.");
            else {
                teamOneName = tempName;
                AlertBox.display("Success", String.format("Team Name '%s' successfully created.", teamOneName));
                if (checkTeamNames(teamOneName, teamTwoName))
                    continueToGame.setDisable(false);
            }

        });

        submitTeamTwoName.setOnAction(e -> {
            teamTwoName = "";
            String tempName = teamTwoNameInput.getText();
            if (!verifyName(tempName))
                AlertBox.display("Error!", "Invalid Name. Try Again.");
            else {
                teamTwoName = tempName;
                AlertBox.display("Success", String.format("Team Name '%s' successfully created.", teamTwoName));
                if (checkTeamNames(teamOneName, teamTwoName))
                    continueToGame.setDisable(false);
            }
        });

        submitPlayerTeamOne.setOnAction(e -> {
            String name = addPlayerTeamOne.getText();
            int x = checkTeamOneName(name);
            if (x != -1) {
                grid.add(new Label(name), 0, x + 3);
                GridPane.setConstraints(addPlayerTeamOne, 0, x + 4);
                GridPane.setConstraints(submitPlayerTeamOne, 0, x + 5);
                numPlayerTeamOne += 1;
            } else
                AlertBox.display("Error!", "Invalid Name. Try Again.");
        });

        submitPlayerTeamTwo.setOnAction(e -> {
            String name = addPlayerTeamTwo.getText();
            int x = checkTeamTwoName(name);
            if (x != 0) {
                grid.add(new Label(name), 2, x + 3);
                GridPane.setConstraints(addPlayerTeamTwo, 2, x + 4);
                GridPane.setConstraints(submitPlayerTeamTwo, 2, x + 5);
                numPlayerTeamTwo += 1;
            } else
                AlertBox.display("Error!", "Invalid Name. Try Again.");
        });

        continueToGame.setOnAction( e -> {
        	application.startGame();
        });
	}

    // Method doesn't allow null names, or strings below 0, or above 20 in length
    // Future: Should check if team names are the same
    private boolean verifyName(String name) {
        if (name == null || name.length() <= 0 || name.length() > 20)
            return false;
        return true;
    }

    private boolean checkTeamNames(String one, String two) {
        return one != null && two != null;
    }

    // Checks for valid player name
    private int checkTeamOneName(String name) {
        if (name != null && name.length() > 0 && name.length() <= MAX_NAME_LENGTH)
            return numPlayerTeamOne;
        return -1;
    }

    // Checks for valid player name
    private int checkTeamTwoName(String name) {
        if (name != null && name.length() > 0 && name.length() <= MAX_NAME_LENGTH)
            return numPlayerTeamTwo;
        return -1;
    }
}
