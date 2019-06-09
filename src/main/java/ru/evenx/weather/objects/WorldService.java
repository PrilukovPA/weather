package ru.evenx.weather.objects;

import java.io.IOException;

import com.jayway.jsonpath.JsonPath;

/**
 * Доступ к сервису "World Weather Online"
 *
 */
public class WorldService extends WeatherService {
	
	public WorldService(String serviceName, String cityName, String url) {
		super(serviceName, cityName, url);
	}
	
	public void parseResponse(String response) {
		String tmp = JsonPath.read(response, "$.data.current_condition.[0].temp_C").toString();
		String hum = JsonPath.read(response, "$.data.current_condition.[0].humidity").toString();
		String pre = JsonPath.read(response, "$.data.current_condition.[0].pressure").toString();
		setTemperature(tmp + " \u00B0C");
		setHumidity(hum + "%");
		setPressure(pre + " мбар");
	}

	public void connect() throws IOException {		
		String responseString = getResponse(getUrl());
		parseResponse(responseString);
	}

}
