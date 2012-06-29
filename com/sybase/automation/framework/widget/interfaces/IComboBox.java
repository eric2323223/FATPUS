/**
 * Created on Mar 11, 2007
 * 
 * Copyright (c) Sybase, Inc. 2004-2007. All rights reserved.
 */
package com.sybase.automation.framework.widget.interfaces;

import java.util.Vector;

/**
 * @author xfu
 */
public interface IComboBox
{
	void select(String sItem);
	
	void select(int index);
	
	void multiSelect(String sItem);
	
	void multiSelect(int index);
	
	String getSelText();
	
	int getSelIndex();
	
	String[] getMultiSelText();
	
	int[] getMultiSelIndex();
	
	int getItemCount();
	
	String[] getContents();
	
	boolean doesItemExist(String sItem);
	
	int getItemIndex(String sItem);
	
	String getItemText(int index);
	
	Vector getContentsAsVector();
	
	void setText(String s);
	
	String getText();
	
	void click();
}
