package inputComponents;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import document.Document;
import document.RssMapDocument;
import rss.RssClient;


public class RssInput implements InputComponent{
	private Document document_ = new RssMapDocument();
	private Map<String, String> configuration_;
	@Override
	public Map<String, String> getConfiguration() {
		return configuration_;
	}

	@Override
	public void setConfiguration(Map<String, String> configuration) {
		configuration_ = configuration;
	}
	
	@Override
	public Document getDocument() {
		return document_;
	}
	//Función que devuelve la información de un canal RSS
	@Override
	public List<Document> execute() {
		Map<String, String> configuration = this.getConfiguration();
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
