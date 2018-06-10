package parser;
//Clase que contiene las funciones necesarias para analizar datos
public class Parser {
	//Constantes necesarias para la validación de una palabra
	private static final String[] SPANISH_PREPOSITIONS_LIST = {"a", "ante", "bajo", "cabe", "con", "contra", "de", 
			"desde", "en", "entre", "hacia", "hasta", "para", "por", "según", "sin", "so", "sobre","tras"};
	private static final String[] SPANISH_PRONOUN_LIST = {"adonde","algo","al","alguien","alguna","algunas","alguno",
			"algunos","ambas","ambos","aquel","aquella","aquellas","aquello","aquellos","bastante",
			"bastantes","como","conmigo","consigo","contigo","cual","cual","cuales","cualesquiera",
			"cualquiera","cuando","cuándo","cuanta","cuantas","cuanto","cuantos","cuya","cuyas","cuyo",
			"cuyos","demas","demasiada","demasiadas","demasiado","demasiados","donde","el","ella","ellas",
			"ello","ellos","esa","esas","ese","eso","esos","esta","es","estas","este","esto","estos","estotra","estotro",
			"idem","la","las","le","les","lo","los","me","media","medias","medio","medios","mi",
			"misma","mismas","mismo","mismos","mucha","muchas","mucho","muchos","nada","nadie",
			"ninguna","ningunas","ninguno","ningunos","nos","nosotras","nosotros","os","otra","otras",
			"otro","otros","poca","pocas","poco","pocos","que","quien","quienes","quien","quiera",
			"se","sus","su","si","tal","tales","tanta","tantas","tanto","tantos","te","ti","toda","todas","todo",
			"todos","tu","una","unas","un","uno","unos","usted","ustedes","varias","varios","vos","vosotras",
			"vosotros","yo"};
	private static final String[] SPANISH_CONNECTORS_LIST = {"y","antes","despues","continuacion","mientras","cuando","del"
			,"o"};
	
	private static final String[] OTHER_LIST = {"titulo:","descripcion:","fecha:","link:","contenido:","autor:"
			,"artista(s):","|","enviado","por:","asunto:","texto:","&nbsp;","&nbsp","&"};
	
	
	//Función para comprobar que no sea una preposición, pronombre, etc
	public static boolean isValid(String word){
		
		//Comprueba las preposiciones
		for ( int i = 0; i<SPANISH_PREPOSITIONS_LIST.length; i++){
			if (word.equals(SPANISH_PREPOSITIONS_LIST[i])){
				return false;
			}
		}
		
		//Comprueba los pronombres
		for ( int i = 0; i<SPANISH_PRONOUN_LIST.length; i++){
			if (word.equals(SPANISH_PRONOUN_LIST[i])){
				return false;
			}
		}
		
		//Comprueba los conectores
		for ( int i = 0; i<SPANISH_CONNECTORS_LIST.length; i++){
			if (word.equals(SPANISH_CONNECTORS_LIST[i])){
				return false;
			}
		}
		
		//Elimina elementos que se generan en los tipo Document
		for ( int i = 0; i<OTHER_LIST.length; i++){
			if (word.equals(OTHER_LIST[i])){
				return false;
			}
		}
		
		//TODO: Más comprobaciones...
		
		return true;
	}
	
	//Función para eliminar tildes y carácteres especiales
	public static String parse(String word){
		word = word.toLowerCase().trim();
		word = word.replaceAll("\\<[^>]*>","");
		word = word.replaceAll("á","a");
		word = word.replaceAll("é","e");
		word = word.replaceAll("í","i");
		word = word.replaceAll("ó","o");
		word = word.replaceAll("ú","u");
		return word;
	}
}
