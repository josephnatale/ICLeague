package parser.xlsParser;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import parser.Parser;

public class XlsParser implements Parser{

	/**
	 * returns an HSSFSheet given a fileName and a sheetName
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	@Override
	public HSSFSheet parseExcellDoc(String fileName, String sheetName) throws FileNotFoundException, IOException {
		
		if(fileName == null || fileName.length() == 0) {
			throw new IllegalArgumentException("fileName is null");
		}
		
		if(sheetName == null || sheetName.length() == 0 ) {
			throw new IllegalArgumentException("sheetName is null");
		}
		
		POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(fileName));
		HSSFWorkbook wb = new HSSFWorkbook(fs);
		HSSFSheet sheet = wb.getSheet(sheetName);
		return sheet;
	}

}
