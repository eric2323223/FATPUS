package testscript.Workflow.Screens;
import resources.testscript.Workflow.Screens.CSFO_One2One_Cascade_Bi_dir_ParentHelper;
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
public class CSFO_One2One_Cascade_Bi_dir_Parent extends CSFO_One2One_Cascade_Bi_dir_ParentHelper
{
	/**
	 * Script Name   : <b>CSFO_One2One_Cascade_Bi_dir_Parent</b>
	 * Generated     : <b>Sep 15, 2011 2:54:36 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/09/15
	 * @author FANFEI
	 */
	public void testMain(Object[] args) 
	{
		// TODO Insert code here
//		WN.useProject(Cfg.projectName);
//		EE.dnd("Database Connections->My Sample Database->sampledb->Tables->wf_ff_a (dba)");
//		EE.dnd("Database Connections->My Sample Database->sampledb->Tables->wf_ff_b (dba)");
//		WN.createRelationship(new Relationship()
//			.startParameter(WN.mboPath(Cfg.projectName, "Wf_ff_a"))
//			.target("Wf_ff_b")
//			.mapping("aid,aid")
//			.composite("true")
//			.bidirectional("true")
//			.type(Relationship.TYPE_OTO));
//		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
//				.name("myWF")
//				.option(WorkFlow.SP_CLIENT_INIT));
//		WorkFlowEditor.dragMbo(Cfg.projectName, "Wf_ff_a");
//		
//		vpManual("creen", true, WorkFlowEditor.hasScreen("Wfffa")).performTest();
//		vpManual("creen", true, WorkFlowEditor.hasScreen("Wfffaupdateinstance")).performTest();
//		vpManual("creen", true, WorkFlowEditor.hasScreen("Wfffadeleteinstance")).performTest();
//		vpManual("creen", true, WorkFlowEditor.hasScreen("WfffaDetail")).performTest();
//		vpManual("creen", true, WorkFlowEditor.hasScreen("Wfffacreate")).performTest();
//		vpManual("creen", true, WorkFlowEditor.hasScreen("WfffbDetail")).performTest();
//		vpManual("creen", true, WorkFlowEditor.hasScreen("Wfffbupdateinstance")).performTest();
//		
//		vpManual("link", true, WorkFlowEditor.hasLinkBetween("WfffaDetail","WfffbDetail")).performTest();
//		vpManual("link", true, WorkFlowEditor.hasLinkBetween("WfffbDetail","Wfffbupdateinstance")).performTest();
//		
		
		//vp1:parentDetail:
		vpManual("hasmenu", true, WorkFlowEditor.hasMenuItemInScreen("WfffaDetail", "Open WfffbDetail")).performTest();
		vpManual("hasmenu", true, WorkFlowEditor.hasMenuItemInScreen("WfffaDetail", "Open Wfffaupdateinstance")).performTest();
		vpManual("hasmenu", true, WorkFlowEditor.hasMenuItemInScreen("WfffaDetail", "Open Wfffadeleteinstance")).performTest();
		vpManual("hasmenu", true, WorkFlowEditor.hasWidgetInScreen("WfffaDetail", new WFEditBox().label("Aid:"))).performTest();
		PropertiesView.clickTab("General");
		vpManual("dataBinding","Wf_ff_a_aid_attribKey",DOF.getCCombo(DOF.getGroup(DOF.getRoot(), "Input Data Binding")).getProperty("text")).performTest();
		
		vpManual("hasmenu", true, WorkFlowEditor.hasWidgetInScreen("WfffaDetail", new WFEditBox().label("Aname:"))).performTest();
		PropertiesView.clickTab("General");
		vpManual("dataBinding","Wf_ff_a_aname_attribKey",DOF.getCCombo(DOF.getGroup(DOF.getRoot(), "Input Data Binding")).getProperty("text")).performTest();
		
		//vp2:childDetail:
		vpManual("hasmenu", true, WorkFlowEditor.hasMenuItemInScreen("WfffbDetail", "Open Wfffbupdateinstance")).performTest();
		vpManual("hasmenu", true, WorkFlowEditor.hasMenuItemInScreen("WfffbDetail", "Wf_ff_b_delete_instance")).performTest();
		vpManual("hasmenu", true, WorkFlowEditor.hasWidgetInScreen("WfffbDetail", new WFEditBox().label("Bid:"))).performTest();
		PropertiesView.clickTab("General");
		vpManual("dataBinding","Wf_ff_b_bid_attribKey",DOF.getCCombo(DOF.getGroup(DOF.getRoot(), "Input Data Binding")).getProperty("text")).performTest();
		
		vpManual("hasmenu", true, WorkFlowEditor.hasWidgetInScreen("WfffbDetail", new WFEditBox().label("Aid:"))).performTest();
		PropertiesView.clickTab("General");
		vpManual("dataBinding","Wf_ff_b_aid_attribKey",DOF.getCCombo(DOF.getGroup(DOF.getRoot(), "Input Data Binding")).getProperty("text")).performTest();
		
		vpManual("hasmenu", true, WorkFlowEditor.hasWidgetInScreen("WfffbDetail", new WFEditBox().label("Bname:"))).performTest();
		PropertiesView.clickTab("General");
		vpManual("dataBinding","Wf_ff_b_bname_attribKey",DOF.getCCombo(DOF.getGroup(DOF.getRoot(), "Input Data Binding")).getProperty("text")).performTest();
		
		//vp3:child_Update:
		vpManual("hasmenu", true, WorkFlowEditor.hasMenuItemInScreen("Wfffbupdateinstance", "Update")).performTest();
		
		vpManual("hasmenu", true, WorkFlowEditor.hasWidgetInScreen("Wfffbupdateinstance", new WFEditBox().label("Bid:"))).performTest();
		PropertiesView.clickTab("General");
		vpManual("dataBinding","Wf_ff_b_bid_attribKey",DOF.getCCombo(DOF.getGroup(DOF.getRoot(), "Input Data Binding")).getProperty("text")).performTest();
		
		vpManual("hasmenu", true, WorkFlowEditor.hasWidgetInScreen("Wfffbupdateinstance", new WFEditBox().label("Aid:"))).performTest();
		PropertiesView.clickTab("General");
		vpManual("dataBinding","Wf_ff_b_aid_attribKey",DOF.getCCombo(DOF.getGroup(DOF.getRoot(), "Input Data Binding")).getProperty("text")).performTest();
		
		vpManual("hasmenu", true, WorkFlowEditor.hasWidgetInScreen("Wfffbupdateinstance", new WFEditBox().label("Bname:"))).performTest();
		PropertiesView.clickTab("General");
		vpManual("dataBinding","Wf_ff_b_bname_attribKey",DOF.getCCombo(DOF.getGroup(DOF.getRoot(), "Input Data Binding")).getProperty("text")).performTest();
		
	}
}
//passed
