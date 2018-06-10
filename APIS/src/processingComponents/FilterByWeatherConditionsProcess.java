package processingComponents;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import document.Document;
import document.ForecastDocument;
import document.SimpleForecastDocument;
import net.aksingh.owmjapis.model.param.Weather;
import net.aksingh.owmjapis.model.param.WeatherData;
import openweathermap.AllWeatherData;
import openweathermap.WeatherConditionsFactory;

public class FilterByWeatherConditionsProcess implements ProcessComponent{
	@Override
	public List<Document> execute(List<Document> data, Map<String, String> configuration) {

		String weather = configuration.get("weatherCondition");
		List<Document> listDocument = new ArrayList<Document>();


		//Comprobación de que los valores del intervalo sean válidos
		if (weather.equals(null))
			return null;

		//Para tratar la información se almacenará en una lista 
		List<AllWeatherData> weatherList = new ArrayList<AllWeatherData>();
		for (Document document : data){
			AllWeatherData w = (AllWeatherData) document.getRawData();
			weatherList.add(w);
		}
		/*Solo es necesario extraer el primer elemento de la lista,
		ya que contiene todos los datos de la predicción*/
		List<WeatherData> wd = weatherList.get(0).getForecast();
		WeatherConditionsFactory wcf = new WeatherConditionsFactory();

		for (int i = 0; i<wd.size(); i++){
			Weather w = wd.get(i).getWeatherList().get(0);
			String condition = wcf.getCondition(w.getDescription());
			
			if (condition.contains(weather)){
				SimpleForecastDocument document = new SimpleForecastDocument();
				document.setRawData(wd.get(i));
				listDocument.add(document);
			}


		}
		return listDocument;

	}

	@Override
	public boolean isCompatibleWith(Document document) {
		if (document instanceof ForecastDocument) {
			return true;
		}
		return false;
	}


}
