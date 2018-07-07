/*Proceso que proporciona informaci�n general sobre una ciudad o pa�s
 * y lo publica en un canal de slack
- Resumen del tiempo
- Top10 de las canciones m�s escuchadas en el pa�s
- Top10 de los tweets con la palabra C�rdoba m�s importantes
- Publicar resultado en un canal de Slack
 */

package test;

import java.util.HashMap;
import java.util.Map;


import inputComponents.ForecastInput;
import inputComponents.SpotifyHitsByCountryInput;
import inputComponents.TwitterSearchInput;
import inputComponents.WeatherInput;
import outputComponents.SlackChannelMessageOutput;
import pipeline.Pipeline;
import processingComponents.TopRetweetsProcess;
import processingComponents.TopTracksProcess;

public class Process4 {

	public static void main(String[] args) {

		Map <String, String> twitterConfig = new HashMap<String,String>();
		Map <String, String> spotifyConfig = new HashMap<String,String>();
		Map <String, String> slackConfig = new HashMap<String,String>();
		Map <String, String> weatherConfig = new HashMap<String,String>();


		//****PARAMETROS DE CONFIGURACI�N �NICOS DE TWITTER

		//Esta key solo funciona correctamente con los valores "hashtag" y "word"
		twitterConfig.put("twitterSearchType", "hashtag");	
		twitterConfig.put("hashtag", "Madrid");			//Hashtag clave para realizar b�squeda
		twitterConfig.put("top", "5");
		twitterConfig.put("word", "la");
		twitterConfig.put("days", "20");//Parametro de busqueda
		twitterConfig.put("results", "1000");//Parametro de busqueda
		twitterConfig.put("country", "Spain");
		twitterConfig.put("intervalEnd", "1");	//Valor del fin de un intervalo de tiempo ( en dias )
		twitterConfig.put("intervalStart", "15");//Valor del inicio de un intervalo de tiempo ( en dias ) 



		//****PARAMETROS DE CONFIGURACI�N �NICOS DE SPOTIFY

		//Esta key solo funciona correctamente los valores "tracks","albums","artists" y "playlists"
		spotifyConfig.put("spotifySearchType", "tracks");		
		spotifyConfig.put("idArtist", "3bgsNtcf5d5h9jbQbohfBK");	//Id de extremoduro en spotify
		spotifyConfig.put("idTrack", "1KPLNOTQDSWe68ea6JUjpx");		//Id de "la vereda de la puerta de atras"
		spotifyConfig.put("genre", "rock");							//Genero musical
		spotifyConfig.put("IdUserSpotify", "spotifycharts");			//Id de usuario
		spotifyConfig.put("IdPlaylist", "37i9dQZEVXbNFJfN1Vw8d9");	//Id de playlist
		spotifyConfig.put("word", "Top 50");
		spotifyConfig.put("country", "Espa�a");						
		spotifyConfig.put("top", "5");


		//****PARAMETROS DE CONFIGURACI�N �NICOS DE SLACK
		slackConfig.put("channelName", "general"); 				//Nombre del canal de slack
		slackConfig.put("slackUserMail", "jmgd_3@outlook.com");	//Email de un usuario en slack


		//****PARAMETROS DE CONFIGURACI�N �NICOS DE OPENWEATHERMAP
		weatherConfig.put("weatherCity", "Sevilla");				//Ciudad de la que se obtendr�n los datos



		//Componentes de entrada
		WeatherInput input1 = new WeatherInput();
		input1.setConfiguration(weatherConfig);
		ForecastInput input2 = new ForecastInput();
		input2.setConfiguration(weatherConfig);
		SpotifyHitsByCountryInput input3 = new SpotifyHitsByCountryInput();
		input3.setConfiguration(spotifyConfig);
		TwitterSearchInput input4 = new TwitterSearchInput();
		input4.setConfiguration(twitterConfig);
		TopRetweetsProcess process = new TopRetweetsProcess();
		process.setConfiguration(twitterConfig);
		TopTracksProcess process1 = new TopTracksProcess();
		process1.setConfiguration(spotifyConfig);
		//Componente de salida
		SlackChannelMessageOutput output = new SlackChannelMessageOutput();
		output.setConfiguration(slackConfig);
		//Flujo de informaci�n
		Pipeline pipeline = new Pipeline();
		pipeline.addInput(input1,input2,input3,input4);
		pipeline.addProcess(process,process1);
		pipeline.addOutput(output);
		
		pipeline.execute();

	}

}
