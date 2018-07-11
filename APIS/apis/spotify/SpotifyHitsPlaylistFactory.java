package spotify;

import java.util.HashMap;
import java.util.Map;

public class SpotifyHitsPlaylistFactory {

	private Map<String, String> playlist_ = new HashMap<String, String>();
	
	public SpotifyHitsPlaylistFactory(){
		playlist_.put("España", "37i9dQZEVXbNFJfN1Vw8d9");
		playlist_.put("Alemania", "37i9dQZEVXbJiZcmkrIHGU");
		playlist_.put("Francia", "37i9dQZEVXbIPWwFssbupI");
		playlist_.put("Global", "37i9dQZEVXbMDoHDwVN2tF");
		playlist_.put("Argentina", "37i9dQZEVXbMMy2roB9myp");
		playlist_.put("Australia", "37i9dQZEVXbJPcfkRz0wJ0");
		playlist_.put("Austria", "37i9dQZEVXbKNHh6NIXu36");
		playlist_.put("Belgica", "37i9dQZEVXbJNSeeHswcKB");
		//...
	}
	public String getPlaylist(String key){
		return playlist_.getOrDefault(key, "37i9dQZEVXbMDoHDwVN2tF");
	}
	
}
