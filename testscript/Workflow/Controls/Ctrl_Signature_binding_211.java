package testscript.Workflow.Controls;
import resources.testscript.Workflow.Controls.Ctrl_Signature_binding_211Helper;
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

import component.entity.PropertiesView;
import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.WorkFlowEditor;
import component.entity.model.WFSignature;

/**
 * Description   : Functional Test Script
 * @author xjf
 */
public class Ctrl_Signature_binding_211 extends Ctrl_Signature_binding_211Helper
{
	/**
	 * Script Name   : <b>Script1</b>
	 * Generated     : <b>Oct 24, 2011 3:50:52 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/10/24
	 * @author xjf
	 */
	public void testMain(Object[] args) 
	{
		// TODO Insert code here
		WN.useProject(Cfg.projectName);
		EE.dnd(EE.parseResourcePath("Database Connections->My Sample Database->sampledb->Tables->department (dba)"), 10, 10);
	
		 WN.deployProject(new DeployOption().startParameter(Cfg.projectName)
					.server("My Unwired Server")
					.serverConnectionMapping("My Sample Database,sampledb"));
		
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName).name("wfslider")
				.option(WorkFlow.SP_SERVER_INIT));


		PropertiesView.jumpStart(new WorkFlow()
		        .mbo("Department")
				.objectQuery("findByPrimaryKey")
				.subject("deptid=2")
				.subjectMatchingRule("deptid=")
				.setParameterValue("dept_id,Subject,deptid="));

		WorkFlowEditor.addScreen("linktoemail");
		WorkFlowEditor.link(WorkFlow.SP_SERVER_INIT, "linktoemail");
		
		WorkFlowEditor.addWidget(Cfg.projectName, "wfslider.xbw", "linktoemail", new WFSignature().label("signature")
				.newKeyBindMbo("signaturekey,string,Department,dept_id"));
		
		//Verify the new key added to the email event but not bound to the signature control
		PropertiesView.clickTab("General");
		System.out.print("*******"+PropertiesView.verifykeylist());
		vpManual("keylist","",PropertiesView.verifykeylist()).performTest();
		DOF.getCTabItem(DOF.getRoot(), "Screen Design").click(atPoint(25,10));
		DOF.getWFScreenDesignCanvas().click();
		PropertiesView.clickTab("Keys");		
		vpManual("ifkeyexist","true",TableHelper.hasDataInColumn(DOF.getTable(DOF.getRoot()),"Key Name","signaturekey")).performTest();
		//end
	
		
	}
}

