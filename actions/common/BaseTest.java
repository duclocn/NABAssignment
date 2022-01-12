package common;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	private WebDriver driver;
	protected final Log log;
	private String projectPath = System.getProperty("user.dir");

	protected BaseTest() {
		log = LogFactory.getLog(getClass());
	}

	protected WebDriver getBrowserDriver(String browserName) {
		switch (browserName) {
		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			System.setProperty(FirefoxDriver.SystemProperty.DRIVER_USE_MARIONETTE, "true");
			System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE,
					projectPath + "\\monitorLogs\\Firefox.log");

			FirefoxProfile profileFF = new FirefoxProfile();
			profileFF.setPreference("geo.enabled", false);

			FirefoxOptions optionFF = new FirefoxOptions();
			optionFF.setProfile(profileFF);

			driver = new FirefoxDriver(optionFF);

			break;

		case "chrome":
			WebDriverManager.chromedriver().setup();
			System.setProperty("webdriver.chrome.args", "--disable-logging");
			System.setProperty("webdriver.chrome.silentOutput", "true");
			System.setProperty(ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY,
					projectPath + "\\monitorLogs\\Chrome.log");

			ChromeOptions options = new ChromeOptions();

			options.addArguments("--disable-geolocation");
			options.setExperimentalOption("useAutomationExtension", false);
			options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));

			driver = new ChromeDriver(options);
			break;

		default:
			System.out.println("Please input correct browser");
			break;
		}

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		return driver;
	}

	public String getFirstSubString(String str, String splitChar) {
		String splitStr[] = str.split(splitChar);
		return splitStr[0];
	}

	public static String getCurrentDate() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MMM dd");
		LocalDateTime now = LocalDateTime.now();
		return dtf.format(now);
	}

}
