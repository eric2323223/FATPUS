/*
 * Created on Jul 5, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.sybase.automation.framework.widget.temp;

import com.rational.test.ft.object.interfaces.GuiSubitemTestObject;
import com.rational.test.ft.object.interfaces.TestObject;
import com.rational.test.ft.sys.graphical.Menu;

/**
 * @author xfu
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class PopupMenu {
	private TestObject menu;

	public PopupMenu(GuiSubitemTestObject ob) {
		menu = (TestObject) ob.invoke("getPopupMenu");
	}

	/**
	 * @return
	 */
	public MenuItem[] getMenuItems() {
		// TODO Auto-generated method stub
		MenuItem[] items = new MenuItem[getItemCount()];
		for (int i = 0; i < items.length; i++) {
			String item = (String) menu.invoke("getItemText",
					"(I)Ljava/lang/String;", new Object[] { new Integer(i) });
			items[i] = new MenuItem(item);
		}
		return items;
	}

	public int getItemCount() {
		return Integer.valueOf(menu.invoke("getItemCount").toString())
				.intValue();
	}

}
