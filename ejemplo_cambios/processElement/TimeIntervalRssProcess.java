package processingComponents;



import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

import document.Document;
import document.RssMapDocument;

@SuppressWarnings("unchecked")
public class TimeIntervalRssProcess implements ProcessComponent{
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
		if (intervalEnd <= 0 || intervalStart <= 0)
			return null;
		if (intervalStart - intervalEnd <= 0)
			return null;

		//Declaración de las fechas de inicio y fin del intervalo
		Calendar dateBefore = Calendar.getInstance();
		dateBefore.add(Calendar.DATE, -intervalEnd);
		Calendar dateAfter = Calendar.getInstance();
		dateAfter.add(Calendar.DATE, -intervalStart);
		
		//Para tratar la información se almacenará en una lista de Emails
		List<Map<String,String>> messagesList = new ArrayList<Map<String,String>>();
		for (Document document : data){
			Map<String, String> message = (Map<String,String>) document.getRawData();
			messagesList.add(message);
		}
		
		for (Map<String, String> s:messagesList){
			String dateString = s.get("pubDate");
			DateFormat formatter = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss Z", Locale.ENGLISH);
			formatter.setTimeZone(TimeZone.getTimeZone("UTC"));

			try {
				Date date = formatter.parse(dateString);
				if (date.before(dateBefore.getTime()) && date.after(dateAfter.getTime())){
					RssMapDocument document = new RssMapDocument();
					document.setRawData(s);	//Si el mensaje está en el intervalo se añade a la lista
					listDocument.add(document);
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}

		return listDocument;
	}

	@Override
	public boolean isCompatibleWith(Document document) {
		if (document instanceof RssMapDocument) {
			return true;
		}
		return false;
	}

}
