package document;

import java.util.Map;

public class FeedBackDocument extends Document{

	@Override
	public String getDataAsString() {
		@SuppressWarnings("unchecked")
		Map<String,Integer> data = (Map<String, Integer>) getRawData();
		String message = "Me gusta: " + String.valueOf(data.get("favorites")).toString()+"\n";
		message = message.concat("Retweets: "+String.valueOf(data.get("retweets")).toString()+"\n");
		message = message.concat("Menciones: "+String.valueOf(data.get("mentions")).toString()+"\n");

		return message;
	}

}
