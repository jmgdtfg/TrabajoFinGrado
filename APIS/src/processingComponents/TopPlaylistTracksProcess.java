package processingComponents;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.wrapper.spotify.model_objects.specification.ArtistSimplified;
import com.wrapper.spotify.model_objects.specification.Paging;
import com.wrapper.spotify.model_objects.specification.Playlist;
import com.wrapper.spotify.model_objects.specification.PlaylistTrack;


public class TopPlaylistTracksProcess implements ProcessComponent{
	
	private int numTop_; //numero de valores que devolver� el top
	
	//Constructor parametrizado obligatorio de la clase
	public TopPlaylistTracksProcess(int numTop){
		numTop_=numTop;
	}
	
	//Funci�n que devuelve las canciones m�s escuchadas de una playlist
	@Override
	public Object execute(Object data, Map<String, String> configuration) {
		
		Playlist p = (Playlist) data;
		Paging<PlaylistTrack> t = p.getTracks();
		PlaylistTrack[] tracks = t.getItems();		
		//Lista de String. Cada elemento contiene la informaci�n de una canci�n
		List<String> listTracks = new ArrayList<String>();
		String artists = "Artista(s): ";
		
		List<PlaylistTrack> trackList = new ArrayList<PlaylistTrack>();	//Lista de tracks
		List<PlaylistTrack> sortTracks = new ArrayList<PlaylistTrack>();//Lista ordenada de tracks
		
		//Transforma PlaylistTrack[] a List<PlaylistTrack>
		for (PlaylistTrack pt : tracks){
			trackList.add(pt);
		}
		//Se obtiene la lista de tracks ordenada
		for (PlaylistTrack pt : trackList){
			//Si el elemento es m�s popular lo a�ade al principio
			if (pt.getTrack().getPopularity() > trackList.get(0).getTrack().getPopularity()){
				sortTracks.add(0, pt);
			}
		}
		//Recorre la lista ordenada de tracks recogiendo la informaci�n necesaria
		for (PlaylistTrack track : sortTracks){
			//Obtiene el artista(s)
			ArtistSimplified[] at = track.getTrack().getArtists();
			for (ArtistSimplified a : at){
				artists = artists.concat(a.getName()+" ");
			}
			//Concatena a los artistas el t�tulo de la canci�n
			listTracks.add(artists+" | T�tulo: "+track.getTrack().getName());
			artists = "Artista(s): ";
		}
		
		//Elaboraci�n del mensaje
		String message = "Top canciones: \n\n";
		for (int i = 0; i < numTop_ ; i++){
			message = message.concat("\n"+listTracks.get(i));
		}
		message = message.concat("\n\n-----------------------------------------------------\n");
		
		//Devuelve un tipo string
		return message;
	}

}
