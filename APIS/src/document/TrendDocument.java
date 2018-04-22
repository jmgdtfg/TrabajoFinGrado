package document;

import twitter4j.Trend;

public class TrendDocument extends Document{

	@Override
	public String getDataAsString() {
		return ((Trend)getRawData()).getName();
	}

}
