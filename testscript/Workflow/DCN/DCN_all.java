package testscript.Workflow.DCN;
import resources.testscript.Workflow.DCN.DCN_allHelper;
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
public class DCN_all extends DCN_allHelper
{
	/**
	 * Script Name   : <b>DCN_all</b>
	 * Generated     : <b>Jan 31, 2012 8:32:58 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2012/01/31
	 * @author eric
	 */
	public void testMain(Object[] args) 
	{
		callScripts("testscript.Workflow.DCN.*", "E2E");
	}
}

