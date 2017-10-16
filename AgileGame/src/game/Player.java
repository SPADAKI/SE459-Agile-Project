package game;

/**
 * Created by Ryan on 10/10/2017.
 */
public class Player {

    private String name;
    private int score;

    public Player(String name) {
        this.name = name;
        score = 0;
    }

    public String getName() {
        return name;
    }

    public void addPoints(int points) {
        score += points;
    }

    public int getScore() {
        return score;
    }
}
