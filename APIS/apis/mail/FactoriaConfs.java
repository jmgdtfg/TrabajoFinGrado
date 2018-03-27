package mail;

import java.util.Properties;
/*La clase FactoriaConfs sirve elegir el servidor del que se recibir� la
 * configuraci�n en funci�n de un par�metro de entrada con el nombre 
 * del tipo de servidor.
 * */
public class FactoriaConfs {
	//Funci�n que devuelve la configuraci�n 
	public Properties getProperties(String servidor){

		if (servidor.equals("gmail")) {

			ConfigurationGmail cg = new ConfigurationGmail();
			return cg.getPropertiesGmail();
			
		}
		else if (servidor.equals("hotmail")){
			
			ConfigurationHotmail ch = new ConfigurationHotmail();
			return ch.getPropertiesHotmail();
			
		}
		/*else if(servidor.equals("..."){
		 * ...
		 * ...
		 * }
		 * */
		else{
			return null;
		}

	}
	//Funci�n que devuelve el servidor
	public String getServer(String servidor){
		
		if(servidor.equals("gmail")){
			ConfigurationGmail cg = new ConfigurationGmail();
			return cg.getServer();
			
		}
		else if (servidor.equals("hotmail")){
			
			ConfigurationHotmail ch = new ConfigurationHotmail();
			return ch.getServer();
			
		}
		/*else if(servidor.equals("..."){
		 * ...
		 * ...
		 * }
		 * */
		else{
			return null;
		}
		
	}

}
