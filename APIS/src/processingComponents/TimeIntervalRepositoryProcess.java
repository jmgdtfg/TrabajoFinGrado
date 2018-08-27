package processingComponents;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.eclipse.egit.github.core.Repository;

import document.Document;
import document.RepositoryDocument;

public class TimeIntervalRepositoryProcess implements ProcessComponent{
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
	public List<Document> execute(List<Document> data, Map<String, String> configuration) {

		int intervalEnd = Integer.valueOf(configuration.get("intervalEnd")).intValue();
		int intervalStart = Integer.valueOf(configuration.get("intervalStart")).intValue();
		List<Document> listDocument = new ArrayList<Document>();


		//Comprobación de que los valores del intervalo sean válidos
		if (intervalEnd<=0 || intervalStart<=0)
			return null;
		if (intervalStart-intervalEnd <=0)
			return null;

		//Declaración de las fechas de inicio y fin del intervalo
		Calendar dateBefore = Calendar.getInstance();
		dateBefore.add(Calendar.DATE, -intervalEnd);
		Calendar dateAfter = Calendar.getInstance();
		dateAfter.add(Calendar.DATE, -intervalStart);

		//Para tratar la información se almacenará en una lista de tweets
		List<Repository> repositoryList = new ArrayList<Repository>();
		for (Document document : data){
			Repository repo = (Repository) document.getRawData();
			repositoryList.add(repo);
		}
		
		for (Repository s:repositoryList){
			if (s.getCreatedAt().before(dateBefore.getTime()) && s.getCreatedAt().after(dateAfter.getTime())){
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
