package mail;

import java.util.Properties;
/*La clase ConfigurationHotmail coge los valores de configuracion comunes de
 * la clase Configuration y a�ade los valores de configuraci�n propios
 * de una conexi�n a hotmail.
 */
public class ConfigurationHotmail extends Configuration{
	private Properties props_ = new Properties();
	private String servidor_ = "imap-mail.outlook.com";
	
	ConfigurationHotmail(){
		//A�adimos todas las propiedades b�sicas de configuraci�n
		props_ = getProperties();
		//Se a�aden las propiedades espec�ficas de gmail
		this.props_.put("mail.smtp.host", "smtp.live.com");
		this.props_.put("mail.smtp.port", "25");
	}
	
	public Properties getPropertiesHotmail(){
		return props_;
	}
	public String getServer(){
		return servidor_;
	}

}
