package testscript.Workflow.Controls;
import resources.testscript.Workflow.Controls.Ctrl_Listview_prop_02_211Helper;
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
import com.sybase.automation.framework.widget.helper.TableHelper;

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
public class Ctrl_Listview_prop_02_211 extends Ctrl_Listview_prop_02_211Helper
{
	/**
	 * Script Name   : <b>Script1</b>
	 * Generated     : <b>Oct 26, 2011 1:14:21 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/10/26
	 * @author xjf
	 */
	public void testMain(Object[] args) 
	{
		// TODO Insert code here
		//test Ctrl_Listview_prop_02
		
		WN.useProject(Cfg.projectName);
		EE.dnd(EE.parseResourcePath("Database Connections->My Sample Database->sampledb->Tables->department (dba)"), 50, 90);
			
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name("wfmbocreate")
				.option(WorkFlow.SP_CLIENT_INIT));
		
		DOF.getCTabItem(DOF.getRoot(), "Flow Design").click(atPoint(25,10));
				
		WorkFlowEditor.addWidget(Cfg.projectName, "wfmbocreate.xbw", "Start", new WFLview());
        PropertiesView.clickTab("Cell");  

        DOF.getButton(DOF.getGroup(DOF.getRoot(), "Cell Lines"), "&Add").click();
        sleep(3);
        vpManual("ifcellexist1","cell line 0",TableHelper.getColumnData(DOF.getTable(DOF.getGroup(DOF.getRoot(), "Cell Lines")),0)[0]).performTest();
        DOF.getButton(DOF.getGroup(DOF.getRoot(), "Cell Lines"), "&Add").click();
        vpManual("ifcellexist2","cell line 1",TableHelper.getColumnData(DOF.getTable(DOF.getGroup(DOF.getRoot(), "Cell Lines")),0)[1]).performTest();
        TableHelper.clickAtCell(DOF.getTable(DOF.getRoot()), 1, 0);
        DOF.getButton(DOF.getGroup(DOF.getRoot(), "Cell Lines"), "&Delete").click();

//		deal the dialog when multiplication cross is disabled
        getScreen().getActiveWindow().inputKeys(SpecialKeys.ENTER);
		MainMenu.saveAll();
		
		//verify whether the cell delete
		vpManual("celldelsucc",1,TableHelper.getColumnData(DOF.getTable(DOF.getGroup(DOF.getRoot(), "Cell Lines")),0).length).performTest();
        
   
		//reopen
		DOF.getWFScreenDesignCanvas().click();
		sleep(1);
	    WN.closeAll();
	    WN.openWorkFlow("wf", "wfmbocreate.xbw");
		 DOF.getCTabItem(DOF.getRoot(), "Screen Design").click(atPoint(25,10));
       TestObject[] boxes = DOF.getWFListViewFigures(DOF.getRoot());
       TestObject box = boxes[0];
       ((GefEditPartTestObject)box).click();
       PropertiesView.clickTab("Cell");
       vpManual("ifcellexist3","cell line 0",TableHelper.getColumnData(DOF.getTable(DOF.getGroup(DOF.getRoot(), "Cell Lines")),0)[0]).performTest();
       
	}
}

