package sj.cucumber.po.factory;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import sj.cucumber.po.config.AppConfig;

public class WebDriverFactory {
	
	private static String CHROME = "chrome";
	private static String IE = "internetexplorer";
	private static String FIREFOX = "firefox";
	
	static WebDriver driver;
	
	public static WebDriver getWebDriver() throws Exception {
		if(driver == null) {
			synchronized (WebDriverFactory.class) {
				if(driver == null) {
					driver = createDriver();
			        driver.manage().timeouts().implicitlyWait(
			        		Integer.parseInt(AppConfig.getInstance().getProperty("implicit.wait", "5")), TimeUnit.SECONDS);

				}
			}
		}
		
		return driver;
	}

	private static WebDriver createDriver() throws Exception {
		String browser = AppConfig.getInstance().getProperty("browser", CHROME);
		if(FIREFOX.equalsIgnoreCase(browser)) {
			return new FirefoxDriver();
		}
		if(IE.equalsIgnoreCase(browser)) {
			return new InternetExplorerDriver();
					
		}
		System.setProperty("webdriver.chrome.driver",
				AppConfig.getInstance().getProperty("chrome.driver.path"));
		return new ChromeDriver();
	}

}
