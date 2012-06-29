/**
 * Created on Mar 11, 2007
 * 
 * Copyright (c) Sybase, Inc. 2004-2007. All rights reserved.
 */
package com.sybase.automation.framework.widget.interfaces;

/**
 * @author xfu
 */
public interface ITextBox {
	void setText(String s);

	String getText();

	void pasteText(String txt);

	void clearText();

	String[] getLines();

	String getLines(int start, int end);

	void insertLines(int line, String txt);

	void verifyText(String expected);

	void verifyText(String expected, boolean fromFile);

	void createVPFile(String actual, String vpPath);
}
