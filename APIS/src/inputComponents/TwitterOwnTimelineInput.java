package inputComponents;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import document.Document;
import document.TweetDocument;
import twitter.TwitterManager;
import twitter4j.Status;
import twitter4j.TwitterException;

public class TwitterOwnTimelineInput implements InputComponent {

	@Override
	public List<Document> execute(Map<String, String> configuration) {
		TwitterManager tm = new TwitterManager();
		
		//List<TweetDocument> tweets = new ArrayList<TweetDocument>();
		List<Document> listDocument = new ArrayList<Document>();
		try {
			for (Status tweet : tm.getOwnTimeline()) {
				TweetDocument document = new TweetDocument();
				document.setRawData(tweet);
				listDocument.add(document);
			}
		} catch (TwitterException e) {
			e.printStackTrace();
		}
		
		return listDocument;
	}

}