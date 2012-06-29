/**
 * Created on Mar 10, 2007
 * 
 * Copyright (c) Sybase, Inc. 2004-2007. All rights reserved.
 */
package com.sybase.automation.framework.widget.eclipse;

import java.io.*;
import java.util.*;
import com.rational.test.ft.object.interfaces.GuiSubitemTestObject;
import com.rational.test.ft.object.interfaces.TestObject;
import com.rational.test.ft.object.interfaces.ToggleGUITestObject;
import com.rational.test.ft.script.RationalTestScript;
import com.rational.test.ft.script.RationalTestScriptConstants;
import com.sybase.automation.framework.core.exception.VerificationFailure;
import com.sybase.automation.framework.widget.interfaces.ICheckBox;

/**
 * @author xfu
 */
public class CheckBox implements ICheckBox {
	private ToggleGUITestObject _CheckBox;

	public CheckBox(ToggleGUITestObject checkbox) {
		_CheckBox = checkbox;
	}

	//Check the checkbox
	public void check() {
		_CheckBox.clickToState(RationalTestScriptConstants.SELECTED);
	}

	//uncheck the checkbox
	public void unCheck() {
		_CheckBox.clickToState(RationalTestScriptConstants.NOT_SELECTED);
	}

	//get state of the item
	public boolean getState() {
		if (_CheckBox.getState().equals(RationalTestScriptConstants.SELECTED)) {
			return true;
		} else {
			return false;
		}
	}

	//set state of the item,true means checked
	//@author kliu
	public void setState(boolean flag) {
		if (flag) {
			_CheckBox.clickToState(RationalTestScriptConstants.SELECTED);
		} else {
			_CheckBox.clickToState(RationalTestScriptConstants.NOT_SELECTED);
		}
	}

	//Is the checkbox enabled?
	public boolean isEnabled() {
		if (_CheckBox.exists()) {
			if (_CheckBox.isEnabled()) {
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
	 * @see com.sybase.automation.framework.widget.interfaces.ICheckBox#verifyState(boolean)
	 */
	public void verifyState(boolean state) {
		String actual = _CheckBox.getProperty("selection").toString();
		String expected = String.valueOf(state);
		if (!actual.equals(expected)) {
			RationalTestScript.logTestResult(new VerificationFailure(expected,
					actual).getMessage(), false);
		}

	}
}
