package inputComponents;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import document.Document;
import document.ForecastDocument;
import net.aksingh.owmjapis.api.APIException;
import net.aksingh.owmjapis.model.param.WeatherData;
import openweathermap.AllWeatherData;
import openweathermap.OpenWeatherMapManager;

public class ForecastInput implements InputComponent{

	@Override
	public List<Document> execute(Map<String, String> configuration) {
		OpenWeatherMapManager weather = new OpenWeatherMapManager();
		List<Document> listDocument = new ArrayList<Document>();
		
		try {
			
			String condition = weather.getCondition(configuration.get("weatherCity"));		
			int temp = weather.getCurrentTemp(configuration.get("weatherCity"));	
			int humidity = weather.getHumidity(configuration.get("weatherCity"));		
			int cloudiness = weather.getCloudiness(configuration.get("weatherCity"));
			List<WeatherData> forecast = weather.HourlyForecast(configuration.get("weatherCity"));
		
			//Se crea una nueva clase CurrentWeather que almacena los datos del tiempo actual
			AllWeatherData cw = new AllWeatherData(condition,temp,humidity,cloudiness,forecast);
			
			ForecastDocument document = new ForecastDocument();
			document.setRawData(cw);
			listDocument.add(document);
			


		} catch (APIException e) {
			e.printStackTrace();
		}
		return listDocument;
	}

}
