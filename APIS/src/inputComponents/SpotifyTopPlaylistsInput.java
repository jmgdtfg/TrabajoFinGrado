package inputComponents;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.PlaylistSimplified;

import document.Document;
import document.TrackDocument;
import spotify.SpotifyManager;

public class SpotifyTopPlaylistsInput implements InputComponent{
	//Función que devuelve las top playlist de un determinado país
	@Override
	public List<Document> execute(Map<String, String> configuration) {

		List<Document> listDocument = new ArrayList<Document>();

		try {
			SpotifyManager sm = new SpotifyManager();


			for (PlaylistSimplified track : sm.getTopPlaylists(configuration.get("country"))){
				TrackDocument document = new TrackDocument();
				document.setRawData(track);
				listDocument.add(document);
			}
		} catch (SpotifyWebApiException | IOException e) {
			e.printStackTrace();
		}

		return listDocument;
	}
}
