package com.sybase.automation.framework.widget.helper;

import java.util.ArrayList;
import java.util.List;

import com.rational.test.ft.object.interfaces.ScrollTestObject;
import com.rational.test.ft.object.interfaces.TestObject;
import com.rational.test.ft.script.RationalTestScript;
import com.sybase.automation.framework.widget.DOF;

public class PropertiesTabHelper extends RationalTestScript{
	public static List<String> getTabNames(){
		List<String> result = new ArrayList<String>();
		TestObject[] tlist = DOF.getRoot().find(atDescendant(
						"class","org.eclipse.ui.internal.views.properties.tabbed.view.TabbedPropertyList$ListElement"));
		if (tlist != null && tlist.length > 0)
			for (TestObject tab:tlist) {
				if (DOF.isVisible(tab)){
					result.add(TabbedPropertyListHelper.getName(tab));
				}
			}
		return result;
	
	}
	
	private static ScrollTestObject getTab(String name) {
		TestObject[] tlist = DOF.getRoot().find(atDescendant(
								"class","org.eclipse.ui.internal.views.properties.tabbed.view.TabbedPropertyList$ListElement"));
		if (tlist != null && tlist.length > 0){
			for (TestObject tab : tlist) {
				if (DOF.isVisible(tab)&&TabbedPropertyListHelper.getName(tab).equals(name)) {
					return (ScrollTestObject)tab;
				}
			}
		}
		return null;
	}
	
	public static boolean hasTabName(String name){
		for(String tab:getTabNames()){
			if(tab.equals(name)){
				return true;
			}
		}
		return false;
	}

	public static void clickTabName(String name){
		getTab(name).click();
	}

}
