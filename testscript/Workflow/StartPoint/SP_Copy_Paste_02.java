package testscript.Workflow.StartPoint;
import resources.testscript.Workflow.StartPoint.SP_Copy_Paste_02Helper;
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
 * @author ffan
 */
public class SP_Copy_Paste_02 extends SP_Copy_Paste_02Helper
{
	/**
	 * Script Name   : <b>SP_Copy_Paste_02</b>
	 * Generated     : <b>Nov 25, 2011 6:24:48 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/11/25
	 * @author ffan
	 */
	public void testMain(Object[] args) 
	{
		// TODO Insert code here
		WN.useProject(Cfg.projectName);
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name("myWF"));
		WorkFlowEditor.addStartingPoint(new StartPoint()
				.type(WorkFlow.SP_ACTIVATE));
		
		DOF.getWFActivateFlowStartingPointFigure().click(RIGHT);
		vpManual("cutAndPaste", false, MenuHelper.isItemEnabled(DOF.getMenu(),"Edit->Copy")).performTest();
	}
}
//passed
