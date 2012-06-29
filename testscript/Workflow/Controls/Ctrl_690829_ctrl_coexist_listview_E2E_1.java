package testscript.Workflow.Controls;
import resources.testscript.Workflow.Controls.Ctrl_690829_ctrl_coexist_listview_E2E_1Helper;
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
import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.WorkFlowEditor;
import component.entity.model.DeployOption;
import component.entity.model.Email;
import component.entity.model.WFCheckbox;
import component.entity.model.WFChoice;
import component.entity.model.WFEditBox;
import component.entity.model.WFScreenMenuItem;
import component.entity.model.WFSignature;
import component.entity.model.WFSlider;
import component.entity.model.WorkFlowPackage;
import component.entity.tplan.Robot;
import component.entity.tplan.TestResult;

/**
 * Description   : Functional Test Script
 * @author ffan
 */
public class Ctrl_690829_ctrl_coexist_listview_E2E_1 extends Ctrl_690829_ctrl_coexist_listview_E2E_1Helper
{
	/**
	 * Script Name   : <b>Ctrl_690829_ctrl_coexist_listview_E2E</b>
	 * Generated     : <b>Dec 15, 2011 10:12:19 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/12/15
	 * @author ffan
	 */
	public void testMain(Object[] args) 
	{
		// TODO Insert code here
		WN.useProject(Cfg.projectName);
//		EE.runSQL(new ScrapbookCP().database("sampledb")
//				.type("Sybase_ASA_12.x").name("My Sample Database"), 
//				GlobalConfig.getRFTProjectRoot()+"/testscript/Workflow/Controls/setup/crete_table.sql");
		EE.dnd("Database Connections->My Sample Database->sampledb->Tables->T_olc (dba)");
		WN.deployProject(new DeployOption().startParameter(Cfg.projectName)
				.server("My Unwired Server")
				.serverConnectionMapping("My Sample Database,sampledb"));
		
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name("myWF")
				.option(WorkFlow.SP_CLIENT_INIT)
				);
		
		WorkFlowEditor.dragMbo(Cfg.projectName, "T_olc");
		WorkFlowEditor.addMenuItem("Start Screen", new WFScreenMenuItem().name("findAll")
				.type("Online Request")
				.project(Cfg.projectName)
				.mbo("T_olc")
				.objectQuery("findAll")
				.defaultSuccessScreen("T_olc"));
		
		WorkFlowEditor.addWidget("T_olc", new WFChoice().label("static:")
				.newKey("static,string")
				.option("Static,A,1:,B,2"));
		
		WorkFlowEditor.addWidget("T_olc", new WFChoice().label("dynamic:")
				.newKey("dynamic,string")
				.option("Dynamic,T_olc.T_olc_a_attribKey,T_olc.T_olc_a_attribKey"));
		
		WorkFlowEditor.addWidget("T_olc", new WFEditBox().label("edit:")
				.newKey("edit,string"));
	
		WorkFlowEditor.addWidget("T_olc", new WFSlider().label("slider:")
				.maxValue("20")
				.minValue("0")
				.newKey("slider,int"));
		
		WorkFlowEditor.addWidget("T_olc", new WFCheckbox().label("check:")
				.newKey("check,bool"));
	
		//VP1:listView is located to the bottom of screen
		moveDownListView();
		
		MainMenu.saveAll();
		
		WN.createWorkFlowPackage(new WorkFlowPackage()
			.startParameter(WN.wfPath(Cfg.projectName, "myWF"))
			.unwiredServer("My Unwired Server")
			.assignToUser("dt")
			.verifyResult("Successfully deployed the workflow", true));

		////1.used to Android:
//		TestResult result = Robot.run("tplan.Workflow.Controls.android.Ctrl_690829_ctrl_coexist_listview_E2E_1.Script");
//		vpManual("DeviceTest", true, result.isPass()).performTest();
		
		////2.used to BB: passed
		TestResult resultBB = Robot.run("tplan.Workflow.Controls.BB.Ctrl_690829_ctrl_coexist_listview_E2E_1.Script");
		vpManual("DeviceTest", true, resultBB.isPass()).performTest();
		
	}
	
	//move the listView to the last controls
	public void moveDownListView(){
		TestObject[] boxes = DOF.getWFListViewFigures(DOF.getRoot());
		((GefEditPartTestObject)boxes[0]).click(RIGHT);
		DOF.getContextMenu().click(atPath("Edit->Cut"));
		DOF.getWFScreenDisplayFigure().click(RIGHT);
		DOF.getContextMenu().click(atPath("Edit->Paste"));
	}
}

