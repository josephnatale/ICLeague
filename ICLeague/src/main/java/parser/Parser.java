package parser;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFSheet;

public interface Parser {

	public HSSFSheet parseExcellDoc(String fileName, String sheetName) throws FileNotFoundException, IOException;
	
}
