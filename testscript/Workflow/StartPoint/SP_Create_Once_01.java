package testscript.Workflow.StartPoint;
import resources.testscript.Workflow.StartPoint.SP_Create_Once_01Helper;
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
import component.entity.EE;
import component.entity.PropertiesView;
import component.entity.WN;
import component.entity.WorkFlow;
import component.entity.WorkFlowEditor;
import component.entity.model.StartPoint;

/**
 * Description   : Functional Test Script
 * @author eric
 */
public class SP_Create_Once_01 extends SP_Create_Once_01Helper
{
	/**
	 * Script Name   : <b>SP_Create_Once_01</b>
	 * Generated     : <b>Sep 2, 2011 1:10:02 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/09/02
	 * @author eric
	 */
	public void testMain(Object[] args) 
	{
		WN.useProject(Cfg.projectName);
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name("myWF"));
		vpManual("Properies", true, WorkFlowEditor.addStartingPoint(new StartPoint().type(WorkFlow.SP_ACTIVATE))).performTest();
		vpManual("Properies", false, WorkFlowEditor.addStartingPoint(new StartPoint().type(WorkFlow.SP_ACTIVATE))).performTest();
	}
}
//passed
