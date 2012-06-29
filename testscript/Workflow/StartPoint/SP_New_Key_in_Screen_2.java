package testscript.Workflow.StartPoint;
import resources.testscript.Workflow.StartPoint.SP_New_Key_in_Screen_2Helper;
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
import component.entity.model.StartPoint;
import component.entity.model.WFEditBox;
import component.entity.model.WFScreenMenuItem;
import component.entity.model.WFSlider;
import component.view.Problems;

/**
 * Description   : Functional Test Script
 * @author eric
 */
public class SP_New_Key_in_Screen_2 extends SP_New_Key_in_Screen_2Helper
{
	/**
	 * Script Name   : <b>SP_New_Key_in_Screen_2</b>
	 * Generated     : <b>Sep 9, 2011 12:15:31 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/09/09
	 * @author FANFEI
	 */
	public void testMain(Object[] args) 
	{
		// TODO Insert code here
		WN.useProject(Cfg.projectName);
		EE.dnd("Database Connections->My Sample Database->sampledb->Tables->department (dba)");
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name("myWF")
				.option(WorkFlow.SP_CLIENT_INIT));
		
		WorkFlowEditor.addScreen("newscreen");
		
		WorkFlowEditor.addWidget("Start", new WFEditBox().label("dept_id:")
				.newKey("id,int"));
		WorkFlowEditor.addMenuItem("Start", new WFScreenMenuItem().name("findByPK")
				.type("Online Request")
				.project(Cfg.projectName)
				.mbo("Department")
				.objectQuery("findByPrimaryKey")
				.defaultSuccessScreen("newscreen")
				.parametermapping("dept_id,id"));
		
		DOF.getCTabItem(DOF.getRoot(), "Flow Design").click();
		DOF.getWFClientInitiateFlowStartingPointFigure().click();
		
		System.out.print("keys="+PropertiesView.getKeyAttributesofStartPoint(new StartPoint()).getKey());
		vpManual("key", true, PropertiesView.getKeyAttributesofStartPoint(new StartPoint()).getKey().contains("id,int")).performTest();
		vpManual("noerror", 0, Problems.getErrors().size()).performTest();
	}
}
//passed
