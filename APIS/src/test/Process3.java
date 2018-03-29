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
import processingComponents.FeedbackTweetsProcess;
import processingComponents.TimeIntervalTweetsProcess;

public class Process3 {

	public static void main(String[] args) {
		
		Map <String, String> configuration = new HashMap<String,String>();
		
		configuration.put("emailList", "i32gaduj@uco.es");	//Lista de emails("a@uco.es,b@uco.es[...]")
		configuration.put("subject", "Prueba proceso 3");	//Asunto del mensaje ( para enviar correos )
		
		//Componente de entrada
		TwitterOwnTimelineInput input = new TwitterOwnTimelineInput();
		//Componentes de procesamiento:
		//Para coger los de la semana anterior se pondría 1(1 dia antes) y 8 (8 días antes)
		TimeIntervalTweetsProcess process1 = new TimeIntervalTweetsProcess(1,8);
		FeedbackTweetsProcess process2 = new FeedbackTweetsProcess();
		//Componente de salida
		EmailOutput output = new EmailOutput();

		//Flujo de información
		
		output.execute(
				process2.execute(
						process1.execute(input.execute(configuration), configuration), configuration), configuration);
	}

}
