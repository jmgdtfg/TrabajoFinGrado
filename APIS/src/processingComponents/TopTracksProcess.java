package processingComponents;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


import com.wrapper.spotify.model_objects.specification.Track;

import document.Document;
import document.TrackDocument;

public class TopTracksProcess implements ProcessComponent{
	

	//Funci�n que devuelve las canciones m�s escuchadas de una playlist
	@Override
	public List<Document> execute(List<Document> data, Map<String, String> configuration) {
		
		int top = Integer.valueOf(configuration.get("top")).intValue();
		List<Document> listDocument = new ArrayList<Document>();
		if (data.size() <= top){
			return data;
		}
		
		//Proceso de ordenaci�n de tweets con m�s likes
		List<Track> sortList = new ArrayList<Track>();
		for (Document document : data){
			Track track = (Track) document.getRawData();
			sortList.add(track);
		}
		//sortList.sort(Comparator.comparing(Track::getPopularity));
		
		//Se devuelven solo el n�mero de resultados pedido en la configuraci�n
		
		for (int i = 0; i < top ; i++){
			TrackDocument document = new TrackDocument();
			document.setRawData(sortList.get(i));
			listDocument.add(document);
		}
		
		return listDocument;
	}

	@Override
	public boolean isCompatibleWith(Document document) {
		if (document instanceof TrackDocument) {
			return true;
		}

		return false;
	}

}
