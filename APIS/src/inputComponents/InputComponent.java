package inputComponents;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

import document.Document;
//Interfaz de los componentes de entrada
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY)
public interface InputComponent {
	/*Funcion que podra tener como parametro de entrada una configuracion
	 * y que devolvera cualquier tipo de objeto (Tweet, tracks, mails...)*/
	List<Document> execute();
	//Getters & Setters Configuración
	Map<String, String> getConfiguration();
	void setConfiguration(Map<String, String> configuration);
	//Getters & Setters del tipo de document
	Document getDocument();

}
