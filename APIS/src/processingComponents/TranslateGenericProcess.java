package processingComponents;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import document.Document;
import document.StringDocument;
import ibm.Translator;


public class TranslateGenericProcess implements ProcessComponent{
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
	public List<Document> execute(List<Document> data, Map<String, String> configuration) {
		List<Document> listDocument = new ArrayList<Document>();
		Translator translator = new Translator();
		String message = "";
		//Proceso de ordenación 
		for (Document document : data){
			message += document.getDataAsString()+"\n";
		}
		String result = translator.translate(message, configuration.get("language"));
		StringDocument document = new StringDocument();
		document.setRawData(result);
		listDocument.add(document);

		return listDocument;

	}

	@Override
	public boolean isCompatibleWith(Document document) {
		return true;
	}

}
