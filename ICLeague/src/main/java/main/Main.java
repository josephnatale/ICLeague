package main;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import model.Matchup;
import parser.Parser;
import parser.ParserFactory;

public class Main {
	
	private static String fileName = "2017ICLeagueChampionship.xls";

	public static void main(String[] args) throws FileNotFoundException, IOException {
		
		Parser parser = ParserFactory.getParser(fileName);
		//TODO: MVP is only parsing sheet for "Week 1", need to implement for all weeks
		
		List<Matchup> matchups = parser.getMatchups();
		
		
		//TODO: pull the below out into its own framework?
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response = restTemplate.getForEntity("URL", String.class);
		
		if(HttpStatus.OK.equals(response.getStatusCode())) {
			System.out.println("request sent to splunk");
		}else {
			System.out.println("failed to publish event..");
		}
		
		

	}

}
