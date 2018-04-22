package document;

import twitter4j.Status;

public class TweetDocument extends Document {
	@Override
	public String getDataAsString() {		
		return ((Status)getRawData()).getUser().getName() + ": " + ((Status) getRawData()).getText();			
	}

}
