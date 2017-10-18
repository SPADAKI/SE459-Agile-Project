package database;

import java.util.ArrayList;

public class Team {
	private String teamName = "";
	private ArrayList<Player> members = new ArrayList<Player>();

	public Team() {

	}

	public void setTeamName(String n) {
		teamName = n;
	}

	public String getTeamName() {
		return teamName;
	}

	public void addPlayer(String name) {
		Player p = new Player(name);
		members.add(p);
	}

	public void removePlayer(int idx) {
		members.remove(idx);
	}

	public void setPlayerName(int idx, String n) {
		Player p = members.get(idx);
		p.setName(n);
	}


	public ArrayList<Player> getPlayers() {
		return members;
	}
}
