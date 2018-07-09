package inputComponents;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.TrackSimplified;

import document.Document;
import document.TrackSimplifiedDocument;
import spotify.SpotifyManager;

public class SpotifyRecommendationsInput implements InputComponent{
	private Document document_ = new TrackSimplifiedDocument();
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

	//Función que devuelve las recomendaciones
	@Override
	public List<Document> execute() {
		Map<String, String> configuration = this.getConfiguration();
		List<Document> listDocument = new ArrayList<Document>();
		try {
			SpotifyManager tm = new SpotifyManager();
			TrackSimplified[] tracks = tm.getRecommendations(
					configuration.get("country"), 
					configuration.get("idArtist"),
					configuration.get("idTrack"),		
					configuration.get("genre"));							
			for (TrackSimplified track : tracks) {
				TrackSimplifiedDocument document = new TrackSimplifiedDocument();
				document.setRawData(track);
				listDocument.add(document);
			}
		} catch (SpotifyWebApiException | IOException e) {
			e.printStackTrace();
		}
		return listDocument;
	}

}
