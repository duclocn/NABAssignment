package common;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
	private long longTimeOut = 30;
	
	public static BasePage getBasePageObject() {
		return new BasePage();
	}
	
	public void openPageURL(WebDriver driver, String url) {
		driver.get(url);
	}
	
	private By getByLocator(String locatorType) {
		By by = null;
		if (locatorType.toLowerCase().startsWith("id=")) {
			by = By.id(locatorType.substring(3));
		} else if (locatorType.toLowerCase().startsWith("css=")) {
			by = By.cssSelector(locatorType.substring(4));
		} else if (locatorType.toLowerCase().startsWith("xpath=")) {
			by = By.xpath(locatorType.substring(6));
		} else if (locatorType.toLowerCase().startsWith("class=")) {
			by = By.className(locatorType.substring(6));
		} else if (locatorType.toLowerCase().startsWith("name=")) {
			by = By.name(locatorType.substring(5));
		} else {
			throw new RuntimeException("Wrong Locator Type");
		}
		return by;
	}
	
	private WebElement getWebElement(WebDriver driver, String locatorType) {
		return driver.findElement(getByLocator(locatorType));
	}
	
	
	public void clickToElement(WebDriver driver, String locatorType) {
		getWebElement(driver, locatorType).click();
	}
	
	
	public void sendkeyToElementAndHitEnter(WebDriver driver, String locatorType, String textValue) {
		WebElement element = getWebElement(driver, locatorType);
		element.clear();
		element.sendKeys(textValue);
		element.sendKeys(Keys.ENTER);
	}
	
	public String getTextElement(WebDriver driver, String locatorType) {
		return getWebElement(driver, locatorType).getText();
	}
	
	
	public void waitForElementVisible(WebDriver driver, String locatorType) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeOut);
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByLocator(locatorType)));
	}
	
	
	public void waitForElementClickable(WebDriver driver, String locatorType) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeOut);
		explicitWait.until(ExpectedConditions.elementToBeClickable(getByLocator(locatorType)));
	}
	
	public boolean isElementDisplayed(WebDriver driver, String locatorType) {
		try {
			return getWebElement(driver, locatorType).isDisplayed();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
