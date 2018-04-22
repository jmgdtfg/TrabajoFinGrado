package inputComponents;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.Playlist;
import com.wrapper.spotify.model_objects.specification.PlaylistTrack;

import document.Document;
import document.TrackDocument;
import spotify.SpotifyHitsPlaylistFactory;
import spotify.SpotifyManager;

public class SpotifyHitsByCountryInput implements InputComponent{

	@Override
	public List<Document> execute(Map<String, String> configuration) {
		SpotifyHitsPlaylistFactory factory = new SpotifyHitsPlaylistFactory();
		String idPlaylist = factory.getPlaylist(configuration.get("country"));

		List<Document> listDocument = new ArrayList<Document>();

		try {
			SpotifyManager sm = new SpotifyManager();
			Playlist playlist = sm.getPlaylist(
					configuration.get("country"), //País
					"spotifycharts", 			//Usuario propio de spotify
					idPlaylist);				//Id de la playlist del país

			for (PlaylistTrack track : playlist.getTracks().getItems()){
				//Una playlist es un conjunto de canciones
				TrackDocument document = new TrackDocument();
				document.setRawData(track.getTrack());
				listDocument.add(document);
			}
		} catch (SpotifyWebApiException | IOException e) {
			e.printStackTrace();
		}

		return listDocument;

	}

}
