package testscript.Workflow.Actions;
import resources.testscript.Workflow.Actions.ACT_Submit_5Helper;
import testscript.Workflow.cfg.Cfg;

import component.entity.EE;
import component.entity.MBOProperties;
import component.entity.PropertiesView;
import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.WorkFlowEditor;
import component.entity.model.Operation;
import component.entity.model.OperationParameter;
import component.entity.model.PK;
import component.entity.model.WFEditBox;
import component.entity.model.WFScreenMenuItem;
import component.view.Problems;

/**
 * Description   : Functional Test Script
 * @author flvVm
 */
public class ACT_Submit_5 extends ACT_Submit_5Helper
{
	/**
	 * Script Name   : <b>ACT_Submit_5</b>
	 * Generated     : <b>Sep 27, 2011 3:01:15 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/09/27
	 * @author flvVm
	 */
	public void testMain(Object[] args) 
	{
		WN.useProject(Cfg.projectName);
		// MBO
		EE.dnd("Database Connections->My Sample Database->sampledb->Tables->department (dba)");
		WN.createPK(new PK().startParameter(Cfg.projectName)
				.name("pk1")
				.type("STRING")
				.nullable("true")
				.storage("Transient")
				.defaultValue("default"));
		MBOProperties mbo = new MBOProperties(Cfg.projectName, "Department");
		mbo.setOperation(new Operation().startParameter(WN.mboPath(Cfg.projectName, "Department"))
				.name("create")
				.parameter(new OperationParameter().pk("pk1").name("dept_name").toString())
				);
		// WF
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name("myWF")
				.option(WorkFlow.SP_CLIENT_INIT));
		WorkFlowEditor.addEditBox(Cfg.projectName, "myWF.xbw", "Start Screen", 
				new WFEditBox().label("dept_id")
							   .logicalType("NUMERIC")
							   .newKey("key1,int")
		);
		WorkFlowEditor.addEditBox(Cfg.projectName, "myWF.xbw", "Start Screen", 
				new WFEditBox().label("dept_name")
							   .logicalType("TEXT")
							   .newKey("key2,string")
		);
		WorkFlowEditor.addEditBox(Cfg.projectName, "myWF.xbw", "Start Screen", 
				new WFEditBox().label("dept_head_id")
							   .logicalType("NUMERIC")
							   .newKey("key3,int")
		);
		WorkFlowEditor.addEditBox(Cfg.projectName, "myWF.xbw", "Start Screen", 
				new WFEditBox().label("pk")
							   .logicalType("TEXT")
							   .newKey("key4,string")
		);
		
		//String[] parameter={"dept_id,key1", "dept_name,key2", "dept_head_id,key3"};
		WorkFlowEditor.addMenuItem("Start Screen", new WFScreenMenuItem()
				.name("Create")
				.type("Submit Workflow")
				.project(Cfg.projectName)
				.mbo("Department")
				.operation("create")
				.submitConfirmMsg("submit confirmation message!")
				.resubmitConfirmMsg("resubmit confirmation message!")
				.parametermapping("dept_id,key1")
				.parametermapping("dept_name,key2")
				.parametermapping("dept_head_id,key3")
				.pkMapping("pk1,key4")
		);
		
		vpManual("exist", true, WorkFlowEditor.hasMenuItemInScreen("Start Screen", "Create")).performTest();
		vpManual("properties", "dept_id,key1", PropertiesView.getMenuItem("Create").getParameterMapping()[0]).performTest();
		vpManual("properties", "dept_name,key2", PropertiesView.getMenuItem("Create").getParameterMapping()[1]).performTest();
		vpManual("properties", "dept_head_id,key3", PropertiesView.getMenuItem("Create").getParameterMapping()[2]).performTest();
		vpManual("properties", "pk1,key4", (PropertiesView.getMenuItem("Create").getPkMapping().split(":"))[0]).performTest();

		vpManual("error", 0, Problems.getErrors().size()).performTest();

	}
}

