package mail;

import java.util.Properties;
/*La clase ConfigurationGmail coge los valores de configuracion comunes de
 * la clase Configuration y a�ade los valores de configuraci�n propios
 * de una conexi�n a gmail.
 */
public class ConfigurationGmail extends Configuration{
	
	private Properties props_ = new Properties();
	private String servidor_ = "imap.gmail.com";
	
	ConfigurationGmail(){
		//A�adimos todas las propiedades b�sicas de configuraci�n
		props_ = getProperties();
		//Se a�aden las propiedades espec�ficas de gmail
		props_.put("mail.smtp.host", "smtp.gmail.com");
		props_.put("mail.smtp.port", "587");
	}
	
	public Properties getPropertiesGmail(){
		return props_;
	}
	public String getServer(){
		return servidor_;
	}

}
