/*
 * Created on May 25, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.sybase.automation.framework.widget.interfaces;

/**
 * @author juanchen
 * @canUse true
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public interface IContextMenu {
	boolean itemChecked(String path);
	boolean itemEnabled(String path);
	String[] getChildren(String path);
}
