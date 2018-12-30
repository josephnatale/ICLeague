package parser;

import java.io.FileNotFoundException;
import java.io.IOException;

import model.Matchup;
import model.WeekMatchups;
/**
 * generic interface which defines how parsers should behave, given the unknows of the format of the data
 * the only method we will define here is 'getMatchups'
 * @author josephnatale
 *
 */
public interface Parser {

	
	/**
	 * interface method to return the matchups for a given week.  Its possible the format of
	 * the data will change making this obsolete at some point
	 * @return
	 */
	public WeekMatchups getMatchups();
	
	/**
	 * initializes the parser
	 */
	public void init() throws FileNotFoundException, IOException;
	
	
}
