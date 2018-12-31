package model;

import java.util.Map;

public class Matchup {
	
	public Matchup(Team homeTeam, Team awayTeam, Map<Player, Double> scoringStats, Map<Player, Double> foulStats) {
		super();
		this.homeTeam = homeTeam;
		this.awayTeam = awayTeam;
		this.homeTeamStats = scoringStats;
		this.awayTeamStats = foulStats;
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
	public Map<Player, Double> getScoringStats() {
		return homeTeamStats;
	}
	public void setScoringStats(Map<Player, Double> scoringStats) {
		this.homeTeamStats = scoringStats;
	}
	public Map<Player, Double> getFoulStats() {
		return awayTeamStats;
	}
	public void setFoulStats(Map<Player, Double> foulStats) {
		this.awayTeamStats = foulStats;
	}
	private Team homeTeam;
	private Team awayTeam;
	private Map<Player,Double> homeTeamStats;
	private Map<Player, Double> awayTeamStats;

}
