/**
 * 
 */
package com.clickandbuy.util;

import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.clickandbuy.core.model.CapabilityConfiguraton;

/**
 * Collection of various utils methods used across tests
 * 
 * @author Ciprian I. Ileana
 * @author Nicolae Petridean
 * 
 */
public class TestUtil {
	
	/**
	 * Prevent instantiation of utils class
	 */
	private TestUtil() {
	}

	public static DesiredCapabilities prepareDesiredCapabilities(CapabilityConfiguraton capabilityConfiguraton) {
//		DesiredCapabilities desiredCapabilities = CapabilityProvider.determineDesiredCapabilities(capabilityConfiguraton.getBrowserName());
		
		DesiredCapabilities desiredCapabilities = new DesiredCapabilities(capabilityConfiguraton.getBrowserName(), capabilityConfiguraton.getBrowserVersion(), Platform.valueOf(capabilityConfiguraton.getPlatform()));

//		desiredCapabilities = DesiredCapabilities.firefox();

//		desiredCapabilities.setCapability("version", capabilityConfiguraton.getBrowserVersion());
//		desiredCapabilities.setCapability("platform", capabilityConfiguraton.getPlatform());

		return desiredCapabilities;

	}

}
