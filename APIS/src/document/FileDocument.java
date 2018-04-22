package document;

import java.io.File;

public class FileDocument extends Document{

	@Override
	public String getDataAsString() {
		File file = (File)getRawData();
		return file.getName();
	}

}
