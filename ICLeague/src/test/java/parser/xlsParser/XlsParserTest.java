package parser.xlsParser;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.junit.Test;

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
	
	

}
