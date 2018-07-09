package pipeline;



import java.util.HashMap;
import java.util.Map;

import document.Document;
import processingComponents.*;
/**
 * 
 * @author jmgd_
 *
 */
public class ProcessAvailability {

	private Map<String,Integer> mapAvailability = new HashMap<String,Integer>();
	
	private FeedbackTweetsProcess feedbackTweets = new FeedbackTweetsProcess();
	FilterByRssAuthorProcess filterByRssAuthor = new FilterByRssAuthorProcess();
	FilterByWeatherCloudinessProcess filterByWeatherCloudiness = new FilterByWeatherCloudinessProcess();
	FilterByWeatherConditionsProcess filterByWeatherConditions = new FilterByWeatherConditionsProcess();
	FilterByWeatherHumidityProcess filterByWeatherHumidity = new FilterByWeatherHumidityProcess();
	FilterByWeatherTempProcess filterByWeatherTemp = new FilterByWeatherTempProcess();
	FilterRepositoriesByAuthorProcess filterRepositoriesByAuthor = new FilterRepositoriesByAuthorProcess();
	FilterRepositoriesBySizeProcess filterRepositoriesBySize = new FilterRepositoriesBySizeProcess();
	GetNumberOfMatchesGenericProcess getNumberOfMatchesGeneric = new GetNumberOfMatchesGenericProcess();
	RepositoryInformationProcess repositoryInformation = new RepositoryInformationProcess();
	TimeIntervalMailProcess timeIntervalMail = new TimeIntervalMailProcess();
	TimeIntervalRepositoryProcess timeIntervalRepository = new TimeIntervalRepositoryProcess();
	TimeIntervalRssProcess timeIntervalRss = new TimeIntervalRssProcess();
	TimeIntervalTweetsProcess timeIntervalTweets = new TimeIntervalTweetsProcess();
	TopArtistsProcess topArtists  = new TopArtistsProcess();
	TopChannelMessagesReactionsProcess topChannelMessagesReactions = new TopChannelMessagesReactionsProcess();
	TopFrecuentWordGenericProcess topFrecuentWordGeneric = new TopFrecuentWordGenericProcess();
	TopLikesProcess topLikes = new TopLikesProcess();
	TopMostFrecuentMailSendersProcess topMostFrecuentMailSenders = new TopMostFrecuentMailSendersProcess();
	TopRetweetsProcess topRetweets = new TopRetweetsProcess();
	TopTracksProcess topTracks = new TopTracksProcess();
	TranslateGenericProcess translateGeneric = new TranslateGenericProcess();
	
