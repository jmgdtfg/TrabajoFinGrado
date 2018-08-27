package processingComponents;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


import com.wrapper.spotify.model_objects.specification.Track;

import document.Document;
import document.TrackDocument;

public class TopTracksProcess implements ProcessComponent{
	private Map<String, String> configuration_;
	@Override
	public Map<String, String> getConfiguration() {
		return configuration_;
	}

	@Override
	public void setConfiguration(Map<String, String> configuration) {
		configuration_ = configuration;
	}

	//Función que devuelve las canciones más escuchadas de una playlist
	@Override
	public List<Document> execute(List<Document> data, Map<String, String> configuration) {
		
		int top = Integer.valueOf(configuration.get("topTracks")).intValue();
		List<Document> listDocument = new ArrayList<Document>();
		if (data.size() <= top){
			return data;
		}
		
		//Proceso de ordenación de tracks
		List<Track> sortList = new ArrayList<Track>();
		for (Document document : data){
			Track track = (Track) document.getRawData();
			sortList.add(track);
		}
		//sortList.sort(Comparator.comparing(Track::getPopularity));
		
		//Se devuelven solo el número de resultados pedido en la configuración
		
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
