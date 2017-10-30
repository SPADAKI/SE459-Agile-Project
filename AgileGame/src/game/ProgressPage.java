package game;

import javafx.application.Application;
import database.QuestionProvider;
import database.SingleChoiceQuestion;
import java.util.List;
import database.IQuestion;
import database.IQuestion.Option;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class ProgressPage extends AGPage implements QuestionLayoutDelegate {
	private QuestionLayout questionLayout;
	private CardLayout cardLayout;
	private TeamLayout teamLayout;

	private IQuestion curQuestion;

	public ProgressPage(AgileGame app, int width, int height) {
		super(app, width, height);

		Pane pane = new Pane();

		questionLayout = new QuestionLayout(this);
		cardLayout = new CardLayout();
		cardLayout.setVisible(false);
		teamLayout = new TeamLayout(GameService.getInstance().getTeamOne(), GameService.getInstance().getTeamTwo());
		teamLayout.setLayoutY(400);

		pane.getChildren().addAll(questionLayout, cardLayout, teamLayout);

		curQuestion = GameService.getInstance().startNewRound();
		questionLayout.setQuestion(curQuestion);

		scene = new Scene(pane, width, height);
	}

	// delegate
	public void selectAnswer(int idx) {
		Alert alert = new Alert(AlertType.INFORMATION);
		if(idx == curQuestion.getAnswer().getVal()) {
			// right answer
			GameService.getInstance().addPointToCurrentTeam(1);

			alert.setTitle("Congratulation");
			alert.setHeaderText(null);
			Team team = GameService.getInstance().getCurrentTeam();
			alert.setContentText("Your answer is right!" + team.getTeamName() + " get 1 point!");
		}
		else {
			// wrong answer
			alert.setTitle("Sorry");
			alert.setHeaderText(null);
			alert.setContentText("Your answer is wrong!");
		}
		alert.showAndWait();
		// go to next question
		curQuestion = GameService.getInstance().nextTurn();
		if(curQuestion == null) {
			// no more question, game over, go back to the LandingPage
			application.showLandingPage();
		}
		else {
			questionLayout.setQuestion(curQuestion);
			teamLayout.refreshData(GameService.getInstance().getCurrentTeamIdx());
		}
	}




	// For testing
	public ProgressPage(){
            // Grid setup
            BorderPane pane = new BorderPane();
            HBox top = new HBox();
            Pane spacerTop = new Pane();
            Pane spacerBottom = new Pane();
            GridPane cardMiddle = new GridPane();
            GridPane questionGrid = new GridPane();
            HBox bottom = new HBox();

            pane.setPadding(new Insets(10, 20, 10, 20));//top, right, bottom, and left padding
            Insets insets = new Insets(10, 0, 10, 0);
            BorderPane.setMargin(cardMiddle, insets);

            // Set gap between elements in the middle gridpane
            cardMiddle.setHgap(20);

            pane.setTop(top);
            pane.setCenter(cardMiddle);
            pane.setBottom(bottom);

            // for testing
            //cardMiddle.setGridLinesVisible(true);
            Label teamOneName = new Label(GameService.getInstance().getTeamOneName());
            Label teamTwoName = new Label(GameService.getInstance().getTeamTwoName());

            teamOneName.setMinSize(Button.USE_PREF_SIZE, Button.USE_PREF_SIZE);
            HBox.setHgrow(spacerTop, Priority.ALWAYS);
            spacerTop.setMinSize(10, 1);
            teamTwoName.setMinSize(Button.USE_PREF_SIZE, Button.USE_PREF_SIZE);

            top.getChildren().addAll(teamOneName, spacerTop, teamTwoName);
        //------------------------------------------------------------------------------------------------------------//
        // Mid Gridpane

            ColumnConstraints topConstraint = new ColumnConstraints();
            topConstraint.setPercentWidth(33);
            ColumnConstraints midConstraint = new ColumnConstraints();
            midConstraint.setPercentWidth(33);
            ColumnConstraints botConstraint = new ColumnConstraints();
            botConstraint.setPercentWidth(33);
            cardMiddle.getColumnConstraints().addAll(topConstraint, midConstraint, botConstraint);



        //------------------------------------------------------------------------------------------------------------//
        // Bot HBox

            bottom.setSpacing(20);
            teamOneName.setMinSize(Button.USE_PREF_SIZE, Button.USE_PREF_SIZE);
            spacerBottom.setMinSize(10, 1);
            HBox.setHgrow(spacerBottom, Priority.ALWAYS);
            teamTwoName.setMinSize(Button.USE_PREF_SIZE, Button.USE_PREF_SIZE);

//            ColumnConstraints teamOneNameConstraint = new ColumnConstraints();
//            teamOneNameConstraint.setPercentWidth(50);
//            ColumnConstraints teamTwoNameConstraint = new ColumnConstraints();
//            teamTwoNameConstraint.setPercentWidth(50);
            List teamOneMembers = GameService.getInstance().getTeamOnePlayers();
            List teamTwoMembers = GameService.getInstance().getTeamTwoPlayers();
            Label[] teamOneNames = new Label[teamOneMembers.size()];
            Label[] teamTwoNames = new Label[teamTwoMembers.size()];

            // Create labels for each player on both teams
            for(int i = 0; i < teamOneMembers.size(); i++) {
                Player player = (Player) teamOneMembers.get(i);
                teamOneNames[i] = new Label(player.getName());
            }
            for(int i = 0; i < teamTwoMembers.size(); i++) {
                Player player = (Player) teamTwoMembers.get(i);
                teamTwoNames[i] = new Label(player.getName());
            }

            // Place player name labels in the grid
//            for (int i = 0; i < teamOneMembers.size(); i++) {
//                Player player = (Player) teamOneMembers.get(i);
//                bottom.add(new Label(player.getName()), i, 0, 1, 1);
//            }
//            bottom.getChildren().addAll(spacer);
//            for (int i = 0; i < teamTwoMembers.size(); i++) {
//                Player player = (Player) teamTwoMembers.get(i);
//                bottom.get(new Label(player.getName()), i + 6, 0, 1, 1);
//            }

            //bottom.setGridLinesVisible(true);
            // Add player names as children to the grid
            for (int i = 0; i < teamOneMembers.size(); i++)
                bottom.getChildren().add(teamOneNames[i]);
            bottom.getChildren().addAll(spacerBottom);
            for (int i = 0; i < teamTwoMembers.size(); i++)
                bottom.getChildren().add(teamTwoNames[i]);


            scene = new Scene(pane, width, height);
    }
}
