package processingComponents;

import java.util.List;
import java.util.Map;

import document.Document;
//Interfaz de los componentes de procesamiento
public interface ProcessComponent {

	List<Document> execute(List<Document> data, Map<String, String> configuration);

	//Getters & Setters Configuraci�n
	Map<String, String> getConfiguration();
	void setConfiguration(Map<String, String> configuration);

	//M�todo para establecer la compatibilidad
	boolean isCompatibleWith(Document document);

}