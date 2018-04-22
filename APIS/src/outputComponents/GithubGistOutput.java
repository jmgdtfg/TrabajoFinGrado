package outputComponents;

import java.util.List;
import java.util.Map;

import document.Document;
import github.GithubManager;

public class GithubGistOutput implements OutputComponent{
	//Función que publica los datos en un Gist
	@Override
	public void execute(List<Document> data, Map<String, String> configuration) {
		
		GithubManager gm = new GithubManager();
		String message = "";
		
		for (Document document : data) {
			message += document.getDataAsString();
			message += "\n";
		}
		
		gm.createGist(
				configuration.get("gistDescription"),
				true,								//El Gist será público por defecto
				message,
				configuration.get("gistName"));
		
	}

}
