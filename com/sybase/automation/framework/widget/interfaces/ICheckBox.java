/**
 * Created on Mar 11, 2007
 * 
 * Copyright (c) Sybase, Inc. 2004-2007. All rights reserved.
 */
package com.sybase.automation.framework.widget.interfaces;

/**
 * @author xfu
 */
public interface ICheckBox {
	void check();

	void unCheck();

	boolean getState();

	//added by klilu
	void setState(boolean flag);

	//added by Ryan
	boolean isEnabled();

	void verifyState(boolean state);

}
