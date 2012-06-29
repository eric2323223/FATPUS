package com.sybase.automation.framework.widget.eclipse;

import com.rational.test.ft.*;
import com.rational.test.ft.object.interfaces.*;
import com.rational.test.ft.script.*;
import com.rational.test.ft.value.*;
import com.rational.test.ft.vp.*;
import com.sybase.automation.framework.widget.interfaces.IButton;


/**
 * @author xfu
 *  
 */
public class Button implements IButton {
	private GuiTestObject _Button;

	// private GuiTestObject button;

	public Button(GuiTestObject button) {
		_Button = button;
	}

	// do click on the button
	public void click() {
		_Button.click();
	}

	// no way to do it ....
	public boolean isEnabled() {
		if (_Button.exists()) {
			if (_Button.isEnabled()) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
}
