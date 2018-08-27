package outputComponents;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import document.Document;
import slack.SlackManager;

public class SlackChannelMessageOutput implements OutputComponent{
	
	private Map<String, String> configuration_;
	@Override
	public Map<String, String> getConfiguration() {
		return configuration_;
	}

	@Override
	public void setConfiguration(Map<String, String> configuration) {
		configuration_ = configuration;
	} 
	//Función que envía los datos a través de un canal de slack
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

			sm.sendMessageWithBot(message, configuration.get("channelName"));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}

}
