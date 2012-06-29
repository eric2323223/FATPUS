/**
 * Created on Mar 19, 2007
 * 
 * Copyright (c) Sybase, Inc. 2004-2007. All rights reserved.
 */
package com.sybase.automation.framework.widget.factory;

import com.rational.test.ft.object.interfaces.GuiSubitemTestObject;
import com.rational.test.ft.object.interfaces.GuiTestObject;
import com.rational.test.ft.object.interfaces.ScrollTestObject;
import com.rational.test.ft.object.interfaces.TextScrollTestObject;
import com.rational.test.ft.object.interfaces.ToggleGUITestObject;
import com.rational.test.ft.script.RationalTestScript;
import com.sybase.automation.framework.widget.eclipse.*;
import com.sybase.automation.framework.widget.interfaces.IButton;
import com.sybase.automation.framework.widget.interfaces.ICheckBox;
import com.sybase.automation.framework.widget.interfaces.IComboBox;
import com.sybase.automation.framework.widget.interfaces.IContextMenu;
import com.sybase.automation.framework.widget.interfaces.IFigureCanvas;
import com.sybase.automation.framework.widget.interfaces.IListBox;
import com.sybase.automation.framework.widget.interfaces.IMenu;
import com.sybase.automation.framework.widget.interfaces.IRadioButton;
import com.sybase.automation.framework.widget.interfaces.ISpinner;
import com.sybase.automation.framework.widget.interfaces.ITabFolder;
import com.sybase.automation.framework.widget.interfaces.ITable;
import com.sybase.automation.framework.widget.interfaces.ITextBox;
import com.sybase.automation.framework.widget.interfaces.IToolbar;
import com.sybase.automation.framework.widget.interfaces.ITree;
import com.sybase.automation.framework.widget.interfaces.IWindow;
import com.sybase.automation.framework.widget.interfaces.ILabel;
import com.sybase.automation.framework.widget.interfaces.ITwistie;

/**
 * @author xfu
 */
public class EclipseWidgetFactory implements IWidgetFactory {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sybase.automation.framework.widget.factory.IWidgetFactory#createButton()
	 */
	public IButton createButton(Object button) {
		return new Button((GuiTestObject) button);
	}
	
	public IButton createButton(RationalTestScript script, String button) {
		return new Button(new GuiTestObject( script.getMappedTestObject(button)));
    }

	

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sybase.automation.framework.widget.factory.IWidgetFactory#createCheckBox()
	 */
	public ICheckBox createCheckBox(Object checkbox) {
		return new CheckBox((ToggleGUITestObject) checkbox);
	}
	
	public ICheckBox createCheckBox(RationalTestScript script, String checkbox) {
		return new CheckBox(new ToggleGUITestObject(script.getMappedTestObject(checkbox)));
	}	

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sybase.automation.framework.widget.factory.IWidgetFactory#createRadioButton()
	 */
	public IRadioButton createRadioButton(Object radiobutton) {
		return new RadioButton((ToggleGUITestObject) radiobutton);
	}

