package game;

import exception.DuplicatePlayerException;
import exception.NullPlayerException;
import exception.NullTeamException;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

public class LandingPage extends AGPage {
    private static int MAX_NAME_LENGTH = 15;
    private static String teamOneName;
    private static String teamTwoName;
    private static int maxPlayers = 5;
    private static int numPlayerTeamOne;
    private static int numPlayerTeamTwo;
    private Team teamOne, teamTwo;
    

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
        teamOne = new Team(teamOneNameInput.getText());
        teamTwo= new Team(teamTwoNameInput.getText());
        
        
        // Continue to Progress Page Button
        Button continueToGame = new Button("Start Game");
        continueToGame.setAlignment(Pos.CENTER_RIGHT);
        continueToGame.setDisable(true);
        grid.add(continueToGame, 1, 5);
        
        //Quit Button on Landing Page
        Button QuitGame =new Button ("Quit Game");
        QuitGame.setAlignment(Pos.CENTER_RIGHT);
        QuitGame.setDisable(false);
        grid.add(QuitGame, 2, 5);
        

        // Add elements to the gridpane
        grid.getChildren().addAll(gameTitle);

        scene = new Scene(grid, width, height);

        submitTeamOneName.setOnAction(e -> {
            teamOneName = "";
            String tempName = teamOneNameInput.getText();
            if (!verifyTeamName(tempName))
                AlertBox.display("Error!", "Invalid Name. Try Again.");
            else {
                teamOneName = tempName;
                AlertBox.display("Success", String.format("Team Name '%s' successfully created.", teamOneName));
                if (checkTeamNames(teamOneName, teamTwoName))
                    continueToGame.setDisable(false);            
                try {
                	GameService.getInstance().setUp(teamOne, teamTwo);
                } catch (NullTeamException error) {}
            }
            teamOneNameInput.clear();
        });

        submitTeamTwoName.setOnAction(e -> {
            teamTwoName = "";
            String tempName = teamTwoNameInput.getText();
            if (!verifyTeamName(tempName))
                AlertBox.display("Error!", "Invalid Name. Try Again.");
            else {
                teamTwoName = tempName;
                AlertBox.display("Success", String.format("Team Name '%s' successfully created.", teamTwoName));
                if (checkTeamNames(teamOneName, teamTwoName))
                    continueToGame.setDisable(false);
                try {
                	GameService.getInstance().setUp(teamOne, teamTwo);
                } catch (NullTeamException error) {}
            }
            teamTwoNameInput.clear();
        });
        
        //Setting Limit to maximum (5) number of Team Members need to be added to each team
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
            } catch (NullPlayerException | DuplicatePlayerException | NullTeamException error){
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
            } catch (NullPlayerException | DuplicatePlayerException | NullTeamException error){
                AlertBox.display("Error!", error.getMessage());
            }
            addPlayerTeamTwo.clear();
        });

        continueToGame.setOnAction( e -> {
            if (GameService.getInstance().checkSetUp())
        	    application.startGame();
            else
                AlertBox.display("Error!", "Game parameters not set up!");
        });
	}

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
    
    private void Quit() {
    	System.out.println("You Quit the Game");
    	
    }
}
