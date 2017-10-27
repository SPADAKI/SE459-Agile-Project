package game;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class CongratulationPage extends AGPage {

	public CongratulationPage(AgileGame app, int w, int h) {
		super(app, w, h);

		// Grid setup
	    GridPane grid = new GridPane();
	    grid.setPadding(new Insets(10, 10, 10, 10));
	    
	    grid.setVgap(10);
	    grid.setHgap(10);
	    
	    // Column setup
	    ColumnConstraints leftContraint = new ColumnConstraints();
	    leftContraint.setPercentWidth(15);
	    ColumnConstraints midConstraint = new ColumnConstraints();
	    midConstraint.setPercentWidth(70);
	    ColumnConstraints rightContraint = new ColumnConstraints();
	    rightContraint.setPercentWidth(15);

	    //title in column 1, row 0
	    Label pageTitle = new Label("CONGRATULATIONS!");
	    pageTitle.setStyle("-fx-font: bold 18 arial;");
	    GridPane.setHalignment(pageTitle, HPos.CENTER);
        GridPane.setConstraints(pageTitle, 1, 0);
        Text message = new Text("Your final score is: %");
        
        grid.getChildren().addAll(pageTitle, message);

        scene = new Scene(grid, w, h);
	}

	

	
}
