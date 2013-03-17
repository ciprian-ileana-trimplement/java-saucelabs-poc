/**
 * 
 */
package com.clickandbuy.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.clickandbuy.core.model.CapabilityConfiguraton;
import com.clickandbuy.core.model.ProfileConfiguration;

/**
 * @author Ciprian I. Ileana
 * 
 */
public class JSONConfigurationUtils {

	/**
	 * class logger.
	 */
	private static final Logger	logger	= Logger.getLogger(JSONConfigurationUtils.class);

	/**
	 * load a JSON file from the resource or file system.
	 * 
	 * @param resource
	 * @return A JSONObject representing the passed resource argument.
	 * @throws Exception
	 * @throws IOException
	 * @throws JSONException
	 */
	private static JSONObject loadJSON(String resource) throws FileNotFoundException, JSONException {
		InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream(resource);

		if (in == null) {
			try {
				in = new FileInputStream(resource);
			} catch (FileNotFoundException e) {
				// ignore
			}
		}

		if (in == null) {
			throw new RuntimeException(resource + " is not a valid resource.");
		}

		StringBuilder b = new StringBuilder();
		InputStreamReader inputreader = new InputStreamReader(in);
		BufferedReader buffreader = new BufferedReader(inputreader);
		String line;

		try {
			while ((line = buffreader.readLine()) != null) {
				b.append(line);
			}
		} catch (IOException e) {
			throw new FileNotFoundException("Cannot read file " + resource + " , " + e.getMessage());
		} finally {
			try {
				buffreader.close();
				inputreader.close();
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		String json = b.toString();
		JSONObject o;
		o = new JSONObject(json);

		return o;
	}

	public static ProfileConfiguration loadProfileConfiguration() throws JSONException, IOException {
		ProfileConfiguration profileConfiguration = null;
		JSONObject jsonObject = loadJSON("\\profiles\\development2\\clickandbuy.saucelabs.poc.profile.DEVELOPMENT2.json");

		if (jsonObject.has("capabilities")) {
			logger.info("JSON Configuration - capabilities - CHECKED");
			logger.info(jsonObject.get("capabilities"));
			JSONArray capabilities = jsonObject.getJSONArray("capabilities");
			if (capabilities.length() >= 1) {
				profileConfiguration = new ProfileConfiguration();
				for (int i = 0; i < capabilities.length(); i++) {
					if (!capabilities.isNull(i)) {
						JSONObject capability = capabilities.getJSONObject(i);

						CapabilityConfiguraton capabilityConfiguraton = new CapabilityConfiguraton();

						String browserName = capability.getString("browserName");
						String browserVersion = capability.getString("version");
						String platform = capability.getString("platform");

						capabilityConfiguraton.setBrowserName(browserName);
						capabilityConfiguraton.setBrowserVersion(browserVersion);
						capabilityConfiguraton.setPlatform(platform);

						profileConfiguration.getCapabilities().add(capabilityConfiguraton);

						logger.info(capabilityConfiguraton);
					} else {
						logger.error("JSON Configuration - capabilities - ignoring empty capability!");
					}
				}

				if (profileConfiguration.getCapabilities().isEmpty()) {
					logger.error("JSON Configuration - capabilities - No capability configuration found!");
					throw new IllegalArgumentException("JSON Configuration - capabilities - No capability configuration found!");
				}
			} else {
				logger.error("JSON Configuration - capabilities - EMPTY!");
				throw new IllegalArgumentException("JSON Configuration - capabilities - EMPTY!");
			}
		} else {
			logger.error("JSON Configuration - capabilities - NOT PRESENT");
			throw new IllegalArgumentException("JSON Configuration - capabilities - NOT PRESENT");
		}

		return profileConfiguration;
	}

	public static void main(String[] args) throws Exception {
		ProfileConfiguration profileConfiguration = loadProfileConfiguration();
	}
}