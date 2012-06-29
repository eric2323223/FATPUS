/*
 * Created on May 24, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.sybase.automation.framework.widget.interfaces;

/**
 * @author zechaoz
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public interface IToolbar {
	
	boolean exists();
	
	void click(String subItem);
	
	boolean isEnabled();
	
	void verifyItemEnabled(String subItem,String expected);

}
