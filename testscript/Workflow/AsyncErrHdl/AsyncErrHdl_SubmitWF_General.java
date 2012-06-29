package testscript.Workflow.AsyncErrHdl;
import resources.testscript.Workflow.AsyncErrHdl.AsyncErrHdl_SubmitWF_GeneralHelper;
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

import component.entity.MainMenu;
import component.entity.PropertiesView;
import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.WorkFlowEditor;
import component.entity.model.WFScreenMenuItem;

/**
 * Description   : Functional Test Script
 * @author eric
 */
public class AsyncErrHdl_SubmitWF_General extends AsyncErrHdl_SubmitWF_GeneralHelper
{
	/**
	 * Script Name   : <b>AsyncErrHdl_SubmitWF_General</b>
	 * Generated     : <b>Mar 13, 2012 2:18:07 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2012/03/13
	 * @author eric
	 */
	public void testMain(Object[] args) 
	{
		WN.useProject(Cfg.projectName);
		WN.createWorkFlow(new WorkFlow().name(Cfg.wfName)
				.startParameter(Cfg.projectName)
				.option(WorkFlow.SP_CLIENT_INIT));
		WorkFlowEditor.addMenuItem("Start", new WFScreenMenuItem().name("item1")
				.type("Submit Workflow")
				.errorScreen("Start")
				.subject("subject")
				.from("unkown"));
		vpManual("name", 0, PropertiesView.getMenuItem("Start","item1").getOutputKey().length).performTest();
		try{
			PropertiesView.set(new WFScreenMenuItem().name("item1")
				.type("Online Request")
				.subject("subject"));
			logError("subject should not be avalible for Online Request menu item");
		}catch(Exception e){
			vpManual("exception", "java.lang.NullPointerException", e.getClass().getName()).performTest();
			MainMenu.resetPerspective();
		}
	}
}

