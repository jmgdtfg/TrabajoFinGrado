package processingComponents;

import java.util.List;
import java.util.Map;

import document.Document;
//Interfaz de los componentes de procesamiento
public interface ProcessComponent {

	List<Document> execute(List<Document> data, Map<String, String> configuration);

	// Para indicar con qu� tipos de documentos es compatible el proceso (ver uso en TopLikesProcess).
	// La utilidad real de este m�todo se ver� posteriormente
	boolean isCompatibleWith(Document document);

}