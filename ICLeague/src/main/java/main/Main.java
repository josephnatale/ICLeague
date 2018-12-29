package main;

import parser.Parser;
import parser.ParserFactory;

public class Main {
	
	private static String fileName = "2017ICLeagueChampionship.xls";

	public static void main(String[] args) {
		
		Parser parser = ParserFactory.getParser(fileName);
		//left off here, we need to get the sheet and start processing

	}

}
