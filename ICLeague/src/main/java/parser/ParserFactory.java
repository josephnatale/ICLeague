package parser;

import parser.xlsParser.XlsParser;

public class ParserFactory {

	public static Parser getParser(String fileToProcess) {
		if(fileToProcess == null || fileToProcess.length() == 0 ) {
			throw new IllegalArgumentException("fileToProcess is null");
		}
		
		if(fileToProcess.endsWith("xls")) {
			return new XlsParser();
		}else {
			throw new IllegalArgumentException("file type not supported");
		}
		
	}
	
}
