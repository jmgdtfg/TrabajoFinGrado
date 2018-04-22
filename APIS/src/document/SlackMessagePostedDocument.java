package document;

import com.ullink.slack.simpleslackapi.events.SlackMessagePosted;

public class SlackMessagePostedDocument extends Document{

	@Override
	public String getDataAsString() {
		SlackMessagePosted message = (SlackMessagePosted) getRawData();
		return message.getMessageContent();
	}

}
