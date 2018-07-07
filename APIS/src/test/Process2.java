/*Este proceso proporciona un resumen de un proyecto GitHub
- 1)Obtener un repositorio dada su URL
- 2)Contar el número de archivos de cada tipo que existen. Ejemplo: 10 archivos .java, 2 .xml...
- 3)Enviar resultado del informe por email y publicar un tweet
 */

package test;

import java.util.HashMap;
import java.util.Map;

import inputComponents.GithubRepositoryInput;
import outputComponents.EmailOutput;
import outputComponents.TwitterOutput;
import pipeline.Pipeline;
import processingComponents.RepositoryInformationProcess;

public class Process2 {

	public static void main(String[] args) {
		
		Map <String, String> githubConfig = new HashMap<String,String>();
		//Map <String, String> twitterConfig = new HashMap<String,String>();
		Map <String, String> mailConfig = new HashMap<String,String>();
		githubConfig.put("repositoryUrl", "https://github.com/jmgdtfg/TrabajoFinGrado/");//URL de un repositorio
		githubConfig.put("localPath", "C:/Users/jmgd_/Desktop/descarga");//Ruta local donde se descarga un repositorio
		mailConfig.put("emailList", "i32gaduj@uco.es");	//Lista de emails("a@uco.es,b@uco.es[...]")
		mailConfig.put("subject", "Prueba proceso 2");		//Asunto del mensaje ( para enviar correos )
		mailConfig.put("user", "jmgdtfg@gmail.com");		//Cuenta que se usa
		mailConfig.put("password", "tfg_pass");			//Contraseña de la cuenta
		mailConfig.put("server", "gmail");				//Tipo de servidor ( disponible gmail y hotmail)
		//Componente de entrada
		GithubRepositoryInput input = new GithubRepositoryInput();
		input.setConfiguration(githubConfig);
		//Componente de procesamiento
		RepositoryInformationProcess process = new RepositoryInformationProcess();
		process.setConfiguration(githubConfig);
		//Componentes de salida: twitter y email
		EmailOutput output1 = new EmailOutput();
		output1.setConfiguration(mailConfig);
		TwitterOutput output2 = new TwitterOutput();
		//output2.setConfiguration(configuration);
		
		//Se realiza el flujo de información.
		
		Pipeline pipeline = new Pipeline();
		pipeline.addInput(input);
		pipeline.addProcess(process);
		pipeline.addOutput(output1,output2);
		
		pipeline.execute();

	}

}
