package testscript.Workflow.Designer_Prop;
import resources.testscript.Workflow.Designer_Prop.Designer_Prop_AllHelper;
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

/**
 * Description   : Functional Test Script
 * @author ffan
 */
public class Designer_Prop_All extends Designer_Prop_AllHelper
{
	/**
	 * Script Name   : <b>Designer_Prop_All</b>
	 * Generated     : <b>Aug 28, 2011 6:46:10 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/08/28
	 * @author ffan
	 */
	public void testMain(Object[] args) 
	{
		// TODO Insert code here
		callScripts("testscript.Workflow.Designer_Prop.*","E2E");
	}
}

