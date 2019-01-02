package model;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class MatchupTest {

	@Test
	public void testToJsonEvent() {
		Player joe = new Player("Joe Natale");
		Player kel = new Player("Kelly Anderson");
		Player tim = new Player("Big T");
		Player sak = new Player("Mr Sak");
		Player[] homePlayers = { joe, kel };
		Team homeTeam = new Team(homePlayers,"Red");
		
		Player[] awayPlayers = { tim, sak };
		Team awayTeam = new Team(awayPlayers, "Blue");
		
		Map<String,Double> homeStats = new HashMap<String,Double>();
		homeStats.put(joe.getName(),10.0);
		homeStats.put(kel.getName(), 9.0);
		Map<String,Double> awayStats = new HashMap<String,Double>();
		awayStats.put(tim.getName(),8.0);
		awayStats.put(sak.getName(), 7.0);
		
		Matchup matchup = new Matchup(homeTeam, awayTeam, homeStats, awayStats);
		
		String event = matchup.toJsonEvent();
		System.out.println(event);
		assertNotNull(event);
	}

}
