package common;

import org.openqa.selenium.WebDriver;

import pageObject.NAB.SearchWeatherPageObject;

public class PageGeneratorManager {
	public static SearchWeatherPageObject getWeatherSearchPage(WebDriver driver) {
		return new SearchWeatherPageObject(driver);
	}	
}
