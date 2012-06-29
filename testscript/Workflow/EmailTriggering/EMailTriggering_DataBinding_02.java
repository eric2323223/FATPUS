package testscript.Workflow.EmailTriggering;
import resources.testscript.Workflow.EmailTriggering.EMailTriggering_DataBinding_02Helper;
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
import component.entity.model.DeployOption;
import component.entity.model.Relationship;
import component.view.Problems;

/**
 * Description   : Functional Test Script
 * @author ffan
 */
public class EMailTriggering_DataBinding_02 extends EMailTriggering_DataBinding_02Helper
{
	/**
	 * Script Name   : <b>EMailTriggering_DataBinding_02</b>
	 * Generated     : <b>Oct 18, 2011 3:25:44 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/10/18
	 * @author ffan
	 */
	public void testMain(Object[] args) 
	{
		// TODO Insert code here
		WN.useProject(Cfg.projectName);
		EE.dnd("Database Connections->My Sample Database->sampledb->Tables->department (dba)");
		EE.dnd("Database Connections->My Sample Database->sampledb->Tables->employee (dba)");
		WN.createRelationship(new Relationship()
			.startParameter(WN.mboPath(Cfg.projectName, "Employee"))
			.name("employees")
			.target("Department")
			.mapping("dept_id,dept_id")
			.composite("true")
			.type(Relationship.TYPE_OTM));

		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name("myWF_databinding")
				.option(WorkFlow.SP_SERVER_INIT)
				.mbo("Employee")
				.objectQuery("findAll")
				.subject("findall")
				.subjectMatchingRule("findall"));
		
		PropertiesView.setNewKeyBindMBORelationship("Flow Design,key62,list,Employee,employees,dept_id");
		
		Boolean hasdata1 = PropertiesView.getKeyAttributesofFlowDesign().getKey().toString().contains("key62,list,Employee/employees");
		System.out.println("data1="+hasdata1);	
		vpManual("key",true,hasdata1).performTest();
		
		Boolean hasdata2 = PropertiesView.getKeyAttributesofFlowDesign().getKey().toString().contains("key1,int,Department/dept_id");
		System.out.println("data2="+hasdata2);	
		vpManual("key",true,hasdata2).performTest();
		
		MainMenu.saveAll();
		WN.closeAll();
		WN.openWorkFlow(Cfg.projectName,"myWF_databinding.xbw");
		DOF.getCTabItem(DOF.getRoot(), "Flow Design").click();
		DOF.getWFServerInitiateFlowStartingPointFigure().click();
		PropertiesView.clickTab("Keys");
		
		vpManual("key",true,hasdata1).performTest();
		vpManual("key",true,hasdata2).performTest();
		
	}
}
//passed
