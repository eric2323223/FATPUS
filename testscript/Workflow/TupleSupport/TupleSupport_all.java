package testscript.Workflow.TupleSupport;
import resources.testscript.Workflow.TupleSupport.TupleSupport_allHelper;
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
public class TupleSupport_all extends TupleSupport_allHelper
{
	/**
	 * Script Name   : <b>TupleSupport_all</b>
	 * Generated     : <b>Feb 4, 2012 4:52:08 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2012/02/04
	 * @author ffan
	 */
	public void testMain(Object[] args) 
	{
		// TODO Insert code here
//		callScripts("testscript.Workflow.TupleSupport.*");
		callScript("testscript.Workflow.TupleSupport.Tuple_DynamicChc_to_DynamicChc_1_E2E");
		callScript("testscript.Workflow.TupleSupport.Tuple_DynamicChc_to_DynamicChc_2_E2E");
		callScript("testscript.Workflow.TupleSupport.Tuple_DynamicChc_to_DynamicChc_3_E2E");
	
		
//		callScript("testscript.Workflow.TupleSupport.Tuple_DynamicChoice_to_Editbox_1_E2E");
		callScript("testscript.Workflow.TupleSupport.Tuple_DynamicChoice_to_Editbox_2_E2E");
//		callScript("testscript.Workflow.TupleSupport.Tuple_StaticChoice_to_Editbox_1_E2E");
		callScript("testscript.Workflow.TupleSupport.Tuple_StaticChoice_to_Editbox_3_E2E");
	
	
	
	}
}





