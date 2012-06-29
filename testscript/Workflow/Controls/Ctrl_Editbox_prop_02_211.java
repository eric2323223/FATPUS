package testscript.Workflow.Controls;
import resources.testscript.Workflow.Controls.Ctrl_Editbox_prop_02_211Helper;
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
import component.entity.model.DeployOption;
import component.entity.model.WFEditBox;

/**
 * Description   : Functional Test Script
 * @author xjf
 */
public class Ctrl_Editbox_prop_02_211 extends Ctrl_Editbox_prop_02_211Helper
{
	/**
	 * Script Name   : <b>Ctrl_Editbox_prop_02</b>
	 * Generated     : <b>Oct 12, 2011 7:15:58 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/10/12
	 * @author xjf
	 */
	public void testMain(Object[] args) 
	{
		// TODO Insert code here
		WN.useProject(Cfg.projectName);
		EE.dnd(EE.parseResourcePath("Database Connections->My Sample Database->sampledb->Tables->department (dba)"), 50, 90);
		
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name("wfmbocreate")
				.option(WorkFlow.SP_CLIENT_INIT));
		
		WorkFlowEditor.addWidget(Cfg.projectName, "wfmbocreate.xbw", "Start", new WFEditBox().label("editbox:")
				.validationExpression("expres{2}ion\\d+")
				.validationMessage("Please input a validate expression(expression1)")
				.maxLength("20")
				.password(true)
				.credentialpassword(true)
				);
	
		vpManual("linesisenabled","false",DOF.getTextField(DOF.getRoot(), "Number of lines:").isEnabled()).performTest();
		vpManual("Maxlengthisenabled","true",DOF.getTextField(DOF.getRoot(), "Maximum length:").isEnabled()).performTest();
		vpManual("Validationmessageisenabled","true",DOF.getTextField(DOF.getRoot(), "Validation message:").isEnabled()).performTest();
		vpManual("regularexpressionisenabled","true",DOF.getTextField(DOF.getRoot(), "Validation regular expression:").isEnabled()).performTest();
		vpManual("usernameisenabled",true,DOF.getButton(DOF.getRoot(),"Credential cache &username").isEnabled()).performTest();
		vpManual("passwordisenabled",true,DOF.getButton(DOF.getRoot(),"Credential cache pass&word").isEnabled()).performTest();
		
		PropertiesView.clickTab("General");
		PropertiesView.setToggleButton(DOF.getButton(DOF.getRoot(), "&Password"),false);
		
		sleep(2);
		PropertiesView.set(new WFEditBox().logicalType("DATE"));
		PropertiesView.clickTab("Advanced");
		vpManual("Validationmessageisenabled","true",DOF.getTextField(DOF.getRoot(), "Validation message:").isEnabled()).performTest();
		vpManual("Minimumisenabled","true",DOF.getTextField(DOF.getRoot(), "Minimum value:").isEnabled()).performTest();
		vpManual("Maximumisenabled","true",DOF.getTextField(DOF.getRoot(), "Maximum value:").isEnabled()).performTest();
		sleep(1);
		PropertiesView.set(new WFEditBox().logicalType("DATETIME"));
		PropertiesView.clickTab("Advanced");
		vpManual("Validationmessageisenabled","true",DOF.getTextField(DOF.getRoot(), "Validation message:").isEnabled()).performTest();
		vpManual("Minimumisenabled","true",DOF.getTextField(DOF.getRoot(), "Minimum value:").isEnabled()).performTest();
		vpManual("Maximumisenabled","true",DOF.getTextField(DOF.getRoot(), "Maximum value:").isEnabled()).performTest();
		vpManual("timeprecisionisenabled","true",DOF.getCCombo(DOF.getRoot(), "Time precision:").isEnabled()).performTest();
		
		sleep(1);
		PropertiesView.set(new WFEditBox().logicalType("TIME")
				                          .Timeprecision("HOURS"));
		sleep(1);
		PropertiesView.set(new WFEditBox().logicalType("NUMERIC")
				.lines("5")
				);
		PropertiesView.clickTab("Advanced");
		vpManual("Validationmessageisenabled","true",DOF.getTextField(DOF.getRoot(), "Validation message:").isEnabled()).performTest();
		vpManual("Minimumisenabled","true",DOF.getTextField(DOF.getRoot(), "Minimum value:").isEnabled()).performTest();
		vpManual("Maximumisenabled","true",DOF.getTextField(DOF.getRoot(), "Maximum value:").isEnabled()).performTest();
		vpManual("regularexpressionisenabled","true",DOF.getTextField(DOF.getRoot(), "Validation regular expression:").isEnabled()).performTest();
		vpManual("linesisenabled","true",DOF.getTextField(DOF.getRoot(), "Number of lines:").isEnabled()).performTest();
		vpManual("Maxlengthisenabled","true",DOF.getTextField(DOF.getRoot(), "Maximum length:").isEnabled()).performTest();
		vpManual("decimalsisenabled","true",DOF.getTextField(DOF.getRoot(), "Number of decimals:").isEnabled()).performTest();
		vpManual("requiredisenabled","true",DOF.getButton(DOF.getRoot(),"&Required").isEnabled()).performTest();
		vpManual("timeprecisionisenabled","false",DOF.getCCombo(DOF.getRoot(), "Time precision:").isEnabled()).performTest();
		vpManual("usernameisenabled","false",DOF.getButton(DOF.getRoot(),"Credential cache &username").isEnabled()).performTest();
		vpManual("passwordisenabled","false",DOF.getButton(DOF.getRoot(),"Credential cache pass&word").isEnabled()).performTest();
				vpManual("Validationmessageisenabled","true",DOF.getTextField(DOF.getRoot(), "Validation message:").isEnabled()).performTest();
		
		PropertiesView.set(new WFEditBox()
		.logicalType("DATETIME"));
		vpManual("whethercheck","true",DOF.getButton(DOF.getRoot(),"Read &only").isEnabled()).performTest();
		PropertiesView.clickTab("Advanced");
		vpManual("Validationmessageisenabled","true",DOF.getTextField(DOF.getRoot(), "Validation message:").isEnabled()).performTest();
		
		
	}
}

