package outputComponents;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import document.Document;
import slack.SlackManager;

public class SlackDirectMessageOutput implements OutputComponent{
	//Función que envía los datos por un mensaje directo de slack
	@Override
	public void execute(List<Document> data, Map<String, String> configuration) {

		try {
			SlackManager sm = new SlackManager();
			
			String message = "";
			
			for (Document document : data) {
				message += document.getDataAsString();
				message += "\n";
			}
			message += "--------------------------------\n";
			
			sm.sendDirectMessageToAUser(configuration.get("slackUserMail"), message);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
