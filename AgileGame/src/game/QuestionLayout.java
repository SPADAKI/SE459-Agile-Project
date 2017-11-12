package game;

import java.util.List;

import database.IQuestion;
import database.IQuestion.Option;
import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class QuestionLayout extends Pane {
	private QuestionLayoutDelegate delegate;
	private Text txtQuestion;
	private Button[] buttons;
	private Button btnNext;
	private int selectedIdx = -1;

	public QuestionLayout(QuestionLayoutDelegate del) {
		super();

		delegate = del;

		GridPane pane = new GridPane();
		pane.setPadding(new Insets(56, 100, 0, 100));
		pane.setVgap(12);

        //set location to column=0 row=1, columnSpan, rowSpan
		txtQuestion = new Text("txtQuestion");
        GridPane.setConstraints(txtQuestion, 0, 1);
        txtQuestion.setStyle("-fx-font: bold 16 arial;");
        txtQuestion.setWrappingWidth(600);
        pane.getChildren().add(txtQuestion);

        buttons = new Button[4];
        for(int i=0; i<4; i++) {
        	buttons[i] = new Button("button"+i);
        	buttons[i].wrapTextProperty().setValue(true);
        	buttons[i].setMaxWidth(600);
        	buttons[i].setAlignment(Pos.BASELINE_LEFT);
            GridPane.setConstraints(buttons[i], 0, 2+i);
            pane.getChildren().add(buttons[i]);

            int tag = i;
            buttons[i].setOnAction(e -> {
            	selectedIdx = tag;
            });
        }

        //set next button to right alignment 
        btnNext = new Button("Next");
        btnNext.setAlignment(Pos.CENTER_RIGHT);
        GridPane.setHalignment(btnNext, HPos.RIGHT);
        btnNext.setOnAction(e -> {
        	// Should select an answer first
        	if(selectedIdx != -1) {
        		delegate.selectAnswer(selectedIdx);
        	}
        });
        pane.getChildren().add(btnNext);

        getChildren().add(pane);
     
        Button QuitGame =new Button ("Quit");
        QuitGame.setAlignment(Pos.CENTER_RIGHT);
        GridPane.setHalignment(QuitGame, HPos.RIGHT);
        QuitGame.setDisable(false);
        pane.add(QuitGame, 0, 0);
        QuitGame.setOnAction(e -> QuitGame());
	}


	//Quit Function called
	private void QuitGame() {
		Boolean answer = AlertBoxNoOption.display("Quit Game?","Sure you want to quit?");
		if(answer==true) {
			System.out.println("You Quit the Game");
			Platform.exit();
			System.exit(0);
		}
		else {
			System.out.println("Continue");
		}
	}
	public void setQuestion(IQuestion que) {
		txtQuestion.setText(que.getQuestion());
		String[] options = que.getOptions();
		for(int i=0; i<4; i++) {
			if(i < options.length) {
				buttons[i].setVisible(true);
				buttons[i].setText(options[i]);
			}
			else {
				buttons[i].setVisible(false);
			}
		}
		 GridPane.setConstraints(btnNext, 0, options.length+2);
	}

}
