package testscript.Workflow.Controls;
import resources.testscript.Workflow.Controls.Script1Helper;
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
import com.sybase.automation.framework.widget.helper.WO;
import component.entity.PropertiesView;

/**
 * Description   : Functional Test Script
 * @author xjf
 */
public class Script1 extends Script1Helper
{
	/**
	 * Script Name   : <b>Script1</b>
	 * Generated     : <b>Nov 21, 2011 6:14:43 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2011/11/21
	 * @author xjf
	 */
	public void testMain(Object[] args) 
	{
		// TODO Insert code here
		
		DOF.getButton(DOF.getDialog("Key"), "&Edit...").click();
	}
}

