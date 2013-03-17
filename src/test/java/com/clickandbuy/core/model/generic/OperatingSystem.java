//package com.clickandbuy.core.model.generic;
//
///**
// * Enumeration representing operating systems supported by SauceLabs
// * 
// * @author Ciprian I. Ileana
// */
//public enum OperatingSystem {
//
//	Badge("badge"), Tip("tip"), TipAlert("tipAlert"), Leaderboard("leaderboard"), Mayorship("mayorship"), Message("message"), Score("score");
//
//	/**
//	 * Private constructor
//	 * 
//	 * @param name
//	 */
//	private OperatingSystem(String name) {
//		this.name = name;
//	}
//
//	/**
//	 * @return
//	 */
//	public String getName() {
//		return name;
//	}
//
//	/**
//	 * Returns NotificationType by JSON name
//	 * 
//	 * @param name
//	 *            JSON name
//	 * @return NotificationType
//	 */
//	public static OperatingSystem getByName(String name) {
//		for (OperatingSystem operatingSystem : values()) {
//			if (operatingSystem.getName().equals(name)) {
//				return operatingSystem;
//			}
//		}
//
//		return null;
//	}
//
//	private String	name;
//}
