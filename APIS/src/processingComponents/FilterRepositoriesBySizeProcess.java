package processingComponents;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.egit.github.core.Repository;

import document.Document;
import document.RepositoryDocument;

public class FilterRepositoriesBySizeProcess implements ProcessComponent{
	@Override
	public List<Document> execute(List<Document> data, Map<String, String> configuration) {

		int maxSize = Integer.valueOf(configuration.get("maxSize")).intValue();
		int minSize = Integer.valueOf(configuration.get("minSize")).intValue();
		List<Document> listDocument = new ArrayList<Document>();


		//Comprobación de que los valores del intervalo sean válidos
		if (maxSize<=0 || minSize<=0)
			return null;
		if (maxSize-minSize <=0)
			return null;

		//Para tratar la información se almacenará en una lista de tweets
		List<Repository> repositoryList = new ArrayList<Repository>();
		for (Document document : data){
			Repository repo = (Repository) document.getRawData();
			repositoryList.add(repo);
		}
		
		for (Repository s:repositoryList){
			if (s.getSize()<=maxSize && s.getSize()>=minSize){
				RepositoryDocument document = new RepositoryDocument();
				document.setRawData(s);	//Si el repositorio está en el intervalo se añade a la lista
				listDocument.add(document);
			}
		}

		return listDocument;

	}

	@Override
	public boolean isCompatibleWith(Document document) {
		if (document instanceof RepositoryDocument) {
			return true;
		}
		return false;
	}

}
