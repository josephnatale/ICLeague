package parser.xlsParser;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
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
import model.WeekMatchups;
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
	public WeekMatchups getMatchups() {
		
		return null;
	}
	
	/**
	 * parses through sheet between start/end rows and returns a map of scoring stats for home team players
	 * @param sheet
	 * @param startRow
	 * @param endRow
	 */
	protected Map<Player,Integer> parseHomePlayerScores(HSSFSheet sheet, int startRow, int endRow) {
		
		Map<Player,Integer> scoreMap= new HashMap<Player, Integer>();
		for(int i = startRow; i > endRow; i++) {
			HSSFRow playerRow = sheet.getRow(i);
			HSSFCell playerCell = playerRow.getCell(HOME_PLAYER_NAME_CELL_NUMBER);
			String playerName = playerCell.getStringCellValue();
			HSSFCell scoreCell = playerRow.getCell(HOME_POINTS_SCORED_CELL_NUMBER);
			String playerScore = scoreCell.getStringCellValue();
			scoreMap.put(new Player(playerName), new Integer(Integer.valueOf(playerScore)));
		}
		
		return scoreMap;
	}
	
	
	
	

}
