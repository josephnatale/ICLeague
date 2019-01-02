package parser.xlsParser;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import model.Matchup;
import model.Player;
import model.Team;
import parser.Parser;

/**
 * this parser assumes a number of things
 *  1) row numbers will be consistent, we use them as a key to where the data is in the sheet
 *  2) the format will be consistent between sheets
 *  
 * @author josephnatale
 *
 */
public class XlsParser implements Parser{
	
	private static int GAME_1_ROW_NUMBER = 1;
	private static int GAME_2_ROW_NUMBER = 13;
	private static int GAME_3_ROW_NUMBER = 25;
	private static int GAME_4_ROW_NUMBER = 37;
	private static int GAME_5_ROW_NUMBER = 49;
	
	private static int HOME_PLAYER_NAME_CELL_NUMBER = 0;
	private static int HOME_POINTS_SCORED_CELL_NUMBER = 1;
	private static int AWAY_PLAYER_NAME_CELL_NUMBER = 4;
	private static int AWAY_POINTS_SCORED_CELL_NUMBER = 5;
	
	private static int HOME_TEAM_NAME_CELL_NUMBER = 0;
	private static int AWAY_TEAM_NAME_CELL_NUMBER = 4;
	
	enum TEAM{
		HOME,
		AWAY
	}
	
	private Integer[] gamesRows = { GAME_1_ROW_NUMBER, GAME_2_ROW_NUMBER, GAME_3_ROW_NUMBER, GAME_4_ROW_NUMBER, GAME_5_ROW_NUMBER };
	
	private String fileName;
	POIFSFileSystem fs;
	HSSFWorkbook wb;

	public XlsParser(String fileName) {
		super();
		this.fileName = fileName;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	@Override
	public void init() throws FileNotFoundException, IOException {
		fs = new POIFSFileSystem(new FileInputStream(fileName));
		wb = new HSSFWorkbook(fs);
	}

	/**
	 * implementation of getMatchups(), will parse the xls creating objects in
	 * the 'model' package
	 */
	@Override
	public Matchup[] getMatchups() {
		
		return null;
	}
	
	/**
	 * parses through sheet between start/end rows and returns a map of scoring stats for home team players
	 * @param sheet
	 * @param startRow
	 * @param endRow
	 */
	protected Map<Player,Double> parsePlayerScores(XlsParser.TEAM team,HSSFSheet sheet, int startRow, int endRow) {
		
		int playerIndex;
		int scoreIndex;
		if(XlsParser.TEAM.HOME.equals(team)) {
			playerIndex = HOME_PLAYER_NAME_CELL_NUMBER;
			scoreIndex = HOME_POINTS_SCORED_CELL_NUMBER;
		}else if (XlsParser.TEAM.AWAY.equals(team)) {
			playerIndex = AWAY_PLAYER_NAME_CELL_NUMBER;
			scoreIndex = AWAY_POINTS_SCORED_CELL_NUMBER;
		}else {
			throw new IllegalArgumentException("valid TEAM not supplied");
		}
		
		Map<Player,Double> scoreMap = new HashMap<Player, Double>();
		for(int i = startRow; i <= endRow; i++) {
			HSSFRow playerRow = sheet.getRow(i);
			HSSFCell playerCell = playerRow.getCell(playerIndex);
			/*
			 * continue of the playercell or the player name is not found
			 */
			if(playerCell == null) {
				continue;
			}
			String playerName = playerCell.getStringCellValue();
			if(playerName == null || playerName.length() == 0) {
				continue;
			}
			HSSFCell scoreCell = playerRow.getCell(scoreIndex);
			int cellType = scoreCell.getCellType();
			if(cellType == HSSFCell.CELL_TYPE_NUMERIC) {
				double playerScoreDouble = scoreCell.getNumericCellValue();
				scoreMap.put(new Player(playerName), new Double(playerScoreDouble));
			}else if(cellType == HSSFCell.CELL_TYPE_STRING) {
				//john uses an 'x' to represent when a player didn't play, i'll use a -1
				scoreMap.put(new Player(playerName), new Double(-1));
			}
			
		}
		
		return scoreMap;
	}
	
	/**
	 * parses the matchup creating teams, players and points and returns a matchup object
	 * @param matchupRow - row in xls where matchup starts
	 * @param sheet
	 * @return
	 */
	protected Matchup parseSingleMatchup(int matchupRowStart, int matchupRowEnd, HSSFSheet sheet) {
		
		if(sheet == null) {
			throw new IllegalArgumentException("sheet is null");
		}
		
		HSSFRow teamsRow = sheet.getRow(matchupRowStart);
		HSSFCell homeTeamCell = teamsRow.getCell(HOME_TEAM_NAME_CELL_NUMBER);
		String homeTeamName = homeTeamCell.getStringCellValue();
		/*
		 * here we need to add 1 to the start and subtract 1 from the end so get the rows where the players are
		 */
		Map<Player,Double> homeTeamStatsMap = parsePlayerScores(TEAM.HOME,sheet,matchupRowStart+1,matchupRowEnd-1 );
		
		
		Player[] homePlayers = new Player[homeTeamStatsMap.keySet().toArray().length];
		int i = 0;
		for(Player p: homeTeamStatsMap.keySet()) {
			homePlayers[i] = p;
			i++;
		}
		Team homeTeam = new Team(homePlayers,homeTeamName);
		
		HSSFCell awayTeamCell = teamsRow.getCell(AWAY_TEAM_NAME_CELL_NUMBER);
		String awayTeamName = awayTeamCell.getStringCellValue();
		//same as above
		Map<Player,Double> awayTeamStatsMap = parsePlayerScores(TEAM.AWAY,sheet,matchupRowStart+1, matchupRowEnd-1 );
		Player[] awayPlayers = new Player[awayTeamStatsMap.keySet().toArray().length];
		int j = 0;
		for(Player p: homeTeamStatsMap.keySet()) {
			homePlayers[j] = p;
			j++;
		}
		Team awayTeam = new Team(awayPlayers,awayTeamName);
		
		return new Matchup(homeTeam, awayTeam, homeTeamStatsMap, awayTeamStatsMap);
	
	}
	
	/**
	 * goes through given sheet and parses all matchups contained within the sheet into 'model' objects
	 * @param sheet
	 * @return
	 */
	protected Matchup[] parseWeeklyMatchups(HSSFSheet sheet) {
		//TODO: need to handle start/end of matchups more gracefully (may require refactoring parseSingleMatchup() method
		ArrayList<Matchup> matchups = new ArrayList<Matchup>();
		for(Integer i: gamesRows) {
			
		}
		return null;
		
	}
	
	

}