	/**
	 * Función que comprobará la disponibilidad de un tipo de documento para todos los procesos
	 * disponibles
	 * @param document
	 * @return
	 */
	public Map<String,Integer> check(Document document){
		//--------------------------------------------------------------
		if (feedbackTweets.isCompatibleWith(document))
			mapAvailability.put(feedbackTweets.getClass().getName(), 1);
		else
			mapAvailability.put(feedbackTweets.getClass().getName(), 0);
		//--------------------------------------------------------------
		if (filterByRssAuthor.isCompatibleWith(document))
			mapAvailability.put(filterByRssAuthor.getClass().getName(), 1);
		else
			mapAvailability.put(filterByRssAuthor.getClass().getName(), 0);
		//--------------------------------------------------------------
		if (filterByWeatherCloudiness.isCompatibleWith(document))
			mapAvailability.put(filterByWeatherCloudiness.getClass().getName(), 1);
		else
			mapAvailability.put(filterByWeatherCloudiness.getClass().getName(), 0);
		//--------------------------------------------------------------
		if (filterByWeatherConditions.isCompatibleWith(document))
			mapAvailability.put(filterByWeatherConditions.getClass().getName(), 1);
		else
			mapAvailability.put(filterByWeatherConditions.getClass().getName(), 0);
		//--------------------------------------------------------------
		if (filterByWeatherHumidity.isCompatibleWith(document))
			mapAvailability.put(filterByWeatherHumidity.getClass().getName(), 1);
		else
			mapAvailability.put(filterByWeatherHumidity.getClass().getName(), 0);
		//--------------------------------------------------------------
		if (filterByWeatherTemp.isCompatibleWith(document))
			mapAvailability.put(filterByWeatherTemp.getClass().getName(), 1);
		else
			mapAvailability.put(filterByWeatherTemp.getClass().getName(), 0);
		//--------------------------------------------------------------
		if (filterRepositoriesByAuthor.isCompatibleWith(document))
			mapAvailability.put(filterRepositoriesByAuthor.getClass().getName(), 1);
		else
			mapAvailability.put(filterRepositoriesByAuthor.getClass().getName(), 0);
		//--------------------------------------------------------------
		if (filterRepositoriesBySize.isCompatibleWith(document))
			mapAvailability.put(filterRepositoriesBySize.getClass().getName(), 1);
		else
			mapAvailability.put(filterRepositoriesBySize.getClass().getName(), 0);
		//--------------------------------------------------------------
		//Proceso GENÉRICO, SIEMPRE ES VÁLIDO
		mapAvailability.put(getNumberOfMatchesGeneric.getClass().getName(), 1);

		//--------------------------------------------------------------
		if (repositoryInformation.isCompatibleWith(document))
			mapAvailability.put(repositoryInformation.getClass().getName(), 1);
		else
			mapAvailability.put(repositoryInformation.getClass().getName(), 0);
		//--------------------------------------------------------------
		if (timeIntervalMail.isCompatibleWith(document))
			mapAvailability.put(timeIntervalMail.getClass().getName(), 1);
		else
			mapAvailability.put(timeIntervalMail.getClass().getName(), 0);
		//--------------------------------------------------------------
		if (timeIntervalRepository.isCompatibleWith(document))
			mapAvailability.put(timeIntervalRepository.getClass().getName(), 1);
		else
			mapAvailability.put(timeIntervalRepository.getClass().getName(), 0);
		//--------------------------------------------------------------
		if (timeIntervalRss.isCompatibleWith(document))
			mapAvailability.put(timeIntervalRss.getClass().getName(), 1);
		else
			mapAvailability.put(timeIntervalRss.getClass().getName(), 0);
		//--------------------------------------------------------------
		if (timeIntervalTweets.isCompatibleWith(document))
			mapAvailability.put(timeIntervalTweets.getClass().getName(), 1);
		else
			mapAvailability.put(timeIntervalTweets.getClass().getName(), 0);
		//--------------------------------------------------------------
		if (topArtists.isCompatibleWith(document))
			mapAvailability.put(topArtists.getClass().getName(), 1);
		else
			mapAvailability.put(topArtists.getClass().getName(), 0);
		//--------------------------------------------------------------
		if (topChannelMessagesReactions.isCompatibleWith(document))
			mapAvailability.put(topChannelMessagesReactions.getClass().getName(), 1);
		else
			mapAvailability.put(topChannelMessagesReactions.getClass().getName(), 0);
		//--------------------------------------------------------------
		//Proceso Genérico, SIEMPRE ES VÁLIDO
		mapAvailability.put(topFrecuentWordGeneric.getClass().getName(), 1);

		//--------------------------------------------------------------
		if (topLikes.isCompatibleWith(document))
			mapAvailability.put(topLikes.getClass().getName(), 1);
		else
			mapAvailability.put(topLikes.getClass().getName(), 0);
		//--------------------------------------------------------------
		if (topMostFrecuentMailSenders.isCompatibleWith(document))
			mapAvailability.put(topMostFrecuentMailSenders.getClass().getName(), 1);
		else
			mapAvailability.put(topMostFrecuentMailSenders.getClass().getName(), 0);
		//--------------------------------------------------------------
		if (topRetweets.isCompatibleWith(document))
			mapAvailability.put(topRetweets.getClass().getName(), 1);
		else
			mapAvailability.put(topRetweets.getClass().getName(), 0);
		//--------------------------------------------------------------
		if (topTracks.isCompatibleWith(document))
			mapAvailability.put(topTracks.getClass().getName(), 1);
		else
			mapAvailability.put(topTracks.getClass().getName(), 0);
		//--------------------------------------------------------------
		//Proceso siempre válido, es genérico
		mapAvailability.put(translateGeneric.getClass().getName(), 1);


		return mapAvailability;
	}
}
