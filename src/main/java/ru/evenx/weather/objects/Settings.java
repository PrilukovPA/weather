package ru.evenx.weather.objects;

/**
 * Настройки пользователя программы, передаваемые в контроллер
 *
 */
public class Settings {
	
	private int serviceId;
	private int cityId;

	public int getServiceId() {
		return serviceId;
	}

	public void setServiceId(int serviceId) {
		this.serviceId = serviceId;
	}

	public int getCityId() {
		return cityId;
	}

	public void setCityId(int cityId) {
		this.cityId = cityId;
	}
}
