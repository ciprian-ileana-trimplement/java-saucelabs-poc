/**
 * 
 */
package com.clickandbuy.core.parent;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.test.context.ContextConfiguration;

import com.clickandbuy.core.model.CapabilityConfiguraton;
import com.clickandbuy.core.model.ProfileConfiguration;
import com.clickandbuy.util.JSONConfigurationUtils;
import com.clickandbuy.util.TestUtil;
import com.saucelabs.common.SauceOnDemandAuthentication;
import com.saucelabs.common.SauceOnDemandSessionIdProvider;
import com.saucelabs.junit.SauceOnDemandTestWatcher;

/**
 * Abstract parent class for all SauceLabs related tests. It loads the spring context and injects the mandatory parameters in the test context. It is also responsible for deciding the active profile.
 * 
 * @author Ciprian I. Ileana
 * @author Nicolae Petridean
 * 
 */
@ContextConfiguration(locations = { "classpath:/META-INF/spring/clickandbuy.saucelabs.poc.spring.xml" })
public abstract class ParentTest implements SauceOnDemandSessionIdProvider {

	/**
	 * class logger.
	 */
	private static final Logger			logger						= Logger.getLogger(ParentTest.class);

	/**
	 * Constructs a {@link SauceOnDemandAuthentication} instance using the supplied user name/access key. To use the authentication supplied by environment variables or from an external file, use the no-arg
	 * {@link SauceOnDemandAuthentication} constructor.
	 */
	public SauceOnDemandAuthentication	authentication				= new SauceOnDemandAuthentication("ciprianileana", "54c66330-430e-4a32-be8a-ab7e2b418965");

	private static ProfileConfiguration	profileConfiguration		= null;

	private WebDriver					webDriver					= null;

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

	@BeforeClass
	public static void testSetup() throws JSONException, IOException {
		logger.info("testSetup");
		profileConfiguration = JSONConfigurationUtils.loadProfileConfiguration();

		logger.info("Loaded [" + profileConfiguration.getCapabilities().size() + "] capabilitie");
	}

	@AfterClass
	public static void testCleanup() {
		logger.info("testCleanup");
		// Teardown for data used by the unit tests
	}

	/**
	 * Implement the actual test (or call your actual test logic) in this method
	 * 
	 * @throws MalformedURLException
	 */
	@Test
	public void testExecution() throws MalformedURLException {
		for (CapabilityConfiguraton capabilityConfiguraton : profileConfiguration.getCapabilities()) {
			logger.info("testExecution - capabilityConfiguraton: [" + capabilityConfiguraton + "]");
			webDriver = prepareWebDriver(capabilityConfiguraton);
			logger.info("testExecution - webDriver: [" + webDriver + "]");
			doTest(webDriver);
		}
	}

	protected abstract void doTest(WebDriver webDriver);

	protected WebDriver prepareWebDriver(CapabilityConfiguraton capabilityConfiguraton) throws MalformedURLException {
		WebDriver webDriver = null;

		DesiredCapabilities desiredCapabilities = TestUtil.prepareDesiredCapabilities(capabilityConfiguraton);

		// String remoteWebDriverURL = saucelabsProtocol + "://" + saucelabsAccount + ":" + saucelabsAccessKey + "@" + saucelabsDomain + ":" + saucelabsPort + saucelabsUrl;
		// webDriver = new RemoteWebDriver(new URL(remoteWebDriverURL), desiredCapabilities);

		webDriver = new RemoteWebDriver(new URL("http://" + authentication.getUsername() + ":" + authentication.getAccessKey() + "@ondemand.saucelabs.com:80/wd/hub"), desiredCapabilities);
		return webDriver;
	}

	/**
	 * @return the webDriver
	 */
	public WebDriver getWebDriver() {
		return webDriver;
	}

	/**
	 * @return the profileConfiguration
	 */
	public static ProfileConfiguration getProfileConfiguration() {
		return profileConfiguration;
	}
}