	public IRadioButton createRadioButton(RationalTestScript script, String radiobutton) {
		return new RadioButton(new ToggleGUITestObject(script.getMappedTestObject(radiobutton)));
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sybase.automation.framework.widget.factory.IWidgetFactory#createTextBox()
	 */
	public ITextBox createTextBox(Object textBox) {
		return new TextBox((TextScrollTestObject) textBox);
	}
	
	
	public ITextBox createTextBox(RationalTestScript script,String textBox)
	{
		return new TextBox(new TextScrollTestObject(script.getMappedTestObject(textBox)));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sybase.automation.framework.widget.factory.IWidgetFactory#createComboBox()
	 */
	public IComboBox createComboBox(Object Combo) {
		return new Combo((GuiSubitemTestObject) Combo);
	}

	public IComboBox createComboBox(RationalTestScript script,String Combo) {
		return new Combo(new GuiSubitemTestObject(script.getMappedTestObject(Combo)));
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sybase.automation.framework.widget.factory.IWidgetFactory#createComboBox()
	 */
	public IComboBox createCComboBox(Object ccombo,Object listbox) {
		return new CCombo((ScrollTestObject) ccombo, (GuiSubitemTestObject) listbox);
	}
	
	public IComboBox createCComboBox(RationalTestScript script,String ccombo,String listbox) {
		return new CCombo(new ScrollTestObject(script.getMappedTestObject(ccombo)),new GuiSubitemTestObject(script.getMappedTestObject(listbox)));
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sybase.automation.framework.widget.factory.IWidgetFactory#createListBox()
	 */
	public IListBox createListBox(Object listbox) {
		return new ListBox((GuiSubitemTestObject) listbox);
	}
	
	public IListBox createListBox(RationalTestScript script,String listbox) {
		return new ListBox(new GuiSubitemTestObject(script.getMappedTestObject(listbox)));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sybase.automation.framework.widget.factory.IWidgetFactory#createTabFolder()
	 */
	public ITabFolder createTabFolder(Object tabFolder) {
		return new TabFolder((GuiSubitemTestObject) tabFolder);
	}
	
	public ITabFolder createTabFolder(RationalTestScript script,String tabFolder) {
		return new TabFolder(new GuiSubitemTestObject(script.getMappedTestObject(tabFolder)));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sybase.automation.framework.widget.factory.IWidgetFactory#createTabFolder()
	 */
	public ITabFolder createCTabFolder(Object ctabfolder) {
		return new CTabFolder((GuiSubitemTestObject) ctabfolder);
	}
	
	public ITabFolder createCTabFolder(RationalTestScript script,String ctabfolder) {
		return new CTabFolder(new GuiSubitemTestObject(script.getMappedTestObject(ctabfolder)));
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sybase.automation.framework.widget.factory.IWidgetFactory#createWindow()
	 */
	public IWindow createWindow(Object win) {
		return new Window();
	}
	
	public IWindow createWindow(RationalTestScript script,String win) {
		return new Window();
	}
	
	public IWindow createWindow() {
		return new Window();
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sybase.automation.framework.widget.factory.IWidgetFactory#createTree()
	 */
	public ITree createTree(Object tree) {
		return new Tree((GuiSubitemTestObject) tree);
	}

	public ITree createTree(RationalTestScript script,String tree) {
		return new Tree(new GuiSubitemTestObject(script.getMappedTestObject(tree)));
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sybase.automation.framework.widget.factory.IWidgetFactory#createTable()
	 */
	public ITable createTable(Object table) {
		return new Table((GuiSubitemTestObject) table);
	}
	
	public ITable createTable(RationalTestScript script,String table) {
		return new Table(new GuiSubitemTestObject(script.getMappedTestObject(table)));
	}

	public ITable createTable(Object table, String tabletype) {
		return new Table((GuiSubitemTestObject) table, tabletype);
	}
	
//	public ITable createTable(RationalTestScript script,String table, String tabletype) {
//		return new Table(new GuiSubitemTestObject(script.getMappedTestObject(table), tabletype));
//	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sybase.automation.framework.widget.factory.IWidgetFactory#createMenu()
	 */
	public IMenu createMenu(Object menu) {
		return new Menu((GuiSubitemTestObject) menu);
	}
	
	public IMenu createMenu(RationalTestScript script,String menu) {
		return new Menu(new GuiSubitemTestObject(script.getMappedTestObject(menu)));
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sybase.automation.framework.widget.factory.IWidgetFactory#createMenu()
	 */
	public ISpinner createSpinner(Object spinner) {
		return new Spinner((ScrollTestObject) spinner);
	}
	
	public ISpinner createSpinner(RationalTestScript script,String spinner) {
		return new Spinner(new ScrollTestObject(script.getMappedTestObject(spinner)));
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sybase.automation.framework.widget.factory.IWidgetFactory#createMenu()
	 */
	public ILabel createLabel(Object label) {
		return new Label((GuiTestObject) label);
	}
	
	public ILabel createLabel(RationalTestScript script,String label) {
		return new Label(new GuiTestObject(script.getMappedTestObject(label)));
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sybase.automation.framework.widget.factory.IWidgetFactory#createMenu()
	 */
	public ITwistie createTwistie(Object twistie) {
		return new Twistie((GuiTestObject) twistie);
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sybase.automation.framework.widget.factory.IWidgetFactory#createMenu()
	 */
	public IToolbar createToolBar(Object toolBar) {
		return new ToolBar((GuiSubitemTestObject) toolBar);
	}
	public IToolbar createToolBar(RationalTestScript script,String toolBar) {
		return new ToolBar(new GuiSubitemTestObject(script.getMappedTestObject(toolBar)));
	}

	/* (non-Javadoc)
	 * @see com.sybase.automation.framework.widget.factory.IWidgetFactory#createContextMenu(java.lang.Object)
	 */
	public IContextMenu createContextMenu() {
		return new ContextMenu();
	}
	
	/* (non-Javadoc)
	 * @see com.sybase.automation.framework.widget.factory.IWidgetFactory#createFigureCanvas (java.lang.Object)
	 */
	public IFigureCanvas createFigureCanvas(Object figureCanvas) {
		return new FigureCanvas((GuiTestObject) figureCanvas);
	}
}
