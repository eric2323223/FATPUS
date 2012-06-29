package testscript.Workflow.Controls;
import resources.testscript.Workflow.Controls.Ctrl_Listview_E2E_1Helper;
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
import com.sybase.automation.framework.widget.helper.TableHelper;
import com.sybase.automation.framework.widget.helper.WO;

import component.entity.EE;
import component.entity.GlobalConfig;
import component.entity.MainMenu;
import component.entity.PropertiesView;
import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.WorkFlowEditor;
import component.entity.model.DeployOption;
import component.entity.model.Email;
import component.entity.model.ScrapbookCP;
import component.entity.model.WFEditBox;
import component.entity.model.WFLview;
import component.entity.model.WorkFlowPackage;
import component.entity.tplan.Robot;
import component.entity.tplan.TestResult;

/**
 * Description   : Functional Test Script
 * @author ffan
 */
public class Ctrl_Listview_E2E_1 extends Ctrl_Listview_E2E_1Helper
{
	/**
	 * Script Name   : <b>Ctrl_Listview_E2E</b>
	 * Generated     : <b>Dec 20, 2011 9:43:49 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/12/20
	 * @author ffan
	 */
	public void testMain(Object[] args) 
	{
		// TODO Insert code here
		WN.useProject(Cfg.projectName);
//		EE.runSQL(new ScrapbookCP().database("sampledb")
//				.type("Sybase_ASA_12.x").name("My Sample Database"), 
//				GlobalConfig.getRFTProjectRoot()+"/testscript/Workflow/Controls/setup/create_onerecord.sql");
//		
		EE.dnd(EE.parseResourcePath("Database Connections->My Sample Database->sampledb->Tables->onerecord (dba)"), 100, 10);
		WN.deployProject(new DeployOption().startParameter(Cfg.projectName)
				.server("My Unwired Server")
				.serverConnectionMapping("My Sample Database,sampledb"));
		
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name("myWF")
				.option(WorkFlow.SP_SERVER_INIT));
		
		PropertiesView.jumpStart(new WorkFlow().mbo("Onerecord")
				.objectQuery("findAll")
				.subject("d=1")
				.subjectMatchingRule("d="));
		
		WorkFlowEditor.addScreen("Myscreen");
		WorkFlowEditor.addScreen("MyDetail");
		WorkFlowEditor.link(WorkFlow.SP_SERVER_INIT, "Myscreen");
		
		WorkFlowEditor.showPropertiesViewInSD("Myscreen");
		PropertiesView.setNewKeyBindMBOQueryRequest("key2,list,Onerecord,age,name");
		
		WorkFlowEditor.addWidget("Myscreen",new WFLview().key("key2")
				.emptyMessage("this is a empty list")
				.alternateColor("#8080ff")
				.lvDetailScreen("MyDetail")
				);
		
		addCell("0","cell line 0","key1","100");
		sleep(1);
		addCell("1","cell line 1","key2","100");
		sleep(1);
		
		WorkFlowEditor.addWidget("MyDetail",new WFEditBox().label("age:")
				.key("key1"));
		WorkFlowEditor.addWidget("MyDetail",new WFEditBox().label("name:")
				.key("key2"));
		MainMenu.saveAll();
		
		WN.createWorkFlowPackage(new WorkFlowPackage()
			.startParameter(WN.wfPath(Cfg.projectName, "myWF"))
			.unwiredServer("My Unwired Server")
			.assignToUser("dt")
			.verifyResult("Successfully deployed the workflow", true));
		WorkFlowEditor.sendNotification(Cfg.projectName, "myWF.xbw", new Email()
			.subject("d=1")
			.to("dt")
			.unwiredServer("My Unwired Server"));
		
		////1.used to Android:
//		TestResult result = Robot.run("tplan.Workflow.Controls.android.Ctrl_Listview.Script");
//		vpManual("DeviceTest", true, result.isPass()).performTest();
		
		////2.used to BB6T:passed
		TestResult resultBB = Robot.run("tplan.Workflow.Controls.BB.Ctrl_Listview_1.Script");
		vpManual("DeviceTest", true, resultBB.isPass()).performTest();
		
	}
	
	public void addCell(String order,String cellName,String cellKey,String length){
		TestObject[] boxes = DOF.getWFListViewFigures(DOF.getRoot());
		((GefEditPartTestObject)boxes[0]).click();
		PropertiesView.maximize();
		PropertiesView.clickTab("Cell");
		PropertiesView.clickTab("General");
		PropertiesView.clickTab("Cell");
		
		DOF.getButton(DOF.getGroup(DOF.getRoot(), "Cell Lines"), "&Add").click();
		String[] cellname = TableHelper.getColumnData(DOF.getTable(DOF.getGroup(DOF.getRoot(),"Cell Lines")), 0);
		for(int i=0;i<cellname.length;i++){
			if(cellname[i].equals(cellName))
				TableHelper.clickAtCell(DOF.getTable(DOF.getRoot()),i , 0);
		DOF.getButton(DOF.getGroup(DOF.getRoot(), "Fields for cell line "+order), "&Add").click();
		TopLevelTestObject dialog = DOF.getDialog("Listview Field");
		
		DOF.getCCombo(dialog, "Key:").click();
		DOF.getCCombo(dialog, "Key:").setProperty("text", cellKey);
		
		WO.setTextField(dialog, DOF.getTextField(dialog, "Field width:"), length);
		DOF.getButton(dialog,"OK").click();
		
		MainMenu.saveAll();
		PropertiesView.restore();
		}
	}
}




