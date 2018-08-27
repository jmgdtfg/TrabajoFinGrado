package outputComponents;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

import document.Document;
//Interfaz de los componentes de salida
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY)
public interface OutputComponent {

	void execute(List<Document> data, Map<String, String> configuration);
	//Getters & Setters Configuración
	Map<String, String> getConfiguration();
	void setConfiguration(Map<String, String> configuration);
}
