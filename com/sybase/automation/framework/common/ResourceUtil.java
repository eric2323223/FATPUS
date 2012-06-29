package com.sybase.automation.framework.common;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * 
 * A collection of utilities on resources accessing
 * 
 * Examples: ResourceUtil.loadResource("\\global.properties");
 * System.out.println(ResourceUtil.get("legacy_project_root"));
 * 
 * @author xfu
 *  
 */
public class ResourceUtil {
	public static String projectRoot = ResourceUtil.class.getClassLoader()
			.getResource(".").getPath();

	private static String resourceFolder = Utilities.getProjectRoot() + "conf";

	private static Properties prop = new java.util.Properties();

	private static String filePath;

	/**
	 * @return Returns the prop.
	 */
	public static Properties getProp() {
		return prop;
	}

	/**
	 * 
	 * @param file
	 * @param key
	 * @return
	 */
	public static String getProperty(String file, String key) {
		loadResource(file);
		return (String) prop.get(key);
	}

	/**
	 * load the .properties file by relative path to \\project root\conf
	 *  
	 */
	public static void loadResource(String path) {

		if (!path.endsWith(".properties"))
			path = path + ".properties";
		try {
			//System.out.println("1:" + resourceFolder + path);
			filePath = resourceFolder + path;
			FileInputStream f = new java.io.FileInputStream(resourceFolder
					+ path);
			prop.load(f);
			f.close();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 
	 * get the value by key
	 *  
	 */

	public static String getProperty(String key) {
		String val = prop.getProperty(key);
		if (val == null) {
			throw new IllegalArgumentException("key {" + key
					+ "} does not exist in file {" + filePath + "}");
		}
		return val;
	}

}