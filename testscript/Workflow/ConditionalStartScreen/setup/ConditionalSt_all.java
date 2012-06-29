package testscript.Workflow.ConditionalStartScreen.setup;
import resources.testscript.Workflow.ConditionalStartScreen.setup.ConditionalSt_allHelper;
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
 * @author mzhao
 */
public class ConditionalSt_all extends ConditionalSt_allHelper
{
	/**
	 * Script Name   : <b>ConditionalSt_all</b>
	 * Generated     : <b>Apr 24, 2012 9:33:20 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2012/04/24
	 * @author mzhao
	 */
	public void testMain(Object[] args) 
	{
		callScript("testscript.Workflow.ConditionalStartScreen.ConditionalSt_Activation_694234_E2E");
		callScript("testscript.Workflow.ConditionalStartScreen.ConditionalSt_Boolean_1_E2E");
		callScript("testscript.Workflow.ConditionalStartScreen.ConditionalSt_Boolean_2_E2E");
		callScript("testscript.Workflow.ConditionalStartScreen.ConditionalSt_Compound_E2E");
		callScript("testscript.Workflow.ConditionalStartScreen.ConditionalSt_Decimal_1_E2E");
		callScript("testscript.Workflow.ConditionalStartScreen.ConditionalSt_Decimal_2_E2E");
		callScript("testscript.Workflow.ConditionalStartScreen.ConditionalSt_Double_1_E2E");
		callScript("testscript.Workflow.ConditionalStartScreen.ConditionalSt_Double_2_E2E");
		callScript("testscript.Workflow.ConditionalStartScreen.ConditionalSt_Int_1_E2E");
		callScript("testscript.Workflow.ConditionalStartScreen.ConditionalSt_Int_2_E2E");
		callScript("testscript.Workflow.ConditionalStartScreen.ConditionalSt_String_1_E2E");
		callScript("testscript.Workflow.ConditionalStartScreen.ConditionalSt_String_2_E2E");
		callScript("testscript.Workflow.ConditionalStartScreen.ConditionalSt_List_1_E2E");
		callScript("testscript.Workflow.ConditionalStartScreen.ConditionalSt_List_2_E2E");
		
	}
}

