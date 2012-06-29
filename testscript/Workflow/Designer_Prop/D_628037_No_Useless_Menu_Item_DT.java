package testscript.Workflow.Designer_Prop;
import resources.testscript.Workflow.Designer_Prop.D_628037_No_Useless_Menu_Item_DTHelper;
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
import component.entity.model.Widget;

/**
 * Description   : Functional Test Script
 * @author ffan
 */
public class D_628037_No_Useless_Menu_Item_DT extends D_628037_No_Useless_Menu_Item_DTHelper
{
	/**
	 * Script Name   : <b>No_Useless_Menu_Item_DT</b>
	 * Generated     : <b>Aug 27, 2011 5:37:22 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/08/27
	 * @author ffan
	 */
	public void testMain(Object[] args) 
	{
		// TODO Insert code here
		WN.useProject(Cfg.projectName);
		WN.createWorkFlow(new WorkFlow().startParameter(Cfg.projectName)
				.name("myWF"));
		
		WorkFlowEditor.addStartingPoint(new StartPoint()
				.type(WorkFlow.SP_CLIENT_INIT));
		WorkFlowEditor.addScreen("screen1");
		vpManual("DeleteFromModel", false, MenuHelper.hasItem(DOF.getMenu(),"Delete from Model")).performTest();
		
	}
}
//PASSED