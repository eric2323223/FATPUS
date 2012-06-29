package testscript.Workflow.Actions;
import resources.testscript.Workflow.Actions.ACT_Submit_1Helper;
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
import component.entity.model.DeployOption;
import component.entity.model.Email;
import component.entity.model.WFEditBox;
import component.entity.model.WFScreenMenuItem;
import component.entity.model.WorkFlowPackage;

/**
 * Description   : Functional Test Script
 * @author eric
 */
public class ACT_Submit_1 extends ACT_Submit_1Helper
{
	/**
	 * Script Name   : <b>ACT_Submit_1</b>
	 * Generated     : <b>Sep 20, 2011 2:39:37 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/09/20
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
				.key("item1")
				.project(Cfg.projectName)
				.mbo("Department")
				.type("Submit Workflow")
				.operation("update")
				.parametermapping("dept_id,key1")
				.parametermapping("dept_name,key2")
				.parametermapping("dept_head_id,key3")
				);
		vpManual("properties", "dept_name,key2", PropertiesView.getMenuItem("item1").getParameterMapping()[0]).performTest();
		vpManual("properties", "dept_head_id,key3", PropertiesView.getMenuItem("item1").getParameterMapping()[1]).performTest();
		vpManual("properties", "dept_id,key1", PropertiesView.getMenuItem("item1").getParameterMapping()[2]).performTest();

	}
}

