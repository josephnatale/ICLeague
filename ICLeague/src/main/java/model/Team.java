package model;

import java.util.List;

public class Team {
	
	private List<Player> ArrayList;
	private String teamName;
	public Team(List<Player> arrayList, String teamName) {
		ArrayList = arrayList;
		this.teamName = teamName;
	}
	public List<Player> getArrayList() {
		return ArrayList;
	}
	public void setArrayList(List<Player> arrayList) {
		ArrayList = arrayList;
	}
	public String getTeamName() {
		return teamName;
	}
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	

}
