package processingComponents;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import document.Document;
import document.TweetDocument;
import twitter4j.Status;

public class TopLikesProcess implements ProcessComponent {
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
		int top = Integer.valueOf(configuration.get("top")).intValue();
		//List<TweetDocument> topLikesTweets = new ArrayList<TweetDocument>();
		List<Document> listDocument = new ArrayList<Document>();
		if (data.size() <= top){
			return data;
		}
		//Proceso de ordenación de tweets con más likes
		List<Status> sortList = new ArrayList<Status>();
		for (Document document : data){
			Status tweet = (Status) document.getRawData();
			sortList.add(tweet);
		}
		sortList.sort(Comparator.comparing(Status::getFavoriteCount).reversed());
		
		//Se devuelven solo el número de resultados pedido en la configuración
		
		for (int i = 0; i < top ; i++){
			TweetDocument document = new TweetDocument();
			document.setRawData(sortList.get(i));
			listDocument.add(document);
		}

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
