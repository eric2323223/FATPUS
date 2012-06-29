package testscript.Workflow.Actions;
import java.util.ArrayList;

import resources.testscript.Workflow.Actions.IsManyNQ_Listview_E2E_Android_01Helper;
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
import com.sybase.automation.framework.widget.DOF;
import com.sybase.automation.framework.widget.helper.TableHelper;

import component.entity.EE;
import component.entity.MainMenu;
import component.entity.PropertiesView;
import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.WorkFlowEditor;
import component.entity.WFCustomizer.CallBackMethod;
import component.entity.WFCustomizer.WFClientResult;
import component.entity.WFCustomizer.WFCustomizer;
import component.entity.customJsGenerator.CustomJsTestScript;
import component.entity.model.DeployOption;
import component.entity.model.Email;
import component.entity.model.StartPoint;
import component.entity.model.WFLview;
import component.entity.model.WFScreenMenuItem;
import component.entity.model.WorkFlowPackage;
import component.view.Problems;

/**
 * Description   : Functional Test Script
 * @author flvxp
 */
public class IsManyNQ_Listview_E2E_Android_01 extends IsManyNQ_Listview_E2E_Android_01Helper
{
	/**
	 * Script Name   : <b>IsManyNQ_Listview_E2E_Android_01</b>
	 * Generated     : <b>Oct 25, 2011 3:24:08 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/10/25
	 * @author flvxp
	 */
	public void testMain(Object[] args) 
	{
		WN.useProject(Cfg.projectName);
		EE.dnd("Database Connections->My Sample Database->sampledb->Tables->department (dba)");
		WN.deployProject(new DeployOption().startParameter(Cfg.projectName)
				.server("My Unwired Server")
				.serverConnectionMapping("My Sample Database,sampledb"));
//		//wf
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name("myWF")
				.option(WorkFlow.SP_SERVER_INIT));
		WorkFlowEditor.addScreen("scr1");
		//Jump Start
		PropertiesView.jumpStart(new WorkFlow()
				.from("supAdmin")
				.to("supAdmin")
				.cc("supAdmin")
				.subject("all")
				.subjectMatchingRule("all")
				.mbo("Department")
				.objectQuery("findAll")
				.received("2011-10-21")
				.body("<name>[name]</name><value>{value}</value>")
				.verifySubject("Subject,all", true));
		WorkFlowEditor.link("Server-initiated", "scr1");
		//
		WN.openWorkFlow(Cfg.projectName, "myWF.xbw");
		DOF.getCTabItem(DOF.getRoot(), "Properties").click();
		sleep(1);
		DOF.getCTabItem(DOF.getRoot(), "Flow Design").click();
		DOF.getWFScreenFigure(DOF.getRoot(), "scr1").doubleClick();
		sleep(0.5);
		PropertiesView.setNewKeyBindMBOQueryRequest("Department,list,Department,dept_id,dept_name,dept_head_id");
		//
		WorkFlowEditor.addWidget(Cfg.projectName, "myWF.xbw", "scr1", new WFLview()
			.key("Department"));
//		add Cells
		String key = "key1,key2,key3";
		String[] keys = key.split(",");
		sleep(0.5);
		for (int i = 0; i < keys.length; i++){
			PropertiesView.clickTab("Cell");
			sleep(0.5);
			DOF.getButton(DOF.getGroup(DOF.getRoot(), "Cell Lines"), "&Add").click();
//			sleep(0.5);
			TableHelper.clickAtCell(DOF.getTable(DOF.getRoot()), i, 0);
			sleep(0.5);
			DOF.getButton(DOF.getGroup(DOF.getRoot(), "Fields for cell line " + String.valueOf(i)), "&Add").click();
			TopLevelTestObject dialog = DOF.getDialog("Listview Field");
			DOF.getCCombo(dialog, "Key:").click();
			RationalTestScript.sleep(0.5);
			DOF.getPoppedUpList().click(SubitemFactory.atText(keys[i]));
			DOF.getButton(dialog, "OK").click();
		}
		MainMenu.saveAll();
		//
		vpManual("error", 0, Problems.getErrors().size()).performTest();
		//
		WFCustomizer.runTest(new WorkFlowPackage()
			.startParameter(WN.filePath(Cfg.projectName, Cfg.wfName))
			.assignToUser("supAdmin")
			.unwiredServer("My Unwired Server")
			.deployToServer("true").verifyResult("Assigning workflow myWF to supAdmin", true),
			customTestScript(), 
			"tplan.Workflow.common.StartWF_android", 
			new CallBackMethod().receiver(WorkFlowEditor.class)
				.methodName("sendNotification")
				.parameter(new Email()
					.unwiredServer("My Unwired Server")
					.to("supAdmin")
					.from("RFT")
					.subject("all")
					.body("bodybody")));
		WFCustomizer.verifyResult(new WFClientResult().data("found=true"));
	}
	
	private CustomJsTestScript customTestScript() {
		CustomJsTestScript s = new CustomJsTestScript();
		s.screen("scr1").checkListItem("200", "0");
		return s;
	}
}

