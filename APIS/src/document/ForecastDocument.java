package document;

import java.text.SimpleDateFormat;
import java.util.List;

import net.aksingh.owmjapis.model.param.Weather;
import net.aksingh.owmjapis.model.param.WeatherData;
import openweathermap.AllWeatherData;
import openweathermap.WeatherConditionsFactory;

public class ForecastDocument extends Document{

	@Override
	public String getDataAsString() {


		AllWeatherData weather = (AllWeatherData) getRawData();
		List<WeatherData> wd = weather.getForecast();
		WeatherConditionsFactory wcf = new WeatherConditionsFactory();
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String data = "";

		for (int i=0;i<wd.size()/4;i++){

			String date = format1.format(wd.get(i).getDateTime());
			int temp = wd.get(i).getMainData().getTemp().intValue() - 273;
			int cloudiness = wd.get(i).getCloudData().getCloudiness();
			int humidity = wd.get(i).getMainData().getHumidity().intValue();
			Weather w = wd.get(i).getWeatherList().get(0);
			String condition = wcf.getCondition(w.getDescription());

			data = data.concat(
					"Fecha: "+date+" | Estado: "+condition+" | Temp.: "+temp+"ºC | Nubosidad: "+
							cloudiness+"% | Humedad: "+humidity+"%\n");
		}
		return data;
	}

}
