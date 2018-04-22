package document;

public abstract class Document {

	private Object rawData;

	public Object getRawData() {
		return rawData;
	}

	public void setRawData(Object rawData) {
		this.rawData = rawData;
	}

	public abstract String getDataAsString();

}
