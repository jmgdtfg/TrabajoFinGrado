package document;

import java.text.SimpleDateFormat;

import net.aksingh.owmjapis.model.param.Weather;
import net.aksingh.owmjapis.model.param.WeatherData;
import openweathermap.WeatherConditionsFactory;

public class SimpleForecastDocument extends Document{


	@Override
	public String getDataAsString() {


		WeatherData wd = (WeatherData) getRawData();

		WeatherConditionsFactory wcf = new WeatherConditionsFactory();
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String data = "";


			String date = format1.format(wd.getDateTime());
			int temp = wd.getMainData().getTemp().intValue() - 273;
			int cloudiness = wd.getCloudData().getCloudiness();
			int humidity = wd.getMainData().getHumidity().intValue();
			Weather w = wd.getWeatherList().get(0);
			String condition = wcf.getCondition(w.getDescription());

			data = data.concat(
					"Fecha: "+date+" | Estado: "+condition+" | Temp.: "+temp+"ºC | Nubosidad: "+
							cloudiness+"% | Humedad: "+humidity+"%\n");
		
		return data;
	}



}
