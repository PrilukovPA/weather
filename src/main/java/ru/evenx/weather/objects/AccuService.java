package ru.evenx.weather.objects;

import java.io.IOException;

import com.jayway.jsonpath.JsonPath;

/**
 * Доступ к сервису "AccuWeather.com"
 *
 */
public class AccuService extends WeatherService {
	
	public AccuService(String serviceName, String cityName, String url) {
		super(serviceName, cityName, url);
	}
	
	public void parseResponse(String response) {
		String tmp = JsonPath.read(response, "$.[0].Temperature.Metric.Value").toString();
		String hum = JsonPath.read(response, "$.[0].RelativeHumidity").toString();
		String pre = JsonPath.read(response, "$.[0].Pressure.Metric.Value").toString();
		setTemperature(tmp + " \u00B0C");
		setHumidity(hum + "%");
		setPressure(pre + " мбар");
	}

	public void connect() throws IOException {
		String responseString = getResponse(getUrl());
		parseResponse(responseString);
	}
	
}
