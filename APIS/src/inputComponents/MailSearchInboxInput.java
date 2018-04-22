package inputComponents;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import document.Document;
import document.MessageDocument;
import mail.MailManager;
import mail.Mensaje;

public class MailSearchInboxInput implements InputComponent{

	//Función que devuelve los mensajes que coinciden con la búsqueda
	@Override
	public List<Document> execute(Map<String, String> configuration) {
		
		MailManager mm = new MailManager();
		List<Document> listDocument = new ArrayList<Document>();
		
		if (configuration.get("mailSearchType").equals("content")){
			for (Mensaje m : mm.filterByContent(configuration.get("word"))){
				MessageDocument document = new MessageDocument();
				document.setRawData(m);
				listDocument.add(document);
			}
		}
		else if (configuration.get("mailSearchType").equals("sender")){
			for (Mensaje m : mm.filterBySender(configuration.get("word"))){
				MessageDocument document = new MessageDocument();
				document.setRawData(m);
				listDocument.add(document);
			}
		}
		else if (configuration.get("mailSearchType").equals("subject")){
			for (Mensaje m : mm.filterBySubject(configuration.get("word"))){
				MessageDocument document = new MessageDocument();
				document.setRawData(m);
				listDocument.add(document);
			}
		}
		
		return listDocument;

	}

}
