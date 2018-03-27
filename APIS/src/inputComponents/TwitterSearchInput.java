package inputComponents;

import java.util.List;
import java.util.Map;

import twitter.TwitterManager;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.TwitterException;

public class TwitterSearchInput implements InputComponent{
	
	private int days_;		//D�as hasta la fecha actual v�lidos para la b�squeda
	private int results_;	//Limitaci�n de resultados de b�squeda
	
	//Constructor parametrizado que recibe los d�as y los resultados 
	public TwitterSearchInput(int days, int results){
		days_ = days;
		results_ = results;
	}
	
	//Devuelve una lista de tweets si la configuraci�n pasada por par�metro es correcta
	@Override
	public Object execute(Map<String, String> configuration) {
		
		TwitterManager tm = new TwitterManager();
		//Si el tipo de la configuraci�n es hashtag, buscara en funci�n de un hashtag
		if (configuration.get("twitterSearchType").equals("hashtag")){
			String hashtag = configuration.get("hashtag");
			try {
				QueryResult result = tm.searchByHashtag(hashtag, days_, results_);
				List<Status> tweets = result.getTweets();
				//Devuelve un List<Status>
				return tweets;
			} catch (TwitterException e) {
				// ***
				e.printStackTrace();
				return null;
			}
		}
		//Si el tipo de la configuraci�n es word, buscara en funci�n de una palabra
		else if (configuration.get("twitterSearchType").equals("word")){
			String word = configuration.get("word");
			try {
				QueryResult result = tm.searchByWord(word, days_, results_);
				List<Status> tweets = result.getTweets();
				//Devuelve un List<Status>
				return tweets;
				
			} catch (TwitterException e) {
				// ***
				e.printStackTrace();
				return null;
			}
		}
		else{
			return "twitterSearchType no es valido";
		}
	}
}
