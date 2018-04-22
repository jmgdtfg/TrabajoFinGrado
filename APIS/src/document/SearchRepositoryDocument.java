package document;

import org.eclipse.egit.github.core.SearchRepository;

public class SearchRepositoryDocument extends Document{

	@Override
	public String getDataAsString() {
		SearchRepository repo = (SearchRepository)getRawData();
		String data = "Nombre: "+repo.getName()+" | Autor: "+repo.getOwner()+" | Lenguaje: "+repo.getLanguage();
		return data;
	}

}
