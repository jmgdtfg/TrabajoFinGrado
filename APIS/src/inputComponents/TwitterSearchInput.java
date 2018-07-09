package inputComponents;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import document.Document;
import document.TweetDocument;
import twitter.TwitterManager;
import twitter4j.Status;
import twitter4j.TwitterException;

public class TwitterSearchInput implements InputComponent{
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

		int days = Integer.valueOf(configuration.get("days")).intValue();
		int results = Integer.valueOf(configuration.get("results")).intValue();

		TwitterManager tm = new TwitterManager();	
		List<Document> listDocument = new ArrayList<Document>();

		if (configuration.get("twitterSearchType").equals("hashtag")){

			try {
				for (Status data : tm.searchByHashtag(configuration.get("hashtag"),days,results)){	
					TweetDocument document = new TweetDocument();	
					document.setRawData(data);
					listDocument.add(document);
				}
			} catch (TwitterException e) {
				e.printStackTrace();
			}
		}
		else if(configuration.get("twitterSearchType").equals("word")){
			try {
				for (Status data : tm.searchByWord(configuration.get("word"),days,results)){	
					TweetDocument document = new TweetDocument();	
					document.setRawData(data);
					listDocument.add(document);
				}
			} catch (TwitterException e) {
				e.printStackTrace();
			}
		}
		return listDocument;
	}
}
