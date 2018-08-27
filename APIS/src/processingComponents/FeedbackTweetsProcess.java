package processingComponents;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import document.Document;
import document.FeedBackDocument;
import document.TweetDocument;
import twitter4j.Status;

public class FeedbackTweetsProcess implements ProcessComponent{
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
	public List<Document> execute(List<Document> data, Map<String, String> configuration) {

		List<Document> listDocument = new ArrayList<Document>();
		
		Map <String,Integer> feedback = new HashMap<String,Integer>();
		int mentions = 0;
		int favorites = 0;
		int retweets = 0;
		
		//Para tratar la información se almacenará en una lista de tweets
		List<Status> tweetList = new ArrayList<Status>();
		for (Document document : data){
			Status tweet = (Status) document.getRawData();
			tweetList.add(tweet);
		}
		
		for (Status s : tweetList){
			favorites = favorites + s.getFavoriteCount();
			retweets = retweets + s.getRetweetCount();
			mentions = mentions + s.getUserMentionEntities().length;
		}
		feedback.put("favorites", favorites);
		feedback.put("retweets", retweets);
		feedback.put("mentions", mentions);
		FeedBackDocument document = new FeedBackDocument();
		document.setRawData(feedback);
		listDocument.add(document);
		return listDocument;

	}

	@Override
	public boolean isCompatibleWith(Document document) {
		if (document instanceof TweetDocument) {
			return true;
		}

		return false;
	}

}