package testscript.Workflow.StartPoint;
import resources.testscript.Workflow.StartPoint.SP_Email_DB_mbo_Enable_2_OptionsHelper;
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

/**
 * Description   : Functional Test Script
 * @author eric
 */
public class SP_Email_DB_mbo_Enable_2_Options extends SP_Email_DB_mbo_Enable_2_OptionsHelper
{
	/**
	 * Script Name   : <b>SP_Email_DB_mbo_Enable_2_Options</b>
	 * Generated     : <b>Sep 13, 2011 4:49:48 PM</b>
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
				.type(WorkFlow.SP_SERVER_INIT));
		PropertiesView.setEmailMbo("States");
		
		PropertiesView.clickTab("Keys");
		DOF.getButton(DOF.getRoot(), "&New...").click();
		sleep(1);
		TopLevelTestObject dialog = DOF.getDialog("Key");
		WO.setTextField(dialog, DOF.getTextField(dialog, "Name:"), "email_key_mbo_queryresults");
		((ToggleGUITestObject)DOF.getButton(dialog, "&Sent by server")).clickToState(SELECTED);
		WO.setCCombo(DOF.getCCombo(dialog, "Mobile business object:"), "States");
		DOF.getButton(dialog, "MBO object &query results").click();
		WO.setCCombo(DOF.getCCombo(dialog, "Type:"),"string");
			
		vpManual("ifuserdefinded",true,DOF.getButton(DOF.getDialog("Key"), "&User-defined").invoke("getSelection")).performTest();		
		DOF.getButton(dialog, "OK").click();
	}
}
//passed
