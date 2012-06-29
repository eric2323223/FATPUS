package testscript.Workflow.Migration;
import resources.testscript.Workflow.Migration.T_Mgrt_Cust_201Helper;
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
import component.entity.model.WorkFlowPackage;
import component.view.Problems;

/**
 * Description   : Functional Test Script
 * @author jiaozhou
 */
public class T_Mgrt_Cust_201 extends T_Mgrt_Cust_201Helper
{
	/**
	 * Script Name   : <b>T_Mgrt_Cust_201</b>
	 * Generated     : <b>May 30, 2012 4:35:38 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2012/05/30
	 * @author jiaozhou
	 */
	public void testMain(Object[] args) 
	{
		WN.deleteAllProject();
		WN.importProjectFromFile(Cfg.PROJECT201);
		sleep(3);
		WN.openLegacyProjectMbo("pro201", "Department", true);
		vpManual("noError", 0 , WN.getMigratingResult().errors().size()).performTest();
		vpManual("noError", 0 , Problems.getErrors().size()).performTest();
		WN.createWorkFlowPackage(new WorkFlowPackage()
			.unwiredServer("My Unwired Server")
			.startParameter(WN.filePath("pro201", "query")));
		vpManual("APIjsbackup", true, WN.hasFile("pro201", "Generated Workflow->query->html->js->API.js.backup")).performTest();
		vpManual("workflowjsbackup", true, WN.hasFile("pro201", "Generated Workflow->query->html->js->WorkflowMessage.js.backup")).performTest();
		vpManual("utiljsbackup", true, WN.hasFile("pro201", "Generated Workflow->query->html->js->Utils.js.backup")).performTest();
		vpManual("jqueryjsbackup", true, WN.hasFile("pro201", "Generated Workflow->query->html->js->jquery->jquery.mobile-1.0a3.js.backup")).performTest();
		vpManual("stylesheetbackup", true, WN.hasFile("pro201", "Generated Workflow->query->html->css->Stylesheet.css")).performTest();
		vpManual("customnewjsbackup", true, WN.hasFile("pro201", "Generated Workflow->query->html->js->Custom.js.new")).performTest();
	
	}
}

