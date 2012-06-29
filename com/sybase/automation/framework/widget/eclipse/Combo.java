package com.sybase.automation.framework.widget.eclipse;

import java.util.Hashtable;
import java.util.Vector;

import com.rational.test.ft.object.TestObjectReference;
import com.rational.test.ft.object.interfaces.GuiSubitemTestObject;
import com.rational.test.ft.object.interfaces.IWindow;
import com.rational.test.ft.object.interfaces.TestObject;
import com.rational.test.ft.script.MouseModifiers;
import com.rational.test.ft.script.RationalTestScript;
import com.rational.test.ft.script.Text;
import com.rational.test.ft.vp.ITestData;
import com.rational.test.ft.vp.ITestDataElementList;
import com.rational.test.ft.vp.ITestDataList;
import com.rational.test.ft.vp.impl.TestDataElement;
import com.rational.test.ft.*;
import com.rational.test.ft.object.interfaces.*;
import com.rational.test.ft.script.*;
import com.rational.test.ft.value.*;
import com.rational.test.ft.vp.*;
import com.sybase.automation.framework.common.ClipBoardUtil;
import com.sybase.automation.framework.widget.interfaces.IComboBox;

/**
 * widget to encapsulate the behavior the Combo Test Object
 * 
 * @author xfu
 * 
 * Target Object: org.eclipse.swt.widgets.Combo
 * 
 */
public class Combo implements IComboBox {
	private GuiSubitemTestObject _Combo;

	public Combo(GuiSubitemTestObject Combo) {
		_Combo = Combo;
	}

	/**
	 * Selects an item from the Combo stored in this particular Combo object
	 * <p>
	 * 
	 * @param sItem
	 *            text of the item to pick
	 */
	public void select(String sItem) {
		comboClick();
		if (!doesItemExist(sItem)) {
			throw new SubitemNotFoundException("item: " + sItem + " not found");
		}
		_Combo.click(new Text(sItem));
	}

	/**
	 * Selects the Combo
	 * <p>
	 * 
	 * @param sItem
	 *            text of the item to pick
	 */
	public void click() {
		_Combo.click();
	}

	/**
	 * Selects an item from this Combo by the index of the item
	 * <p>
	 * 
	 * @param index
	 *            index of the item to pick, counting from 0
	 */
	public void select(int index) {
		comboClick();
		String sItem = this.getItemText(index);
		if (sItem == null) {
			throw new SubitemNotFoundException("index out of range");
		}
		// this.select(sItem);
		if (!doesItemExist(sItem)) {
			System.out
					.println("Combo::select: item not found. No selection could  be made.");
			return;
		}
		_Combo.click(new Text(sItem));
	}

	/**
	 * Selects an item from this multi-select Combo while preserving other
	 * selections
	 * <p>
	 * 
	 * @param sItem
	 *            text of the item to pick
	 */
	public void multiSelect(String sItem) {
		if (this.isMultiSelectable()) {
			MouseModifiers mm = new MouseModifiers(MouseModifiers.MOUSE_LEFT);
			mm.setCtrl();
			_Combo.click(mm, new Text(sItem));
		} else {
			System.out
					.println("Combo::multiSelect(): Combo does not support multi-select. Performing a select instead.");
			this.select(sItem);
		}
	}

	/**
	 * Selects an item from this multi-select Combo while preserving other
	 * selections
	 * <p>
	 * 
	 * @param index
	 *            index of the item to pick
	 */
	public void multiSelect(int index) {
		String sItem = getItemText(index);
		if (sItem == null) {
			System.out
					.println("Combo::multiSelect: index is out of range. No selection could be made.");
			return;
		}
		this.multiSelect(sItem);
	}

	/**
	 * Gets the text of the first item selected in the Combo stored in this
	 * particular instance of the class
	 * <p>
	 * Note: if the Combo is multi-select, use getMultiSelText
	 * 
	 * @return text of selected item
	 */
	public String getSelText() {
		if (getItemCount() == 0)
			return null;
		String[] selected = getMultiSelText();
		if (selected == null)
			return null;
		return selected[0];
	}

	/**
	 * Gets the index of the first item selected in the Combo stored in this
	 * particular instance of the class
	 * <p>
	 * Note: if the Combo is multi-select, use getMultiSelIndex
	 * 
	 * @return the index of the selected item
	 */
	public int getSelIndex() {
		if (getItemCount() == 0)
			return -1;
		int[] selected = getMultiSelIndex();
		if (selected == null)
			return -1;
		return selected[0];
	}

	/**
	 * Gets text of all the selected items in the Combo stored in this
	 * particular instance of the class
	 * <p>
	 * 
	 * @return String array containing text of all the selected items
	 */
	public String[] getMultiSelText() {
		ITestData data = _Combo.getTestData("selected");
		ITestDataList dataList = (ITestDataList) data;
		if (dataList.getElementCount() == 0)
			return null;
		ITestDataElementList elementList = (ITestDataElementList) dataList
				.getElements();
		return convertElementListToStringArray(elementList);
	}

