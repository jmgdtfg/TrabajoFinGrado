package document;

import java.nio.file.Path;

public class PathDocument extends Document{

	@Override
	public String getDataAsString() {
		Path path = (Path)getRawData();
		return path.toString();
	}
	

}
