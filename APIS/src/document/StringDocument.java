package document;

public class StringDocument extends Document{

	@Override
	public String getDataAsString() {
		return (String) getRawData();
	}

}
