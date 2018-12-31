package model;

import java.util.Map;

public class Matchup {
	
	public Matchup(Team homeTeam, Team awayTeam, Map<Player, Double> scoringStats, Map<Player, Double> foulStats) {
		super();
		this.homeTeam = homeTeam;
		this.awayTeam = awayTeam;
		this.scoringStats = scoringStats;
		this.foulStats = foulStats;
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
		return scoringStats;
	}
	public void setScoringStats(Map<Player, Double> scoringStats) {
		this.scoringStats = scoringStats;
	}
	public Map<Player, Double> getFoulStats() {
		return foulStats;
	}
	public void setFoulStats(Map<Player, Double> foulStats) {
		this.foulStats = foulStats;
	}
	private Team homeTeam;
	private Team awayTeam;
	private Map<Player,Double> scoringStats;
	private Map<Player, Double> foulStats;

}
