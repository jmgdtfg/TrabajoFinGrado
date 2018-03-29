/*Proceso que proporciona información general sobre una ciudad o país
 * y lo publica en un canal de slack
- Resumen del tiempo
- Top10 de las canciones más escuchadas en el país
- Top10 de los tweets con la palabra Córdoba más importantes
- Publicar resultado en un canal de Slack
 */

package test;

import java.util.HashMap;
import java.util.Map;

import inputComponents.SpotifyPlaylistInput;
import inputComponents.TwitterSearchInput;
import inputComponents.WeatherInput;
import outputComponents.SlackChannelMessageOutput;
import processingComponents.TopPlaylistTracksProcess;
import processingComponents.TopRetweetsProcess;
import processingComponents.WeatherSummaryProcess;

public class Process4 {

	public static void main(String[] args) {
		
		Map <String, String> configuration = new HashMap<String,String>();
		
		configuration.put("weatherCity", "Sevilla");		//Ciudad de la que se obtendrán los datos
		configuration.put("IdUserSpotify", "kolibrimusic");			//Id de usuario
		configuration.put("IdPlaylist", "1vc0RKeYOU1wFt3cVd9TkO");	//Id de playlist
		configuration.put("twitterSearchType", "word");	
		configuration.put("word", "Córdoba");
		configuration.put("channelName", "general");
		
		//Componentes de entrada
		WeatherInput input1 = new WeatherInput();
		//A este componente de entrada se le especificará en la configuración que obtenga la
		//playlist de top 50 país
		SpotifyPlaylistInput input2 = new SpotifyPlaylistInput();
													//(dias,resultados)
		TwitterSearchInput input3 = new TwitterSearchInput(15,1000);
		
		//Componentes de procesamiento
		WeatherSummaryProcess process1 = new WeatherSummaryProcess();
		//Se le pasa por parámetro el número de resultados del top
		TopPlaylistTracksProcess process2 = new TopPlaylistTracksProcess(10);
		//Se le pasa por parámetro el número de resultados del top
		TopRetweetsProcess process3 = new TopRetweetsProcess(10);
		
		//Componente de salida
		SlackChannelMessageOutput output = new SlackChannelMessageOutput();
		
		//Flujo de información

		String r1 = (String) process1.execute(input1.execute(configuration), configuration);
		String r2 = (String) process2.execute(input2.execute(configuration), configuration);
		String r3 = (String) process3.execute(input3.execute(configuration), configuration);
		
		output.execute(r1+r2+r3, configuration);
	}

}
