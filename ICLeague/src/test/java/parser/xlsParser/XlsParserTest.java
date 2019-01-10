package parser.xlsParser;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.junit.Test;

import model.Matchup;
import model.Player;
import parser.Parser;
import parser.xlsParser.XlsParser;

public class XlsParserTest {

	@Test
	public void testInit() throws FileNotFoundException, IOException {
		
		Parser parser = new XlsParser("2017ICLeagueChampionship.xls");
		parser.init();
		assertTrue(true);
	
	}
	
	
	@Test(expected=FileNotFoundException.class)
	public void testInitFileNotFound() throws FileNotFoundException, IOException {

		Parser parser = new XlsParser("2017ICLeagueChampionship.xls_NOT_FOUND");
		parser.init();
		fail();
	}
	
	@Test
	public void returnValidHomePlayerStatsMap() throws FileNotFoundException, IOException {
		

		XlsParser parser = new XlsParser("2017ICLeagueChampionship.xls");
		parser.init();
		POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream("2017ICLeagueChampionship.xls"));
		HSSFWorkbook wb = new HSSFWorkbook(fs);
		//see the test data, rows 3-12 is where the players are
		Map<String,Double> map = parser.parsePlayerScores(XlsParser.TEAM.HOME,wb.getSheet("Week 1"), 2, 13);
		assertEquals(9,map.size());
		
	}
	
	
	@Test
	public void returnValidAwayPlayerStatsMap() throws FileNotFoundException, IOException {

		XlsParser parser = new XlsParser("2017ICLeagueChampionship.xls");
		parser.init();
		POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream("2017ICLeagueChampionship.xls"));
		HSSFWorkbook wb = new HSSFWorkbook(fs);
		//see the test data, rows 3-12 is where the players are
		Map<String,Double> map = parser.parsePlayerScores(XlsParser.TEAM.AWAY,wb.getSheet("Week 1"), 2, 13);
		assertEquals(9,map.size());
		
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testParseSingleMatchupSheetIsNull() throws FileNotFoundException, IOException {
		
		XlsParser parser = new XlsParser("2017ICLeagueChampionship.xls");
		parser.init();
		parser.parseSingleMatchup(0, 0, null);
		
		
	}
	
	@Test
	public void testParseSingleMatchupReturnGame1() throws FileNotFoundException, IOException {
		
		XlsParser parser = new XlsParser("2017ICLeagueChampionship.xls");
		parser.init();
		POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream("2017ICLeagueChampionship.xls"));
		HSSFWorkbook wb = new HSSFWorkbook(fs);
		
		Matchup matchup = parser.parseSingleMatchup(1, 13, wb.getSheet("Week 1"));
		assertEquals("Orange",matchup.getHomeTeam().getTeamName());
		assertEquals("Yellow",matchup.getAwayTeam().getTeamName());
		assertEquals(9,matchup.getHomeTeam().getPlayers().length);
		assertEquals(9,matchup.getAwayTeam().getPlayers().length);
		
		
	}
	
	@Test
	public void testParseSingleMatchupReturnGame2() throws FileNotFoundException, IOException {
		
		XlsParser parser = new XlsParser("2017ICLeagueChampionship.xls");
		parser.init();
		POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream("2017ICLeagueChampionship.xls"));
		HSSFWorkbook wb = new HSSFWorkbook(fs);
		
		Matchup matchup = parser.parseSingleMatchup(13, 25, wb.getSheet("Week 1"));
		assertEquals("Black",matchup.getHomeTeam().getTeamName());
		assertEquals("Gray",matchup.getAwayTeam().getTeamName());
		assertEquals(10,matchup.getHomeTeam().getPlayers().length);
		assertEquals(10,matchup.getAwayTeam().getPlayers().length);
		
		
	}
	
	@Test
	public void testParseSingleMatchupReturnGame4() throws FileNotFoundException, IOException {
		
		XlsParser parser = new XlsParser("2017ICLeagueChampionship.xls");
		parser.init();
		POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream("2017ICLeagueChampionship.xls"));
		HSSFWorkbook wb = new HSSFWorkbook(fs);
		
		Matchup matchup = parser.parseSingleMatchup(37, 49, wb.getSheet("Week 1"));
		assertEquals("Purple",matchup.getHomeTeam().getTeamName());
		assertEquals("Powder",matchup.getAwayTeam().getTeamName());
		assertEquals(10,matchup.getHomeTeam().getPlayers().length);
		assertEquals(9,matchup.getAwayTeam().getPlayers().length);
		
		
	}
	
	@Test
	public void testParseSingleMatchupReturnGameLast() throws FileNotFoundException, IOException {
		
		XlsParser parser = new XlsParser("2017ICLeagueChampionship.xls");
		parser.init();
		POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream("2017ICLeagueChampionship.xls"));
		HSSFWorkbook wb = new HSSFWorkbook(fs);
		
		Matchup matchup = parser.parseSingleMatchup(49, wb.getSheet("Week 1").getLastRowNum(), wb.getSheet("Week 1"));
		assertEquals("Green",matchup.getHomeTeam().getTeamName());
		assertEquals("Red",matchup.getAwayTeam().getTeamName());
		assertEquals(9,matchup.getHomeTeam().getPlayers().length);
		assertEquals(9,matchup.getAwayTeam().getPlayers().length);
		
		
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testParseWeeklyMatchupsSheetNamesIsNull() throws FileNotFoundException, IOException {
		XlsParser parser = new XlsParser("2017ICLeagueChampionship.xls");
		parser.init();
		List<Matchup> matchups = parser.getWeeklyMatchups(null);
	}
	
	@Test
	public void testParseWeeklyMatchupsParseSingleWeek() throws FileNotFoundException, IOException {
		XlsParser parser = new XlsParser("2017ICLeagueChampionship.xls");
		parser.init();
		List<Matchup> matchups = parser.getWeeklyMatchups("Week 1");
		assertEquals(5,matchups.size());
	}
	
	
}
