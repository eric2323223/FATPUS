package testscript.Workflow.Keys;
import resources.testscript.Workflow.Keys.EnhancedKey_HardCodedValue_DTHelper;
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
import component.entity.MainMenu;
import component.entity.PropertiesView;
import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.WorkFlowEditor;
import component.entity.model.WFEditBox;

/**
 * Description   : Functional Test Script
 * @author ffan
 */
public class EnhancedKey_HardCodedValue_DT extends EnhancedKey_HardCodedValue_DTHelper
{
	/**
	 * Script Name   : <b>EnhancedKey_HardCodedValue_DT</b>
	 * Generated     : <b>Nov 25, 2011 4:51:00 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/11/25
	 * @author ffan
	 */
	public void testMain(Object[] args) 
	{
		// TODO Insert code here
		WN.useProject(Cfg.projectName);
		EE.dnd("Database Connections->My Sample Database->sampledb->Tables->department (dba)");
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name("myWF")
				.option(WorkFlow.SP_SERVER_INIT)
				.mbo("Department")
				.objectQuery("findAll")
				.subject("dept_id=1")
				.subjectMatchingRule("dept_id="));
		
		WorkFlowEditor.addScreen("testscreen");
		WorkFlowEditor.addWidget("testscreen", new WFEditBox().label("My:")
				.labelPosition("LEFT"));
		
		setHardCodeValue();
		Boolean key = PropertiesView.getKeyAttributesofFlowDesign().getKey().contains("id,string,myhardcode");
		System.out.println("key="+key);
		vpManual("hasKey",true,key).performTest();
	}
	
	
	public static void setHardCodeValue(){
		DOF.getButton(DOF.getRoot(), "&New key...").click();
		TopLevelTestObject dialog = DOF.getDialog("Key");
		WO.setTextField(dialog, DOF.getTextField(dialog, "Name:"), "id");
		WO.setCCombo(DOF.getCCombo(dialog, "Type:"), "string");
		((ToggleGUITestObject)DOF.getButton(dialog, "&Sent by server")).clickToState(SELECTED);
		DOF.getButton(dialog, "&Hard-coded value").click();
		WO.setTextField(dialog, DOF.getTextField(DOF.getGroup(dialog,"Input Data Binding")),"myhardcode");
		DOF.getButton(dialog, "OK").click();
		MainMenu.saveAll();
	}
}
//passed
