/**
 * Created on Mar 11, 2007
 * 
 * Copyright (c) Sybase, Inc. 2004-2007. All rights reserved.
 */
package com.sybase.automation.framework.widget.interfaces;

import java.util.Vector;

import com.rational.test.ft.vp.ITestDataElementList;

/**
 * @author xfu
 */
public interface ITabFolder {
	void selectTab(String tabName);
	
	int getItemCount();

	String[] getContents();

	boolean doesItemExist(String sItem);

	int getItemIndex(String sItem);

	//**Changed the method name from getItemText getItemAt;
	String getItemAt(int index);

	Vector getContentsAsVector();
}
