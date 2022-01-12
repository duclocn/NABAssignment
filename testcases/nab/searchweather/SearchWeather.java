package nab.searchweather;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import common.BaseTest;
import common.GlobalConstants;
import common.PageGeneratorManager;
import pageObject.NAB.SearchWeatherPageObject;
import pageUIs.SearchWeatherUI;

public class SearchWeather extends BaseTest {
	private WebDriver driver;
	private SearchWeatherPageObject searchWeatherPage;
	private String cityName, cityNameSearched;

	@Parameters({ "browser" })
	@BeforeClass
	public void beforeClass(String browserName) {
		Reporter.log("Pre-Conditon: Open browser '");
		driver = getBrowserDriver(browserName);
		searchWeatherPage = PageGeneratorManager.getWeatherSearchPage(driver);
		searchWeatherPage.openPageURL(driver, GlobalConstants.HOME_PAGE_URL);

		cityName = "Ha Noi";
		cityNameSearched = "Ha Noi, VN";
	}

	@Test
	public void Searching_01_Search_Weather_In_Your_City() {
		Reporter.log("Searching_01_Search_City - Step 01: Verify Search City textbox is available");
		log.info("Searching_01_Search - Step 01: Verify Search City textbox is available");
		searchWeatherPage.waitForElementClickable(driver, SearchWeatherUI.WEATHER_SEARCH_TEXTFIELD);

		Reporter.log("Searching_01_Search_City - Step 02: Input city name for searching");
		searchWeatherPage.inputToSearchTextBox(cityName);

		Reporter.log("Searching_01_Search_City - Step 03: Verify city name searched successfully");
		searchWeatherPage.waitForElementVisible(driver, SearchWeatherUI.CITY_NAME_RESULT_TEXT);
		Assert.assertEquals(searchWeatherPage.getTextElement(driver, SearchWeatherUI.CITY_NAME_RESULT_TEXT), cityNameSearched);

		Reporter.log("Searching_01_Search_City - Step 04: Click on city to view details");
		String temperatureSearched = getFirstSubString(searchWeatherPage.getTextElement(driver, SearchWeatherUI.TEMPERATURE_RESULT_TEXT), "°");
		searchWeatherPage.clickToElement(driver, SearchWeatherUI.CITY_NAME_RESULT_TEXT);

		Reporter.log("Searching_01_Search_City - Step 05: Verify date, city name, temperature");
		searchWeatherPage.isElementDisplayed(driver, SearchWeatherUI.CURRENT_DATE_TEXT);

		Assert.assertEquals(getFirstSubString(searchWeatherPage.getTextElement(driver, SearchWeatherUI.CURRENT_DATE_TEXT), ","), getCurrentDate());
		Assert.assertEquals(searchWeatherPage.getTextElement(driver, SearchWeatherUI.CITY_NAME_DETAIL_TEXT), "Hanoi, VN");
		Assert.assertEquals(getFirstSubString(searchWeatherPage.getTextElement(driver, SearchWeatherUI.TEMPERATURE_DETAIL_TEXT), "°"), temperatureSearched);
	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		Reporter.log("Post-Condition: Close browser");
		driver.quit();
	}
}
