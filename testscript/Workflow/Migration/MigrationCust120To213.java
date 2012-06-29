package testscript.Workflow.Migration;
import resources.testscript.Workflow.Migration.MigrationCust120To213Helper;
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
 * @author eric
 */
public class MigrationCust120To213 extends MigrationCust120To213Helper
{
	/**
	 * Script Name   : <b>MigrationCust120To213</b>
	 * Generated     : <b>Mar 6, 2012 6:52:02 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2012/03/06
	 * @author eric
	 */
	public void testMain(Object[] args) 
	{
		WN.deleteAllProject();
		WN.importProjectFromFile(Cfg.PROJECT20);
		sleep(3);
		WN.openLegacyProjectMbo("pro20", "Department", true);
		vpManual("noError", 0 , WN.getMigratingResult().errors().size()).performTest();
		vpManual("noError", 0 , Problems.getErrors().size()).performTest();
		WN.createWorkFlowPackage(new WorkFlowPackage()
			.unwiredServer("My Unwired Server")
			.startParameter(WN.filePath("pro20", "query")));
		vpManual("APIjsbackup", true, WN.hasFile("pro20", "Generated Workflow->query->html->js->API.js.backup")).performTest();
		vpManual("workflowjsbackup", true, WN.hasFile("pro20", "Generated Workflow->query->html->js->WorkflowMessage.js.backup")).performTest();
		vpManual("utiljsbackup", true, WN.hasFile("pro20", "Generated Workflow->query->html->js->Utils.js.backup")).performTest();
		vpManual("jqueryjsbackup", true, WN.hasFile("pro20", "Generated Workflow->query->html->js->jquery->jquery.mobile-1.0a3.js.backup")).performTest();
		vpManual("stylesheetbackup", true, WN.hasFile("pro20", "Generated Workflow->query->html->css->Stylesheet.css")).performTest();
		vpManual("customnewjsbackup", true, WN.hasFile("pro20", "Generated Workflow->query->html->js->Custom.js.new")).performTest();
	
	}
}

