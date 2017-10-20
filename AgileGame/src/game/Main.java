package game;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import static java.awt.SystemColor.window;

public class Main extends Application {

    private Stage window;
    private Scene main;
    private int width = 800;
    private int height = 300;
    private static String teamOneName;
    private static String teamTwoName;

    @Override
    public void start(Stage primaryStage) throws Exception{
        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        window = primaryStage;
        primaryStage.setTitle("Agile Game - Group Six");


        // Grid setup
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.getColumnConstraints().add(new ColumnConstraints(150));
        grid.getColumnConstraints().add(new ColumnConstraints(100));
        grid.getColumnConstraints().add(new ColumnConstraints(150));

        // Set vertical and horizontal gap
        grid.setVgap(10);
        grid.setHgap(10);

        Label gameTitle = new Label("Team Six Agile Game");
        GridPane.setConstraints(gameTitle, 2, 0);

        // Team One Name Input fields
        TextField teamOneNameInput = new TextField();
        teamOneNameInput.setPromptText("Team One Name");
        GridPane.setConstraints(teamOneNameInput, 0, 1);
        Button submitTeamOneName = new Button("Submit");
        GridPane.setConstraints(submitTeamOneName, 0, 2);

        // Team Two Name Input fields
        TextField teamTwoNameInput = new TextField();
        teamTwoNameInput.setPromptText("Team Two Name");
        GridPane.setConstraints(teamTwoNameInput, 8, 1);
        Button submitTeamTwoName = new Button("Submit");
        GridPane.setConstraints(submitTeamTwoName, 8, 2);

        // Continue to Add Players Scene
        Button continueToPlayerCreation = new Button("Next");
        GridPane.setConstraints(continueToPlayerCreation, 8, 5);
        continueToPlayerCreation.setDisable(true);
        
        //Start game button ****
        Button startGameInterface = new Button("start game");
        GridPane.setConstraints(startGameInterface,2, 8);

        // Add elements to the gridpane
        grid.getChildren().addAll(gameTitle, teamOneNameInput, submitTeamOneName, teamTwoNameInput, submitTeamTwoName, continueToPlayerCreation, startGameInterface);

        main = new Scene(grid, width, height);

        submitTeamOneName.setOnAction(e -> {
            teamOneName = "";
            String tempName = teamOneNameInput.getText();
            if (!verifyName(tempName))
                AlertBox.display("Error!", "Invalid Name. Try Again.");
            else {
                teamOneName = tempName;
                AlertBox.display("Success", String.format("Team Name '%s' successfully created.", teamOneName));
                if (checkTeamNames(teamOneName, teamTwoName))
                    continueToPlayerCreation.setDisable(false);
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
                    continueToPlayerCreation.setDisable(false);
            }
        });
        
        // new page ***
        startGameInterface.setOnAction(e -> {
            new ProgressPage();
            
        });
        


        primaryStage.setScene(main);
        primaryStage.show();
    }

    // Method doesn't allow null names, or strings below 0, or above 20 in length
    // Future: Should check if team names are the same
    private boolean verifyName(String name) {
        if (name == null || name.length() <= 0 || name.length() > 20)
            return false;
        return true;
    }

    private boolean checkTeamNames(String one, String two) {
        if (one == null && two == null)
            return false;
        return true;
    }
    public static void main(String[] args) {
        launch(args);
    }
}
