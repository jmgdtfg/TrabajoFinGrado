package processingComponents;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import document.Document;
import document.ForecastDocument;
import document.SimpleForecastDocument;
import net.aksingh.owmjapis.model.param.WeatherData;
import openweathermap.AllWeatherData;

public class FilterByWeatherCloudinessProcess implements ProcessComponent{
	private Map<String, String> configuration_;
	@Override
	public Map<String, String> getConfiguration() {
		return configuration_;
	}

	@Override
	public void setConfiguration(Map<String, String> configuration) {
		configuration_ = configuration;
	}
	@Override
	public List<Document> execute(List<Document> data, Map<String, String> configuration) {
		int cloudinessMax = Integer.valueOf(configuration.get("cloudinessMax"));
		int cloudinessMin = Integer.valueOf(configuration.get("cloudinessMin"));
		List<Document> listDocument = new ArrayList<Document>();

		//Comprobación de que los valores del intervalo sean válidos
		if (cloudinessMax-cloudinessMin < 0)
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
			int cloudiness = wd.get(i).getCloudData().getCloudiness();
			
			
			if (cloudiness >= cloudinessMin && cloudiness <= cloudinessMax){
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
