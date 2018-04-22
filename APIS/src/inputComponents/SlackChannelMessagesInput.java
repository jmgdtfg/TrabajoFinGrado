package inputComponents;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.ullink.slack.simpleslackapi.events.SlackMessagePosted;

import document.Document;
import document.SlackMessagePostedDocument;
import slack.SlackManager;

public class SlackChannelMessagesInput implements InputComponent{
	//Funci�n que devuelve los mensajes de un canal
	@Override
	public List<Document> execute(Map<String, String> configuration) {
		
		List<Document> listDocument = new ArrayList<Document>();
		try {
			SlackManager sm = new SlackManager();	
			for (SlackMessagePosted data :sm.getChannelMessages(configuration.get("channelName"))){	
				SlackMessagePostedDocument document = new SlackMessagePostedDocument();	
				document.setRawData(data);
				listDocument.add(document);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return listDocument;
		

		
	}

}
