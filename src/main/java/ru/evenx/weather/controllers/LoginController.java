package ru.evenx.weather.controllers;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ru.evenx.weather.interfaces.WeatherInfo;
import ru.evenx.weather.objects.Settings;
import ru.evenx.weather.objects.WeatherServiceHelper;

@Controller
public class LoginController {
	
	private WeatherServiceHelper helper;  
	
	@Autowired   
	public void setWeatherServiceHelper(WeatherServiceHelper helper) {
		this.helper = helper;
	}

	/**
	 * Главная страница
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView main(HttpServletRequest request) {		
		Settings settings = new Settings();
		return new ModelAndView("main", "settings", settings);
	}

	/**
	 * Страница текущей погоды
	 */
	@RequestMapping(value = "/current", method = RequestMethod.POST)
	public ModelAndView current(@ModelAttribute("settings") Settings settings) {		
		WeatherInfo service = helper.getWeatherInfo(settings);
		if (service != null) {
			return new ModelAndView("weather", "service", service);
		} else {
			return new ModelAndView("error");
		}
	}
	
	/**
	 * Список заведенных сервисов
	 */
	@ModelAttribute("serviceList")
	public Map<String, String> getServiceList() {
	    return helper.getServiceList();
	}
	
	/**
	 * Список заведенных городов
	 * @return
	 */
	@ModelAttribute("cityList")
	public Map<String, String> getCityList() {
		return helper.getCityList();
	}

}
