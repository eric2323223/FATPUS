/*
 * Created on Dec 19, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.sybase.automation.framework.widget.eclipse;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Vector;

import com.rational.test.ft.SubitemNotFoundException;
import com.rational.test.ft.object.interfaces.GuiSubitemTestObject;
import com.rational.test.ft.object.interfaces.ScrollTestObject;
import com.rational.test.ft.vp.ITestData;
import com.rational.test.ft.vp.ITestDataElementList;
import com.rational.test.ft.vp.ITestDataList;
import com.sybase.automation.framework.widget.interfaces.*;

/**
 * widget to encapsulate the behavior the CCombo Test Object
 * 
 * @author xfu
 * 
 * Example: new CCombo(CCombo3(),List2()).select(2); new
 * CCombo(CCombo3(),List2()).select("ar_MA"); System.out.println(new
 * CCombo(CCombo3(),List2()).getSelText());
 *  
 */
public class CCombo implements IComboBox {
	private ScrollTestObject _CCombo;
	private ListBox lb;

	/**
	 * construtor
	 * <p>
	 * 
	 * @param
	 * 
	 * @return
	 */
	public CCombo(ScrollTestObject ccombo, GuiSubitemTestObject listbox) {
		_CCombo = ccombo;
		lb = new ListBox(listbox);
	}

	/**
	 * Selects an item from the listbox stored in this particular ListBox object
	 * <p>
	 * 
	 * @param sItem
	 *            text of the item to pick
	 */
	public void select(String sItem) {
		comboClick();
		if (!lb.doesItemExist(sItem)) {
			throw new SubitemNotFoundException("item: " + sItem + " not found");

		}
		lb.select(sItem);
	}

	/**
	 * Selects an item from the listbox stored in this particular ListBox object
	 * <p>
	 * 
	 * @param sItem
	 *            text of the item to pick
	 */
	public void click() {
		_CCombo.click();
	}
	
	/**
	 * Selects an item from this listbox by the index of the item
	 * <p>
	 * 
	 * @param index
	 *            index of the item to pick
	 */
	public void select(int index) {
		comboClick();
		String sItem = lb.getItemText(index);
		if (sItem == null) {
			throw new SubitemNotFoundException("index out of range");
		}
		lb.select(sItem);
	}

	/**
	 * Determines whether the item exists in this listbox
	 * <p>
	 * 
	 * @param sItem
	 *            the item to find in the list
	 * @return true if item exists; otherwise false
	 */
	public boolean doesItemExist(String sItem) {
		comboClick();
		return lb.getItemIndex(sItem) != -1;
	}

	/**
	 * Gets the text of the first item selected in the listbox stored in this
	 * particular instance of the class
	 * <p>
	 * Note: if the listbox is multi-select, use getMultiSelText
	 * 
	 * @return text of selected item
	 */
	public String getSelText() {
		/* make the list avaible */
		comboClick();
		/* get the selected text */
		String[] selected = lb.getMultiSelText();
		if (selected == null)
			return null;
		return selected[0];

	}

	/**
	 * click the down arrow , making the drop-down list available.
	 * <p>
	 *  
	 */
	private void comboClick() {

		java.awt.Point p;
		p = (java.awt.Point) _CCombo.getProperty("size");
		p.x -= 10;
		p.y -= 10;
		_CCombo.click(p);

	}

	/*
	 * return the items in the ccomb box list as an array
	 * 
	 * @auther zhouw
	 */
	public String[] getContents() 
	{		
        if (lb.getItemCount() == 0)
        return null;
        return lb.getContents(); 
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see framework.widget.interfaces.IComboBox#getContentsAsVector()
	 */
	public Vector getContentsAsVector() 
	{
		
		throw new IllegalStateException("This function is not implemented");
	}

	/*
	 * return the item count in the ccomb box list
	 * 
	 * @auther zhouw
	 */
	public int getItemCount() 
	{
		return lb.getItemCount();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see framework.widget.interfaces.IComboBox#getItemIndex(java.lang.String)
	 */
	public int getItemIndex(String sItem) {
		throw new IllegalStateException("This function is not implemented");
	}

	/*
	 * return the itemtext of the specified text in the ccomb box list
	 * 
	 * @auther zhouw
	 */
	public String getItemText(int index) 
	{
		if (getItemCount() == 0)
	        return null;
			System.out.println("there are " +getItemCount()+ " items in the list!");
	        String contents[] = this.getContents();
//	        for (int i =0 ; i <contents.length; i ++)
//	        {	
//	        	System.out.println("item["+i+"] is : "+ contents[i]);
//	        }
	        if (index < 0 || index >= contents.length) 
	        {
	            System.out.println(
	                "Combo::getItemText: index is out of range.Returning null string");
	            return null;
	        }
	        return contents[index];
		
		
//		throw new IllegalStateException("This function is not implemented");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see framework.widget.interfaces.IComboBox#getMultiSelIndex()
	 */
	public int[] getMultiSelIndex() {
		throw new IllegalStateException("This function is not implemented");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see framework.widget.interfaces.IComboBox#getMultiSelText()
	 */
	public String[] getMultiSelText() {
		throw new IllegalStateException("This function is not implemented");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see framework.widget.interfaces.IComboBox#getSelIndex()
	 */
	public int getSelIndex() {
		throw new IllegalStateException("This function is not implemented");
	}

/**
	 * Gets the text of the current item selected in the listbox stored in this
	 * particular instance of the class,and gets the text of the current item
	 * user-defined or modified
	 * 
	 * @return text of selected item
	 */
	public String getText() {
		return (String)_CCombo.getProperty("text");
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see framework.widget.interfaces.IComboBox#multiSelect(int)
	 */
	public void multiSelect(int index) {
		throw new IllegalStateException("This function is not implemented");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see framework.widget.interfaces.IComboBox#multiSelect(java.lang.String)
	 */
	public void multiSelect(String sItem) {
		throw new IllegalStateException("This function is not implemented");
	}

/**
	 * Set the value for CCombo box
	 * 
	 */
	public void setText(String s) {
		_CCombo.setProperty("text",s);
	}
}
