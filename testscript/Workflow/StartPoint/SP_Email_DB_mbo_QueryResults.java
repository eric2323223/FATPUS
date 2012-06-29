package testscript.Workflow.StartPoint;
import resources.testscript.Workflow.StartPoint.SP_Email_DB_mbo_QueryResultsHelper;
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
import component.entity.PropertiesView;
import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.WorkFlowEditor;
import component.entity.model.StartPoint;
import component.view.Problems;

/**
 * Description   : Functional Test Script
 * @author eric
 */
public class SP_Email_DB_mbo_QueryResults extends SP_Email_DB_mbo_QueryResultsHelper
{
	/**
	 * Script Name   : <b>SP_Email_DB_mbo_QueryResults</b>
	 * Generated     : <b>Sep 13, 2011 9:38:29 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/09/13
	 * @author FANFEI
	 */
	public void testMain(Object[] args) 
	{
		// TODO Insert code here
		WN.useProject(Cfg.projectName);
		EE.dnd("Database Connections->My Sample Database->sampledb->Tables->states (dba)");
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name("myWF"));
		WorkFlowEditor.addStartingPoint(new StartPoint()
				.type(WorkFlow.SP_SERVER_INIT).mbo("States"));
		PropertiesView.setEmailMbo("States");
		
		WorkFlowEditor.addScreen("A");
		WorkFlowEditor.link(WorkFlow.SP_SERVER_INIT, "A");
		DOF.getWFServerInitiateFlowStartingPointFigure().click();
		
		PropertiesView.clickTab("Keys");
		DOF.getButton(DOF.getRoot(), "&New...").click();
		sleep(1);
		TopLevelTestObject dialog = DOF.getDialog("Key");
		WO.setTextField(dialog, DOF.getTextField(dialog, "Name:"), "email_key_mbo_queryresults");
		((ToggleGUITestObject)DOF.getButton(dialog, "&Sent by server")).clickToState(SELECTED);
		WO.setCCombo(DOF.getCCombo(dialog, "Mobile business object:"), "States");
		DOF.getButton(dialog, "MBO object &query results").click();
		
		vpManual("buttonState",true,DOF.getButton(dialog, "&Edit...").invoke("getEnabled")).performTest();		
		vpManual("listValue","list",DOF.getCCombo(dialog, "Type:").getProperty("text")).performTest();		
		
		DOF.getButton(dialog, "OK").click();
		
		vpManual("Properies", "email_key_mbo_queryresults,list,States", PropertiesView.getKeyAttributesofStartPoint(new StartPoint()).getKey()).performTest();
		
		PropertiesView.editKey("email_key_mbo_queryresults");
		sleep(1);
		vpManual("buttonState",true,DOF.getButton(DOF.getDialog("Key"), "&Edit...").invoke("getEnabled")).performTest();		
		sleep(1);
		vpManual("listValue","email_key_mbo_queryresults",DOF.getTextField(dialog, "Name:").getProperty("text")).performTest();
		sleep(1);
		vpManual("listValue","list",DOF.getCCombo(dialog, "Type:").getProperty("text")).performTest();		
		vpManual("listValue","States",DOF.getCCombo(dialog, "Mobile business object:").getProperty("text")).performTest();		
		
		DOF.getButton(dialog, "OK").click();
		
		vpManual("haserror",0,Problems.getErrors().size()).performTest();		
	}
}
//passed
