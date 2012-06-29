package testscript.Workflow.Actions;
import resources.testscript.Workflow.Actions.Other_PkgGenHelper;
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
import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.WorkFlowEditor;
import component.entity.WFCustomizer.WFCustomizer;
import component.entity.customJsGenerator.CustomJsTestScript;
import component.entity.model.Operation;
import component.entity.model.WorkFlowPackage;
import component.view.Problems;

/**
 * Description   : Functional Test Script
 * @author flvVm
 */
public class Other_PkgGen extends Other_PkgGenHelper
{
	/**
	 * Script Name   : <b>Other_PkgGen</b>
	 * Generated     : <b>Oct 10, 2011 12:55:35 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/10/10
	 * @author flvVm
	 */
	public void testMain(Object[] args) 
	{
		WN.useProject(Cfg.projectName);
		// MBO
		EE.dnd("Database Connections->My Sample Database->sampledb->Tables->department (dba)");
		MBOProperties mbo = new MBOProperties(Cfg.projectName, "Department");
		mbo.addOperation(new Operation().startParameter(WN.mboPath(Cfg.projectName, "Department"))
				.name("O_HParas")
				.sqlQuery("DELETE FROM sampledb.dba.department WHERE dept_id = :dept_id")
		);
		mbo.addOperation(new Operation().startParameter(WN.mboPath(Cfg.projectName, "Department"))
				.name("O_NParas")
				.sqlQuery("DELETE FROM sampledb.dba.department WHERE dept_id = 100")
		);
		// WF
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name("myWF")
				.option(WorkFlow.SP_CLIENT_INIT)
		);
		WorkFlowEditor.dragOperation(Cfg.projectName, "Department", "O_HParas");
		WorkFlowEditor.dragOperation(Cfg.projectName, "Department", "O_NParas");
		WorkFlowEditor.link("Start Screen", "Department_O_HParas");
		WorkFlowEditor.link("Start Screen", "Department_O_NParas");
		//
		WN.createWorkFlowPackage(new WorkFlowPackage()
				.startParameter(WN.filePath(Cfg.projectName, "myWF"))
				.unwiredServer("My Unwired Server")
				.deployToServer("false")
				.verifyResult("Code generation complete", true));
		//
		vpManual("error", 0, Problems.getErrors().size()).performTest();
	}
}

