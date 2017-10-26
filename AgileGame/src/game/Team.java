package game;

import exception.DuplicatePlayerException;
import exception.NullPlayerException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ryan on 10/10/2017.
 */
public class Team {

    private String name;
    private int score;
    private List<Player> members;

    public Team(String name) {
        this.name = name;
        this.score = 0;
        members = new ArrayList<>();
    }

    public String getName() { return name; }

    public int getScore() { return score; }

    public void addPoints(int points) { score += points; }

    public void addMember(Player player) throws DuplicatePlayerException, NullPlayerException {
        if (player == null)
            throw new NullPlayerException("Cannot add a null player!");
        if (members.contains(player))
            throw new DuplicatePlayerException("Player already exists!");
        members.add(player);
    }

    public List<Player> getMembers() { return members; }

}
