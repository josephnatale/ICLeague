package parser.xlsParser;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.junit.Test;

import parser.xlsParser.XlsParser;

public class XlsParserTest {

	@Test(expected=IllegalArgumentException.class)
	public void testFileNameIsNull() throws FileNotFoundException, IOException {
		
		new XlsParser().parseExcellDoc(null, "someString");
		fail("should throw exception");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testSheetNameIsNull() throws FileNotFoundException, IOException {
		
		new XlsParser().parseExcellDoc("someString", null);
		fail("should throw exception");
	}
	
	@Test
	public void testSheetDoesNotExist() {
		try {
			HSSFSheet sheet = new XlsParser().parseExcellDoc("2017ICLeagueChampionship.xls", "sheetName");
			assertNull(sheet);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			fail();
		} catch (IOException e) {
			e.printStackTrace();
			fail();
		}
	}
	
	@Test(expected=FileNotFoundException.class)
	public void testFileDoesNotExist() throws FileNotFoundException, IOException {
		
		HSSFSheet sheet = new XlsParser().parseExcellDoc("badfilename.xls", "sheetName");

	}
	
	public void testSheetFound() throws FileNotFoundException, IOException {
		
		HSSFSheet sheet = new XlsParser().parseExcellDoc("2017ICLeagueChampionship.xls", "Week 1");
		assertNotNull(sheet);

	}

}
