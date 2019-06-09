package weather;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

import javax.sql.DataSource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import ru.evenx.weather.controllers.LoginController;
import ru.evenx.weather.interfaces.WeatherInfo;
import ru.evenx.weather.objects.AccuService;
import ru.evenx.weather.objects.Settings;
import ru.evenx.weather.objects.WeatherServiceHelper;
import ru.evenx.weather.objects.WorldService;

public class WeatherServiceHelperTest {
	private DataSource ds;
	
	@Before
	public void setUp() {
		ds = dataSource();		
	}
	
	@After
	public void tearDown() {
		((EmbeddedDatabase)ds).shutdown();		
	}
	
	public DataSource dataSource() {
		EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
		EmbeddedDatabase db = builder
			.setType(EmbeddedDatabaseType.H2) 
			.addScript("weather_schema.sql")
			.addScript("weather_data_test.sql")			
			.build();		
		return db;
	}

	@Test
	public void testCityList() {
		WeatherServiceHelper helper = new WeatherServiceHelper();
		helper.setDataSource(ds);
		
		LoginController lc = new LoginController();
		lc.setWeatherServiceHelper(helper);
		
		Map<String, String> map = lc.getCityList();
		String cityName = map.get("1");
		assertEquals(cityName, "Екатеринбург");		
		assertEquals(map.size(), 3);
	}
	
	@Test
	public void testServiceList() {
		WeatherServiceHelper helper = new WeatherServiceHelper();
		helper.setDataSource(ds);
		
		LoginController lc = new LoginController();
		lc.setWeatherServiceHelper(helper);
		
		Map<String, String> map = lc.getServiceList();
		String serviceName = map.get("1");
		assertEquals(serviceName, "World Weather Online");
		assertEquals(map.size(), 2);
	}
	
	@Test
	public void testGetServiceName() {
		WeatherServiceHelper helper = new WeatherServiceHelper();
		helper.setDataSource(ds);		
		assertEquals(helper.getServiceName(1), "World Weather Online");		
	}
	
	@Test
	public void testGetCityName() {
		WeatherServiceHelper helper = new WeatherServiceHelper();
		helper.setDataSource(ds);		
		assertEquals(helper.getCityName(1), "Екатеринбург");		
	}
	
	@Test
	public void testGetUrl() {
		WeatherServiceHelper helper = new WeatherServiceHelper();
		helper.setDataSource(ds);		
		assertEquals(helper.getUrl(1, 1), "http://api.worldweatheronline.com/premium/v1/weather.ashx?key=3700b5ba25794185a6d54121190406&q=Ekaterinburg&format=json&fx=no&mca=no");		
	}
	
	@Test
	public void testCreateService() {
		WeatherServiceHelper helper = new WeatherServiceHelper();
		helper.setDataSource(ds);		
		Settings settings = new Settings();
		settings.setServiceId(1);
		settings.setCityId(1);		
		WeatherInfo wi = helper.createService(settings);
		assertEquals(wi instanceof WorldService, true);
		assertEquals(wi.getServiceName(), "World Weather Online");		
		assertEquals(wi.getCityName(), "Екатеринбург");
	}
	
	@Test
	public void testGetWeatherInfo() {
		WeatherServiceHelper helper = new WeatherServiceHelper();
		helper.setDataSource(ds);		
		Settings settings = new Settings();
		settings.setServiceId(0);
		settings.setCityId(0);		
		WeatherInfo wi = helper.getWeatherInfo(settings);
		assertEquals(wi, null);		
	}
	
	@Test
	public void testParseResponseACC() {
		AccuService serv = new AccuService(null, null, null);
		StringBuilder sb = new StringBuilder();
		try {
			Files.lines(Paths.get("src\\test\\resources\\\\acc.txt"), StandardCharsets.UTF_8).forEach(l -> sb.append(l));
			serv.parseResponse(sb.toString());
			assertEquals(serv.getHumidity().startsWith("46"), true);
			assertEquals(serv.getPressure().startsWith("1016.0"), true);
			assertEquals(serv.getTemperature().startsWith("20.5"), true);
		} catch (IOException e) {
			fail();
		}		
	}
	
	@Test
	public void testParseResponseWWO() {
		WorldService serv = new WorldService(null, null, null);
		StringBuilder sb = new StringBuilder();
		try {
			Files.lines(Paths.get("src\\test\\resources\\wwo.txt"), StandardCharsets.UTF_8).forEach(l -> sb.append(l));
			serv.parseResponse(sb.toString());
			assertEquals(serv.getHumidity().startsWith("64"), true);
			assertEquals(serv.getPressure().startsWith("1016"), true);
			assertEquals(serv.getTemperature().startsWith("18"), true);
		} catch (IOException e) {
			fail();
		}		
	}
}
