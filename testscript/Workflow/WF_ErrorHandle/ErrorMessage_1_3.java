package testscript.Workflow.WF_ErrorHandle;
import resources.testscript.Workflow.WF_ErrorHandle.ErrorMessage_1_3Helper;
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
import component.entity.EE;
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
public class ErrorMessage_1_3 extends ErrorMessage_1_3Helper
{
	/**
	 * Script Name   : <b>ErrorMessage_1_3</b>
	 * Generated     : <b>Jan 12, 2012 10:20:28 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2012/01/12
	 * @author ffan
	 */
	public void testMain(Object[] args) 
	{
		// TODO Insert code here
		WN.useProject(Cfg.projectName);
		EE.dnd("Database Connections->My Sample Database->sampledb->Tables->department (dba)");
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name("myWF")
				.option(WorkFlow.SP_CLIENT_INIT));
		
		WorkFlowEditor.addScreen("ActScr");
		WorkFlowEditor.addScreen("ErrScr");
		WorkFlowEditor.addScreen("ErrDetailScr");
		
		WorkFlowEditor.addWidget("Start", new WFEditBox()
				.label("dept_id:")
				.newKey("keyid,int"));
		WorkFlowEditor.addMenuItem("Start", new WFScreenMenuItem().name("findByPrimaryKey")
				.type("Online Request")
				.project(Cfg.projectName)
				.mbo("Department")
				.objectQuery("findByPrimaryKey")
				.defaultSuccessScreen("ActScr")
				.parametermapping("dept_id,keyid"));
		
		WorkFlowEditor.addWidget("ActScr", new WFEditBox()
				.label("dept_id:")
				.key("Department_dept_id_attribKey"));
		WorkFlowEditor.addWidget("ActScr", new WFEditBox()
				.label("dept_name:")
				.key("Department_dept_name_attribKey"));
		WorkFlowEditor.addWidget("ActScr", new WFEditBox()
				.label("dept_head_id:")
				.key("Department_dept_head_id_attribKey"));
		
		//Create:
		WorkFlowEditor.addMenuItem("ActScr", new WFScreenMenuItem().name("submit")
				.type("Online Request")
				.project(Cfg.projectName)
				.mbo("Department")
				.operation("create")
				.errorScreen("ErrScr")
				.defaultSuccessScreen("ActScr")
				.parametermapping("dept_id,Department_dept_id_attribKey")
				.parametermapping("dept_name,Department_dept_name_attribKey")
				.parametermapping("dept_head_id,Department_dept_head_id_attribKey")
				);
		
		System.out.print("keys="+PropertiesView.getKeyOfScreen("ErrScr").getKey());
		vpManual("key",true,PropertiesView.getKeyOfScreen("ErrScr").getKey().contains("ErrorLogs,list")).performTest();
		
		WorkFlowEditor.addWidget("ErrScr", new WFLview()
			.key("ErrorLogs")
			.lvDetailScreen("ErrDetailScr"));
		
		WorkFlowEditor.addWidget("ErrDetailScr", new WFEditBox()
			.label("myEB:")
			.key("ErrorLogMessage"));
		
		vpManual("noError",0,Problems.getErrors().size()).performTest();
	}
}
//passed
