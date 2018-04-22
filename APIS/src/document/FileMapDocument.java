package document;

import java.util.Map;

public class FileMapDocument extends Document{

	@Override
	public String getDataAsString() {
		@SuppressWarnings("unchecked")
		Map<String,Integer> datos = (Map<String,Integer>)getRawData();
		String message = "";
		for (Map.Entry<String,Integer> entry : datos.entrySet()) {
			String key = entry.getKey();
			int value = entry.getValue();
			if (key == "")
				message = message.concat("Otros"+": "+value+"\n");
			else
				message = message.concat(key+": "+value+"\n");
		}
		return message;
		
	}

}
