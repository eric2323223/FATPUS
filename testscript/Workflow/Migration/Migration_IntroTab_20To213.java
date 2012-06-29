package testscript.Workflow.Migration;
import resources.testscript.Workflow.Migration.Migration_IntroTab_20To213Helper;
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
import com.sybase.automation.framework.widget.DOF;
import com.sybase.automation.framework.widget.helper.TabFolderHelper;

import component.entity.Preference;
import component.entity.PropertiesView;
import component.entity.WN;
import component.entity.WorkFlowEditor;
import component.entity.model.WFScreenMenuItem;
import component.entity.model.WorkFlowPackage;
import component.view.Problems;

/**
 * Description   : Functional Test Script
 * @author eric
 */
public class Migration_IntroTab_20To213 extends Migration_IntroTab_20To213Helper
{
	/**
	 * Script Name   : <b>Migration_IntroTab_20To213</b>
	 * Generated     : <b>Mar 6, 2012 10:04:06 PM</b>
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
		Preference.setDisplayIntroductionTabInWorkflowEditor(false);
		WorkFlowEditor.open("pro20", "query");
		vpManual("hasIntrotab",false,TabFolderHelper.hasItem( DOF.getCTabFolder(DOF.getRoot(),"Flow Design"), "Introduction")).performTest();
		
		Preference.setDisplayIntroductionTabInWorkflowEditor(true);
		
	}
}

