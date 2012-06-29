package com.sybase.automation.framework.widget.eclipse;

import java.util.Vector;

import com.rational.test.ft.object.interfaces.GuiSubitemTestObject;
import com.rational.test.ft.object.interfaces.TestObject;
import com.rational.test.ft.script.RationalTestScriptConstants;
import com.rational.test.ft.script.Text;
import com.rational.test.ft.vp.ITestData;
import com.rational.test.ft.vp.ITestDataElementList;
import com.rational.test.ft.vp.ITestDataList;
import com.sybase.automation.framework.widget.interfaces.*;

/**
 * Widget to encapsulate the behavior the TabFolder Test Object
 * 
 * @author xfu
 * 
 *  
 */
public class TabFolder implements ITabFolder

{
	private GuiSubitemTestObject _TabFolder;

	public ITestDataList tdl;

	public TabFolder(GuiSubitemTestObject tabFolder) {
		_TabFolder = tabFolder;
		//tdl = (ITestDataList) _TabFolder.getTestData("list");
	}

	/**
	 * Select the tab
	 * <p>
	 *
	 * @param tabName
	 *               will be selected tab name
	 */
	//** added by Ryan
	public void selectTab(String tabName) {
		_TabFolder.setState(RationalTestScriptConstants.SINGLE_SELECT, new Text("Sybase_AS&E"));
		//_TabFolder.click(new Text(tabName));
	}
	 
	/**
	 * Returns the number of list items
	 * <p>
	 * 
	 * @return number items in the list
	 */
	public int getItemCount() {
		ITestData data = (ITestData) _TabFolder.getTestData("list");
		ITestDataList list = (ITestDataList) data;
		return list.getElementCount();
	}

	/**
	 * Returns all items in this listbox
	 * <p>
	 * 
	 * @return String array containing the text of each of the elements in the
	 *         TabFolder
	 */
	public String[] getContents() {
		ITestDataList dataList = (ITestDataList) _TabFolder.getTestData("list");
		if (dataList.getElementCount() == 0)
			return null;
		ITestDataElementList elementList = (ITestDataElementList) dataList
				.getElements();
		return convertElementListToStringArray(elementList);
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
		return getItemIndex(sItem) != -1;
	}

	/**
	 * Finds an item in this listbox
	 * <p>
	 * 
	 * @param sItem
	 *            the item to find in the list
	 * @return index of item; -1 if item not found
	 */
	public int getItemIndex(String sItem) {
		//It would be easiest to use the Vector like so:
		//return this.getContentsAsVector().indexOf(new
		// TestDataElement(sItem));
		//but RFT doesn't have a constructor to create an "element" type
		//and no find item methods on an ElementList
		//so there's no way to search for a particular string in the vector
		//so instead, we use the arrays and search manually
		if (getItemCount() == 0)
			return -1;
		String contents[] = this.getContents();
		for (int i = 0; i < contents.length; i++) {
			if (contents[i].equals(sItem)) {
				return i;
			}
		}
		//if get here, item not found, so return -1
		return -1;
	}

	/**
	 * Gets the text of an item in this listbox
	 * <p>
	 * 
	 * @param index
	 *            the index of the item
	 * @return the text of the item; null if not found
	 */
	//**modified by Ryan,changed the method name
	//public String getItemText(int index) {
	public String getItemAt(int index) {
		if (getItemCount() == 0)
			return null;
		String contents[] = this.getContents();
		if (index < 0 || index >= contents.length) {
			System.out.println("index is out of range.Returning null string");
			return null;
		}
		return contents[index];
	}

	/**
	 * Returns all items in a listbox as a Vector
	 * <p>
	 * 
	 * @return Vector of com.rational.test.ft.vp.impl.TestDataElements
	 *         representing the items in the listbox
	 */
	public Vector getContentsAsVector() {
		ITestDataList dataList = (ITestDataList) _TabFolder.getTestData("list");
		ITestDataElementList elementList = (ITestDataElementList) dataList
				.getElements();
		return elementList.getElements();
	}

	/**
	 * Helper method to convert an ITestDataElementList to a String array
	 * <p>
	 * 
	 * @param elementList
	 *            the element list to convert
	 * @return String array containing text of all the elements in the list
	 */
	private String[] convertElementListToStringArray(
			ITestDataElementList elementList) {
		String[] list = new String[elementList.getLength()];
		for (int i = 0; i < list.length; i++)
			list[i] = elementList.getElement(i).getElement().toString();
		return list;
	}

}