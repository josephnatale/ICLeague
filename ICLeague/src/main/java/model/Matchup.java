package model;

import java.util.Map;

import com.google.gson.Gson;

public class Matchup implements Splunkable{
	
	public Matchup(Team homeTeam, Team awayTeam, Map<String, Double> homeStats, Map<String, Double> awayStats) {
		this.homeTeam = homeTeam;
		this.awayTeam = awayTeam;
		this.homeTeamStats = homeStats;
		this.awayTeamStats = awayStats;
	}
	public Team getHomeTeam() {
		return homeTeam;
	}
	public void setHomeTeam(Team homeTeam) {
		this.homeTeam = homeTeam;
	}
	public Team getAwayTeam() {
		return awayTeam;
	}
	public void setAwayTeam(Team awayTeam) {
		this.awayTeam = awayTeam;
	}
	public Map<String, Double> getScoringStats() {
		return homeTeamStats;
	}
	public void setScoringStats(Map<String, Double> scoringStats) {
		this.homeTeamStats = scoringStats;
	}
	public Map<String, Double> getAwayTeamStats() {
		return awayTeamStats;
	}
	public void setAwayTeamStats(Map<String, Double> foulStats) {
		this.awayTeamStats = foulStats;
	}
	private Team homeTeam;
	private Team awayTeam;
	private Map<String,Double> homeTeamStats;
	private Map<String, Double> awayTeamStats;
	
	@Override
	public String toJsonEvent() {
		/*
		 * TODO: abstract the Gson library out with an interface, thoguht I needed this when creating custom
		 * serializers...not sure there's value anymore.
		 */
		Gson gson = new Gson();
		String jsonEvent = gson.toJson(this);
		return jsonEvent;
	}

}
