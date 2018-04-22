package document;

import com.wrapper.spotify.model_objects.specification.Artist;

public class ArtistDocument extends Document{

	@Override
	public String getDataAsString() {
		
		Artist artist = (Artist)getRawData();
		String data = "Nombre: "+artist.getName()+" | Seguidores: "+artist.getFollowers();
		return data;
		
	}

}
