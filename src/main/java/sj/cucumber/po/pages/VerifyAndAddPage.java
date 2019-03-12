package sj.cucumber.po.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import sj.cucumber.po.config.AppConfig;

public class VerifyAndAddPage {
	WebDriver driver;
	
	public VerifyAndAddPage(WebDriver driver) {
		this.driver=driver;
		
	}
	
	@FindAll({
		@FindBy(how = How.XPATH, using="//*[starts-with(@id, 'txt_val_')]")
	})
	private List<WebElement> valueElementList;
	
	@FindAll({
		@FindBy(how = How.XPATH, using="//*[starts-with(@id, 'lbl_val_')]")
	})
	private List<WebElement> labelElementList;

	
	@FindBy(how=How.ID, using = "lbl_ttl_val")
	private WebElement lblTtlElement;
	
	@FindBy(how=How.ID, using = "txt_ttl_val")
	private WebElement txtTtlElement;
	
	public List<WebElement> getValueElementList() {
		return valueElementList;
	}

	public List<WebElement> getLabelElementList() {
		return labelElementList;
	}

	public WebElement getLblTtlElement() {
		return lblTtlElement;
	}

	public WebElement getTxtTtlElement() {
		return txtTtlElement;
	}
	
	public void openUrl() throws Exception{
		driver.navigate().to(AppConfig.getInstance().getProperty("url"));
		
	}

}
