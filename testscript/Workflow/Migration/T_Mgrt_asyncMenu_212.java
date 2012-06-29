package testscript.Workflow.Migration;
import resources.testscript.Workflow.Migration.T_Mgrt_asyncMenu_212Helper;
import testscript.Workflow.Migration.cfg.Cfg;

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
import component.entity.WN;
import component.entity.WorkFlowEditor;
import component.entity.model.WFScreenMenuItem;
import component.view.Problems;

/**
 * Description   : Functional Test Script
 * @author jiaozhou
 */
public class T_Mgrt_asyncMenu_212 extends T_Mgrt_asyncMenu_212Helper
{
	/**
	 * Script Name   : <b>T_Mgrt_asyncMenu_212</b>
	 * Generated     : <b>May 31, 2012 10:09:03 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2012/05/31
	 * @author jiaozhou
	 */
	public void testMain(Object[] args) 
	{
		WN.deleteAllProject();
		WN.importProjectFromFile(Cfg.PROJECT212);
		WN.openLegacyProjectMbo("pro212", "Department", true);
		vpManual("noError", 0 , WN.getMigratingResult().errors().size()).performTest();
		vpManual("noError", 0 , Problems.getErrors().size()).performTest();
		WN.openWorkFlow("pro212", "asynccrudwf");
		WorkFlowEditor.setMenuItem("Department_create", new WFScreenMenuItem().name("Create")
				.generateErrorScreen(true).subject("hello!").from("Jennie Zhou"));
		vpManual("hasLink", true, WorkFlowEditor.hasLinkBetween("Department_c...", "ErrorList")).performTest();
		vpManual("hasKey", true, WorkFlowEditor.hasKeyInScreen("ErrorList", "AsyncRequestErrorLogs")).performTest();
		
	}
}

