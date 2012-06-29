package testscript.Workflow.StartPoint;
import resources.testscript.Workflow.StartPoint.SP_Email_General_MBOHelper;
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
import component.entity.WFFlowDesigner;
import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.WorkFlowEditor;
import component.entity.model.StartPoint;

/**
 * Description   : Functional Test Script
 * @author eric
 */
public class SP_Email_General_MBO extends SP_Email_General_MBOHelper
{
	/**
	 * Script Name   : <b>SP_Email_General_MBO</b>
	 * Generated     : <b>Sep 13, 2011 11:19:00 AM</b>
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
		EE.dnd("Database Connections->My Sample Database->sampledb->Tables->department (dba)");
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name("myWF"));
		WorkFlowEditor.addStartingPoint(new StartPoint()
				.type(WorkFlow.SP_SERVER_INIT));
		PropertiesView.setEmailMbo("Department", Cfg.projectName);
		
		vpManual("EmailMbo",Cfg.projectName+"/Department",PropertiesView.getEmailMbo()).performTest();
		vpManual("hasscreen","Server-initiated (Department)",WFFlowDesigner.getStartPointname(WorkFlow.SP_SERVER_INIT)).performTest();
		
		PropertiesView.clearMbo();
		
		vpManual("EmailMbo","",PropertiesView.getEmailMbo()).performTest();
		vpManual("hasscreen","Server-initiated ()",WFFlowDesigner.getStartPointname(WorkFlow.SP_SERVER_INIT)).performTest();
	}
}
//passed
