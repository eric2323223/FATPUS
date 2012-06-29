package testscript.Workflow.Controls;
import resources.testscript.Workflow.Controls.listview_coincident_AndroidHelper;
import testscript.Workflow.cfg.Cfg;

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
import com.sybase.automation.framework.widget.temp.MenuItem;

import component.entity.EE;
import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.WorkFlowEditor;
import component.entity.model.DeployOption;
import component.entity.model.WFScreenMenuItem;
import component.entity.model.WorkFlowPackage;

/**
 * Description   : Functional Test Script
 * @author eric
 */
public class listview_coincident_Android extends listview_coincident_AndroidHelper
{
	/**
	 * Script Name   : <b>listview_coincident_Android</b>
	 * Generated     : <b>Sep 5, 2011 4:07:17 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/09/05
	 * @author FANFEI
	 */
	public void testMain(Object[] args) 
	{
		// TODO Insert code here
		WN.useProject(Cfg.projectName);
		EE.dnd("Database Connections->My Sample Database->sampledb->Tables->department (dba)", 10, 10);
		WN.deployProject(new DeployOption().startParameter(Cfg.projectName)
				.server("My Unwired Server")
				.serverConnectionMapping("My Sample Database,sampledb"));
		
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName).name(
				"wf_listview_coincident").option(WorkFlow.SP_CLIENT_INIT));
		WorkFlowEditor.dragMbo(Cfg.projectName, "Department");
		WorkFlowEditor.addMenuItem("Start Screen", new WFScreenMenuItem()
		         .name("findall")
		         .type("Online Request")
		         .mbo("wf/Department")
		         .objectQuery("findAll")
		         .defaultSuccessScreen("DepartmentDetail"));
		
		WN.createWorkFlowPackage(new WorkFlowPackage()
				.startParameter(WN.wfPath(Cfg.projectName, "wf_listview_coincident"))
				.unwiredServer("My Unwired Server")
				.assignToUser("DeviceTest")
				.verifyResult("Successfully deployed the workflow", true));
		//ET:ok      device: need to verify the data is same as the detail after.
	}
}

