package inputComponents;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import document.Document;
import document.MessageDocument;
import mail.MailManager;
import mail.MessageData;

public class MailInboxInput implements InputComponent{

	//Función que devuelve la bandeja de entrada
	@Override
	public List<Document> execute(Map<String, String> configuration) {

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
