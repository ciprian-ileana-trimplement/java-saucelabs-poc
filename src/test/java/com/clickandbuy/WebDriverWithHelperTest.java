/**
 * 
 */
package com.clickandbuy;

import static junit.framework.Assert.assertEquals;

import java.net.URL;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.saucelabs.common.SauceOnDemandAuthentication;
import com.saucelabs.common.SauceOnDemandSessionIdProvider;
import com.saucelabs.junit.SauceOnDemandTestWatcher;

/**
 * Simple {@link RemoteWebDriver} test that demonstrates how to run your Selenium tests with <a href="http://saucelabs.com/ondemand">Sauce OnDemand</a>.
 * 
 * This test also includes the <a href="">Sauce JUnit</a> helper classes, which will use the Sauce REST API to mark the Sauce Job as passed/failed.
 * 
 * In order to use the {@link SauceOnDemandTestWatcher}, the test must implement the {@link SauceOnDemandSessionIdProvider} interface.
 * 
 * @author Ciprian I. Ileana
 * @author Nicolae Petridean
 */
public class WebDriverWithHelperTest implements SauceOnDemandSessionIdProvider {

	/**
	 * Constructs a {@link SauceOnDemandAuthentication} instance using the supplied user name/access key. To use the authentication supplied by environment variables or from an external file, use the no-arg
	 * {@link SauceOnDemandAuthentication} constructor.
	 */
	public SauceOnDemandAuthentication	authentication				= new SauceOnDemandAuthentication("ciprianileana", "54c66330-430e-4a32-be8a-ab7e2b418965");

	/**
	 * JUnit Rule which will mark the Sauce Job as passed/failed when the test succeeds or fails.
	 */
	public @Rule
	SauceOnDemandTestWatcher			resultReportingTestWatcher	= new SauceOnDemandTestWatcher(this, authentication);

	/**
	 * JUnit Rule which will record the test name of the current test. This is referenced when creating the {@link DesiredCapabilities}, so that the Sauce Job is created with the test name.
	 */
	public @Rule
	TestName							testName					= new TestName();

	private WebDriver					driver;

	private String						sessionId;

	@Before
	public void setUp() throws Exception {

		DesiredCapabilities capabillities = DesiredCapabilities.firefox();
		capabillities.setCapability("version", "5");
		capabillities.setCapability("platform", Platform.XP);
		capabillities.setCapability("name", testName.getMethodName());
		this.driver = new RemoteWebDriver(new URL("http://" + authentication.getUsername() + ":" + authentication.getAccessKey() + "@ondemand.saucelabs.com:80/wd/hub"), capabillities);
		this.sessionId = ((RemoteWebDriver) driver).getSessionId().toString();
	}

	@Override
	public String getSessionId() {
		return sessionId;
	}

	@Test
	public void webDriverWithHelper() throws Exception {
		driver.get("http://www.amazon.com/");
		assertEquals("Amazon.com: Online Shopping for Electronics, Apparel, Computers, Books, DVDs & more", driver.getTitle());
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
	}

}
