package testscript.Workflow.Migration;
import resources.testscript.Workflow.Migration.Migration_allHelper;
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
 * @author jiaozhou
 */
public class Migration_all extends Migration_allHelper
{
	/**
	 * Script Name   : <b>Migration_all</b>
	 * Generated     : <b>Apr 25, 2012 1:52:41 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2012/04/25
	 * @author jiaozhou
	 */
	public void testMain(Object[] args) 
	{
		//------Tooling part----------
		callScript("testscript.Workflow.Migration.MigrationCust120To213");
		callScript("testscript.Workflow.Migration.Migration_CustomAction_20To213");
		callScript("testscript.Workflow.Migration.Migration_asyncMenu_20To213");
		callScript("testscript.Workflow.Migration.Migration_IntroTab_20To213");
		
		callScript("testscript.Workflow.Migration.T_Mgrt_Cust_201");
		callScript("testscript.Workflow.Migration.T_Mgrt_CustomAction_201");
		callScript("testscript.Workflow.Migration.T_Mgrt_asyncMenu_201");
		callScript("testscript.Workflow.Migration.T_Mgrt_IntroTab_201");
		
		callScript("testscript.Workflow.Migration.T_Mgrt_Cust_21");
		callScript("testscript.Workflow.Migration.T_Mgrt_CustomAction_21");
		callScript("testscript.Workflow.Migration.T_Mgrt_asyncMenu_21");
		callScript("testscript.Workflow.Migration.T_Mgrt_IntroTab_21");
		
		callScript("testscript.Workflow.Migration.T_Mgrt_Cust_211");
		callScript("testscript.Workflow.Migration.T_Mgrt_CustomAction_211");
		callScript("testscript.Workflow.Migration.T_Mgrt_asyncMenu_211");
		callScript("testscript.Workflow.Migration.T_Mgrt_IntroTab_211");
		
		callScript("testscript.Workflow.Migration.T_Mgrt_Cust_212");
		callScript("testscript.Workflow.Migration.T_Mgrt_CustomAction_212");
		callScript("testscript.Workflow.Migration.T_Mgrt_asyncMenu_212");
		callScript("testscript.Workflow.Migration.T_Mgrt_IntroTab_212");
		
		//------E2E part--------------
		//Migration for 2.0 Project
		callScript("testscript.Workflow.Migration.MigrationCRDSyc20To213");
		callScript("testscript.Workflow.Migration.MigrationCRDSyc20To213_2");
		callScript("testscript.Workflow.Migration.MigrationCRDSyc20To213_3");
		callScript("testscript.Workflow.Migration.MigrationCUDASyc20To213_1");
		callScript("testscript.Workflow.Migration.MigrationCUDASyc20To213_2");
		callScript("testscript.Workflow.Migration.MigrationCUDASyc20To213_3");
		callScript("testscript.Workflow.Migration.Mgrt_OQ_findbypk20To213");
		callScript("testscript.Workflow.Migration.Mgrt_OQ_findall20To213");
		callScript("testscript.Workflow.Migration.Mgrt_OLC_Q20To213");
		callScript("testscript.Workflow.Migration.Mgrt_SP_20To213");
		callScript("testscript.Workflow.Migration.Mgrt_Ctrl_20To213");
		callScript("testscript.Workflow.Migration.Mgrt_ComplexListview_20To213");
	    //Migration for 2.0.1 Project
		callScript("testscript.Workflow.Migration.Mgrt_201To213_CUD_1");
		callScript("testscript.Workflow.Migration.Mgrt_201To213_CUD_2");
		callScript("testscript.Workflow.Migration.Mgrt_201To213_CUD_3");
		callScript("testscript.Workflow.Migration.Mgrt_201To213_ACUD_1");
		callScript("testscript.Workflow.Migration.Mgrt_201To213_ACUD_2");
		callScript("testscript.Workflow.Migration.Mgrt_201To213_ACUD_3");
		callScript("testscript.Workflow.Migration.Mgrt_201To213_OQfindbypk");
		callScript("testscript.Workflow.Migration.Mgrt_201To213_OQfindall");
		callScript("testscript.Workflow.Migration.Mgrt_201To213_OLC");
		callScript("testscript.Workflow.Migration.Mgrt_201To213_SP");
		callScript("testscript.Workflow.Migration.Mgrt_201To213_Ctrl");
		callScript("testscript.Workflow.Migration.Mgrt_201To213_ComplexListview");
		//Migration for 2.1 Project
		callScript("testscript.Workflow.Migration.Mgrt_21To213_CUD_1");
		callScript("testscript.Workflow.Migration.Mgrt_21To213_CUD_2");
		callScript("testscript.Workflow.Migration.Mgrt_21To213_CUD_3");
		callScript("testscript.Workflow.Migration.Mgrt_21To213_ACUD_1");
		callScript("testscript.Workflow.Migration.Mgrt_21To213_ACUD_2");
		callScript("testscript.Workflow.Migration.Mgrt_21To213_ACUD_3");
		callScript("testscript.Workflow.Migration.Mgrt_21To213_OQfindbypk");
		callScript("testscript.Workflow.Migration.Mgrt_21To213_OQfindall");
		callScript("testscript.Workflow.Migration.Mgrt_21To213_OLC");
		callScript("testscript.Workflow.Migration.Mgrt_21To213_SP");
		callScript("testscript.Workflow.Migration.Mgrt_21To213_Ctrl");
		callScript("testscript.Workflow.Migration.Mgrt_21To213_ComplexListview");
		callScript("testscript.Workflow.Migration.Mgrt_21To213_dynamicChoice");
		//Migration for 2.1.1 Project
		callScript("testscript.Workflow.Migration.Mgrt_211To213_CUD_1");
		callScript("testscript.Workflow.Migration.Mgrt_211To213_CUD_2");
		callScript("testscript.Workflow.Migration.Mgrt_211To213_CUD_3");
		callScript("testscript.Workflow.Migration.Mgrt_211To213_ACUD_1");
		callScript("testscript.Workflow.Migration.Mgrt_211To213_ACUD_2");
		callScript("testscript.Workflow.Migration.Mgrt_211To213_ACUD_3");
		callScript("testscript.Workflow.Migration.Mgrt_211To213_Ctrl");
		
		//Migration for 2.1.2 Project
		callScript("testscript.Workflow.Migration.Mgrt_212To213_CUD_1");
		callScript("testscript.Workflow.Migration.Mgrt_212To213_CUD_2");
		callScript("testscript.Workflow.Migration.Mgrt_212To213_CUD_3");
		callScript("testscript.Workflow.Migration.Mgrt_212To213_ACUD_1");
		callScript("testscript.Workflow.Migration.Mgrt_212To213_ACUD_2");
		callScript("testscript.Workflow.Migration.Mgrt_212To213_ACUD_3");
		callScript("testscript.Workflow.Migration.Mgrt_212To213_OQfindbypk");
		callScript("testscript.Workflow.Migration.Mgrt_212To213_OQfindall");
		callScript("testscript.Workflow.Migration.Mgrt_212To213_OLC");
		callScript("testscript.Workflow.Migration.Mgrt_212To213_SP");
		callScript("testscript.Workflow.Migration.Mgrt_212To213_Ctrl");
		callScript("testscript.Workflow.Migration.Mgrt_212To213_ComplexListview");	
		callScript("testscript.Workflow.Migration.Mgrt_RestoreData");
	}
}

