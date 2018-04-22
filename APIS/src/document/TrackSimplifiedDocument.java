package document;

import com.wrapper.spotify.model_objects.specification.TrackSimplified;

public class TrackSimplifiedDocument extends Document{

	@Override
	public String getDataAsString() {
		TrackSimplified track = (TrackSimplified)getRawData();
		return track.getName();
	}

}
