/**
 * 
 */
package com.sybase.automation.framework.tool;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * @author dongxu
 */
public class Cfg {

	public static Cfg BDOC = new Cfg(
			"testscript/UEPBinaryDoc/conf/bdoc_1_0.properties");
	
	public static Cfg DPLY = new Cfg(
			"testscript/UEPDeploy/conf/Deployment.properties");

	public static Cfg DB = new Cfg(
			"testscript/UEPDataBaseIntegration/conf/db_1_0.properties");
	
	public static Cfg MultiS = new Cfg("testscript/UEPMulti_Select/conf/Cfg");

	public static Cfg COMMON = new Cfg("component/common/common_1_0.properties");

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(BDOC.getProjPath());
		for (Enumeration enumer = BDOC.props.propertyNames(); enumer
				.hasMoreElements();) {
			String name = (String) enumer.nextElement();
			System.out.println(name + "=" + BDOC.get(name));
		}
	}

	/** config holder */
	private Properties props;

	/**
	 * Construct a Cfg with specified config file path
	 * 
	 * @param path
	 */
	public Cfg(String path) {
		init(path);
	}

	/**
	 * get a config
	 * 
	 * @param name
	 * @return if config not found, null will be returned
	 */
	public String get(String name) {
		return props.getProperty(name);
	}

	/**
	 * get a int config
	 * 
	 * @param name
	 * @return if config can't transform to int, number format exception will be
	 *         thrown
	 */
	public int getInt(String name) {
		return Integer.parseInt(get(name));
	}

	/**
	 * Retrieve base path of current project
	 * 
	 * @return
	 */
	public String getProjPath() {
		String urlPath = this.getClass().getResource("/").getPath();
		String rst = "";
		try {
			rst = URLDecoder.decode(urlPath, "utf-8");
			if (rst.charAt(0) == '/') {
				rst = rst.substring(1, rst.length());
			}
		} catch (UnsupportedEncodingException e) {
		}
		return rst;
	}

	/**
	 * Implementation of constructor
	 * 
	 * @param path
	 */
	private void init(String path) {
		props = new Properties();
		try {
			reload(path);
		} catch (IOException e) {
			System.out.println("Load config failed!");
		}
	}

	/**
	 * Reload current config with a new config file
	 * 
	 * @param path
	 *            relative to project base
	 * @throws IOException
	 */
	public void reload(String path) throws IOException {
		InputStream in = new BufferedInputStream(new FileInputStream(
				getProjPath() + path));
		props.load(in);
	}

	/**
	 * Append a config file
	 * 
	 * @param path
	 *            new config file path
	 * @param overwrite
	 *            whether overwrite existing config
	 * @throws IOException
	 */
	public void append(String path, boolean overwrite) throws IOException {
		InputStream in = new BufferedInputStream(new FileInputStream(
				getProjPath() + path));
		Properties newProps = new Properties();
		newProps.load(in);
		for (Iterator iter = newProps.entrySet().iterator(); iter.hasNext();) {
			Map.Entry me = (Map.Entry) iter.next();
			if (overwrite || !props.containsKey(me.getKey())) {
				String key = new String((String) me.getKey());
				String value = new String((String) me.getValue());
				props.put(key, value);
			}
		}
	}

	/**
	 * Get the Properties object, which contains configs
	 * 
	 * @return
	 */
	public Properties getProperties() {
		return props;
	}

	public static String gStr(Map m, String key, String defaultValue) {
		Object rst = m.get(key);
		if (rst == null) {
			if (defaultValue == null) {
				throw new RuntimeException("DB.g: key not contained!");
			} else {
				rst = defaultValue;
			}
		}
		return (String) rst;
	}

	public static boolean gBool(Map m, String key, boolean defaultValue) {
		return Boolean
				.parseBoolean(gStr(m, key, Boolean.toString(defaultValue)));
	}
}