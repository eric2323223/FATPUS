package testscript.Workflow.Keys;
import resources.testscript.Workflow.Keys.PKMapping_list_ToolingHelper;
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
import com.sybase.automation.framework.widget.helper.WO;

import component.entity.EE;
import component.entity.MBOProperties;
import component.entity.PropertiesView;
import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.WorkFlowEditor;
import component.entity.model.LoadArgument;
import component.entity.model.PK;
import component.entity.model.WFEditBox;
import component.entity.model.WFScreenMenuItem;
import component.view.Problems;

/**
 * Description   : Functional Test Script
 * @author ffan
 */
public class PKMapping_list_Tooling extends PKMapping_list_ToolingHelper
{
	/**
	 * Script Name   : <b>PKMapping_list_Tooling</b>
	 * Generated     : <b>Oct 11, 2011 6:35:27 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/10/11
	 * @author ffan
	 */
	public void testMain(Object[] args) 
	{
		// TODO Insert code here
		WN.useProject(Cfg.projectName);
		EE.dnd("Database Connections->My Sample Database->sampledb->Tables->department (dba)");
		WN.createPK(new PK().startParameter(Cfg.projectName)
				.name("PersonalizationKey1")
				.type("STRING(20)")
				.storage("Transient")
				.nullable("false")
				.protect("false"));
		WN.createPK(new PK().startParameter(Cfg.projectName)
				.name("PersonalizationKey2")
				.type("STRING(20)")
				.storage("Transient")
				.nullable("false")
				.protect("false"));
		MBOProperties mbo = new MBOProperties(Cfg.projectName,"Department");
		mbo.setSQLQuery("SELECT dept_id,dept_name,dept_head_id FROM sampledb.dba.department where dept_name=:dept_name");
		mbo.setLoadArgument(new LoadArgument().name("dept_name").pk("PersonalizationKey1"));
		
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name("myWF")
				.option(WorkFlow.SP_CLIENT_INIT));
		
		WorkFlowEditor.dragMbo(Cfg.projectName, "Department");
		WorkFlowEditor.addWidget("Start",new WFEditBox().label("dept_name:").newKey("dept_name,string"));
		WorkFlowEditor.addMenuItem("Start", new WFScreenMenuItem().name("findAll")
				.type("Online Request")
				.project(Cfg.projectName)
				.mbo("Department")
				.objectQuery("findAll")
				.defaultSuccessScreen("Department"));
		PropertiesView.clickTab("Personalization Key Mappings");
		DOF.getButton(DOF.getRoot(), "&Add").click();
		TopLevelTestObject dialog = DOF.getDialog("Personalization Key Mapping");
		DOF.getCCombo(dialog, "Personalization key").click();
		System.out.println(PropertiesView.verifykeylistcom());
		vpManual("keylistitem","PersonalizationKey1",PropertiesView.verifykeylistcom()).performTest();
		
		WO.setCCombo(DOF.getCCombo(dialog, "Personalization key"), "PersonalizationKey1");
		WO.setCCombo(DOF.getCCombo(dialog, "Key"), "dept_name");
		sleep(0.5);
		DOF.getButton(dialog, "OK").click();
		vpManual("error",0,Problems.getErrors().size()).performTest();
		
		
		
		
//		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
//				.name("myWF_server")
//				.option(WorkFlow.SP_SERVER_INIT)
//				.mbo("Department")
//				.objectQuery("findAll")
//				.subject("findall")
//				.subjectMatchingRule("findall"));
//		
//		vpManual("properties","PersonalizationKey,PersonalizationKey:PersonalizationKey2,PersonalizationKey2",PropertiesView.getPKMappingofStartPoint().getKey()).performTest();
//		PropertiesView.deletePkMappingOfStartPoint("PersonalizationKey");
//		vpManual("properties","PersonalizationKey2,PersonalizationKey2",PropertiesView.getPKMappingofStartPoint().getKey()).performTest();
//		PropertiesView.addPkMappingOfStartPoint("PersonalizationKey,Department");
//		vpManual("properties","PersonalizationKey2,PersonalizationKey2:PersonalizationKey,Department",PropertiesView.getPKMappingofStartPoint().getKey()).performTest();
	}
}
//passed
