/*
 * Created on Mar 21, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.sybase.automation.framework.widget.eclipse;

import com.rational.test.ft.object.interfaces.GuiSubitemTestObject;
import com.rational.test.ft.object.interfaces.TestObject;
import com.rational.test.ft.script.SubitemFactory;
import com.sybase.automation.framework.widget.DOF;
import com.sybase.automation.framework.widget.interfaces.*;

/**
 * @author zechaoz
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class Menu extends SubitemFactory implements IMenu {
	private GuiSubitemTestObject _Menu;

	public Menu(GuiSubitemTestObject menu) {
		_Menu = menu;
	}

	public void clickAtPath(String path) {
		_Menu.click(atPath(path));
	}

	public boolean isEnabled(String path) {
		String[] parts = path.split("->");
		TestObject menu = DOF.getMenu();
		TestObject[] items = (TestObject[]) menu.invoke("getItems");
		boolean matched = false;
		TestObject item = null;
		for (int i = 0; i < parts.length; i++) {
			matched = false;
			for (int j = 0; j < items.length; j++) {
				String itemName = stripOff(items[j].invoke("getText").toString(), "&");
				if (itemName.indexOf(parts[i]) != -1) {
					item = items[j];
					if (i < parts.length - 1) {
						items = (TestObject[]) ((TestObject) items[j].invoke("getMenu")).invoke("getItems");
						matched = true;
						break;
					} else {
						return ((Boolean) item.invoke("isEnabled")).booleanValue();
					}
				}
			}
			if (!matched)
				return false;
		}
		return false;
	}

	private static String stripOff(String source, String symbol) {
		String result = "";
		String[] parts = source.split(symbol);
		for (int i = 0; i < parts.length; i++) {
			result = result + parts[i];
		}
		return result;
	}
}
