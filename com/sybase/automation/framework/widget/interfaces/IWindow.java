/**
 * Created on Mar 11, 2007
 * 
 * Copyright (c) Sybase, Inc. 2004-2007. All rights reserved.
 */
package com.sybase.automation.framework.widget.interfaces;

/**
 * @author xfu
 */
public interface IWindow {
	/* Judge policies for window title equality */
	/** equal after to lower and trim */
	public static final short EXP_EQUAL = 1;

	/** exactly equal */
	public static final short EXP_EX_EQUAL = 2;

	/** contains expected */
	public static final short EXP_CONTAIN = 3;

	String getTitle();

	void inputKeys(String input);

	void waitUntilExist(String expWinTitle);

	void waitUntilExist(String expWinTitle, int interval, int timeOut,
			short judgePolicy);

	void waitUntilGone(String expWinTitle);

	void waitUntilGone(String expWinTitle, int interval, int timeOut,
			short judgePolicy);

	void verifyTitle(String expected);

	void dismiss(String title);

	void dismiss(String title, String keys);
}
