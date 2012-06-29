package testscript.Workflow.AllKindOfMbo;
import resources.testscript.Workflow.AllKindOfMbo.M_636112_RefreshWithMBO_2Helper;
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
import component.entity.MBOProperties;
import component.entity.MainMenu;
import component.entity.PropertiesView;
import component.entity.WFFlowDesigner;
import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.WorkFlowEditor;
import component.entity.model.WFEditBox;

/**
 * Description   : Functional Test Script
 * @author ffan
 */
public class M_636112_RefreshWithMBO_2 extends M_636112_RefreshWithMBO_2Helper
{
	/**
	 * Script Name   : <b>M_636112_RefreshWithMBO_2</b>
	 * Generated     : <b>Nov 25, 2011 11:10:53 PM</b>
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
				.name("myWF_2")
				.option(WorkFlow.SP_SERVER_INIT));
		
		PropertiesView.jumpStart(new WorkFlow().mbo("Department")
				.objectQuery("findAll")
				.subject("findall")
				.subjectMatchingRule("findall"));
		
		WorkFlowEditor.dragMbo(Cfg.projectName, "Department");
		WorkFlowEditor.link(WorkFlow.SP_SERVER_INIT,"Department");
		
		vpManual("haseditbox",false,WorkFlowEditor.hasWidgetInScreen("DepartmentDetail", new WFEditBox().label("Mydept name:"))).performTest();
		vpManual("haseditbox",false,WorkFlowEditor.hasWidgetInScreen("Departmentupdateinstance", new WFEditBox().label("Mydept name:"))).performTest();
		vpManual("haseditbox",false,WorkFlowEditor.hasWidgetInScreen("Departmentdeleteinstance", new WFEditBox().label("Mydept name:"))).performTest();
		vpManual("haseditbox",false,WorkFlowEditor.hasWidgetInScreen("Departmentcreate", new WFEditBox().label("Mydept name:"))).performTest();
		
		DOF.getCTabItem(DOF.getRoot(), "Flow Design").click(atPoint(25,10));
		
		WFFlowDesigner.removeScreen("DepartmentDetail");
		WFFlowDesigner.removeScreen("Departmentupdateinstance");
		WFFlowDesigner.removeScreen("Departmentdeleteinstance");
		WFFlowDesigner.removeScreen("Departmentcreate");
		WFFlowDesigner.removeScreen("Department");
		
		//add a new  attribute "Mydept_name":
		MBOProperties mbo = new MBOProperties(Cfg.projectName, "Department");
		mbo.addAttribute("mydept_name","STRING(20)",true,"dept_name");
		MainMenu.saveAll();
		
		WN.openWorkFlow(Cfg.projectName, "myWF_2.xbw");
		WorkFlowEditor.dragMbo(Cfg.projectName, "Department");
		WorkFlowEditor.link(WorkFlow.SP_SERVER_INIT,"Department");
		vpManual("haseditbox",true,WorkFlowEditor.hasWidgetInScreen("DepartmentDetail", new WFEditBox().label("Mydept name:"))).performTest();
		vpManual("haseditbox",true,WorkFlowEditor.hasWidgetInScreen("Departmentupdateinstance", new WFEditBox().label("Mydept name:"))).performTest();
		vpManual("haseditbox",true,WorkFlowEditor.hasWidgetInScreen("Departmentdeleteinstance", new WFEditBox().label("Mydept name:"))).performTest();
		vpManual("haseditbox",false,WorkFlowEditor.hasWidgetInScreen("Departmentcreate", new WFEditBox().label("Mydept name:"))).performTest();
		
	}
}
//passed 2
