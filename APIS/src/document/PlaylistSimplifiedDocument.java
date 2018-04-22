package document;

import com.wrapper.spotify.model_objects.specification.PlaylistSimplified;

public class PlaylistSimplifiedDocument extends Document{

	@Override
	public String getDataAsString() {
		PlaylistSimplified playlist = (PlaylistSimplified)getRawData();
		return playlist.getName();
	}

}
