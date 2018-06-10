package document;

import mail.MessageData;

public class MessageDocument extends Document{

	@Override
	public String getDataAsString() {

		String message = "";
		MessageData data = (MessageData) getRawData();
		message = "Enviado por: "+data.getFrom_()+"\n";
		message += "Asunto: "+data.getSubject_()+"\n";
		message += "Texto: "+data.getMessage_();

		return message;
	}

}
