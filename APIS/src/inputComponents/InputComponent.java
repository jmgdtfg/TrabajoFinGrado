package inputComponents;

import java.util.Map;
//Interfaz de los componentes de entrada
public interface InputComponent {
	/*Funci�n que podr� tener como par�metro de entrada una configuraci�n
	 * y que devolver� cualquier tipo de objeto (Tweet, tracks, mails...)*/
	Object execute(Map<String, String> configuration);

}
