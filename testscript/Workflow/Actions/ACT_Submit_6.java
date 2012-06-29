package testscript.Workflow.Actions;
import resources.testscript.Workflow.Actions.ACT_Submit_6Helper;
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
import com.sybase.automation.framework.common.SpecialKeys;
import com.sybase.automation.framework.widget.DOF;

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
public class ACT_Submit_6 extends ACT_Submit_6Helper
{
	/**
	 * Script Name   : <b>ACT_Submit_6</b>
	 * Generated     : <b>Sep 28, 2011 10:13:40 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/09/28
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
		WorkFlowEditor.addScreen("SuccessScreen");
		WorkFlowEditor.addScreen("ErrorScreen");
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
		
		//
		WorkFlowEditor.addMenuItem("Start Screen", new WFScreenMenuItem()
				.name("Create")
				.type("Online Request")
				.project(Cfg.projectName)
				.mbo("Department")
				.operation("create")
				.submitErrMsg("Operation error message!")
				.errorScreen("ErrorScreen")
				.defaultSuccessScreen("SuccessScreen")
				.parametermapping("dept_id,key1")
				.parametermapping("dept_name,key2")
				.parametermapping("dept_head_id,key3")
				.pkMapping("pk1,key4")
		);//				
		
		vpManual("properties", "dept_id,key1", PropertiesView.getMenuItem("Create").getParameterMapping()[0]).performTest();
		vpManual("properties", "dept_name,key2", PropertiesView.getMenuItem("Create").getParameterMapping()[1]).performTest();
		vpManual("properties", "dept_head_id,key3", PropertiesView.getMenuItem("Create").getParameterMapping()[2]).performTest();
		vpManual("properties", "pk1,key4", (PropertiesView.getMenuItem("Create").getPkMapping().split(":"))[0]).performTest();

		//test success screen can not input character
		PropertiesView.clickTab("General");
		DOF.getCCombo(DOF.getRoot(), "Default Success Screen:").click();
		DOF.getRoot().inputKeys(SpecialKeys.CLEARALL);
		vpManual("exist", true, DOF.getCCombo(DOF.getRoot(),"Default Success Screen:").getProperty("text").equals("SuccessScreen")).performTest();

		//test error screen can not input character
		DOF.getCCombo(DOF.getRoot(), "Error screen:").click();
		DOF.getRoot().inputKeys(SpecialKeys.CLEARALL);
		vpManual("exist", true, DOF.getCCombo(DOF.getRoot(),"Error screen:").getProperty("text").equals("ErrorScreen")).performTest();
		
		vpManual("error", 0, Problems.getErrors().size()).performTest();
	}
}

