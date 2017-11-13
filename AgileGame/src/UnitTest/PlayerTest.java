package UnitTest;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import game.Player;

public class PlayerTest {
	
	Player p1 = new Player("p1");

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetName() {
		Player p1 = new Player("p1");
		assertEquals(p1.getName(),"p1");
		
	}

	@Test
	public void testAddPoints() {
		p1.addPoints(2);
		assertEquals(p1.getScore(),2);
		
	}

	@Test
	public void testGetScore() {
		p1.getScore();
		assertEquals(p1.getScore(),0);
	}

}
