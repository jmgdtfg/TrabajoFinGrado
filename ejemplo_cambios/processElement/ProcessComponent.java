package processingComponents;

import java.util.List;
import java.util.Map;

import document.Document;
//Interfaz de los componentes de procesamiento
public interface ProcessComponent {

	List<Document> execute(List<Document> data, Map<String, String> configuration);

	//Getters & Setters Configuración
	Map<String, String> getConfiguration();
	void setConfiguration(Map<String, String> configuration);

	//Método para establecer la compatibilidad
	boolean isCompatibleWith(Document document);

}