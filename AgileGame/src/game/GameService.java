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
    private static int NUMBER_OF_ROUNDS = 10;
    private Team teamOne;
    private Team teamTwo;
    private Game game;
    private static GameService service;
    private boolean activeGame;

    private int currentTeamIdx;	//0: TeamOne, 1: TeamTwo
    private ArrayList<IQuestion> questions;
    private int questionIdx;

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
        this.teamOne = teamOne;
        this.teamTwo = teamTwo;
    }

    /**
     * Method checks if the game, teams, and players have been set up
     * @return activeGame boolean whether setup has been successful.
     */
    protected boolean checkSetUp() throws NullPlayerException, NullTeamException {
        return !(teamOne.getMembers().size() < MIN_REQUIRED_PLAYERS || teamTwo.getMembers().size() < MIN_REQUIRED_PLAYERS) && activeGame;
    }

    /**
     * Get Team One Name constructor
     * @return string of team two name
     */
    protected String getTeamOneName() {
        return teamOne.getTeamName();
    }

    /**
     * Get Team Two name constructor
     * @return string of team two name
     */
    protected String getTeamTwoName() {
        return teamTwo.getTeamName();
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
     * Get team one.
     * @return teamOne
     */
    public Team getTeamOne() {
    	return teamOne;
    }

    /**
     * Get team one.
     * @return teamTwo
     */
    public Team getTeamTwo() {
    	return teamTwo;
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

    // Jason added functions
    public IQuestion startNewRound() {
    	teamOne.clearScore();
    	teamTwo.clearScore();
    	currentTeamIdx = 0;
        questions = QuestionProvider.getQuestions(2);
        questionIdx = 0;
        return questions.get(questionIdx++);
    }

    public int getCurrentTeamIdx() {
    	return currentTeamIdx;
    }

    public Team getCurrentTeam() {
    	if(currentTeamIdx == 0) return teamOne;
    	else return teamTwo;
    }

   public IQuestion nextTurn() {
	   currentTeamIdx = (++currentTeamIdx)%2;

	   if(questionIdx < questions.size()) return questions.get(questionIdx++);
	   else return null;
   }

   public void addPointToCurrentTeam(int point) {
	   if(currentTeamIdx == 0) teamOne.addPoints(point);
   		else  teamTwo.addPoints(point);
   }

    /**
     * Resets the service in preparation of a new game
     */
    public void tearDown(){
        teamOne = null;
        teamTwo = null;
        game = null;
        activeGame = false;
        questions = null;
    }
}
