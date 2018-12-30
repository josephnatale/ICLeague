package main;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFSheet;

import parser.Parser;
import parser.ParserFactory;

public class Main {
	
	private static String fileName = "2017ICLeagueChampionship.xls";

	public static void main(String[] args) throws FileNotFoundException, IOException {
		
		Parser parser = ParserFactory.getParser(fileName);
		//left off here, we need to get the sheet and start processing
		
		
		

	}

}
