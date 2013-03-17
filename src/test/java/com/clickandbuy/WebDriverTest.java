/**
 * 
 */
package com.clickandbuy;

import static junit.framework.Assert.assertEquals;

import java.net.MalformedURLException;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.clickandbuy.core.model.CapabilityConfiguraton;
import com.clickandbuy.core.parent.ParentTest;
import com.saucelabs.common.SauceOnDemandSessionIdProvider;
import com.saucelabs.junit.SauceOnDemandTestWatcher;

/**
 * Simple {@link RemoteWebDriver} test that demonstrates how to run your Selenium tests with <a href="http://saucelabs.com/ondemand">Sauce OnDemand</a>. *
 * 
 * @author Ciprian I. Ileana
 * @author Nicolae Petridean
 */
public class WebDriverTest extends ParentTest implements SauceOnDemandSessionIdProvider {

	/**
	 * class logger.
	 */
	private static final Logger	logger						= Logger.getLogger(WebDriverTest.class);

	private WebDriver						webDriver				= null;

	/**
	 * JUnit Rule which will mark the Sauce Job as passed/failed when the test succeeds or fails.
	 */
	public @Rule
	SauceOnDemandTestWatcher	resultReportingTestWatcher	= new SauceOnDemandTestWatcher(this, authentication);

	private String				sessionId;

	/**
	 * @throws MalformedURLException
	 */
	@Before
	public void setUp() throws MalformedURLException {
	}

	/**
	 * 
	 */
	@After
	public void tearDown() {
	}

	@Override
	public String getSessionId() {
		return sessionId;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.clickandbuy.core.parent.ParentTest#doTest(org.openqa.selenium.WebDriver)
	 */
	@Override
	public void doTest(CapabilityConfiguraton capabilityConfiguraton) throws MalformedURLException {
		//tearup
		webDriver = prepareWebDriver(capabilityConfiguraton);
		sessionId = ((RemoteWebDriver) webDriver).getSessionId().toString();

		//actual test
		logger.info("Dummy test with WebDriver: [" + webDriver + "]!!!");
		webDriver.get("http://www.amazon.com/");
		assertEquals("Amazon.com: Online Shopping for Electronics, Apparel, Computers, Books, DVDs & more", webDriver.getTitle());
		
		//teardown
		webDriver.quit();
	}

}
