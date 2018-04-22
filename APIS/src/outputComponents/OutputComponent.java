package outputComponents;

import java.util.List;
import java.util.Map;

import document.Document;
//Interfaz de los componentes de salida
public interface OutputComponent {

	void execute(List<Document> data, Map<String, String> configuration);

}
