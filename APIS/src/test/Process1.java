/* 
 * Este proceso se encarga de obtener un numero relevante de tweets de un determinado
 * hashtag. Despu�s selecciona el top 5 de tweets m�s retuiteados y el top 5 de tweets
 * con m�s veces marcados como favoritos(equivalente a me gusta). Por �ltimo env�a los 
 * resultados a una  determinada cuenta de email
 * - Selecciona los del �ltimo mes
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

		configuration.put("twitterSearchType", "hashtag");	//Tipo de b�squeda
		configuration.put("hashtag", "Sevilla");			//Hashtag
		configuration.put("emailList", "i32gaduj@uco.es");	//Lista de emails("a@uco.es,b@uco.es[...]")
		configuration.put("subject", "Prueba proceso 1");	//Asunto del mensaje ( para enviar correos )

		//Se le pasan por par�metro los d�as y los resultados al componente de entrada
		TwitterSearchInput input = new TwitterSearchInput(100,1000);
		//Se le pasa como par�metro el n�mero de resultados del top.
		TopLikesProcess process1 = new TopLikesProcess(5);
		//Se le pasa como par�metro el n�mero de resultados del top.
		TopRetweetsProcess process2 = new TopRetweetsProcess(5);
		//Se declara el componente de salida
		EmailOutput output = new EmailOutput();
		
		//Se realiza el flujo de datos.
		String r1 = (String) process1.execute(input.execute(configuration), configuration);
		String r2 = (String) process2.execute(input.execute(configuration), configuration);
	
		output.execute(r1+r2, configuration);
		
	}

}
