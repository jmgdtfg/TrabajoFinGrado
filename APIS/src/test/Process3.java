/*Resumen semanal del propio timeline:
Primero se obtienen los tweet del timeline
Se filtran los tweets de la semana anterior.
Se calcula el total de menciones, retweets, favoritos...
Se enviar resultado por email
 */

package test;

import java.util.HashMap;
import java.util.Map;

import inputComponents.TwitterOwnTimelineInput;
import outputComponents.EmailOutput;
import pipeline.Pipeline;
import processingComponents.FeedbackTweetsProcess;
import processingComponents.TimeIntervalTweetsProcess;

public class Process3 {

	public static void main(String[] args) {
		
		
		Map <String, String> twitterConfig = new HashMap<String,String>();
		Map <String, String> mailConfig = new HashMap<String,String>();

		mailConfig.put("mailSearchType", "subject");
		mailConfig.put("emailList", "i32gaduj@uco.es");	//Lista de emails("a@uco.es,b@uco.es[...]")
		mailConfig.put("subject", "probando...");		//Asunto del mensaje ( para enviar correos )
		mailConfig.put("user", "jmgdtfg@gmail.com");		//Cuenta que se usa
		mailConfig.put("password", "tfg_pass");			//Contrase�a de la cuenta
		mailConfig.put("server", "gmail");				//Tipo de servidor ( disponible gmail y hotmail)

		twitterConfig.put("twitterSearchType", "hashtag");	
		twitterConfig.put("hashtag", "Madrid");			//Hashtag clave para realizar b�squeda
		twitterConfig.put("top", "5");
		twitterConfig.put("word", "la");
		twitterConfig.put("days", "20");//Parametro de busqueda
		twitterConfig.put("results", "1000");//Parametro de busqueda
		twitterConfig.put("country", "Spain");
		twitterConfig.put("intervalEnd", "1");	//Valor del fin de un intervalo de tiempo ( en dias )
		twitterConfig.put("intervalStart", "8");//Valor del inicio de un intervalo de tiempo ( en dias ) 
		
		//Componente de entrada
		TwitterOwnTimelineInput input = new TwitterOwnTimelineInput();
		input.setConfiguration(twitterConfig);
		//Componentes de procesamiento:
		//Para coger los de la semana anterior se pondr�a 1(1 dia antes) y 8 (8 d�as antes)
		TimeIntervalTweetsProcess process1 = new TimeIntervalTweetsProcess();
		process1.setConfiguration(twitterConfig);
		FeedbackTweetsProcess process2 = new FeedbackTweetsProcess();
		process2.setConfiguration(twitterConfig);
		//Componente de salida
		EmailOutput output = new EmailOutput();
		output.setConfiguration(mailConfig);

		//Flujo de informaci�n
		Pipeline pipeline = new Pipeline();
		pipeline.addInput(input);
		pipeline.addProcess(process1,process2);
		pipeline.addOutput(output);
		
		pipeline.execute();
		
	}

}
