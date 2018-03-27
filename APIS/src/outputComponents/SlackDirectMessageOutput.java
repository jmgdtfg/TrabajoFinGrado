package outputComponents;

import java.io.IOException;
import java.util.Map;

import slack.SlackManager;

public class SlackDirectMessageOutput implements OutputComponent{
	//Funci�n que env�a los datos por un mensaje directo de slack
	@Override
	public void execute(Object data, Map<String, String> configuration) {

		try {
			SlackManager sm = new SlackManager();
			String message = (String) data;
			sm.sendDirectMessageToAUser(configuration.get("slackUserMail"), message);
			
		} catch (IOException e) {
			// ***
			e.printStackTrace();
		}
		
	}

}
