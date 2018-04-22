package inputComponents;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.egit.github.core.Repository;

import document.Document;
import document.RepositoryDocument;
import github.GithubManager;


public class GithubOwnRepositoriesInput implements InputComponent{
	//Función que devuelve una lista de los propios repositorios
	@Override
	public List<Document> execute(Map<String, String> configuration) {
		
		GithubManager gm = new GithubManager();
		List<Document> listDocument = new ArrayList<Document>();
		try {
			for (Repository repo : gm.getOwnRepos()) {
				RepositoryDocument document = new RepositoryDocument();
				document.setRawData(repo);
				listDocument.add(document);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return listDocument;
	}

}
