package testscript.Workflow.ScreenFlow;
import resources.testscript.Workflow.ScreenFlow.ScreenFlow_allHelper;
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
 * @author eric
 */
public class ScreenFlow_all extends ScreenFlow_allHelper
{
	/**
	 * Script Name   : <b>ScreenFlow_all</b>
	 * Generated     : <b>Jan 31, 2012 12:26:41 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2012/01/31
	 * @author eric
	 */
	public void testMain(Object[] args) 
	{
		callScripts("testscript.Workflow.ScreenFlow.*","E2E");
	}
}

