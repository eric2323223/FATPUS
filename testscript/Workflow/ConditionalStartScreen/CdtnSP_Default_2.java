package testscript.Workflow.ConditionalStartScreen;
import resources.testscript.Workflow.ConditionalStartScreen.CdtnSP_Default_2Helper;
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
import component.view.Problems;

/**
 * Description   : Functional Test Script
 * @author mzhao
 */
public class CdtnSP_Default_2 extends CdtnSP_Default_2Helper
{
	/**
	 * Script Name   : <b>CdtnSP_Default_2</b>
	 * Generated     : <b>Mar 20, 2009 1:35:50 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2009/03/20
	 * @author mzhao
	 */
	public void testMain(Object[] args) 
	{
		// TODO Insert code here
		WN.useProject(Cfg.projectName);
		EE.dnd("Database Connections->My Sample Database->sampledb->Tables->AllMBODataType (dba)");
		
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
		//		vpManual("hasError",0,Problems.getErrors().size()).performTest();
		
		DOF.getCTabItem(DOF.getRoot(),"Flow Design").click();
	    DOF.getWFServerInitiateFlowStartingPointFigure().click();
		PropertiesView.clickTab("Start Screen(s)");	
		
		DOF.getCCombo(DOF.getRoot(), "Default Screen:").click();		
		PropertiesView.verifykeylistcom();	
		System.out.println(PropertiesView.verifykeylistcom());
		vpManual("list",",AllMBODataType,AllMBODataTypeDetail,AllMBODataTypecreate,AllMBODataTypedeleteinstance,AllMBODataTypeupdateinstance",PropertiesView.verifykeylistcom())
		.performTest();
	}
}

