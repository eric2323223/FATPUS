package testscript.Workflow.WF_ErrorHandle;
import resources.testscript.Workflow.WF_ErrorHandle.EH_633845_Improved_ErrorMsg_4Helper;
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
import component.entity.model.StartPoint;
import component.entity.model.WFScreenMenuItem;
import component.view.Problems;

/**
 * Description   : Functional Test Script
 * @author ffan
 */
public class EH_633845_Improved_ErrorMsg_4 extends EH_633845_Improved_ErrorMsg_4Helper
{
	/**
	 * Script Name   : <b>Improved_ErrorMsg_4</b>
	 * Generated     : <b>Oct 14, 2011 3:27:10 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/10/14
	 * @author ffan
	 */
	public void testMain(Object[] args) 
	{
		// TODO Insert code here
		WN.useProject(Cfg.projectName);
		EE.dnd("Database Connections->My Sample Database->sampledb->Tables->department (dba)");
		
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name("myWF")
				.option(WorkFlow.SP_SERVER_INIT)
				.mbo("Department")
				.objectQuery("findAll")
				.subject("findall")
				.subjectMatchingRule("findall"));
		
		WorkFlowEditor.setMenuItem("Departmentdeleteinstance", new WFScreenMenuItem().name("Delete").generateErrorScreen(true));
		
		vpManual("hasScreen","true",WorkFlowEditor.hasScreen("ErrorList")).performTest();
		vpManual("hasScreen","true",WorkFlowEditor.hasScreen("ErrorDetail")).performTest();
		vpManual("hasError",0,Problems.getErrors().size()).performTest();
		
//		System.out.println("ErrorList="+getCellValue("ErrorList","cell line 0").getKey());
		vpManual("ErrorList","ErrorLogMessage,",getCellValue("ErrorList","cell line 0").getKey()).performTest();
		
//		System.out.println("ErrorDetail="+getCellValue("ErrorDetail","cell line 0").getKey());
		vpManual("ErrorDetail","name,Name:value,Value",getCellValue("ErrorDetail","cell line 0").getKey()).performTest();
	
	}
	
	public static StartPoint getCellValue(String screen,String cellName){
		DOF.getCTabItem(DOF.getRoot(), "Flow Design").click();
		DOF.getWFScreenFigure(DOF.getRoot(), screen).doubleClick();
		TestObject[] boxes = DOF.getWFListViewFigures(DOF.getRoot());
		((GefEditPartTestObject)boxes[0]).click();
		PropertiesView.maximize();
		PropertiesView.clickTab("Cell");
		String[] cellname = TableHelper.getColumnData(DOF.getTable(DOF.getGroup(DOF.getRoot(),"Cell Lines")), 0);
		StartPoint sc = new StartPoint();
		for(int i=0;i<cellname.length;i++){
			if(cellname[i].equals(cellName))
			{
				TableHelper.clickAtCell(DOF.getTable(DOF.getRoot()),i , 0);
				String[] key = TableHelper.getColumnData(DOF.getTable(DOF.getGroup(DOF.getRoot(), "Fields for cell line 0")), "Key");
				String[] type = TableHelper.getColumnData(DOF.getTable(DOF.getGroup(DOF.getRoot(), "Fields for cell line 0")), "Field Header Title");
		
				if(key.length != 0){
					for(int j=0;j<key.length;j++){
						sc.key(key[j]+","+type[j]);
					}
				}
				else
					sc.key("null");
			}
		}
		MainMenu.saveAll();
		PropertiesView.restore();
		MainMenu.saveAll();
		return sc;
	}
}
//passed