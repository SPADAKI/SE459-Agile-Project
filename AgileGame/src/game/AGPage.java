package game;

import javafx.scene.Scene;

public class AGPage {
	protected AgileGame application = null;
	protected Scene scene = null;
	protected int width;
	protected int height;

	public AGPage(AgileGame app, int w, int h) {
		application = app;
		width = w;
		height = h;
	}

	// Default Constructor Used for testing
	public AGPage(){}

	public Scene getScene() {
		return scene;
	}

	public void updateSize(int width, int height) {

	}
}
