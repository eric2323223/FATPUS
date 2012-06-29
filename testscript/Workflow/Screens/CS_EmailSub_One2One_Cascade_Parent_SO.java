package testscript.Workflow.Screens;
import resources.testscript.Workflow.Screens.CS_EmailSub_One2One_Cascade_Parent_SOHelper;
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
import component.entity.PropertiesView;
import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.WorkFlowEditor;
import component.entity.model.Relationship;
import component.entity.model.WFEditBox;

/**
 * Description   : Functional Test Script
 * @author eric
 */
public class CS_EmailSub_One2One_Cascade_Parent_SO extends CS_EmailSub_One2One_Cascade_Parent_SOHelper
{
	/**
	 * Script Name   : <b>CS_EmailSub_One2One_Cascade_Parent_SO</b>
	 * Generated     : <b>Sep 15, 2011 4:01:29 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/09/15
	 * @author FANFEI
	 */
	public void testMain(Object[] args) 
	{
		// TODO Insert code here
		WN.useProject(Cfg.projectName);
		
//		EE.runSQL(new ScrapbookCP().database("sampledb")
//				.type("Sybase_ASA_12.x").name("My Sample Database"), 
//				GlobalConfig.getRFTProjectRoot()+"/testscript/Workflow/Screens/setup/add_a_b.sql");
					
		EE.dnd("Database Connections->My Sample Database->sampledb->Tables->wf_ff_a (dba)");
		EE.dnd("Database Connections->My Sample Database->sampledb->Tables->wf_ff_b (dba)");
		WN.createRelationship(new Relationship()
			.startParameter(WN.mboPath(Cfg.projectName, "Wf_ff_a"))
			.target("Wf_ff_b")
			.mapping("aid,aid")
			.composite("true")
			.type(Relationship.TYPE_OTO));
		
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name("myWF")
				.option(WorkFlow.SP_SERVER_INIT)
				.mbo("Wf_ff_a")
				.objectQuery("findByPrimaryKey")
				.subject("aid=1")
				.subjectMatchingRule("aid=")
				.setParameterValue("aid,Subject,aid="));
		
		vpManual("hasscreen", true, WorkFlowEditor.hasScreen("Wfffa")).performTest();
		vpManual("hasscreen", true, WorkFlowEditor.hasScreen("WfffaDetail")).performTest();
		vpManual("hasscreen", true, WorkFlowEditor.hasScreen("WfffbDetail")).performTest();
		vpManual("hasscreen", true, WorkFlowEditor.hasScreen("Wfffbupdateinstance")).performTest();
		
		vpManual("link", true, WorkFlowEditor.hasLinkBetween("Server-initiated (Wf_ff_a)","WfffaDetail")).performTest();
		vpManual("link", true, WorkFlowEditor.hasLinkBetween("WfffaDetail","WfffbDetail")).performTest();
		vpManual("link", true, WorkFlowEditor.hasLinkBetween("WfffbDetail","Wfffbupdateinstance")).performTest();

		vpManual("hasmenu", true, WorkFlowEditor.hasMenuItemInScreen("WfffaDetail", "Open WfffbDetail")).performTest();
		vpManual("haswidget",true,WorkFlowEditor.hasWidgetInScreen("Wfffbupdateinstance",new WFEditBox().label("Aid:"))).performTest();
	
		PropertiesView.clickTab("General");
		System.out.print(DOF.getCCombo(DOF.getGroup(DOF.getRoot(), "Input Data Binding")).getProperty("text"));
		vpManual("dataBinding","Wf_ff_b_aid_attribKey",DOF.getCCombo(DOF.getGroup(DOF.getRoot(), "Input Data Binding")).getProperty("text")).performTest();
	}
}
//passed 