package rest.service;

import java.util.HashMap;
import java.util.Map;

public class AvailableElementsData {
	Map<String,String> inputs_ = new HashMap<String,String>();
	Map<String,String> process_ = new HashMap<String,String>();
	Map<String,String> outputs_ = new HashMap<String,String>();
	
	public Map<String, String> getInputs_() {
		return inputs_;
	}

	public void setInputs_(Map<String, String> inputs_) {
		this.inputs_ = inputs_;
	}

	public Map<String, String> getProcess_() {
		return process_;
	}

	public void setProcess_(Map<String, String> process_) {
		this.process_ = process_;
	}

	public Map<String, String> getOutputs_() {
		return outputs_;
	}

	public void setOutputs_(Map<String, String> outputs_) {
		this.outputs_ = outputs_;
	}

	public AvailableElementsData(){
		inputs_.put("Forecast", "Disponible");
		inputs_.put("Github own repositories", "Disponible");
		inputs_.put("Github repository", "Disponible");
		inputs_.put("Github search repository", "Disponible");
		inputs_.put("Mail inbox", "Disponible");
		inputs_.put("Mail search inbox", "Disponible");
		inputs_.put("RSS", "Disponible");
		inputs_.put("Slack Channel Message", "Disponible");
		inputs_.put("Slack own channels", "Disponible");
		inputs_.put("Slack users list", "Disponible");
		inputs_.put("Spotify hits by country", "Disponible");
		inputs_.put("Spotify playlist", "Disponible");
		inputs_.put("Spotify recommendations", "Disponible");
		inputs_.put("Spotify search", "Disponible");
		inputs_.put("Spotify top playlists", "Disponible");
		inputs_.put("SpreadSheet", "Disponible");
		inputs_.put("Twitter own timeline", "Disponible");
		inputs_.put("Twitter search input", "Disponible");
		inputs_.put("Twitter trending topics", "Disponible");
		inputs_.put("Twitter user timeline", "Disponible");
		inputs_.put("Weather", "Disponible");
		
		process_.put("Feedback tweets process", "Disponible");
		process_.put("Filter by rss author", "Disponible");
		process_.put("Filter by weather cloudiness", "Disponible");
		process_.put("Filter by weather conditions", "Disponible");
		process_.put("Filter by weather humidity", "Disponible");
		process_.put("Filter by weather temp", "Disponible");
		process_.put("Filter repositories by author", "Disponible");
		process_.put("Filter repositories by size", "Disponible");
		process_.put("Get number of matches (G)", "Disponible");
		process_.put("Repository Information", "Disponible");
		process_.put("Time interval mail", "Disponible");
		process_.put("Time interval repository", "Disponible");
		process_.put("Time interval rss", "Disponible");
		process_.put("Time interval tweets", "Disponible");
		process_.put("Top Artists", "Disponible");
		process_.put("Top channel messages reactions", "Disponible");
		process_.put("Top frecuent word (G)", "Disponible");
		process_.put("Top likes", "Disponible");
		process_.put("Top most frecuent mail senders", "Disponible");
		process_.put("Top retweets", "Disponible");
		process_.put("Top tracks", "Disponible");
		process_.put("Translate (G)", "Disponible");

		outputs_.put("Email", "Disponible");
		outputs_.put("Github Gist", "Disponible");
		outputs_.put("Slack channel meessage", "Disponible");
		outputs_.put("Slack direct message", "Disponible");
		outputs_.put("SpreadSheet", "Disponible");
		outputs_.put("Twitter", "Disponible");
		
	}
}
