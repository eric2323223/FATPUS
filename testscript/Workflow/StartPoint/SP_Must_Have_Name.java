package testscript.Workflow.StartPoint;
import resources.testscript.Workflow.StartPoint.SP_Must_Have_NameHelper;
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
import component.entity.WFFlowDesigner;
import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.WorkFlowEditor;
import component.entity.model.StartPoint;
import component.view.Problems;

/**
 * Description   : Functional Test Script
 * @author eric
 */
public class SP_Must_Have_Name extends SP_Must_Have_NameHelper
{
	/**
	 * Script Name   : <b>SP_Must_Have_Name</b>
	 * Generated     : <b>Sep 2, 2011 12:21:42 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/09/02
	 * @author eric
	 */
	public void testMain(Object[] args) 
	{
		WN.useProject(Cfg.projectName);
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name("myWF"));
		
		WorkFlowEditor.addStartingPoint(new StartPoint()
			.type(WorkFlow.SP_CLIENT_INIT));
		vpManual("SPname","Client-initiated",WFFlowDesigner.getStartPointname(WorkFlow.SP_CLIENT_INIT)).performTest();
	
		WorkFlowEditor.addStartingPoint(new StartPoint()
				.type(WorkFlow.SP_ACTIVATE));
		vpManual("SPname","Activate",WFFlowDesigner.getStartPointname(WorkFlow.SP_ACTIVATE)).performTest();
		
		WorkFlowEditor.addStartingPoint(new StartPoint()
			.type(WorkFlow.SP_CREDENTIAL_REQUEST));
		vpManual("SPname","Credential Request",WFFlowDesigner.getStartPointname(WorkFlow.SP_CREDENTIAL_REQUEST)).performTest();
		
		WorkFlowEditor.addStartingPoint(new StartPoint()
			.type(WorkFlow.SP_SERVER_INIT));
		MainMenu.saveAll();
		vpManual("hasscreen","<...>",WFFlowDesigner.getStartPointname(WorkFlow.SP_SERVER_INIT)).performTest();
		sleep(1);
		System.out.println(Problems.getErrors().size());
		vpManual("hasscreen",2,Problems.getErrors().size()).performTest();
		
	}
}
//passed
