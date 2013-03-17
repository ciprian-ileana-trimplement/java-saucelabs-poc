/**
 * 
 */
package com.clickandbuy;

import static junit.framework.Assert.assertEquals;

import java.net.MalformedURLException;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.clickandbuy.core.parent.ParentTest;

/**
 * Simple {@link RemoteWebDriver} test that demonstrates how to run your Selenium tests with <a href="http://saucelabs.com/ondemand">Sauce OnDemand</a>. *
 * 
 * @author Ciprian I. Ileana
 * @author Nicolae Petridean
 */
public class WebDriverTest extends ParentTest {

	/**
	 * class logger.
	 */
	private static final Logger	logger	= Logger.getLogger(WebDriverTest.class);

	/**
	 * @throws MalformedURLException
	 */
	@Before
	public void setUp() throws MalformedURLException {
		// webDriver = prepareWebDriver();

		// DesiredCapabilities capabillities = DesiredCapabilities.firefox();
		// capabillities.setCapability("version", "5");
		// capabillities.setCapability("platform", Platform.XP);
		// this.driver = new RemoteWebDriver(new URL("http://martchouk:87335815-89fd-4022-94e0-9c268f5991f9@ondemand.saucelabs.com:80/wd/hub"), capabillities);
	}

	/**
	 * 
	 */
	@After
	public void tearDown() {
		getWebDriver().quit();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.clickandbuy.parent.ParentTest#doTest()
	 */
	// @Override
	public void doTest(WebDriver webDriver) {
		logger.info("Dummy test with WebDriver: [" + webDriver + "]!!!");
		getWebDriver().get("http://www.amazon.com/");
		assertEquals("Amazon.com: Online Shopping for Electronics, Apparel, Computers, Books, DVDs & more", webDriver.getTitle());

	}

	/* (non-Javadoc)
	 * @see com.saucelabs.common.SauceOnDemandSessionIdProvider#getSessionId()
	 */
	@Override
	public String getSessionId() {
		return "MySession_"+System.nanoTime();
	}
}
