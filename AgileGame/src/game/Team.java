package game;

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

    public boolean addMember(Player player) {
        if (player != null && !members.contains(player)) {
            members.add(player);
        return true;
        }
        return false;
    }

    public List<Player> getMembers() { return members; }

}
