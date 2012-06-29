package testscript.Auxiliary;
import resources.testscript.Auxiliary.CleanUpHelper;
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
import com.sybase.automation.framework.common.DialogCloser;

import component.entity.EE;
import component.entity.MainMenu;
import component.entity.WN;

/**
 * Description   : Functional Test Script
 * @author eric
 */
public class CleanUp extends CleanUpHelper
{
	/**
	 * Script Name   : <b>CleanUp</b>
	 * Generated     : <b>Mar 23, 2011 2:10:26 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/03/23
	 * @author eric
	 */
	public void testMain(Object[] args) 
	{
		new DialogCloser().closeDialogs();
		MainMenu.saveAll();
		MainMenu.closeAll();
		MainMenu.resetPerspective();
//		EE.deleteAllPackagesFromUnwiredServer("My Unwired Server");
//		WN.deleteAllProject();
		
	}
}

