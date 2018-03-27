package outputComponents;

import java.io.IOException;
import java.util.Map;

import slack.SlackManager;

public class SlackChannelMessageOutput implements OutputComponent{
	//Funci�n que env�a los datos a trav�s de un canal de slack
	@Override
	public void execute(Object data, Map<String, String> configuration) {
		
		try {
			SlackManager sm = new SlackManager();
			String message = (String) data;
			sm.sendMessageWithBot(message, configuration.get("channelName"));
			
		} catch (IOException e) {
			// ***
			e.printStackTrace();
		}
		
		
	}

}
