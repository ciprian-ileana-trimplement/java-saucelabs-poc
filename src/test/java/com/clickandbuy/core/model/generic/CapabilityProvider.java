//package com.clickandbuy.core.model.generic;
//
//import org.openqa.selenium.remote.DesiredCapabilities;
//
///**
// * Enumeration representing operating systems supported by SauceLabs
// * 
// * @author Ciprian I. Ileana
// */
//public enum CapabilityProvider {
//
//	OPERA("OPERA", DesiredCapabilities.opera()),
//	IE("IE", DesiredCapabilities.internetExplorer()),
//	FIREFOX("FIREFOX", DesiredCapabilities.firefox()),
//	SAFARI("SAFARI", DesiredCapabilities.safari()),
//	CHROME("CHROME", DesiredCapabilities.chrome()),
//	IPAD("IPAD", DesiredCapabilities.ipad()),
//	IPHONE("IPHONE", DesiredCapabilities.iphone()),
//	ANDROID("ANDROID", DesiredCapabilities.android());
//
//	private String				name;
//
//	private DesiredCapabilities	desiredCapabilities;
//
//	/**
//	 * Private constructor
//	 * 
//	 * @param name
//	 */
//	private CapabilityProvider(String name, DesiredCapabilities desiredCapabilities) {
//		this.name = name;
//	}
//
//	/**
//	 * Returns NotificationType by JSON name
//	 * 
//	 * @param name
//	 *            JSON name
//	 * @return NotificationType
//	 */
//	public static DesiredCapabilities determineDesiredCapabilities(String name) {
//		if ((name == null) || (name.trim().length() < 1)) {
//			throw new IllegalArgumentException("Can not determine DesiredCapabilities associated with name[" + name + "]");
//		}
//
//		for (CapabilityProvider capabilityProvider : values()) {
//			if (capabilityProvider.getName().trim().toUpperCase().equals(name.trim().toUpperCase())) {
//				return capabilityProvider.getDesiredCapabilities();
//			}
//		}
//
//		throw new IllegalArgumentException("Can not determine DesiredCapabilities associated with name[" + name + "]");
//	}
//
//	/**
//	 * @return the name
//	 */
//	public String getName() {
//		return name;
//	}
//
//	/**
//	 * @return the desiredCapabilities
//	 */
//	public DesiredCapabilities getDesiredCapabilities() {
//		return desiredCapabilities;
//	}
//
//}