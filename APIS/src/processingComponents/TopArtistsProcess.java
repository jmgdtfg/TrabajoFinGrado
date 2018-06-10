package processingComponents;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wrapper.spotify.model_objects.specification.ArtistSimplified;
import com.wrapper.spotify.model_objects.specification.Track;

import document.Document;
import document.StringDocument;
import document.TrackDocument;

public class TopArtistsProcess implements ProcessComponent{

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<Document> execute(List<Document> data, Map<String, String> configuration) {
		
		int top = Integer.valueOf(configuration.get("topArtists")).intValue();
		
		List<Document> listDocument = new ArrayList<Document>();
		if (data.size() <= top){
			return data;
		}
		//Mapa que almacena los artistas y el numero de veces que aparecen
		Map <String,Integer> topMap = new HashMap<String,Integer>();
		
		//Proceso de ordenación de artistas
		for (Document document : data){
			Track track = (Track) document.getRawData();
			ArtistSimplified[] artistList = track.getArtists();
			for (ArtistSimplified a : artistList){
				if (topMap.containsKey(a.getName())){
					topMap.replace(a.getName(), topMap.get(a.getName())+1);
				}
				else{
					topMap.put(a.getName(), 1);
				}
			}
		}
		//Ordenacion del map
		Object[] a = topMap.entrySet().toArray();
		Arrays.sort(a, new Comparator() {
		    public int compare(Object o1, Object o2) {
		        return ((Map.Entry<String, Integer>) o2).getValue()
		                   .compareTo(((Map.Entry<String, Integer>) o1).getValue());
		    }
		});
		List<String> sortList = new ArrayList();
		for (Object e : a) {			
		    sortList.add(((Map.Entry<String, Integer>) e).getKey());
		}
		for (String s : sortList){
			System.out.println(s);
		}
		
		for (int i = 0; i < top ; i++){
			StringDocument document = new StringDocument();
			document.setRawData(sortList.get(i));
			listDocument.add(document);
		}
		
		return listDocument;
		
		
	}

	@Override
	public boolean isCompatibleWith(Document document) {
		if (document instanceof TrackDocument) {
			return true;
		}

		return false;
	}

}
