package ru.evenx.weather.objects;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import ru.evenx.weather.interfaces.WeatherInfo;

public class WeatherServiceHelper {
	
	private DataSource dataSource;
	
	private static final Logger logger = LoggerFactory.getLogger("ru.evenx.logback");
	
	public static final int WORLD_WEATHER_ONLINE_ID = 1; // ID сервиса "World Weather Online" в базе.
	public static final int ACCU_WEATHER_ID = 2; // ID сервиса "AccuWeather.com" в базе.
	
	@Autowired   
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	/**
	 * Запрос списка заведенных сервисов
	 */
	public Map<String, String> getServiceList() {		
		Map<String, String> retVal = new HashMap<>();
		try (Connection con = dataSource.getConnection();
			 Statement	stmt = con.createStatement();
			 ResultSet rs = stmt.executeQuery("SELECT s.id, s.name FROM service s")) {
			
			while(rs.next()) {
				retVal.put(rs.getString("id"), rs.getString("name"));
			}
			
		} catch (Exception e) {
			logger.error("Service list fetching fault", e);
		}
		return retVal;
	}

	/**
	 * Запрос списка заведенных городов
	 */
	public Map<String, String> getCityList() {
		Map<String, String> retVal = new HashMap<>();
		try (Connection con = dataSource.getConnection();
			 Statement	stmt = con.createStatement();
			 ResultSet rs = stmt.executeQuery("SELECT c.id, c.name FROM city c")) {
			
			while(rs.next()) {
				retVal.put(rs.getString("id"), rs.getString("name"));
			}
			
		} catch (Exception e) {
			logger.error("City list fetching fault", e);
		}
		return retVal;
	}
	
	/**
	 * Запрос названия сервиса по id
	 */
	public String getServiceName(int id) {
		return getServiceList().get(String.valueOf(id));
	}
	
	/**
	 * Запрос названия города по id
	 */
	public String getCityName(int id) {
		return getCityList().get(String.valueOf(id));
	}
	
	/**
	 * Запрашивает URL сервиса
	 * @param serviceId id сервиса в базе
	 * @param cityId id города в базе
	 * @return URL сервиса
	 */
	public String getUrl(int serviceId, int cityId) {
		String retVal = null;
		try (Connection con = dataSource.getConnection();
			 PreparedStatement	stmt = createPreparedStatement(con, serviceId, cityId);
			 ResultSet rs = stmt.executeQuery()) {
			
			if (rs.first()) {							
				retVal = rs.getString("url");				
			} else {
				logger.error("URL not found");
			}
			
		} catch (Exception e) {
			logger.error("URL request fault", e);
		}
		return retVal;
	}
	
	/**
	 * Создание нужного сервиса
	 */
	public WeatherInfo createService(Settings settings) {
		
		WeatherInfo retVal = null;
		
		logger.info("settings.serviceId = {}, settings.cityId = {}", settings.getServiceId(), settings.getCityId());
		
		String serviceName = getServiceName(settings.getServiceId());
		String cityName = getCityName(settings.getCityId());
		String url = getUrl(settings.getServiceId(), settings.getCityId());
		
		logger.info("serviceName = {}, cityName = {}, url = {}", serviceName, cityName, url);
		
		switch(settings.getServiceId()) {
			case WORLD_WEATHER_ONLINE_ID: 
				retVal = new WorldService(serviceName, cityName, url);
				break;
			case ACCU_WEATHER_ID: 
				retVal = new AccuService(serviceName, cityName, url);
				break;			
		}
		
		return retVal;
	}
	
	/**
	 * Запрос данных у сервиса
	 */
	public WeatherInfo getWeatherInfo(Settings settings) {			
		WeatherInfo retVal = createService(settings);
		try {
			retVal.connect();
		} catch (Exception e) {
			logger.error("Getting service fault", e);
			return null;
		}		
		return retVal;
	} 
	
	private PreparedStatement createPreparedStatement(Connection con, int serviceId, int cityId) throws SQLException {
	    String sql = "SELECT sc.url FROM service_city sc WHERE sc.service_id = ? AND sc.city_id = ?";
	    PreparedStatement stmt = con.prepareStatement(sql);
	    stmt.setInt(1, serviceId);
		stmt.setInt(2, cityId);
	    return stmt;
	}
}
























