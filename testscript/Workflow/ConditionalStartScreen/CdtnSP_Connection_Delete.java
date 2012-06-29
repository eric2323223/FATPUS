package testscript.Workflow.ConditionalStartScreen;
import resources.testscript.Workflow.ConditionalStartScreen.CdtnSP_Connection_DeleteHelper;
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

/**
 * Description   : Functional Test Script
 * @author mzhao
 */
public class CdtnSP_Connection_Delete extends CdtnSP_Connection_DeleteHelper
{
	/**
	 * Script Name   : <b>CdtnSP_Connection_Delete</b>
	 * Generated     : <b>Mar 22, 2012 12:09:15 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2012/03/22
	 * @author mzhao
	 */
	public void testMain(Object[] args) 
	{
		// TODO Insert code here
		WN.useProject(Cfg.projectName);
		EE.dnd("Database Connections->My Sample Database->sampledb->Tables->AllMBODataType (dba)");
//		
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name("myWF")
				.option(WorkFlow.SP_SERVER_INIT)
				.project(Cfg.projectName)
				.mbo("AllMBODataType")
				.objectQuery("findByPrimaryKey")
		        .subject("id=1")
				.subjectMatchingRule("id=")
				.setParameterValue("id,Subject,id=,,"));				
				MainMenu.saveAll();
//		
		DOF.getCTabItem(DOF.getRoot(),"Flow Design").click();	
	    DOF.getWFServerInitiateFlowStartingPointFigure().click();
		PropertiesView.clickTab("Start Screen(s)");	
		
		// add condition Screen
        PropertiesView.addConditionSPNewTableOfMenuItem("MYY");
        
        // delete condition Screen using delete button
        PropertiesView.deleteConditionSPNewTableOfMenuItem("MYY");
        System.out.println(PropertiesView.getConditionSPNewTableOfMenuItem().getKey());
		vpManual("NULLScreen","null",PropertiesView.getConditionSPNewTableOfMenuItem().getKey()).performTest();
		
		//verify the delete Conditionalgotonavigation was deleted.
		String link = WorkFlowEditor.getLinkTypeBetween(WorkFlow.SP_SERVER_INIT,"Screen");
		System.out.print("ffffff"+link);
		vpManual("noline","",link).performTest();
		
		
	}
}

