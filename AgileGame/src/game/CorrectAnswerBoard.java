package game;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class CorrectAnswerBoard {
	
	private JFrame rightans;
	private JPanel p;
	private JButton exit;
	
	
	
	public CorrectAnswerBoard()
	{
		makeinterface();
	}
	
	public void makeinterface() {
		rightans = new JFrame("you got right ans");
		rightans.setVisible(true);
		rightans.setSize(750, 200);
		rightans.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		p = new JPanel();
		
		JLabel correctmessage = new JLabel("you got the correct answer",JLabel.CENTER);
		
		exit = new JButton("exit"); 
		exit.setSize(250, 100);
		TeamHandler t1handler = new TeamHandler();
		exit.addMouseListener(t1handler);
		
		
		
		p.add(exit);
		rightans.add(p,BorderLayout.SOUTH);
		rightans.add(correctmessage,BorderLayout.CENTER);
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
			rightans.dispose();					
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
