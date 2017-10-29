package game;

import javafx.scene.Group;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class TeamLayout extends Pane {

	public TeamLayout() {
		super();

		GridPane pane = new GridPane();

	    Text txtTeamOne = new Text("Team One");
	    GridPane.setConstraints(txtTeamOne, 0, 1);
	    txtTeamOne.setStyle("-fx-font: bold 14 arial;");
	    txtTeamOne.setWrappingWidth(400);
        pane.getChildren().add(txtTeamOne);
	    Text txtTeamTwo = new Text("Team Two");
	    GridPane.setConstraints(txtTeamTwo, 1, 1);
	    txtTeamTwo.setStyle("-fx-font: bold 14 arial;");
	    txtTeamTwo.setWrappingWidth(400);
        pane.getChildren().add(txtTeamTwo);

        getChildren().add(pane);
	}
}
