


import java.io.IOException;
import java.util.HashMap;

import java.util.Map;
import java.util.Map.Entry;

import inputComponents.*;		//Todos los componentes de entrada
import jackson.JacksonUtils;
import outputComponents.*;		//Todos los componentes de salida
import pipeline.Pipeline;
import pipeline.ProcessAvailability;
import processingComponents.*;	//Todos los componentes de procesamiento


//Ejemplo de un flujo de información completo
public class Prueba {

	@SuppressWarnings("unchecked")
	public static void main(String[] args){

		//Mapas de configuración

		Map <String, String> twitterConfig = new HashMap<String,String>();
		Map <String, String> spotifyConfig = new HashMap<String,String>();
		Map <String, String> githubConfig = new HashMap<String,String>();
		Map <String, String> mailConfig = new HashMap<String,String>();
		Map <String, String> slackConfig = new HashMap<String,String>();
		Map <String, String> weatherConfig = new HashMap<String,String>();
		Map <String, String> rssConfig = new HashMap<String,String>();
		Map <String, String> rssConfig2 = new HashMap<String,String>();
		Map <String, String> sheetConfig = new HashMap<String,String>();
		Map <String, String> otherConfig = new HashMap<String,String>();
		Map <String, String> translatorConfig = new HashMap<String,String>();



		
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
		twitterConfig.put("intervalStart", "8");//Valor del inicio de un intervalo de tiempo ( en dias ) 
		twitterConfig.put("user", "policia");
		
		//****PARAMETROS DE CONFIGURACIÓN ÚNICOS DE EMAIL
		
		//Esta key solo funciona correctamente con los valores "content","sender" y "subject"
		mailConfig.put("mailSearchType", "subject");
		mailConfig.put("emailList", "i32gaduj@uco.es");	//Lista de emails("a@uco.es,b@uco.es[...]")
		mailConfig.put("subject", "probando...");		//Asunto del mensaje ( para enviar correos )
		mailConfig.put("user", "jmgdtfg@gmail.com");		//Cuenta que se usa
		mailConfig.put("password", "tfg_pass");			//Contraseña de la cuenta
		mailConfig.put("server", "gmail");				//Tipo de servidor ( disponible gmail y hotmail)
		mailConfig.put("word", "prueba");				//Palabra de búsqueda
		mailConfig.put("top", "5");				//Palabra de búsqueda
		mailConfig.put("intervalEnd", "1");	//Valor del fin de un intervalo de tiempo ( en dias )
		mailConfig.put("intervalStart", "80");//Valor del inicio de un intervalo de tiempo ( en dias )
		
		//****PARAMETROS DE CONFIGURACIÓN ÚNICOS DE SPOTIFY
		
		//Esta key solo funciona correctamente los valores "tracks","albums","artists" y "playlists"
		spotifyConfig.put("spotifySearchType", "tracks");		
		spotifyConfig.put("idArtist", "3bgsNtcf5d5h9jbQbohfBK");	//Id de extremoduro en spotify
		spotifyConfig.put("idTrack", "1KPLNOTQDSWe68ea6JUjpx");		//Id de "la vereda de la puerta de atras"
		spotifyConfig.put("genre", "rock");							//Genero musical
		spotifyConfig.put("IdUserSpotify", "spotifycharts");		//Id de usuario
		spotifyConfig.put("IdPlaylist", "37i9dQZEVXbNFJfN1Vw8d9");	//Id de playlist
		spotifyConfig.put("word", "Top 50");
		spotifyConfig.put("country", "Alemania");						
		spotifyConfig.put("topTracks", "5");
		spotifyConfig.put("topArtists", "5");
		
		
		//****PARAMETROS DE CONFIGURACIÓN ÚNICOS DE GITHUB
		githubConfig.put("repositoryUrl", "https://github.com/jmgdtfg/TFG/");//URL de un repositorio
		githubConfig.put("localPath", "C:/Users/jmgd_/Desktop/descarga");//Ruta local donde se descarga un repositorio
		githubConfig.put("searchLanguage", "Java");//Se utiliza para buscar resultados con un lenguaje de programación concreto.
		githubConfig.put("gistName", "---GIST---");//Nombre de un Gist
		githubConfig.put("gistDescription", "Gist de prueba...");//Descripción del Gist
		githubConfig.put("word", "la");//Palabra de búsqueda
		githubConfig.put("intervalEnd", "1");	//Valor del fin de un intervalo de tiempo ( en dias )
		githubConfig.put("intervalStart", "200");//Valor del inicio de un intervalo de tiempo ( en dias )
		githubConfig.put("minSize", "1");	//Valor del fin de un intervalo de tamaño ( en ficheros )
		githubConfig.put("maxSize", "150");//Valor del inicio de un intervalo de tamaño ( en ficheros )
		githubConfig.put("author", "jm");//Nombre del autor

		
		//****PARAMETROS DE CONFIGURACIÓN ÚNICOS DE SLACK
		slackConfig.put("channelName", "general"); 				//Nombre del canal de slack
		slackConfig.put("slackUserMail", "jmgd_3@outlook.com");	//Email de un usuario en slack
		slackConfig.put("top", "5");
		
		//****PARAMETROS DE CONFIGURACIÓN ÚNICOS DE RSS
		rssConfig.put("rssUrl", "http://www.abc.es/rss/feeds/abc_EspanaEspana.xml"); //URL del canal RSS
		rssConfig.put("limit", "5");
		rssConfig.put("intervalEnd", "1");	//Valor del fin de un intervalo de tiempo ( en dias )
		rssConfig.put("intervalStart", "20");//Valor del inicio de un intervalo de tiempo ( en dias )
		rssConfig.put("author", "abc");//Valor del inicio de un intervalo de tiempo ( en dias )
		
		//Otros parametros de configuracion para RSS
		
		rssConfig2.put("rssUrl", "http://estaticos.marca.com/rss/futbol/primera-division.xml"); //URL del canal RSS
		rssConfig2.put("limit", "90");
		rssConfig2.put("intervalEnd", "1");	//Valor del fin de un intervalo de tiempo ( en dias )
		rssConfig2.put("intervalStart", "20");//Valor del inicio de un intervalo de tiempo ( en dias )
		rssConfig2.put("author", "abc");//Valor del inicio de un intervalo de tiempo ( en dias )
		
		
		
		//****PARAMETROS DE CONFIGURACIÓN ÚNICOS DE OPENWEATHERMAP
		weatherConfig.put("weatherCity", "Madrid");				//Ciudad de la que se obtendrán los datos
		weatherConfig.put("weatherCondition", "lluvia");
		weatherConfig.put("tempMax", "23");	
		weatherConfig.put("tempMin", "20");	
		weatherConfig.put("humidityMax", "55");	
		weatherConfig.put("humidityMin", "0");
		weatherConfig.put("cloudinessMax", "15");	
		weatherConfig.put("cloudinessMin", "0");
		
		//****PARAMETROS DE CONFIGURACIÓN ÚNICOS DE SPREADSHEET
		sheetConfig.put("sheetName", "Prueba8");					//Nombre de la hoja de cálculo
		sheetConfig.put("initRow", "5");						//Fila a partir de la cual se empezará a escribir
		sheetConfig.put("maxColumn", "2");						//Número de columnas que se usarán
		
		//****PARAMETROS DE CONFIGURACIÓN ÚNICOS DE TRANSLATOR
		translatorConfig.put("language", "italian");	
		
		//OTROS
		otherConfig.put("top", "10");
		otherConfig.put("key", "a");	
		
		
		//INPUT
		RssInput input = new RssInput();
		ForecastInput input2 = new ForecastInput();
		
		input.setConfiguration(rssConfig2);
		//PROCESS
		TimeIntervalRssProcess process = new TimeIntervalRssProcess();
		process.setConfiguration(rssConfig2);
		GetNumberOfMatchesGenericProcess processX = new GetNumberOfMatchesGenericProcess();
		processX.setConfiguration(otherConfig);
		FilterRepositoriesByAuthorProcess process2 = new FilterRepositoriesByAuthorProcess();
		process.setConfiguration(githubConfig);
		//OUTPUT
		SlackChannelMessageOutput output = new SlackChannelMessageOutput();
		output.setConfiguration(slackConfig);
		
				
		// USO DE PIPELINE
		Pipeline pipeline = new Pipeline();
		
//		pipeline.addInput(input);
//		pipeline.addProcess(process, processX);
//		pipeline.addOutput(output);
//
//		//Ejemplo serialización
//		String json = JacksonUtils.serialize(pipeline);
//		System.out.println("JSON de prueba:"+ json);
//		//Ejemplo deserializacion
//		Pipeline pipeline2 = new Pipeline();
//		pipeline2 = JacksonUtils.deserialize(json);
//		
//		pipeline2.execute();
		ProcessAvailability availability = new ProcessAvailability();
		Map<String, Integer> map = availability.check(input2.getDocument());
		for (Entry<String, Integer> entry : map.entrySet())
		{
		    System.out.println(entry.getKey() + "/" + entry.getValue());
		}
	}
}