package com.sybase.automation.framework.tool;
import java.util.Hashtable;

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
import com.sybase.automation.framework.widget.DOF;

/**
 * Description   : Functional Test Script
 * @author eric
 */
public class DialogCloser extends RationalTestScript
{
	/**
	 * Script Name   : <b>DialogCloser</b>
	 * Generated     : <b>Aug 23, 2010 5:25:31 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2010/08/23
	 * @author eric
	 */
	public void testMain(Object[] args) 
	{
		// TODO Insert code here
		closeAll();
	}
	
	private Hashtable<String, String> dialogs = new Hashtable<String, String>();
	
	public DialogCloser(){
		dialogs.put("Preview", "OK");
		dialogs.put("Properties Dialog","OK");
	}
	
	public boolean close(String name){
		if (dialogs.get(name) != null) {
			DOF.getButton(DOF.getDialog(name), dialogs.get(name)).click();
			return true;
		}else{
			System.out.println("Don't know how to close dialog: "+name);
			return false;
		}
	}
	
	public void closeAll(){
		while(true){
			String currentTopWindow = DOF.getTopDialogName();
			if(!currentTopWindow.startsWith("Mobile Development")){
				if (close(currentTopWindow)) {
					sleep(1);
				}else{
					break;
				}
			}else{
				break;
			}
		}
	}
}

