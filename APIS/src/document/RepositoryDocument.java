package document;

import org.eclipse.egit.github.core.Repository;

public class RepositoryDocument extends Document{

	@Override
	public String getDataAsString() {
		Repository repo = (Repository)getRawData();
		String data = "Autor: " + repo.getOwner().getName() + " | Tipo: "+repo.getLanguage() +"\n";
		data += "Repositorio: "+repo.getGitUrl();
		return data;
	}

}
