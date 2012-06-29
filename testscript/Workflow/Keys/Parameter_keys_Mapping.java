package testscript.Workflow.Keys;
import resources.testscript.Workflow.Keys.Parameter_keys_MappingHelper;
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
import component.entity.MainMenu;
import component.entity.PropertiesView;
import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.WorkFlowEditor;
import component.entity.model.StartPoint;
import component.entity.model.WFEditBox;
import component.entity.model.WFScreenMenuItem;

/**
 * Description   : Functional Test Script
 * @author ffan
 */
public class Parameter_keys_Mapping extends Parameter_keys_MappingHelper
{
	/**
	 * Script Name   : <b>Parameter_keys_Mapping</b>
	 * Generated     : <b>Oct 10, 2011 5:17:34 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/10/10
	 * @author ffan
	 */
	public void testMain(Object[] args) 
	{
		// TODO Insert code here
		WN.useProject(Cfg.projectName);
		EE.dnd("Database Connections->My Sample Database->sampledb->Tables->department (dba)");
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name("myWF")
				.option(WorkFlow.SP_CLIENT_INIT));
		
		//set Create Operation screen---:
			WorkFlowEditor.addWidget("Start", new WFEditBox().label("id:")
					.newKey("id,int"));
			WorkFlowEditor.addWidget("Start", new WFEditBox().label("name:")
					.newKey("n,string"));
			WorkFlowEditor.addWidget("Start", new WFEditBox().label("head_id:")
					.newKey("head_id,int"));
			
			MainMenu.saveAll();
			WorkFlowEditor.addMenuItem("Start", new WFScreenMenuItem().name("create")
					.type("Online Request")
					.project(Cfg.projectName)
					.mbo("Department")
					.operation("create")
					.parametermapping("dept_id,id")
					.parametermapping("dept_name,n")
					.parametermapping("dept_head_id,head_id")
					);
		
		MainMenu.saveAll();
		
		DOF.getWFMenuItemFigure(DOF.getRoot(), "Cancel Screen").click();
//		System.out.println("ParameterMapping="+PropertiesView.getPatameterMappingofMenuItem("Start Screen","create").getKey());
		vpManual("PM","dept_id,id,:dept_name,n,:dept_head_id,head_id,",PropertiesView.getPatameterMappingofMenuItem("Start Screen","create").getKey()).performTest();
		
	}
}
//passed
