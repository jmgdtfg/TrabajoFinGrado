package processingComponents;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

import twitter4j.Status;

public class TopLikesProcess implements ProcessComponent{
	
	private int numTop_;					//N�mero de resultados para el top
	private static List<Status> topLikes_;	//Lista que contiene tweets

	//Constructor parametrizado obligatorio de la clase.
	public TopLikesProcess(int numTop){
		numTop_ = numTop;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Object execute(Object data, Map<String, String> configuration) {


		List<Status> tweets = (List<Status>) data;

		//Si hay menos de numTop_ tweets se finalizar� el proceso.
		if (tweets.size()<numTop_){
			String message = "No se encontraron suficientes datos";
			return message;
		}

		//Se ordenan los tweets de m�s a menos Favoritos
		tweets.sort(Comparator.comparing(Status::getFavoriteCount).reversed());
		topLikes_ = tweets;
		//Elaboraci�n del mensaje
		String message = "TOP "+numTop_+": Tweets con m�s me gusta\n\n";

		for (int i = 0; i < numTop_ ; i++){
			int number = i + 1;
			message = message.concat(number+") "+topLikes_.get(i).getUser().getName()+ ": " +topLikes_.get(i).getText()+"\n");
		}
		message = message.concat("------------------------------------------------\n");	

		return message;

	}

}
