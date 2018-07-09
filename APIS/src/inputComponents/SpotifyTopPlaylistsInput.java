package inputComponents;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.PlaylistSimplified;

import document.Document;
import document.PlaylistSimplifiedDocument;
import spotify.SpotifyManager;

public class SpotifyTopPlaylistsInput implements InputComponent{
	private Document document_ = new PlaylistSimplifiedDocument();
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

	//Función que devuelve las top playlist de un determinado país
	@Override
	public List<Document> execute() {
		Map<String, String> configuration = this.getConfiguration();

		List<Document> listDocument = new ArrayList<Document>();

		try {
			SpotifyManager sm = new SpotifyManager();


			for (PlaylistSimplified track : sm.getTopPlaylists(configuration.get("country"))){
				PlaylistSimplifiedDocument document = new PlaylistSimplifiedDocument();
				document.setRawData(track);
				listDocument.add(document);
			}
		} catch (SpotifyWebApiException | IOException e) {
			e.printStackTrace();
		}
		return listDocument;
	}
}
