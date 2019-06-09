package ru.evenx.weather.objects;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import ru.evenx.weather.interfaces.WeatherInfo;

/**
 * Абстрактный погодный сервис
 *
 */
public abstract class WeatherService implements WeatherInfo {
	
	private static final String NO_VALUE = "не определено";
	private String temperature = NO_VALUE; // Температура воздуха
	private String humidity = NO_VALUE; // Относительная влажность
	private String pressure = NO_VALUE; // Атмосферное давление
	private String cityName;
	private String serviceName;
	private String url;
	private static HttpClient httpClient = HttpClientBuilder.create().build();
	
	public WeatherService(String serviceName, String cityName, String url) {
		super();
		this.serviceName = serviceName;
		this.cityName = cityName;		
		this.url = url;
	}
	
	/**
	 * Получает ответ от погодного сервиса на переданный URL
	 */
	public static String getResponse(String url) throws IOException {
        HttpGet request = new HttpGet(url);
        HttpResponse response = httpClient.execute(request);
		if (response.getStatusLine().getStatusCode() != 200) {
			throw new IOException(response.getStatusLine().getReasonPhrase());
		}
		return EntityUtils.toString(response.getEntity());
	}
	
	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	@Override
	public String getTemperature() {
		return temperature;
	}
	
	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}

	@Override
	public String getHumidity() {
		return humidity;
	}
	
	public void setHumidity(String humidity) {
		this.humidity = humidity;
	}

	@Override
	public String getPressure() {
		return pressure;
	}
	
	public void setPressure(String pressure) {
		this.pressure = pressure;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}	

}
