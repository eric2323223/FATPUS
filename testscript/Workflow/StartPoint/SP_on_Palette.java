package testscript.Workflow.StartPoint;
import resources.testscript.Workflow.StartPoint.SP_on_PaletteHelper;
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
import component.entity.EE;
import component.entity.MainMenu;
import component.entity.WFFlowDesigner;
import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.WorkFlowEditor;
import component.entity.model.StartPoint;
import component.view.Problems;

/**
 * Description   : Functional Test Script
 * @author ffan
 */
public class SP_on_Palette extends SP_on_PaletteHelper
{
	/**
	 * Script Name   : <b>SP_on_Palette</b>
	 * Generated     : <b>Nov 25, 2011 6:04:43 AM</b>
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
				.name("AllSP"));
		
		WorkFlowEditor.addStartingPoint(new StartPoint()
			.type(WorkFlow.SP_CLIENT_INIT));
		vpManual("SPname","Client-initiated",WFFlowDesigner.getStartPointname(WorkFlow.SP_CLIENT_INIT)).performTest();
		WorkFlowEditor.addScreen("c");
		WorkFlowEditor.link(WorkFlow.SP_CLIENT_INIT, "c");
		
		WorkFlowEditor.addStartingPoint(new StartPoint()
				.type(WorkFlow.SP_ACTIVATE));
		vpManual("SPname","Activate",WFFlowDesigner.getStartPointname(WorkFlow.SP_ACTIVATE)).performTest();
		
		WorkFlowEditor.addStartingPoint(new StartPoint()
			.type(WorkFlow.SP_CREDENTIAL_REQUEST));
		vpManual("SPname","Credential Request",WFFlowDesigner.getStartPointname(WorkFlow.SP_CREDENTIAL_REQUEST)).performTest();
		
		WorkFlowEditor.addStartingPoint(new StartPoint()
			.type(WorkFlow.SP_SERVER_INIT)
			.mbo("Department"));
		vpManual("SPname","Server-initiated (Department)",WFFlowDesigner.getStartPointname(WorkFlow.SP_SERVER_INIT)).performTest();
		WorkFlowEditor.addScreen("s");
		WorkFlowEditor.link(WorkFlow.SP_SERVER_INIT, "s");
		
		MainMenu.saveAll();
		vpManual("haserror",0,Problems.getErrors().size()).performTest();
	}
}
//passed
