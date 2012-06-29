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
import com.rational.test.ft.script.RationalTestScriptConstants;
import com.sybase.automation.framework.widget.interfaces.*;


/**
 * @author xfu
 */
public class RadioButton implements IRadioButton {
	private ToggleGUITestObject _RadioButton;

	public RadioButton(ToggleGUITestObject radiobutton) {
		_RadioButton = radiobutton;
	}

	// click the item
	public void click() {
		_RadioButton.click();
	}

	//get the state of the radio item,return true when the item was selected,else return false 
	public boolean getState() {
		if (_RadioButton.getState()
				.equals(RationalTestScriptConstants.SELECTED)) {
			return true;
		} else {
			return false;
		}
	}
	
	//check the radio if enabled,return true when the radio is enabled,else return false
	public boolean isEnabled() {
		if (_RadioButton.exists()) {
			if (_RadioButton.isEnabled()) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
}
