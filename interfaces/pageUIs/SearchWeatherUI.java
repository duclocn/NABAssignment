package pageUIs;

public class SearchWeatherUI {
	public static final String WEATHER_SEARCH_TEXTFIELD = "xpath=//div[@id='desktop-menu']//input[@placeholder='Weather in your city']";
	public static final String CITY_NAME_RESULT_TEXT = "xpath=//a[contains(text(),'Ha Noi')]";
	public static final String TEMPERATURE_RESULT_TEXT = "css=span[class*='badge-info']";
	public static final String LOADING_ICON = "css=div[class='owm-loader']";
	public static final String NO_PRECIPITATION_TEXT = "xpath=//div[text()='No precipitation']";
	public static final String TEMPERATURE_DETAIL_TEXT = "xpath=//div[@class='current-temp']//span";
	public static final String CITY_NAME_DETAIL_TEXT = "css=div[class='section-content'] h2";
	public static final String CURRENT_DATE_TEXT = "css=div[class='section-content'] span[class='orange-text']";
}
