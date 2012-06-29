package com.sybase.automation.framework.widget.helper;

import com.rational.test.ft.object.interfaces.TestObject;
import com.rational.test.ft.script.RationalTestScript;

public class TabbedPropertyListHelper extends RationalTestScript{
	
	public static String getName(TestObject tab){
		return ((TestObject)tab.invoke("getTabItem")).invoke("getText").toString();
	}
	

	public static void main(String[] args){
		System.out.println("hello, world!");
	}
}
