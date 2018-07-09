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
	private Document document_ = new TrendDocument();
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
