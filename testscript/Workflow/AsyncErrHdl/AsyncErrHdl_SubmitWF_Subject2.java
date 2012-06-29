package testscript.Workflow.AsyncErrHdl;
import resources.testscript.Workflow.AsyncErrHdl.AsyncErrHdl_SubmitWF_Subject2Helper;
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

import component.entity.MainMenu;
import component.entity.PropertiesView;
import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.WorkFlowEditor;
import component.entity.model.WFScreenMenuItem;
import component.view.Problems;

/**
 * Description   : Functional Test Script
 * @author flvxp
 */
public class AsyncErrHdl_SubmitWF_Subject2 extends AsyncErrHdl_SubmitWF_Subject2Helper
{
	/**
	 * Script Name   : <b>AsyncErrHdl_SubmitWF_Subject2</b>
	 * Generated     : <b>Mar 21, 2012 1:54:15 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2012/03/21
	 * @author flvxp
	 */
	public void testMain(Object[] args) 
	{
		WN.useProject(Cfg.projectName);
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName).name(
				"myWF").option(WorkFlow.SP_CLIENT_INIT));
		WorkFlowEditor.addScreen("ErrorScreen");
		WorkFlowEditor.addMenuItem("Start", new WFScreenMenuItem().name("Menu")
				.type("Submit Workflow")
				.errorScreen("ErrorScreen"));
		vpManual("ErrScreen", true, PropertiesView.getMenuItem("Start", "Menu").getErrorScreen().equals("ErrorScreen")).performTest();
		WorkFlowEditor.setMenuItem("Start", new WFScreenMenuItem().name("Menu")
				.subject("~!@#$%^&*()_+{}|:\"<>?[];\',./'"));
		vpManual("ESField", true, PropertiesView.getMenuItem("Start", "Menu").getSubject().equals("~!@#$%^&*()_+{}|:\"<>?[];\',./'")).performTest();
		WorkFlowEditor.setMenuItem("Start", new WFScreenMenuItem().name("Menu")
				.subject(""));
		vpManual("ESField", true, PropertiesView.getMenuItem("Start", "Menu").getSubject().equals("")).performTest();
		vpManual("error", 0, Problems.getErrors().size()).performTest();
	}
}

