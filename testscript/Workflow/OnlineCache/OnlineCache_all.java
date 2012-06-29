package testscript.Workflow.OnlineCache;
import resources.testscript.Workflow.OnlineCache.OnlineCache_allHelper;
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
public class OnlineCache_all extends OnlineCache_allHelper
{
	/**
	 * Script Name   : <b>OnlineCache_all</b>
	 * Generated     : <b>Feb 12, 2012 7:18:40 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2012/02/12
	 * @author eric
	 */
	public void testMain(Object[] args) 
	{
		callScripts("testscript.Workflow.OnlineCache.*","E2E");
	}
}

