package inputComponents;

import java.util.List;
import java.util.Map;

import document.Document;
//Interfaz de los componentes de entrada
// Lo de <T extends Document<?>> limita el tipo de dato a documentos
public interface InputComponent {
	/*Funcion que podra tener como parametro de entrada una configuracion
	 * y que devolvera cualquier tipo de objeto (Tweet, tracks, mails...)*/
	List<Document> execute(Map<String, String> configuration);
	//Getters & Setters Configuraci�n
	Map<String, String> getConfiguration();
	void setConfiguration(Map<String, String> configuration);
}
