package testscript.Workflow.Credential;
import resources.testscript.Workflow.Credential.C618608_CachedCredential_WarningHelper;
import testscript.Workflow.cfg.Cfg;

import com.rational.test.ft.object.interfaces.ToggleGUITestObject;
import com.sybase.automation.framework.widget.DOF;
import component.entity.EE;
import component.entity.MainMenu;
import component.entity.WN;
import component.entity.WorkFlow;
import component.view.Problems;
import component.view.properties.workflow.WFControlObject;

/**
 * Description   : Functional Test Script
 * @author flvxp
 */
public class C618608_CachedCredential_Warning extends C618608_CachedCredential_WarningHelper
{
	/**
	 * Script Name   : <b>C618608_CachedCredential_Warning</b>
	 * Generated     : <b>Oct 13, 2011 6:17:46 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/10/13
	 * @author flvxp
	 */
	public void testMain(Object[] args) 
	{
		WN.useProject(Cfg.projectName);
		// MBO
		EE.dnd("Database Connections->My Sample Database->sampledb->Tables->department (dba)");
		// WF
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name("myWF")
				.option(WorkFlow.SP_SERVER_INIT)
				.option(WorkFlow.SP_CREDENTIAL_REQUEST)
				.mbo("Department")
				.objectQuery("findAll"));
		WN.openWorkFlow(Cfg.projectName, "myWF.xbw");
		DOF.getCTabItem(DOF.getRoot(), "Flow Design").click();
		DOF.getCTabItem(DOF.getRoot(), "Properties").click();
		ToggleGUITestObject obj = WFControlObject.getCheckBoxOnRoot("Authentication", "Use static credentials");
		obj.clickToState(NOT_SELECTED);
		MainMenu.saveAll();
		Object o = obj.invoke("getSelection");
		vpManual("selected", true, o.equals(false)).performTest();
		//
		vpManual("Warning", true, (Problems.getWarnings().get(0)).getDescription().toString().equals("The credentials used in a notification triggered workflow are not cached between invocations.  Consider using static credentials for notification triggered workflows.")).performTest();
	}
}

