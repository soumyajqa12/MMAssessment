package sj.cucumber.po.steps;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import sj.cucumber.po.holder.TestHolder;
import sj.cucumber.po.pages.VerifyAndAddPage;

public class VerifyAndAddSteps {

	WebDriver driver = null; 
    private TestHolder testHolder;
    private VerifyAndAddPage page;
    NumberFormat format = NumberFormat.getCurrencyInstance(Locale.US);
    Pattern currencyPattern = Pattern.compile("^\\$(\\d{1,3}(\\,\\d{3})*|(\\d+))(\\.\\d{2})?$");
	
	public VerifyAndAddSteps(TestHolder testHolder) {
		this.testHolder = testHolder;
	}

	@Given("^I have open the browser$") 
	public void openBrowser() throws Exception { 
		driver = testHolder.getWebDriver();
	} 

	@When("^I open the Mass Mutual Assessment URL$") 
	public void goToMassMutualAssessmentPage() throws Exception { 
		page=PageFactory.initElements(driver, VerifyAndAddPage.class);
		page.openUrl();
	} 

	@Then("^All the labels and values should exist$") 
	public void labelsAndvalues() { 
		 
		 Assert.assertTrue(page.getLblTtlElement().isDisplayed());
		 Assert.assertTrue(page.getTxtTtlElement().isDisplayed());
		
	}
	
	@Then("^Verify the right count of values appear on the screen$")
	public void rightCount() {
		Assert.assertEquals("Count of labels and values are not matching.", page.getLabelElementList().size(), page.getValueElementList().size());
		
		int i=0;
		for(WebElement valueElement : page.getValueElementList()) {
			Assert.assertTrue("valueElement " + i +" is not displayed", valueElement.isDisplayed());
			i++;
		}
		
		i=0;
		for(WebElement labelElement : page.getLabelElementList()) {
			Assert.assertTrue("labelElement " + i +" is not displayed", labelElement.isDisplayed());
			i++;
		}
		
		Assert.assertTrue("Total labelElement is not displayed", page.getLblTtlElement().isDisplayed());
		Assert.assertTrue("Total valueElement is not displayed", page.getTxtTtlElement().isDisplayed());
	}
	
	@Then("^Verify the values are formatted as currencies$")
	public void currencyFormat() {
		for(WebElement valueElement : page.getValueElementList()) {
			assertCurrencyFormat(valueElement.getAttribute("value"), valueElement.getAttribute("id"));
		}
		assertCurrencyFormat(page.getTxtTtlElement().getAttribute("value"), page.getTxtTtlElement().getAttribute("id"));
	}

	private void assertCurrencyFormat(String txt, String elementName) {
		Matcher matcher = currencyPattern.matcher(txt);
		Assert.assertTrue("Not in currency format" + elementName, matcher.matches());
	}
	
	@Then("^Verify the values on the screen are greater than 0$")
	public void greaterThanZero() throws ParseException {
		for(WebElement valueElement : page.getValueElementList()) {
			assertGreaterthanZero(valueElement.getAttribute("value"), valueElement.getAttribute("id"));
		}
		assertGreaterthanZero(page.getTxtTtlElement().getAttribute("value"), page.getTxtTtlElement().getAttribute("id"));
	}

	private void assertGreaterthanZero(String text, String elementName) throws ParseException {
		double value = format.parse(text).doubleValue();
		Assert.assertTrue("Not greater than zero" + elementName, (value>0));
	}
	@Then("^Verify the total balance is correct based on the values listed on the screen$")
	public void correctBalance() throws ParseException {
		double expectedSum = format.parse(page.getTxtTtlElement().getAttribute("value")).doubleValue();
		double actualSum = getSum(page.getValueElementList());
		Assert.assertEquals(expectedSum, actualSum, 0);
	}
		
	@Then("^Verify the total balance matches the sum of the values$")
	public void totalBalance() throws ParseException {
		double expectedSum = format.parse(page.getTxtTtlElement().getAttribute("value")).doubleValue();
		double actualSum = getSum(page.getValueElementList());
		Assert.assertEquals("Total balance is not matching the sum of the values.", expectedSum, actualSum, 0);
	}
	
	private double getSum(List<WebElement> valueElementList) throws ParseException {
		double result = 0;
		for(WebElement valueElement : valueElementList) {
			result = format.parse(valueElement.getAttribute("value")).doubleValue() + result;
		}
		return result;
	}
}
