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
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class ProgressPage extends AGPage {

	public ProgressPage(AgileGame app, int width, int height) {
		super(app, width, height);

		// Grid setup
        BorderPane pane = new BorderPane();
        HBox top = new HBox();
        Pane spacerTop = new Pane();
        Pane spacerBottom = new Pane();
        GridPane cardMiddle = new GridPane();
        GridPane questionGrid = new GridPane();
        HBox bottom = new HBox();
        questionGrid.setPadding(new Insets(10, 10, 10, 10));//top, right, bottom, and left padding

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

        ColumnConstraints left = new ColumnConstraints();
        left.setPercentWidth(33);
        ColumnConstraints mid= new ColumnConstraints();
        mid.setPercentWidth(33);
        ColumnConstraints right = new ColumnConstraints();
        right.setPercentWidth(33);
        cardMiddle.getColumnConstraints().addAll(left, mid, right);

//        int height = QuestionCard.getHeight();
//        int width = QuestionCard.getWidth();

        // Question buttons
        Button question1 = new Button("5 Points");
        question1.setPrefHeight(QuestionCard.getHeight());
        question1.setPrefWidth(QuestionCard.getWidth());
        cardMiddle.add(question1, 0, 0);

        Button question2 = new Button("3 Points");
        question2.setPrefHeight(QuestionCard.getHeight());
        question2.setPrefWidth(QuestionCard.getWidth());
        cardMiddle.add(question2, 1, 0);

        Button question3 = new Button("1 Point");
        question3.setPrefHeight(QuestionCard.getHeight());
        question3.setPrefWidth(QuestionCard.getWidth());
        cardMiddle.add(question3, 2, 0);

        question1.setOnAction(e -> {
            pane.setCenter(questionGrid);
        });

        question2.setOnAction(e -> {
            pane.setCenter(questionGrid);
        });

        question3.setOnAction(e -> {
            pane.setCenter(questionGrid);
        });

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

    //----------------------------------------------------------------------------------------------------------------//
    // Question Grid Setup

        // Set vertical and horizontal gap
        questionGrid.setVgap(10);//between element
        questionGrid.setHgap(10);
        // Column setup
        ColumnConstraints leftContraint = new ColumnConstraints();
        leftContraint.setPercentWidth(15);
        ColumnConstraints midConstraint = new ColumnConstraints();
        midConstraint.setPercentWidth(70);
        ColumnConstraints rightContraint = new ColumnConstraints();
        rightContraint.setPercentWidth(15);

        
        //question title in column 1, row 0
        Label pageTitle = new Label("QUESTION:");
        pageTitle.setStyle("-fx-font: bold 18 arial;");

        GridPane.setHalignment(pageTitle, HPos.CENTER);
        GridPane.setConstraints(pageTitle, 1, 0);

//>>>>>>>>>>>>>>>>>load from database works, need to work on auto generate buttons
        //if team 1 turn: print questions by idx from list
        int qIdx = 0;
        Team teamOne = LandingPage.teamOne;
        //Team teamOne = GameService.getInstance().getTeamOneName();
        IQuestion curQuestion = teamOne.getQuestions().get(qIdx);
        String qText = curQuestion.getQuestion();
        int curValue = curQuestion.getAnswer().getVal();
        int curScore = teamOne.getScore();
        int nextScore = teamOne.getScore() + curValue;


        //set location to column=0 row=1, columnSpan, rowSpan
        Text question = new Text(qText);
        GridPane.setConstraints(question, 0, 1);
        question.setStyle("-fx-font: bold 14 arial;");
        question.setWrappingWidth(app.getWidth());

        Button answerA = new Button(curQuestion.getOption(Option.fromInt(0)));
        Button answerB = new Button(curQuestion.getOption(Option.fromInt(1)));
        Button answerC = new Button(curQuestion.getOption(Option.fromInt(2)));
        Button answerD = new Button(curQuestion.getOption(Option.fromInt(3)));


        Button next = new Button("Next");
        GridPane.setConstraints(next, 0, 5);
        GridPane.setConstraints(answerA, 0, 2);
        answerA.wrapTextProperty().setValue(true);
        answerA.setMaxWidth(600);
        answerA.setAlignment(Pos.BASELINE_LEFT);
        GridPane.setConstraints(answerB, 0, 3);
        answerB.wrapTextProperty().setValue(true);
        answerB.setMaxWidth(600);
        answerB.setAlignment(Pos.BASELINE_LEFT);
        GridPane.setConstraints(answerC, 0, 4);
        answerC.wrapTextProperty().setValue(true);
        answerC.setMaxWidth(600);
        answerC.setAlignment(Pos.BASELINE_LEFT);


        // Button listener
           answerA.setOnAction(e -> {
           	if (true/*0 == curQuestion.getAnswer()*/) {
           		new CorrectAnswerBoard();
           		//curScore += curValue;


           	}
           	else
           	{
           		new WrongAnswerBoard();
           	}

           });

           answerB.setOnAction(e -> {
           	if (true/*0 == curQuestion.getAnswer()*/) {
           		new CorrectAnswerBoard();
           		//curScore += curValue;
           	}
           	else
           	{
           		new WrongAnswerBoard();
           	}

           });

           answerC.setOnAction(e -> {
           	if (true/*0 == curQuestion.getAnswer()*/) {
           		new CorrectAnswerBoard();
           		//curScore += curValue;
           	}
           	else
           	{
           		new WrongAnswerBoard();
           	}

           });

            answerD.setOnAction(e -> {
                if (true/*0 == curQuestion.getAnswer()*/) {
                    new CorrectAnswerBoard();
                    //curScore += curValue;
                }
                else
                {
                    new WrongAnswerBoard();
                }

            });

           next.setOnAction(e -> {

        	   //qIdx += 1;
        	   //load next question
        	   //another team's turn
                pane.setCenter(cardMiddle);
               });


        //statusArea: show users and scores

        Text statusText = new Text("Status board:");
        statusText.setStyle("-fx-font: bold 16 arial;");
        GridPane.setConstraints(statusText, 0, 6);

        Text curQuestionValue = new Text("Current question value: " + curValue);
        GridPane.setConstraints(curQuestionValue, 0, 7);

        Text scoreBoard = new Text("Team One current Total Scores: " + curScore);
        GridPane.setConstraints(scoreBoard, 0, 8);

        Text updateScoreBoard = new Text("If correct, Total Scores: " + nextScore);
        GridPane.setConstraints(updateScoreBoard, 0, 9);

        HBox statusArea = new HBox();
        statusArea.setPadding(new Insets(15, 5, 15, 12));
        statusArea.setSpacing(10);
        statusArea.setMaxWidth(width);
        GridPane.setConstraints(statusArea, 0, 7, 3, 30);

        // Add elements to the grid pane
         questionGrid.getChildren().addAll(pageTitle, question, answerA, answerB, answerC, next,
        		curQuestionValue, statusText, scoreBoard, statusArea, updateScoreBoard);
    //----------------------------------------------------------------------------------------------------------------//
        scene = new Scene(pane, width, height);

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

            int height = QuestionCard.getHeight();
            int width = QuestionCard.getWidth();

            // Question buttons
            Button question1 = new Button("5 Points");
            question1.setPrefHeight(QuestionCard.getHeight());
            question1.setPrefWidth(QuestionCard.getWidth());
            cardMiddle.add(question1, 0, 0);

            Button question2 = new Button("3 Points");
            question2.setPrefHeight(QuestionCard.getHeight());
            question2.setPrefWidth(QuestionCard.getWidth());
            cardMiddle.add(question2, 1, 0);

            Button question3 = new Button("1 Point");
            question3.setPrefHeight(QuestionCard.getHeight());
            question3.setPrefWidth(QuestionCard.getWidth());
            cardMiddle.add(question3, 2, 0);


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
