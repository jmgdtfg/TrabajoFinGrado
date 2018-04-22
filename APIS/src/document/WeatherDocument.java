package document;

import openweathermap.AllWeatherData;

public class WeatherDocument extends Document{

	@Override
	public String getDataAsString() {
		AllWeatherData weather = (AllWeatherData) getRawData();
		String data = "Clima: "+weather.getConditions()+" | Temperatura: "+weather.getTemp();
		data += " | Nubosidad: "+weather.getCloudiness()+" | Humedad: "+weather.getHumidity()+"\n";
		return data;
	}

}
