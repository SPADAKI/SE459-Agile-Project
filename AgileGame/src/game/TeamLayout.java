package game;

import java.util.List;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class TeamLayout extends Pane {
	private Team team1, team2;
	private Text txtTeamOneScore;
	private Text txtTeamTwoScore;
	private Circle highlight;

	public TeamLayout(Team t1, Team t2) {
		super();
		team1 = t1;
		team2 = t2;
		final int width = 800, height = 200, paddingH = 36, paddingV = 18;

		// Team one, left
		Pane leftPane = new Pane();
		Text txtTeamOne = new Text(t1.getTeamName());
	    txtTeamOne.setStyle("-fx-font: bold 18 arial;");
	    txtTeamOne.setLayoutX(paddingH+32);
	    txtTeamOne.setLayoutY(paddingV);
	    leftPane.getChildren().add(txtTeamOne);

	    txtTeamOneScore = new Text("Score: "+t1.getScore());
	    txtTeamOneScore.setStyle("-fx-font: bold 18 arial;");
	    txtTeamOneScore.setLayoutX(paddingH+120);
	    txtTeamOneScore.setLayoutY(paddingV);
	    leftPane.getChildren().add(txtTeamOneScore);

	    List<Player> mems1 = t1.getMembers();
	    for(int i=0; i<mems1.size(); i++) {
	    	Player mem = mems1.get(i);
	    	Label label = new Label(mem.getName());
	    	label.setWrapText(true);
	    	label.setLayoutX(paddingH+60*i);
	    	label.setLayoutY(paddingV+60);
	    	label.setStyle("-fx-font: bold 14 arial;");
	    	leftPane.getChildren().add(label);
	    }

	    // Team two, right
		Pane rightPane = new Pane();
		rightPane.setLayoutX(width/2);

		Text txtTeamTwo = new Text(t2.getTeamName());
		txtTeamTwo.setStyle("-fx-font: bold 18 arial;");
		txtTeamTwo.setLayoutX(paddingH+32);
		txtTeamTwo.setLayoutY(paddingV);
		rightPane.getChildren().add(txtTeamTwo);

	    txtTeamTwoScore = new Text("Score: "+t2.getScore());
	    txtTeamTwoScore.setStyle("-fx-font: bold 18 arial;");
	    txtTeamTwoScore.setLayoutX(paddingH+120);
	    txtTeamTwoScore.setLayoutY(paddingV);
	    rightPane.getChildren().add(txtTeamTwoScore);

	    List<Player> mems2 = t2.getMembers();
	    for(int i=0; i<mems2.size(); i++) {
	    	Player mem = mems2.get(i);
	    	Label label = new Label(mem.getName());
	    	label.setWrapText(true);
	    	label.setLayoutX(paddingH+60*i);
	    	label.setLayoutY(paddingV+60);
	    	label.setStyle("-fx-font: bold 14 arial;");
	    	rightPane.getChildren().add(label);
	    }
	    
	    highlight = new Circle();
	    highlight.setCenterX(50);
	    highlight.setCenterY(14);
	    highlight.setRadius(4.0f);
	    highlight.setFill(Color.BLUE);
	     	
	    Rectangle r = new Rectangle();
	    r.setX(paddingV);
	    r.setY(paddingH);
	    r.setHeight(120);
	    r.setWidth(764);
	    
	    r.setFill(Color.web("rgba(255,255,255,0.75)"));
        getChildren().addAll(r, leftPane, rightPane, highlight);
	}

	public void refreshData(int curTeamIdx) {
		txtTeamOneScore.setText("Score: "+team1.getScore());
		txtTeamTwoScore.setText("Score: "+team2.getScore());

		if(curTeamIdx == 0) highlight.setCenterX(50);
		else highlight.setCenterX(450);
	}
}
