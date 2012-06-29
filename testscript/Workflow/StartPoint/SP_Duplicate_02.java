package testscript.Workflow.StartPoint;
import resources.testscript.Workflow.StartPoint.SP_Duplicate_02Helper;
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
import com.sybase.automation.framework.widget.helper.MenuHelper;
import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.WorkFlowEditor;
import component.entity.model.StartPoint;

/**
 * Description   : Functional Test Script
 * @author eric
 */
public class SP_Duplicate_02 extends SP_Duplicate_02Helper
{
	/**
	 * Script Name   : <b>SP_Duplicate_02</b>
	 * Generated     : <b>Sep 14, 2011 4:38:58 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/09/14
	 * @author FANFEI
	 */
	public void testMain(Object[] args) 
	{
		// TODO Insert code here
		WN.useProject(Cfg.projectName);
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name(Cfg.wfName));
		WorkFlowEditor.addStartingPoint(new StartPoint()
				.type(WorkFlow.SP_CREDENTIAL_REQUEST));
		vpManual("Duplicate", false, MenuHelper.hasItem(DOF.getMenu(),"Duplicate")).performTest();
	}
}
//passed
