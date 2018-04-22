package document;

import java.util.Map;

public class RssMapDocument extends Document{

	@Override
	public String getDataAsString() {

		@SuppressWarnings("unchecked")
		Map<String,String> m = (Map<String, String>) getRawData();
		String message = "";
		message = message.concat("Título: "+m.get("title")+"\n");
		message = message.concat("Descripción: "+m.get("description")+"\n");
		message = message.concat("Fecha: "+m.get("pubDate")+"\n");
		message = message.concat("Contenido: "+m.get("content")+"\n");
		message = message.concat("Link: "+m.get("link")+"\n");
		message = message.concat("Autor: "+m.get("author")+"\n");
		message = message.concat("-----------------------\n");

		return message;
	}

}
