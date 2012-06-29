package testscript.Workflow.StartPoint;
import resources.testscript.Workflow.StartPoint.SP_Cut_Paste_03Helper;
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
public class SP_Cut_Paste_03 extends SP_Cut_Paste_03Helper
{
	/**
	 * Script Name   : <b>SP_Cut_Paste_03</b>
	 * Generated     : <b>Sep 4, 2011 10:20:05 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/09/04
	 * @author eric
	 */
	public void testMain(Object[] args) 
	{
		WN.useProject(Cfg.projectName);
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name("myWF"));
		WorkFlowEditor.addStartingPoint(new StartPoint()
				.type(WorkFlow.SP_CREDENTIAL_REQUEST));
		vpManual("cutAndPaste", false, MenuHelper.isItemEnabled(DOF.getMenu(),"Edit->Cut")).performTest();
	}
}
//passed
