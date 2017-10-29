package game;

import javafx.scene.Group;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class CardLayout extends Pane {

	public CardLayout() {
		super();

		Rectangle r = new Rectangle();
	    r.setWidth(800);
	    r.setHeight(400);
	    r.setFill(Color.RED);
	    getChildren().add(r);
	}
}
