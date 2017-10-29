package game;

import java.util.List;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class TeamLayout extends Pane {
	private Team team1, team2;
	private Text txtTeamOneScore;
	private Text txtTeamTwoScore;
	private Rectangle highlight;

	public TeamLayout(Team t1, Team t2) {
		super();
		team1 = t1;
		team2 = t2;
		final int width = 800, height = 200, paddingH = 20, paddingV = 18;
		// Team one, left
		Pane leftPane = new Pane();

		Text txtTeamOne = new Text(t1.getTeamName()+" : ");
	    txtTeamOne.setStyle("-fx-font: bold 14 arial;");
	    txtTeamOne.setLayoutX(paddingH);
	    txtTeamOne.setLayoutY(paddingV);
	    leftPane.getChildren().add(txtTeamOne);

	    txtTeamOneScore = new Text(""+t1.getScore());
	    txtTeamOneScore.setStyle("-fx-font: bold 14 arial;");
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
	    	leftPane.getChildren().add(label);
	    }

	    // Team two, right
		Pane rightPane = new Pane();
		rightPane.setLayoutX(width/2);

		Text txtTeamTwo = new Text(t2.getTeamName()+" : ");
		txtTeamTwo.setStyle("-fx-font: bold 14 arial;");
		txtTeamTwo.setLayoutX(paddingH);
		txtTeamTwo.setLayoutY(paddingV);
		rightPane.getChildren().add(txtTeamTwo);

	    txtTeamTwoScore = new Text(""+t2.getScore());
	    txtTeamTwoScore.setStyle("-fx-font: bold 14 arial;");
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
	    	rightPane.getChildren().add(label);
	    }

	    highlight = new Rectangle();
	    highlight.setWidth(40);
	    highlight.setHeight(40);
	    highlight.setFill(Color.BLUE);
	    highlight.setLayoutX(320);
	    highlight.setLayoutY(paddingH);

        getChildren().addAll(leftPane, rightPane, highlight);
	}

	public void refreshData(int curTeamIdx) {
		txtTeamOneScore.setText(""+team1.getScore());
		txtTeamTwoScore.setText(""+team2.getScore());

		if(curTeamIdx == 0) highlight.setLayoutX(320);
		else highlight.setLayoutX(720);
	}
}
