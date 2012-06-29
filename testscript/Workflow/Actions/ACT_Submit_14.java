package testscript.Workflow.Actions;
import resources.testscript.Workflow.Actions.ACT_Submit_14Helper;
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
import component.entity.MBOProperties;
import component.entity.PropertiesView;
import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.WorkFlowEditor;
import component.entity.model.Operation;
import component.entity.model.OperationParameter;
import component.entity.model.PK;
import component.entity.model.StartPoint;
import component.entity.model.WFEditBox;
import component.entity.model.WFScreenMenuItem;
import component.view.Problems;

/**
 * Description   : Functional Test Script
 * @author flvVm
 */
public class ACT_Submit_14 extends ACT_Submit_14Helper
{
	/**
	 * Script Name   : <b>ACT_Submit_14</b>
	 * Generated     : <b>Sep 29, 2011 4:05:59 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/09/29
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
				.name("delete")
				.parameter(new OperationParameter().pk("pk1").name("dept_name").toString())
				);
		// WF
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name("myWF")
		);
		WorkFlowEditor.addStartingPoint(new StartPoint().type(WorkFlow.SP_CLIENT_INIT));
		WorkFlowEditor.dragOperation(Cfg.projectName, "Department", "delete");
		WorkFlowEditor.link("Client-initiated", "Department_delete");
		WorkFlowEditor.addEditBox(Cfg.projectName, "myWF.xbw", "Department_delete", 
				new WFEditBox().label("pk")
							   .logicalType("TEXT")
							   .newKey("pkvalue,string")
		);
		WorkFlowEditor.setMenuItem("Department_delete", new WFScreenMenuItem()
				.name("Delete")
				.pkMapping("pk1,pkvalue")
		);
		vpManual("properties", "pk1,pkvalue", (PropertiesView.getMenuItem("Create").getPkMapping().split(":"))[0]).performTest();
		WN.deletePK(Cfg.projectName, "pk1");
		
		vpManual("error", 1, Problems.getErrors().size()).performTest();
		
	}
}

