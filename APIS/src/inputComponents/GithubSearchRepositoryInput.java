package inputComponents;

import java.io.IOException;
import java.util.Map;

import github.GithubManager;

public class GithubSearchRepositoryInput implements InputComponent{
	//Funci�n que devuelve los resultados de una b�squeda
	@Override
	public Object execute(Map<String, String> configuration) {
		
		GithubManager gm = new GithubManager();
		
		try {
			//Devuelve un objeto List<SearchRepository>
			return gm.searchRepos(
					configuration.get("word"),
					configuration.get("searchLanguage"));
		
		} catch (IOException e) {
			// **
			e.printStackTrace();
			return null;
		}

	}

}
