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
import com.sybase.automation.framework.tool.NativeInvoker;
import com.sybase.automation.framework.widget.DOF;

/**
 * Description   : Functional Test Script
 * @author eric
 */
public class MenuHelper extends RationalTestScript
{
	/**
	 * Script Name   : <b>MenuHelper</b>
	 * Generated     : <b>Dec 21, 2010 11:14:55 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2010/12/21
	 * @author eric
	 */
	public void testMain(Object[] args) 
	{
		System.out.println(isItemEnabled(DOF.getMenu(), "File->Revert"));
		System.out.println(isItemEnabled(DOF.getMenu(), "Edit->Copy"));
		DOF.getWFActivateFlowStartingPointFigure().click(RIGHT);
		System.out.println(isItemEnabled(DOF.getContextMenu(), "Edit->Copy"));
	}
	
	public static boolean isItemEnabled(TestObject menu, String itemPath){
		TestObject item = getItem(menu, itemPath);
//		DOF.printMethods(item);
		return item.invoke("isEnabled").toString().equalsIgnoreCase("true")?true:false;
	}
	
	public static TestObject getItem(TestObject menu, String itemPath){
		String[] pathLevels = itemPath.split("->");
		TestObject currentMenu = menu;
		for(int i =0;i<pathLevels.length;i++){
			TestObject[] children = (TestObject[])currentMenu.invoke("getItems");
			boolean matchFlag = false;
			for(TestObject item:children){
				String itemText = item.invoke("getText").toString().replace("&", "");
				if(itemText.contains(pathLevels[i])){
					if(i==pathLevels.length-1){
						return item;
					}
					else{
						currentMenu = (TestObject)item.invoke("getMenu");
						matchFlag = true;
						break;
					}
				}
			}
			if(!matchFlag){
				return null;
			}
		}
		return null;
	}

	public static boolean hasItem(GuiSubitemTestObject contextMenu, String string) {
		try{
			contextMenu.click(atPath(string));
			return true;
		}catch(SubitemNotFoundException e){
			return false;
		}
	}
	
}

