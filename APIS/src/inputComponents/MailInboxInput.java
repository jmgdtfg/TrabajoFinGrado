package inputComponents;

import java.util.Map;

import mail.MailManager;

public class MailInboxInput implements InputComponent{

	private String user_;		//Correo del usuario
	private String password_;	//Contrase�a de la cuenta
	private String server_;		//Servidor. V�lido => "gmail"(por defecto) y "hotmail"

	//Constructor por defecto
	public MailInboxInput(){};

	//Constructor parametrizado
	public MailInboxInput(String user, String password, String server){
		user_ = user;
		password_ = password;
		server_ = server;
	}

	//Funci�n que devuelve la bandeja de entrada
	@Override
	public Object execute(Map<String, String> configuration) {

		if (user_.isEmpty()){
			MailManager mm = new MailManager();
			//Devuelve el objeto Message[]
			return mm.getInbox();
		}
		else{
			MailManager mm = new MailManager(user_,password_,server_);
			//Devuelve el objeto Message[]
			return mm.getInbox();
		}

	}

}
