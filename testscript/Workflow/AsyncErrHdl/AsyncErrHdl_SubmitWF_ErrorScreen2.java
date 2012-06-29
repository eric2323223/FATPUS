package testscript.Workflow.AsyncErrHdl;
import resources.testscript.Workflow.AsyncErrHdl.AsyncErrHdl_SubmitWF_ErrorScreen2Helper;
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
import component.entity.MainMenu;
import component.entity.PropertiesView;
import component.entity.WFScreenDesigner;
import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.WorkFlowEditor;
import component.entity.model.WFScreenMenuItem;

/**
 * Description   : Functional Test Script
 * @author flvxp
 */
public class AsyncErrHdl_SubmitWF_ErrorScreen2 extends AsyncErrHdl_SubmitWF_ErrorScreen2Helper
{
	/**
	 * Script Name   : <b>AsyncErrHdl_SubmitWF_ErrorScreen2</b>
	 * Generated     : <b>Mar 20, 2012 10:23:16 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2012/03/20
	 * @author flvxp
	 */
	public void testMain(Object[] args) 
	{
		WN.useProject(Cfg.projectName);
		EE.dnd("Database Connections->My Sample Database->sampledb->Tables->department (dba)");
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName).name(
				"myWF").option(WorkFlow.SP_CLIENT_INIT));
		WorkFlowEditor.dragOperation(Cfg.projectName, "Department", "create");
//		WorkFlowEditor.link("Start", "Departmentcreate");
		WorkFlowEditor.addMenuItem("Start", new WFScreenMenuItem().name("Open Departmentcreate")
				.type("Open")
				.screen("Departmentcreate"));
		WorkFlowEditor.setMenuItem("Departmentcreate", new WFScreenMenuItem()
				.name("Create")
				.generateErrorScreen(true));
		vpManual("ESField", true, DOF.getCCombo(DOF.getRoot(),"Error screen:").getProperty("text").equals("ErrorList")).performTest();
		MainMenu.saveAll();
		WorkFlowEditor.close(Cfg.projectName, "myWF.xbw");
		WN.openWorkFlow(Cfg.projectName, "myWF.xbw");
		WorkFlowEditor.hasLinkBetween("ErrorList", "ErrorDetail");
		vpManual("ESField", true, PropertiesView.getMenuItem("Departmentcreate", "Create").getErrorScreen().equals("ErrorList")).performTest();
		MainMenu.saveAll();
	}
}

