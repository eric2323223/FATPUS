package testscript.Workflow.AllKindOfMbo;
import resources.testscript.Workflow.AllKindOfMbo.M_636112_RefreshWithMBO_1Helper;
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
public class M_636112_RefreshWithMBO_1 extends M_636112_RefreshWithMBO_1Helper
{
	/**
	 * Script Name   : <b>M_636112_RefreshWithMBO_1</b>
	 * Generated     : <b>Nov 18, 2011 3:34:18 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/11/18
	 * @author ffan
	 */
	public void testMain(Object[] args) 
	{
		// TODO Insert code here
		WN.useProject(Cfg.projectName);
		EE.dnd("Database Connections->My Sample Database->sampledb->Tables->department (dba)");	
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name("myWF_1")
				.option(WorkFlow.SP_SERVER_INIT));
		PropertiesView.jumpStart(new WorkFlow().mbo("Department")
				.objectQuery("findAll")
				.subject("findall")
				.subjectMatchingRule("findall"));
		
		WorkFlowEditor.dragMbo(Cfg.projectName, "Department");
		WorkFlowEditor.link(WorkFlow.SP_SERVER_INIT,"Department");
		
		vpManual("haseditbox",true,WorkFlowEditor.hasWidgetInScreen("Departmentcreate", new WFEditBox().label("Dept name:"))).performTest();
		vpManual("haseditbox",true,WorkFlowEditor.hasWidgetInScreen("DepartmentDetail", new WFEditBox().label("Dept name:"))).performTest();
		vpManual("haseditbox",true,WorkFlowEditor.hasWidgetInScreen("Departmentupdateinstance", new WFEditBox().label("Dept name:"))).performTest();
		vpManual("haseditbox",true,WorkFlowEditor.hasWidgetInScreen("Departmentdeleteinstance", new WFEditBox().label("Dept name:"))).performTest();
		
		DOF.getCTabItem(DOF.getRoot(), "Flow Design").click(atPoint(25,10));
		
		WFFlowDesigner.removeScreen("DepartmentDetail");
		WFFlowDesigner.removeScreen("Departmentupdateinstance");
		WFFlowDesigner.removeScreen("Departmentdeleteinstance");
		WFFlowDesigner.removeScreen("Departmentcreate");
		WFFlowDesigner.removeScreen("Department");
		
		//delete the attribute "dept_name":
		MBOProperties mbo = new MBOProperties(Cfg.projectName, "Department");
		mbo.deleteAttribute("dept_name");
		MainMenu.saveAll();
		
		WN.openWorkFlow(Cfg.projectName, "myWF_1.xbw");
		WorkFlowEditor.dragMbo(Cfg.projectName, "Department");
		WorkFlowEditor.link(WorkFlow.SP_SERVER_INIT,"Department");
		vpManual("haseditbox",false,WorkFlowEditor.hasWidgetInScreen("DepartmentDetail", new WFEditBox().label("Dept name:"))).performTest();
		vpManual("haseditbox",true,WorkFlowEditor.hasWidgetInScreen("Departmentcreate", new WFEditBox().label("Dept name:"))).performTest();
		vpManual("haseditbox",true,WorkFlowEditor.hasWidgetInScreen("Departmentupdateinstance", new WFEditBox().label("Dept name:"))).performTest();
		vpManual("haseditbox",true,WorkFlowEditor.hasWidgetInScreen("Departmentdeleteinstance", new WFEditBox().label("Dept name:"))).performTest();
		
	}
}
//passed 2
