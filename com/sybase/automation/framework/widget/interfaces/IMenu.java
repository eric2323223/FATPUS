/**
 * Created on Mar 19, 2007
 * 
 * Copyright (c) Sybase, Inc. 2004-2007. All rights reserved.
 */
package com.sybase.automation.framework.widget.interfaces;

/**
 * @author xfu
 */
public interface IMenu
{
	void clickAtPath(String path);
	
	boolean isEnabled(String node);
}
