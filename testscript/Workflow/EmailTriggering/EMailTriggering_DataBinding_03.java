package testscript.Workflow.EmailTriggering;
import resources.testscript.Workflow.EmailTriggering.EMailTriggering_DataBinding_03Helper;
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
import component.entity.PropertiesView;
import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.WorkFlowEditor;
import component.entity.model.ObjectQuery;
import component.entity.model.StartPoint;
import component.entity.model.WizardRunner;

/**
 * Description   : Functional Test Script
 * @author ffan
 */
public class EMailTriggering_DataBinding_03 extends EMailTriggering_DataBinding_03Helper
{
	/**
	 * Script Name   : <b>EMailTriggering_DataBinding_03</b>
	 * Generated     : <b>Oct 17, 2011 3:54:57 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/10/17
	 * @author ffan
	 */
	public void testMain(Object[] args) 
	{
		// TODO Insert code here

		WN.useProject("wf_2");
		EE.dnd("Database Connections->My Sample Database->sampledb->Tables->employee (dba)");

		WN.useProject(Cfg.projectName);
		EE.dnd("Database Connections->My Sample Database->sampledb->Tables->employee (dba)");	
		WN.useProject(Cfg.projectName);
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name("myWF"));
		
		WorkFlowEditor.addStartingPoint(new StartPoint()
			.type(WorkFlow.SP_SERVER_INIT)
			.name("Employee")
			.project("wf_2")
			.mbo("Employee")
			.objectQuery("findAll"));
		
		String type1 = PropertiesView.getTypeOfNewKeyBindMBOAttribute("key1,string,Employee,emp_id");
		vpManual("type","int",type1).performTest();
		
		String type2 = PropertiesView.getTypeOfNewKeyBindMBOAttribute("key1,string,Employee,birth_date");
		vpManual("type","DateTime",type2).performTest();
		
		String type3 = PropertiesView.getTypeOfNewKeyBindMBOAttribute("key1,string,Employee,sex");
		vpManual("type","string",type3).performTest();
		
		PropertiesView.setKeyOfServerInitial("key1,string,Employee,sex");
		vpManual("key","key1,string,Employee/sex",PropertiesView.getStartPoint(WorkFlow.SP_SERVER_INIT).getKey()).performTest();
		
		MainMenu.saveAll();
		WN.closeAll();
		WN.openWorkFlow(Cfg.projectName,"myWF.xbw");
		DOF.getCTabItem(DOF.getRoot(), "Flow Design").click();
		DOF.getWFServerInitiateFlowStartingPointFigure().click();
		PropertiesView.clickTab("Keys");
		
		vpManual("key","key1,string,Employee/sex",PropertiesView.getStartPoint(WorkFlow.SP_SERVER_INIT).getKey()).performTest();
		WN.deleteProject("wf_2");
		MainMenu.saveAll();
		
	}
}
//passed
