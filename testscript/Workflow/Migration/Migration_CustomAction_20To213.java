package testscript.Workflow.Migration;
import resources.testscript.Workflow.Migration.Migration_CustomAction_20To213Helper;
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
import component.entity.Preference;
import component.entity.WN;
import component.entity.WorkFlowEditor;
import component.entity.model.WFScreenMenuItem;
import component.view.Problems;

/**
 * Description   : Functional Test Script
 * @author eric
 */
public class Migration_CustomAction_20To213 extends Migration_CustomAction_20To213Helper
{
	/**
	 * Script Name   : <b>Migration_CustomAction_20To213</b>
	 * Generated     : <b>Mar 6, 2012 11:19:45 PM</b>
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
		WN.openWorkFlow("pro20", "query");
		WorkFlowEditor.addCustomAction("Start Screen", new WFScreenMenuItem().name("item1")
				.type("Open")
				.screen("Department_update_instance"));
		vpManual("customAction", true, WorkFlowEditor.hasLinkBetween("Start Screen", "Department_update_instance")).performTest();
	
	}
}

