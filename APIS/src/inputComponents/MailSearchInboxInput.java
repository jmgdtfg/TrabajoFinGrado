package inputComponents;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import document.Document;
import document.MessageDocument;
import mail.MailManager;
import mail.MessageData;

public class MailSearchInboxInput implements InputComponent{
	private Document document_ = new MessageDocument();
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

	//Función que devuelve los mensajes que coinciden con la búsqueda
	@Override
	public List<Document> execute() {
		Map<String, String> configuration = this.getConfiguration();
		
		MailManager mm = new MailManager();
		List<Document> listDocument = new ArrayList<Document>();
		
		if (configuration.get("mailSearchType").equals("content")){
			for (MessageData m : mm.filterByContent(configuration.get("word"))){
				MessageDocument document = new MessageDocument();
				document.setRawData(m);
				listDocument.add(document);
			}
		}
		else if (configuration.get("mailSearchType").equals("sender")){
			for (MessageData m : mm.filterBySender(configuration.get("word"))){
				MessageDocument document = new MessageDocument();
				document.setRawData(m);
				listDocument.add(document);
			}
		}
		else if (configuration.get("mailSearchType").equals("subject")){
			for (MessageData m : mm.filterBySubject(configuration.get("word"))){
				MessageDocument document = new MessageDocument();
				document.setRawData(m);
				listDocument.add(document);
			}
		}
		return listDocument;

	}

}
