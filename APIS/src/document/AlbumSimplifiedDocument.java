package document;

import com.wrapper.spotify.model_objects.specification.AlbumSimplified;
import com.wrapper.spotify.model_objects.specification.ArtistSimplified;

public class AlbumSimplifiedDocument extends Document{

	@Override
	public String getDataAsString() {
		AlbumSimplified album = (AlbumSimplified)getRawData();
		ArtistSimplified[] ar = album.getArtists();
		String data = "Album: "+ album.getName()+" | Artista(s): ";
		for (ArtistSimplified x : ar){
			data += x.getName()+" ";
		}
		return data;
	}

}
