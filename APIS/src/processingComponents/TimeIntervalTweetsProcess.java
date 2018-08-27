package processingComponents;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import document.Document;
import document.TweetDocument;
import twitter4j.Status;

public class TimeIntervalTweetsProcess implements ProcessComponent{
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

		int intervalEnd = Integer.valueOf(configuration.get("intervalEnd")).intValue();
		int intervalStart = Integer.valueOf(configuration.get("intervalStart")).intValue();
		List<Document> listDocument = new ArrayList<Document>();


		//Comprobación de que los valores del intervalo sean válidos
		if (intervalEnd<=0 || intervalStart<=0)
			return null;
		if (intervalStart-intervalEnd <=0)
			return null;

		//Declaración de las fechas de inicio y fin del intervalo
		Calendar dateBefore = Calendar.getInstance();
		dateBefore.add(Calendar.DATE, -intervalEnd);
		Calendar dateAfter = Calendar.getInstance();
		dateAfter.add(Calendar.DATE, -intervalStart);

		//Para tratar la información se almacenará en una lista de tweets
		List<Status> tweetList = new ArrayList<Status>();
		for (Document document : data){
			Status tweet = (Status) document.getRawData();
			tweetList.add(tweet);
		}
		
		for (Status s:tweetList){
			if (s.getCreatedAt().before(dateBefore.getTime()) && s.getCreatedAt().after(dateAfter.getTime())){
				TweetDocument document = new TweetDocument();
				document.setRawData(s);	//Si el tweet está en el intervalo se añade a la lista
				listDocument.add(document);
			}
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
