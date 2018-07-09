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
	private Document document_ = new TweetDocument();
	private Map<String, String> configuration_;
	@Override
	public Map<String, String> getConfiguration() {
		return configuration_;
	}

	@Override
	public void setConfiguration(Map<String, String> configuration) {
		configuration_ = configuration;
	}
	
	@Override
	public Document getDocument() {
		return document_;
	}

	@Override
	public List<Document> execute() {
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