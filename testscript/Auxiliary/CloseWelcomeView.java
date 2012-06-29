package testscript.Auxiliary;
import resources.testscript.Auxiliary.CloseWelcomeViewHelper;
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
public class CloseWelcomeView extends CloseWelcomeViewHelper
{
	/**
	 * Script Name   : <b>CloseWelcomeView</b>
	 * Generated     : <b>Mar 18, 2011 1:38:10 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/03/18
	 * @author eric
	 */
	public void testMain(Object[] args) 
	{
		if(DOF.getCTabFolder(DOF.getRoot(), "Welcome")!=null){
			DOF.getCTabFolder(DOF.getRoot(), "Welcome").click(RIGHT, atText("Welcome"));
			DOF.getContextMenu().click(atPath("Close"));
		}
		DOF.getRoot().maximize();
	}
}

