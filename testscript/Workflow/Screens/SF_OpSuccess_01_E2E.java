package testscript.Workflow.Screens;
import java.util.ArrayList;

import resources.testscript.Workflow.Screens.SF_OpSuccess_01_E2EHelper;
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
import com.sybase.automation.framework.common.CDBUtil;

import component.entity.EE;
import component.entity.MainMenu;
import component.entity.WFFlowDesigner;
import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.WorkFlowEditor;
import component.entity.WFCustomizer.CallBackMethod;
import component.entity.WFCustomizer.WFClientResult;
import component.entity.WFCustomizer.WFCustomizer;
import component.entity.customJsGenerator.CustomJsTestScript;
import component.entity.model.DeployOption;
import component.entity.model.DeployedMbo;
import component.entity.model.Email;
import component.entity.model.StartPoint;
import component.entity.model.WFEditBox;
import component.entity.model.WFScreenMenuItem;
import component.entity.model.WorkFlowPackage;
import component.view.Problems;

/**
 * Description   : Functional Test Script
 * @author eric
 */
public class SF_OpSuccess_01_E2E extends SF_OpSuccess_01_E2EHelper
{
	/**
	 * Script Name   : <b>SF_OpSuccess_01_E2E_Android</b>
	 * Generated     : <b>Sep 19, 2011 11:05:44 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/09/19
	 * @author FANFEI
	 */
	public void testMain(Object[] args) 
	{
		// TODO Insert code here
		WN.useProject(Cfg.projectName);
		EE.dnd("Database Connections->My Sample Database->sampledb->Tables->wf_ff_a (dba)");
		WN.deployProject(new DeployOption()
 		.startParameter(Cfg.projectName)
 		.server("My Unwired Server")
 		.mode(DeployOption.MODE_REPLACE)
 		.serverConnectionMapping("My Sample Database,sampledb"));
	
		
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name("myWF")
				.option(WorkFlow.SP_CLIENT_INIT));
		
		WorkFlowEditor.addScreen("scr1");
		WorkFlowEditor.addScreen("scr2");
		WorkFlowEditor.addMenuItem("Start",new WFScreenMenuItem().name("Open scr1").type("Open").screen("scr1"));
		
		WorkFlowEditor.addWidget("scr1", new WFEditBox().label("aid:")
				.newKey("aid,int"));
		WorkFlowEditor.addWidget("scr1", new WFEditBox().label("aname:")
				.newKey("aname,string"));
		
		WorkFlowEditor.addMenuItem("scr1", new WFScreenMenuItem()
				.name("create")
				.type("Online Request")
				.project(Cfg.projectName)
				.mbo("Wf_ff_a")
				.operation("create")
				.defaultSuccessScreen("scr2")
				.parametermapping("aid,aid")
				.parametermapping("aname,aname")
				);
		
		WorkFlowEditor.addWidget("scr2", new WFEditBox().label("aid:")
				.key("aid")
				.ifReadonly(true));
		WorkFlowEditor.addWidget("scr2", new WFEditBox().label("aname:")
				.key("aname")
				.ifReadonly(true));
		
		MainMenu.saveAll();
		vpManual("error",0,Problems.getErrors().size()).performTest();
		
		WFCustomizer.runTest(new WorkFlowPackage()
			.startParameter(WN.filePath(Cfg.projectName, "myWF"))
			.unwiredServer("My Unwired Server")
			.deployToServer("true")
			.assignToSelectedUser(Cfg.deviceUser),
			customTestScript()
//			, 
//			"tplan.Workflow.iconcommon.BB.myWF_icon.Script"
			);
		
		WFCustomizer.verifyResult(new WFClientResult().data(
				"id=aid,value=4|"+
				"id=aname,value=Afour"));
		
		//vp2:verify the new record  has added into backend db 
		java.util.List<String> clause = new ArrayList<String>();
		clause.add("aid=4");
		clause.add("aname='Afour'");
		vpManual("dbresult",1,CDBUtil.getRecordCount("localhost", Cfg.projectName, "Wf_ff_a", clause)).performTest();
		
	}

	private CustomJsTestScript customTestScript() {
		CustomJsTestScript s = new CustomJsTestScript();
		s.screen("Start").moveTo("scr1").throughMenuItem("Open scr1");
		s.screen("scr1").setData("aid","4");
		s.screen("scr1").setData("aname","Afour");
		s.screen("scr1").moveTo("scr2").throughMenuItem("create");
		s.screen("scr2").getData("aid");
		s.screen("scr2").getData("aname");
		return s;
	}
}
//passed BB6T 20120208