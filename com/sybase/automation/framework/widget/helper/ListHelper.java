package com.sybase.automation.framework.widget.helper;

import com.rational.test.ft.*;
import com.rational.test.ft.object.interfaces.*;
import com.rational.test.ft.object.interfaces.SAP.*;
import com.rational.test.ft.object.interfaces.WPF.*;
import com.rational.test.ft.object.interfaces.dojo.*;
import com.rational.test.ft.object.interfaces.siebel.*;
import com.rational.test.ft.object.interfaces.flex.*;
import com.rational.test.ft.script.*;
import com.rational.test.ft.value.*;
import com.rational.test.ft.vp.*;

/**
 * Description : Functional Test Script
 * 
 * @author eric
 */
public class ListHelper extends RationalTestScript {
	/**
	 * Script Name : <b>ListHelper</b> Generated : <b>Aug 11, 2010 2:46:35
	 * PM</b> Description : Functional Test Script Original Host : WinNT Version
	 * 5.1 Build 2600 (S)
	 * 
	 * @since 2010/08/11
	 * @author eric
	 */
	public void testMain(Object[] args) {
		// TODO Insert code here
	}

	public static boolean hasItem(TestObject list, String item) {
		String[] items = getItems(list);
		if (items == null) {
			return false;
		} else {
			for (String entry : items) {
				if (entry.equals(item)) {
					return true;
				}
			}
			return false;
		}
	}

	public static String[] getItems(TestObject list) {
		String[] items = (String[]) list.invoke("getItems");
		return items;
	}
	
	public static void chooseElement(SelectGuiSubitemTestObject list, String element){
		list.click(atPath(element));
	}

}
