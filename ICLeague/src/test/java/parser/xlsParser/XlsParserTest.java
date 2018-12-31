package parser.xlsParser;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.junit.Test;

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
		Map<Player,Double> map = parser.parseHomePlayerScores(XlsParser.TEAM.HOME,wb.getSheet("Week 1"), 2, 13);
		assertEquals(9,map.size());
		
	}
	
	
	@Test
	public void returnValidAwayPlayerStatsMap() throws FileNotFoundException, IOException {
		

		XlsParser parser = new XlsParser("2017ICLeagueChampionship.xls");
		parser.init();
		POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream("2017ICLeagueChampionship.xls"));
		HSSFWorkbook wb = new HSSFWorkbook(fs);
		//see the test data, rows 3-12 is where the players are
		Map<Player,Double> map = parser.parseHomePlayerScores(XlsParser.TEAM.AWAY,wb.getSheet("Week 1"), 2, 13);
		assertEquals(9,map.size());
		
	}
}
