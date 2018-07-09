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
		Map<String, String> configuration = this.getConfiguration();

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
