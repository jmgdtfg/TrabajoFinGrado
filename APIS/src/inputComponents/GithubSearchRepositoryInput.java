package inputComponents;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.egit.github.core.SearchRepository;

import document.Document;
import document.SearchRepositoryDocument;
import github.GithubManager;

public class GithubSearchRepositoryInput implements InputComponent{
	//Función que devuelve los resultados de una búsqueda
	@Override
	public List<Document> execute(Map<String, String> configuration) {
		
		GithubManager gm = new GithubManager();
		List<Document> listDocument = new ArrayList<Document>();

		try {
			for (SearchRepository data : gm.searchRepos(configuration.get("word"),configuration.get("searchLanguage"))) {
				SearchRepositoryDocument document = new SearchRepositoryDocument();
				document.setRawData(data);
				listDocument.add(document);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return listDocument;

	}

}
