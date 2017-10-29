package game;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class TeamLayout extends Group {

	public TeamLayout() {
		super();

		Rectangle r = new Rectangle();
	    r.setWidth(800);
	    r.setHeight(200);
	    r.setFill(Color.YELLOW);
	    r.setY(400);
	    getChildren().add(r);
	}
}
