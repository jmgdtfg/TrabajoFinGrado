package processingComponents;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import document.Document;
import document.RssMapDocument;


public class FilterByRssAuthorProcess implements ProcessComponent{
	private Map<String, String> configuration_;
	@Override
	public Map<String, String> getConfiguration() {
		return configuration_;
	}

	@Override
	public void setConfiguration(Map<String, String> configuration) {
		configuration_ = configuration;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Document> execute(List<Document> data, Map<String, String> configuration) {
		String author = configuration.get("author");

		List<Document> listDocument = new ArrayList<Document>();


		//Comprobación de que los valores del intervalo sean válidos
		if (author == null)
			return null;

		
		//Para tratar la información se almacenará en una lista de Emails
		List<Map<String,String>> messagesList = new ArrayList<Map<String,String>>();
		for (Document document : data){
			Map<String, String> message = (Map<String,String>) document.getRawData();
			messagesList.add(message);
		}
		
		for (Map<String, String> s:messagesList){
			String autor = s.get("author");
			if (autor.contains(author)){
				RssMapDocument document = new RssMapDocument();
				document.setRawData(s);	//Si el mensaje está en el intervalo se añade a la lista
				listDocument.add(document);
			}
		}

		return listDocument;
	}

	@Override
	public boolean isCompatibleWith(Document document) {
		if (document instanceof RssMapDocument) {
			return true;
		}
		return false;
	}

}
