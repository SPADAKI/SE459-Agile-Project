package game;

import java.util.List;

/**
 * Created by Ryan on 10/10/2017.
 */
public class GameService {

    private Team teamOne;
    private Team teamTwo;

    public List getTeamOnePlayers() {
        return teamOne.getMembers();
    }

    public List getTeamTwoPlayers() {
        return teamTwo.getMembers();
    }

    public void addPlayerTeamOne(Player player) {
        teamTwo.addMember(player);
    }

    public void addPlayerTeamTwo(Player player) {
        teamTwo.addMember(player);
    }

}
