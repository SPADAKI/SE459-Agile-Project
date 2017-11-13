 package UnitTest;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import exception.DuplicatePlayerException;
import exception.NullPlayerException;
import game.Player;
import game.Team;

public class TeamTest {
	Team t1 = new Team("test1");

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testTeam() {
		
	}

	@Test
	public void testSetTeamName() {
		t1.setTeamName("change");
		assertEquals(t1.getTeamName(),"change");
	}

	@Test
	public void testGetTeamName() {
		Team t2 = new Team("test2");
		assertEquals(t2.getTeamName(),"test2");
	}

	@Test
	public void testGetScore() {
		assertEquals(t1.getScore(),0);
	}

	@Test
	public void testClearScore() {
		t1.addPoints(5);
		t1.clearScore();
		assertEquals(t1.getScore(),0);
		
	}

	@Test
	public void testAddPoints() {
		t1.addPoints(5);
		assertEquals(t1.getScore(),5);
		
	}

	@Test
	public void testAddMember() throws DuplicatePlayerException, NullPlayerException {
		Player pp = new Player("pp");
		t1.addMember(pp);
	}

}
