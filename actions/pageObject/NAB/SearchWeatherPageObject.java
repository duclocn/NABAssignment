package pageObject.NAB;

import org.openqa.selenium.WebDriver;

import common.BasePage;
import pageUIs.SearchWeatherUI;

public class SearchWeatherPageObject extends BasePage {
	private WebDriver driver;
	
	public SearchWeatherPageObject(WebDriver driver) {
		this.driver = driver;
	}
	
	public void inputToSearchTextBox(String cityName) {
		waitForElementClickable(driver, SearchWeatherUI.WEATHER_SEARCH_TEXTFIELD);
		sendkeyToElementAndHitEnter(driver, SearchWeatherUI.WEATHER_SEARCH_TEXTFIELD, cityName);
	}
}
