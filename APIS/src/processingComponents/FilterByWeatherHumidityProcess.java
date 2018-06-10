package processingComponents;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import document.Document;
import document.ForecastDocument;
import document.SimpleForecastDocument;
import net.aksingh.owmjapis.model.param.WeatherData;
import openweathermap.AllWeatherData;

public class FilterByWeatherHumidityProcess implements ProcessComponent{

	@Override
	public List<Document> execute(List<Document> data, Map<String, String> configuration) {

		int humidityMax = Integer.valueOf(configuration.get("humidityMax"));
		int humidityMin = Integer.valueOf(configuration.get("humidityMin"));
		List<Document> listDocument = new ArrayList<Document>();

		//Comprobación de que los valores del intervalo sean válidos
		if (humidityMax-humidityMin < 0)
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

		for (int i = 0; i<wd.size(); i++){
			int humidity = wd.get(i).getMainData().getHumidity();
			
			
			if (humidity >= humidityMin && humidity <= humidityMax){
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
