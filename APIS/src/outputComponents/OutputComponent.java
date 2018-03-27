package outputComponents;

import java.util.Map;
//Interfaz de los componentes de salida
public interface OutputComponent {
	/*Funci�n que tiene como par�metro de entrada los datos y
	 * una configuraci�n. No devuelve nada*/
	void execute(Object data, Map<String, String> configuration);
	
}
