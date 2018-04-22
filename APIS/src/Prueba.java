

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import document.Document;
import inputComponents.*;		//Todos los componentes de entrada
import outputComponents.*;		//Todos los componentes de salida
import processingComponents.*;	//Todos los componentes de procesamiento
import rss.RssClient;

//Ejemplo de un flujo de información completo
public class Prueba {

	public static void main(String[] args){

		//Mapas de configuración

		Map <String, String> twitterConfig = new HashMap<String,String>();
		Map <String, String> spotifyConfig = new HashMap<String,String>();
		Map <String, String> githubConfig = new HashMap<String,String>();
		Map <String, String> mailConfig = new HashMap<String,String>();
		Map <String, String> slackConfig = new HashMap<String,String>();
		Map <String, String> weatherConfig = new HashMap<String,String>();
		Map <String, String> rssConfig = new HashMap<String,String>();
		Map <String, String> sheetConfig = new HashMap<String,String>();




		
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
		
		
		//****PARAMETROS DE CONFIGURACIÓN ÚNICOS DE EMAIL
		
		//Esta key solo funciona correctamente con los valores "content","sender" y "subject"
		mailConfig.put("mailSearchType", "subject");
		mailConfig.put("emailList", "i32gaduj@uco.es");	//Lista de emails("a@uco.es,b@uco.es[...]")
		mailConfig.put("subject", "probando...");		//Asunto del mensaje ( para enviar correos )
		mailConfig.put("user", "jmgdtfg@gmail.com");		//Cuenta que se usa
		mailConfig.put("password", "tfg_pass");			//Contraseña de la cuenta
		mailConfig.put("server", "gmail");				//Tipo de servidor ( disponible gmail y hotmail)
		mailConfig.put("word", "prueba");				//Palabra de búsqueda
		
		//****PARAMETROS DE CONFIGURACIÓN ÚNICOS DE SPOTIFY
		
		//Esta key solo funciona correctamente los valores "tracks","albums","artists" y "playlists"
		spotifyConfig.put("spotifySearchType", "tracks");		
		spotifyConfig.put("idArtist", "3bgsNtcf5d5h9jbQbohfBK");	//Id de extremoduro en spotify
		spotifyConfig.put("idTrack", "1KPLNOTQDSWe68ea6JUjpx");		//Id de "la vereda de la puerta de atras"
		spotifyConfig.put("genre", "rock");							//Genero musical
		spotifyConfig.put("IdUserSpotify", "spotifycharts");			//Id de usuario
		spotifyConfig.put("IdPlaylist", "37i9dQZEVXbNFJfN1Vw8d9");	//Id de playlist
		spotifyConfig.put("word", "Top 50");
		spotifyConfig.put("country", "Alemania");						
		spotifyConfig.put("top", "5");
		
		
		//****PARAMETROS DE CONFIGURACIÓN ÚNICOS DE GITHUB
		githubConfig.put("repositoryUrl", "https://github.com/jmgdtfg/TFG/");//URL de un repositorio
		githubConfig.put("localPath", "C:/Users/jmgd_/Desktop/descarga");//Ruta local donde se descarga un repositorio
		githubConfig.put("searchLanguage", "Java");//Se utiliza para buscar resultados con un lenguaje de programación concreto.
		githubConfig.put("gistName", "---GIST---");//Nombre de un Gist
		githubConfig.put("gistDescription", "Gist de prueba...");//Descripción del Gist
		githubConfig.put("word", "la");//Palabra de búsqueda
		
		//****PARAMETROS DE CONFIGURACIÓN ÚNICOS DE SLACK
		slackConfig.put("channelName", "general"); 				//Nombre del canal de slack
		slackConfig.put("slackUserMail", "jmgd_3@outlook.com");	//Email de un usuario en slack
		
		//****PARAMETROS DE CONFIGURACIÓN ÚNICOS DE RSS
		rssConfig.put("rssUrl", "http://ep00.epimg.net/rss/elpais/portada.xml"); //URL del canal RSS
		rssConfig.put("limit", "5");
		
		//****PARAMETROS DE CONFIGURACIÓN ÚNICOS DE OPENWEATHERMAP
		weatherConfig.put("weatherCity", "Sevilla");				//Ciudad de la que se obtendrán los datos
		
		//****PARAMETROS DE CONFIGURACIÓN ÚNICOS DE SPREADSHEET
		sheetConfig.put("sheetName", "Datos");					//Nombre de la hoja de cálculo
		
		//EJEMPLO
		//MailSearchInboxInput input = new MailSearchInboxInput();// no va
		//RepositoryInformationProcess process = new RepositoryInformationProcess();
		//EmailOutput output = new EmailOutput();
		
		SlackUsersListInput input = new SlackUsersListInput();

		SpreadSheetInput input2 = new SpreadSheetInput();
		SlackChannelMessageOutput output = new SlackChannelMessageOutput();

		output.execute(input2.execute(sheetConfig), slackConfig);
		
	}
}