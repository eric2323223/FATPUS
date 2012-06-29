package testscript.Workflow.AsyncErrHdl;

import resources.testscript.Workflow.AsyncErrHdl.AsyncErrHdl_SubmitWF_ErrorScreen3Helper;
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

import component.entity.MainMenu;
import component.entity.PropertiesView;
import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.WorkFlowEditor;
import component.entity.model.WFScreenMenuItem;

/**
 * Description : Functional Test Script
 * 
 * @author flvxp
 */
public class AsyncErrHdl_SubmitWF_ErrorScreen3 extends
		AsyncErrHdl_SubmitWF_ErrorScreen3Helper {
	/**
	 * Script Name : <b>AsyncErrHdl_SubmitWF_ErrorScreen3</b> Generated : <b>Mar
	 * 20, 2012 11:14:56 PM</b> Description : Functional Test Script Original
	 * Host : WinNT Version 5.1 Build 2600 (S)
	 * 
	 * @since 2012/03/20
	 * @author flvxp
	 */
	public void testMain(Object[] args) {
		WN.useProject(Cfg.projectName);
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName).name(
				"myWF").option(WorkFlow.SP_CLIENT_INIT));
		WorkFlowEditor.addScreen("ErrorScreen");
		WorkFlowEditor.addMenuItem("Start", new WFScreenMenuItem().name("Menu")
				.type("Submit Workflow")
				.errorScreen("ErrorScreen"));
		vpManual("ESField", true, PropertiesView.getMenuItem("Start", "Menu").getErrorScreen().equals("ErrorScreen")).performTest();
		String type = WorkFlowEditor.getLinkTypeBetween("Start", "ErrorScreen");
		vpManual("ESField", true, type.equals("/AsyncErrorRequest")).performTest();
		//
		WorkFlowEditor.setMenuItem("Start", new WFScreenMenuItem().name("Menu").errorScreen(""));
		vpManual("ESField", true, PropertiesView.getMenuItem("Start", "Menu").getErrorScreen().equals("")).performTest();
		String type1 = WorkFlowEditor.getLinkTypeBetween("Start", "ErrorScreen");
		vpManual("ESField", true, type1.equals("")).performTest();
	}
}
