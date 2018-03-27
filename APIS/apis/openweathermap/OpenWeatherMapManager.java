package openweathermap;

import java.util.List;
import net.aksingh.owmjapis.api.APIException;
import net.aksingh.owmjapis.core.OWM;
import net.aksingh.owmjapis.model.CurrentWeather;
import net.aksingh.owmjapis.model.HourlyWeatherForecast;
import net.aksingh.owmjapis.model.param.Weather;
import net.aksingh.owmjapis.model.param.WeatherData;

public class OpenWeatherMapManager {

	private String token_ = "";
	private OWM owm_ = new OWM(token_);
	
	//Constructor por defecto
	public OpenWeatherMapManager(){}

	//Constructor parametrizado
	public OpenWeatherMapManager(String token){
		token_=token;
	}
	//Funci�n que obtiene las condiciones climatol�gicas del lugar
	public String getCondition(String city) throws APIException{
		WeatherConditionsFactory wcf = new WeatherConditionsFactory();
		CurrentWeather cwd = owm_.currentWeatherByCityName(city);
		List<Weather> lw = cwd.component5();
		Weather w = lw.get(0);
		String condition = w.getDescription();
		return wcf.getCondition(condition);
	}
	
	//Funci�n que devuelve la temperatura actual de una ciudad pasada por par�metro.
	public int getCurrentTemp(String city) throws APIException {
		CurrentWeather cwd = owm_.currentWeatherByCityName(city);
		int Kelvin = cwd.getMainData().getTemp().intValue();
		int Centigrados = Kelvin - 273;
		return Centigrados;
	}
	//Funci�n que devuelve la maxima temperatura del d�a de una ciudad pasada por par�metro.
	public int getMaxTemp(String city) throws APIException {
		CurrentWeather cwd = owm_.currentWeatherByCityName(city);
		int Kelvin = cwd.getMainData().getTempMax().intValue();
		int Centigrados = Kelvin - 273;
		return Centigrados;
	}
	//Funci�n que devuelve la m�nima temperatura del d�a de una ciudad pasada por par�metro.
	public int getMinTemp(String city) throws APIException {
		CurrentWeather cwd = owm_.currentWeatherByCityName(city);
		int Kelvin = cwd.getMainData().getTempMin().intValue();
		int Centigrados = Kelvin - 273;
		return Centigrados;
	}
	//Funci�n que devuelve la humidad de una ciudad pasada por par�metro
	public int getHumidity(String city) throws APIException {
		CurrentWeather cwd = owm_.currentWeatherByCityName(city);
		int humidity = cwd.getMainData().getHumidity().intValue();
		return humidity;
	}
	//Funci�n que devuelve la lluvia de una ciudad pasada por par�metro
	public int getCloudiness(String city) throws APIException {
		CurrentWeather cwd = owm_.currentWeatherByCityName(city);
		//Devuelve un porcentaje ej. 93% nubosidad
		int cloudiness = cwd.getCloudData().getCloudiness();
		return cloudiness;

	}
	//Pron�stico
	public List<WeatherData> HourlyForecast(String city) throws APIException{

		//DailyWeatherForecast;
		HourlyWeatherForecast hwf = owm_.hourlyWeatherForecastByCityName(city);
		List<WeatherData> wd = hwf.getDataList();

		/*				for (int i=0;i<wd.size();i++){
					System.out.println(wd.get(i).getDateTime());
					System.out.println(wd.get(i).getMainData().getTemp().intValue());
					System.out.println(wd.get(i).getCloudData().getCloudiness());
				}*/

		return wd;
		//System.out.println(wd.get(1).getCloudData().toString());
	}



}