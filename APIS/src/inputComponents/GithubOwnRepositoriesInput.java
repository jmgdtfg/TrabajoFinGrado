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
	private Document document_ = new RepositoryDocument();
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
	public Document getDocument() {
		return document_;
	}

	//Función que devuelve una lista de los propios repositorios
	@Override
	public List<Document> execute() {
		
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