	/**
	 * Gets the index of all the selected items in the Combo stored in this
	 * particular instance of the class
	 * <p>
	 * 
	 * @return int array containing indexes of all the selected items
	 */
	public int[] getMultiSelIndex() {
		ITestData data = _Combo.getTestData("selected");
		ITestDataList dataList = (ITestDataList) data;
		if (dataList.getElementCount() == 0)
			return null;
		ITestDataElementList elementList = (ITestDataElementList) dataList
				.getElements();
		Vector vSelectedList = elementList.getElements();
		int[] iSelectedList = new int[elementList.getLength()];
		for (int i = 0; i < iSelectedList.length; i++) {
			TestDataElement element = (TestDataElement) vSelectedList.get(i);
			iSelectedList[i] = this.getItemIndex((String) element.getElement());
		}
		return iSelectedList;
	}

	/**
	 * Returns the number of list items
	 * <p>
	 * 
	 * @return number items in the list
	 */
	public int getItemCount() {
		ITestData data = (ITestData) _Combo.getTestData("list");
		ITestDataList list = (ITestDataList) data;
		return list.getElementCount();
	}

	/**
	 * Returns all items in this Combo
	 * <p>
	 * 
	 * @return String array containing the text of each of the elements in the
	 *         Combo
	 */
	public String[] getContents() {
		ITestDataList dataList = (ITestDataList) _Combo.getTestData("list");
		if (dataList.getElementCount() == 0)
			return null;
		ITestDataElementList elementList = (ITestDataElementList) dataList
				.getElements();
		return convertElementListToStringArray(elementList);
	}

	/**
	 * Determines whether the item exists in this Combo
	 * <p>
	 * 
	 * @param sItem
	 *            the item to find in the list
	 * @return true if item exists; otherwise false
	 */
	public boolean doesItemExist(String sItem) {
		if (getItemCount() == 0)
			return false;
		return getItemIndex(sItem) != -1;
	}

	/**
	 * Finds an item in this Combo
	 * <p>
	 * 
	 * @param sItem
	 *            the item to find in the list
	 * @return index of item; -1 if item not found
	 */
	public int getItemIndex(String sItem) {
		// It would be easiest to use the Vector like so:
		// return this.getContentsAsVector().indexOf(new
		// TestDataElement(sItem));
		// but RFT doesn't have a constructor to create an "element" type
		// and no find item methods on an ElementList
		// so there's no way to search for a particular string in the vector
		// so instead, we use the arrays and search manually
		if (getItemCount() == 0)
			return -1;
		String contents[] = this.getContents();
		for (int i = 0; i < contents.length; i++) {
			if (contents[i].equals(sItem)) {
				return i;
			}
		}
		// if get here, item not found, so return -1
		return -1;
	}

	/**
	 * Gets the text of an item in this Combo
	 * <p>
	 * 
	 * @param index
	 *            the index of the item
	 * @return the text of the item; null if not found
	 */
	public String getItemText(int index) {
		if (getItemCount() == 0)
			return null;
		String contents[] = this.getContents();
		if (index < 0 || index >= contents.length) {
			System.out
					.println("Combo::getItemText: index is out of range.Returning null string");
			return null;
		}
		return contents[index];
	}

	/**
	 * Helper function for multi-select to determine whether Combo is
	 * multi-selectable
	 * <p>
	 * (really to determine if you will get an error by trying to ctrl-click)
	 */
	protected boolean isMultiSelectable() {
		if (Boolean.valueOf(_Combo.getProperty(".multiple").toString()) != Boolean.TRUE) {
			return false;
		}
		return true;
	}

	/**
	 * Returns all items in a Combo as a Vector
	 * <p>
	 * 
	 * @return Vector of com.rational.test.ft.vp.impl.TestDataElements
	 *         representing the items in the Combo
	 */
	// protected Vector getContentsAsVector()
	public Vector getContentsAsVector() {
		ITestDataList dataList = (ITestDataList) _Combo.getTestData("list");
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
	protected String[] convertElementListToStringArray(
			ITestDataElementList elementList) {
		String[] list = new String[elementList.getLength()];
		for (int i = 0; i < list.length; i++)
			list[i] = elementList.getElement(i).getElement().toString();
		return list;
	}

	/**
	 * set the text of Combo
	 * 
	 */
	public void setText(String s) {
		this.comboClickAtText();
		IWindow w = RationalTestScript.getScreen().getActiveWindow();
		w.inputKeys("{ExtHome}+{ExtEnd}{ExtDelete}");
		w.inputChars(s);

	}

	/**
	 * Paste the text of Combo
	 * 
	 * @param s
	 */
	public void pasteText(String s) {
		this.comboClickAtText();
		IWindow w = RationalTestScript.getScreen().getActiveWindow();
		w.inputKeys("{ExtHome}+{ExtEnd}{ExtDelete}");
		ClipBoardUtil.setClipboardText(s);
		w.inputKeys("^v");
	}

	/**
	 * get the text of Combo
	 * <p>
	 */
	public String getText() {
		return _Combo.getProperty("Text").toString();
	}

	private void comboClick() {

		java.awt.Point p;
		p = (java.awt.Point) _Combo.getProperty("size");
		p.x -= 10;
		p.y -= 10;
		_Combo.click(p);

	}

	private void comboClickAtText() {
		java.awt.Point p;
		p = (java.awt.Point) _Combo.getProperty("size");
		p.x -= 25;
		p.y -= 10;
		_Combo.click(p);
	}
}
