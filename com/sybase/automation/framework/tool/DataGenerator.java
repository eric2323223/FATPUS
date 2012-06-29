package com.sybase.automation.framework.tool;

import java.util.Random;

import com.sybase.automation.framework.tool.DataDescription;

public class DataGenerator {

	static final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	static Random rnd = new Random();

	public static String generateString(DataDescription dd) {
		int len = dd.getLength();
		StringBuilder sb = new StringBuilder(len);
		for (int i = 0; i < len; i++)
			sb.append(AB.charAt(rnd.nextInt(AB.length())));
		return sb.toString();
	}

}
