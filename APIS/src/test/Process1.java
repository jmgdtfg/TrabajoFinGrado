/* 
 * Este proceso se encarga de obtener un numero relevante de tweets de un determinado
 * hashtag. Después selecciona el top 5 de tweets más retuiteados y el top 5 de tweets
 * con más veces marcados como favoritos(equivalente a me gusta). Por último envía los 
 * resultados a una  determinada cuenta de email
 * - Selecciona los del último mes
 */

package test;

import java.util.HashMap;
import java.util.Map;

import inputComponents.TwitterSearchInput;
import outputComponents.EmailOutput;
import processingComponents.TopLikesProcess;
import processingComponents.TopRetweetsProcess;

public class Process1 {

	public static void main(String[] args) {
		
		Map <String, String> configuration = new HashMap<String,String>();

		configuration.put("twitterSearchType", "hashtag");	//Tipo de búsqueda
		configuration.put("hashtag", "Sevilla");			//Hashtag
		configuration.put("emailList", "i32gaduj@uco.es");	//Lista de emails("a@uco.es,b@uco.es[...]")
		configuration.put("subject", "Prueba proceso 1");	//Asunto del mensaje ( para enviar correos )

		//Se le pasan por parámetro los días y los resultados al componente de entrada
		TwitterSearchInput input = new TwitterSearchInput(100,1000);
		//Se le pasa como parámetro el número de resultados del top.
		TopLikesProcess process1 = new TopLikesProcess(5);
		//Se le pasa como parámetro el número de resultados del top.
		TopRetweetsProcess process2 = new TopRetweetsProcess(5);
		//Se declara el componente de salida
		EmailOutput output = new EmailOutput();
		
		//Se realiza el flujo de datos.
		String r1 = (String) process1.execute(input.execute(configuration), configuration);
		String r2 = (String) process2.execute(input.execute(configuration), configuration);
	
		output.execute(r1+r2, configuration);
		
	}

}
