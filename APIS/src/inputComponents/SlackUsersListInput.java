package inputComponents;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import document.Document;
import document.StringDocument;
import slack.SlackManager;

public class SlackUsersListInput implements InputComponent{
	//Función que devuelve los usuarios
	@Override
	public List<Document> execute(Map<String, String> configuration) {

		List<Document> listDocument = new ArrayList<Document>();
		try {
			SlackManager sm = new SlackManager();	
			for (String data : sm.getUsers()){	
				StringDocument document = new StringDocument();	
				document.setRawData(data);
				listDocument.add(document);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return listDocument;
		
	}

}
