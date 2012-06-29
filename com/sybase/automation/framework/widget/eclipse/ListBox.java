package com.sybase.automation.framework.widget.eclipse;
import java.util.Vector;
import com.rational.test.ft.object.TestObjectReference;
import com.rational.test.ft.object.interfaces.GuiSubitemTestObject;
import com.rational.test.ft.object.interfaces.TestObject;
import com.rational.test.ft.script.MouseModifiers;
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
import com.sybase.automation.framework.widget.interfaces.IListBox;
/**
 * 
 * Widget to encapsulate the behavior the ListBox Test Object
 * @author xfu
 * 
 */
public class ListBox implements IListBox
{
	private GuiSubitemTestObject _ListBox;
	
    public ListBox(GuiSubitemTestObject listbox) 
    {
    	_ListBox = listbox;
    	_ListBox.getObjectReference();

    }
    /**
	 * Selects an item from the listbox stored in this particular ListBox object
	 * <p>
	 * 
	 * @param sItem
	 *            text of the item to pick
	 */
    public void select(String sItem) 
    {
        if (!doesItemExist(sItem)) 
        {
            throw new SubitemNotFoundException("item: " + sItem + " not found");
        }
        _ListBox.click(new Text(sItem));
    }
    /**
	 * Selects an item from this listbox by the index of the item
	 * <p>
	 * 
	 * @param index
	 *            index of the item to pick, counting from 0
	 */
    public void select(int index) 
    {
        String sItem = this.getItemText(index);
        if (sItem == null) 
        {
            throw new SubitemNotFoundException("index out of range");
        }
        this.select(sItem);
    }
    /**
	 * Selects an item from this multi-select listbox while preserving other
	 * selections
	 * <p>
	 * 
	 * @param sItem
	 *            text of the item to pick
	 */
    public void multiSelect(String sItem) 
    {
        if (this.isMultiSelectable()) 
        {
            MouseModifiers mm = new MouseModifiers(MouseModifiers.MOUSE_LEFT);
            mm.setCtrl();
            _ListBox.click(mm, new Text(sItem));
        }
        else 
        {
            System.out.println(
                "ListBox::multiSelect(): ListBox does not support multi-select. Performing a select instead.");
            this.select(sItem);
        }
    }
    /**
	 * Selects an item from this multi-select listbox while preserving other
	 * selections
	 * <p>
	 * 
	 * @param index
	 *            index of the item to pick
	 */
    public void multiSelect(int index) 
    {
        String sItem = getItemText(index);
        if (sItem == null) 
        {
            System.out.println(
                "ListBox::multiSelect: index is out of range. No selection could be made.");
            return;
        }
        this.multiSelect(sItem);
    }
    /**
	 * Gets the text of the first item selected in the listbox stored in this
	 * particular instance of the class
	 * <p>
	 * Note: if the listbox is multi-select, use getMultiSelText
	 * 
	 * @return text of selected item
	 */
    public String getSelText() 
    {
        String[] selected = getMultiSelText();
        if (selected == null)
        return null;
        return selected[0];
    }
    /**
	 * Gets the index of the first item selected in the listbox stored in this
	 * particular instance of the class
	 * <p>
	 * Note: if the listbox is multi-select, use getMultiSelIndex
	 * 
	 * @return the index of the selected item
	 */
    public int getSelIndex() 
    {
        int[] selected = getMultiSelIndex();
        if (selected == null)
        return -1;
        return selected[0];
    }
    /**
	 * Gets text of all the selected items in the listbox stored in this
	 * particular instance of the class
	 * <p>
	 * 
	 * @return String array containing text of all the selected items
	 */
    public String[] getMultiSelText() 
    {
        ITestData data = _ListBox.getTestData("selected");
        ITestDataList dataList = (ITestDataList) data;
        if (dataList.getElementCount() == 0)
        return null;
        ITestDataElementList elementList =
            (ITestDataElementList) dataList.getElements();
        return convertElementListToStringArray(elementList);
    }
    /**
	 * Gets the index of all the selected items in the listbox stored in this
	 * particular instance of the class
	 * <p>
	 * 
	 * @return int array containing indexes of all the selected items
	 */
    public int[] getMultiSelIndex() 
    {
        ITestData data = _ListBox.getTestData("selected");
        ITestDataList dataList = (ITestDataList) data;
        if (dataList.getElementCount() == 0)
        return null;
        ITestDataElementList elementList =
            (ITestDataElementList) dataList.getElements();
        Vector vSelectedList = elementList.getElements();
        int[] iSelectedList = new int[elementList.getLength()];
        for (int i = 0; i < iSelectedList.length; i++) 
        {
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
    public int getItemCount() 
    {
        ITestData data = (ITestData) _ListBox.getTestData("list");
        ITestDataList list = (ITestDataList) data;
        return list.getElementCount();
    }
    /**
	 * Returns all items in this listbox
	 * <p>
	 * 
	 * @return String array containing the text of each of the elements in the
	 *         listbox
	 */
    public String[] getContents() 
    {
        ITestDataList dataList = (ITestDataList) _ListBox.getTestData("list");
        if (dataList.getElementCount() == 0)
        return null;
        ITestDataElementList elementList =
            (ITestDataElementList) dataList.getElements();
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
    public boolean doesItemExist(String sItem) 
    {
        if (getItemCount() == 0)
        return false;
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
    public int getItemIndex(String sItem) 
    {
        //It would be easiest to use the Vector like so:
        //return this.getContentsAsVector().indexOf(new TestDataElement(sItem));
        //but RFT doesn't have a constructor to create an "element" type
        //and no find item methods on an ElementList
        //so there's no way to search for a particular string in the vector
        //so instead, we use the arrays and search manually
        if (getItemCount() == 0)
        return -1;
        String contents[] = this.getContents();
        for (int i = 0; i < contents.length; i++) 
        {
            if (contents[i].equals(sItem)) 
            {
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
    public String getItemText(int index) 
    {
        if (getItemCount() == 0)
        return null;
        String contents[] = this.getContents();
        if (index < 0 || index >= contents.length) 
        {
            System.out.println(
                "ListBox::getItemText: index is out of range.Returning null string");
            return null;
        }
        return contents[index];
    }
    /**
	 * Helper function for multi-select to determine whether ListBox is
	 * multi-selectable
	 * <p>
	 * (really to determine if you will get an error by trying to ctrl-click)
	 */
    protected boolean isMultiSelectable() 
    {
        if (Boolean.valueOf(_ListBox.getProperty(".multiple").toString())
        != Boolean.TRUE) 
        {
            return false;
        }
        return true;
    }
    /**
	 * Returns all items in a listbox as a Vector
	 * <p>
	 * 
	 * @return Vector of com.rational.test.ft.vp.impl.TestDataElements
	 *         representing the items in the listbox
	 */
    //protected Vector getContentsAsVector()
    public Vector getContentsAsVector()
    {
        ITestDataList dataList = (ITestDataList) _ListBox.getTestData("list");
        ITestDataElementList elementList =
            (ITestDataElementList) dataList.getElements();
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
    protected String[] convertElementListToStringArray(ITestDataElementList elementList) 
    {
        String[] list = new String[elementList.getLength()];
        for (int i = 0; i < list.length; i++)
        list[i] = elementList.getElement(i).getElement().toString();
        return list;
    }
}
