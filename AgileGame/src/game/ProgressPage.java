package game;

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

<<<<<<< HEAD
import com.sun.javafx.geom.Rectangle;
import com.sun.prism.paint.Color;

public class ProgressPage extends Application {

    private Stage window;
    private Scene main;
    private int width = 600;
    private int height = 600;
    
    public void ProgressPage() {
    	
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        window = primaryStage;
        primaryStage.setTitle("Agile Game Time - Progress Page");

        // Grid setup
=======
		// Grid setup
>>>>>>> 2e84d08b9c96e116eb4f05f6ba867741dcd747ad
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 30));//top, right, bottom, and left padding
        // Set vertical and horizontal gap
        grid.setVgap(10);//between element
        grid.setHgap(10);
        grid.getColumnConstraints().add(new ColumnConstraints(150));
        grid.getColumnConstraints().add(new ColumnConstraints(300));
        grid.getColumnConstraints().add(new ColumnConstraints(150));


        //question title in column 1, row 0
        Label pageTitle = new Label("QUESTION:");
        pageTitle.setStyle("-fx-font: bold 18 arial;");

        GridPane.setConstraints(pageTitle, 1, 0);

        //Sample "hard-code" question in column 1, row 1
        Text question = new Text("Which of the following is example of the kinds of things that can wrong during a typical software development effort?");
        //set location to column=0 row=1, columnSpan, rowSpan
        GridPane.setConstraints(question, 0, 1, 3, 1);
        question.setWrappingWidth(550);
        question.setStyle("-fx-font: bold 18 arial;");


        Button answerA = new Button("A. Another name for the Waterfall model.");
        Button answerB = new Button("B. The closer to the end of the project we are, the easier it is to project exactly when we'll be able to deliver the completed software.");
        Button answerC = new Button("C. We never know exactly what we're building until later on in a project.");
        Button next = new Button("Next");
        GridPane.setConstraints(next, 2, 5);
        GridPane.setConstraints(answerA, 0, 2, 3, 1);
        answerA.wrapTextProperty().setValue(true);
        answerA.setMaxWidth(500);
        answerA.setAlignment(Pos.BASELINE_LEFT);
        GridPane.setConstraints(answerB, 0, 3, 3, 1);
        answerB.wrapTextProperty().setValue(true);
        answerB.setMaxWidth(500);
        answerB.setAlignment(Pos.BASELINE_LEFT);
        GridPane.setConstraints(answerC, 0, 4, 3, 1);
        answerC.wrapTextProperty().setValue(true);
        answerC.setMaxWidth(500);
        answerC.setAlignment(Pos.BASELINE_LEFT);

        //statusArea: show users and scores
        Text statusText = new Text("Status board:");
        GridPane.setConstraints(statusText, 0, 6, 3, 1);

        HBox statusArea = new HBox();
        statusArea.setPadding(new Insets(15, 5, 15, 12));
        statusArea.setSpacing(10);
        statusArea.setMaxWidth(500);
        statusArea.setStyle("-fx-background-color: #99b1c9;");
        GridPane.setConstraints(statusArea, 0, 7, 3, 30);



        // Add elements to the grid pane
        grid.getChildren().addAll(pageTitle, question, answerA, answerB, answerC, next, statusText,statusArea);

<<<<<<< HEAD
        main = new Scene(grid, width, height);

        primaryStage.setScene(main);
        primaryStage.show();
        
    }
    
    
=======
        scene = new Scene(grid, width, height);
	}
>>>>>>> 2e84d08b9c96e116eb4f05f6ba867741dcd747ad

//    public HBox addHBox() {
//
//
//        Button buttonCurrent = new Button("Current");
//        buttonCurrent.setPrefSize(100, 20);
//
//        Button buttonProjected = new Button("Projected");
//        buttonProjected.setPrefSize(100, 20);
//        hbox.getChildren().addAll(buttonCurrent, buttonProjected);
//
//        return hbox;
//    }
}
