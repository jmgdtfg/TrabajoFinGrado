/* 
 * Este proceso se encarga de obtener un numero relevante de tweets de un determinado
 * hashtag. Despu�s selecciona el top 5 de tweets m�s retuiteados y el top 5 de tweets
 * con m�s veces marcados como favoritos(equivalente a me gusta). Por �ltimo env�a los 
 * resultados a una  determinada cuenta de email
 * - Selecciona los del �ltimo mes
 */

package test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import document.Document;
import inputComponents.TwitterSearchInput;
import outputComponents.EmailOutput;
import processingComponents.TopLikesProcess;
import processingComponents.TopRetweetsProcess;

public class Process1 {

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
		twitterConfig.put("intervalStart", "30");//Valor del inicio de un intervalo de tiempo ( en dias ) 

		//Se le pasan por par�metro los d�as y los resultados al componente de entrada
		TwitterSearchInput input = new TwitterSearchInput();
		//Se le pasa como par�metro el n�mero de resultados del top.
		TopLikesProcess process1 = new TopLikesProcess();
		//Se le pasa como par�metro el n�mero de resultados del top.
		TopRetweetsProcess process2 = new TopRetweetsProcess();
		//Se declara el componente de salida
		EmailOutput output = new EmailOutput();
		
		//Se realiza el flujo de datos.

		List<Document> listDocument = process1.execute(input.execute(twitterConfig), twitterConfig);
		List<Document> listDocument2 = process2.execute(input.execute(twitterConfig), twitterConfig);
	
		listDocument.addAll(listDocument2);
		output.execute(listDocument, mailConfig);
		
	}

}
