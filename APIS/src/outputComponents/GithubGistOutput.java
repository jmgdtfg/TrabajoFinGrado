package outputComponents;

import java.util.Map;

import github.GithubManager;

public class GithubGistOutput implements OutputComponent{
	//Funci�n que publica los datos en un Gist
	@Override
	public void execute(Object data, Map<String, String> configuration) {
		
		GithubManager gm = new GithubManager();
		String message = (String) data;
		
		gm.createGist(
				configuration.get("gistDescription"),
				true,								//El Gist ser� p�blico por defecto
				message,
				configuration.get("gistName"));
		
	}

}
