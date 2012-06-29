/**
 * Created on Mar 19, 2007
 * 
 * Copyright (c) Sybase, Inc. 2004-2007. All rights reserved.
 */
package com.sybase.automation.framework.widget.factory;

import com.rational.test.ft.object.interfaces.TestObject;
import com.rational.test.ft.script.RationalTestScript;
import com.sybase.automation.framework.widget.interfaces.*;

/**
 * @author xfu
 */
public interface IWidgetFactory
{
	IButton createButton(Object button);
    IButton createButton(RationalTestScript script, String button);

    ICheckBox createCheckBox(Object checkbox);
    ICheckBox createCheckBox(RationalTestScript script, String checkbox);

    IRadioButton createRadioButton(Object radiobutton);
    IRadioButton createRadioButton(RationalTestScript script, String radiobutton);
    
    ITextBox createTextBox(Object textBox);
    ITextBox createTextBox(RationalTestScript script,String textBox);

    IComboBox createComboBox(Object combobox);
    IComboBox createComboBox(RationalTestScript script, String combobox);
    
    IListBox createListBox(Object listbox);
    IListBox createListBox(RationalTestScript script, String listbox);
    
    ITabFolder createTabFolder(Object tabfolder);
    ITabFolder createTabFolder(RationalTestScript script, String tabfolder);
    
    IWindow createWindow(Object window);
    IWindow createWindow(RationalTestScript script, String window);
    
    ITree createTree(Object tree);
    ITree createTree(RationalTestScript script, String tree);
    
    ITable createTable(Object table);
    ITable createTable(RationalTestScript script, String table);
    
    IMenu createMenu(Object menu);
    IMenu createMenu(RationalTestScript script, String menu);
    
    ISpinner createSpinner(Object spinner);
    ISpinner createSpinner(RationalTestScript script, String spinner);
    
    ILabel createLabel(Object label) ;
    ILabel createLabel(RationalTestScript script, String label) ;
    
    IToolbar createToolBar(Object toolBar) ;
    IToolbar createToolBar(RationalTestScript script, String toolBar) ;
    
    IContextMenu createContextMenu() ;
}
