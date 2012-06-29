package testscript.Workflow.Controls;
import resources.testscript.Workflow.Controls.Ctrl_Slider_binding_211Helper;
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
import component.entity.model.DeployOption;
import component.entity.model.WFSlider;

/**
 * Description   : Functional Test Script
 * @author xjf
 */
public class Ctrl_Slider_binding_211 extends Ctrl_Slider_binding_211Helper
{
	/**
	 * Script Name   : <b>Script1</b>
	 * Generated     : <b>Oct 24, 2011 3:49:28 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/10/24
	 * @author xjf
	 */
	public void testMain(Object[] args) 
	{
		// TODO Insert code here
		
		WN.useProject(Cfg.projectName);
		EE.dnd(EE.parseResourcePath("Database Connections->My Sample Database->sampledb->Tables->department (dba)"), 10, 10);
	
		 WN.deployProject(new DeployOption().startParameter(Cfg.projectName)
					.server("My Unwired Server")
					.serverConnectionMapping("My Sample Database,sampledb"));
		
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName).name("wfslider")
				.option(WorkFlow.SP_SERVER_INIT));


		PropertiesView.jumpStart(new WorkFlow()
		        .mbo("Department")
				.objectQuery("findByPrimaryKey")
				.subject("deptid=2")
				.subjectMatchingRule("deptid=")
				.setParameterValue("dept_id,Subject,deptid="));

		WorkFlowEditor.addScreen("linktoemail");
		WorkFlowEditor.link(WorkFlow.SP_SERVER_INIT, "linktoemail");
		
		WorkFlowEditor.addWidget(Cfg.projectName, "wfslider.xbw", "linktoemail", new WFSlider().label("slider")
				.newKeyBindMbo("sldkey,int,Department,dept_id")
				);
	
			DOF.getButton(DOF.getRoot(), "&New key...").click();
			vpManual("sentbyserverenabled","true",DOF.getButton(DOF.getDialog("Key"), "&Sent by server").isEnabled()).performTest();
			WO.setTextField(DOF.getDialog("Key"), DOF.getTextField(DOF.getDialog("Key"), "Name:"), "sldkey1");
			WO.setCCombo(DOF.getCCombo(DOF.getDialog("Key"), "Type:"), "int");
			
			((ToggleGUITestObject)DOF.getButton(DOF.getDialog("Key"), "&Sent by server")).clickToState(SELECTED);
			WO.setCCombo(DOF.getCCombo(DOF.getDialog("Key"), "Mobile business object:"), "Department");
		    DOF.getButton(DOF.getDialog("Key"), "&Mobile business object attribute").click();
		    DOF.getCCombo(DOF.getDialog("Key"), "Name:").click();
		    System.out.print(PropertiesView.verifykeylistcom());
			boolean b = PropertiesView.verifykeylistcom().contains("dept_id,dept_name,dept_head_id");
			vpManual("listright",true,b).performTest();
			WO.setCCombo(DOF.getCCombo(DOF.getDialog("Key"), "Name:"),"dept_id");
			vpManual("typeright","int",DOF.getCCombo(DOF.getDialog("Key"), "Type:").getProperty("text").toString());
			DOF.getButton(DOF.getDialog("Key"), "OK").click();
			 String key = DOF.getCCombo(DOF.getGroup(DOF.getRoot(), "Input Data Binding")).getProperty("text").toString();
		System.out.print(">>>>>>>>"+key);
		vpManual("keylist","sldkey1",key).performTest();
	}
}

