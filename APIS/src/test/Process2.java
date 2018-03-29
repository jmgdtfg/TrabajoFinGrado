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
import processingComponents.RepositoryInformationProcess;

public class Process2 {

	public static void main(String[] args) {
		
		Map <String, String> configuration = new HashMap<String,String>();

		configuration.put("repositoryUrl", "https://github.com/jmgdtfg/TrabajoFinGrado/");//URL de un repositorio
		configuration.put("localPath", "C:/Users/jmgd_/Desktop/descarga");//Ruta local donde se descarga un repositorio
		configuration.put("emailList", "i32gaduj@uco.es");	//Lista de emails("a@uco.es,b@uco.es[...]")
		configuration.put("subject", "Prueba proceso 2");		//Asunto del mensaje ( para enviar correos )

		//Componente de entrada
		GithubRepositoryInput input = new GithubRepositoryInput();
		//Componente de procesamiento
		RepositoryInformationProcess process = new RepositoryInformationProcess();
		//Componentes de salida: twitter y email
		EmailOutput output1 = new EmailOutput();
		TwitterOutput output2 = new TwitterOutput();
		
		//Se realiza el flujo de información.
		output1.execute(process.execute(input.execute(configuration), configuration), configuration);
		output2.execute(process.execute(input.execute(configuration), configuration), configuration);

	}

}
