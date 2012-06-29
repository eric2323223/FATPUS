package com.sybase.automation.framework.tool;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {

	public static boolean ifStrArrayContainStrArray(String[] A, String[] B) {
		for (int i = 0; i < B.length; i++) {
			if (!ifStrArrayContainStr(A, B[i]))
				return false;
		}
		return true;
	}

	public static boolean ifStrArrayContainStr(String[] A, String B) {
		for (int i = 0; i < A.length; i++) {
			if (A[i].equals(B))
				return true;
			;
		}
		return false;
	}

	public static int subArray(String[] A, String[] B) {
		int pos;
		if (A.length < B.length) {
			return -1;
		}
		for (int i = 0; i < A.length; i++) {
			if (A[i].equals(B[0])) {
				pos = i;
				int matchCount = 0;
				boolean flag = true;
				for (int j = 0; j < B.length; j++) {
					if (!A[i++].equals(B[j])) {
						flag = false;
						i = i - matchCount;
						break;
					}
					matchCount++;
				}
				if (flag) {
					return pos;
				}
				// i = i-matchCount;
			}
		}
		return -1;
	}

	/**
	 * Verify string segment, user can choose whether using regular expression
	 * 
	 * @param text
	 * @param segment
	 * @param regex
	 */
	public static boolean verifySegment(String text, String segment, boolean regex) {
		if (regex) {
			Matcher m = Pattern.compile(segment).matcher(text);
			return m.find();
		} else {
			return text.toLowerCase().trim().indexOf(
					segment.toLowerCase().trim()) != -1;
		}
	}
}
