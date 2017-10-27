package game;

import database.IQuestion;
import database.QuestionProvider;
import exception.DuplicatePlayerException;
import exception.NullPlayerException;
import exception.NullTeamException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Ryan on 10/10/2017.
 */
public class GameService {

    private static int MIN_REQUIRED_PLAYERS = 1;
    private Team teamOne;
    private Team teamTwo;
    private HashMap<Team, List<IQuestion>> questions;
    private Game game;
    private static GameService service;
    private boolean activeGame;

    /**
     * GameService factory method
     * @return sole instance of the GameService
     */
    protected static GameService getInstance() {
        if (service == null)
            service = new GameService();
        return service;
    }

    /**
     * Sets up the GameService with two teams
     * @param teamOne
     * @param teamTwo
     * @throws NullTeamException if either @param team is null
     */
    public void setUp(Team teamOne, Team teamTwo) throws NullTeamException{
    	
        if (teamOne == null || teamTwo == null) {
            activeGame = false;
            throw new NullTeamException("Team does not exist!");
        }
        activeGame = true;
        this.teamOne = teamOne;
        this.teamTwo = teamTwo;
    }

    /**
     * Method checks if the game, teams, and players have been set up
     * @return activeGame boolean whether setup has been successful.
     */
    protected boolean checkSetUp() {
 
        return !(teamOne.getMembers().size() < MIN_REQUIRED_PLAYERS || teamTwo.getMembers().size() < MIN_REQUIRED_PLAYERS) && activeGame;
    }

    /**
     * Method attempts to add a player to Team One
     * @param player is a new player to be added to this team
     * @throws DuplicatePlayerException if player already exists on the team
     * @throws NullPlayerException if @param player is null
     * @throws NullTeamException if team one hasn't been created yet
     */
    protected void addPlayerTeamOne(Player player) throws DuplicatePlayerException, NullPlayerException, NullTeamException{
        if (teamOne == null)
            throw new NullTeamException("Team does not exist!");
        teamOne.addMember(player);
    }

    /**
     * Method attempts to add a player to Team Two
     * @param player is a new player to be added to this team
     * @throws DuplicatePlayerException if player already exists on the team
     * @throws NullPlayerException if @param player is null
     * @throws NullTeamException if team one hasn't been created yet
     */
    protected void addPlayerTeamTwo(Player player) throws DuplicatePlayerException, NullPlayerException, NullTeamException {
        if (teamTwo == null)
            throw new NullTeamException("Team does not exist!");
        teamTwo.addMember(player);
    }

    /**
     *
     * @return List of players on Team One
     */
    public List getTeamOnePlayers() {
        return teamOne.getMembers();
    }

    /**
     *
     * @return List of players on Team Two
     */
    public List getTeamTwoPlayers() {
        return teamTwo.getMembers();
    }

    // Method not done yet
    public IQuestion getQuestion(Team team) {
        return null;
    }

    /**
     * Resets the service in preparation of a new game
     */
    public void tearDown(){
        teamOne = null;
        teamTwo = null;
        game = null;
        activeGame = false;
    }
}
