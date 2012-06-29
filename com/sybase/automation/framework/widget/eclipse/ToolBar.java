/*
 * Created on May 24, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.sybase.automation.framework.widget.eclipse;

import com.rational.test.ft.object.interfaces.GuiSubitemTestObject;
import com.rational.test.ft.object.interfaces.TestObject;
import com.rational.test.ft.script.RationalTestScript;
import com.rational.test.ft.script.SubitemFactory;
import com.sybase.automation.framework.widget.interfaces.IToolbar;

/**
 * @author zechaoz
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class ToolBar extends SubitemFactory implements IToolbar {
	private GuiSubitemTestObject _ToolBar;

	public ToolBar(GuiSubitemTestObject toolBar) {
		_ToolBar = toolBar;
	}

	public boolean exists() {
		if (_ToolBar.exists()) {
			return true;
		} else {
			return false;
		}
	}

	public void click(String subItem) {
		_ToolBar.click(atToolTipText(subItem));
	}

	public boolean isEnabled() {
		if (_ToolBar.exists()) {
			if (_ToolBar.isEnabled()) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sybase.automation.framework.widget.interfaces.IToolbar#verifyItemEnabled()
	 */
	public void verifyItemEnabled(String subItem, String expected) {

		TestObject ob = (TestObject) _ToolBar
				.getSubitem(atToolTipText(subItem));
		String actual = ob.getProperty("enabled").toString().equals("true") ? "enabled"
				: "disabled";

		if (actual.equals(expected)) {
			RationalTestScript.logTestResult(format("Status of : " + subItem,
					expected, actual), true);

		} else {
			RationalTestScript.logTestResult(format("Status of " + subItem,
					expected, actual), false);

		}
	}

	private String format(String message, Object expected, Object actual) {
		String formatted = "";
		if (message != null)
			formatted = message + " ";
		return formatted + "expected:{" + expected + "} but was:{" + actual
				+ "}";
	}
}
