package rss;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class RssClient {

	private String url_;	//Direcci�n del canal RSS

	//Constructor parametrizado del cliente RSS
	public RssClient(String url){
		url_ = url;
	}

	//Funci�n que devuelve el n�mero de items (pasado por par�metro) de un canal RSS
	public List<Map<String,String>> rssReader(int numItems) throws IOException{
		//Se obtiene el documento RSS completo
		Document doc = Jsoup.connect(url_).get();

		//Lista de items. Cada item es un Map
		List<Map<String,String>> itemList = new ArrayList<>();

		//Se obtienen todos los items.
		Elements items = doc.getElementsByTag("item");

		//Si se piden m�s items de los que hay solo se devolver�n los que existan.
		if (numItems>items.size())
			numItems = items.size(); 

		for(int i = 0; i < numItems; i++){ 

			String title = items.get(i).getElementsByTag("title").html();
			String description = items.get(i).getElementsByTag("description").html();
			String pubDate = items.get(i).getElementsByTag("pubDate").html();
			String content = items.get(i).getElementsByTag("content:encoded").html();
			String link = items.get(i).getElementsByTag("link").html();
			String author = items.get(i).getElementsByTag("author").html();

			//map para insertar los valores de un item concreto.
			//Jsoup.parse cambia los valores &7hs por su correspondiente etiqueta html
			//Con clearData se eliminan las etiquetas html
			Map<String,String> map = new HashMap<String,String>();
			map.put("title", this.clearData(Jsoup.parse(title).text()));
			map.put("description", this.clearData(Jsoup.parse(description).text()));
			map.put("pubDate", this.clearData(Jsoup.parse(pubDate).text()));
			map.put("content", this.clearData(Jsoup.parse(content).text()));
			map.put("link", this.clearData(Jsoup.parse(link).text()));
			map.put("author", this.clearData(Jsoup.parse(author).text()));

			//Se a�ade a la lista el nuevo elemento
			itemList.add(map);

		}

		return itemList;
	}

	//Funci�n que elimina todas las etiquetas html de los datos
	private String clearData(String data){
		data = data.replaceAll("\\<[^>]*>","");
		return data;
	}

}
