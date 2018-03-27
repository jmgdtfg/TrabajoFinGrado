package processingComponents;

import java.util.Map;
//Interfaz de los componentes de procesamiento
public interface ProcessComponent {
	/*Funci�n que tiene como par�metros de entrada los datos y una
	 * configuraci�n. Devuelve cualquier tipo de objeto*/
	Object execute(Object data, Map<String, String> configuration);
	
}
