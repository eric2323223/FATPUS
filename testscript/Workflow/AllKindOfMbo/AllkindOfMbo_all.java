package testscript.Workflow.AllKindOfMbo;
import resources.testscript.Workflow.AllKindOfMbo.AllkindOfMbo_allHelper;
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
public class AllkindOfMbo_all extends AllkindOfMbo_allHelper
{
	/**
	 * Script Name   : <b>AllkindOfMbo_all</b>
	 * Generated     : <b>Feb 4, 2012 4:34:47 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2012/02/04
	 * @author ffan
	 */
	public void testMain(Object[] args) 
	{
		// TODO Insert code here
//		callScripts("testscript.Workflow.AllKindOfMbo.*","E2E");
		
		
		//run Tooling in batch mode feature and feature
//		callScripts("testscript.Workflow.AllKindOfMbo.*","E2E");
//		callScripts("testscript.Workflow.ConditionalScreen.*", "E2E");
//		callScripts("testscript.Workflow.Designer_Prop.*","E2E");
//		callScripts("testscript.Workflow.WF_ErrorHandle.*");
		
		
		callScripts("testscript.Workflow.ConditionalStartScreen.*E2E");
		
//		////run E2E :0 out of 7 scripts failed, fail rate: 0.0%
		callScripts("testscript.Workflow.AllKindOfMbo.*E2E*");
//		//E2E: 0 out of 10 scripts failed, fail rate: 0.0%
		callScripts("testscript.Workflow.ConditionalScreen.*E2E");
//		//E2E:  0 out of 6 scripts failed, fail rate: 0.0%
		callScripts("testscript.Workflow.EmailInjection.*E2E");
//		
		callScripts("testscript.Workflow.Keys.*E2E*");
		//the result in batch mode===2 out of 37 scripts failed, fail rate: 5.4054055%
		

		
		
		
	}
}

