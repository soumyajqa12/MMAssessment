package sj.cucumber.po.holder;

import org.openqa.selenium.WebDriver;

import sj.cucumber.po.factory.WebDriverFactory;

public class TestHolder {
	
	WebDriver driver; 
	
	public TestHolder() {
	}
	
	public WebDriver getWebDriver() throws Exception {
		if(driver == null) {
			driver = WebDriverFactory.getWebDriver(); 
			//driver.manage().window().setSize(new Dimension(500, 500));
		}
		return driver;
	}

}
