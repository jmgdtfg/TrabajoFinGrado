package ibm;

import java.util.HashMap;
import java.util.Map;

public class LanguagesFactory {
	
	private Map<String,String> languages = new HashMap<String,String>();
	
	public LanguagesFactory(){
		languages.put("english", "en");
		languages.put("spanish", "es");
		languages.put("italian", "it");
		languages.put("germany", "de");
		languages.put("frances", "fr");
	}

	public Map<String, String> getLanguages() {
		return languages;
	}
	

}
