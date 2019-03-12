package sj.cucumber.po.steps;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import sj.cucumber.po.holder.TestHolder;

public class Hooks {
	
	private TestHolder testHolder;
	
	public Hooks(TestHolder testHolder) {
		this.testHolder = testHolder;
	}
	
	@Before
	public void setUp() {
		
	}
	
	@After
	public void tearDown() throws Exception {
		this.testHolder.getWebDriver().close();
		this.testHolder.getWebDriver().quit();
	}
	

}
