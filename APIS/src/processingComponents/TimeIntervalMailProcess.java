package processingComponents;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import document.Document;
import document.MessageDocument;
import mail.MessageData;

public class TimeIntervalMailProcess implements ProcessComponent{


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

		//Para tratar la información se almacenará en una lista de Emails
		List<MessageData> messagesList = new ArrayList<MessageData>();
		for (Document document : data){
			MessageData message = (MessageData) document.getRawData();
			messagesList.add(message);
		}
		
		for (MessageData s:messagesList){
			if (s.getDate_().before(dateBefore.getTime()) && s.getDate_().after(dateAfter.getTime())){
				MessageDocument document = new MessageDocument();
				document.setRawData(s);	//Si el mensaje está en el intervalo se añade a la lista
				listDocument.add(document);
			}
		}

		return listDocument;

	}

	@Override
	public boolean isCompatibleWith(Document document) {
		if (document instanceof MessageDocument) {
			return true;
		}
		return false;
	}
}
