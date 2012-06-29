package com.sybase.automation.framework.widget.eclipse;

import com.rational.test.ft.*;
import com.rational.test.ft.object.interfaces.*;
import com.rational.test.ft.script.*;
import com.rational.test.ft.value.*;
import com.rational.test.ft.vp.*;
import com.sybase.automation.framework.widget.interfaces.ITwistie;


/**
 * @author kliu
 *  
 */
public class Twistie implements ITwistie {
	private GuiTestObject _Twistie;

	// private GuiTestObject twistie;

	public Twistie(GuiTestObject twistie) {
		_Twistie = twistie;
	}

	// do expand on the twistie
	public void expand() {
		if (_Twistie.getProperty("expanded").toString().endsWith("false")) {
			_Twistie.click();
		} 
	}
	// do collapse on the twistie
	public void collapse() {
		if (_Twistie.getProperty("expanded").toString().endsWith("true")) {
			_Twistie.click();
		}
	}
	// 
	public boolean isExpanded() {
		if (_Twistie.exists()) {
			if (_Twistie.getProperty("expanded").toString().endsWith("true")) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
}
