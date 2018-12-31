package model;

public class Team {
	
	private Player[] players;
	private String teamName;
	public Team(Player[] players, String teamName) {
		this.players = players;
		this.teamName = teamName;
	}
	public Player[] getPlayers() {
		return players;
	}
	public void setPlayers(Player[] players) {
		this.players = players;
	}
	public String getTeamName() {
		return teamName;
	}
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	

}
