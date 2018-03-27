package inputComponents;

import java.io.IOException;
import java.util.Map;

import rss.RssClient;

public class RssInput implements InputComponent{
	
	private int itemsLimit_;	//Numero m�ximo de items que devolver� la funci�n.
	
	//Constructor parametrizado de la clases RssInput. Necesita el num de items.
	public RssInput(int numItems){
		itemsLimit_ = numItems;
	}
	//Funci�n que devuelve la informaci�n de un canal RSS
	@Override
	public Object execute(Map<String, String> configuration) {

		RssClient rc = new RssClient(configuration.get("rssUrl"));		
		try {
			
			//Devuelve un objeto de tipo List<Map<String,String>>
			return rc.rssReader(itemsLimit_);
			
		} catch (IOException e) {
			// ***
			e.printStackTrace();
			return null;
		}
	}

}
