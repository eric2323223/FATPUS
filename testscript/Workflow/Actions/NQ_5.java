package testscript.Workflow.Actions;
import resources.testscript.Workflow.Actions.NQ_5Helper;
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
import component.entity.model.StartPoint;
import component.entity.model.WFScreenMenuItem;
import component.view.Problems;

/**
 * Description   : Functional Test Script
 * @author flvVm
 */
public class NQ_5 extends NQ_5Helper
{
	/**
	 * Script Name   : <b>NQ_5</b>
	 * Generated     : <b>Oct 10, 2011 3:22:20 PM</b>
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
		//MBOProperties mbo = new MBOProperties(Cfg.projectName, "Department");
		// WF
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name("myWF")
		);
		WorkFlowEditor.addStartingPoint(new StartPoint().type(WorkFlow.SP_CLIENT_INIT));
		WorkFlowEditor.addScreen("invokeScr");
		WorkFlowEditor.addScreen("resultScr");
		WorkFlowEditor.link("Client-initiated", "invokeScr");
		//
		WorkFlowEditor.addMenuItem("invokeScr", new WFScreenMenuItem()
				.name("ObjectQuery")
				.type("Online Request")
				.project(Cfg.projectName)
				.mbo("Department")
				.defaultSuccessScreen("resultScr")
		);
		//
		PropertiesView.clickTab("Parameter Mappings");
		PropertiesView.clickTab("General");
		//System.out.println(DOF.getButton(DOF.getRoot(), "Invoke &parent update").invoke("getEnabled"));
		Object o = DOF.getButton(DOF.getRoot(), "Invoke &parent update").invoke("getSelection");
		vpManual("exist", true, o.equals(true)).performTest();
		//
		o = DOF.getButton(DOF.getRoot(), "Invoke object &query").invoke("getSelection");
		vpManual("exist", true, o.equals(false)).performTest();
		//
		//vpManual("error", 1, Problems.getErrors().size()).performTest();
	}
}

