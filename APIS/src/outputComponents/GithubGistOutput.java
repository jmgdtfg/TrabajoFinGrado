package outputComponents;

import java.util.List;
import java.util.Map;

import document.Document;
import github.GithubManager;

public class GithubGistOutput implements OutputComponent{
	private Map<String, String> configuration_;
	@Override
	public Map<String, String> getConfiguration() {
		return configuration_;
	}

	@Override
	public void setConfiguration(Map<String, String> configuration) {
		configuration_ = configuration;
	} 
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
