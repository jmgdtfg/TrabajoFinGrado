package inputComponents;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import document.Document;
import document.RssMapDocument;
import rss.RssClient;


public class RssInput implements InputComponent{

	//Funci�n que devuelve la informaci�n de un canal RSS
	@Override
	public List<Document> execute(Map<String, String> configuration) {

		int limit = Integer.valueOf(configuration.get("limit")).intValue();
		RssClient rc = new RssClient(configuration.get("rssUrl"));
		List<Document> listDocument = new ArrayList<Document>();
		try{
			for (Map<String, String> item : rc.rssReader(limit)) {
				RssMapDocument document = new RssMapDocument();
				document.setRawData(item);
				listDocument.add(document);
			}
		}catch(IOException e){
			e.printStackTrace();
		}

		return listDocument;

	}

}
