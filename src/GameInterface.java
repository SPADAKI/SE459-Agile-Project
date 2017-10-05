import javax.swing.*;
import java.awt.*;


public class GameInterface {
	

	private JFrame gamewindow;
	
	public GameInterface(){
		// execute when clicking the startgame button on player interface
		makeinterface();
		
	}
	
	public void makeinterface() {
		gamewindow = new JFrame("game playing");
		gamewindow.setVisible(true);
		gamewindow.setSize(1000, 1200);
		gamewindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	

}
