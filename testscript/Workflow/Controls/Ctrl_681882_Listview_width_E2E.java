package testscript.Workflow.Controls;
import resources.testscript.Workflow.Controls.Ctrl_681882_Listview_width_E2EHelper;
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
import component.entity.MainMenu;
import component.entity.PropertiesView;
import component.entity.WFFlowDesigner;
import component.entity.WFScreenDesigner;
import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.WorkFlowEditor;
import component.entity.model.DeployOption;
import component.entity.model.Email;
import component.entity.model.StartPoint;
import component.entity.model.WFLview;
import component.entity.model.WorkFlowPackage;
import component.entity.tplan.Robot;
import component.entity.tplan.TestResult;

/**
 * Description   : Functional Test Script
 * @author ffan
 */
public class Ctrl_681882_Listview_width_E2E extends Ctrl_681882_Listview_width_E2EHelper
{
	/**
	 * Script Name   : <b>Ctrl_681882_Listview_width_E2E</b>
	 * Generated     : <b>Dec 13, 2011 1:44:25 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/12/13
	 * @author ffan
	 */
	public void testMain(Object[] args) 
	{
		// TODO Insert code here
		WN.useProject(Cfg.projectName);
		EE.dnd("Database Connections->My Sample Database->sampledb->Tables->department (dba)");
		WN.deployProject(new DeployOption().startParameter(Cfg.projectName)
				.server("My Unwired Server")
				.serverConnectionMapping("My Sample Database,sampledb"));

		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name("myWF")
				.option(WorkFlow.SP_SERVER_INIT)
				.mbo("Department")
				.objectQuery("findAll")
				.subject("dept=1")
				.subjectMatchingRule("dept=")
				);
		
		DOF.getCTabItem(DOF.getRoot(), "Flow Design").click();
		DOF.getWFScreenFigure(DOF.getRoot(), "Department").doubleClick();
		TestObject[] boxes = DOF.getWFListViewFigures(DOF.getRoot());
		((GefEditPartTestObject)boxes[0]).click();
		PropertiesView.maximize();
		PropertiesView.clickTab("Cell");
		
		setCell("0","cell line 0","Department_dept_id_attribKey","0");
		MainMenu.saveAll();
		sleep(1);
		setCell("1","cell line 1","Department_dept_name_attribKey","0");
		MainMenu.saveAll();
		sleep(1);
		setCell("2","cell line 2","Department_dept_head_id_attribKey","0");
		MainMenu.saveAll();
		sleep(1);
		
		MainMenu.saveAll();
		PropertiesView.restore();
		
		WN.createWorkFlowPackage(new WorkFlowPackage()
			.startParameter(WN.wfPath(Cfg.projectName, "myWF"))
			.unwiredServer("My Unwired Server")
			.assignToUser("dt")
			.verifyResult("Successfully deployed the workflow", true));
		WorkFlowEditor.sendNotification(Cfg.projectName, "myWF.xbw", new Email()
			.subject("dept=1")
			.to("dt")
			.unwiredServer("My Unwired Server"));

		
		////1.used to Android:
		TestResult result = Robot.run("tplan.Workflow.Controls.android.Ctrl_681882_Listview_width.Script");
		vpManual("DeviceTest", true, result.isPass()).performTest();
		
		//2.used to BB6: passed
		TestResult resultBB = Robot.run("tplan.Workflow.Controls.BB.Ctrl_681882_Listview_width.Script");
		vpManual("DeviceTest", true, resultBB.isPass()).performTest();
		
	}
	
	public void setCell(String order,String cellName,String cellKey,String length){
		String[] cellname = TableHelper.getColumnData(DOF.getTable(DOF.getGroup(DOF.getRoot(),"Cell Lines")), 0);
		for(int i=0;i<cellname.length;i++){
			if(cellname[i].equals(cellName))
				TableHelper.clickAtCell(DOF.getTable(DOF.getRoot()),i , 0);
		}
		String[] name = TableHelper.getColumnData(DOF.getTable(DOF.getGroup(DOF.getRoot(),"Fields for cell line "+order)), "Key");
		int row = TableHelper.getRowIndexOfRecordInColumn(DOF.getTable(DOF.getGroup(DOF.getRoot(),"Fields for cell line "+order)), "Key",cellKey);
		
		DOF.getTable(DOF.getGroup(DOF.getRoot(),"Fields for cell line "+order)).click(atCell(atRow(row), atColumn("Key")));
		DOF.getButton(DOF.getGroup(DOF.getRoot(), "Fields for cell line "+order), "&Edit...").click();
		
		TopLevelTestObject dialog = DOF.getDialog("Listview Field");
		WO.setTextField(dialog, DOF.getTextField(dialog, "Field width:"), length);
		DOF.getButton(dialog,"OK").click();
	}
}

