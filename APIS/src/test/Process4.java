/*Proceso que proporciona información general sobre una ciudad o país
 * y lo publica en un canal de slack
- Resumen del tiempo
- Top10 de las canciones más escuchadas en el país
- Top10 de los tweets con la palabra Córdoba más importantes
- Publicar resultado en un canal de Slack
 */

package test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import document.Document;
import inputComponents.ForecastInput;
import inputComponents.SpotifyHitsByCountryInput;
import inputComponents.TwitterSearchInput;
import inputComponents.WeatherInput;
import outputComponents.SlackChannelMessageOutput;
import processingComponents.TopRetweetsProcess;
import processingComponents.TopTracksProcess;

public class Process4 {

	public static void main(String[] args) {

		Map <String, String> twitterConfig = new HashMap<String,String>();
		Map <String, String> spotifyConfig = new HashMap<String,String>();
		Map <String, String> slackConfig = new HashMap<String,String>();
		Map <String, String> weatherConfig = new HashMap<String,String>();


		//****PARAMETROS DE CONFIGURACIÓN ÚNICOS DE TWITTER

		//Esta key solo funciona correctamente con los valores "hashtag" y "word"
		twitterConfig.put("twitterSearchType", "hashtag");	
		twitterConfig.put("hashtag", "Madrid");			//Hashtag clave para realizar búsqueda
		twitterConfig.put("top", "5");
		twitterConfig.put("word", "la");
		twitterConfig.put("days", "20");//Parametro de busqueda
		twitterConfig.put("results", "1000");//Parametro de busqueda
		twitterConfig.put("country", "Spain");
		twitterConfig.put("intervalEnd", "1");	//Valor del fin de un intervalo de tiempo ( en dias )
		twitterConfig.put("intervalStart", "15");//Valor del inicio de un intervalo de tiempo ( en dias ) 



		//****PARAMETROS DE CONFIGURACIÓN ÚNICOS DE SPOTIFY

		//Esta key solo funciona correctamente los valores "tracks","albums","artists" y "playlists"
		spotifyConfig.put("spotifySearchType", "tracks");		
		spotifyConfig.put("idArtist", "3bgsNtcf5d5h9jbQbohfBK");	//Id de extremoduro en spotify
		spotifyConfig.put("idTrack", "1KPLNOTQDSWe68ea6JUjpx");		//Id de "la vereda de la puerta de atras"
		spotifyConfig.put("genre", "rock");							//Genero musical
		spotifyConfig.put("IdUserSpotify", "spotifycharts");			//Id de usuario
		spotifyConfig.put("IdPlaylist", "37i9dQZEVXbNFJfN1Vw8d9");	//Id de playlist
		spotifyConfig.put("word", "Top 50");
		spotifyConfig.put("country", "España");						
		spotifyConfig.put("top", "5");


		//****PARAMETROS DE CONFIGURACIÓN ÚNICOS DE SLACK
		slackConfig.put("channelName", "general"); 				//Nombre del canal de slack
		slackConfig.put("slackUserMail", "jmgd_3@outlook.com");	//Email de un usuario en slack


		//****PARAMETROS DE CONFIGURACIÓN ÚNICOS DE OPENWEATHERMAP
		weatherConfig.put("weatherCity", "Sevilla");				//Ciudad de la que se obtendrán los datos



		//Componentes de entrada
		WeatherInput input1 = new WeatherInput();
		ForecastInput input2 = new ForecastInput();

		SpotifyHitsByCountryInput input3 = new SpotifyHitsByCountryInput();

		TwitterSearchInput input4 = new TwitterSearchInput();

		TopRetweetsProcess process = new TopRetweetsProcess();
		TopTracksProcess process1 = new TopTracksProcess();

		//Componente de salida
		SlackChannelMessageOutput output = new SlackChannelMessageOutput();

		//Flujo de información

		List<Document> listDocument = input1.execute(weatherConfig);
		List<Document> listDocument2 = input2.execute(weatherConfig);
		List<Document> listDocument3 = input3.execute(spotifyConfig);
		List<Document> listDocument4 = input4.execute(twitterConfig);

		//procesamiento de los tweets
		listDocument4 = process.execute(listDocument4, twitterConfig);
		//procesamiento de las canciones
		listDocument3 = process1.execute(listDocument3, spotifyConfig);

		listDocument.addAll(listDocument2);
		listDocument.addAll(listDocument3);
		listDocument.addAll(listDocument4);

		//envio de salida

		output.execute(listDocument, slackConfig);

	}

}
