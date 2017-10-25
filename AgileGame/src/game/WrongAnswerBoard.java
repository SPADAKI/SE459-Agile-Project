package game;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;


public class WrongAnswerBoard {
	private JFrame wrongans;
	private JPanel p;
	private JButton exit;
	private JLabel statusbar;
	
	
	public WrongAnswerBoard()
	{
		makeinterface();
	}
	
	public void makeinterface() {
		wrongans = new JFrame("you got wrong ans");
		wrongans.setVisible(true);
		wrongans.setSize(750, 200);
		wrongans.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		p = new JPanel();
		
		JLabel correctmessage = new JLabel("sorry, you got the wrong answer",JLabel.CENTER);
		
		exit = new JButton("exit"); 
		exit.setSize(250, 100);
		TeamHandler t1handler = new TeamHandler();
		exit.addMouseListener(t1handler);
		
		
		
		p.add(exit);
		wrongans.add(p,BorderLayout.SOUTH);
		wrongans.add(correctmessage,BorderLayout.CENTER);
	}
	
	private class TeamHandler implements MouseListener, MouseMotionListener{

		@Override
		public void mouseDragged(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			wrongans.dispose();					
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

}
}
