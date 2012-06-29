package com.sybase.automation.framework.widget.helper;

import java.util.ArrayList;
import java.util.List;

import com.rational.test.ft.object.interfaces.GuiSubitemTestObject;
import com.rational.test.ft.object.interfaces.TestObject;
import com.rational.test.ft.script.RationalTestScript;

public class TabFolderHelper extends RationalTestScript {

	public static List<String> getItemNames(TestObject tabFolder){
		List<String> result = new ArrayList<String> ();
		TestObject[] items = (TestObject[])tabFolder.invoke("getItems");
		for(TestObject item:items){
			String name = item.invoke("getText").toString();
			result.add(name);
		}
		return result;
	}

	public static boolean hasItem(TestObject tabFolder, String str) {
		for(String name:getItemNames(tabFolder)){
			if(name.equals(str)){
				return true;
			}
		}
		return false;
	}
}
