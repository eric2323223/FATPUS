package testscript.Workflow.WF_ErrorHandle;
import resources.testscript.Workflow.WF_ErrorHandle.ErrorMessage_4_1Helper;
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
import com.sybase.automation.framework.widget.helper.TableHelper;
import com.sybase.automation.framework.widget.helper.WO;

import component.entity.EE;
import component.entity.MainMenu;
import component.entity.PropertiesView;
import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.WorkFlowEditor;
import component.entity.model.WFEditBox;
import component.entity.model.WFLview;
import component.entity.model.WFScreenMenuItem;
import component.view.Problems;

/**
 * Description   : Functional Test Script
 * @author ffan
 */
public class ErrorMessage_4_1 extends ErrorMessage_4_1Helper
{
	/**
	 * Script Name   : <b>ErrorMessage_4</b>
	 * Generated     : <b>Oct 14, 2011 3:03:57 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/10/14
	 * @author ffan
	 */
	public void testMain(Object[] args) 
	{
		// TODO Insert code here
		WN.useProject(Cfg.projectName);
		EE.dnd("Database Connections->My Sample Database->sampledb->Tables->department (dba)");
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name("myWF")
				.option(WorkFlow.SP_SERVER_INIT));
		
		PropertiesView.jumpStart(new WorkFlow()
				.mbo("Department")
				.objectQuery("findByPrimaryKey")
				.subject("dept_id=100")
				.subjectMatchingRule("dept_id=")
				.setParameterValue("dept_id,Subject,dept_id="));
		
		WorkFlowEditor.addScreen("ActScr");
		WorkFlowEditor.addScreen("DumScr");
		
		WorkFlowEditor.link(WorkFlow.SP_SERVER_INIT, "ActScr");
		
		WorkFlowEditor.addWidget("ActScr", new WFEditBox().label("dept_id:")
					.key("dept_id"));
		WorkFlowEditor.addWidget("ActScr", new WFEditBox().label("dept_name:")
					.newKeyBindMbo("name,string,Department,dept_name"));
		WorkFlowEditor.addWidget("ActScr", new WFEditBox().label("dept_head_id:")
					.newKeyBindMbo("head_id,int,Department,dept_head_id"));
		//delete:
		WorkFlowEditor.addMenuItem("ActScr", new WFScreenMenuItem().name("submit")
				.type("Submit Workflow")
				.project(Cfg.projectName)
				.mbo("Department")
				.operation("delete"));
		
		WorkFlowEditor.link("ActScr", "DumScr");
		
		vpManual("key","name,string,Department/dept_name,:head_id,int,Department/dept_head_id,:dept_id,int,subject => dept_id=<dept_id>,:AsyncRequestErrorLogs,list,,",PropertiesView.getKeyOfScreen("DumScr").getKey()).performTest();
		vpManual("noError",0,Problems.getErrors().size()).performTest();
	}
}
//passed
