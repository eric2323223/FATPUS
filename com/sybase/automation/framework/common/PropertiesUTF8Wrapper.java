/*
 * Created on May 25, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.sybase.automation.framework.common;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

/**
 * TODO Add your comments for the class here
 * 
 * @author szhuang
 * @version
 */

public class PropertiesUTF8Wrapper extends Properties {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String getProperty(String key, String defaultValue) {
		String result = super.getProperty(key, defaultValue);
		try {
			byte[] bytes = result.getBytes("8859_1");
			result = new String(bytes, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return result;
	}

	public String getProperty(String key) {
		String result = super.getProperty(key);
		try {
			byte[] bytes = result.getBytes("8859_1");
			result = new String(bytes, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return result;
	}

	public static void main(String[] args) throws Exception, IOException {
		PropertiesUTF8Wrapper prop = new PropertiesUTF8Wrapper();
		prop.load(new FileInputStream("c:/test.properties"));
		System.out.println(prop.getProperty("a"));
	}
}