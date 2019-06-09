package ru.evenx.weather.interfaces;

public interface WeatherInfo {
	
	String getTemperature();
	String getHumidity();
	String getPressure();	
	String getCityName();
	String getServiceName();
	void connect() throws Exception;
}


