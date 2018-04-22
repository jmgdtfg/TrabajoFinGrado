package document;

import com.wrapper.spotify.model_objects.specification.ArtistSimplified;
import com.wrapper.spotify.model_objects.specification.Track;

public class TrackDocument extends Document{

	@Override
	public String getDataAsString() {

		Track track = (Track)getRawData();
		ArtistSimplified[] ar = track.getArtists();
		String data = "Título: " + track.getName()+" | Artista(s): ";
		for (ArtistSimplified as : ar){
			data += as.getName()+" ";
		}		
		return data;
	}

}
