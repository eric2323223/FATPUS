package testscript.Workflow.Controls;
import resources.testscript.Workflow.Controls.Ctrl_Listview_prop_03_211Helper;
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
import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.WorkFlowEditor;
import component.entity.model.DeployOption;
import component.entity.model.WFLview;

/**
 * Description   : Functional Test Script
 * @author xjf
 */
public class Ctrl_Listview_prop_03_211 extends Ctrl_Listview_prop_03_211Helper
{
	/**
	 * Script Name   : <b>Script1</b>
	 * Generated     : <b>Oct 26, 2011 7:22:17 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/10/26
	 * @author xjf
	 */
	public void testMain(Object[] args) 
	{
		// TODO Insert code here.
		//test Ctrl_Listview_prop_03
		
		WN.useProject(Cfg.projectName);
		EE.dnd(EE.parseResourcePath("Database Connections->My Sample Database->sampledb->Tables->department (dba)"), 50, 90);
		
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName).name("wfmbocreate")
				.option(WorkFlow.SP_SERVER_INIT));

		PropertiesView.jumpStart(new WorkFlow()
		        .mbo("Department")
				.objectQuery("findAll")
				.subject("getall")
				.subjectMatchingRule("getall")
				);

		WorkFlowEditor.addScreen("linktoemail");
		WorkFlowEditor.link(WorkFlow.SP_SERVER_INIT, "linktoemail");
	
		//******************add key from key tab	
		DOF.getCTabItem(DOF.getRoot(), "Screen Design").click(atPoint(25,10));
		DOF.getCTabFolder(DOF.getRoot(), "Properties").click(atText("Properties"));
		PropertiesView.clickTab("Keys");
		PropertiesView.setNewKeyBindMBOQueryRequest("listkey,list,Department,dept_id,dept_name,dept_head_id");
		
		WorkFlowEditor.addWidget(Cfg.projectName, "wfmbocreate.xbw", "linktoemail", new WFLview());
		
		 PropertiesView.clickTab("General");
		 WO.setCCombo(DOF.getCCombo(DOF.getGroup(DOF.getRoot(), "Input Data Binding")),"listkey");
		 sleep(1);
		 MainMenu.saveAll();
		 
		 DOF.getCTabItem(DOF.getRoot(), "Screen Design").click(atPoint(25,10));
	   	 DOF.getWFListViewFigures(DOF.getRoot());
         PropertiesView.clickTab("Cell");
         sleep(1);
         DOF.getButton(DOF.getGroup(DOF.getRoot(), "Cell Lines"), "&Add").click();
         TableHelper.clickAtCell(DOF.getTable(DOF.getRoot()), 0, 0);
         DOF.getButton(DOF.getGroup(DOF.getRoot(), "Fields for"), "&Add").click();
         TopLevelTestObject dialog = DOF.getDialog("Listview Field");
            WO.setCCombo(DOF.getCCombo(dialog, "Key:"), "key1");
            DOF.getButton(dialog, "Italic").click();
            DOF.getButton(dialog, "Bold").click();

            DOF.getButton(dialog, "OK").click();
		
		//*********verify the new added field 
		TableHelper.clickAtCell(DOF.getTable(DOF.getGroup(DOF.getRoot(), "Fields for cell line 0")), 0, 0);    
		vpManual("width","100",TableHelper.getCellValue(DOF.getTable(DOF.getGroup(DOF.getRoot(), "Fields for cell line 0")),0,1));
		
        //**********edit the new field        
            DOF.getButton(DOF.getGroup(DOF.getRoot(), "Fields for cell line 0"), "&Edit...").click();
            TopLevelTestObject dialog1 = DOF.getDialog("Listview Field");
          WO.setCCombo(DOF.getCCombo(dialog1, "Key:"), "key2");
			WO.setTextField(dialog1, DOF.getTextField(dialog1, "Field width:"), "50");
          DOF.getButton(dialog1, "OK").click();
            vpManual("widthmodified","50",TableHelper.getCellValue(DOF.getTable(DOF.getGroup(DOF.getRoot(), "Fields for cell line 0")),0,1)).performTest();
            
            //********add another field
            DOF.getButton(DOF.getGroup(DOF.getRoot(), "Fields for cell line 0"), "&Add").click();
            TopLevelTestObject dialog2 = DOF.getDialog("Listview Field");
          WO.setCCombo(DOF.getCCombo(dialog2, "Key:"), "key3");
			WO.setTextField(dialog2, DOF.getTextField(dialog2, "Field width:"), "30");
          DOF.getButton(dialog2, "OK").click();
            vpManual("newaddedfield","30",TableHelper.getCellValue(DOF.getTable(DOF.getGroup(DOF.getRoot(), "Fields for cell line 0")),1,1)).performTest();
            
            //*********delete one field
            TableHelper.clickAtCell(DOF.getTable(DOF.getGroup(DOF.getRoot(), "Fields for cell line 0")), 1, 0);
            DOF.getButton(DOF.getGroup(DOF.getRoot(), "Fields for cell line 0"), "&Delete").click();
            vpManual("deletefield","false",TableHelper.hasDataInColumn(DOF.getTable(DOF.getGroup(DOF.getRoot(),"Fields for cell line 0")), "Key", "key3")).performTest();
            
     	}
}

