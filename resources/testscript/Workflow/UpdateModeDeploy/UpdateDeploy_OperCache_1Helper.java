// DO NOT EDIT: This file is automatically generated.
//
// Only the associated template file should be edited directly.
// Helper class files are automatically regenerated from the template
// files at various times, including record actions and test object
// insertion actions.  Any changes made directly to a helper class
// file will be lost when automatically updated.

package resources.testscript.Workflow.UpdateModeDeploy;

import com.rational.test.ft.object.interfaces.*;
import com.rational.test.ft.object.interfaces.SAP.*;
import com.rational.test.ft.object.interfaces.WPF.*;
import com.rational.test.ft.object.interfaces.siebel.*;
import com.rational.test.ft.object.interfaces.flex.*;
import com.rational.test.ft.object.interfaces.dojo.*;
import com.rational.test.ft.script.*;
import com.rational.test.ft.vp.IFtVerificationPoint;

/**
 * Script Name   : <b>UpdateDeploy_OperCache_1</b><br>
 * Generated     : <b>2012/03/27 2:42:01 AM</b><br>
 * Description   : Helper class for script<br>
 * Original Host : Windows XP x86 5.1 build 2600 Service Pack 2 <br>
 * 
 * @since  March 27, 2012
 * @author lee
 */
public abstract class UpdateDeploy_OperCache_1Helper extends RationalTestScript
{
	/**
	 * TabFolder: with default state.
	 *		.mappableClassIndex : 0
	 * 		.class : org.eclipse.swt.widgets.TabFolder
	 * 		tabCount : 6
	 * 		.tabs : {Data Source,Definition,Operation Parameters,Client Parameters,Roles,Cache Polic ...
	 * 		.classIndex : 0
	 */
	protected GuiSubitemTestObject tabFolder() 
	{
		return new GuiSubitemTestObject(
                        getMappedTestObject("tabFolder"));
	}
	/**
	 * TabFolder: with specific test context and state.
	 *		.mappableClassIndex : 0
	 * 		.class : org.eclipse.swt.widgets.TabFolder
	 * 		tabCount : 6
	 * 		.tabs : {Data Source,Definition,Operation Parameters,Client Parameters,Roles,Cache Polic ...
	 * 		.classIndex : 0
	 */
	protected GuiSubitemTestObject tabFolder(TestObject anchor, long flags) 
	{
		return new GuiSubitemTestObject(
                        getMappedTestObject("tabFolder"), anchor, flags);
	}
	
	

	protected UpdateDeploy_OperCache_1Helper()
	{
		setScriptName("testscript.Workflow.UpdateModeDeploy.UpdateDeploy_OperCache_1");
	}
	
}
