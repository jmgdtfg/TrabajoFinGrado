package inputComponents;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.jgit.api.errors.GitAPIException;

import document.Document;
import document.FileDocument;
import github.GithubManager;

public class GithubRepositoryInput implements InputComponent{
	Document document_ = new FileDocument();
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

	//Función que devuelve un repositorio
	@Override
	public List<Document> execute() {
		Map<String, String> configuration = this.getConfiguration();
		
		GithubManager gm = new GithubManager();
		List<Document> listDocument = new ArrayList<Document>();
		try {
			
			FileDocument document = new FileDocument();
			document.setRawData(gm.cloneRepository(configuration.get("repositoryUrl"),	configuration.get("localPath")));
			listDocument.add(document);
			
		} catch (GitAPIException | IOException e) {
			e.printStackTrace();
		}
		return listDocument;
	}

}
