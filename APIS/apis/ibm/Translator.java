package ibm;

import java.util.List;

import com.ibm.watson.developer_cloud.http.ServiceCall;
import com.ibm.watson.developer_cloud.language_translator.v2.LanguageTranslator;
import com.ibm.watson.developer_cloud.language_translator.v2.model.IdentifiedLanguages;
import com.ibm.watson.developer_cloud.language_translator.v2.model.IdentifyOptions;
import com.ibm.watson.developer_cloud.language_translator.v2.model.IdentifyOptions.Builder;
import com.ibm.watson.developer_cloud.language_translator.v2.model.TranslateOptions;
import com.ibm.watson.developer_cloud.language_translator.v2.model.Translation;
import com.ibm.watson.developer_cloud.language_translator.v2.model.TranslationResult;


public class Translator {

	private String user_ = "1";
	private String password_ = "";
	private LanguageTranslator service_ = new LanguageTranslator();
	//Constructor de la clase
	public Translator(){
		//Se crea un nuevo servicio
		service_.setUsernameAndPassword(user_,password_);
	}
	//Hacer map para los lenguajes disponibles
	public String translate(String text, String language){

		//Se identifica el lenguaje
		Builder identifyOptions = new IdentifyOptions.Builder();
		ServiceCall<IdentifiedLanguages> identifiedLanguages = service_
				.identify(identifyOptions.text(text).build());

		IdentifiedLanguages il = identifiedLanguages.execute();
		String source = il.getLanguages().get(0).getLanguage();

		if (il.getLanguages().get(0).getLanguage().isEmpty())
			return "No se identifica el idioma a traducir";

		//Comprobamos que el idioma esté disponible o asignamos inglés por defecto
		LanguagesFactory lf = new LanguagesFactory();
		String target = lf.getLanguages().getOrDefault(language, "en");

		if (target.contains(source)){
			return text;
		}
		//El traductor solo permite las traducir idioma->ingles o ingles->idioma
		if (source.equals("en") || target.equals("en")){
			TranslateOptions translateOptions = new TranslateOptions.Builder()
					.addText(text)
					.source(source)
					.target(target)
					.build();

			TranslationResult result = service_.translate(translateOptions).execute();
			List<Translation> translations = result.getTranslations();
			return translations.get(0).getTranslation();
		}
		//Hay que realizar una traducción intermedia al inglés para poder traducir.
		else{
			TranslateOptions translateOptions = new TranslateOptions.Builder()
					.addText(text)
					.source(source)
					.target("en")
					.build();

			TranslationResult result = service_.translate(translateOptions).execute();
			List<Translation> translations = result.getTranslations();
			String firstResult = translations.get(0).getTranslation();
			
			TranslateOptions translateFinalOptions = new TranslateOptions.Builder()
					.addText(firstResult)
					.source("en")
					.target(target)
					.build();

			TranslationResult result2 = service_.translate(translateFinalOptions).execute();
			List<Translation> finalTranslations = result2.getTranslations();
			String finalResult = finalTranslations.get(0).getTranslation();
			
			return finalResult;
		
		}
	}
}
