package testscript.Workflow.Migration;
import resources.testscript.Workflow.Migration.T_Mgrt_CustomAction_21Helper;
import testscript.Workflow.Migration.cfg.Cfg;

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
import component.entity.WN;
import component.entity.WorkFlowEditor;
import component.entity.model.WFScreenMenuItem;
import component.view.Problems;

/**
 * Description   : Functional Test Script
 * @author jiaozhou
 */
public class T_Mgrt_CustomAction_21 extends T_Mgrt_CustomAction_21Helper
{
	/**
	 * Script Name   : <b>T_Mgrt_CustomAction_21</b>
	 * Generated     : <b>May 30, 2012 4:52:59 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2012/05/30
	 * @author jiaozhou
	 */
	public void testMain(Object[] args) 
	{
		WN.deleteAllProject();
		WN.importProjectFromFile(Cfg.PROJECT21);
		WN.openLegacyProjectMbo("projectfor21", "Department", true);
		vpManual("noError", 0 , WN.getMigratingResult().errors().size()).performTest();
		vpManual("noError", 0 , Problems.getErrors().size()).performTest();
		WN.openWorkFlow("projectfor21", "query");
		WorkFlowEditor.addCustomAction("Start Screen", new WFScreenMenuItem().name("item1")
				.type("Open")
				.screen("Department_update_instance"));
		vpManual("customAction", true, WorkFlowEditor.hasLinkBetween("Start Screen", "Department_update_instance")).performTest();
	
	}
}

