package inputComponents;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import document.Document;
import document.MessageDocument;
import mail.MailManager;
import mail.MessageData;

public class MailInboxInput implements InputComponent{
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

	//Función que devuelve la bandeja de entrada
	@Override
	public List<Document> execute() {
		Map<String, String> configuration = this.getConfiguration();

		List<Document> listDocument = new ArrayList<Document>();

		MailManager mm = new MailManager(
				configuration.get("user"),
				configuration.get("password"),
				configuration.get("server"));

		for (MessageData m : mm.getInbox()){
			MessageDocument document = new MessageDocument();
			document.setRawData(m);
			listDocument.add(document);
		}
		return listDocument;

	}

}
