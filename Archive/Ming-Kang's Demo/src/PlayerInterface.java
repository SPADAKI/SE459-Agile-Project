import javax.swing.*;
import java.awt.*;


public class PlayerInterface {
	
	private JFrame f;
	private JPanel p,team1,team2;
	private JButton t1_newplayer,t2_newplayer;
	private JButton startb;
	
	
	public PlayerInterface()
	{
		makeinterface();
	}
	
	public void makeinterface() {
		f = new JFrame("game setting");
		f.setVisible(true);
		f.setSize(1000, 1200);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		p = new JPanel();
		team1 = new JPanel();
		team2 = new JPanel();
		
		t1_newplayer = new JButton("new player"); 
		t1_newplayer.setSize(250, 100);
		
		t2_newplayer = new JButton("new player");
		t2_newplayer.setSize(250, 100);
		
		startb = new JButton("game start");
		
		
		
		team1.add(t1_newplayer);
		team2.add(t2_newplayer);
		p.add(startb);
		f.add(p);
		f.add(team1,BorderLayout.WEST);
		f.add(team2,BorderLayout.EAST);
	}

	public static void main(String[] args) {
		
		//new PlayerInterface();

	}

}
