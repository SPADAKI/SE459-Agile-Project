package game;

import java.util.List;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.paint.ImagePattern;

public class TeamLayout extends Pane {
	private Team team1, team2;
	private Text txtTeamOneScore;
	private Text txtTeamOne;
	private Text txtTeamTwo;
	private Text txtTeamTwoScore;
	private Circle highlight;
	private ImageView teamTurn;

	public TeamLayout(Team t1, Team t2) {
		super();
		team1 = t1;
		team2 = t2;
		final int width = 800, height = 200, paddingH = 36, paddingV = 25;

		// Team one, left
		Pane leftPane = new Pane();
		leftPane.setLayoutX(20);
		txtTeamOne = new Text("Team: " + t1.getTeamName());
	    txtTeamOne.setStyle("-fx-font: bold 18 arial;");
	    txtTeamOne.setFill(Color.RED);
	    txtTeamOne.setLayoutX(paddingH+25);
	    txtTeamOne.setLayoutY(paddingV);
	    leftPane.getChildren().add(txtTeamOne);

	    txtTeamOneScore = new Text("Score: "+t1.getScore());
	    txtTeamOneScore.setStyle("-fx-font: bold 18 arial;");
	    txtTeamOneScore.setLayoutX(paddingH+25);
	    txtTeamOneScore.setLayoutY(paddingV+30);
	    leftPane.getChildren().add(txtTeamOneScore);

	    List<Player> mems1 = t1.getMembers();
	    for(int i=0; i<mems1.size(); i++) {
	    	Player mem = mems1.get(i);
	    	Label label = new Label(mem.getName());
	    	label.setWrapText(true);
	    	label.setLayoutX(paddingH+60*i);
	    	label.setLayoutY(paddingV+40);
	    	label.setStyle("-fx-font: bold 14 arial;");
	    	leftPane.getChildren().add(label);
	    }

	    // Team two, right
		Pane rightPane = new Pane();
		rightPane.setLayoutX(width/2);

		txtTeamTwo = new Text("Team: " + t2.getTeamName());
		txtTeamTwo.setStyle("-fx-font: bold 18 arial;");
		txtTeamTwo.setLayoutX(paddingH+32);
		txtTeamTwo.setLayoutY(paddingV);
		rightPane.getChildren().add(txtTeamTwo);

	    txtTeamTwoScore = new Text("Score: "+t2.getScore());
	    txtTeamTwoScore.setStyle("-fx-font: bold 18 arial;");
	    txtTeamTwoScore.setLayoutX(paddingH+32);
	    txtTeamTwoScore.setLayoutY(paddingV+30);
	    rightPane.getChildren().add(txtTeamTwoScore);

	    List<Player> mems2 = t2.getMembers();
	    for(int i=0; i<mems2.size(); i++) {
	    	Player mem = mems2.get(i);
	    	Label label = new Label(mem.getName());
	    	label.setWrapText(true);
	    	label.setLayoutX(paddingH+60*i);
	    	label.setLayoutY(paddingV+40);
	    	label.setStyle("-fx-font: bold 14 arial;");
	    	rightPane.getChildren().add(label);
	    }
	 
	    teamTurn = new ImageView(new Image("game/teamTurnIndicator.png", 40, 50, false, false));
	     	
	    Rectangle scoreBoardBG = new Rectangle();
	    scoreBoardBG.setX(0);
	    scoreBoardBG.setY(0);
	    scoreBoardBG.setHeight(150);
	    scoreBoardBG.setWidth(800);
	    Image paneURL = new Image("game/teamPane2.png");
	    scoreBoardBG.setFill(new ImagePattern(paneURL, 0, 0, 1, 1, true));
        getChildren().addAll(scoreBoardBG, leftPane, rightPane, teamTurn);
	}

	public void refreshData(int curTeamIdx) {
		txtTeamOneScore.setText("Score: "+team1.getScore());
		txtTeamTwoScore.setText("Score: "+team2.getScore());

		if(curTeamIdx == 0) {
			
			teamTurn.setLayoutX(36);
			txtTeamTwo.setStyle("-fx-font: bold 18 arial;");
			txtTeamTwo.setFill(Color.BLACK);
			txtTeamOne.setStyle("-fx-font: bold 18 arial;");
			txtTeamOne.setFill(Color.RED);
			}
		else {
			teamTurn.setX(420);
			txtTeamOne.setStyle("-fx-font: bold 18 arial;");
			txtTeamOne.setFill(Color.BLACK);
			txtTeamTwo.setStyle("-fx-font: bold 18 arial;");
			txtTeamTwo.setFill(Color.RED);
		
		}
	}
}
