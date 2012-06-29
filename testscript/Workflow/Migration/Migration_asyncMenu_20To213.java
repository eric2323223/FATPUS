package testscript.Workflow.Migration;
import resources.testscript.Workflow.Migration.Migration_asyncMenu_20To213Helper;
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
 * @author eric
 */
public class Migration_asyncMenu_20To213 extends Migration_asyncMenu_20To213Helper
{
	/**
	 * Script Name   : <b>Migration_asyncMenu_20To213</b>
	 * Generated     : <b>Mar 6, 2012 11:42:54 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2012/03/06
	 * @author eric
	 */
	public void testMain(Object[] args) 
	{
		WN.deleteAllProject();
		WN.importProjectFromFile(Cfg.PROJECT20);
		WN.openLegacyProjectMbo("pro20", "Department", true);
		vpManual("noError", 0 , WN.getMigratingResult().errors().size()).performTest();
		vpManual("noError", 0 , Problems.getErrors().size()).performTest();
		WN.openWorkFlow("pro20", "asynccrudwf");
		WorkFlowEditor.setMenuItem("Department_create", new WFScreenMenuItem().name("Create")
				.generateErrorScreen(true).subject("hello!").from("Jennie Zhou"));
		vpManual("hasLink", true, WorkFlowEditor.hasLinkBetween("Department_c...", "ErrorList")).performTest();
		vpManual("hasKey", true, WorkFlowEditor.hasKeyInScreen("ErrorList", "AsyncRequestErrorLogs")).performTest();
		
	}
}

