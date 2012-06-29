/*
 * Created on Jul 5, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.sybase.automation.framework.widget.temp;

import com.rational.test.ft.object.interfaces.TestObject;

/**
 * @author xfu
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class MenuItem {

	private String acceleratorKey;

	private String itemText;

	private String shortCut;

	/**
	 * @param string
	 */
	public MenuItem(String item) {
		if (item == null) {
			acceleratorKey = null;
			itemText = null;
			shortCut = null;
			return;
		}

		int keyIdx = item.indexOf("&");
		int shortCutIdx = item.indexOf("\t");
		//get acceleratorKey
		if (keyIdx == -1) {
			acceleratorKey = null;
		} else {
			acceleratorKey = item.substring(keyIdx + 1, keyIdx + 2);
		}
		//get shortCut
		if (shortCutIdx == -1) {
			shortCut = null;
		} else {
			shortCut = item.substring(shortCutIdx + 1);
		}
		//get Item Text
		int startIdx = 0;
		int endIdx = item.length();
		if (shortCutIdx != -1)
			endIdx = shortCutIdx;
		itemText = item.substring(startIdx, endIdx);

	}

	/**
	 * @return
	 */
	public String getAcceleratorKey() {
		// TODO Auto-generated method stub
		return acceleratorKey;
	}

	/**
	 * @return
	 */
	public String getItemText() {
		// TODO Auto-generated method stub
		return itemText;
	}

	/**
	 * @return
	 */
	public String getShortCut() {
		// TODO Auto-generated method stub
		return shortCut;
	}

}
