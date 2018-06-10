package processingComponents;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import document.Document;
import document.StringDocument;
import parser.Parser;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class TopFrecuentWordGenericProcess implements ProcessComponent{


	
	@Override
	public List<Document> execute(List<Document> data, Map<String, String> configuration) {
		int top = Integer.valueOf(configuration.get("top")).intValue();
		//Mapa en el que se asigna el número de veces que se repite cada palabra
		Map<String,Integer> wordsMap = new HashMap<String,Integer>();

		List<Document> listDocument = new ArrayList<Document>();

		//Proceso de ordenación 
		for (Document document : data){
			String message = document.getDataAsString();
			
			String[] words = message.split(" ");
			for ( String word : words) {
				word = Parser.parse(word);
				if (wordsMap.containsKey(word) && Parser.isValid(word.trim())){
					wordsMap.replace(word, wordsMap.get(word)+1);
				}
				else{
					wordsMap.put(word, 1);

				}
			}
		}


		//Ordenacion del map
		Object[] a = wordsMap.entrySet().toArray();
		Arrays.sort(a, new Comparator() {
			public int compare(Object o1, Object o2) {
				return ((Map.Entry<String, Integer>) o2).getValue()
						.compareTo(((Map.Entry<String, Integer>) o1).getValue());
			}
		});
		List<String> sortList = new ArrayList();
		for (Object e : a) {			
			sortList.add(((Map.Entry<String, Integer>) e).getKey());
		}

		for (int i = 0; i < top ; i++){
			StringDocument document = new StringDocument();
			document.setRawData(sortList.get(i));
			listDocument.add(document);
		}
		return listDocument;

	}


	@Override
	public boolean isCompatibleWith(Document document) {
		return true;
	}


	
}
