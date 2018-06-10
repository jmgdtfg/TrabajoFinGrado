package processingComponents;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import document.Document;
import document.StringDocument;
import parser.Parser;

public class GetNumberOfMatchesGenericProcess implements ProcessComponent{
	@Override
	public List<Document> execute(List<Document> data, Map<String, String> configuration) {
		String key = configuration.get("key");
		key = Parser.parse(key);
		//Mapa en el que se asigna el número de veces que se repite cada palabra
		Map<String,Integer> wordsMap = new HashMap<String,Integer>();

		List<Document> listDocument = new ArrayList<Document>();

		//Proceso de ordenación 
		for (Document document : data){
			String message = document.getDataAsString();

			String[] words = message.split(" ");
			for (String word : words) {
				word = Parser.parse(word);
				if (wordsMap.containsKey(word)){
					wordsMap.replace(word, wordsMap.get(word)+1);
				}
				else{
					wordsMap.put(word, 1);

				}
			}
		}


		if (wordsMap.containsKey(key)){
			int matches = wordsMap.get(key);
			StringDocument document = new StringDocument();
			document.setRawData(String.valueOf(matches));
			listDocument.add(document);
		}

		return listDocument;
	}

	@Override
	public boolean isCompatibleWith(Document document) {
		return true;
	}

}
