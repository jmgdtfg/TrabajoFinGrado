package inputComponents;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.AlbumSimplified;
import com.wrapper.spotify.model_objects.specification.Artist;
import com.wrapper.spotify.model_objects.specification.PlaylistSimplified;
import com.wrapper.spotify.model_objects.specification.Track;

import document.AlbumSimplifiedDocument;
import document.ArtistDocument;
import document.Document;
import document.TrackDocument;
import spotify.SpotifyManager;

public class SpotifySearchInput implements InputComponent{
	private Document document1_ = new TrackDocument();
	private Document document2_ = new AlbumSimplifiedDocument();
	private Document document3_ = new ArtistDocument();
	private Document document4_ = new TrackDocument();
	//Según el valor de la flag, se utilizará un documento u otro
	private int flag = 0;
	
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
		if (flag == 1)
			return document1_;
		else if(flag == 2)
			return document2_;
		else if (flag == 3)
			return document3_;
		else if (flag == 4)
			return document4_;
		else
			return null;
	}

	//Función que devolverá los resultados de la búsqueda.
	@Override
	public List<Document> execute() {
		Map<String, String> configuration = this.getConfiguration();
		List<Document> listDocument = new ArrayList<Document>();
		try {
			SpotifyManager sm = new SpotifyManager();
			//configuration.get("country") debe devolver el país EN ESPAÑOL
			if (configuration.get("spotifySearchType").equals("tracks")){		

				for (Track track : sm.searchSongs(configuration.get("word"), configuration.get("country"))){
					TrackDocument document = new TrackDocument();
					document.setRawData(track);
					listDocument.add(document);
				}
;
			}
			else if (configuration.get("spotifySearchType").equals("albums")){
				
				for (AlbumSimplified album : sm.searchAlbums(configuration.get("word"), configuration.get("country"))){
					AlbumSimplifiedDocument document = new AlbumSimplifiedDocument();
					document.setRawData(album);
					listDocument.add(document);
				}


			}
			else if (configuration.get("spotifySearchType").equals("artists")){

				for (Artist artist: sm.searchArtists(configuration.get("word"), configuration.get("country"))){
					ArtistDocument document = new ArtistDocument();
					document.setRawData(artist);
					listDocument.add(document);
				}

			}
			else if (configuration.get("spotifySearchType").equals("playlists")){
	
				for (PlaylistSimplified playlist : sm.searchPlaylist(configuration.get("word"), configuration.get("country"))){
					TrackDocument document = new TrackDocument();
					document.setRawData(playlist);
					listDocument.add(document);
				}
				
			}


		} catch (SpotifyWebApiException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return listDocument;
	}



}
