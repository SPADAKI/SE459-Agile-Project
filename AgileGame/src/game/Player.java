package game;

/**
 * Created by Ryan on 10/10/2017.
 */
public class Player {

    private String name;
    private int score;

    /**
     * Default constructor. Initializes player name
     * and sets score to 0.
     * @param name
     */
    public Player(String name) {
        this.name = name;
        score = 0;
    }

    /**
     *
     * @return player's name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param points will be added to the current score
     */
    public void addPoints(int points) {
        score += points;
    }

    /**
     *
     * @return score
     */
    public int getScore() {
        return score;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Player))
            return false;
        Player p = (Player) o;

        return (this.getName().equals(p.getName())) && (this.getScore() == p.getScore());
    }
}
