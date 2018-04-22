package inputComponents;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.TrackSimplified;

import document.Document;
import document.TweetDocument;
import spotify.SpotifyManager;

public class SpotifyRecommendationsInput implements InputComponent{
	//Función que devuelve las recomendaciones
	@Override
	public List<Document> execute(Map<String, String> configuration) {
		List<Document> listDocument = new ArrayList<Document>();
		try {
			SpotifyManager tm = new SpotifyManager();
			TrackSimplified[] tracks = tm.getRecommendations(
					configuration.get("country"), 
					configuration.get("idArtist"),
					configuration.get("idTrack"),		
					configuration.get("genre"));							
			for (TrackSimplified track : tracks) {
				TweetDocument document = new TweetDocument();
				document.setRawData(track);
				listDocument.add(document);
			}
		} catch (SpotifyWebApiException | IOException e) {
			e.printStackTrace();
		}


		
		return listDocument;
	}

}
