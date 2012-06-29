package testscript.Workflow.WF_ErrorHandle;
import resources.testscript.Workflow.WF_ErrorHandle.EH_633845_Improved_ErrorMsg_3Helper;
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
import com.sybase.automation.framework.widget.helper.TableHelper;
import com.sybase.automation.framework.widget.helper.WO;

import component.entity.EE;
import component.entity.MainMenu;
import component.entity.PropertiesView;
import component.entity.WFFlowDesigner;
import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.WorkFlowEditor;
import component.entity.model.WFEditBox;
import component.entity.model.WFLview;
import component.entity.model.WFScreenMenuItem;
import component.view.Problems;

/**
 * Description   : Functional Test Script
 * @author ffan
 */
public class EH_633845_Improved_ErrorMsg_3 extends EH_633845_Improved_ErrorMsg_3Helper
{
	/**
	 * Script Name   : <b>Improved_ErrorMsg_3</b>
	 * Generated     : <b>Oct 14, 2011 3:24:11 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/10/14
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
				.subject("findall")
				.subjectMatchingRule("findall"));
		
		WorkFlowEditor.addScreen("ErrorScreen");
		WorkFlowEditor.addScreen("ErrorDetailScreen");
		
		WorkFlowEditor.setMenuItem("Departmentdeleteinstance", new WFScreenMenuItem().name("Delete").setErrorScreen("ErrorScreen"));
		WorkFlowEditor.addWidget("ErrorScreen", new WFLview().key("ErrorLogs")
						.lvDetailScreen("ErrorDetailScreen"));
		
		PropertiesView.addCell("0","cell line 0","ErrorLogMessage","100");
		sleep(1);
		MainMenu.saveAll();
		sleep(1);
		
		WorkFlowEditor.addWidget("ErrorDetailScreen", new WFLview().key("ErrorLogMessageAsList"));
		sleep(1);
		PropertiesView.addCell("0","cell line 0","name","100");
		PropertiesView.addCell("1","cell line 1","value","100");
		
		vpManual("noerror",0,Problems.getErrors().size()).performTest();
		}
	}
//passed 2