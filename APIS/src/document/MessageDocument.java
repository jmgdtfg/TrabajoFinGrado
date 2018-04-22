package document;

import mail.Mensaje;

public class MessageDocument extends Document{

	@Override
	public String getDataAsString() {

		String message = "";
		Mensaje data = (Mensaje) getRawData();
		message = "Enviado por: "+data.getFrom_()+"\n";
		message += "Asunto: "+data.getSubject_()+"\n";
		message += "Texto: "+data.getMessage_();

		return message;
	}

}
