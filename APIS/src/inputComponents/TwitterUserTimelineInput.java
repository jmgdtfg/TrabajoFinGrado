package inputComponents;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import document.Document;
import document.TweetDocument;
import twitter.TwitterManager;
import twitter4j.Status;
import twitter4j.TwitterException;

public class TwitterUserTimelineInput implements InputComponent{

	@Override
	public List<Document> execute(Map<String, String> configuration) {

		TwitterManager tm = new TwitterManager();
		List<Document> listDocument = new ArrayList<Document>();
		try {
			
			for (Status tweet:tm.getUserTimeline(configuration.get("user"))){
				TweetDocument document = new TweetDocument();
				document.setRawData(tweet);
				listDocument.add(document); //La información del document debe ser de tipo TweetDocumetn
			}
			
			
		} catch (TwitterException e) {
			e.printStackTrace();
		}
		return listDocument;
	}

}
