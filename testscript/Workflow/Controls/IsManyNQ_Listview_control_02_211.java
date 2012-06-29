package testscript.Workflow.Controls;
import resources.testscript.Workflow.Controls.IsManyNQ_Listview_control_02_211Helper;
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
import com.sybase.automation.framework.widget.DOF;

import component.entity.EE;
import component.entity.MBOProperties;
import component.entity.PropertiesView;
import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.WorkFlowEditor;
import component.entity.model.DeployOption;
import component.entity.model.DeployedMbo;
import component.entity.model.Relationship;
import component.entity.model.WFLview;
import component.entity.model.WFNewscreen;

/**
 * Description   : Functional Test Script
 * @author xjf
 */
public class IsManyNQ_Listview_control_02_211 extends IsManyNQ_Listview_control_02_211Helper
{
	/**
	 * Script Name   : <b>Script1</b>
	 * Generated     : <b>Oct 25, 2011 3:50:45 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/10/25
	 * @author xjf
	 */
	public void testMain(Object[] args) 
	{
		// TODO Insert code here
		
		//test IsManyNQ_Listview_control_02
		
		WN.useProject(Cfg.projectName);
		EE.dnd(EE.parseResourcePath("Database Connections->My Sample Database->sampledb->Tables->department (dba)"), 100, 10);
	
	 
	   WN.deployProject(new DeployOption().startParameter(Cfg.projectName)
				.server("My Unwired Server")
				.serverConnectionMapping("My Sample Database,simpledb"));
	   

		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName).name("wflist")
				.option(WorkFlow.SP_SERVER_INIT));


		PropertiesView.jumpStart(new WorkFlow()
		        .mbo("Department")
				.objectQuery("findAll")
				.subject("getall")
				.subjectMatchingRule("getall")
				);
	
		WorkFlowEditor.dragMbo(Cfg.projectName, "Department");
		
		//verify listview scr and detail scr exist
		vpManual("haslistviewscr","true",WorkFlowEditor.hasScreen("Department")).performTest();
		vpManual("hasdetailscr","true",WorkFlowEditor.hasScreen("DepartmentDetail")).performTest();
		
		//reopen
		DOF.getRoot().click();
		   WN.closeAll();
		   WN.openWorkFlow("wf", "wflist.xbw");
		   DOF.getCTabItem(DOF.getRoot(), "Flow Design").click(atPoint(25,10));
		   
		   vpManual("haslistviewscr","true",WorkFlowEditor.hasScreen("Department")).performTest();
			vpManual("hasdetailscr","true",WorkFlowEditor.hasScreen("DepartmentDetail")).performTest();
	}
}

