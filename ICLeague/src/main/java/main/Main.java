package main;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.client.RestTemplateCustomizer;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import api.SplunkResponse;
import model.Matchup;
import parser.Parser;
import parser.ParserFactory;

public class Main {
	
	private static String fileName = "2017ICLeagueChampionship.xls";
	
	private static String TOKEN_VALUE = "18ad4907-3caf-4df0-9817-26127cf08988";

	public static void main(String[] args) throws FileNotFoundException, IOException, RestClientException, URISyntaxException {
		
		Parser parser = ParserFactory.getParser(fileName);
		parser.init();
		//TODO: MVP is only parsing sheet for "Week 1", need to implement for all weeks
		
		List<Matchup> matchups = parser.getMatchups();
		
		//TODO: clean this up and pull it out into its own component
		for(Matchup matchup: matchups) {
			HttpHeaders headers = new HttpHeaders();
			headers.set("Authorization", "Splunk B5A79AAD-D822-46CC-80D1-819F80D7BFB0");  
			HttpEntity<Matchup> entity = new HttpEntity<Matchup>(matchup,headers);
			//TODO: move URL to private static field
			SplunkResponse repsonse = new RestTemplate().postForObject("https://input-prd-p-96k6j8bg99sz.cloud.splunk.com:8088/services/collector/", entity, SplunkResponse.class);
			if(!repsonse.getText().equals("Success")){
				System.out.println("failed to write eventto splunk");
			}
			
		}

	}

}
