package testscript.Workflow.Migration;
import resources.testscript.Workflow.Migration.T_Mgrt_Cust_211Helper;
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
public class T_Mgrt_Cust_211 extends T_Mgrt_Cust_211Helper
{
	/**
	 * Script Name   : <b>T_Mgrt_Cust_211</b>
	 * Generated     : <b>May 30, 2012 5:37:57 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2012/05/30
	 * @author jiaozhou
	 */
	public void testMain(Object[] args) 
	{
		WN.deleteAllProject();
		WN.importProjectFromFile(Cfg.PROJECT211);
		sleep(3);
		WN.openLegacyProjectMbo("projectfor211", "Department", true);
		vpManual("noError", 0 , WN.getMigratingResult().errors().size()).performTest();
		vpManual("noError", 0 , Problems.getErrors().size()).performTest();
		WN.createWorkFlowPackage(new WorkFlowPackage()
			.unwiredServer("My Unwired Server")
			.startParameter(WN.filePath("projectfor211", "asynccrudwf")));
		vpManual("APIjsbackup", true, WN.hasFile("projectfor211", "Generated Workflow->asynccrudwf->html->js->API.js.backup")).performTest();
		vpManual("workflowjsbackup", true, WN.hasFile("projectfor211", "Generated Workflow->asynccrudwf->html->js->WorkflowMessage.js.backup")).performTest();
		vpManual("utiljsbackup", true, WN.hasFile("projectfor211", "Generated Workflow->asynccrudwf->html->js->Utils.js.backup")).performTest();
//		vpManual("jqueryjsbackup", true, WN.hasFile("projectfor21", "Generated Workflow->query->html->js->jquery->jquery.mobile-1.0a4.js.backup")).performTest();
		vpManual("stylesheetbackup", true, WN.hasFile("projectfor211", "Generated Workflow->asynccrudwf->html->css->Stylesheet.css")).performTest();
		vpManual("customnewjsbackup", true, WN.hasFile("projectfor211", "Generated Workflow->asynccrudwf->html->js->Custom.js.new")).performTest();
	
	}
}

