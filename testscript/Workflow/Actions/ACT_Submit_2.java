package testscript.Workflow.Actions;
import resources.testscript.Workflow.Actions.ACT_Submit_2Helper;
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
import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.WorkFlowEditor;
import component.entity.model.StartPoint;
import component.entity.model.WFEditBox;
import component.entity.model.WFScreenMenuItem;

/**
 * Description   : Functional Test Script
 * @author eric
 */
public class ACT_Submit_2 extends ACT_Submit_2Helper
{
	/**
	 * Script Name   : <b>ACT_Submit_2</b>
	 * Generated     : <b>Sep 21, 2011 12:51:58 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/09/21
	 * @author eric
	 */
	public void testMain(Object[] args) 
	{
		WN.useProject(Cfg.projectName);
		EE.dnd("Database Connections->My Sample Database->sampledb->Tables->department (dba)");
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
			.name("myWF")
			.option(WorkFlow.SP_CLIENT_INIT));
		WorkFlowEditor.addWidget("Start Screen", new WFEditBox().label("id").newKey("key1,int"));
		WorkFlowEditor.addWidget("Start Screen", new WFEditBox().label("name").newKey("key2,string"));
		WorkFlowEditor.addWidget("Start Screen", new WFEditBox().label("head_id").newKey("key3,int"));
		
		WorkFlowEditor.addMenuItem("Start Screen", new WFScreenMenuItem()
			.name("item1")
			.project(Cfg.projectName).mbo("Department")
			.type("Submit Workflow")
			.operation("update")
			.parametermapping("dept_id,Department_create_dept_id_paramKey")
			.parametermapping("dept_name,Department_create_dept_name_paramKey")
			.parametermapping("dept_head_id,Department_create_dept_head_id_paramKey")
		
		);
	}
}

