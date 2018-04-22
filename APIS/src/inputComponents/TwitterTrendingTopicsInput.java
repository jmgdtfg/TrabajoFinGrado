package inputComponents;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import document.Document;
import document.TrendDocument;
import twitter.TwitterManager;
import twitter4j.Trend;
import twitter4j.TwitterException;

public class TwitterTrendingTopicsInput implements InputComponent{
	
	@Override
	public List<Document> execute(Map<String, String> configuration) {
		TwitterManager tm = new TwitterManager();	
		List<Document> listDocument = new ArrayList<Document>();
		try {
			for (Trend data : tm.getTrendingTopics(configuration.get("country"))){	
				TrendDocument document = new TrendDocument();	
				document.setRawData(data);
				listDocument.add(document);
			}
		} catch (TwitterException e) {	
			e.printStackTrace();
		}
		return listDocument;
	}
}
