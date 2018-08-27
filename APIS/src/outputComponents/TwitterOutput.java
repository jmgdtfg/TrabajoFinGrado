package outputComponents;

import java.util.List;
import java.util.Map;

import document.Document;
import twitter.TwitterManager;
import twitter4j.TwitterException;

public class TwitterOutput implements OutputComponent{
	private Map<String, String> configuration_;
	@Override
	public Map<String, String> getConfiguration() {
		return configuration_;
	}

	@Override
	public void setConfiguration(Map<String, String> configuration) {
		configuration_ = configuration;
	} 

	private static int limit_ = 280; 	//Límite de carácteres


	@Override
	public void execute(List<Document> data, Map<String, String> configuration) {		
		TwitterManager tm = new TwitterManager();
		String message = "";

		for (Document document : data) {
			message += document.getDataAsString();
			message += "\n";
		}
		message += "--------------------------------\n";
		//Si el mensaje es demasiado grande se recorta
		if (message.length()>limit_){
			String newMessage = message.substring(0, 270);
			message = "";
			message = newMessage + "[...]";
		}

		try {
			//Se escribe el tweet
			tm.writeTweet(message);
		} catch (TwitterException e) {
			e.printStackTrace();
		}
	}

}
