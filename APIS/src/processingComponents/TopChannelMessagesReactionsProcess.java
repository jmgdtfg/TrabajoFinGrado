package processingComponents;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import com.ullink.slack.simpleslackapi.events.SlackMessagePosted;

import document.Document;
import document.SlackMessagePostedDocument;

public class TopChannelMessagesReactionsProcess implements ProcessComponent{

	@Override
	public List<Document> execute(List<Document> data, Map<String, String> configuration) {
		int top = Integer.valueOf(configuration.get("top")).intValue();
		List<Document> listDocument = new ArrayList<Document>();
		if (data.size() <= top){
			return data;
		}
		//Proceso de ordenación de mensajes con más reacciones
		List<SlackMessagePosted> sortList = new ArrayList<SlackMessagePosted>();
		for (Document document : data){
			SlackMessagePosted message = (SlackMessagePosted) document.getRawData();
			sortList.add(message);
			
		}
		sortList.sort(Comparator.comparing(SlackMessagePosted::getTotalCountOfReactions).reversed());
		
		//Se devuelven solo el número de resultados pedido en la configuración
		
		for (int i = 0; i < top ; i++){
			SlackMessagePostedDocument document = new SlackMessagePostedDocument();
			document.setRawData(sortList.get(i));
			listDocument.add(document);
		}

		return listDocument;
	}

	@Override
	public boolean isCompatibleWith(Document document) {
		if (document instanceof SlackMessagePostedDocument) {
			return true;
		}

		return false;
	}

}
