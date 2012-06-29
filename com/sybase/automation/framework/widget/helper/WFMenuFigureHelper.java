package com.sybase.automation.framework.widget.helper;

import java.util.ArrayList;
import java.util.List;

import com.rational.test.ft.object.interfaces.GefEditPartTestObject;
import com.rational.test.ft.object.interfaces.TestObject;
import com.rational.test.ft.script.RationalTestScript;
import com.sybase.automation.framework.tool.NativeInvoker;
import com.sybase.automation.framework.widget.DOF;

public class WFMenuFigureHelper extends RationalTestScript{

	public static List<String> getMenuItems(GefEditPartTestObject menu) {
		List<String> itemNames = new ArrayList<String>();
		TestObject[] objs = menu.find(atDescendant(".class","com.sybase.uep.xbw.diagram.screen.edit.parts.MenuItemNameEditPart"));
		if (objs != null && objs.length > 0) {
			for (TestObject obj : objs) {
//				System.out.println(obj.getProperty("text").toString());
				itemNames.add(obj.getProperty("text").toString());
			}
		}
		return itemNames;
	}

	public static List<String> getCustomMenuItems(GefEditPartTestObject menu) {
		List<String> itemNames = new ArrayList<String>();
		TestObject[] objs = menu.find(atDescendant(".class","com.sybase.uep.xbw.diagram.screen.edit.parts.ScreenActionItemNameEditPart"));
		if (objs != null && objs.length > 0) {
			for (TestObject obj : objs) {
				itemNames.add(obj.getProperty("text").toString());
			}
		}
		return itemNames;
	}
}
