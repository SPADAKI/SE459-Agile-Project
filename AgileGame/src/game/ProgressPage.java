package game;

import java.util.List;

import database.IQuestion;
import database.IQuestion.Option;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;


public class ProgressPage extends AGPage {
	
	public ProgressPage(AgileGame app, int width, int height) {
		super(app, width, height);

		// Grid setup
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));//top, right, bottom, and left padding
        // Set vertical and horizontal gap
        grid.setVgap(10);//between element
        grid.setHgap(10);
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
        GridPane.setConstraints(pageTitle, 0, 0);
        GridPane.setHalignment(pageTitle, HPos.CENTER);
        

//>>>>>>>>>>>>>>>>>load from database works, need to work on auto generate buttons        
        //if team 1 turn: print questions by idx from list
        int qIdx = 0;
        Team teamOne = LandingPage.getTeamOne();
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
           
           next.setOnAction(e -> {
        	   
        	   //qIdx += 1;
        	   //load next question
        	   //another team's turn
              	
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
        grid.getChildren().addAll(pageTitle, question, answerA, answerB, answerC, next, 
        		curQuestionValue, statusText, scoreBoard, statusArea, updateScoreBoard);

        scene = new Scene(grid, width, height);

	}

}
