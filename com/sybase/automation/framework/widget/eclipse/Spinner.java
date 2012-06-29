/*
 * Created on Mar 22, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.sybase.automation.framework.widget.eclipse;

import com.rational.test.ft.object.interfaces.GuiSubitemTestObject;
import com.rational.test.ft.object.interfaces.IWindow;
import com.rational.test.ft.object.interfaces.ScrollTestObject;
import com.rational.test.ft.object.interfaces.TextScrollTestObject;
import com.rational.test.ft.script.RationalTestScript;
import com.sybase.automation.framework.common.ClipBoardUtil;
import com.sybase.automation.framework.widget.interfaces.ISpinner;

/**
 * @author juanchen
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class Spinner implements ISpinner {

	private ScrollTestObject _Spinner;

	public Spinner(ScrollTestObject spinner) {
		_Spinner = spinner;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sybase.automation.framework.widget.interfaces.ISpinner#increase()
	 */
	public void increase() {
		throw new IllegalStateException("This function is not implemented");

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sybase.automation.framework.widget.interfaces.ISpinner#decrease()
	 */
	public void decrease() {
		throw new IllegalStateException("This function is not implemented");

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sybase.automation.framework.widget.interfaces.ISpinner#setText()
	 */
	public void setText(String s) {
		_Spinner.click();
		ClipBoardUtil.setClipboardText(s);
		IWindow w = RationalTestScript.getScreen().getActiveWindow();
		w.inputKeys("{ExtHome}+{ExtEnd}{ExtDelete}");
		/* paste the contents into textbox */
		w.inputKeys("^v");

	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sybase.automation.framework.widget.interfaces.ISpinner#setText()
	 * @author kliu
	 */
	
	public String getText() {
		_Spinner.click();
		return _Spinner.getProperty("selection").toString();


	}

}
