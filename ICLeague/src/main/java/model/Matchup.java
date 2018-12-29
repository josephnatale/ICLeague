package model;

import java.util.Map;

public class Matchup {
	
	public Matchup(Team homeTeam, Team awayTeam, Map<Player, Integer> scoringStats, Map<Player, Integer> foulStats) {
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
	public Map<Player, Integer> getScoringStats() {
		return scoringStats;
	}
	public void setScoringStats(Map<Player, Integer> scoringStats) {
		this.scoringStats = scoringStats;
	}
	public Map<Player, Integer> getFoulStats() {
		return foulStats;
	}
	public void setFoulStats(Map<Player, Integer> foulStats) {
		this.foulStats = foulStats;
	}
	private Team homeTeam;
	private Team awayTeam;
	private Map<Player,Integer> scoringStats;
	private Map<Player, Integer> foulStats;

}
