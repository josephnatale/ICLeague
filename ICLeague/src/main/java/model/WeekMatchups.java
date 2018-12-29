package model;

import java.util.List;

public class WeekMatchups {

	private List<Matchup> matchups;

	public WeekMatchups(List<Matchup> matchups) {
		super();
		this.matchups = matchups;
	}

	public List<Matchup> getMatchups() {
		return matchups;
	}

	public void setMatchups(List<Matchup> matchups) {
		this.matchups = matchups;
		
	}
}
