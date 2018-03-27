package inputComponents;

import java.io.IOException;
import java.util.Map;

import com.wrapper.spotify.exceptions.SpotifyWebApiException;

import spotify.SpotifyManager;

public class SpotifySearchInput implements InputComponent{

	//Funci�n que devolver� los resultados de la b�squeda.
	@Override
	public Object execute(Map<String, String> configuration) {
		try {
			SpotifyManager sm = new SpotifyManager();
			//configuration.get("country") debe devolver el pa�s EN ESPA�OL
			if (configuration.get("spotifySearchType").equals("tracks")){		
				//Devuelve un Track[]
				return sm.searchSongs(configuration.get("word"), configuration.get("country"));
			}
			else if (configuration.get("spotifySearchType").equals("albums")){
				//Devuelve un AlbumSimplified[]
				return sm.searchAlbums(configuration.get("word"), configuration.get("country"));
			}
			else if (configuration.get("spotifySearchType").equals("artists")){
				//Devuelve un Artist[]
				return sm.searchArtists(configuration.get("word"), configuration.get("country"));
			}
			else if (configuration.get("spotifySearchType").equals("playlists")){
				//Devuelve un PlaylistSimplified[]
				return sm.searchPlaylist(configuration.get("word"), configuration.get("country"));
			}
			
			else{
				return "spotifySearchType no es v�lido";
			}
			
			
		} catch (SpotifyWebApiException e) {
			// ***
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			// ***
			e.printStackTrace();
			return null;
		}

	}

}
