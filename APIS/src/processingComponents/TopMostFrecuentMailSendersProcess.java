package processingComponents;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import document.Document;
import document.MessageDocument;
import document.StringDocument;
import mail.MessageData;


public class TopMostFrecuentMailSendersProcess implements ProcessComponent{
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<Document> execute(List<Document> data, Map<String, String> configuration) {
		int top = Integer.valueOf(configuration.get("top")).intValue();

		List<Document> listDocument = new ArrayList<Document>();
		if (data.size() <= top){
			return data;
		}
		//Mapa que almacena los emisores y el numero de veces que aparecen
		Map <String,Integer> topMap = new HashMap<String,Integer>();

		//Proceso de ordenación 
		for (Document document : data){
			MessageData message = (MessageData) document.getRawData();
			String sender = message.getFrom_();

			if (topMap.containsKey(sender)){
				topMap.replace(sender, topMap.get(sender)+1);
			}
			else{
				topMap.put(sender, 1);

			}
		} 
		//Ordenacion del map
		Object[] a = topMap.entrySet().toArray();
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
		if (document instanceof MessageDocument) {
			return true;
		}
		return false;
	}
}
