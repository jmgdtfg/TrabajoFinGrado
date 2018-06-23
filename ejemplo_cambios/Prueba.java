


import java.util.HashMap;

import java.util.Map;


import inputComponents.*;		//Todos los componentes de entrada
import outputComponents.*;		//Todos los componentes de salida
import pipeline.Pipeline;
import processingComponents.*;	//Todos los componentes de procesamiento


//Ejemplo de un flujo de información completo
public class Prueba {

	@SuppressWarnings("unchecked")
	public static void main(String[] args){

		//Mapas de configuración

		Map <String, String> slackConfig = new HashMap<String,String>();
		Map <String, String> rssConfig2 = new HashMap<String,String>();


		slackConfig.put("channelName", "general"); 				//Nombre del canal de slack
		slackConfig.put("slackUserMail", "jmgd_3@outlook.com");	//Email de un usuario en slack
		slackConfig.put("top", "5");
		
	
		
		rssConfig2.put("rssUrl", "http://estaticos.marca.com/rss/futbol/primera-division.xml"); //URL del canal RSS
		rssConfig2.put("limit", "90");
		rssConfig2.put("intervalEnd", "1");	//Valor del fin de un intervalo de tiempo ( en dias )
		rssConfig2.put("intervalStart", "20");//Valor del inicio de un intervalo de tiempo ( en dias )
		rssConfig2.put("author", "abc");//Valor del inicio de un intervalo de tiempo ( en dias )
		
		
		//INPUT
		RssInput input = new RssInput();
		input.setConfiguration(rssConfig2);
		//PROCESS
		TimeIntervalRssProcess process = new TimeIntervalRssProcess();
		process.setConfiguration(rssConfig2);
		//OUTPUT
		SlackChannelMessageOutput output = new SlackChannelMessageOutput();
		output.setConfiguration(slackConfig);
		
				
		// USO DE PIPELINE
		Pipeline pipeline = new Pipeline();
		
		pipeline.addInput(input);
		pipeline.addProcess(process);
		pipeline.addOutput(output);

		pipeline.execute();
		
		
	}
}