package testscript.Workflow.Keys;
import resources.testscript.Workflow.Keys.CredCacheKey_1Helper;
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

import component.entity.MainMenu;
import component.entity.PropertiesView;
import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.WorkFlowEditor;
import component.entity.model.Module;
import component.entity.model.StartPoint;

/**
 * Description   : Functional Test Script
 * @author ffan
 */
public class CredCacheKey_1 extends CredCacheKey_1Helper
{
	/**
	 * Script Name   : <b>CredCacheKey_1</b>
	 * Generated     : <b>Aug 28, 2011 7:39:46 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/09/30
	 * @author ffan
	 */
	public void testMain(Object[] args) 
	{
//		 TODO Insert code here
		WN.useProject(Cfg.projectName);
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName).name("myWF")
				.option(WorkFlow.SP_CLIENT_INIT)
				.option(WorkFlow.SP_CREDENTIAL_REQUEST));
		
		MainMenu.saveAll();
		WorkFlowEditor.showPropertiesViewInFD();
		System.out.println(PropertiesView.getContent("cck"));
		vpManual("getMse", "myWF_credentials" ,PropertiesView.getContent("cck") ).performTest();
		PropertiesView.editModule(new Module().credentialCacheKey("newCCK"));
    	vpManual("getMse", "" , PropertiesView.getStatusMessage()).performTest();
    	PropertiesView.editModule(new Module().credentialCacheKey("!@#$%^&*(~"));
    	vpManual("getMse", "Value not updated, !@#$%^&&*(~ is not a valid credentials cache key." , PropertiesView.getStatusMessage()).performTest();
	}
}
//passed
