/**
 * Created on Mar 11, 2007
 * 
 * Copyright (c) Sybase, Inc. 2004-2007. All rights reserved.
 */
package com.sybase.automation.framework.widget.eclipse;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.rational.test.ft.WindowActivateFailedException;
import com.rational.test.ft.object.interfaces.IWindow;
import com.rational.test.ft.script.RationalTestScript;
import com.sybase.automation.framework.core.exception.VerificationFailure;

/**
 * @author xfu
 */
public class Window implements
		com.sybase.automation.framework.widget.interfaces.IWindow

{

	/**
	 * get the current active window
	 */
	private IWindow getActiveWindow() {
		return RationalTestScript.getScreen().getActiveWindow();
	}

	/**
	 * get the current active window title
	 */
	public String getTitle() {
		return getActiveWindow().getText();
	}

	/**
	 * set value into active window then click ok button
	 * 
	 * @author ywhu
	 * @return
	 */
	public void inputKeys(String input) {
		try {
			IWindow aw = RationalTestScript.getScreen().getActiveWindow();
			if (aw == null) {
				RationalTestScript.logError("get native dialog window failed");
				RationalTestScript.stop();
			}
			aw.inputKeys(input);
			aw.inputKeys("{ENTER}");
		} catch (WindowActivateFailedException e) {
			// ignored
		}
	}

	/**
	 * wait until specified window popup
	 * <p>
	 * 
	 * @param title:
	 *            title of the expected window
	 * 
	 */

	public void waitUntilExist(String title) {
		waitUntilExist(title, 2, 30, EXP_CONTAIN);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sybase.automation.framework.widget.interfaces.IWindow#waitUntilGone(java.lang.String)
	 */
	public void waitUntilGone(String title) {
		waitUntilGone(title, 2, 30, EXP_CONTAIN);
	}

	/**
	 * wait until specified window popup,if button is also provide, it's clicked
	 * <p>
	 * 
	 * @param expWinTitle:
	 *            title of the expected window
	 * @param interval
	 * @param timeOut
	 * @param judgePolicy
	 * 
	 */
	public void waitUntilExist(String expWinTitle, int interval, int timeOut,
			short judgePolicy) {
		waitUntilState(expWinTitle, interval, timeOut, judgePolicy, true);
	}

	public void waitUntilGone(String expWinTitle, int interval, int timeOut,
			short judgePolicy) {
		waitUntilState(expWinTitle, interval, timeOut, judgePolicy, false);
	}

	private void waitUntilState(String expWinTitle, int interval, int timeOut,
			short judgePolicy, boolean exist) {
		int total = 0;
		while ((isWindowExpected(expWinTitle, judgePolicy) != exist)
				&& total < timeOut) {
			System.out.println("waitUntilState: wait " + interval
					+ " second, total " + total + " seconds, "
					+ (exist ? "exist" : "not exist"));
			RationalTestScript.sleep(interval);
			total += interval;
			System.out.println("now top window is " + getTitle());
		}
		if (total >= timeOut) {
			throw new RuntimeException(
					"No or Unexpected Window/Dialog Pops Up ");
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sybase.automation.framework.widget.interfaces.IWindow#verifyTitle(java.lang.String)
	 */
	public void verifyTitle(String expected) {
		if (!expected.equals(getTitle())) {
			throw new VerificationFailure(expected, getTitle());
		}
	}

	/**
	 * close the active window by press "Enter" if the title matchs.
	 */
	public void dismiss(String title) {
		if (getTitle().equals(title)) {
			getActiveWindow().inputKeys("{ENTER}");
		}

	}

	public void dismiss(String title, String keys) {
		if (getTitle().equals(title)) {
			getActiveWindow().inputKeys(keys);
		}

	}

	/**
	 * Judge whether current window's title matches the expected title
	 * 
	 * @param expWinTitle
	 * @return
	 */
	private boolean isWindowExpected(String expWinTitle, short method) {
		boolean rst = false;
		String titleClean = getTitle().toLowerCase().trim();
		String expClean = expWinTitle.toLowerCase().trim();
		switch (method) {
		case EXP_CONTAIN:
			rst = titleClean.indexOf(expClean) != -1;
			if (!rst) {
				Pattern p = Pattern.compile(expWinTitle);
				Matcher m = p.matcher(titleClean);
				rst = m.find();
			}
			break;
		case EXP_EQUAL:
			rst = titleClean.equals(expClean);
			break;
		default:
			rst = getTitle().equals(expWinTitle);
			break;
		}
		return rst;
	}
}
