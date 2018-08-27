package outputComponents;

import java.util.List;
import java.util.Map;

import document.Document;
import mail.MailManager;

public class EmailOutput implements OutputComponent {
	private Map<String, String> configuration_;
	@Override
	public Map<String, String> getConfiguration() {
		return configuration_;
	}

	@Override
	public void setConfiguration(Map<String, String> configuration) {
		configuration_ = configuration;
	} 
	@Override
	public void execute(List<Document> data, Map<String, String> configuration) {		
		MailManager mm = new MailManager(
				configuration.get("user"),
				configuration.get("password"),
				configuration.get("server"));
		String message = "";
		
		for (Document document : data) {
			message += document.getDataAsString();
			message += "\n";
		}
		message += "--------------------------------\n";
		
		mm.sendEmail(configuration.get("emailList"), configuration.get("subject"), message);		
	}

}
