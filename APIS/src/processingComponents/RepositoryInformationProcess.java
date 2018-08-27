package processingComponents;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FilenameUtils;

import document.Document;
import document.FileDocument;
import document.FileMapDocument;
import github.GithubManager;

public class RepositoryInformationProcess implements ProcessComponent{
	private Map<String, String> configuration_;
	@Override
	public Map<String, String> getConfiguration() {
		return configuration_;
	}

	@Override
	public void setConfiguration(Map<String, String> configuration) {
		configuration_ = configuration;
	}
	//Función que obtiene la información de un repositorio.
	@Override
	public List<Document> execute(List<Document> data, Map<String, String> configuration) {
		
		List<Document> listDocument = new ArrayList<Document>();
		
		GithubManager gm = new GithubManager();

			
			File file = (File) data.get(0).getRawData();

		// Lista para los valores devueltos por getDirectoryTree(path)
		List<Path> pathFiles = new LinkedList<Path>();
		
		//Mapa para acoger los datos =>"tipo", nº de apariciones
		Map<String, Integer> datos = new HashMap<String, Integer>();
		
		try {
			pathFiles = gm.getDirectoryTree(file.getParent());
			
			//Recorremos las rutas de cada archivo
			for (Path p : pathFiles){
				/*Si el para una palabra clave determinada EJ: xml, no existe ningún valor en
				 * el Map se le asignará un nuevo valor de aparición (1)
				 * */
				if (datos.get(FilenameUtils.getExtension(p.getFileName().toString()))==null){
					datos.put(FilenameUtils.getExtension(p.getFileName().toString()), 1);
				}
				//Si ya existe dicha palabra clave se reemplazara su valor antiguo por uno nuevo (+1)
				else if(datos.get(FilenameUtils.getExtension(p.getFileName().toString()))>=1){
					int value = datos.get(FilenameUtils.getExtension(p.getFileName().toString()))+1;
					datos.replace(
							FilenameUtils.getExtension(p.getFileName().toString()),
							value);
				}
			}
			//Por último se añade a un nuevo documento
			FileMapDocument document2 = new FileMapDocument();
			document2.setRawData(datos);
			listDocument.add(document2);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return listDocument;

	}

	@Override
	public boolean isCompatibleWith(Document document) {
		if (document instanceof FileDocument) {
			return true;
		}
		return false;
	}

}
